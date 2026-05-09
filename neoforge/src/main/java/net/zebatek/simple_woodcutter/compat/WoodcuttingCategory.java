package net.zebatek.simple_woodcutter.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.zebatek.simple_woodcutter.Simple_woodcutter;
import net.zebatek.simple_woodcutter.block.ModBlocks;
import net.zebatek.simple_woodcutter.recipe.WoodcutterRecipe;

public class WoodcuttingCategory implements IRecipeCategory<WoodcutterRecipe> {

    public static final ResourceLocation UID = new ResourceLocation(Simple_woodcutter.MOD_ID, "woodcutting");
    public static final RecipeType<WoodcutterRecipe> WOODCUTTING_TYPE = new RecipeType<>(UID, WoodcutterRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public WoodcuttingCategory(IGuiHelper helper) {
        ResourceLocation jeiVanillaGui = new ResourceLocation("jei", "textures/jei/gui/gui_vanilla.png");
        this.background = helper.createDrawable(jeiVanillaGui, 0, 220, 82, 34);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.WOODCUTTER_ITEM));
    }

    @Override
    public RecipeType<WoodcutterRecipe> getRecipeType() {
        return WOODCUTTING_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("container.simple_woodcutter.woodcutter");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, WoodcutterRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 9)
                .addIngredients(recipe.getIngredients().get(0));

        Minecraft minecraft = Minecraft.getInstance();
        builder.addSlot(RecipeIngredientRole.OUTPUT, 61, 9)
                .addItemStack(recipe.getResultItem(minecraft.level.registryAccess()));
    }

    @Override
    public void draw(WoodcutterRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
    }
}