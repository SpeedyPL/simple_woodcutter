package net.zebatek.simple_woodcutter.api;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.zebatek.simple_woodcutter.recipe.ModRecipes;
import net.zebatek.simple_woodcutter.recipe.WoodcutterRecipe;

public class WoodcutterAPI {
    public static RecipeType<WoodcutterRecipe> getRecipeType() {
        return ModRecipes.getTYPE();
    }

    public static RecipeSerializer<WoodcutterRecipe> getRecipeSerializer() {
        return ModRecipes.getSERIALIZER();
    }
}