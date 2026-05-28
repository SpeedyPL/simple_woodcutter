package net.zebatek.simple_woodcutter.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.zebatek.simple_woodcutter.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTags extends FabricTagProvider.BlockTagProvider {
    public ModBlockTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.WOODCUTTER)
                .setReplace(false);
    }
}
