package net.zebatek.simple_woodcutter.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.zebatek.simple_woodcutter.block.ModBlocks;
import net.zebatek.simple_woodcutter.recipe.Woodcutting;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider);
    }

    protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput exporter){
        return new RecipeProvider(provider, exporter) {
            @Override
            public void buildRecipes() {
                shaped(RecipeCategory.MISC, ModBlocks.WOODCUTTER)
                        .pattern(" I ")
                        .pattern("PPP")
                        .pattern("I I")
                        .define('I', Items.IRON_INGOT)
                        .define('P', ItemTags.PLANKS)
                        .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                        .save(output);

                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, registries, ItemTags.OAK_LOGS, Blocks.OAK_PLANKS, 4);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, registries,ItemTags.SPRUCE_LOGS, Blocks.SPRUCE_PLANKS, 4);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, registries,ItemTags.BIRCH_LOGS, Blocks.BIRCH_PLANKS, 4);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, registries,ItemTags.JUNGLE_LOGS, Blocks.JUNGLE_PLANKS, 4);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, registries,ItemTags.ACACIA_LOGS, Blocks.ACACIA_PLANKS, 4);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output,registries, ItemTags.DARK_OAK_LOGS, Blocks.DARK_OAK_PLANKS, 4);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, registries,ItemTags.CRIMSON_STEMS, Blocks.CRIMSON_PLANKS, 4);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, registries,ItemTags.WARPED_STEMS, Blocks.WARPED_PLANKS, 4);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, registries,ItemTags.MANGROVE_LOGS, Blocks.MANGROVE_PLANKS, 4);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, registries,ItemTags.BAMBOO_BLOCKS, Blocks.BAMBOO_PLANKS, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, registries,ItemTags.CHERRY_LOGS, Blocks.CHERRY_PLANKS, 4);

                Woodcutting.woodcutting(RecipeCategory.MISC, output, Items.DEAD_BUSH, Items.STICK, 1);
                Woodcutting.woodcutting(RecipeCategory.MISC, output, registries,ItemTags.PLANKS, Items.STICK, 8);
                Woodcutting.woodcutting(RecipeCategory.MISC, output, registries,ItemTags.SAPLINGS, Items.STICK, 2);
                Woodcutting.woodcutting(RecipeCategory.MISC, output, registries,ItemTags.SLABS, Items.STICK, 4);
                Woodcutting.woodcutting(RecipeCategory.MISC, output, registries,ItemTags.FENCES, Items.STICK, 4);
                Woodcutting.woodcutting(RecipeCategory.MISC, output, registries,ItemTags.FENCE_GATES, Items.STICK, 2);
                Woodcutting.woodcutting(RecipeCategory.MISC, output, Items.LADDER, Items.STICK, 6);
                Woodcutting.woodcutting(RecipeCategory.MISC, output, registries,ItemTags.SIGNS, Items.STICK, 4);

                Woodcutting.woodcutting(RecipeCategory.MISC, output, registries,ItemTags.PLANKS, Items.LADDER, 1);

                Woodcutting.woodcutting(RecipeCategory.MISC, output, registries,ItemTags.PLANKS, Items.BOWL, 2);
                Woodcutting.woodcutting(RecipeCategory.MISC, output, registries,ItemTags.SLABS, Items.BOWL, 1);

                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.OAK_PLANKS, Blocks.OAK_STAIRS, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_STAIRS, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BIRCH_PLANKS, Blocks.BIRCH_STAIRS, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_STAIRS, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.ACACIA_PLANKS, Blocks.ACACIA_STAIRS, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_STAIRS, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_STAIRS, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.WARPED_PLANKS, Blocks.WARPED_STAIRS, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.MANGROVE_PLANKS, Blocks.MANGROVE_STAIRS, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BAMBOO_PLANKS, Blocks.BAMBOO_STAIRS, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CHERRY_PLANKS, Blocks.CHERRY_STAIRS, 1);

                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.OAK_PLANKS, Blocks.OAK_SLAB, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_SLAB, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BIRCH_PLANKS, Blocks.BIRCH_SLAB, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_SLAB, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.ACACIA_PLANKS, Blocks.ACACIA_SLAB, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_SLAB, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_SLAB, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.WARPED_PLANKS, Blocks.WARPED_SLAB, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.MANGROVE_PLANKS, Blocks.MANGROVE_SLAB, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BAMBOO_PLANKS, Blocks.BAMBOO_SLAB, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CHERRY_PLANKS, Blocks.CHERRY_SLAB, 2);

                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.OAK_PLANKS, Blocks.OAK_PRESSURE_PLATE, 4);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_PRESSURE_PLATE, 4);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BIRCH_PLANKS, Blocks.BIRCH_PRESSURE_PLATE, 4);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_PRESSURE_PLATE, 4);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.ACACIA_PLANKS, Blocks.ACACIA_PRESSURE_PLATE, 4);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_PRESSURE_PLATE, 4);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_PRESSURE_PLATE, 4);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.WARPED_PLANKS, Blocks.WARPED_PRESSURE_PLATE, 4);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.MANGROVE_PLANKS, Blocks.MANGROVE_PRESSURE_PLATE, 4);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BAMBOO_PLANKS, Blocks.BAMBOO_PRESSURE_PLATE, 4);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CHERRY_PLANKS, Blocks.CHERRY_PRESSURE_PLATE, 4);

                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.OAK_PLANKS, Blocks.OAK_BUTTON, 27);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_BUTTON, 27);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BIRCH_PLANKS, Blocks.BIRCH_BUTTON, 27);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_BUTTON, 27);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.ACACIA_PLANKS, Blocks.ACACIA_BUTTON, 27);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_BUTTON, 27);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_BUTTON, 27);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.WARPED_PLANKS, Blocks.WARPED_BUTTON, 27);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.MANGROVE_PLANKS, Blocks.MANGROVE_BUTTON, 27);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BAMBOO_PLANKS, Blocks.BAMBOO_BUTTON, 27);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CHERRY_PLANKS, Blocks.CHERRY_BUTTON, 27);

                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.OAK_PLANKS, Blocks.OAK_SIGN, 5);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_SIGN, 5);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BIRCH_PLANKS, Blocks.BIRCH_SIGN, 5);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_SIGN, 5);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.ACACIA_PLANKS, Blocks.ACACIA_SIGN, 5);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_SIGN, 5);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_SIGN, 5);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.WARPED_PLANKS, Blocks.WARPED_SIGN, 5);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.MANGROVE_PLANKS, Blocks.MANGROVE_SIGN, 5);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BAMBOO_PLANKS, Blocks.BAMBOO_SIGN, 5);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CHERRY_PLANKS, Blocks.CHERRY_SIGN, 5);

                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.OAK_STAIRS, Blocks.OAK_SLAB, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.SPRUCE_STAIRS, Blocks.SPRUCE_SLAB, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BIRCH_STAIRS, Blocks.BIRCH_SLAB, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.JUNGLE_STAIRS, Blocks.JUNGLE_SLAB, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.ACACIA_STAIRS, Blocks.ACACIA_SLAB, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.DARK_OAK_STAIRS, Blocks.DARK_OAK_SLAB, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CRIMSON_STAIRS, Blocks.CRIMSON_SLAB, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.WARPED_STAIRS, Blocks.WARPED_SLAB, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.MANGROVE_STAIRS, Blocks.MANGROVE_SLAB, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BAMBOO_STAIRS, Blocks.BAMBOO_SLAB, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CHERRY_STAIRS, Blocks.CHERRY_SLAB, 1);

                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.OAK_DOOR, Blocks.OAK_TRAPDOOR, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.SPRUCE_DOOR, Blocks.SPRUCE_TRAPDOOR, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BIRCH_DOOR, Blocks.BIRCH_TRAPDOOR, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.JUNGLE_DOOR, Blocks.JUNGLE_TRAPDOOR, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.ACACIA_DOOR, Blocks.ACACIA_TRAPDOOR, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.DARK_OAK_DOOR, Blocks.DARK_OAK_TRAPDOOR, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CRIMSON_DOOR, Blocks.CRIMSON_TRAPDOOR, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.WARPED_DOOR, Blocks.WARPED_TRAPDOOR, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.MANGROVE_DOOR, Blocks.MANGROVE_TRAPDOOR, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BAMBOO_DOOR, Blocks.BAMBOO_TRAPDOOR, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CHERRY_DOOR, Blocks.CHERRY_TRAPDOOR, 2);

                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Items.OAK_BOAT, Blocks.OAK_PLANKS, 5);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Items.SPRUCE_BOAT, Blocks.SPRUCE_PLANKS, 5);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Items.BIRCH_BOAT, Blocks.BIRCH_PLANKS, 5);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Items.JUNGLE_BOAT, Blocks.JUNGLE_PLANKS, 5);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Items.ACACIA_BOAT, Blocks.ACACIA_PLANKS, 5);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Items.DARK_OAK_BOAT, Blocks.DARK_OAK_PLANKS, 5);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Items.MANGROVE_BOAT, Blocks.MANGROVE_PLANKS, 5);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Items.BAMBOO_RAFT, Blocks.BAMBOO_PLANKS, 5);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Items.CHERRY_BOAT, Blocks.CHERRY_PLANKS, 5);

                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.OAK_PLANKS, Blocks.OAK_FENCE, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_FENCE, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BIRCH_PLANKS, Blocks.BIRCH_FENCE, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_FENCE, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.ACACIA_PLANKS, Blocks.ACACIA_FENCE, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_FENCE, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_FENCE, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.WARPED_PLANKS, Blocks.WARPED_FENCE, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.MANGROVE_PLANKS, Blocks.MANGROVE_FENCE, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BAMBOO_PLANKS, Blocks.BAMBOO_FENCE, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CHERRY_PLANKS, Blocks.CHERRY_FENCE, 2);

                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.OAK_PLANKS, Blocks.OAK_FENCE_GATE, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_FENCE_GATE, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BIRCH_PLANKS, Blocks.BIRCH_FENCE_GATE, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_FENCE_GATE, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.ACACIA_PLANKS, Blocks.ACACIA_FENCE_GATE, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_FENCE_GATE, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_FENCE_GATE, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.WARPED_PLANKS, Blocks.WARPED_FENCE_GATE, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.MANGROVE_PLANKS, Blocks.MANGROVE_FENCE_GATE, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BAMBOO_PLANKS, Blocks.BAMBOO_FENCE_GATE, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CHERRY_PLANKS, Blocks.CHERRY_FENCE_GATE, 2);

                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.OAK_PLANKS, Blocks.OAK_DOOR, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_DOOR, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BIRCH_PLANKS, Blocks.BIRCH_DOOR, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_DOOR, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.ACACIA_PLANKS, Blocks.ACACIA_DOOR, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_DOOR, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_DOOR, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.WARPED_PLANKS, Blocks.WARPED_DOOR, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.MANGROVE_PLANKS, Blocks.MANGROVE_DOOR, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BAMBOO_PLANKS, Blocks.BAMBOO_DOOR, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CHERRY_PLANKS, Blocks.CHERRY_DOOR, 1);

                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.OAK_PLANKS, Blocks.OAK_TRAPDOOR, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_TRAPDOOR, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BIRCH_PLANKS, Blocks.BIRCH_TRAPDOOR, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_TRAPDOOR, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.ACACIA_PLANKS, Blocks.ACACIA_TRAPDOOR, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_TRAPDOOR, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_TRAPDOOR, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.WARPED_PLANKS, Blocks.WARPED_TRAPDOOR, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.MANGROVE_PLANKS, Blocks.MANGROVE_TRAPDOOR, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BAMBOO_PLANKS, Blocks.BAMBOO_TRAPDOOR, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CHERRY_PLANKS, Blocks.CHERRY_TRAPDOOR, 2);

                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.OAK_SLAB, Blocks.OAK_SIGN, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.SPRUCE_SLAB, Blocks.SPRUCE_SIGN, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BIRCH_SLAB, Blocks.BIRCH_SIGN, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.JUNGLE_SLAB, Blocks.JUNGLE_SIGN, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.ACACIA_SLAB, Blocks.ACACIA_SIGN, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.DARK_OAK_SLAB, Blocks.DARK_OAK_SIGN, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CRIMSON_SLAB, Blocks.CRIMSON_SIGN, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.WARPED_SLAB, Blocks.WARPED_SIGN, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.MANGROVE_SLAB, Blocks.MANGROVE_SIGN, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BAMBOO_SLAB, Blocks.BAMBOO_SIGN, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CHERRY_SLAB, Blocks.CHERRY_SIGN, 2);

                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.OAK_SLAB, Blocks.OAK_FENCE, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.SPRUCE_SLAB, Blocks.SPRUCE_FENCE, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BIRCH_SLAB, Blocks.BIRCH_FENCE, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.JUNGLE_SLAB, Blocks.JUNGLE_FENCE, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.ACACIA_SLAB, Blocks.ACACIA_FENCE, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.DARK_OAK_SLAB, Blocks.DARK_OAK_FENCE, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CRIMSON_SLAB, Blocks.CRIMSON_FENCE, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.WARPED_SLAB, Blocks.WARPED_FENCE, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.MANGROVE_SLAB, Blocks.MANGROVE_FENCE, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BAMBOO_SLAB, Blocks.BAMBOO_FENCE, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CHERRY_SLAB, Blocks.CHERRY_FENCE, 1);

                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.OAK_SLAB, Blocks.OAK_FENCE_GATE, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.SPRUCE_SLAB, Blocks.SPRUCE_FENCE_GATE, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BIRCH_SLAB, Blocks.BIRCH_FENCE_GATE, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.JUNGLE_SLAB, Blocks.JUNGLE_FENCE_GATE, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.ACACIA_SLAB, Blocks.ACACIA_FENCE_GATE, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.DARK_OAK_SLAB, Blocks.DARK_OAK_FENCE_GATE, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CRIMSON_SLAB, Blocks.CRIMSON_FENCE_GATE, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.WARPED_SLAB, Blocks.WARPED_FENCE_GATE, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.MANGROVE_SLAB, Blocks.MANGROVE_FENCE_GATE, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BAMBOO_SLAB, Blocks.BAMBOO_FENCE_GATE, 1);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CHERRY_SLAB, Blocks.CHERRY_FENCE_GATE, 1);

                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.OAK_SLAB, Blocks.OAK_PRESSURE_PLATE, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.SPRUCE_SLAB, Blocks.SPRUCE_PRESSURE_PLATE, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BIRCH_SLAB, Blocks.BIRCH_PRESSURE_PLATE, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.JUNGLE_SLAB, Blocks.JUNGLE_PRESSURE_PLATE, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.ACACIA_SLAB, Blocks.ACACIA_PRESSURE_PLATE, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.DARK_OAK_SLAB, Blocks.DARK_OAK_PRESSURE_PLATE, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CRIMSON_SLAB, Blocks.CRIMSON_PRESSURE_PLATE, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.WARPED_SLAB, Blocks.WARPED_PRESSURE_PLATE, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.MANGROVE_SLAB, Blocks.MANGROVE_PRESSURE_PLATE, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BAMBOO_SLAB, Blocks.BAMBOO_PRESSURE_PLATE, 2);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CHERRY_SLAB, Blocks.CHERRY_PRESSURE_PLATE, 2);

                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.OAK_SLAB, Blocks.OAK_BUTTON, 13);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.SPRUCE_SLAB, Blocks.SPRUCE_BUTTON, 13);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BIRCH_SLAB, Blocks.BIRCH_BUTTON, 13);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.JUNGLE_SLAB, Blocks.JUNGLE_BUTTON, 13);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.ACACIA_SLAB, Blocks.ACACIA_BUTTON, 13);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.DARK_OAK_SLAB, Blocks.DARK_OAK_BUTTON, 13);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CRIMSON_SLAB, Blocks.CRIMSON_BUTTON, 13);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.WARPED_SLAB, Blocks.WARPED_BUTTON, 13);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.MANGROVE_SLAB, Blocks.MANGROVE_BUTTON, 13);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.BAMBOO_SLAB, Blocks.BAMBOO_BUTTON, 13);
                Woodcutting.woodcutting(RecipeCategory.BUILDING_BLOCKS, output, Blocks.CHERRY_SLAB, Blocks.CHERRY_BUTTON, 13);
            }
            };
        }

    @Override
    public String getName() {
        return "SimpleWoodcutterRecipes";
    }
}
