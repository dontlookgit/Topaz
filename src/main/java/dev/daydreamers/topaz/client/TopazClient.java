package dev.daydreamers.topaz.client;

import dev.daydreamers.topaz.client.events.KeyInputHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.profiling.jfr.sample.NetworkIoStatistics;

public class TopazClient implements ClientModInitializer {

    public static String CLIENT_NAME = "Topaz";
    public static String CLIENT_VERSION = "0.4";

    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
            Window window = client.getWindow();
            window.setTitle(CLIENT_NAME + " " + CLIENT_VERSION);
        });
        KeyInputHandler.register();
    }
}
