package dev.daydreamers.topaz.client.features.movement;

import net.minecraft.client.MinecraftClient;

public class Step {

    public static final String name = "Step";
    public static boolean toggle = true;
    public static final int color = 0x656565;

    public static void onStep() {
        if (toggle) {
        assert MinecraftClient.getInstance().player != null;
        if(MinecraftClient.getInstance().player.verticalCollision) {
            MinecraftClient.getInstance().player.stepHeight = 1;
        } else {
            MinecraftClient.getInstance().player.stepHeight = 0.5f;
            }
        }
    }
}
