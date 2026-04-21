package net.zebatek.simple_woodcutter.block;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StonecutterBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class WoodCutterBlock extends StonecutterBlock {
    public WoodCutterBlock() {
        super(BlockBehaviour.Properties.of().strength(2f).sound(SoundType.WOOD));
    }
}
