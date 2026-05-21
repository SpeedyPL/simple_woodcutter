package net.zebatek.simple_woodcutter.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.zebatek.simple_woodcutter.SimpleWoodcutter;
import net.zebatek.simple_woodcutter.block.ModBlocks;
import net.zebatek.simple_woodcutter.menu.WoodcutterMenu;
import net.zebatek.simple_woodcutter.recipe.WoodcutterRecipe;

public final class SimpleWoodcutterFabric implements ModInitializer {

    public static Block WOODCUTTER;
    public static Item WOODCUTTER_ITEM;
    public static MenuType<WoodcutterMenu> WOODCUTTER_MENU;
    public static RecipeSerializer<WoodcutterRecipe> WOODCUTTER_SERIALIZER;
    public static RecipeType<WoodcutterRecipe> WOODCUTTER_TYPE;

    @Override
    public void onInitialize() {
        WOODCUTTER = ModBlocks.createWoodcutterBlock();
        WOODCUTTER_ITEM = ModBlocks.createWoodcutterItem(WOODCUTTER);

        Registry.register(BuiltInRegistries.BLOCK,
                new ResourceLocation(SimpleWoodcutter.MOD_ID, "woodcutter"), WOODCUTTER);

        Registry.register(BuiltInRegistries.ITEM,
                new ResourceLocation(SimpleWoodcutter.MOD_ID, "woodcutter"), WOODCUTTER_ITEM);

        WOODCUTTER_MENU = Registry.register(
                BuiltInRegistries.MENU,
                new ResourceLocation(SimpleWoodcutter.MOD_ID, "woodcutter"),
                new MenuType<>(WoodcutterMenu::new, FeatureFlags.VANILLA_SET)
        );

        WOODCUTTER_SERIALIZER = Registry.register(
                BuiltInRegistries.RECIPE_SERIALIZER,
                new ResourceLocation(SimpleWoodcutter.MOD_ID, "woodcutting"),
                new WoodcutterRecipe.Serializer()
        );

        WOODCUTTER_TYPE = Registry.register(
                BuiltInRegistries.RECIPE_TYPE,
                new ResourceLocation(SimpleWoodcutter.MOD_ID, "woodcutting"),
                new RecipeType<WoodcutterRecipe>() {
                    @Override
                    public String toString() { return "woodcutting"; }
                }
        );

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(content -> {
            content.accept(WOODCUTTER_ITEM);
        });

        ModBlocks.WOODCUTTER = WOODCUTTER;
        ModBlocks.WOODCUTTER_ITEM = WOODCUTTER_ITEM;

        SimpleWoodcutter.init();
    }
}