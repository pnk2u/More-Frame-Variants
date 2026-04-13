package de.pnku.mstv_mframev;

/*
import de.pnku.mstv_mframev.compat.fastitemframes.MoreFrameVariantsCompatibilityFIFClientConstructor;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
These imports are commented out as FastItemFrames has at this time not been updated to 26.1 yet. */
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.util.List;
import java.util.Map;

@Environment(value = EnvType.CLIENT)
public class MoreFrameVariantsClient implements ClientModInitializer {

    // Any painting in the namespaces listed here
    public static final List<String> compatible_painting_namespaces = List.of(
            "minecraft",
            "darkpaintings",
            "mcwpaintings",
            "nemos-paintings"
    );
    public static final Map<String, String> excluded_paintings = Map.of(
            "mcwpaintings:lumberjack", "birch"
    );
    
    @Override
    public void onInitializeClient() {
        if (MoreFrameVariants.isFifLoaded) {
            /*
            ClientModConstructor.construct("mstv-mframev", MoreFrameVariantsCompatibilityFIFClientConstructor::new);
            This line is commented out as FastItemFrames has at this time not been updated to 26.1 yet. */
        }
    }
    
}
