package net.olin.blockrail.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.olin.blockrail.BlockRail;

public class ModItems {
    public static final Item TICKET = registerItem("ticket", new Item(new FabricItemSettings()));
    public static final Item BUCKET_OF_BEER = registerItem("bucket_of_beer", new Item(new FabricItemSettings().food(ModFoodComponents.BUCKET_OF_BEER)));


	private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(BlockRail.MOD_ID, name), item);
    }
    public static void registerModItems() {
        BlockRail.LOGGER.info("Loading items for " + BlockRail.MOD_ID);
    }
}
