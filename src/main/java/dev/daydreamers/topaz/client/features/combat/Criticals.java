package dev.daydreamers.topaz.client.features.combat;

import dev.daydreamers.topaz.client.utils.Wrapper;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class Criticals {

    public static void doCritical() {
        assert Wrapper.getMC().player != null;
        double posX = Wrapper.getMC().player.getX();
        double posY = Wrapper.getMC().player.getY();
        double posZ = Wrapper.getMC().player.getZ();
        Wrapper.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(posX, posY + 0.0633, posZ, false));
        Wrapper.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(posX, posY, posZ, false));
    }

}
