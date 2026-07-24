package net.zebatek.simple_woodcutter.fabric.compat;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.zebatek.simple_woodcutter.SimpleWoodcutter;
import net.zebatek.simple_woodcutter.block.ModBlocks;
import net.zebatek.simple_woodcutter.recipe.WoodcutterRecipeView;

public class WoodcuttingCategory extends AbstractRecipeCategory<WoodcutterRecipeView> {

    public static final Identifier UID = Identifier.fromNamespaceAndPath(SimpleWoodcutter.MOD_ID, "woodcutting");
    public static final RecipeType<WoodcutterRecipeView> WOODCUTTING_TYPE = new RecipeType<>(UID, WoodcutterRecipeView.class);

    public WoodcuttingCategory(IGuiHelper helper) {
        super(
                WOODCUTTING_TYPE,
                Component.translatable("container.simple_woodcutter.woodcutter"),
                helper.createDrawableItemLike(ModBlocks.WOODCUTTER),
                82,
                34
        );
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, WoodcutterRecipeView recipe, IFocusGroup focuses) {
        builder.addInputSlot(1, 9)
                .setStandardSlotBackground()
                .addIngredients(recipe.ingredient());

        builder.addOutputSlot(61, 9)
                .setOutputSlotBackground()
                .addItemStack(recipe.result());
    }

    @Override
    public void createRecipeExtras(IRecipeExtrasBuilder builder, WoodcutterRecipeView recipe, IFocusGroup focuses) {
        builder.addRecipeArrow().setPosition(26, 9);
    }
}