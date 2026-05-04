package de.pnku.mstv_mframev.compat.fastitemframes;

import fuzs.fastitemframes.common.client.handler.ClientEventHandler;
import fuzs.fastitemframes.common.init.ModRegistry;
import fuzs.fastitemframes.common.world.level.block.ItemFrameBlock;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.common.api.client.renderer.v1.RenderStateExtraData;
import net.minecraft.client.renderer.entity.state.ItemFrameRenderState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import static de.pnku.mstv_mframev.compat.fastitemframes.MoreFrameVariantsCompatibilityFIF.WOOD_TYPE;
import static de.pnku.mstv_mframev.compat.fastitemframes.MoreFrameVariantsCompatibilityFIF.WoodTypeProperty;

public class MoreFrameVariantsCompatibilityFIFClientConstructor implements ClientModConstructor {

    public static BlockState getCompatFifBlockState(boolean isGlow, boolean isMap, boolean isDyed, String woodVariant) {
        Block block = isGlow ? ModRegistry.GLOW_ITEM_FRAME_BLOCK.value() : ModRegistry.ITEM_FRAME_BLOCK.value();
        return block.defaultBlockState().setValue(ItemFrameBlock.MAP, isMap).setValue(ItemFrameBlock.DYED, isDyed).setValue(WOOD_TYPE, WoodTypeProperty.getByName(woodVariant));
    }

}

