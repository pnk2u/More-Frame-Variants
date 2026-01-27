package de.pnku.mstv_mframev.mixin.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import de.pnku.mstv_mframev.compat.fastitemframes.MoreFrameVariantsCompatibilityFIFClientConstructor;
import de.pnku.mstv_mframev.renderer.renderstates.MoreFrameVariantItemFrameRenderState;
import de.pnku.mstv_mframev.util.IItemFrame;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MapRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemFrameRenderer;
import net.minecraft.client.renderer.entity.state.ItemFrameRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BlockStateDefinitions;
import net.minecraft.core.Direction;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static de.pnku.mstv_mframev.MoreFrameVariants.*;

@Environment(value = EnvType.CLIENT)
@Mixin(ItemFrameRenderer.class)
public abstract class ItemFrameRendererMixin extends EntityRenderer<ItemFrame, MoreFrameVariantItemFrameRenderState> {

    protected ItemFrameRendererMixin(EntityRendererProvider.Context context) {super(context);}

    @Shadow @Final
    private BlockRenderDispatcher blockRenderer;
    @Shadow @Final
    private MapRenderer mapRenderer;


    @Inject(method = "createRenderState()Lnet/minecraft/client/renderer/entity/state/ItemFrameRenderState;", at = @At("HEAD"), cancellable = true)
    public void injectedCreateRenderState(CallbackInfoReturnable<MoreFrameVariantItemFrameRenderState> cir) {
        cir.setReturnValue(new MoreFrameVariantItemFrameRenderState());
    }

    @Inject(method = "submit(Lnet/minecraft/client/renderer/entity/state/ItemFrameRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/CameraRenderState;)V", at = @At(value = "HEAD"), cancellable = true)
    public void injectedSubmit(ItemFrameRenderState itemFrameRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState, CallbackInfo ci){
        MoreFrameVariantItemFrameRenderState moreFrameVariantItemFrameRenderState = (MoreFrameVariantItemFrameRenderState) itemFrameRenderState;
        String itemFrameVariant = moreFrameVariantItemFrameRenderState.itemFrameVariant;
        if (itemFrameVariant != null && !itemFrameVariant.isEmpty() && !itemFrameVariant.equals("birch")) {
            super.submit(moreFrameVariantItemFrameRenderState, poseStack, submitNodeCollector, cameraRenderState);
            poseStack.pushPose();
            Direction direction = itemFrameRenderState.direction;
            Vec3 vec3 = this.getRenderOffset(moreFrameVariantItemFrameRenderState);
            poseStack.translate(-vec3.x(), -vec3.y(), -vec3.z());
            double d = 0.46875;
            poseStack.translate((double) direction.getStepX() * 0.46875, (double) direction.getStepY() * 0.46875, (double) direction.getStepZ() * 0.46875);
            float f;
            float g;
            if (direction.getAxis().isHorizontal()) {
                f = 0.0F;
                g = 180.0F - direction.toYRot();
            } else {
                f = (float) (-90 * direction.getAxisDirection().getStep());
                g = 180.0F;
            }

            poseStack.mulPose(Axis.XP.rotationDegrees(f));
            poseStack.mulPose(Axis.YP.rotationDegrees(g));
            // Copied from FIF's ItemFrameRendererMixin
            if (isFifLoaded) {
                if (MoreFrameVariantsCompatibilityFIFClientConstructor.getCompatFifIsDyedRenderState(moreFrameVariantItemFrameRenderState)) {
                    int color = MoreFrameVariantsCompatibilityFIFClientConstructor.getCompatFifDyedColorRenderState(moreFrameVariantItemFrameRenderState);
                    BlockState blockState = mframev$getItemFrameVariantFakeState(itemFrameVariant, moreFrameVariantItemFrameRenderState.isGlowFrame, moreFrameVariantItemFrameRenderState.mapId != null, true);
                    BlockStateModel blockStateModel = this.blockRenderer.getBlockModel(blockState);
                    poseStack.pushPose();
                    poseStack.translate(-0.5F, -0.5F, -0.5F);
                    float red = ARGB.redFloat(color);
                    float green = ARGB.greenFloat(color);
                    float blue = ARGB.blueFloat(color);
                    submitNodeCollector.submitBlockModel(poseStack,
                            RenderTypes.entitySolidZOffsetForward(TextureAtlas.LOCATION_BLOCKS),
                            blockStateModel,
                            red,
                            green,
                            blue,
                            itemFrameRenderState.lightCoords,
                            OverlayTexture.NO_OVERLAY,
                            itemFrameRenderState.outlineColor);
                    poseStack.popPose();
                    itemFrameRenderState.isInvisible = true;
                }
            }
            //
            if (!itemFrameRenderState.isInvisible) {
                BlockState blockState = mframev$getItemFrameVariantFakeState(itemFrameVariant, moreFrameVariantItemFrameRenderState.isGlowFrame, moreFrameVariantItemFrameRenderState.mapId != null, false);
                BlockStateModel blockStateModel = this.blockRenderer.getBlockModel(blockState);
                poseStack.pushPose();
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                submitNodeCollector.submitBlockModel(
                        poseStack,
                        RenderTypes.entitySolidZOffsetForward(TextureAtlas.LOCATION_BLOCKS),
                        blockStateModel,
                        1.0F,
                        1.0F,
                        1.0F,
                        itemFrameRenderState.lightCoords,
                        OverlayTexture.NO_OVERLAY,
                        itemFrameRenderState.outlineColor
                );
                poseStack.popPose();
            }

            if (itemFrameRenderState.isInvisible) {
                poseStack.translate(0.0F, 0.0F, 0.5F);
            } else {
                poseStack.translate(0.0F, 0.0F, 0.4375F);
            }

            if (itemFrameRenderState.mapId != null) {
                int j = itemFrameRenderState.rotation % 4 * 2;
                poseStack.mulPose(Axis.ZP.rotationDegrees((float)j * 360.0F / 8.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(180.0F));
                float h = 0.0078125F;
                poseStack.scale(0.0078125F, 0.0078125F, 0.0078125F);
                poseStack.translate(-64.0F, -64.0F, 0.0F);
                poseStack.translate(0.0F, 0.0F, -1.0F);
                int k = this.getLightCoords(itemFrameRenderState.isGlowFrame, 15728850, itemFrameRenderState.lightCoords);
                this.mapRenderer.render(itemFrameRenderState.mapRenderState, poseStack, submitNodeCollector, true, k);
            } else if (!itemFrameRenderState.item.isEmpty()) {
                poseStack.mulPose(Axis.ZP.rotationDegrees((float)itemFrameRenderState.rotation * 360.0F / 8.0F));
                int j = this.getLightCoords(itemFrameRenderState.isGlowFrame, 15728880, itemFrameRenderState.lightCoords);
                poseStack.scale(0.5F, 0.5F, 0.5F);
                itemFrameRenderState.item.submit(poseStack, submitNodeCollector, j, OverlayTexture.NO_OVERLAY, itemFrameRenderState.outlineColor);
            }

            poseStack.popPose();
            ci.cancel();
        }
    }

    @Unique
    public BlockState mframev$getItemFrameVariantFakeState(String woodVariant, boolean isGlow, boolean hasMap, boolean isDyed) {
        if (isFifLoaded && (isGlow || isDyed)) {
            return MoreFrameVariantsCompatibilityFIFClientConstructor.getCompatFifBlockState(isGlow, hasMap, isDyed, woodVariant);
        }
        StateDefinition<Block, BlockState> itemFrameVariantFakeDefinition = BlockStateDefinitions.STATIC_DEFINITIONS.get(asId(woodVariant + (isGlow ? "_glow_" : "_") + "item_frame"));
        return itemFrameVariantFakeDefinition.any().setValue(BlockStateProperties.MAP, hasMap);
    }

    @Shadow
    private int getLightCoords(boolean isGlow, int i, int j) {
        return isGlow ? i : j;
    }

    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/decoration/ItemFrame;Lnet/minecraft/client/renderer/entity/state/ItemFrameRenderState;F)V", at = @At("HEAD"))
    public void injectedExtractRenderState(ItemFrame itemFrame, ItemFrameRenderState itemFrameRenderState, float f, CallbackInfo ci){
        MoreFrameVariantItemFrameRenderState moreFrameVariantItemFrameRenderState = (MoreFrameVariantItemFrameRenderState) itemFrameRenderState;
        super.extractRenderState(itemFrame, moreFrameVariantItemFrameRenderState, f);
        moreFrameVariantItemFrameRenderState.itemFrameVariant = ((IItemFrame) itemFrame).mframev$getIFWoodVariant();
    }
}
