package net.zebatek.simple_woodcutter.fabric;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.zebatek.simple_woodcutter.Simple_woodcutter;
import net.fabricmc.api.ModInitializer;
import net.zebatek.simple_woodcutter.block.ModBlocks;

public final class Simple_woodcutterFabric implements ModInitializer {
    @Override
    public void onInitialize() {

        Registry.register(BuiltInRegistries.BLOCK,
                new ResourceLocation(Simple_woodcutter.MOD_ID, "woodcutter"), ModBlocks.WOODCUTTER);

        Registry.register(BuiltInRegistries.ITEM,
                new ResourceLocation(Simple_woodcutter.MOD_ID, "woodcutter"), ModBlocks.WOODCUTTER_ITEM);

        Simple_woodcutter.init();
    }
}
