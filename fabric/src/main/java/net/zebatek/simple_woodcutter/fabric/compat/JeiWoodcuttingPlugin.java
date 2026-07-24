package net.zebatek.simple_woodcutter.fabric.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.IRecipeManager;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.zebatek.simple_woodcutter.SimpleWoodcutter;
import net.zebatek.simple_woodcutter.block.ModBlocks;
import net.zebatek.simple_woodcutter.network.ClientWoodcutterRecipes;
import net.zebatek.simple_woodcutter.recipe.WoodcutterRecipeView;

import java.util.List;

@JeiPlugin
public class JeiWoodcuttingPlugin implements IModPlugin {
    private IRecipeManager recipeManager;
    private boolean pushed = false;

    @Override
    public Identifier getPluginUid() {
        return Identifier.fromNamespaceAndPath(SimpleWoodcutter.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new WoodcuttingCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        List<WoodcutterRecipeView> current = ClientWoodcutterRecipes.get();
        if (!current.isEmpty()) {
            registration.addRecipes(WoodcuttingCategory.WOODCUTTING_TYPE, current);
            this.pushed = true;
        }
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        this.recipeManager = jeiRuntime.getRecipeManager();
        ClientWoodcutterRecipes.setOnUpdate(this::tryPush);
        tryPush();
    }

    private void tryPush() {
        if (this.pushed || this.recipeManager == null) {
            return;
        }
        List<WoodcutterRecipeView> current = ClientWoodcutterRecipes.get();
        if (!current.isEmpty()) {
            this.recipeManager.addRecipes(WoodcuttingCategory.WOODCUTTING_TYPE, current);
            this.pushed = true;
        }
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.WOODCUTTER_ITEM), WoodcuttingCategory.WOODCUTTING_TYPE);
    }
}