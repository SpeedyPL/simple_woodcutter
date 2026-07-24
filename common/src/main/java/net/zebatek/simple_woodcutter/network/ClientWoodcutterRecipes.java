package net.zebatek.simple_woodcutter.network;

import net.zebatek.simple_woodcutter.recipe.WoodcutterRecipeView;

import java.util.ArrayList;
import java.util.List;

public class ClientWoodcutterRecipes {
    private static List<WoodcutterRecipeView> recipes = new ArrayList<>();
    private static Runnable onUpdate = () -> {};

    public static void set(List<WoodcutterRecipeView> newRecipes) {
        recipes = newRecipes;
        onUpdate.run();
    }

    public static List<WoodcutterRecipeView> get() {
        return recipes;
    }

    public static void setOnUpdate(Runnable callback) {
        onUpdate = callback;
    }
}