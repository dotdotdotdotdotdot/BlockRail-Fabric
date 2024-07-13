package net.olin.blockrail.blocks.entity;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.olin.blockrail.item.ModItems;
import net.olin.blockrail.screen.tradecontrollerscreen.Trade3_screen_handler;
import org.jetbrains.annotations.Nullable;

public class Trade3_ent extends BlockEntity implements SidedInventory, ExtendedScreenHandlerFactory, ImplementedInventory {
	private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);
	private static final int INPUT_SLOT = 0;
	private static final int OUTPUT_SLOT = 1;
	protected final PropertyDelegate propertyDelegate;
	private int counterMax;
	private int counterCurrent;


	//Trade data
	private final int block_id = 2;

	private Item inputItem = AllItems.ANDESITE_ALLOY.asItem();
	private int inputAmount = 128; //Hur mycket input

	private Item outputItem = ModItems.TICKET;
	private int outputAmount = 1; //hur mycket output
	//

	public Trade3_ent(BlockPos pos, BlockState state) {
		super(ModBlockEntities.TRADE3, pos, state);
		this.counterMax = inputAmount;
		this.propertyDelegate = new PropertyDelegate() {
			@Override
			public int get(int index) {
				return switch (index) {
					case 0 -> Trade3_ent.this.counterCurrent;
					case 1 -> Trade3_ent.this.counterMax;

					default -> 0;

				};
			}
			@Override
			public void set(int index, int value) {
				switch (index) {
					case 0:
						Trade3_ent.this.counterCurrent = value;
					case 1:
						Trade3_ent.this.counterMax = value;
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
		return Text.empty();
	}

	@Override
	public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
		buf.writeBlockPos(this.pos);
	}

	@Override
	protected void writeNbt(NbtCompound nbt) {
		super.writeNbt(nbt);
		Inventories.writeNbt(nbt, inventory);
		nbt.putInt("counter", this.counterCurrent);
	}
	@Override
	public void readNbt(NbtCompound nbt) {
		super.readNbt(nbt);
		Inventories.readNbt(nbt, inventory);
		this.counterCurrent = nbt.getInt("counterCurrent");
	}
	@Override
	public DefaultedList<ItemStack> getItems() {
		return this.inventory;
	}

	@Nullable
	@Override
	public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
		return new Trade3_screen_handler(syncId, playerInventory, this, this.propertyDelegate);
	}

	public void tick(World world, BlockPos pos, BlockState state) {
		if (world != null && world.isClient()) {
			return;
		}

		if (isOutputSlotEmptyOrReceivable()) {
			if (this.hasTrade()) {

				this.increaseCounter();
				this.removeItem();

				if (hasTradingFinished()) {
					this.giveItem();
					this.resetProgress();
				}
			}
		}
	}

	private void increaseCounter() {
		this.counterCurrent++;
	}
	private void resetProgress() {
		this.counterCurrent = 0;
	}

	private void removeItem() {
		boolean inputItemCheck = getStack(INPUT_SLOT).getItem() == inputItem;
		if(inputItemCheck) {
			this.removeStack(INPUT_SLOT, 1);
		}
	}

	private void giveItem() {
		ItemStack result = new ItemStack(outputItem);
		this.setStack(OUTPUT_SLOT, new ItemStack(result.getItem(), getStack(OUTPUT_SLOT).getCount() + result.getCount()));
	}

	private boolean hasTradingFinished() {
		return counterCurrent >= counterMax;
	}

	private boolean hasTrade() {
		ItemStack result = new ItemStack(outputItem);
		boolean hasInput = getStack(INPUT_SLOT).getItem() == inputItem;

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

	public int[] getAvailableSlots(Direction side) {
		return new int[]{0,1};
	}

	@Override
	public boolean canExtract(int slot, ItemStack stack, Direction dir) {
		if(slot == 1){
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isValid(int slot, ItemStack stack) {
        return true;
	}


	public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
		if(slot == 0){
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean canPlayerUse(PlayerEntity player) {
		return Inventory.canPlayerUse(this, player);
	}

	@Override
	public void clear() {

	}
}
