package net.zebatek.simple_woodcutter.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.zebatek.simple_woodcutter.SimpleWoodcutter;
import net.zebatek.simple_woodcutter.recipe.WoodcutterRecipeView;

import java.util.List;

public record WoodcutterRecipeSyncPayload(List<WoodcutterRecipeView> recipes) implements CustomPacketPayload {

    public static final Type<WoodcutterRecipeSyncPayload> TYPE =
            new Type<>(Identifier.fromNamespaceAndPath(SimpleWoodcutter.MOD_ID, "sync_woodcutter_recipes"));

    public static final StreamCodec<RegistryFriendlyByteBuf, WoodcutterRecipeSyncPayload> STREAM_CODEC = StreamCodec.composite(
            WoodcutterRecipeView.STREAM_CODEC.apply(ByteBufCodecs.list()),
            WoodcutterRecipeSyncPayload::recipes,
            WoodcutterRecipeSyncPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}