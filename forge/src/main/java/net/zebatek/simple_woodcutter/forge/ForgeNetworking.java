package net.zebatek.simple_woodcutter.forge;

import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkProtocol;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;
import net.zebatek.simple_woodcutter.SimpleWoodcutter;
import net.zebatek.simple_woodcutter.network.ClientWoodcutterRecipes;
import net.zebatek.simple_woodcutter.network.WoodcutterRecipeSyncPayload;

public class ForgeNetworking {
    public static final SimpleChannel CHANNEL = ChannelBuilder
            .named(Identifier.fromNamespaceAndPath(SimpleWoodcutter.MOD_ID, "main"))
            .simpleChannel();

    public static void register() {
        CHANNEL.protocol(NetworkProtocol.PLAY)
                .flow(PacketFlow.CLIENTBOUND)
                .add(WoodcutterRecipeSyncPayload.class, WoodcutterRecipeSyncPayload.STREAM_CODEC, (payload, ctx) -> {
                    ctx.enqueueWork(() -> ClientWoodcutterRecipes.set(payload.recipes()));
                    ctx.setPacketHandled(true);
                })
                .build();
    }

    public static void sendToPlayer(ServerPlayer player, WoodcutterRecipeSyncPayload payload) {
        CHANNEL.send(payload, PacketDistributor.PLAYER.with(player));
    }
}