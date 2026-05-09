package net.zebatek.simple_woodcutter.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks {
    public static final Block WOODCUTTER = new WoodCutterBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2f).sound(SoundType.COPPER));
    public static final Item WOODCUTTER_ITEM = new BlockItem(WOODCUTTER, new Item.Properties());
}
