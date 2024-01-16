package dev.daydreamers.topaz.client.features.render;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.daydreamers.topaz.client.TopazClient;
import dev.daydreamers.topaz.client.Wrapper;
import dev.daydreamers.topaz.client.features.movement.Fly;
import dev.daydreamers.topaz.client.features.movement.Sprint;
import dev.daydreamers.topaz.client.features.movement.Step;
import dev.daydreamers.topaz.client.features.player.Nofall;
import dev.daydreamers.topaz.client.features.player.Retard;
import dev.daydreamers.topaz.client.features.player.Sneak;
import dev.daydreamers.topaz.client.features.world.Autotool;
import dev.daydreamers.topaz.client.mixins.InGameHudMixin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;

import java.nio.channels.WritePendingException;
import java.util.ArrayList;
import java.util.List;

import static net.fabricmc.fabric.api.client.screen.v1.Screens.getTextRenderer;

public class Overlay {

    private static double tps = 0;

    public static void onOverlayRender() {
        if (!Wrapper.getMC().getDebugHud().shouldShowDebugHud()) {
            //Wrapper.getMC().textRenderer.drawWithShadow(new MatrixStack(), String.format("%s (rel-%s)", TopazClient.CLIENT_NAME + " " + TopazClient.CLIENT_VERSION, SharedConstants.getGameVersion().getName()), 2, 2, 0xffffff);
            //onArraylistRender();
            onTPSRender();
        }
    }

    public static void onTPSRender() {
        //MinecraftClient.getInstance().textRenderer.drawWithShadow(new MatrixStack(), "TPS: " + tps + "/20", 2, 2 + MinecraftClient.getInstance().textRenderer.fontHeight, 0xffffff);
    }

    public static void onArraylistRender() {
        List<String> featureNames = new ArrayList<>();
        List<Boolean> featureStates = new ArrayList<>();
        List<Integer> featureColors = new ArrayList<>();

        featureNames.add(Nofall.name);
        featureStates.add(Nofall.toggle);
        featureColors.add(Nofall.color);
        featureNames.add(Step.name);
        featureStates.add(Step.toggle);
        featureColors.add(Step.color);
        featureNames.add(Retard.name);
        featureStates.add(Retard.toggle);
        featureColors.add(Retard.color);
        featureNames.add(Sprint.name);
        featureStates.add(Sprint.toggle);
        featureColors.add(Sprint.color);
        featureNames.add(Fly.name);
        featureStates.add(Fly.toggle);
        featureColors.add(Fly.color);
        featureNames.add(Sneak.name);
        featureStates.add(Sneak.toggle);
        featureColors.add(Sneak.color);
        featureNames.add(Autotool.name);
        featureStates.add(Autotool.toggle);
        featureColors.add(Autotool.color);

        int y = 2;
        for (int i = 0; i < featureNames.size(); i++) {
            String featureName = featureNames.get(i);
            boolean featureState = featureStates.get(i);
            int featureColor = featureColors.get(i);
            int screenWidth = Wrapper.getMC().getWindow().getScaledWidth();
            int x = screenWidth - Wrapper.getMC().textRenderer.getWidth(featureName) - 2;

            if (featureState) {
                //DrawContext context;
                //TextRenderer textRenderer = Wrapper.getMC().textRenderer;
                //context.drawCenteredTextWithShadow(textRenderer, featureName, x, y, featureColor);
                //Wrapper.getMC().textRenderer.drawWithShadow(new MatrixStack(), featureName, x, y, featureColor);
                //context.drawCenteredTextWithShadow(getTextRenderer(), featureName, x, y, featureColor);
                y += 10;
            }
        }
    }
}
