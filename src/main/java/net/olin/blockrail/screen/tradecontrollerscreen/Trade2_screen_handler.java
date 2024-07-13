package net.olin.blockrail.screen.tradecontrollerscreen;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.olin.blockrail.blocks.entity.Trade2_ent;
import net.olin.blockrail.screen.ModScreenHandlers;
import org.jetbrains.annotations.Nullable;

public class Trade2_screen_handler extends ScreenHandler {

	private final Inventory inventory;
	public final Trade2_ent blockEntity;
	private final PropertyDelegate propertyDelegate;

	public Trade2_screen_handler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
		this(syncId, playerInventory, playerInventory.player.getWorld().getBlockEntity(buf.readBlockPos()),
				new ArrayPropertyDelegate(3));
	}

	public Trade2_screen_handler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate arrayPropertyDelegate) {
		super(ModScreenHandlers.TRADE2_SCREEN_HANDLER, syncId);
        checkSize((Inventory) blockEntity, 2);
		this.inventory = ((Inventory) blockEntity);
        inventory.onOpen(playerInventory.player);
		this.propertyDelegate = arrayPropertyDelegate;
		this.blockEntity = ((Trade2_ent) blockEntity);

		this.addSlot(new Slot(inventory, 0, 26, 34));
		this.addSlot(new Slot(inventory, 1, 134, 34));

		addPlayerInventory(playerInventory);
		addPlayerHotbar(playerInventory);

		addProperties(arrayPropertyDelegate);
	}

	public boolean isTrading() { return propertyDelegate.get(0) > 0; }

	public int getCounterProgress() {
		int counter = this.propertyDelegate.get(0);
		int counted = this.propertyDelegate.get(1);
		int counterBar = 59;

		return counted != 0 && counter != 0 ? counter * counterBar / counted : 0;
	}

	public int getCount() {
		return this.propertyDelegate.get(0);
	}

	public void setSelectedButtonIndex(int i) {
		propertyDelegate.set(2, i);
	}

	public int getSelectedButtonIndex() {
		return this.propertyDelegate.get(2);
	}




	protected Trade2_screen_handler(@Nullable ScreenHandlerType<?> type, int syncId, Inventory inventory, Trade2_ent blockEntity, PropertyDelegate propertyDelegate) {
		super(type, syncId);
		this.inventory = inventory;
		this.blockEntity = blockEntity;
		this.propertyDelegate = propertyDelegate;
    }


	@Override
	public ItemStack quickMove(PlayerEntity player, int invSlot) {
		ItemStack newStack = ItemStack.EMPTY;
		Slot slot = this.slots.get(invSlot);
		if (slot != null && slot.hasStack()) {
			ItemStack originalStack = slot.getStack();
			newStack = originalStack.copy();
			if (invSlot < this.inventory.size()) {
				if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
				return ItemStack.EMPTY;
			}

			if (originalStack.isEmpty()) {
				slot.setStack(ItemStack.EMPTY);
			} else {
				slot.markDirty();
			}
		}
		return newStack;
	}

	@Override
	public boolean canUse(PlayerEntity player) {
		return this.inventory.canPlayerUse(player);
	}

	private void addPlayerInventory(PlayerInventory playerInventory) {
		for (int i = 0; i < 3; ++i) {
			for (int l = 0; l < 9; ++l) {
				this.addSlot(new Slot(playerInventory, l+i*9+9,8+l*18,84+i*18));
			}
		}
	}

	public int getScaledProgress() {
		int progress = this.propertyDelegate.get(0);
		int maxProgress = this.propertyDelegate.get(1);
		int progressArrowSize = 80;

		return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
	}

	private void addPlayerHotbar(PlayerInventory playerInventory) {
		for (int i = 0; i < 9; ++i) {
			this.addSlot(new Slot(playerInventory, i, 8+i*18, 142));
		}
	}
}
