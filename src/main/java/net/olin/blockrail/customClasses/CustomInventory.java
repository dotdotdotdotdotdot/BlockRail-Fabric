//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.olin.blockrail.customClasses;

import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Clearable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface CustomInventory extends Clearable {
    int MAX_COUNT_PER_STACK = 64;
    int field_42619 = 8;

    int size();

    boolean isEmpty();

    ItemStack getStack(int slot);

    ItemStack removeStack(int slot, int amount);

    ItemStack removeStack(int slot);

    void setStack(int slot, ItemStack stack);

    default int getMaxCountPerStack() {
        return 64;
    }

    void markDirty();

    boolean canPlayerUse(PlayerEntity player);

    default void onOpen(PlayerEntity player) {
    }

    default void onClose(PlayerEntity player) {
    }

    default boolean isValid(int slot, ItemStack stack) {
        return true;
    }

    default boolean canTransferTo(Inventory hopperInventory, int slot, ItemStack stack) {
        return true;
    }

    default int count(Item item) {
        int i = 0;

        for(int j = 0; j < this.size(); ++j) {
            ItemStack itemStack = this.getStack(j);
            if (itemStack.getItem().equals(item)) {
                i += itemStack.getCount();
            }
        }

        return i;
    }

    default boolean containsAny(Set<Item> items) {
        return this.containsAny((stack) -> {
            return !stack.isEmpty() && items.contains(stack.getItem());
        });
    }

    default boolean containsAny(Predicate<ItemStack> predicate) {
        for(int i = 0; i < this.size(); ++i) {
            ItemStack itemStack = this.getStack(i);
            if (predicate.test(itemStack)) {
                return true;
            }
        }

        return false;
    }

    static boolean canPlayerUse(BlockEntity blockEntity, PlayerEntity player) {
        return canPlayerUse(blockEntity, player, 8);
    }

    static boolean canPlayerUse(BlockEntity blockEntity, PlayerEntity player, int range) {
        World world = blockEntity.getWorld();
        BlockPos blockPos = blockEntity.getPos();
        if (world == null) {
            return false;
        } else if (world.getBlockEntity(blockPos) != blockEntity) {
            return false;
        } else {
            return player.squaredDistanceTo((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5) <= (double)(range * range);
        }
    }
}
