package dev.daydreamers.topaz.client.features.movement;

import net.minecraft.client.MinecraftClient;

public class Sprint {

    public static final String name = "Sprint";
    public static boolean toggle = false;
    public static final int color = 0xF3BF00;

    public static void onSprint() {
        if (toggle) {
            assert MinecraftClient.getInstance().player != null;
            if (MinecraftClient.getInstance().options.forwardKey.isPressed()) {
                MinecraftClient.getInstance().player.setSprinting(true);
            }
        }
    }
}
