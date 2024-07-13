package net.olin.blockrail.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.olin.blockrail.BlockRail;
import net.olin.blockrail.blocks.ModBlocks;

public class ModItemGroups {

    public static final ItemGroup BLOCKRAIL_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(BlockRail.MOD_ID, "blockrail"),
            // Change group item identifier
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.blockrail"))
                    .icon(() -> new ItemStack(ModItems.TICKET)).entries((displayContext, entries) -> {
                        // Items
                        entries.add(ModItems.TICKET);

						// Food
						entries.add(ModItems.BUCKET_OF_BEER);

                        // Blocks
						entries.add(ModBlocks.TRADE0);
                        entries.add(ModBlocks.TRADE1);
                        entries.add(ModBlocks.TRADE2);
                        entries.add(ModBlocks.TRADE3);

                    }).build());
    public static void registerItemGroups() {
        BlockRail.LOGGER.info("Loading item-groups for " + BlockRail.MOD_ID);
    }
}
