package net.olin.blockrail.trades;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class TradeRecipe implements Recipe<SimpleInventory> {
    private final Identifier id;
    private final ItemStack output;
    private final DefaultedList<Ingredient> ingredients;
    private final DefaultedList<Integer> ingredientCounts;
    public TradeRecipe(Identifier id, ItemStack output, DefaultedList<Ingredient> ingredients, DefaultedList<Integer> ingredientCounts) {
        this.id = id;
        this.output = output;
        this.ingredients = ingredients;
        this.ingredientCounts = ingredientCounts;
    }
    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if(world.isClient()) {
            return false;
        }
        return ingredients.get(0).test(inventory.getStack(0));
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return output.copy();
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public static class Type implements RecipeType<TradeRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type ();
        public static final String ID = "trade";
    }

    public int getRequiredIngredientCount() {
        return this.ingredientCounts.stream().reduce(0, Integer::sum);
    }

    public static class Serializer implements RecipeSerializer<TradeRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "trade";

        @Override
        public TradeRecipe read(Identifier id, JsonObject json) {
            ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"));

            JsonArray ingredients = JsonHelper.getArray(json, "ingredients");
            DefaultedList<Ingredient> inputs = DefaultedList.of();
            DefaultedList<Integer> counts = DefaultedList.of();

            for (JsonElement element : ingredients) {
                JsonObject ingJson = element.getAsJsonObject();
                Ingredient ing = Ingredient.fromJson(ingJson);
                int count = JsonHelper.getInt(ingJson, "count", 1);
                inputs.add(ing);
                counts.add(count);
            }

            return new TradeRecipe(id, output, inputs, counts);
        }

        @Override
        public TradeRecipe read(Identifier id, PacketByteBuf buf) {
            int size = buf.readInt();
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);
            DefaultedList<Integer> counts = DefaultedList.ofSize(size, 0);

            for (int i = 0; i < size; i++) {
                inputs.set(i, Ingredient.fromPacket(buf));
                counts.set(i, buf.readInt());
            }

            ItemStack output = buf.readItemStack();
            return new TradeRecipe(id, output, inputs, counts);
        }

        @Override
        public void write(PacketByteBuf buf, TradeRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (int i = 0; i < recipe.ingredients.size(); i++) {
                recipe.ingredients.get(i).write(buf);
                buf.writeInt(recipe.ingredientCounts.get(i));
            }
            buf.writeItemStack(recipe.output);
        }
    }
}
