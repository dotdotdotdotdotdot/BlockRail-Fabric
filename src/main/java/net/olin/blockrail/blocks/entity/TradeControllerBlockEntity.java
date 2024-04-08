package net.olin.blockrail.blocks.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.olin.blockrail.screen.tradecontrollerscreen.TradeControllerBlockScreenHandler;
import net.olin.blockrail.trades.Trades;

import org.jetbrains.annotations.Nullable;

public class TradeControllerBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
	private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
	private static final int INPUT_SLOT_1 = 0;
	private static final int INPUT_SLOT_2 = 1;
	private static final int OUTPUT_SLOT = 2;
	private int counter = 0;
	private int scrollActive = 0;
	protected PropertyDelegate propertyDelegate;

    public TradeControllerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TRADE_CONTROLLER_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
			@Override
			public int get(int index) {
				return switch (index) {
					case 0 -> TradeControllerBlockEntity.this.scrollActive = 0;
					case 1 -> TradeControllerBlockEntity.this.scrollActive = 1;
					default -> 0;
				};
			}
			@Override
			public void set(int index, int value) {
				switch (index) {
					case 0, 1 -> TradeControllerBlockEntity.this.scrollActive = value;
                }
			}
			@Override
			public int size() {
				return 2;
			}
		};
    };

	@Override
	public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) { buf.writeBlockPos(this.pos); }

	@Override
	protected void writeNbt(NbtCompound nbt) {
		super.writeNbt(nbt);
		Inventories.writeNbt(nbt, inventory);
		for (int i = 0; i < Trades.TRADES.size(); i++) {
			nbt.putInt(Trades.TRADES.get(i).getName().toString(), Trades.TRADES.get(i).getCost());
		}
	}

	@Override
	public void readNbt(NbtCompound nbt) {
		super.readNbt(nbt);
		Inventories.readNbt(nbt, inventory);
		for (int i = 0; i < Trades.TRADES.size(); i++) {
			nbt.getInt(Trades.TRADES.get(i).getName().toString());
		}
	}

	@Override
	public Text getDisplayName() { return Text.translatable("block.blockrail.trade_controller_block"); }

	@Override
	public DefaultedList<ItemStack> getItems() {
		return inventory;
	}

	@Nullable
	@Override
	public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
		return new TradeControllerBlockScreenHandler(syncId, playerInventory, this);
	}

	public void tick(World world, BlockPos pos, BlockState state) {
		if (world != null && world.isClient()) {
			return;
		}

//		if (TradeSelected()) {
//			if (isOutputSlotEmptyOrReceivable()) {
//				if (this.hasTrade()) {
//					this.consumeItem();
//				}
//			}
//		}
	}

	private boolean isOutputSlotEmptyOrReceivable() {
		return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
	}

	public boolean TradeSelected() {

		return false;
	}

	private void Trade() {
		return;
	}
}
