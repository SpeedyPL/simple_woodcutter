package net.zebatek.simple_woodcutter.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class WoodcutterRecipe implements Recipe<SingleRecipeInput> {
    private final Recipe.CommonInfo commonInfo;
    private final Ingredient inputIngredient;
    private final ItemStackTemplate result;
    private PlacementInfo placementInfo;

    public WoodcutterRecipe(Recipe.CommonInfo commonInfo, Ingredient ingredient, ItemStackTemplate result) {
        this.commonInfo = commonInfo;
        this.inputIngredient = ingredient;
        this.result = result;
    }

    public Ingredient getInputIngredient() {
        return this.inputIngredient;
    }

    public ItemStackTemplate getResult() {
        return this.result;
    }

    public ItemStack getOutputResult() {
        return this.result.create();
    }

    @Override
    public boolean matches(SingleRecipeInput container, Level level) {
        return this.inputIngredient.test(container.getItem(0));
    }

    @Override
    public ItemStack assemble(SingleRecipeInput input) {
        return this.result.create();
    }

    @Override
    public boolean showNotification() {
        return this.commonInfo.showNotification();
    }

    @Override
    public String group() {
        return "";
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public PlacementInfo placementInfo() {
        if (this.placementInfo == null) {
            this.placementInfo = PlacementInfo.create(this.inputIngredient);
        }
        return this.placementInfo;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return null;
    }

    @Override
    public RecipeType<? extends Recipe<SingleRecipeInput>> getType() {
        return ModRecipes.getTYPE();
    }

    @Override
    public RecipeSerializer<? extends Recipe<SingleRecipeInput>> getSerializer() {
        return ModRecipes.getSERIALIZER();
    }

    public static final MapCodec<WoodcutterRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Recipe.CommonInfo.MAP_CODEC.forGetter(recipe -> recipe.commonInfo),
            Ingredient.CODEC.fieldOf("ingredient").forGetter(WoodcutterRecipe::getInputIngredient),
            ItemStackTemplate.CODEC.fieldOf("result").forGetter(WoodcutterRecipe::getResult)
    ).apply(instance, WoodcutterRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, WoodcutterRecipe> STREAM_CODEC = StreamCodec.composite(
            Recipe.CommonInfo.STREAM_CODEC, recipe -> recipe.commonInfo,
            Ingredient.CONTENTS_STREAM_CODEC, WoodcutterRecipe::getInputIngredient,
            ItemStackTemplate.STREAM_CODEC, WoodcutterRecipe::getResult,
            WoodcutterRecipe::new
    );
}