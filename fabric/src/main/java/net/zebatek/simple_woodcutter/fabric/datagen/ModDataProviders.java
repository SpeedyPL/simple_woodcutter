package net.zebatek.simple_woodcutter.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ModDataProviders {
    public static void register(FabricDataGenerator.Pack pack){
        pack.addProvider(ModRecipeProvider::new);
    }
}
