package dev.daydreamers.topaz.client.features.render;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.SelectorTextContent;
import net.minecraft.text.Style;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

import java.util.List;

/*
Stolen from Wurst. Difficulty seeing nametag when entity is underwater or bad lighting.
Idea: Get the name of player and render a new tag in OpenGL over the old tag. In theory should be less dependent on game code.
 */

public class PlayerESP {

    public static boolean toggle = true;

    public static void nametag () {
        if (toggle) {
            ClientTickEvents.END_CLIENT_TICK.register(client -> {
                assert client.world != null;
                for (AbstractClientPlayerEntity player : client.world.getPlayers()) {
                    MutableText nameTag = MutableText.of(new SelectorTextContent(player.getName().toString(), null));
                    //MutableText nameTag = MutableText.of(new LiteralTextContent(player.getName().getString()));
                    // Modify name tag appearance based on sneaking status
                    if (player.isSneaking()) {
                        nameTag.setStyle(Style.EMPTY.withColor(Formatting.WHITE)
                                .withBold(true)
                                .withItalic(false));
                    } else {
                        nameTag.setStyle(Style.EMPTY.withColor(Formatting.DARK_RED)
                                .withBold(true)
                                .withItalic(false));
                    }
                    // Set name tag above player
                    player.setCustomName(nameTag);
                    player.setCustomNameVisible(true);
                }
            });
        }
    }
}
