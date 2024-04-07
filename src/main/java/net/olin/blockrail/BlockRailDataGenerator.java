package net.olin.blockrail;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.olin.blockrail.datagen.ModBlockTagProvider;
import net.olin.blockrail.datagen.ModItemTagProvider;
import net.olin.blockrail.datagen.ModLootTableProvider;
import net.olin.blockrail.datagen.ModModelProvider;
import net.olin.blockrail.datagen.ModRecipeProvider;

public class BlockRailDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeProvider::new);
	}
}
