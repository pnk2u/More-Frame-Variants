package de.pnku.mstv_mframev.mixin.client.renderer;

import de.pnku.mstv_mframev.MoreFrameVariants;
import de.pnku.mstv_mframev.compat.fastitemframes.MoreFrameVariantsCompatibilityFIF;
import de.pnku.mstv_mframev.util.IItemFrame;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemFrameRenderer;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static de.pnku.mstv_mframev.MoreFrameVariants.MOD_ID;
import static de.pnku.mstv_mframev.MoreFrameVariants.isFifLoaded;

@Environment(value = EnvType.CLIENT)
@Mixin(ItemFrameRenderer.class)
public abstract class ItemFrameRendererMixin<T extends ItemFrame> extends EntityRenderer<T> {

    protected ItemFrameRendererMixin(EntityRendererProvider.Context context) {super(context);}

    @Inject(method="getFrameModelResourceLoc", at = @At("HEAD"), cancellable = true)
    private void injectedGetFrameModelResourceLoc(ItemFrame itemFrame, ItemStack item, CallbackInfoReturnable<ModelResourceLocation> cir) {
        String woodVariant = ((IItemFrame) itemFrame).mframev$getIFWoodVariant();
        String namespace = MOD_ID;
        if (isFifLoaded) {
            if (MoreFrameVariantsCompatibilityFIF.getCompatFifDyedEntityColor(itemFrame).isPresent()) {
                namespace = "fastitemframes";
            }
        }
        if (!woodVariant.equals("birch") || namespace.equals("fastitemframes")) {
            boolean isGlow = itemFrame.getType() == EntityType.GLOW_ITEM_FRAME;
            String mapVariantBl = (item.is(Items.FILLED_MAP)) ? "map=true" : "map=false";
            String frameBaseName = (isGlow) ? "_glow_item_frame" : "_item_frame";
            ModelResourceLocation modelResourceLocation = new ModelResourceLocation(new ResourceLocation(namespace, woodVariant + frameBaseName), mapVariantBl);
            cir.setReturnValue(modelResourceLocation);
        }
    }
}
