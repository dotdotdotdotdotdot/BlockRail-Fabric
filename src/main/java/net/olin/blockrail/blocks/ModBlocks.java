package net.olin.blockrail.blocks;

import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.AllBlocks;

import com.simibubi.create.content.redstone.displayLink.source.StationSummaryDisplaySource;
import com.simibubi.create.content.redstone.displayLink.source.TrainStatusDisplaySource;
import com.simibubi.create.content.trains.graph.EdgePointType;
import com.simibubi.create.content.trains.station.StationBlock;
import com.simibubi.create.content.trains.track.TrackTargetingBlockItem;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.olin.blockrail.BlockRail;
import net.olin.blockrail.blocks.custom.ExportStationBlock;
import net.olin.blockrail.blocks.custom.SoundBlock;
import net.olin.blockrail.blocks.custom.TradeControllerBlock;

import java.util.Properties;

import static com.simibubi.create.Create.REGISTRATE;
import static com.simibubi.create.content.redstone.displayLink.AllDisplayBehaviours.assignDataBehaviour;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;


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
