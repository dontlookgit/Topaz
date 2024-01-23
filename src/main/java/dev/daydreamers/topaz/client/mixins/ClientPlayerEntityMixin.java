package dev.daydreamers.topaz.client.mixins;

import dev.daydreamers.topaz.client.features.combat.Killaura;
import dev.daydreamers.topaz.client.features.movement.*;
import dev.daydreamers.topaz.client.features.player.Nofall;
import dev.daydreamers.topaz.client.features.player.Retard;
import dev.daydreamers.topaz.client.features.player.Sneak;
import dev.daydreamers.topaz.client.features.world.Autotool;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    public void onPreTick(CallbackInfo info) {
        Step.onStep();
        Nofall.onNofall();
        //PlayerESP.nametag();
        Retard.onRetard();
        Sprint.onSprint();
        Fly.onFly();
        Sneak.onSneak();
        Autotool.onAutotool();
        Dolphin.onDolphin();
        Killaura.onKillaura();
        BoatFly.onBoatFly();
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void onPostTick(CallbackInfo info) {

    }
}