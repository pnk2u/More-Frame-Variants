package de.pnku.mstv_mframev.compat.fastitemframes;

import fuzs.fastitemframes.client.handler.ClientEventHandler;
import fuzs.fastitemframes.init.ModRegistry;
import fuzs.fastitemframes.world.level.block.ItemFrameBlock;
import fuzs.fastitemframes.world.level.block.entity.ItemFrameBlockEntity;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.core.v1.context.BlockColorsContext;
import fuzs.puzzleslib.api.client.renderer.v1.RenderPropertyKey;
import net.minecraft.client.renderer.entity.state.ItemFrameRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import static de.pnku.mstv_mframev.compat.fastitemframes.MoreFrameVariantsCompatibilityFIF.*;

public class MoreFrameVariantsCompatibilityFIFClientConstructor implements ClientModConstructor {

    public void onRegisterBlockColorProviders(BlockColorsContext context) {
        context.registerBlockColor((BlockState blockState, @Nullable BlockAndTintGetter blockAndTintGetter, @Nullable BlockPos blockPos, int tintIndex) -> {
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
        return RenderPropertyKey.has(renderState, ClientEventHandler.COLOR_RENDER_PROPERTY_KEY);
    }

    public static int getCompatFifDyedColorRenderState(ItemFrameRenderState renderState) {
        return (Integer) RenderPropertyKey.getOrDefault(renderState, ClientEventHandler.COLOR_RENDER_PROPERTY_KEY, 9128489);
    }

    public static BlockState getCompatFifBlockState(boolean isGlow, boolean isMap, boolean isDyed, String woodVariant) {
        Block block = isGlow ? ModRegistry.GLOW_ITEM_FRAME_BLOCK.value() : ModRegistry.ITEM_FRAME_BLOCK.value();
        return block.defaultBlockState().setValue(ItemFrameBlock.MAP, isMap).setValue(ItemFrameBlock.DYED, isDyed).setValue(WOOD_TYPE, WoodType.getEnumByName(woodVariant));
    }

}
