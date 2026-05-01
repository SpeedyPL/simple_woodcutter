package net.zebatek.simple_woodcutter.api;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.zebatek.simple_woodcutter.recipe.ModRecipes;
import net.zebatek.simple_woodcutter.recipe.WoodcutterRecipe;

public class WoodcutterAPI {
    public static final RecipeType<WoodcutterRecipe> TYPE = ModRecipes.WOODCUTTER_TYPE.get();
    public static final RecipeSerializer<WoodcutterRecipe> SERIALIZER = ModRecipes.WOODCUTTER_SERIALIZER.get();
}