package dev.daydreamers.topaz.client.events;

import dev.daydreamers.topaz.client.features.movement.Fly;
import dev.daydreamers.topaz.client.features.movement.Sprint;
import dev.daydreamers.topaz.client.features.movement.Step;
import dev.daydreamers.topaz.client.features.player.Nofall;
import dev.daydreamers.topaz.client.features.player.Retard;
import dev.daydreamers.topaz.client.features.player.Sneak;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import org.lwjgl.glfw.GLFW;

/*
This keybind system sucks. Fix in the future.
 */

public class KeyInputHandler {
    public static final String KEY_CATEGORY = "Topaz";

    public static KeyBinding nofallKey;
    public static KeyBinding stepKey;
    public static KeyBinding retardKey;
    public static KeyBinding sprintKey;
    public static KeyBinding flyKey;
    public static KeyBinding sneakKey;

    public static void registerKeyInput() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (nofallKey.wasPressed()) {
                Nofall.toggle = !Nofall.toggle;
            }
            if (stepKey.wasPressed()) {
                Step.toggle = !Step.toggle;
            }
            if (retardKey.wasPressed()) {
                Retard.toggle = !Retard.toggle;
            }
            if (sprintKey.wasPressed()) {
                Sprint.toggle = !Sprint.toggle;
            }
            if (flyKey.wasPressed()) {
                Fly.toggle = !Fly.toggle;
            }
            if (sneakKey.wasPressed()) {
                Sneak.toggle = !Sneak.toggle;
                if (sneakKey.wasPressed() && !Sneak.toggle) {
                    Sneak.onDisable();
                }
            }
        });
    }

    public static void register() {
        nofallKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(Nofall.name, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_N, KEY_CATEGORY));
        stepKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(Step.name, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_B, KEY_CATEGORY));
        retardKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(Retard.name, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, KEY_CATEGORY));
        sprintKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(Sprint.name, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_M, KEY_CATEGORY));
        flyKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(Fly.name, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R, KEY_CATEGORY));
        sneakKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(Sneak.name, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_Z, KEY_CATEGORY));

        registerKeyInput();
    }

}
