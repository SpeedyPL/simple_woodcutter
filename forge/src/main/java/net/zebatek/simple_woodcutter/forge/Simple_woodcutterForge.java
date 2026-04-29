package net.zebatek.simple_woodcutter.forge;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zebatek.simple_woodcutter.Simple_woodcutter;
import net.minecraftforge.fml.common.Mod;
import net.zebatek.simple_woodcutter.block.ModBlocks;
import net.zebatek.simple_woodcutter.menu.WoodcutterMenu;
import net.zebatek.simple_woodcutter.menu.WoodcutterScreen;
import net.zebatek.simple_woodcutter.recipe.ModRecipes;
import net.zebatek.simple_woodcutter.recipe.WoodcutterRecipe;
import net.zebatek.simple_woodcutter.registry.ModMenuTypes;

@Mod(Simple_woodcutter.MOD_ID)
public final class Simple_woodcutterForge {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Simple_woodcutter.MOD_ID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Simple_woodcutter.MOD_ID);
    private static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Simple_woodcutter.MOD_ID);

    public static final RegistryObject<MenuType<WoodcutterMenu>> FG_WOODCUTTER_MENU = MENUS.register(
            "woodcutter",
            () -> IForgeMenuType.create((id, inv, data) -> new WoodcutterMenu(id, inv))
    );

    private static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Simple_woodcutter.MOD_ID);

    private static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, Simple_woodcutter.MOD_ID);

    public static final RegistryObject<RecipeSerializer<WoodcutterRecipe>> WOODCUTTER_SERIALIZER = SERIALIZERS.register(
            "woodcutting", WoodcutterRecipe.Serializer::new);

    public static final RegistryObject<RecipeType<WoodcutterRecipe>> WOODCUTTER_TYPE = RECIPE_TYPES.register(
            "woodcutting", () -> new RecipeType<WoodcutterRecipe>() {

                @Override
                public String toString() { return "woodcutting"; }
            });

    public Simple_woodcutterForge() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BLOCKS.register("woodcutter", () -> ModBlocks.WOODCUTTER);
        ITEMS.register("woodcutter", () -> ModBlocks.WOODCUTTER_ITEM);

        ModMenuTypes.WOODCUTTER_MENU = FG_WOODCUTTER_MENU;
        ModRecipes.WOODCUTTER_SERIALIZER = WOODCUTTER_SERIALIZER;
        ModRecipes.WOODCUTTER_TYPE = WOODCUTTER_TYPE;

        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
        MENUS.register(eventBus);
        SERIALIZERS.register(eventBus);
        RECIPE_TYPES.register(eventBus);

        eventBus.addListener(this::clientSetup);
        Simple_woodcutter.init();
    }

    private void clientSetup(final FMLClientSetupEvent event){
        event.enqueueWork(() -> {
            MenuScreens.register(Simple_woodcutterForge.FG_WOODCUTTER_MENU.get(), WoodcutterScreen::new);
        });
    }
}
