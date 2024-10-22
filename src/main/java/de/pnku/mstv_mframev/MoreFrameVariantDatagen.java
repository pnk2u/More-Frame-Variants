package de.pnku.mstv_mframev;

import de.pnku.mstv_mframev.datagen.MoreFrameVariantLangGenerator;
import de.pnku.mstv_mframev.datagen.MoreFrameVariantModelGenerator;
//import de.pnku.mstv_mframev.datagen.MoreFrameVariantRecipeGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;


public class MoreFrameVariantDatagen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();

		//pack.addProvider(MoreFrameVariantLangGenerator::new);
        //pack.addProvider(MoreFrameVariantModelGenerator::new);
        //pack.addProvider(MoreFrameVariantRecipeGenerator::new);
    }
}
