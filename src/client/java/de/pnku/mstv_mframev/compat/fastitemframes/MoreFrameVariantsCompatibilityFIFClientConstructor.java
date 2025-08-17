package de.pnku.mstv_mframev.compat.fastitemframes;

import de.pnku.mstv_mframev.item.MoreFrameVariantItems;
import fuzs.fastitemframes.client.renderer.blockentity.ItemFrameBlockRenderer;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.core.v1.context.AdditionalModelsContext;
import fuzs.puzzleslib.api.client.core.v1.context.ColorProvidersContext;
import fuzs.puzzleslib.api.client.core.v1.context.ItemModelPropertiesContext;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import static fuzs.fastitemframes.client.FastItemFramesClient.DYED_MODEL_PROPERTY;

public class MoreFrameVariantsCompatibilityFIFClientConstructor implements ClientModConstructor {
    public void onRegisterItemModelProperties(ItemModelPropertiesContext context) {
        context.registerItemProperty(DYED_MODEL_PROPERTY, (itemStack, clientLevel, livingEntity, i) -> ((DyeableLeatherItem)itemStack.getItem()).hasCustomColor(itemStack) ? 1.0F : 0.0F, MoreFrameVariantItems.more_all_item_frames.toArray(new ItemLike[0]));
    }

    public void onRegisterItemColorProviders(ColorProvidersContext<Item, ItemColor> context) {
        context.registerColorProvider((itemStack, tintIndex) -> tintIndex == 0 && ((DyeableLeatherItem)itemStack.getItem()).hasCustomColor(itemStack) ? ((DyeableLeatherItem)itemStack.getItem()).getColor(itemStack) : -1, MoreFrameVariantItems.more_all_item_frames.toArray(new Item[0]));
    }


    @Override
    public void onRegisterAdditionalModels(AdditionalModelsContext context) {
        String[] woodVariants = {"acacia", "bamboo", "cherry", "crimson", "dark_oak", "jungle", "mangrove", "oak", "spruce", "warped"};
        String[][] modelVariants = {{"glow_", ""}, {"glow_", "_map"}, {"", ""}, {"", "_map"}};
        for (String woodVariant : woodVariants) {
            for (String[] modelVariant : modelVariants) {
                String glowPrefix = modelVariant[0];
                String mapSuffix = modelVariant[1];
                ResourceLocation resLoc = new ResourceLocation("fastitemframes", "block/" + woodVariant + "_" + glowPrefix + "item_frame" + mapSuffix);
                context.registerAdditionalModel(resLoc);
            }
        }
    }
}
