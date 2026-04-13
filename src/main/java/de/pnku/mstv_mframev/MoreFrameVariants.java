package de.pnku.mstv_mframev;

/*
import de.pnku.mstv_mframev.compat.fastitemframes.MoreFrameVariantsCompatibilityFIF;
import de.pnku.mstv_mframev.compat.fastitemframes.MoreFrameVariantsCompatibilityFIFConstructor;
These imports are commented out as FastItemFrames has at this time not been updated to 26.1 yet. */
import de.pnku.mstv_mframev.item.MoreFrameVariantItems;
/*
import fuzs.puzzleslib.api.core.v1.ModConstructor;
This import is commented out as FastItemFrames has at this time not been updated to 26.1 yet. */
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MoreFrameVariants implements ModInitializer {

	public static final String MOD_ID = "mstv-mframev";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static Boolean isFifLoaded = false;

	
	@Override
	public void onInitialize() {
		MoreFrameVariantItems.registerFrameItems();
		isFifLoaded = FabricLoader.getInstance().isModLoaded("fastitemframes");
		if (isFifLoaded) {
			/*
			ModConstructor.construct("mstv-mframev", MoreFrameVariantsCompatibilityFIFConstructor::new);
			MoreFrameVariantsCompatibilityFIF.registerCompatFifDatapack();
			These lines are commented out as FastItemFrames has at this time not been updated to 26.1 yet. */
		}
	}

	public static Identifier asId(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

}
