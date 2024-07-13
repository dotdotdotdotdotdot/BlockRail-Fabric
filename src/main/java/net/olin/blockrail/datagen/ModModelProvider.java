package net.olin.blockrail.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.olin.blockrail.blocks.ModBlocks;
import net.olin.blockrail.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
	public ModModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
		blockStateModelGenerator.registerSimpleState(ModBlocks.TRADE0);
		blockStateModelGenerator.registerSimpleState(ModBlocks.TRADE1);
	}

	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {
		itemModelGenerator.register(ModItems.TICKET, Models.GENERATED);
		itemModelGenerator.register(ModItems.BUCKET_OF_BEER, Models.GENERATED);
	}
}
