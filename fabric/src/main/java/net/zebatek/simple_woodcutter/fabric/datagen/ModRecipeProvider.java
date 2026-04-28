package net.zebatek.simple_woodcutter.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.zebatek.simple_woodcutter.block.ModBlocks;
import net.zebatek.simple_woodcutter.recipe.Woodcutting;

import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.WOODCUTTER)
                        .pattern(" I ")
                        .pattern("PPP")
                        .pattern("I I")
                        .define('I', Items.IRON_INGOT)
                        .define('P', ItemTags.PLANKS)
                        .unlockedBy("has", has(Items.IRON_INGOT))
                        .save(consumer);

        Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, consumer, Blocks.OAK_LOG, Blocks.OAK_PLANKS, 4);
    }
}
