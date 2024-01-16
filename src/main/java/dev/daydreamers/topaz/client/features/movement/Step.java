package dev.daydreamers.topaz.client.features.movement;

import dev.daydreamers.topaz.client.Wrapper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextContent;

import java.util.List;

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
