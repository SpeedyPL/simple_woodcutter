package net.zebatek.simple_woodcutter.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.recipe.v1.sync.RecipeSynchronization;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
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
import net.zebatek.simple_woodcutter.network.WoodcutterRecipeSyncPayload;
import net.zebatek.simple_woodcutter.recipe.WoodcutterRecipe;
import net.zebatek.simple_woodcutter.recipe.WoodcutterRecipeCollector;

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
                Identifier.fromNamespaceAndPath(SimpleWoodcutter.MOD_ID, "woodcutter"), WOODCUTTER);

        Registry.register(BuiltInRegistries.ITEM,
                Identifier.fromNamespaceAndPath(SimpleWoodcutter.MOD_ID, "woodcutter"), WOODCUTTER_ITEM);

        WOODCUTTER_MENU = Registry.register(
                BuiltInRegistries.MENU,
                Identifier.fromNamespaceAndPath(SimpleWoodcutter.MOD_ID, "woodcutter"),
                new MenuType<>(WoodcutterMenu::new, FeatureFlags.VANILLA_SET)
        );

        WOODCUTTER_SERIALIZER = Registry.register(
                BuiltInRegistries.RECIPE_SERIALIZER,
                Identifier.fromNamespaceAndPath(SimpleWoodcutter.MOD_ID, "woodcutting"),
                new RecipeSerializer<>(WoodcutterRecipe.CODEC, WoodcutterRecipe.STREAM_CODEC)
        );

        WOODCUTTER_TYPE = Registry.register(
                BuiltInRegistries.RECIPE_TYPE,
                Identifier.fromNamespaceAndPath(SimpleWoodcutter.MOD_ID, "woodcutting"),
                new RecipeType<WoodcutterRecipe>() {
                    @Override
                    public String toString() { return "woodcutting"; }
                }
        );

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(content -> {
            content.accept(WOODCUTTER_ITEM);
        });

        RecipeSynchronization.synchronizeRecipeSerializer(WOODCUTTER_SERIALIZER);

        PayloadTypeRegistry.clientboundPlay().register(WoodcutterRecipeSyncPayload.TYPE, WoodcutterRecipeSyncPayload.STREAM_CODEC);

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) ->
                sender.sendPacket(new WoodcutterRecipeSyncPayload(WoodcutterRecipeCollector.collect(server))));

        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, resourceManager, success) -> {
            var payload = new WoodcutterRecipeSyncPayload(WoodcutterRecipeCollector.collect(server));
            server.getPlayerList().getPlayers().forEach(player -> ServerPlayNetworking.send(player, payload));
        });

        ModBlocks.WOODCUTTER = WOODCUTTER;
        ModBlocks.WOODCUTTER_ITEM = WOODCUTTER_ITEM;

        SimpleWoodcutter.init();
    }
}