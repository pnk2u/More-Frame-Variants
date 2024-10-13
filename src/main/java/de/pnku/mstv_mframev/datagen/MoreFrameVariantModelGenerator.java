package de.pnku.mstv_mframev.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.world.item.Item;

import static de.pnku.mstv_mframev.item.MoreFrameVariantItems.*;

public class MoreFrameVariantModelGenerator extends FabricModelProvider {
    public MoreFrameVariantModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        for (Item frameVariant : more_frame_variants) {
            itemModelGenerator.generateFlatItem(frameVariant, ModelTemplates.FLAT_ITEM);
        }
    }
}
