package de.pnku.mstv_mframev.mixin.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import de.pnku.mstv_mframev.MoreFrameVariants;
import de.pnku.mstv_mframev.mixin.client.util.TextureAtlasHolderAccessor;
import de.pnku.mstv_mframev.renderer.renderstates.MoreFrameVariantPaintingRenderState;
import de.pnku.mstv_mframev.util.IPainting;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PaintingRenderer;
import net.minecraft.client.renderer.entity.state.PaintingRenderState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.PaintingTextureManager;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(value = EnvType.CLIENT)
@Mixin(PaintingRenderer.class)
public abstract class PaintingRendererMixin extends EntityRenderer<Painting, MoreFrameVariantPaintingRenderState> {

    public PaintingRendererMixin(EntityRendererProvider.Context context) {
        super(context);
    }

    @Inject(method = "createRenderState()Lnet/minecraft/client/renderer/entity/state/PaintingRenderState;", at = @At("HEAD"), cancellable = true)
    public void injectedCreateRenderState(CallbackInfoReturnable<MoreFrameVariantPaintingRenderState> cir){
        cir.setReturnValue(new MoreFrameVariantPaintingRenderState());
    }

    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/decoration/Painting;Lnet/minecraft/client/renderer/entity/state/PaintingRenderState;F)V", at = @At("HEAD"))
    public void injectedExtractRenderState(Painting painting, PaintingRenderState paintingRenderState, float f, CallbackInfo ci) {
        MoreFrameVariantPaintingRenderState moreFrameVariantPaintingRenderState = (MoreFrameVariantPaintingRenderState) paintingRenderState;
        super.extractRenderState(painting, moreFrameVariantPaintingRenderState, f);
        moreFrameVariantPaintingRenderState.paintingFrameVariant = ((IPainting) painting).mframev$getPWoodVariant();
    }

    @Inject(method = "render(Lnet/minecraft/client/renderer/entity/state/PaintingRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At("TAIL"))
    public void injectedRender(PaintingRenderState paintingRenderState, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci) {
        MoreFrameVariantPaintingRenderState moreFrameVariantPaintingRenderState = (MoreFrameVariantPaintingRenderState) paintingRenderState;
        String paintingFrameVariant = moreFrameVariantPaintingRenderState.paintingFrameVariant;
        if (!paintingFrameVariant.isEmpty() && !paintingFrameVariant.equals("default")) {
            PaintingVariant paintingVariant = paintingRenderState.variant;
            if (paintingVariant != null) {
                poseStack.pushPose();
                poseStack.mulPose(Axis.YP.rotationDegrees((float)(180 - paintingRenderState.direction.get2DDataValue() * 90)));
                PaintingTextureManager paintingTextureManager = Minecraft.getInstance().getPaintingTextures();
                TextureAtlasSprite textureAtlasSprite = paintingTextureManager.getBackSprite();
                VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entitySolidZOffsetForward(textureAtlasSprite.atlasLocation()));
                TextureAtlasHolderAccessor accessor = ((TextureAtlasHolderAccessor) Minecraft.getInstance().getPaintingTextures());
                String vanillaPaintingSpriteName = paintingTextureManager.get(paintingVariant).contents().name().getPath();
                TextureAtlasSprite paintingSprite = accessor.callGetSprite(MoreFrameVariants.asId(vanillaPaintingSpriteName + "_" + paintingFrameVariant));
                TextureAtlasSprite backSprite = accessor.callGetSprite(MoreFrameVariants.asId(paintingFrameVariant + "_planks"));
                this.renderPainting(
                        poseStack,
                        vertexConsumer,
                        paintingRenderState.lightCoords,
                        paintingVariant.width(),
                        paintingVariant.height(),
                        paintingSprite,
                        backSprite
                );
                poseStack.popPose();
                super.render(moreFrameVariantPaintingRenderState, poseStack, multiBufferSource, i);
            }
        }
    }

    @Shadow private void renderPainting(PoseStack poseStack, VertexConsumer vertexConsumer, int[] is, int i, int j, TextureAtlasSprite textureAtlasSprite, TextureAtlasSprite textureAtlasSprite2){}
}