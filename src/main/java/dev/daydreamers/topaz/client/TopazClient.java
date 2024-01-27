package dev.daydreamers.topaz.client;

import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.daydreamers.topaz.client.utils.Command;
import dev.daydreamers.topaz.client.utils.KeyInputHandler;
import dev.daydreamers.topaz.client.utils.Wrapper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;

public class TopazClient implements ClientModInitializer {

    public static String CLIENT_NAME = "Topaz";
    public static String CLIENT_VERSION = "0.4";

    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
        Command.onCommand();
    }
}
