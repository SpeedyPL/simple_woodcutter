package net.zebatek.simple_woodcutter.forge;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zebatek.simple_woodcutter.SimpleWoodcutter;
import net.zebatek.simple_woodcutter.block.ModBlocks;
import net.zebatek.simple_woodcutter.menu.WoodcutterMenu;
import net.zebatek.simple_woodcutter.menu.WoodcutterScreen;
import net.zebatek.simple_woodcutter.network.WoodcutterRecipeSyncPayload;
import net.zebatek.simple_woodcutter.recipe.WoodcutterRecipe;
import net.zebatek.simple_woodcutter.recipe.WoodcutterRecipeCollector;

@Mod(SimpleWoodcutter.MOD_ID)
public final class SimpleWoodcutterForge {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SimpleWoodcutter.MOD_ID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SimpleWoodcutter.MOD_ID);
    private static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, SimpleWoodcutter.MOD_ID);
    private static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, SimpleWoodcutter.MOD_ID);
    private static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, SimpleWoodcutter.MOD_ID);

    public static final RegistryObject<Block> WOODCUTTER_BLOCK = BLOCKS.register("woodcutter", ModBlocks::createWoodcutterBlock);
    public static final RegistryObject<Item> WOODCUTTER_ITEM = ITEMS.register("woodcutter", () -> ModBlocks.createWoodcutterItem(WOODCUTTER_BLOCK.get()));

    public static final RegistryObject<MenuType<WoodcutterMenu>> FG_WOODCUTTER_MENU = MENUS.register(
            "woodcutter",
            () -> IForgeMenuType.create((id, inv, data) -> new WoodcutterMenu(id, inv))
    );

    public static final RegistryObject<RecipeSerializer<WoodcutterRecipe>> WOODCUTTER_SERIALIZER = SERIALIZERS.register(
            "woodcutting", () -> new RecipeSerializer<>(WoodcutterRecipe.CODEC, WoodcutterRecipe.STREAM_CODEC));

    public static final RegistryObject<RecipeType<WoodcutterRecipe>> WOODCUTTER_TYPE = RECIPE_TYPES.register(
            "woodcutting", () -> new RecipeType<WoodcutterRecipe>() {
                @Override
                public String toString() { return "woodcutting"; }
            });

    public SimpleWoodcutterForge(FMLJavaModLoadingContext context) {
        BusGroup modBusGroup = context.getModBusGroup(); // Pobieramy BusGroup zgodnie z nowym API

        BLOCKS.register(modBusGroup);
        ITEMS.register(modBusGroup);
        MENUS.register(modBusGroup);
        SERIALIZERS.register(modBusGroup);
        RECIPE_TYPES.register(modBusGroup);

        // 1. Zdarzenia dawnego "Mod Busa" przypinamy używając getBus() bezpośrednio na klasie zdarzenia
        FMLCommonSetupEvent.getBus(modBusGroup).addListener(this::setup);
        BuildCreativeModeTabContentsEvent.BUS.addListener(this::addCreative);

        // 2. Zdarzenia dawnego "Game Busa" (rozgrywki, serwera, graczy) mają gotowe statyczne pole .BUS
        PlayerLoggedInEvent.BUS.addListener(this::onPlayerLoggedIn);

        ForgeNetworking.register();

        SimpleWoodcutter.init();
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModBlocks.WOODCUTTER = WOODCUTTER_BLOCK.get();
            ModBlocks.WOODCUTTER_ITEM = WOODCUTTER_ITEM.get();

            MenuScreens.register(FG_WOODCUTTER_MENU.get(), WoodcutterScreen::new);
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(WOODCUTTER_ITEM.get());
        }
    }

    private void onPlayerLoggedIn(final PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            ForgeNetworking.sendToPlayer(serverPlayer, new WoodcutterRecipeSyncPayload(WoodcutterRecipeCollector.collect(serverPlayer.level().getServer())));
        }
    }
}