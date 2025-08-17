package de.pnku.mstv_mframev.compat.fastitemframes;

import de.pnku.mstv_mframev.item.MoreFrameVariantItems;
import fuzs.fastitemframes.handler.ItemFrameHandler;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.item.Item;

public class MoreFrameVariantsCompatibilityFIFConstructor implements ModConstructor {
    public void onCommonSetup() {
        for (Item moreFrameVariantItem : MoreFrameVariantItems.more_all_item_frames) {
            CauldronInteraction.WATER.put(moreFrameVariantItem, ItemFrameHandler::itemFrameCauldronInteraction);
        }
    }
}
