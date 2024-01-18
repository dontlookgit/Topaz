package dev.daydreamers.topaz.client.features.player;

import dev.daydreamers.topaz.client.utils.Wrapper;
import dev.daydreamers.topaz.client.features.movement.Fly;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

public class Nofall {

    public static final String name = "Nofall";
    public static boolean toggle = true;
    public static final int color = 0xC90000;

    public static void onNofall() {
        assert Wrapper.getMC().player != null;
        if(Wrapper.getMC().player.fallDistance >= 2 && toggle && !Fly.toggle) {
            PlayerMoveC2SPacket packetLook = new PlayerMoveC2SPacket.LookAndOnGround(Wrapper.getMC().player.getYaw(), Wrapper.getMC().player.getPitch(), true);
            PlayerMoveC2SPacket packetPos = new PlayerMoveC2SPacket.PositionAndOnGround(Wrapper.getMC().player.getX(), Wrapper.getMC().player.getY(), Wrapper.getMC().player.getZ(), true);
            Wrapper.sendPacket(packetLook);
            Wrapper.sendPacket(packetPos);
        } else if (Fly.toggle) {
            PlayerMoveC2SPacket packetLookFly = new PlayerMoveC2SPacket.LookAndOnGround(Wrapper.getMC().player.getYaw(), Wrapper.getMC().player.getPitch(), false);
            PlayerMoveC2SPacket packetPosFly = new PlayerMoveC2SPacket.PositionAndOnGround(Wrapper.getMC().player.getX(), Wrapper.getMC().player.getY(), Wrapper.getMC().player.getZ(), false);
            Wrapper.sendPacket(packetLookFly);
            Wrapper.sendPacket(packetPosFly);
        }
        //onMLG();
    }

    //currently broken. it looks down but continually presses the use key when looking at the ground even when back on ground.
    //possible fix include get the position of where the water was placed and picking up from there with a counter.
    //have a check to see if players on ground or if it picked up water.
    //also need to fix switch, it seems broken, possibly delay.
    public static void onMLG() {
        assert MinecraftClient.getInstance().player != null;
        if(MinecraftClient.getInstance().player.fallDistance >= 2 && toggle) {
            PlayerMoveC2SPacket packetLook = new PlayerMoveC2SPacket.LookAndOnGround(MinecraftClient.getInstance().player.getYaw(), 91, MinecraftClient.getInstance().player.isOnGround());
            Objects.requireNonNull(MinecraftClient.getInstance().getNetworkHandler()).sendPacket(packetLook);
            int d = MinecraftClient.getInstance().player.getInventory().selectedSlot;
            switchToWaterBucket(MinecraftClient.getInstance().player);
            ItemStack heldItem = MinecraftClient.getInstance().player.getMainHandStack();
            assert MinecraftClient.getInstance().world != null;
            if (MinecraftClient.getInstance().world.canSetBlock(MinecraftClient.getInstance().player.getBlockPos()) && heldItem.getItem() == Items.WATER_BUCKET) {
                MinecraftClient.getInstance().player.setPitch(91);
                MinecraftClient.getInstance().options.useKey.setPressed(true);
                if (isPlayerInWater(MinecraftClient.getInstance().player)) {
                    MinecraftClient.getInstance().player.setPitch(91);
                    MinecraftClient.getInstance().options.useKey.setPressed(true);
                    MinecraftClient.getInstance().player.getInventory().selectedSlot = d;
                }
            }
        }
    }

    public static boolean hasWaterBucket(PlayerEntity player) {
        for (int slot = 0; slot < 9; slot++) {
            ItemStack stack = player.getInventory().getStack(slot);
            if (stack.getItem() == Items.WATER_BUCKET) {
                return true;
            }
        }
        return false;
    }

    public static void switchToWaterBucket(PlayerEntity player) {
        if (hasWaterBucket(MinecraftClient.getInstance().player)) {
            for (int slot = 0; slot < 9; slot++) {
                ItemStack stack = player.getInventory().getStack(slot);
                if (stack.getItem() == Items.WATER_BUCKET) {
                    player.setStackInHand(Hand.MAIN_HAND, stack);
                    player.getInventory().selectedSlot = slot;
                    break;
                }
            }
        }
    }

    public static boolean isPlayerInWater(PlayerEntity player) {
        World world = player.getEntityWorld();
        BlockPos footPos = player.getBlockPos();
        BlockPos headPos = footPos.up((int) player.getHeight());
        BlockState footState = world.getBlockState(footPos);
        BlockState headState = world.getBlockState(headPos);
        return footState.getBlock() == Blocks.WATER || headState.getBlock() == Blocks.WATER;
    }
}
