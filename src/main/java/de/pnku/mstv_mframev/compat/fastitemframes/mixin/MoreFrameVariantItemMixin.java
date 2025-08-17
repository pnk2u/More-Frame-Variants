package de.pnku.mstv_mframev.compat.fastitemframes.mixin;

import de.pnku.mstv_mframev.item.MoreFrameVariantItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MoreFrameVariantItem.class)
public class MoreFrameVariantItemMixin  implements DyeableLeatherItem {
    public MoreFrameVariantItemMixin(EntityType<? extends HangingEntity> type, Item.Properties properties) {
        super();
    }
    // Empty Mixin class to implement DyeableLeatherItem to achieve compatibility with Fast Item Frames
}
