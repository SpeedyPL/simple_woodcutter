package net.zebatek.simple_woodcutter.recipe;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public record WoodcutterRecipeView(Ingredient ingredient, ItemStack result) {
    public static final StreamCodec<RegistryFriendlyByteBuf, WoodcutterRecipeView> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, WoodcutterRecipeView::ingredient,
            ItemStack.STREAM_CODEC, WoodcutterRecipeView::result,
            WoodcutterRecipeView::new
    );

    public static WoodcutterRecipeView from(WoodcutterRecipe recipe) {
        return new WoodcutterRecipeView(recipe.getInputIngredient(), recipe.getOutputResult());
    }
}