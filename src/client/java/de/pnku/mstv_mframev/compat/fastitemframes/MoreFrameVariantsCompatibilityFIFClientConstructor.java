package de.pnku.mstv_mframev.compat.fastitemframes;

import fuzs.fastitemframes.client.handler.ClientEventHandler;
import fuzs.fastitemframes.init.ModRegistry;
import fuzs.fastitemframes.world.level.block.ItemFrameBlock;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.renderer.v1.RenderStateExtraData;
import net.minecraft.client.renderer.entity.state.ItemFrameRenderState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import static de.pnku.mstv_mframev.compat.fastitemframes.MoreFrameVariantsCompatibilityFIF.*;

public class MoreFrameVariantsCompatibilityFIFClientConstructor implements ClientModConstructor {

    public static boolean getCompatFifIsDyedRenderState(ItemFrameRenderState renderState) {
        return RenderStateExtraData.has(renderState, ClientEventHandler.COLOR_RENDER_PROPERTY_KEY);
    }

    public static int getCompatFifDyedColorRenderState(ItemFrameRenderState renderState) {
        return (Integer) RenderStateExtraData.getOrDefault(renderState, ClientEventHandler.COLOR_RENDER_PROPERTY_KEY, 9128489);
    }

    public static BlockState getCompatFifBlockState(boolean isGlow, boolean isMap, boolean isDyed, String woodVariant) {
        Block block = isGlow ? ModRegistry.GLOW_ITEM_FRAME_BLOCK.value() : ModRegistry.ITEM_FRAME_BLOCK.value();
        return block.defaultBlockState().setValue(ItemFrameBlock.MAP, isMap).setValue(ItemFrameBlock.DYED, isDyed).setValue(WOOD_TYPE, WoodType.getEnumByName(woodVariant));
    }

}
