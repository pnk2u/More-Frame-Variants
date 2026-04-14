package de.pnku.mstv_mframev.compat.fastitemframes.mixin;

import de.pnku.mstv_mframev.util.IItemFrame;
import fuzs.fastitemframes.handler.ItemFrameHandler;
import fuzs.fastitemframes.init.ModRegistry;
import fuzs.fastitemframes.world.level.block.ItemFrameBlock;
import fuzs.fastitemframes.world.level.block.entity.ItemFrameBlockEntity;
import fuzs.puzzleslib.api.event.v1.core.EventResult;
import net.minecraft.core.BlockPos;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static de.pnku.mstv_mframev.compat.fastitemframes.MoreFrameVariantsCompatibilityFIF.WOOD_TYPE;
import static de.pnku.mstv_mframev.compat.fastitemframes.MoreFrameVariantsCompatibilityFIF.WoodType;

@Mixin(ItemFrameHandler.class)
public class ItemFrameHandlerMixin {

    @Shadow private static BlockState getItemFrameStateForPlacement(ServerLevel serverLevel, Block block, BlockPos blockPos, ItemFrame itemFrame) {return null;}
    @Shadow private static void setItemFrameBlock(ServerLevel serverLevel, BlockPos blockPos, BlockState blockState, ItemFrame itemFrame) {}

    @Inject(method = "onEntityLoad", at = @At(value = "HEAD"), remap = false)
    private static void injectedOnEntityLoad(Entity entity, ServerLevel serverLevel, boolean isFreshEntity, CallbackInfoReturnable<EventResult> cir) {
        if (entity.getType().is(ModRegistry.ITEM_FRAMES_ENTITY_TYPE_TAG) && entity instanceof ItemFrame itemFrame) {
            serverLevel.getServer().schedule(new TickTask(serverLevel.getServer().getTickCount(), () -> {
                Block block = ItemFrameBlock.BY_ITEM.get(itemFrame.getFrameItemStack().getItem());
                BlockPos blockPos = entity.blockPosition();
                if (block != null && serverLevel.hasChunkAt(blockPos) && (serverLevel.isEmptyBlock(blockPos)
                        || serverLevel.getBlockState(blockPos).is(Blocks.WATER))) {
                    BlockState blockState = getItemFrameStateForPlacement(serverLevel, block, blockPos, itemFrame);
                    if (blockState != null) {
                        setItemFrameBlock(serverLevel, blockPos, blockState.setValue(ItemFrameBlock.FACING, itemFrame.getDirection()).setValue(WOOD_TYPE, WoodType.getEnumByName(((IItemFrame) itemFrame).mframev$getIFWoodVariant())), itemFrame);
                        itemFrame.kill(serverLevel);
                    }
                }
            }));
        }
    }
}
