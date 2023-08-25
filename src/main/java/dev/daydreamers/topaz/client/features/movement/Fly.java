package dev.daydreamers.topaz.client.features.movement;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.math.Vec3d;

public class Fly {

    public static final String name = "Fly";
    public static boolean toggle = false;
    public static final int color = 0x5DD8E8;

    private static int bypassTimer = 0;

    private static float speed = 1F;

    public static void onFly() {
        if (toggle) {
            assert MinecraftClient.getInstance().player != null;
            bypassTimer++;
            //Entity flying = MinecraftClient.getInstance().player;
            double x = 0;
            double y = 0;
            double z = 0;


            if (MinecraftClient.getInstance().player.input.jumping) {
                y += speed;
            }

            if (MinecraftClient.getInstance().player.input.sneaking) {
                y -= speed;
            }

            if (MinecraftClient.getInstance().player.input.movementForward != 0 || MinecraftClient.getInstance().player.input.movementSideways != 0) {
                double angle = Math.toRadians(MinecraftClient.getInstance().player.headYaw) - Math.atan2(Math.signum(MinecraftClient.getInstance().player.input.movementSideways), Math.signum(MinecraftClient.getInstance().player.input.movementForward));

                x = -Math.sin(angle) * speed;
                z = Math.cos(angle) * speed;
            }

            MinecraftClient.getInstance().player.setVelocity(x, y, z);

            if (bypassTimer > 10) {
                //Vec3d velocity = MinecraftClient.getInstance().player.getVelocity();
                MinecraftClient.getInstance().player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(MinecraftClient.getInstance().player.getX(), MinecraftClient.getInstance().player.getY() - 0.2, MinecraftClient.getInstance().player.getZ(), false));
                MinecraftClient.getInstance().player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(MinecraftClient.getInstance().player.getX(), MinecraftClient.getInstance().player.getY() + 0.2, MinecraftClient.getInstance().player.getZ(), false));
                bypassTimer = 0;
            }
            if (MinecraftClient.getInstance().player.getY() % 1 == 0) {
                if (MinecraftClient.getInstance().player.isOnGround()) {
                    if (MinecraftClient.getInstance().player.getVelocity().y == 0) {

                    }
                }
            }
        }
    }
}
