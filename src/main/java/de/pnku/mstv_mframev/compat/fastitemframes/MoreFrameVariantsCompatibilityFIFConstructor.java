/* This class is commented out as FastItemFrames has at this time not been updated to 26.1 yet.
package de.pnku.mstv_mframev.compat.fastitemframes;

import de.pnku.mstv_mframev.item.MoreFrameVariantItems;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;

public class MoreFrameVariantsCompatibilityFIFConstructor implements ModConstructor {

    static InteractionResult dyedItemIteration(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, ItemStack itemStack) {
        if (!itemStack.is(ItemTags.DYEABLE)) {
            return InteractionResult.TRY_WITH_EMPTY_HAND;
        } else if (!itemStack.has(DataComponents.DYED_COLOR)) {
            return InteractionResult.TRY_WITH_EMPTY_HAND;
        } else {
            if (!level.isClientSide()) {
                ItemStack newItemStack = itemStack.copyWithCount(1);
                newItemStack.remove(DataComponents.DYED_COLOR);
                player.setItemInHand(interactionHand, ItemUtils.createFilledResult(itemStack, player, newItemStack, false));
                LayeredCauldronBlock.lowerFillLevel(blockState, level, blockPos);
            }
            return InteractionResult.SUCCESS;
        }
    }

    public void onCommonSetup() {
        for (Item moreFrameVariantItem : MoreFrameVariantItems.more_all_item_frames) {
            CauldronInteraction.WATER.map().put(moreFrameVariantItem, MoreFrameVariantsCompatibilityFIFConstructor::dyedItemIteration);
        }
    }
}

*/