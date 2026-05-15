package net.zebatek.simple_woodcutter.forge;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.zebatek.simple_woodcutter.menu.WoodcutterMenu;
import net.zebatek.simple_woodcutter.platform.IPlatformHelper;
import net.zebatek.simple_woodcutter.recipe.WoodcutterRecipe;

public class ForgePlatformHelper implements IPlatformHelper {
    @Override
    public RecipeType<WoodcutterRecipe> getRecipeType() {
        return SimpleWoodcutterForge.WOODCUTTER_TYPE.get();
    }

    @Override
    public RecipeSerializer<WoodcutterRecipe> getRecipeSerializer() {
        return SimpleWoodcutterForge.WOODCUTTER_SERIALIZER.get();
    }

    @Override
    public MenuType<WoodcutterMenu> getMenuType() {
        return SimpleWoodcutterForge.FG_WOODCUTTER_MENU.get();
    }
}
