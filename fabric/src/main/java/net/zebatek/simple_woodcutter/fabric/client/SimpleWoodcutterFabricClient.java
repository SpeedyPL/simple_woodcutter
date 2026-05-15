package net.zebatek.simple_woodcutter.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.RenderType;
import net.zebatek.simple_woodcutter.block.ModBlocks;
import net.zebatek.simple_woodcutter.menu.WoodcutterScreen;
import net.zebatek.simple_woodcutter.registry.ModMenuTypes;

public final class SimpleWoodcutterFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MenuScreens.register(ModMenuTypes.getMENU(), WoodcutterScreen::new);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOODCUTTER, RenderType.cutout());
    }
}
