package dev.daydreamers.topaz.client.features.movement;

import dev.daydreamers.topaz.client.utils.Wrapper;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class BoatFly {

    public static final String name = "BoatFly";
    public static boolean toggle = false;
    public static final int color = 0x8ed94b;
    public static float speed = 1f;

    public static void onBoatFly() {
        assert Wrapper.getMC().player != null;
        if(!Wrapper.getMC().player.hasVehicle())
            return;
        if(toggle) {
            Entity vehicle = Wrapper.getMC().player.getVehicle();
            assert vehicle != null;
            Vec3d velocity = vehicle.getVelocity();
            double motionX = velocity.x;
            double motionY = 0;
            double motionZ = velocity.z;
            if(Wrapper.getMC().options.jumpKey.isPressed()) {
                motionY = speed;
            }
            if(Wrapper.getMC().options.forwardKey.isPressed()) {
                float yawRad = vehicle.getYaw() * MathHelper.RADIANS_PER_DEGREE;
                motionX = MathHelper.sin(-yawRad) * speed;
                motionZ = MathHelper.cos(yawRad) * speed;
            }
            //apply motion
            vehicle.setVelocity(motionX, motionY, motionZ);
        }
    }

}
