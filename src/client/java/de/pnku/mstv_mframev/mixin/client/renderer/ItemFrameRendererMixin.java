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
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemFrameRenderer;
import net.minecraft.client.renderer.entity.state.ItemFrameRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BlockStateDefinitions;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.saveddata.maps.MapId;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;

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

    @Inject(method = "render(Lnet/minecraft/client/renderer/entity/state/ItemFrameRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "HEAD"), cancellable = true)
    public void injectedRender(ItemFrameRenderState itemFrameRenderState, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci){
        MoreFrameVariantItemFrameRenderState moreFrameVariantItemFrameRenderState = (MoreFrameVariantItemFrameRenderState) itemFrameRenderState;
        String itemFrameVariant = moreFrameVariantItemFrameRenderState.itemFrameVariant;
        if (!itemFrameVariant.isEmpty() && !itemFrameVariant.equals("birch")) {
            super.render(moreFrameVariantItemFrameRenderState, poseStack, multiBufferSource, i);
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
                    ModelResourceLocation modelResourceLocation = mframev$GetFrameModelResourceLoc(moreFrameVariantItemFrameRenderState);
                    ResourceLocation resourceLocation = MoreFrameVariantsCompatibilityFIFClientConstructor.getCompatFifBlockModelLoc(modelResourceLocation);
                    ModelManager modelManager = this.blockRenderer.getBlockModelShaper().getModelManager();
                    BakedModel bakedModel;
                    if (resourceLocation != null) {
                        bakedModel = MoreFrameVariantsCompatibilityFIFClientConstructor.getCompatFifBakedModel(modelManager, resourceLocation);
                    } else {
                        bakedModel = modelManager.getModel(modelResourceLocation);
                    }

                    poseStack.pushPose();
                    poseStack.translate(-0.5F, -0.5F, -0.5F);
                    float red = ARGB.redFloat(color);
                    float green = ARGB.greenFloat(color);
                    float blue = ARGB.blueFloat(color);
                    this.blockRenderer.getModelRenderer().renderModel(poseStack.last(), multiBufferSource.getBuffer(RenderType.entitySolidZOffsetForward(TextureAtlas.LOCATION_BLOCKS)), (BlockState) null, bakedModel, red, green, blue, i, OverlayTexture.NO_OVERLAY);
                    poseStack.popPose();
                    if (!moreFrameVariantItemFrameRenderState.item.isEmpty()) {
                        poseStack.translate(0.0F, 0.0F, -0.0625F);
                    }
                    ci.cancel();
                }
            }
            //
            if (!itemFrameRenderState.isInvisible) {
                BlockState blockState = mframev$getItemFrameVariantFakeState(itemFrameVariant, moreFrameVariantItemFrameRenderState.isGlowFrame, moreFrameVariantItemFrameRenderState.mapId != null);
                BlockStateModel blockStateModel = this.blockRenderer.getBlockModel(blockState);
                poseStack.pushPose();
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                ModelBlockRenderer.renderModel(poseStack.last(), multiBufferSource.getBuffer(RenderType.entitySolidZOffsetForward(TextureAtlas.LOCATION_BLOCKS)), blockStateModel, 1.0F, 1.0F, 1.0F, i, OverlayTexture.NO_OVERLAY);
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
                int k = this.getLightCoords(itemFrameRenderState.isGlowFrame, 15728850, i);
                this.mapRenderer.render(itemFrameRenderState.mapRenderState, poseStack, multiBufferSource, true, k);
            } else if (!itemFrameRenderState.item.isEmpty()) {
                poseStack.mulPose(Axis.ZP.rotationDegrees((float)itemFrameRenderState.rotation * 360.0F / 8.0F));
                int j = this.getLightCoords(itemFrameRenderState.isGlowFrame, 15728880, i);
                poseStack.scale(0.5F, 0.5F, 0.5F);
                itemFrameRenderState.item.render(poseStack, multiBufferSource, j, OverlayTexture.NO_OVERLAY);
            }

            poseStack.popPose();
            ci.cancel();
        }
    }

    @Unique
    public BlockState mframev$getItemFrameVariantFakeState(String woodVariant, boolean isGlow, boolean hasMap) {
        Map<ResourceLocation, StateDefinition<Block, BlockState>> staticStateDefinitions = BlockStateDefinitions.STATIC_DEFINITIONS;
        StateDefinition<Block, BlockState> itemFrameVariantFakeDefinition = staticStateDefinitions.get(asId(woodVariant + (isGlow ? "_glow_" : "_") + "item_frame"));
        return (BlockState)((BlockState)(itemFrameVariantFakeDefinition).any()).setValue(BlockStateProperties.MAP, hasMap);
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
