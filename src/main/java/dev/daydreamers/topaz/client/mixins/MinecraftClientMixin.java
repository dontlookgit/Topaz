package dev.daydreamers.topaz.client.mixins;

import dev.daydreamers.topaz.Topaz;
import dev.daydreamers.topaz.client.TopazClient;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Inject(method = "updateWindowTitle()V", at = @At("TAIL"))
    public void updateTitle(CallbackInfo ci){
        MinecraftClient.getInstance().getWindow().setTitle(TopazClient.CLIENT_NAME + " " + TopazClient.CLIENT_VERSION);
    }

}
