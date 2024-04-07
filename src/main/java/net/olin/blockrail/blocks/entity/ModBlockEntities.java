package net.olin.blockrail.blocks.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.olin.blockrail.BlockRail;
import net.olin.blockrail.blocks.ModBlocks;


public class ModBlockEntities {
	public static final BlockEntityType<ExportStationBlockEntity> EXPORT_STATION_BLOCK_ENTITY =
			Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(BlockRail.MOD_ID, "export_block_be"),
					FabricBlockEntityTypeBuilder.create(ExportStationBlockEntity::new,
							ModBlocks.EXPORT_BLOCK).build());

	public static final BlockEntityType<TradeControllerBlockEntity> TRADE_CONTROLLER_BLOCK_ENTITY =
			Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(BlockRail.MOD_ID, "trade_controller_block_be"),
					FabricBlockEntityTypeBuilder.create(TradeControllerBlockEntity::new,
							ModBlocks.TRADE_CONTROLLER_BLOCK).build());



	public static void registerBlockEntities() {
		BlockRail.LOGGER.info("Loading block entities for " + BlockRail.MOD_ID);
	}

}
