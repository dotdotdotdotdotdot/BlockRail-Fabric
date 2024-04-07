package net.olin.blockrail.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.olin.blockrail.BlockRail;
import net.olin.blockrail.blocks.custom.ExportStationBlock;
import net.olin.blockrail.blocks.custom.TradeControllerBlock;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;


public class ModBlocks {
    public static final Block EXPORT_BLOCK = registerBlock("export_block",
            new ExportStationBlock(FabricBlockSettings.copyOf(Blocks.NETHERITE_BLOCK).nonOpaque()));
	public static final Block TRADE_CONTROLLER_BLOCK = registerBlock("trade_controller_block",
			new TradeControllerBlock(FabricBlockSettings.copyOf(Blocks.NETHERITE_BLOCK).nonOpaque()));

	private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(BlockRail.MOD_ID, name), block);
    }
    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(BlockRail.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }
    public static void registerModBlocks() {
        BlockRail.LOGGER.info("Loading blocks for " + BlockRail.MOD_ID);
    }
}
