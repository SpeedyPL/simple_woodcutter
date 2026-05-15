package net.zebatek.simple_woodcutter.platform;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.zebatek.simple_woodcutter.menu.WoodcutterMenu;
import net.zebatek.simple_woodcutter.recipe.WoodcutterRecipe;

public interface IPlatformHelper {
    RecipeType<WoodcutterRecipe> getRecipeType();
    RecipeSerializer<WoodcutterRecipe>  getRecipeSerializer();
    MenuType<WoodcutterMenu> getMenuType();
}
