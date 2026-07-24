package net.zebatek.simple_woodcutter.recipe;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.List;

public class WoodcutterRecipeCollector {
    public static List<WoodcutterRecipeView> collect(MinecraftServer server) {
        return server.getRecipeManager().getRecipes().stream()
                .map(RecipeHolder::value)
                .filter(recipe -> recipe.getType() == ModRecipes.getTYPE())
                .map(recipe -> (WoodcutterRecipe) recipe)
                .map(WoodcutterRecipeView::from)
                .toList();
    }
}