package net.zebatek.simple_woodcutter.recipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.function.Supplier;

public class ModRecipes {
    public static Supplier<RecipeType<WoodcutterRecipe>> WOODCUTTER_TYPE;
    public static Supplier<RecipeSerializer<WoodcutterRecipe>> WOODCUTTER_SERIALIZER;
}