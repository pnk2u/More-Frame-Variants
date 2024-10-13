package de.pnku.mstv_mframev;

import de.pnku.mstv_mframev.item.MoreFrameVariantItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MoreFrameVariants implements ModInitializer {

	public static final String MOD_ID = "mstv-mframev";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	
	@Override
	public void onInitialize() {
		MoreFrameVariantItems.registerPaintingItems();
	}

	public static ResourceLocation asId(String path) {
		return new ResourceLocation(MOD_ID, path);
	}

}
