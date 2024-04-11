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

import net.olin.blockrail.blocks.ModBlocks;
import net.olin.blockrail.screen.tradecontrollerscreen.TradeControllerBlockScreenHandler;
import net.olin.blockrail.trades.Trade;
import net.olin.blockrail.trades.Trades;

import org.jetbrains.annotations.Nullable;

public class TradeControllerBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
	private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
	private static final int INPUT_SLOT_1 = 0;
	private static final int OUTPUT_SLOT = 1;
	private int counter = 0;
	private int tradeIndex = 0;
	private int counted = 256;
	private Trade trade;
	protected PropertyDelegate propertyDelegate;

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
					case 0 -> TradeControllerBlockEntity.this.counter = value;
					case 1 -> TradeControllerBlockEntity.this.counted = value;
					case 2 -> TradeControllerBlockEntity.this.tradeIndex = value;
                }
			}
			@Override
			public int size() {
				return 3;
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
			nbt.putInt(Trades.TRADES.get(i).getName(), Trades.TRADES.get(i).getCost());
			nbt.putInt("counter", counter);
		}
		nbt.putInt(Trades.TRADES.get(tradeIndex).getName(), tradeIndex);
	}
	@Override
	public void readNbt(NbtCompound nbt) {
		super.readNbt(nbt);
		Inventories.readNbt(nbt, inventory);
		for (int i = 0; i < Trades.TRADES.size(); i++) {
			nbt.getInt(Trades.TRADES.get(i).getName());
			nbt.getInt("counter");
		}
		nbt.getInt(Trades.TRADES.get(tradeIndex).getName());
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
		return new TradeControllerBlockScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
	}
	public void tick(World world, BlockPos pos, BlockState state) {
		if (world != null && world.isClient()) {
			return;
		}
		if (isOutputSlotEmptyOrReceivable()) {
			checkSelectedTrade();
			if (this.hasTrade(Trades.TRADES.get(tradeIndex))) {
                this.removeItem();
				this.increaseCounter();
				markDirty(world, pos, state);

				if (tradeFinished()) {
					this.tradeItem(Trades.TRADES.get(tradeIndex));
					this.resetCounter();
				}
			}
		}
	}

	private void resetCounter() {
		this.counter = 0;
		this.counted = Trades.TRADES.get(tradeIndex).getCost();
	}

	private void tradeItem(Trade trade) {
		ItemStack result = new ItemStack(trade.getItemReward().getItem(), trade.getReward());

		this.setStack(OUTPUT_SLOT, new ItemStack(result.getItem(), getStack(OUTPUT_SLOT).getCount() + result.getCount()));
	}

	private void removeItem() {
		this.removeStack(INPUT_SLOT_1, 1);
	}

	private boolean hasTrade(Trade trade) {
		boolean hasInput = getStack(INPUT_SLOT_1).getItem() == trade.getItemCost().getItem();

		return hasInput && canInsertAmountIntoOutputSlot(new ItemStack(trade.getItemReward().getItem())) && canInsertItemIntoOutputSlot(trade.getItemReward().getItem());
	}

	private void increaseCounter() {
		++this.counter;
	}
	private int checkSelectedTrade() {
		return this.tradeIndex;
	}

	private boolean tradeFinished() {
		return counter >= counted;
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
