package dev.daydreamers.topaz.client.mixins;

import dev.daydreamers.topaz.client.TopazClient;
import dev.daydreamers.topaz.client.Wrapper;
import dev.daydreamers.topaz.client.features.combat.Killaura;
import dev.daydreamers.topaz.client.features.movement.Dolphin;
import dev.daydreamers.topaz.client.features.movement.Fly;
import dev.daydreamers.topaz.client.features.movement.Sprint;
import dev.daydreamers.topaz.client.features.movement.Step;
import dev.daydreamers.topaz.client.features.player.Nofall;
import dev.daydreamers.topaz.client.features.player.Retard;
import dev.daydreamers.topaz.client.features.player.Sneak;
import dev.daydreamers.topaz.client.features.world.Autotool;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    @Shadow public abstract void tick(boolean paused);

    @Shadow public abstract TextRenderer getTextRenderer();

    @Shadow public abstract DebugHud getDebugHud();

    @Unique
    String CLIENT_STRING = TopazClient.CLIENT_NAME + " " + TopazClient.CLIENT_VERSION;

    @Inject(at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;enableBlend()V", remap = false, ordinal = 3), method = "render(Lnet/minecraft/client/gui/DrawContext;F)V")
    private void draw(DrawContext context, float tickDelta, CallbackInfo ci){
        Window window = Wrapper.getMC().getWindow();
        window.setTitle(CLIENT_STRING);
        if (!Wrapper.getMC().getDebugHud().shouldShowDebugHud()) {
            //Overlay.onOverlayRender();
            //Client name & fps
            context.drawCenteredTextWithShadow(getTextRenderer(), CLIENT_STRING, CLIENT_STRING.length() + (CLIENT_STRING.length() / 2) * 4 + 1, 2, 0xFFFFFF);
            String FPS = "FPS:" + Integer.toString(Wrapper.getMC().getCurrentFps());
            context.drawCenteredTextWithShadow(getTextRenderer(), FPS, FPS.length() + (FPS.length() / 2) * 4 + 1, 12, 0xFFFFFF);
            //Arraylist
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
            featureNames.add(Dolphin.name);
            featureStates.add(Dolphin.toggle);
            featureColors.add(Dolphin.color);
            featureNames.add(Killaura.name);
            featureStates.add(Killaura.toggle);
            featureColors.add(Killaura.color);

            int y = 2;
            for (int i = 0; i < featureNames.size(); i++) {
                String featureName = featureNames.get(i);
                boolean featureState = featureStates.get(i);
                int featureColor = featureColors.get(i);
                int screenWidth = Wrapper.getMC().getWindow().getScaledWidth();
                int x = screenWidth - Wrapper.getMC().textRenderer.getWidth(featureName) / 2 - 2;
                if (featureState) {
                    //Wrapper.getMC().textRenderer.drawWithShadow(new MatrixStack(), featureName, x, y, featureColor);
                    context.drawCenteredTextWithShadow(getTextRenderer(), featureName, x, y, featureColor);
                    y += 10;
                }
            }
        }
    }
}