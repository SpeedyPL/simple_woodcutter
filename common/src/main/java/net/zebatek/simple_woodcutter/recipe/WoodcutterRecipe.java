package net.zebatek.simple_woodcutter.recipe;

import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.level.Level;
import net.zebatek.simple_woodcutter.block.ModBlocks;
import org.jetbrains.annotations.NotNull;

public class WoodcutterRecipe extends SingleItemRecipe {
    public WoodcutterRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result) {
        super(ModRecipes.WOODCUTTER_TYPE.get(), ModRecipes.WOODCUTTER_SERIALIZER.get(), id, group, ingredient, result);
    }

    @Override
    public boolean matches(Container container, Level level) {
        return this.ingredient.test(container.getItem(0));
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.WOODCUTTER_ITEM);
    }

    public static class Serializer implements RecipeSerializer<WoodcutterRecipe> {
        @Override
        public WoodcutterRecipe fromJson(ResourceLocation id, JsonObject json) {
            String group = GsonHelper.getAsString(json, "group", "");
            Ingredient ingredient = Ingredient.fromJson(json.get("ingredient"));
            String resultItem = GsonHelper.getAsString(json, "result");
            int count = GsonHelper.getAsInt(json, "count", 1);

            ItemStack resultStack = new ItemStack(BuiltInRegistries.ITEM.get(new ResourceLocation(resultItem)), count);
            return new WoodcutterRecipe(id, group, ingredient, resultStack);
        }

        @Override
        public WoodcutterRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            String group = buf.readUtf();
            Ingredient ingredient = Ingredient.fromNetwork(buf);
            ItemStack result = buf.readItem();
            return new WoodcutterRecipe(id, group, ingredient, result);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, WoodcutterRecipe recipe) {
            buf.writeUtf(recipe.getGroup());
            recipe.getIngredients().get(0).toNetwork(buf);
            buf.writeItem(recipe.getResultItem(net.minecraft.core.RegistryAccess.EMPTY));
        }
    }
}
