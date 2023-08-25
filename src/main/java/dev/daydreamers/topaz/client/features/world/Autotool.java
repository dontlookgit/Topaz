package dev.daydreamers.topaz.client.features.world;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;

/*
    Trying to figure it out.
    Write out:
        - Register block hit
        - Find best item
        - Look for item in hotbar
        - Switch to item in hotbar
        - Switch back to previous hotbar slot on break
*/

public class Autotool {

    public static void onAutotool() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if(world.isClient) {
                return ActionResult.PASS;
            }

            BlockState blockState = world.getBlockState(hitResult.getBlockPos());
            Block block = blockState.getBlock();

            ItemStack bestTool = getBestToolForBlock(block);

            return ActionResult.PASS;
        });
    }

    private static ItemStack getBestToolForBlock(Block block) {
        if (block == Blocks.STONE) {
            return new ItemStack(Items.IRON_AXE);
        } else if (block == Blocks.DIRT || block == Blocks.GRASS_BLOCK) {
            return new ItemStack(Items.IRON_SHOVEL);
        } else if (block == Blocks.OAK_LOG || block == Blocks.OAK_PLANKS) {
            return new ItemStack(Items.IRON_AXE);
        } else {
            return null;
        }
    }

}
