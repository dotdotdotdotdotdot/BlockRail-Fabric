package net.olin.blockrail;

import net.fabricmc.api.ModInitializer;

import net.olin.blockrail.blocks.ModBlocks;
import net.olin.blockrail.blocks.entity.ModBlockEntities;
import net.olin.blockrail.item.ModItemGroups;
import net.olin.blockrail.item.ModItems;
import net.olin.blockrail.screen.ModScreenHandlers;
import net.olin.blockrail.util.ModTags;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlockRail implements ModInitializer {
	public static final String MOD_ID = "blockrail";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Loading " + MOD_ID);

		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();

		ModBlocks.registerModBlocks();

		ModBlockEntities.registerBlockEntities();
		ModTags.registerModTags();

		ModScreenHandlers.registerScreenHandler();



	}
}
