package de.pnku.mstv_mframev.compat.fastitemframes.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import de.pnku.mstv_mframev.compat.fastitemframes.MoreFrameVariantsCompatibilityFIF;
import de.pnku.mstv_mframev.renderer.renderstates.MoreFrameVariantItemFrameRenderState;
import fuzs.fastitemframes.common.client.handler.ClientEventHandler;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static de.pnku.mstv_mframev.compat.fastitemframes.MoreFrameVariantsCompatibilityFIF.WOOD_TYPE;

@Mixin(ClientEventHandler.class)
public abstract class ClientEventHandlerMixin {
    @WrapOperation(method = "onExtractEntityRenderState", at = @At(value = "INVOKE", target = "Lfuzs/fastitemframes/common/client/renderer/blockentity/ItemFrameBlockRenderer;getItemFrameBlockState(ZZZ)Lnet/minecraft/world/level/block/state/BlockState;"))
    private static BlockState wrappedOnExtractEntityRenderStateGetItemFrameBlockState(boolean isGlowFrame, boolean isMapFrame, boolean isDyed, Operation<BlockState> original, Entity entity, EntityRenderState entityRenderState) {
        if (entityRenderState instanceof MoreFrameVariantItemFrameRenderState moreFrameVariantItemFrameRenderState) {
            return original.call(isGlowFrame, isMapFrame, isDyed).setValue(WOOD_TYPE, MoreFrameVariantsCompatibilityFIF.WoodType.getEnumByName(moreFrameVariantItemFrameRenderState.itemFrameVariant));
        } else {
            return original.call(isGlowFrame, isMapFrame, isDyed);
        }
    }
}
