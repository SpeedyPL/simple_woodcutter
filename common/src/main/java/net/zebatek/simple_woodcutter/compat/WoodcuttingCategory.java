package net.zebatek.simple_woodcutter.compat;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.zebatek.simple_woodcutter.SimpleWoodcutter;
import net.zebatek.simple_woodcutter.block.ModBlocks;
import net.zebatek.simple_woodcutter.recipe.WoodcutterRecipe;

public class WoodcuttingCategory extends AbstractRecipeCategory<WoodcutterRecipe> {

    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(SimpleWoodcutter.MOD_ID, "woodcutting");
    public static final RecipeType<WoodcutterRecipe> WOODCUTTING_TYPE = new RecipeType<>(UID, WoodcutterRecipe.class);

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
    public void setRecipe(IRecipeLayoutBuilder builder, WoodcutterRecipe recipe, IFocusGroup focuses) {
        builder.addInputSlot(1, 9)
                .setStandardSlotBackground()
                .addIngredients(recipe.getIngredients().get(0));

        builder.addOutputSlot(61, 9)
                .setOutputSlotBackground()
                .addItemStack(recipe.getResultItem(Minecraft.getInstance().level.registryAccess()));
    }

    @Override
    public void createRecipeExtras(IRecipeExtrasBuilder builder, WoodcutterRecipe recipe, IFocusGroup focuses) {
        builder.addRecipeArrow().setPosition(26, 9);
    }
}