package de.pnku.mstv_mframev.renderer.renderstates;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.state.ItemFrameRenderState;

@Environment(EnvType.CLIENT)
public class MoreFrameVariantItemFrameRenderState extends ItemFrameRenderState {
    public String itemFrameVariant;
}
