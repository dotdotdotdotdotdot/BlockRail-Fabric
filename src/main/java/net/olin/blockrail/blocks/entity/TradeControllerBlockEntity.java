package net.olin.blockrail.blocks.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtInt;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.Ingredient;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.olin.blockrail.BlockRail;
import net.olin.blockrail.item.ModItems;
import net.olin.blockrail.screen.tradecontrollerscreen.TradeControllerBlockScreenHandler;
import net.olin.blockrail.trades.TradeRecipe;
import org.jetbrains.annotations.Nullable;
import java.util.Optional;

public class TradeControllerBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
	private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);
	private static final int INPUT_SLOT = 0;
	private static final int OUTPUT_SLOT = 1;
	protected final PropertyDelegate propertyDelegate;
	private int counter;
	private int counted;
	private int tradeIndex;

	public TradeControllerBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.TRADE_CONTROLLER_BLOCK_ENTITY, pos, state);
		this.propertyDelegate = new PropertyDelegate() {
			@Override
			public int get(int index) {
				return switch (index) {
					case 0 -> TradeControllerBlockEntity.this.counter;
					case 1 -> TradeControllerBlockEntity.this.counted;
					case 2 -> TradeControllerBlockEntity.this.tradeIndex;

					default -> 0;

				};
			}

			@Override
			public void set(int index, int value) {
				switch (index) {
					case 0: TradeControllerBlockEntity.this.counter = value;
					case 1: TradeControllerBlockEntity.this.counted = value;
					case 2: TradeControllerBlockEntity.this.tradeIndex = value;
				}
			}
			@Override
			public int size() {
				return 2;
			}
		};
	}
	@Override
	public Text getDisplayName() {
		return Text.translatable("block.blockrail.trade_controller_block");
	}

	@Override
	public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
		buf.writeBlockPos(this.pos);
	}

	@Override
	public void readNbt(NbtCompound nbt) {
		super.readNbt(nbt);
		Inventories.readNbt(nbt, inventory);
		counter = nbt.getInt("counter");
		tradeIndex = nbt.getInt("trade_index");
		System.out.println("readnbt: " + tradeIndex);
	}

	@Override
	protected void writeNbt(NbtCompound nbt) {
		super.writeNbt(nbt);
		Inventories.writeNbt(nbt, inventory);
		nbt.putInt("counter", counter);
		nbt.putInt("trade_index", tradeIndex);
		System.out.println("write: " + tradeIndex);
	}

	@Override
	public DefaultedList<ItemStack> getItems() {
		return this.inventory;
	}

	@Nullable
	@Override
	public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
		return new TradeControllerBlockScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
	}

	public void tick(World world, BlockPos pos, BlockState state) {
		if (world != null && world.isClient()) {
			return;
		}



		if (isOutputSlotEmptyOrReceivable()) {
			markDirty(world, pos, state);
			if (this.hasTrade()) {
				this.increaseCounter();
				markDirty(world, pos, state);

				if (hasTradingFinished()) {
					this.tradeItem();
					this.resetProgress();
				}
			} else {
				this.resetProgress();
			}
		}
	}

	public void setTradeIndex(int index) {
		tradeIndex = index;
		markDirty();
	}

	public int getTradeIndex() {
		return tradeIndex;
	}

	private void increaseCounter() {
		counter++;
	}

	private void resetProgress() {
		this.counter = 0;
	}

	private void tradeItem() {
		this.removeStack(INPUT_SLOT, 1);
		ItemStack result = new ItemStack(ModItems.BUCKET_OF_BEER);

		this.setStack(OUTPUT_SLOT, new ItemStack(result.getItem(), getStack(OUTPUT_SLOT).getCount() + result.getCount()));
	}

	private boolean hasTradingFinished() {
		return counter >= counted;
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
