package de.pnku.mstv_mframev.mixin.client.renderer;

import de.pnku.mstv_mframev.util.IItemFrame;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemFrameRenderer;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static de.pnku.mstv_mframev.MoreFrameVariants.*;

@Environment(value = EnvType.CLIENT)
@Mixin(ItemFrameRenderer.class)
public abstract class ItemFrameRendererMixin extends EntityRenderer<ItemFrame> {


    protected ItemFrameRendererMixin(EntityRendererProvider.Context context) {super(context);}

    @Inject(method="getFrameModelResourceLoc", at = @At("HEAD"), cancellable = true)
    private void injectedGetFrameModelResourceLoc(ItemFrame itemFrame, ItemStack item, CallbackInfoReturnable<ModelResourceLocation> cir) {
        String woodVariant = ((IItemFrame) itemFrame).mframev$getIFWoodVariant();
        if (!woodVariant.equals("birch")) {
            boolean isGlow = itemFrame.getType() == EntityType.GLOW_ITEM_FRAME;
            String mapVariantBl = (item.is(Items.FILLED_MAP)) ? "map=true" : "map=false";
            String frameBaseName = (isGlow) ? "_glow_item_frame" : "_item_frame";
            ModelResourceLocation modelResourceLocation = new ModelResourceLocation(asId(woodVariant + frameBaseName), mapVariantBl);
            //LOGGER.info(modelResourceLocation.toString());
            cir.setReturnValue(modelResourceLocation);
        }
    }

}
