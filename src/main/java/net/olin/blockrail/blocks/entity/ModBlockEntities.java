package net.olin.blockrail.blocks.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.olin.blockrail.BlockRail;
import net.olin.blockrail.blocks.ModBlocks;


public class ModBlockEntities {
	public static final BlockEntityType<Trade0_ent> TRADE0 =
			Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(BlockRail.MOD_ID, "trade0_controller_block_be"),
					FabricBlockEntityTypeBuilder.create(Trade0_ent::new,
							ModBlocks.TRADE0).build());


	public static final BlockEntityType<Trade1_ent> TRADE1 =
			Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(BlockRail.MOD_ID, "trade1_controller_block_be"),
					FabricBlockEntityTypeBuilder.create(Trade1_ent::new,
							ModBlocks.TRADE1).build());

	public static final BlockEntityType<Trade2_ent> TRADE2 =
			Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(BlockRail.MOD_ID, "trade2_controller_block_be"),
					FabricBlockEntityTypeBuilder.create(Trade2_ent::new,
							ModBlocks.TRADE2).build());

	public static final BlockEntityType<Trade3_ent> TRADE3 =
			Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(BlockRail.MOD_ID, "trade3_controller_block_be"),
					FabricBlockEntityTypeBuilder.create(Trade3_ent::new,
							ModBlocks.TRADE3).build());

	public static void registerBlockEntities() {
		BlockRail.LOGGER.info("Loading block entities for " + BlockRail.MOD_ID);
	}

}
