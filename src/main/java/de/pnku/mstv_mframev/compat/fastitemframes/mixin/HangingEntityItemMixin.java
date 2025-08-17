package de.pnku.mstv_mframev.compat.fastitemframes.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import de.pnku.mstv_mframev.compat.fastitemframes.util.ICompatFIF;
import fuzs.fastitemframes.capability.ItemFrameColorCapability;
import fuzs.fastitemframes.init.ModRegistry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.HangingEntityItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(HangingEntityItem.class)
abstract class HangingEntityItemMixin extends Item {
    public HangingEntityItemMixin(Item.Properties properties) {
        super(properties);
    }

    @WrapOperation(method = "useOn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"))
    public boolean useOn(Level level, Entity entity, Operation<Boolean> operation, UseOnContext context) {
        if (entity.getType().is(ModRegistry.ITEM_FRAMES_ENTITY_TYPE_TAG) && entity instanceof ItemFrame itemFrame) {
            if (this instanceof DyeableLeatherItem item) {
                ItemStack itemInHand = context.getItemInHand();
                if (item.hasCustomColor(itemInHand)) {
                    ((ICompatFIF) itemFrame).mframev$setCompatFIFColor(item.getColor(itemInHand));
                }
            }
        }
        return operation.call(level, entity);
    }
}
