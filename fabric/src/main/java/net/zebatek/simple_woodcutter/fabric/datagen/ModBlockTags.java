package net.zebatek.simple_woodcutter.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.zebatek.simple_woodcutter.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTags extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.WOODCUTTER)
                .setReplace(false);
    }
}
