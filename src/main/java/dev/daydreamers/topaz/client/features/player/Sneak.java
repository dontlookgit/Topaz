package dev.daydreamers.topaz.client.features.player;

import dev.daydreamers.topaz.client.utils.Wrapper;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;

public class Sneak {

    public static final String name = "Sneak";
    public static boolean toggle = false;
    public static final int color = 0x44A148;

    public static void onSneak() {
        if (toggle) {
            assert Wrapper.getMC().player != null;
            Wrapper.sendPacket(new ClientCommandC2SPacket(Wrapper.getMC().player, ClientCommandC2SPacket.Mode.PRESS_SHIFT_KEY));
        }
    }

    public static void onDisable() {
        assert Wrapper.getMC().player != null;
        Wrapper.sendPacket(new ClientCommandC2SPacket(Wrapper.getMC().player, ClientCommandC2SPacket.Mode.RELEASE_SHIFT_KEY));
    }
}
