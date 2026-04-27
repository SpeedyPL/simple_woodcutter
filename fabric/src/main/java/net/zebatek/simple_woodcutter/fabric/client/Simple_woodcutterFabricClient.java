package net.zebatek.simple_woodcutter.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;
import net.zebatek.simple_woodcutter.menu.WoodcutterScreen;
import net.zebatek.simple_woodcutter.registry.ModMenuTypes;

public final class Simple_woodcutterFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MenuScreens.register(ModMenuTypes.WOODCUTTER_MENU.get(), WoodcutterScreen::new);
    }
}
