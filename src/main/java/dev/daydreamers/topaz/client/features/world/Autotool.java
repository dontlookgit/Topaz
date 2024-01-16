package dev.daydreamers.topaz.client.features.world;

import dev.daydreamers.topaz.client.Wrapper;
import dev.daydreamers.topaz.client.mixins.ClientPlayerInteractionManagerAccessor;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

/*
Stolen from KiwiClient. Works perfectly.
 */

public class Autotool {

    public static final String name = "Autotool";
    public static boolean toggle = true;
    public static final int color = 0xa1b3d1;

    static boolean prevState = true;
    static int slot = 0;

    public static void onAutotool() {
        ClientPlayerInteractionManager interactionManager = Wrapper.getMC().interactionManager;

        if (interactionManager == null) {
            return;
        }
        if (prevState && !interactionManager.isBreakingBlock()) {
            assert Wrapper.getMC().player != null;
            Wrapper.getMC().player.getInventory().selectedSlot = slot;
        } else if (prevState != interactionManager.isBreakingBlock()) {
            assert Wrapper.getMC().player != null;
            slot = Wrapper.getMC().player.getInventory().selectedSlot;
        }
        if (interactionManager.isBreakingBlock()) {
            BlockPos blockPos = ((ClientPlayerInteractionManagerAccessor) interactionManager).getCurrentBreakingPos();
            assert Wrapper.getMC().world != null;
            BlockState blockState = Wrapper.getMC().world.getBlockState(blockPos);
            swap(blockState);
        }
        prevState = interactionManager.isBreakingBlock();
    }

    public static void swap(BlockState state) {
        float best = 1f;
        int index = -1;
        int optAirIndex = -1;
        for (int i = 0; i < 9; i++) {
            ItemStack stack = Objects.requireNonNull(Wrapper.getMC().player).getInventory().getStack(i);
            if (stack.getItem() == Items.AIR) {
                optAirIndex = i;
            }
            float s = stack.getMiningSpeedMultiplier(state);
            if (s > best) {
                index = i;
            }
        }
        if (index != -1) {
            Wrapper.getMC().player.getInventory().selectedSlot = index;
        } else {
            if (optAirIndex != -1) {
                Wrapper.getMC().player.getInventory().selectedSlot = optAirIndex;
            }
        }
    }

}
