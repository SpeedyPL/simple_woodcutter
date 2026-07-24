package net.zebatek.simple_woodcutter.recipe;

import net.minecraft.advancements.Criterion;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

public class WoodcuttingRecipeBuilder implements RecipeBuilder {
    private final ItemStackTemplate result;
    private final Ingredient ingredient;
    private String group = "";

    public WoodcuttingRecipeBuilder(ItemStackTemplate result, Ingredient ingredient) {
        this.result = result;
        this.ingredient = ingredient;
    }

    @Override
    public RecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        return this;
    }

    @Override
    public RecipeBuilder group(String group) {
        this.group = group == null ? "" : group;
        return this;
    }

    @Override
    public void save(RecipeOutput output, ResourceKey<Recipe<?>> id) {
        WoodcutterRecipe recipe = new WoodcutterRecipe(RecipeBuilder.createCraftingCommonInfo(true), this.ingredient, this.result);
        output.accept(id, recipe, null);
    }

    @Override
    public ResourceKey<Recipe<?>> defaultId() {
        return RecipeBuilder.getDefaultRecipeId(this.result);
    }
}