package dev.daydreamers.topaz.client.features.player;

import dev.daydreamers.topaz.client.utils.Wrapper;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

import java.util.Random;

public class Retard {

    public static final String name = "Retard";
    public static boolean toggle = false;
    public static int color = 0x83457D;


    public static void onRetard() {
        if (toggle) {
            Random random = new Random();
            float yaw = random.nextFloat(361);
            float pitch = random.nextFloat(-91, 91);
            assert Wrapper.getMC().player != null;
            PlayerMoveC2SPacket packetLook = new PlayerMoveC2SPacket.LookAndOnGround(yaw, pitch, Wrapper.getMC().player.isOnGround());
            Wrapper.sendPacket(packetLook);
        }
    }
}
