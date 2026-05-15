package net.zebatek.simple_woodcutter.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks {
    public static Block createWoodcutterBlock() {
        return new WoodCutterBlock(BlockBehaviour.Properties.of()
                .noOcclusion()
                .strength(2f)
                .sound(SoundType.COPPER));

    }
    public static Item createWoodcutterItem(Block block) {
        return new BlockItem(block, new Item.Properties());
    }

    public static Block WOODCUTTER;
    public static Item WOODCUTTER_ITEM;
}
