package dev.daydreamers.topaz.client.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/*
Stolen from Wurst. Difficulty seeing when entity is underwater or bad lighting.
 */

@Mixin(EntityRenderer.class)
public class EntityRendererMixin {

    @Shadow
    @Final
    protected EntityRenderDispatcher dispatcher;

    @Inject(at = @At("HEAD"),
            method = "renderLabelIfPresent(Lnet/minecraft/entity/Entity;Lnet/minecraft/text/Text;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            cancellable = true)
    private void onRenderLabelIfPresent(Entity entity, Text text, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
        topazRenderLabelIfPresent(entity, text, matrixStack, vertexConsumerProvider, i);
        ci.cancel();
    }

    /**
     * Copy of renderLabelIfPresent() since calling the original would result in
     * an infinite loop. Also makes it easier to modify.
     */
    protected void topazRenderLabelIfPresent(Entity entity, Text text, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light)
    {
        //NameTagsHack nameTags = WurstClient.INSTANCE.getHax().nameTagsHack;

        // disable distance limit if configured in NameTags
        double distanceSq = dispatcher.getSquaredDistanceToCamera(entity);
        if(distanceSq > 4096)
            return;

        // disable sneaking changes if NameTags is enabled
        boolean notSneaky = !entity.isSneaky();

        float matrixY = entity.getHeight() + 0.5F;
        int labelY = "deadmau5".equals(text.getString()) ? -10 : 0;

        matrices.push();
        matrices.translate(0, matrixY, 0);
        matrices.multiply(dispatcher.getRotation());

        // adjust scale if NameTags is enabled
        float scale = 0.025F;

        assert MinecraftClient.getInstance().player != null;
        double distance = MinecraftClient.getInstance().player.distanceTo(entity);
        if(distance > 10)
            scale *= (float) (distance / 10);

        matrices.scale(-scale, -scale, scale);

        Matrix4f matrix = matrices.peek().getPositionMatrix();
        float bgOpacity =
                MinecraftClient.getInstance().options.getTextBackgroundOpacity(0.25F);
        int bgColor = (int)(bgOpacity * 255F) << 24;
        TextRenderer tr = getTextRenderer();
        float labelX = (float) -tr.getWidth(text) / 2;

        // draw background
        tr.draw(text, labelX, labelY, 0xD90101, false, matrix, vertexConsumers, true, bgColor, light);
        //tr.draw(text, labelX, labelY, 0x20FFFFFF, false, matrix, vertexConsumers,
                //notSneaky ? TextRenderer.TextLayerType.SEE_THROUGH : TextRenderer.TextLayerType.NORMAL,
                //bgColor, light);

        // use the see-through layer for text if configured in NameTags
        TextRenderer.TextLayerType textLayer = TextRenderer.TextLayerType.SEE_THROUGH;

        // draw text
        if(notSneaky)
            tr.draw(text, labelX, labelY, 0xFFFFFF, false, matrix, vertexConsumers, true, 0, light);
        matrices.pop();
    }

    @Shadow
    public TextRenderer getTextRenderer()
    {
        return null;
    }

}
