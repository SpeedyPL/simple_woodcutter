package net.zebatek.simple_woodcutter.recipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.zebatek.simple_woodcutter.platform.Services;

public class ModRecipes {
    public static RecipeType<WoodcutterRecipe> getTYPE() {
        return Services.PLATFORM.getRecipeType();
    }

    public static RecipeSerializer<WoodcutterRecipe> getSERIALIZER() {
        return Services.PLATFORM.getRecipeSerializer();
    }
}