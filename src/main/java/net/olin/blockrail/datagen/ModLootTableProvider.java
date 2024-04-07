package net.olin.blockrail.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.olin.blockrail.blocks.ModBlocks;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
	public ModLootTableProvider(FabricDataOutput dataOutput) {
		super(dataOutput);
	}

	@Override
	public void generate() {
		addDrop(ModBlocks.EXPORT_BLOCK);
		addDrop(ModBlocks.TRADE_CONTROLLER_BLOCK);
	}
}
