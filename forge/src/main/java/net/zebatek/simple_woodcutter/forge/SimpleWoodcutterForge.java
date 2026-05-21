package net.zebatek.simple_woodcutter.forge;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
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
import net.zebatek.simple_woodcutter.recipe.WoodcutterRecipe;

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
            "woodcutting", WoodcutterRecipe.Serializer::new);

    public static final RegistryObject<RecipeType<WoodcutterRecipe>> WOODCUTTER_TYPE = RECIPE_TYPES.register(
            "woodcutting", () -> new RecipeType<WoodcutterRecipe>() {
                @Override
                public String toString() { return "woodcutting"; }
            });

    public SimpleWoodcutterForge() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
        MENUS.register(eventBus);
        SERIALIZERS.register(eventBus);
        RECIPE_TYPES.register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::addCreative);

        SimpleWoodcutter.init();
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModBlocks.WOODCUTTER = WOODCUTTER_BLOCK.get();
            ModBlocks.WOODCUTTER_ITEM = WOODCUTTER_ITEM.get();

            MenuScreens.register(FG_WOODCUTTER_MENU.get(), WoodcutterScreen::new);
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event){
        if(event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS){
            event.accept(WOODCUTTER_ITEM.get());
        }
    }
}