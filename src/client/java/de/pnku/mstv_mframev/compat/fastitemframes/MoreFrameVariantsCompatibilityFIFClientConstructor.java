package de.pnku.mstv_mframev.compat.fastitemframes;

import de.pnku.mstv_mframev.item.MoreFrameVariantItems;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.core.v1.context.ColorProvidersContext;
import fuzs.puzzleslib.api.client.core.v1.context.ItemModelPropertiesContext;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.ItemLike;

import static fuzs.fastitemframes.client.FastItemFramesClient.DYED_MODEL_PROPERTY;

public class MoreFrameVariantsCompatibilityFIFClientConstructor implements ClientModConstructor {
    public void onRegisterItemModelProperties(ItemModelPropertiesContext context) {
        context.registerItemProperty(DYED_MODEL_PROPERTY, (itemStack, clientLevel, livingEntity, i) -> itemStack.has(DataComponents.DYED_COLOR) ? 1.0F : 0.0F, MoreFrameVariantItems.more_all_item_frames.toArray(new ItemLike[0]));
    }

    public void onRegisterItemColorProviders(ColorProvidersContext<Item, ItemColor> context) {
        context.registerColorProvider((itemStack, tintIndex) -> tintIndex == 0 ? DyedItemColor.getOrDefault(itemStack, -1) : -1, MoreFrameVariantItems.more_all_item_frames.toArray(new Item[0]));
    }
}
