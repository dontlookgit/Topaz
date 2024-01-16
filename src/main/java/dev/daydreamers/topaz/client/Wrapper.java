package dev.daydreamers.topaz.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.Packet;

import java.util.Objects;

public class Wrapper {

    public static MinecraftClient getMC() {
        return MinecraftClient.getInstance();
    }

    public static void sendPacket(Packet packet) {
        Objects.requireNonNull(Wrapper.getMC().getNetworkHandler()).sendPacket(packet);
    }

}
