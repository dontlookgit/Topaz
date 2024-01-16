package dev.daydreamers.topaz.client.features.movement;

import dev.daydreamers.topaz.client.Wrapper;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class Fly {

    public static final String name = "Fly";
    public static boolean toggle = false;
    public static final int color = 0x5DD8E8;

    private static int bypassTimer = 0;

    private static float speed = 1F;

    public static void onFly() {
        if (toggle) {
            assert Wrapper.getMC().player != null;
            bypassTimer++;
            //Entity flying = MinecraftClient.getInstance().player;
            double x = 0;
            double y = 0;
            double z = 0;


            if (Wrapper.getMC().player.input.jumping) {
                y += speed;
            }

            if (Wrapper.getMC().player.input.sneaking) {
                y -= speed;
            }

            if (Wrapper.getMC().player.input.movementForward != 0 || Wrapper.getMC().player.input.movementSideways != 0) {
                double angle = Math.toRadians(Wrapper.getMC().player.headYaw) - Math.atan2(Math.signum(Wrapper.getMC().player.input.movementSideways), Math.signum(Wrapper.getMC().player.input.movementForward));

                x = -Math.sin(angle) * speed;
                z = Math.cos(angle) * speed;
            }

            Wrapper.getMC().player.setVelocity(x, y, z);

            if (bypassTimer > 10) {
                //Vec3d velocity = MinecraftClient.getInstance().player.getVelocity();
                Wrapper.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(Wrapper.getMC().player.getX(), Wrapper.getMC().player.getY() - 0.2, Wrapper.getMC().player.getZ(), false));
                Wrapper.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(Wrapper.getMC().player.getX(), Wrapper.getMC().player.getY() + 0.2, Wrapper.getMC().player.getZ(), false));
                bypassTimer = 0;
            }
            if (Wrapper.getMC().player.getY() % 1 == 0) {
                if (Wrapper.getMC().player.isOnGround()) {
                    if (Wrapper.getMC().player.getVelocity().y == 0) {

                    }
                }
            }
        }
    }
}
