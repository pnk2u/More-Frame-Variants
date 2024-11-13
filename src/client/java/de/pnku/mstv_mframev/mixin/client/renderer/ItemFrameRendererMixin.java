package de.pnku.mstv_mframev.mixin.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import de.pnku.mstv_mframev.renderer.renderstates.MoreFrameVariantItemFrameRenderState;
import de.pnku.mstv_mframev.util.IItemFrame;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MapRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemFrameRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.state.ItemFrameRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.saveddata.maps.MapId;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
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
    private ItemRenderer itemRenderer;
    @Shadow @Final
    private MapRenderer mapRenderer;


    @Inject(method = "createRenderState()Lnet/minecraft/client/renderer/entity/state/ItemFrameRenderState;", at = @At("HEAD"), cancellable = true)
    public void injectedCreateRenderState(CallbackInfoReturnable<MoreFrameVariantItemFrameRenderState> cir) {
        cir.setReturnValue(new MoreFrameVariantItemFrameRenderState());
    }

    @Inject(method = "render(Lnet/minecraft/client/renderer/entity/state/ItemFrameRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "HEAD"), cancellable = true)
    public void injectedRender(ItemFrameRenderState itemFrameRenderState, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci){
        MoreFrameVariantItemFrameRenderState moreFrameVariantItemFrameRenderState = (MoreFrameVariantItemFrameRenderState) itemFrameRenderState;
        String itemFrameVariant = moreFrameVariantItemFrameRenderState.itemFrameVariant;
        if (!itemFrameVariant.isEmpty() && !itemFrameVariant.equals("birch")){
            super.render(moreFrameVariantItemFrameRenderState, poseStack, multiBufferSource, i);
            poseStack.pushPose();
            Direction direction = itemFrameRenderState.direction;
            Vec3 vec3 = this.getRenderOffset(moreFrameVariantItemFrameRenderState);
            poseStack.translate(-vec3.x(), -vec3.y(), -vec3.z());
            double d = 0.46875;
            poseStack.translate((double)direction.getStepX() * 0.46875, (double)direction.getStepY() * 0.46875, (double)direction.getStepZ() * 0.46875);
            float f;
            float g;
            if (direction.getAxis().isHorizontal()) {
                f = 0.0F;
                g = 180.0F - direction.toYRot();
            } else {
                f = (float)(-90 * direction.getAxisDirection().getStep());
                g = 180.0F;
            }

            poseStack.mulPose(Axis.XP.rotationDegrees(f));
            poseStack.mulPose(Axis.YP.rotationDegrees(g));
            ItemStack itemStack = itemFrameRenderState.itemStack;
            if (!itemFrameRenderState.isInvisible) {
                ModelManager modelManager = this.blockRenderer.getBlockModelShaper().getModelManager();
                ModelResourceLocation modelResourceLocation = this.mframev$GetFrameModelResourceLoc(moreFrameVariantItemFrameRenderState, itemStack);
                poseStack.pushPose();
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.blockRenderer.getModelRenderer().renderModel(poseStack.last(), multiBufferSource.getBuffer(RenderType.entitySolidZOffsetForward(TextureAtlas.LOCATION_BLOCKS)), (BlockState)null, modelManager.getModel(modelResourceLocation), 1.0F, 1.0F, 1.0F, i, OverlayTexture.NO_OVERLAY);
                poseStack.popPose();
            }

            if (!itemStack.isEmpty()) {
                MapId mapId = itemFrameRenderState.mapId;
                if (itemFrameRenderState.isInvisible) {
                    poseStack.translate(0.0F, 0.0F, 0.5F);
                } else {
                    poseStack.translate(0.0F, 0.0F, 0.4375F);
                }

                int j = mapId != null ? itemFrameRenderState.rotation % 4 * 2 : itemFrameRenderState.rotation;
                poseStack.mulPose(Axis.ZP.rotationDegrees((float)j * 360.0F / 8.0F));
                if (mapId != null) {
                    poseStack.mulPose(Axis.ZP.rotationDegrees(180.0F));
                    float h = 0.0078125F;
                    poseStack.scale(0.0078125F, 0.0078125F, 0.0078125F);
                    poseStack.translate(-64.0F, -64.0F, 0.0F);
                    poseStack.translate(0.0F, 0.0F, -1.0F);
                    int k = this.getLightVal(itemFrameRenderState.isGlowFrame, 15728850, i);
                    this.mapRenderer.render(itemFrameRenderState.mapRenderState, poseStack, multiBufferSource, true, k);
                } else if (itemFrameRenderState.itemModel != null) {
                    int l = this.getLightVal(itemFrameRenderState.isGlowFrame, 15728880, i);
                    poseStack.scale(0.5F, 0.5F, 0.5F);
                    this.itemRenderer.render(itemStack, ItemDisplayContext.FIXED, false, poseStack, multiBufferSource, l, OverlayTexture.NO_OVERLAY, itemFrameRenderState.itemModel);
                }
            }

            poseStack.popPose();
            ci.cancel();
        }
    }

    @Shadow
    private int getLightVal(boolean bl, int i, int j) {
        return bl ? i : j;
    }

    @Unique
    private ModelResourceLocation mframev$GetFrameModelResourceLoc(MoreFrameVariantItemFrameRenderState moreFrameVariantItemFrameRenderState, ItemStack itemStack) {
        String woodVariant = moreFrameVariantItemFrameRenderState.itemFrameVariant;
            boolean isGlow = moreFrameVariantItemFrameRenderState.isGlowFrame;
            String mapVariantBl = (itemStack.is(Items.FILLED_MAP)) ? "map=true" : "map=false";
            String frameBaseName = (isGlow) ? "_glow_item_frame" : "_item_frame";
            return new ModelResourceLocation(asId(woodVariant + frameBaseName), mapVariantBl);
            //LOGGER.info(modelResourceLocation.toString());
    }

    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/decoration/ItemFrame;Lnet/minecraft/client/renderer/entity/state/ItemFrameRenderState;F)V", at = @At("HEAD"))
    public void injectedExtractRenderState(ItemFrame itemFrame, ItemFrameRenderState itemFrameRenderState, float f, CallbackInfo ci){
        MoreFrameVariantItemFrameRenderState moreFrameVariantItemFrameRenderState = (MoreFrameVariantItemFrameRenderState) itemFrameRenderState;
        super.extractRenderState(itemFrame, moreFrameVariantItemFrameRenderState, f);
        moreFrameVariantItemFrameRenderState.itemFrameVariant = ((IItemFrame) itemFrame).mframev$getIFWoodVariant();
    }

}
