package de.pnku.mstv_mframev.compat.fastitemframes;

import de.pnku.mstv_mframev.item.MoreFrameVariantItems;
import fuzs.fastitemframes.init.ModRegistry;
import fuzs.fastitemframes.world.level.block.entity.ItemFrameBlockEntity;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.core.v1.context.ColorProvidersContext;
import fuzs.puzzleslib.api.client.core.v1.context.ItemModelPropertiesContext;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import static fuzs.fastitemframes.client.FastItemFramesClient.DYED_MODEL_PROPERTY;

public class MoreFrameVariantsCompatibilityFIFClientConstructor implements ClientModConstructor {
    public void onRegisterItemModelProperties(ItemModelPropertiesContext context) {
        context.registerItemProperty(DYED_MODEL_PROPERTY, (itemStack, clientLevel, livingEntity, i) -> itemStack.has(DataComponents.DYED_COLOR) ? 1.0F : 0.0F, MoreFrameVariantItems.more_all_item_frames.toArray(new ItemLike[0]));
    }

    public void onRegisterBlockColorProviders(ColorProvidersContext<Block, BlockColor> context) {
        context.registerColorProvider((BlockColor)(BlockState blockState, @Nullable BlockAndTintGetter blockAndTintGetter, @Nullable BlockPos blockPos, int tintIndex) -> {
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

    public void onRegisterItemColorProviders(ColorProvidersContext<Item, ItemColor> context) {
        context.registerColorProvider((ItemColor) (itemStack, tintIndex) -> tintIndex == 0 ? DyedItemColor.getOrDefault(itemStack, -1) : -1, MoreFrameVariantItems.more_all_item_frames.toArray(new Item[0]));
    }
}
