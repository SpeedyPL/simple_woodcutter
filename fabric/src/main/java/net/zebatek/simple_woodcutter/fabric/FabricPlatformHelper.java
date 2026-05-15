package net.zebatek.simple_woodcutter.fabric;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.zebatek.simple_woodcutter.menu.WoodcutterMenu;
import net.zebatek.simple_woodcutter.platform.IPlatformHelper;
import net.zebatek.simple_woodcutter.recipe.WoodcutterRecipe;

public class FabricPlatformHelper implements IPlatformHelper {
    @Override
    public RecipeType<WoodcutterRecipe> getRecipeType() {
        return SimpleWoodcutterFabric.WOODCUTTER_TYPE;
    }

    @Override
    public RecipeSerializer<WoodcutterRecipe> getRecipeSerializer() {
        return SimpleWoodcutterFabric.WOODCUTTER_SERIALIZER;
    }

    @Override
    public MenuType<WoodcutterMenu> getMenuType() {
        return SimpleWoodcutterFabric.WOODCUTTER_MENU;
    }
}
