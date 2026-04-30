package net.zebatek.simple_woodcutter.fabric.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.zebatek.simple_woodcutter.Simple_woodcutter;
import net.zebatek.simple_woodcutter.block.ModBlocks;
import net.zebatek.simple_woodcutter.recipe.ModRecipes;
import net.zebatek.simple_woodcutter.recipe.WoodcutterRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@JeiPlugin
public class JeiWoodcuttingPluginFabric implements IModPlugin {

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return new ResourceLocation(Simple_woodcutter.MOD_ID, "jei_plugin_fabric");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new WoodcuttingCategoryFabric(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<WoodcutterRecipe> recipes = recipeManager.getAllRecipesFor(ModRecipes.WOODCUTTER_TYPE.get());

        registration.addRecipes(WoodcuttingCategoryFabric.WOODCUTTING_TYPE, recipes);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.WOODCUTTER_ITEM), WoodcuttingCategoryFabric.WOODCUTTING_TYPE);
    }
}