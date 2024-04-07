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
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;

import net.minecraft.util.math.BlockPos;

import net.minecraft.world.World;

import net.olin.blockrail.screen.tradecontrollerscreen.TradeControllerBlockScreenHandler;

import org.jetbrains.annotations.Nullable;

public class TradeControllerBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
	private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

	private static final int INPUT_SLOT_1 = 0;
	private static final int INPUT_SLOT_2 = 1;
	private static final int OUTPUT_SLOT = 2;

    public TradeControllerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TRADE_CONTROLLER_BLOCK_ENTITY, pos, state);

		};

	@Override
	public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) { buf.writeBlockPos(this.pos); }

	@Override
	protected void writeNbt(NbtCompound nbt) {
		super.writeNbt(nbt);
		Inventories.writeNbt(nbt, inventory);
	}

	@Override
	public void readNbt(NbtCompound nbt) {
		super.readNbt(nbt);
		Inventories.readNbt(nbt, inventory);
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
	}
}
