package net.zebatek.simple_woodcutter.forge;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.zebatek.simple_woodcutter.Simple_woodcutter;
import net.minecraftforge.fml.common.Mod;
import net.zebatek.simple_woodcutter.block.ModBlocks;

@Mod(Simple_woodcutter.MOD_ID)
public final class Simple_woodcutterForge {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Simple_woodcutter.MOD_ID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Simple_woodcutter.MOD_ID);

    public Simple_woodcutterForge() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BLOCKS.register("woodcutter", () -> ModBlocks.WOODCUTTER);
        ITEMS.register("woodcutter", () -> ModBlocks.WOODCUTTER_ITEM);

        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);

        Simple_woodcutter.init();
    }
}
