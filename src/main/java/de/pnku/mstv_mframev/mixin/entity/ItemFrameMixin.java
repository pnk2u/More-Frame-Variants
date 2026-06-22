package de.pnku.mstv_mframev.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import de.pnku.mstv_mframev.util.IItemFrame;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static de.pnku.mstv_mframev.item.MoreFrameVariantItem.stackFromIFWoodVariant;

@Mixin(ItemFrame.class)
public abstract class ItemFrameMixin extends HangingEntity implements IItemFrame {

    @Unique
    private static final EntityDataAccessor<String> DATA_ID_TYPE;

    protected ItemFrameMixin(EntityType<? extends HangingEntity> entityType, Level level) {
        super(entityType, level);
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
    protected void injectedAddAdditionalSaveData(ValueOutput valueOutput, CallbackInfo ci) {
        valueOutput.putString("Type", this.mframev$getIFWoodVariant());
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    protected void injectedReadAdditionalSaveData(ValueInput valueInput, CallbackInfo ci) {
        this.mframev$setIFWoodVariant(valueInput.getStringOr("Type", "birch"));
    }

    @WrapOperation(method = "dropItem(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/Entity;Z)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/decoration/ItemFrame;getFrameItemStack()Lnet/minecraft/world/item/ItemStack;"))
    protected ItemStack redirectedGetFrameItemStack(ItemFrame itemFrame, Operation<ItemStack> original) {
        boolean isGlow = itemFrame.getType().equals(EntityTypes.GLOW_ITEM_FRAME);
        String woodVariant = ((IItemFrame) itemFrame).mframev$getIFWoodVariant();
        return stackFromIFWoodVariant(woodVariant, isGlow, original.call(itemFrame));
    }

    @ModifyReturnValue(method = "getPickResult", at = @At("RETURN"))
    protected ItemStack injectedGetPickResult(ItemStack original) {
        boolean isGlow = this.getType().equals(EntityTypes.GLOW_ITEM_FRAME);
        String woodVariant = ((IItemFrame) this).mframev$getIFWoodVariant();
        if (woodVariant != null) {
            return stackFromIFWoodVariant(woodVariant, isGlow, original);
        }
        return original;
    }

    static {
        DATA_ID_TYPE = SynchedEntityData.defineId(ItemFrameMixin.class, EntityDataSerializers.STRING);
    }
}
