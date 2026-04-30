package net.zebatek.simple_woodcutter.fabric.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.zebatek.simple_woodcutter.Simple_woodcutter;
import net.zebatek.simple_woodcutter.block.ModBlocks;
import net.zebatek.simple_woodcutter.recipe.WoodcutterRecipe;

public class WoodcuttingCategoryFabric implements IRecipeCategory<WoodcutterRecipe> {

    public static final ResourceLocation UID = new ResourceLocation(Simple_woodcutter.MOD_ID, "woodcutting");
    public static final RecipeType<WoodcutterRecipe> WOODCUTTING_TYPE = new RecipeType<>(UID, WoodcutterRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public WoodcuttingCategoryFabric(IGuiHelper helper) {
        this.background = helper.createBlankDrawable(82, 34);
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
        builder.addInputSlot(1, 9)
                .setStandardSlotBackground()
                .addIngredients(recipe.getIngredients().get(0));

        Minecraft minecraft = Minecraft.getInstance();
        builder.addOutputSlot(61, 9)
                .setOutputSlotBackground()
                .addItemStack(recipe.getResultItem(minecraft.level.registryAccess()));
    }

    @Override
    public void createRecipeExtras(IRecipeExtrasBuilder builder, WoodcutterRecipe recipe, IFocusGroup focuses) {
        builder.addRecipeArrow().setPosition(26, 9);
    }
}