package net.zebatek.simple_woodcutter.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.zebatek.simple_woodcutter.SimpleWoodcutter;

public class Woodcutting {
    public static void woodcutting(RecipeCategory category, RecipeOutput output, ItemLike input, ItemLike result, int count){
        new WoodcuttingRecipeBuilder(new ItemStackTemplate(result.asItem(), count), Ingredient.of(input))
                .save(output, recipeKey(getID(result).getPath() + "_from_" + getID(input).getPath() + "_woodcutting"));
    }

    public static void woodcutting(RecipeCategory category, RecipeOutput output, HolderLookup.Provider registries,
                                   TagKey<Item> inputTag, ItemLike result, int count) {
        var itemLookup = registries.lookupOrThrow(Registries.ITEM);
        Ingredient ingredient = Ingredient.of(itemLookup.getOrThrow(inputTag));

        new WoodcuttingRecipeBuilder(new ItemStackTemplate(result.asItem(), count), ingredient)
                .save(output, recipeKey(getID(result).getPath() + "_from_" + inputTag.location().getPath() + "_woodcutting"));
    }

    private static ResourceKey<Recipe<?>> recipeKey(String path) {
        return ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath(SimpleWoodcutter.MOD_ID, path));
    }

    private static Identifier getID(ItemLike item){
        return BuiltInRegistries.ITEM.getKey(item.asItem());
    }
}