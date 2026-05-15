package net.zebatek.simple_woodcutter.neoforge;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.zebatek.simple_woodcutter.menu.WoodcutterMenu;
import net.zebatek.simple_woodcutter.platform.IPlatformHelper;
import net.zebatek.simple_woodcutter.recipe.WoodcutterRecipe;

public class NeoForgePlatformHelper implements IPlatformHelper {
    @Override
    public RecipeType<WoodcutterRecipe> getRecipeType() {
        return SimpleWoodcutterNeoForge.WOODCUTTER_TYPE.get();
    }

    @Override
    public RecipeSerializer<WoodcutterRecipe> getRecipeSerializer() {
        return SimpleWoodcutterNeoForge.WOODCUTTER_SERIALIZER.get();
    }

    @Override
    public MenuType<WoodcutterMenu> getMenuType() {
        return SimpleWoodcutterNeoForge.WOODCUTTER_MENU.get();
    }
}
