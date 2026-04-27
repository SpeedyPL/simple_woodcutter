package net.zebatek.simple_woodcutter.fabric;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.zebatek.simple_woodcutter.Simple_woodcutter;
import net.fabricmc.api.ModInitializer;
import net.zebatek.simple_woodcutter.block.ModBlocks;
import net.zebatek.simple_woodcutter.menu.WoodcutterMenu;
import net.zebatek.simple_woodcutter.recipe.ModRecipes;
import net.zebatek.simple_woodcutter.recipe.WoodcutterRecipe;
import net.zebatek.simple_woodcutter.registry.ModMenuTypes;

public final class Simple_woodcutterFabric implements ModInitializer {
    @Override
    public void onInitialize() {

        Registry.register(BuiltInRegistries.BLOCK,
                new ResourceLocation(Simple_woodcutter.MOD_ID, "woodcutter"), ModBlocks.WOODCUTTER);

        Registry.register(BuiltInRegistries.ITEM,
                new ResourceLocation(Simple_woodcutter.MOD_ID, "woodcutter"), ModBlocks.WOODCUTTER_ITEM);

        MenuType<WoodcutterMenu> fabricMenu = Registry.register(
                BuiltInRegistries.MENU,
                new ResourceLocation(Simple_woodcutter.MOD_ID, "woodcutter"),
                new MenuType<>(WoodcutterMenu::new, FeatureFlags.VANILLA_SET)
        );

        RecipeSerializer<WoodcutterRecipe> fabricSerializer = Registry.register(
                BuiltInRegistries.RECIPE_SERIALIZER,
                new ResourceLocation(Simple_woodcutter.MOD_ID, "woodcutting"),
                new WoodcutterRecipe.Serializer()
        );

        RecipeType<WoodcutterRecipe> fabricType = Registry.register(
                BuiltInRegistries.RECIPE_TYPE,
                new ResourceLocation(Simple_woodcutter.MOD_ID, "woodcutting"),
                new RecipeType<WoodcutterRecipe>() {

                    @Override
                    public String toString() {return "woodcutting";}
                }
        );

        ModMenuTypes.WOODCUTTER_MENU = () -> fabricMenu;
        ModRecipes.WOODCUTTER_SERIALIZER = () -> fabricSerializer;
        ModRecipes.WOODCUTTER_TYPE = () -> fabricType;

        Simple_woodcutter.init();
    }
}
