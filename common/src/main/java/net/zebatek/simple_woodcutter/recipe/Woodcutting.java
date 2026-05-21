package net.zebatek.simple_woodcutter.recipe;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.zebatek.simple_woodcutter.SimpleWoodcutter;

public class Woodcutting {
    public static void woodcutting(RecipeCategory category, RecipeOutput output, ItemLike input, ItemLike result, int count){
        new SingleItemRecipeBuilder(
                category,
                WoodcutterRecipe.FACTORY,
                Ingredient.of(input),
                result,
                count
        ).unlockedBy("has_" + getID(input).getPath(),
                        InventoryChangeTrigger.TriggerInstance.hasItems(input))
                .save(output, new ResourceLocation(SimpleWoodcutter.MOD_ID,
                        getID(result).getPath() + "_from_" + getID(input).getPath() + "_woodcutting"));
    }

    public static void woodcutting(RecipeCategory category,RecipeOutput output ,TagKey<Item> inputTag, ItemLike result, int count) {
        new SingleItemRecipeBuilder(category, WoodcutterRecipe.FACTORY, Ingredient.of(inputTag), result, count)
                .unlockedBy("has_" + inputTag.location().getPath(),
                        InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(inputTag).build()))
                .save(output, new ResourceLocation(SimpleWoodcutter.MOD_ID,
                        getID(result).getPath() + "_from_" + inputTag.location().getPath() + "_woodcutting"));
    }

    private static ResourceLocation getID(ItemLike item){
        return BuiltInRegistries.ITEM.getKey(item.asItem());
    }
}
