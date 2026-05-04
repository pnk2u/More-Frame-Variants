package de.pnku.mstv_mframev.mixin.entity;

import de.pnku.mstv_mframev.util.IPainting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

import static de.pnku.mstv_mframev.item.MoreFrameVariantItems.more_paintings_by_wood_type;

@Mixin(Painting.class)
public abstract class PaintingMixin extends HangingEntity implements IPainting {

    @Unique
    private static final EntityDataAccessor<String> DATA_ID_TYPE;

    protected PaintingMixin(EntityType<? extends HangingEntity> entityType, Level level) {super(entityType, level);}

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    protected void defineSynchedData(CallbackInfo ci) {
        this.entityData.define(DATA_ID_TYPE, "default");
    }

    @Unique
    public void mframev$setPWoodVariant(String woodVariant) {
        this.entityData.set(DATA_ID_TYPE, woodVariant);
    }

    @Unique
    public String mframev$getPWoodVariant() {
        return this.entityData.get(DATA_ID_TYPE);
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    protected void injectedAddAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        compound.putString("Type", this.mframev$getPWoodVariant());
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    protected void injectedReadAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        if (compound.contains("Type", 8)) {
            this.mframev$setPWoodVariant(compound.getString("Type"));
        }
    }

    @Inject(method = "dropItem", at = @At("HEAD"), cancellable = true)
    private void injectedDropItem(Entity brokenEntity, CallbackInfo ci) {
        String woodVariant = this.mframev$getPWoodVariant();
        if (woodVariant != null) {
            ItemStack itemStack = stackFromPWoodVariant(woodVariant);
            if (this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                this.playSound(SoundEvents.PAINTING_BREAK, 1.0F, 1.0F);
                if (brokenEntity instanceof Player) {
                    Player player = (Player)brokenEntity;
                    if (player.getAbilities().instabuild) {
                        return;
                    }
                }

                this.spawnAtLocation(itemStack);
            }
        }
        ci.cancel();
    }

    @Inject(method = "getPickResult", at = @At("HEAD"), cancellable = true)
    public void injectedGetPickResult(CallbackInfoReturnable<ItemStack> cir) {
        String woodVariant = this.mframev$getPWoodVariant();
        if (woodVariant != null) {
            cir.setReturnValue(stackFromPWoodVariant(woodVariant));
        }
    }

    @Unique
    public ItemStack stackFromPWoodVariant(String woodVariant) {
        Item paintingItem = more_paintings_by_wood_type.get(woodVariant);
        return new ItemStack(Objects.requireNonNullElse(paintingItem, Items.PAINTING));
    }

    static {
        DATA_ID_TYPE = SynchedEntityData.defineId(PaintingMixin.class, EntityDataSerializers.STRING);
    }
}
