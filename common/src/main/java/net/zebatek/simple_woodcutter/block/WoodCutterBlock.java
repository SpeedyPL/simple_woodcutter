package net.zebatek.simple_woodcutter.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StonecutterBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class WoodCutterBlock extends StonecutterBlock {
    public WoodCutterBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.STONECUTTER));
    }
}
