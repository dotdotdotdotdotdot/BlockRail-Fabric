package net.olin.blockrail.blocks.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

import net.minecraft.world.World;

import net.olin.blockrail.item.ModItems;

import net.olin.blockrail.screen.exportscreen.ExportBlockScreenHandler;

import org.jetbrains.annotations.Nullable;

public class ExportStationBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
	private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);
	private static final int INPUT_SLOT = 0;
	private static final int OUTPUT_SLOT = 1;
	protected final PropertyDelegate propertyDelegate;
	private int progress = 0;
	private int maxProgress = 72;
	public ExportStationBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.EXPORT_STATION_BLOCK_ENTITY, pos, state);
		this.propertyDelegate = new PropertyDelegate() {
			@Override
			public int get(int index) {
				return switch (index) {
					case 0 -> ExportStationBlockEntity.this.maxProgress;
					case 1 -> ExportStationBlockEntity.this.progress;
					default -> 0;
				};
			}

			@Override
			public void set(int index, int value) {
				switch (index) {
					case 0 -> ExportStationBlockEntity.this.progress = value;
					case 1 -> ExportStationBlockEntity.this.maxProgress = value;
				}
			}

			@Override
			public int size() {
				return 2;
			}
		};
	}

	@Override
	public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
		buf.writeBlockPos(this.pos);
	}

	@Override
	protected void writeNbt(NbtCompound nbt) {
		super.writeNbt(nbt);
		Inventories.writeNbt(nbt, inventory);
		nbt.putInt("export_block.progress", progress);
	}

	@Override
	public void readNbt(NbtCompound nbt) {
		super.readNbt(nbt);
		Inventories.readNbt(nbt, inventory);
		nbt.getInt("export_block.progress");
	}

	@Override
	public Text getDisplayName() {
		return Text.translatable("block.blockrail.export_block");
	}

	@Override
	public DefaultedList<ItemStack> getItems() {
		return inventory;
	}

	@Nullable
	@Override
	public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
		return new ExportBlockScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
	}

	public void tick(World world, BlockPos pos, BlockState state) {
		if (world != null && world.isClient()){
			return;
		}

		if (isOutputSlotEmptyOrReceivable()) {
			if (this.hasTrade()) {
				this.increaseTradeProgress();
				markDirty(world, pos, state);

				if (hasTradingFinished()) {
					this.tradeItem();
					this.resetProgress();
				}
			} else {
				this.resetProgress();
			}
		} else {
			this.resetProgress();
			markDirty(world, pos, state);
		}
	}

	private void increaseTradeProgress() {
		progress++;
	}

	private void resetProgress() {
		this.progress = 0;
	}

	private void tradeItem() {
		this.removeStack(INPUT_SLOT, 1);
		ItemStack result = new ItemStack(ModItems.BUCKET_OF_BEER);

		this.setStack(OUTPUT_SLOT, new ItemStack(result.getItem(), getStack(OUTPUT_SLOT).getCount() + result.getCount()));
	}

	private boolean hasTradingFinished() {
		return progress >= maxProgress;
	}

	private boolean hasTrade() {
		ItemStack result = new ItemStack(ModItems.BUCKET_OF_BEER);
		boolean hasInput = getStack(INPUT_SLOT).getItem() == Items.EMERALD;

		return hasInput && canInsertAmountIntoOutputSlot(result) && canInsertItemIntoOutputSlot(result.getItem());
	}

	private boolean canInsertItemIntoOutputSlot(Item item) {
		return this.getStack(OUTPUT_SLOT).getItem() == item || this.getStack(OUTPUT_SLOT).isEmpty();
	}

	private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
		return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= getStack(OUTPUT_SLOT).getMaxCount();
	}

	private boolean isOutputSlotEmptyOrReceivable() {
		return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
	}
}
