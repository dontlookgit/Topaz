package dev.daydreamers.topaz.client.features.movement;

import dev.daydreamers.topaz.client.Wrapper;
import net.minecraft.util.math.Vec3d;

public class Dolphin {

    public static final String name = "Dolphin";
    public static boolean toggle = false;
    public static final int color = 0x4a87f0;

    public static void onDolphin() {
        if (toggle) {
            assert Wrapper.getMC().player != null;
            if (Wrapper.getMC().player.isTouchingWater() && !Wrapper.getMC().player.isSneaking()) {
                Vec3d velocity = Wrapper.getMC().player.getVelocity();
                Wrapper.getMC().player.setVelocity(velocity.x, velocity.y + 0.05, velocity.z);
            }
        }
    }

}
