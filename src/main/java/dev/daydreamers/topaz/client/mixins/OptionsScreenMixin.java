package dev.daydreamers.topaz.client.mixins;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptionsScreen.class)
public class OptionsScreenMixin extends Screen {


    @Shadow @Final private Screen parent;
    @Shadow @Final private GameOptions settings;

    protected OptionsScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("TAIL"))
    public void onOptionScreenInit(CallbackInfo info) {
        //this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, this.height / 6 + 144 - 6, 150, 20, Text.translatable("topaz.screen"), (button) -> {
            //assert this.client != null;
        //}));
    }
}
