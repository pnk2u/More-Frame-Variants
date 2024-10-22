package de.pnku.mstv_mframev.renderer.renderstates;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.state.PaintingRenderState;

@Environment(EnvType.CLIENT)
public class MoreFrameVariantPaintingRenderState extends PaintingRenderState {
    public String paintingFrameVariant;
}
