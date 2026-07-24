package net.zebatek.simple_woodcutter.block;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.zebatek.simple_woodcutter.SimpleWoodcutter;

public class ModBlocks {
    public static Block createWoodcutterBlock() {
        return new WoodCutterBlock(BlockBehaviour.Properties.of()
                .noOcclusion()
                .strength(2f)
                .sound(SoundType.COPPER)
                .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(SimpleWoodcutter.MOD_ID, "woodcutter")))
        );

    }
    public static Item createWoodcutterItem(Block block) {
        return new BlockItem(block, new Item.Properties()
                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(SimpleWoodcutter.MOD_ID, "woodcutter"))));
    }

    public static Block WOODCUTTER;
    public static Item WOODCUTTER_ITEM;
}
