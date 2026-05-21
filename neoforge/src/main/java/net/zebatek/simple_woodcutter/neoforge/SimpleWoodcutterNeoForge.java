package net.zebatek.simple_woodcutter.neoforge;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zebatek.simple_woodcutter.SimpleWoodcutter;
import net.zebatek.simple_woodcutter.block.ModBlocks;
import net.zebatek.simple_woodcutter.menu.WoodcutterMenu;
import net.zebatek.simple_woodcutter.menu.WoodcutterScreen;
import net.zebatek.simple_woodcutter.recipe.WoodcutterRecipe;
import net.zebatek.simple_woodcutter.registry.ModMenuTypes;

@Mod(SimpleWoodcutter.MOD_ID)
public final class SimpleWoodcutterNeoForge {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, SimpleWoodcutter.MOD_ID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, SimpleWoodcutter.MOD_ID);
    private static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(BuiltInRegistries.MENU, SimpleWoodcutter.MOD_ID);
    private static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, SimpleWoodcutter.MOD_ID);
    private static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, SimpleWoodcutter.MOD_ID);

    public static final DeferredHolder<Block, Block> WOODCUTTER_BLOCK = BLOCKS.register("woodcutter", ModBlocks::createWoodcutterBlock);
    public static final DeferredHolder<Item, Item> WOODCUTTER_ITEM = ITEMS.register("woodcutter", () -> ModBlocks.createWoodcutterItem(WOODCUTTER_BLOCK.get()));

    public static final DeferredHolder<MenuType<?>, MenuType<WoodcutterMenu>> WOODCUTTER_MENU = MENUS.register(
            "woodcutter",
            () -> IMenuTypeExtension.create((id, inv, data) -> new WoodcutterMenu(id, inv))
    );

    public static final DeferredHolder<RecipeSerializer<?> ,RecipeSerializer<WoodcutterRecipe>> WOODCUTTER_SERIALIZER = SERIALIZERS.register(
            "woodcutting", WoodcutterRecipe.Serializer::new);

    public static final DeferredHolder<RecipeType<?> ,RecipeType<WoodcutterRecipe>> WOODCUTTER_TYPE = RECIPE_TYPES.register(
            "woodcutting", () -> new RecipeType<WoodcutterRecipe>() {
                @Override
                public String toString() { return "woodcutting"; }
            });

    public SimpleWoodcutterNeoForge(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
        MENUS.register(eventBus);
        SERIALIZERS.register(eventBus);
        RECIPE_TYPES.register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::addCreative);

        SimpleWoodcutter.init();
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModBlocks.WOODCUTTER = WOODCUTTER_BLOCK.get();
            ModBlocks.WOODCUTTER_ITEM = WOODCUTTER_ITEM.get();
        });
    }

    private void clientSetup(final RegisterMenuScreensEvent event){
        event.register(ModMenuTypes.getMENU(), WoodcutterScreen::new);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event){
        if(event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS){
            event.accept(WOODCUTTER_ITEM.get());
        }
    }
}