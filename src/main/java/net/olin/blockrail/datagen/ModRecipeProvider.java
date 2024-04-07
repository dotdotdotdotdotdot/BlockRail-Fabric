package net.olin.blockrail.datagen;

import com.simibubi.create.AllBlocks;

import com.simibubi.create.content.decoration.encasing.CasingBlock;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;
import net.olin.blockrail.blocks.ModBlocks;
import net.olin.blockrail.item.ModItems;

import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
	public ModRecipeProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generate(Consumer<RecipeJsonProvider> exporter) {


//		ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SOUND_BLOCK, 1)
//				.pattern("SSS")
//				.pattern("SSS")
//				.pattern("SRS")
//				.input("S", AllBlocks.RAILWAY_CASING)
	}
}
