package de.pnku.mstv_mframev.mixin.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import de.pnku.mstv_mframev.MoreFrameVariants;
import de.pnku.mstv_mframev.mixin.client.util.TextureAtlasHolderAccessor;
import de.pnku.mstv_mframev.util.IPainting;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PaintingRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.Painting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Environment(value = EnvType.CLIENT)
@Mixin(PaintingRenderer.class)
public abstract class PaintingRendererMixin extends EntityRenderer<Painting> {

    public PaintingRendererMixin(EntityRendererProvider.Context context) {
        super(context);
    }

    @ModifyVariable(method = "renderPainting", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private TextureAtlasSprite renderMframevPainting(
            TextureAtlasSprite paintingSprite,
            PoseStack poseStack,
            VertexConsumer consumer,
            Painting painting,
            int width,
            int height,
            TextureAtlasSprite backSprite
    ) {
        TextureAtlasHolderAccessor accessor = ((TextureAtlasHolderAccessor) Minecraft.getInstance().getPaintingTextures());
        String vanillaPaintingSpriteName = paintingSprite.contents().name().getPath();
        String paintingWoodVariant = ((IPainting) painting).mframev$getPWoodVariant();
        if (!paintingWoodVariant.equals("default")) {
            paintingSprite = accessor.callGetSprite(MoreFrameVariants.asId(vanillaPaintingSpriteName + "_" + paintingWoodVariant));
        }
        return paintingSprite;
    }
    @ModifyVariable(method = "renderPainting", at = @At("HEAD"), argsOnly = true, ordinal = 1)
    private TextureAtlasSprite renderMframevFrame(
            TextureAtlasSprite paintingSprite,
            PoseStack poseStack,
            VertexConsumer consumer,
            Painting painting,
            int width,
            int height,
            TextureAtlasSprite backSprite
    ) {
        TextureAtlasHolderAccessor accessor = ((TextureAtlasHolderAccessor) Minecraft.getInstance().getPaintingTextures());
        String paintingWoodVariant = ((IPainting) painting).mframev$getPWoodVariant();
        if (!paintingWoodVariant.equals("default")) {
            backSprite = accessor.callGetSprite(MoreFrameVariants.asId(paintingWoodVariant + "_planks"));
            return backSprite;
        } else {
            return Minecraft.getInstance().getPaintingTextures().getBackSprite();
        }
    }
}