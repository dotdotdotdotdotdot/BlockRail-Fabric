package net.olin.blockrail.trades;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.olin.blockrail.BlockRail;

public class ModRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(BlockRail.MOD_ID, TradeRecipe.Serializer.ID),
                TradeRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(BlockRail.MOD_ID, TradeRecipe.Type.ID),
                TradeRecipe.Type.INSTANCE);
    }
}
