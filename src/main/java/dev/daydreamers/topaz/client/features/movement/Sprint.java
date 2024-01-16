package dev.daydreamers.topaz.client.features.movement;

import dev.daydreamers.topaz.client.Wrapper;
import net.minecraft.client.MinecraftClient;

public class Sprint {

    public static final String name = "Sprint";
    public static boolean toggle = false;
    public static final int color = 0xF3BF00;

    public static void onSprint() {
        if (toggle) {
            assert Wrapper.getMC().player != null;
            if (Wrapper.getMC().options.forwardKey.isPressed()) {
                Wrapper.getMC().player.setSprinting(true);
            }
        }
    }
}
