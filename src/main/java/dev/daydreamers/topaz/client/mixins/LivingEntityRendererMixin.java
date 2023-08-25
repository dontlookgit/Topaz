package dev.daydreamers.topaz.client.mixins;

import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;getInstance()Lnet/minecraft/client/MinecraftClient;", ordinal = 0), method = "hasLabel(Lnet/minecraft/entity/LivingEntity;)Z", cancellable = true)
    private void shouldForceLabel(LivingEntity e, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
