package de.pnku.mstv_mframev.compat.fastitemframes.mixin;

import de.pnku.mstv_mframev.compat.fastitemframes.util.ICompatFIF;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemFrame.class)
public abstract class ItemFrameMixin extends HangingEntity implements ICompatFIF {

    @Unique
    private static final EntityDataAccessor<Integer> DATA_ID_IF_COLOR;

    protected ItemFrameMixin(EntityType<? extends HangingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    protected void defineSynchedData(CallbackInfo ci) {
        this.entityData.define(DATA_ID_IF_COLOR, 10511680);
    }

    @Unique
    public void mframev$setCompatFIFColor(Integer color) {
        this.entityData.set(DATA_ID_IF_COLOR, color);
    }

    @Unique
    public Integer mframev$getCompatFIFColor() {
        return this.entityData.get(DATA_ID_IF_COLOR);
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    protected void injectedAddAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        compound.putInt("IF_Color", this.mframev$getCompatFIFColor());
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    protected void injectedReadAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        if (compound.contains("IF_Color", 3)) {
            this.mframev$setCompatFIFColor(compound.getInt("IF_Color"));
        }
    }

    static {
        DATA_ID_IF_COLOR = SynchedEntityData.defineId(ItemFrameMixin.class, EntityDataSerializers.INT);
    }
}
