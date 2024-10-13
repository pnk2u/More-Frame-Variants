package de.pnku.mstv_mframev.datagen;

import de.pnku.mstv_mframev.item.MoreFrameVariantItem;
import de.pnku.mstv_mframev.item.MoreFrameVariantItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.world.item.Item;
import org.apache.commons.text.WordUtils;

public class MoreFrameVariantLangGenerator extends FabricLanguageProvider {
    public MoreFrameVariantLangGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        for (Item paintingVariant : MoreFrameVariantItems.more_paintings) {
            String paintingName = WordUtils.capitalizeFully(((MoreFrameVariantItem) paintingVariant).mframevWoodType + " Painting");
            translationBuilder.add(paintingVariant, paintingName);
        }
        for (Item itemFrameVariant : MoreFrameVariantItems.more_item_frames) {
            String itemFrameName = WordUtils.capitalizeFully(((MoreFrameVariantItem) itemFrameVariant).mframevWoodType + " Item Frame");
            translationBuilder.add(itemFrameVariant, itemFrameName);
        }
        for (Item glowItemFrameVariant : MoreFrameVariantItems.more_glow_item_frames) {
            String glowItemFrameName = WordUtils.capitalizeFully(((MoreFrameVariantItem) glowItemFrameVariant).mframevWoodType + " Glow Item Frame");
            translationBuilder.add(glowItemFrameVariant, glowItemFrameName);
        }
    }
}
