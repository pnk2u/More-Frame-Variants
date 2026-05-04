package de.pnku.mstv_mframev.mixin.entity;

import de.pnku.mstv_mframev.item.MoreFrameVariantItems;
import de.pnku.mstv_mframev.util.IPainting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.painting.Painting;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;
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
    protected void defineSynchedData(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(DATA_ID_TYPE, "default");
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
    protected void injectedAddAdditionalSaveData(ValueOutput valueOutput, CallbackInfo ci) {
        valueOutput.putString("Type", this.mframev$getPWoodVariant());
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    protected void injectedReadAdditionalSaveData(ValueInput valueInput, CallbackInfo ci) {
        this.mframev$setPWoodVariant(valueInput.getStringOr("Type", "default"));

    }

    @Inject(method = "dropItem", at = @At("HEAD"), cancellable = true)
    private void injectedDropItem(ServerLevel serverLevel, @Nullable Entity brokenEntity, CallbackInfo ci) {
        String woodVariant = this.mframev$getPWoodVariant();
        if (woodVariant != null) {
            ItemStack itemStack = stackFromPWoodVariant(woodVariant);
            if (serverLevel.getGameRules().get(GameRules.ENTITY_DROPS)) {
                this.playSound(SoundEvents.PAINTING_BREAK, 1.0F, 1.0F);
                if (brokenEntity instanceof Player) {
                    Player player = (Player)brokenEntity;
                    if (player.hasInfiniteMaterials()) {
                        return;
                    }
                }

                this.spawnAtLocation(serverLevel, itemStack);
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
