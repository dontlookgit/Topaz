package dev.daydreamers.topaz.client.features.movement;

import dev.daydreamers.topaz.client.utils.Wrapper;

public class Step {

    public static final String name = "Step";
    public static boolean toggle = true;
    public static final int color = 0x656565;

    public static void onStep() {
        if (toggle) {
        assert Wrapper.getMC().player != null;
        if(Wrapper.getMC().player.verticalCollision) {
            Wrapper.getMC().player.setStepHeight(1);
        } else {
            Wrapper.getMC().player.setStepHeight(0.5F);
            }
        }
    }
}
