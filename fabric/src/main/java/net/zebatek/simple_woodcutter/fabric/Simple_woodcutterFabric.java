package net.zebatek.simple_woodcutter.fabric;

import net.zebatek.simple_woodcutter.Simple_woodcutter;
import net.fabricmc.api.ModInitializer;

public final class Simple_woodcutterFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        Simple_woodcutter.init();
    }
}
