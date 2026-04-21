package net.zebatek.simple_woodcutter.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModBlocks {
    public static final Block WOODCUTTER = new WoodCutterBlock();
    public static final Item WOODCUTTER_ITEM = new BlockItem(WOODCUTTER, new Item.Properties());
}
