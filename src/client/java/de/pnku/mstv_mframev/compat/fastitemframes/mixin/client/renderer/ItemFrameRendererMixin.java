package de.pnku.mstv_mframev.compat.fastitemframes.mixin.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import de.pnku.mstv_mframev.MoreFrameVariants;
import de.pnku.mstv_mframev.util.IItemFrame;
import fuzs.fastitemframes.capability.ItemFrameColorCapability;
import fuzs.fastitemframes.client.renderer.blockentity.ItemFrameBlockRenderer;
import fuzs.fastitemframes.init.ModRegistry;
import fuzs.puzzleslib.api.client.core.v1.ClientAbstractions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemFrameRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.OptionalInt;

import static de.pnku.mstv_mframev.MoreFrameVariants.MOD_ID;
import static de.pnku.mstv_mframev.compat.fastitemframes.MoreFrameVariantsCompatibilityFIF.getCompatFifDyedEntityColor;

@Mixin(ItemFrameRenderer.class)
public abstract class ItemFrameRendererMixin<T extends ItemFrame> extends EntityRenderer<T> {

    @Shadow @Final private BlockRenderDispatcher blockRenderer;
    @Shadow protected abstract ModelResourceLocation getFrameModelResourceLoc(T entity, ItemStack item);

    protected ItemFrameRendererMixin(EntityRendererProvider.Context context) {
        super(context);
    }

    @ModifyVariable(
            method = {"render"},
            at = @At(value = "STORE"),
            ordinal = 0
    )
    public boolean render(boolean isInvisible, T entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if (!isInvisible) {
            OptionalInt color = getCompatFifDyedEntityColor(entity);
            if (color.isPresent()) {
                ItemStack itemStack = entity.getItem();
                ResourceLocation resourceLocation = ResourceLocation.tryParse((this.getFrameModelResourceLoc(entity, itemStack).toString()).replace(":", ":block/").replace("birch_", "").replace("#map=true", "_map").replace("#map=false", ""));
                poseStack.pushPose();
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                float red = (float) FastColor.ARGB32.red(color.getAsInt()) / 255.0F;
                float green = (float) FastColor.ARGB32.green(color.getAsInt()) / 255.0F;
                float blue = (float) FastColor.ARGB32.blue(color.getAsInt()) / 255.0F;
                this.blockRenderer.getModelRenderer().renderModel(poseStack.last(), buffer.getBuffer(Sheets.solidBlockSheet()), (BlockState)null, ClientAbstractions.INSTANCE.getBakedModel(resourceLocation), red, green, blue, packedLight, OverlayTexture.NO_OVERLAY);
                poseStack.popPose();
                if (!itemStack.isEmpty()) {
                    poseStack.translate(0.0F, 0.0F, -0.0625F);
                }
                return true;
            }
        }

        return isInvisible;
    }
}
