package net.zebatek.simple_woodcutter.recipe;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.zebatek.simple_woodcutter.Simple_woodcutter;

import java.util.function.Consumer;

public class Woodcutting {
    public static void woodcutting(RecipeCategory category, Consumer<FinishedRecipe>consumer, ItemLike input, ItemLike result, int count){
        new SingleItemRecipeBuilder(
                category,
                ModRecipes.WOODCUTTER_SERIALIZER.get(),
                Ingredient.of(input),
                result,
                count
        ).unlockedBy("has_" + getID(input).getPath(),
                        InventoryChangeTrigger.TriggerInstance.hasItems(input))
                .save(consumer, new ResourceLocation(Simple_woodcutter.MOD_ID,
                        getID(result).getPath() + "_from_" + getID(input).getPath() + "_woodcutting"));
    }

    public static void woodcutting(RecipeCategory category, Consumer<FinishedRecipe> consumer, TagKey<Item> inputTag, ItemLike result, int count) {
        new SingleItemRecipeBuilder(category, ModRecipes.WOODCUTTER_SERIALIZER.get(), Ingredient.of(inputTag), result, count)
                .unlockedBy("has_" + inputTag.location().getPath(),
                        InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(inputTag).build()))
                .save(consumer, new ResourceLocation(Simple_woodcutter.MOD_ID,
                        getID(result).getPath() + "_from_" + inputTag.location().getPath() + "_woodcutting"));
    }

    private static ResourceLocation getID(ItemLike item){
        return item.asItem().builtInRegistryHolder().key().location();
    }
}
