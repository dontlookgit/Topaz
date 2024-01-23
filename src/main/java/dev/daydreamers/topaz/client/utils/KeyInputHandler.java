package dev.daydreamers.topaz.client.utils;

import dev.daydreamers.topaz.client.features.combat.Killaura;
import dev.daydreamers.topaz.client.features.movement.*;
import dev.daydreamers.topaz.client.features.player.Nofall;
import dev.daydreamers.topaz.client.features.player.Retard;
import dev.daydreamers.topaz.client.features.player.Sneak;
import dev.daydreamers.topaz.client.features.render.Fullbright;
import dev.daydreamers.topaz.client.features.world.Autotool;
import dev.daydreamers.topaz.client.features.world.Speedmine;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
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
    public static KeyBinding autotoolKey;
    public static KeyBinding dolphinKey;
    public static KeyBinding killauraKey;
    public static KeyBinding speedmineKey;
    public static KeyBinding fullbrightKey;
    public static KeyBinding boatflyKey;

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
            if (autotoolKey.wasPressed()) {
                Autotool.toggle = !Autotool.toggle;
            }
            if (dolphinKey.wasPressed()) {
                Dolphin.toggle = !Dolphin.toggle;
            }
            if (killauraKey.wasPressed()) {
                Killaura.toggle = !Killaura.toggle;
            }
            if (speedmineKey.wasPressed()) {
                Speedmine.toggle = !Speedmine.toggle;
                Autotool.toggle = !Autotool.toggle;
            }
            if (fullbrightKey.wasPressed()) {
                Fullbright.toggle = !Fullbright.toggle;
            }
            if (boatflyKey.wasPressed()) {
                BoatFly.toggle = !BoatFly.toggle;
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
        autotoolKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(Autotool.name, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, KEY_CATEGORY));
        dolphinKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(Dolphin.name, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_J, KEY_CATEGORY));
        killauraKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(Killaura.name, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_K, KEY_CATEGORY));
        speedmineKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(Speedmine.name, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_G, KEY_CATEGORY));
        fullbrightKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(Fullbright.name, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_C, KEY_CATEGORY));
        boatflyKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(BoatFly.name, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, KEY_CATEGORY));
        registerKeyInput();
    }

}
