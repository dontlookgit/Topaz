package dev.daydreamers.topaz.client.mixins;

import dev.daydreamers.topaz.client.TopazClient;
import dev.daydreamers.topaz.client.events.KeyInputHandler;
import dev.daydreamers.topaz.client.features.movement.Fly;
import dev.daydreamers.topaz.client.features.movement.Sprint;
import dev.daydreamers.topaz.client.features.movement.Step;
import dev.daydreamers.topaz.client.features.player.Nofall;
import dev.daydreamers.topaz.client.features.player.Retard;
import dev.daydreamers.topaz.client.features.player.Sneak;
import dev.daydreamers.topaz.client.features.render.Overlay;
import dev.daydreamers.topaz.client.features.render.PlayerESP;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    public void onPreTick(CallbackInfo info) {
        Step.onStep();
        Nofall.onNofall();
        PlayerESP.nametag();
        Retard.onRetard();
        Sprint.onSprint();
        Fly.onFly();
        Sneak.onSneak();
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void onPostTick(CallbackInfo info) {

    }
}