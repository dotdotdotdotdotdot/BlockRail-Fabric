package net.olin.blockrail.screen.tradecontrollerscreen;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

import net.minecraft.screen.slot.Slot;
import net.olin.blockrail.blocks.entity.TradeControllerBlockEntity;
import net.olin.blockrail.screen.ModScreenHandlers;

import net.olin.blockrail.trades.Trade;
import net.olin.blockrail.trades.Trades;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static net.olin.blockrail.trades.Trades.TRADES;

public class TradeControllerBlockScreenHandler extends ScreenHandler {

	private final Inventory inventory;
	private final TradeControllerBlockEntity blockEntity;

	public TradeControllerBlockScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
		this(syncId, playerInventory, playerInventory.player.getWorld().getBlockEntity(buf.readBlockPos()));
	}

	public TradeControllerBlockScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity) {
		super(ModScreenHandlers.TRADE_CONTROLLER_BLOCK_SCREEN_HANDLER, syncId);
		checkSize((Inventory) blockEntity, 3);
		this.inventory = ((Inventory) blockEntity);
		inventory.onOpen(playerInventory.player);
		this.blockEntity = ((TradeControllerBlockEntity) blockEntity);

		this.addSlot(new Slot(inventory, 0, 36, 37));
		this.addSlot(new Slot(inventory, 1, 62, 37));
		this.addSlot(new Slot(inventory, 2, 120, 38));

		addPlayerInventory(playerInventory);
		addPlayerHotbar(playerInventory);

		addTradeButtons(TRADES);

	}

	public void addTradeButtons(ArrayList<Trade> trades) {

	}

	@Override
	public boolean onButtonClick(PlayerEntity player, int id) {
		return super.onButtonClick(player, id);
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

	private void addPlayerHotbar(PlayerInventory playerInventory) {
		for (int i = 0; i < 9; ++i) {
			this.addSlot(new Slot(playerInventory, i, 8+i*18, 142));
		}
	}
}
