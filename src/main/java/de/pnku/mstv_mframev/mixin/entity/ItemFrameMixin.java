package de.pnku.mstv_mframev.mixin.entity;

import de.pnku.mstv_mframev.item.MoreFrameVariantItems;
import de.pnku.mstv_mframev.util.IItemFrame;
import de.pnku.mstv_mframev.util.IPainting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemFrame.class)
public abstract class ItemFrameMixin extends HangingEntity implements IItemFrame {

    @Unique
    private static final EntityDataAccessor<String> DATA_ID_TYPE;

    @Shadow
    private float dropChance;

    protected ItemFrameMixin(EntityType<? extends HangingEntity> entityType, Level level) {
        super(entityType, level);
    }
    public ItemFrameMixin(Level level, BlockPos pos, Direction facingDirection) {
        this(EntityType.ITEM_FRAME, level, pos, facingDirection);
    }

    public ItemFrameMixin(EntityType<? extends ItemFrame> entityType, Level level, BlockPos pos, Direction direction) {
        super(entityType, level, pos);
        this.dropChance = 1.0F;
        this.setDirection(direction);
    }


    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    protected void defineSynchedData(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(DATA_ID_TYPE, "birch");
    }

    @Unique
    public void mframev$setIFWoodVariant(String woodVariant) {
        this.entityData.set(DATA_ID_TYPE, woodVariant);
    }

    @Unique
    public String mframev$getIFWoodVariant() {
        return this.entityData.get(DATA_ID_TYPE);
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    protected void injectedAddAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        compound.putString("Type", this.mframev$getIFWoodVariant());
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    protected void injectedReadAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        if (compound.contains("Type", 8)) {
            this.mframev$setIFWoodVariant(compound.getString("Type"));
        }
    }

    @Redirect(method = "dropItem(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/Entity;Z)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/decoration/ItemFrame;getFrameItemStack()Lnet/minecraft/world/item/ItemStack;"))
    protected ItemStack redirectedGetFrameItemStack(ItemFrame itemFrame) {
        boolean isGlow = itemFrame.getType().equals(EntityType.GLOW_ITEM_FRAME);

        switch (((IItemFrame) itemFrame).mframev$getIFWoodVariant()) {
            case "birch" -> {return isGlow ? new ItemStack(Items.GLOW_ITEM_FRAME) : new ItemStack(Items.ITEM_FRAME);}
            case "acacia" -> {return isGlow ? new ItemStack(MoreFrameVariantItems.ACACIA_GLOW_ITEM_FRAME) : new ItemStack(MoreFrameVariantItems.ACACIA_ITEM_FRAME);}
            case "bamboo" -> {return isGlow ? new ItemStack(MoreFrameVariantItems.BAMBOO_GLOW_ITEM_FRAME) : new ItemStack(MoreFrameVariantItems.BAMBOO_ITEM_FRAME);}
            case "cherry" -> {return isGlow ? new ItemStack(MoreFrameVariantItems.CHERRY_GLOW_ITEM_FRAME) : new ItemStack(MoreFrameVariantItems.CHERRY_ITEM_FRAME);}
            case "crimson" -> {return isGlow ? new ItemStack(MoreFrameVariantItems.CRIMSON_GLOW_ITEM_FRAME) : new ItemStack(MoreFrameVariantItems.CRIMSON_ITEM_FRAME);}
            case "dark_oak" -> {return isGlow ? new ItemStack(MoreFrameVariantItems.DARK_OAK_GLOW_ITEM_FRAME) : new ItemStack(MoreFrameVariantItems.DARK_OAK_ITEM_FRAME);}
            case "pale_oak" -> {return isGlow ? new ItemStack(MoreFrameVariantItems.PALE_OAK_GLOW_ITEM_FRAME) : new ItemStack(MoreFrameVariantItems.PALE_OAK_ITEM_FRAME);}
            case "jungle" -> {return isGlow ? new ItemStack(MoreFrameVariantItems.JUNGLE_GLOW_ITEM_FRAME) : new ItemStack(MoreFrameVariantItems.JUNGLE_ITEM_FRAME);}
            case "mangrove" -> {return isGlow ? new ItemStack(MoreFrameVariantItems.MANGROVE_GLOW_ITEM_FRAME) : new ItemStack(MoreFrameVariantItems.MANGROVE_ITEM_FRAME);}
            case "spruce" -> {return isGlow ? new ItemStack(MoreFrameVariantItems.SPRUCE_GLOW_ITEM_FRAME) : new ItemStack(MoreFrameVariantItems.SPRUCE_ITEM_FRAME);}
            case "warped" -> {return isGlow ? new ItemStack(MoreFrameVariantItems.WARPED_GLOW_ITEM_FRAME) : new ItemStack(MoreFrameVariantItems.WARPED_ITEM_FRAME);}
            case null, default -> {return isGlow ? new ItemStack(Items.GLOW_ITEM_FRAME) : new ItemStack(Items.ITEM_FRAME);}
        }
    }

    static {
        DATA_ID_TYPE = SynchedEntityData.defineId(ItemFrameMixin.class, EntityDataSerializers.STRING);
    }
}
