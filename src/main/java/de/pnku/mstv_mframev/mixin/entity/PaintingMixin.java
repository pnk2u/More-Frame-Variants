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
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static de.pnku.mstv_mframev.MoreFrameVariants.LOGGER;

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
    private void injectedDropItem(ServerLevel serverLevel, @Nullable Entity brokenEntity, CallbackInfo ci) {
        if (((IPainting) this).mframev$getPWoodVariant() != null) {
            // debug
            LOGGER.info("Painting Variant found: {}", ((IPainting) this).mframev$getPWoodVariant());

            ItemStack itemStack;
            switch (((IPainting) this).mframev$getPWoodVariant()) {
                case "acacia" -> itemStack = new ItemStack(MoreFrameVariantItems.ACACIA_PAINTING);
                case "bamboo" -> itemStack = new ItemStack(MoreFrameVariantItems.BAMBOO_PAINTING);
                case "birch" -> itemStack = new ItemStack(MoreFrameVariantItems.BIRCH_PAINTING);
                case "cherry" -> itemStack = new ItemStack(MoreFrameVariantItems.CHERRY_PAINTING);
                case "crimson" -> itemStack = new ItemStack(MoreFrameVariantItems.CRIMSON_PAINTING);
                case "dark_oak" -> itemStack = new ItemStack(MoreFrameVariantItems.DARK_OAK_PAINTING);
                case "pale_oak" -> itemStack = new ItemStack(MoreFrameVariantItems.PALE_OAK_PAINTING);
                case "jungle" -> itemStack = new ItemStack(MoreFrameVariantItems.JUNGLE_PAINTING);
                case "mangrove" -> itemStack = new ItemStack(MoreFrameVariantItems.MANGROVE_PAINTING);
                case "oak" -> itemStack = new ItemStack(MoreFrameVariantItems.OAK_PAINTING);
                case "spruce" -> itemStack = new ItemStack(MoreFrameVariantItems.SPRUCE_PAINTING);
                case "warped" -> itemStack = new ItemStack(MoreFrameVariantItems.WARPED_PAINTING);
                case null, default -> itemStack = new ItemStack(Items.PAINTING);
            }
            if (serverLevel.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
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

    static {
        DATA_ID_TYPE = SynchedEntityData.defineId(PaintingMixin.class, EntityDataSerializers.STRING);
    }
}
