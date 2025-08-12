package de.pnku.mstv_mframev.compat.fastitemframes;

import de.pnku.mstv_mframev.item.MoreFrameVariantItems;
import fuzs.fastitemframes.client.handler.ClientEventHandler;
import fuzs.fastitemframes.client.renderer.blockentity.ItemFrameBlockRenderer;
import fuzs.fastitemframes.init.ModRegistry;
import fuzs.fastitemframes.world.level.block.entity.ItemFrameBlockEntity;
import fuzs.puzzleslib.api.client.core.v1.ClientAbstractions;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.core.v1.context.BlockColorsContext;
import fuzs.puzzleslib.api.client.renderer.v1.RenderPropertyKey;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.renderer.entity.state.ItemFrameRenderState;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class MoreFrameVariantsCompatibilityFIFClientConstructor implements ClientModConstructor {

    public void onRegisterBlockColorProviders(BlockColorsContext context) {
        context.registerBlockColor((BlockColor)(BlockState blockState, @Nullable BlockAndTintGetter blockAndTintGetter, @Nullable BlockPos blockPos, int tintIndex) -> {
            if (blockAndTintGetter != null && blockPos != null) {
                BlockEntity patt0$temp = blockAndTintGetter.getBlockEntity(blockPos);
                if (patt0$temp instanceof ItemFrameBlockEntity) {
                    ItemFrameBlockEntity blockEntity = (ItemFrameBlockEntity)patt0$temp;
                    return blockEntity.getColor().orElse(-1);
                }
            }

            return -6265536;
        }, new Block[]{(Block) ModRegistry.ITEM_FRAME_BLOCK.value(), (Block)ModRegistry.GLOW_ITEM_FRAME_BLOCK.value()});
    }

    public static boolean getCompatFifIsDyedRenderState(ItemFrameRenderState renderState) {
        return RenderPropertyKey.containsRenderProperty(renderState, ClientEventHandler.COLOR_RENDER_PROPERTY_KEY);
    }

    public static int getCompatFifDyedColorRenderState(ItemFrameRenderState renderState) {
        return (Integer) RenderPropertyKey.getRenderProperty(renderState, ClientEventHandler.COLOR_RENDER_PROPERTY_KEY);
    }

    public static ResourceLocation getCompatFifBlockModelLoc(ModelResourceLocation modelResourceLocation) {
        return (ResourceLocation) ItemFrameBlockRenderer.ITEM_FRAME_BLOCK_MODELS.get(modelResourceLocation);
    }

    public static BakedModel getCompatFifBakedModel(ModelManager modelManager, ResourceLocation resourceLocation) {
        return ClientAbstractions.INSTANCE.getBakedModel(modelManager, resourceLocation);
    }

}
