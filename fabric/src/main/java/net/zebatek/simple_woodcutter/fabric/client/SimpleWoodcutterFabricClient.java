package net.zebatek.simple_woodcutter.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.screens.MenuScreens;
import net.zebatek.simple_woodcutter.network.ClientWoodcutterRecipes;
import net.zebatek.simple_woodcutter.network.WoodcutterRecipeSyncPayload;
import net.zebatek.simple_woodcutter.registry.ModMenuTypes;
import net.zebatek.simple_woodcutter.menu.WoodcutterScreen;

public final class SimpleWoodcutterFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MenuScreens.register(ModMenuTypes.getMENU(), WoodcutterScreen::new);

        ClientPlayNetworking.registerGlobalReceiver(WoodcutterRecipeSyncPayload.TYPE, (payload, context) ->
                ClientWoodcutterRecipes.set(payload.recipes()));
    }
}