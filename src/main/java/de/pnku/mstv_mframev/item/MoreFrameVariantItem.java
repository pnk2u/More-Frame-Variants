package de.pnku.mstv_mframev.item;

import de.pnku.mstv_mframev.util.IItemFrame;
import de.pnku.mstv_mframev.util.IPainting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;


public class MoreFrameVariantItem extends HangingEntityItem {
    public final String mframevWoodType;
    private final EntityType<? extends HangingEntity> type;

    public MoreFrameVariantItem(String mframevWoodType, EntityType<? extends HangingEntity> type, Item.Properties properties) {
        super(type, properties);
        this.mframevWoodType = mframevWoodType;
        this.type = type;
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        BlockPos blockPos = context.getClickedPos();
        Direction direction = context.getClickedFace();
        BlockPos blockPos2 = blockPos.relative(direction);
        Player player = context.getPlayer();
        ItemStack itemStack = context.getItemInHand();
        if (player != null && !this.mayPlace(player, direction, itemStack, blockPos2)) {
            return InteractionResult.FAIL;
        } else {
            Level level = context.getLevel();
            Object hangingEntity;
            if (this.type == EntityType.PAINTING) {
                Optional<Painting> optional = Painting.create(level, blockPos2, direction);
                if (optional.isEmpty()) {
                    return InteractionResult.CONSUME;
                }

                hangingEntity = (HangingEntity)optional.get();
            } else if (this.type == EntityType.ITEM_FRAME) {
                hangingEntity = new ItemFrame(level, blockPos2, direction);
            } else {
                if (this.type != EntityType.GLOW_ITEM_FRAME) {
                    return InteractionResult.sidedSuccess(level.isClientSide);
                }

                hangingEntity = new GlowItemFrame(level, blockPos2, direction);
            }

            CompoundTag compoundTag = itemStack.getTag();
            if (compoundTag != null) {
                EntityType.updateCustomEntityTag(level, player, (Entity)hangingEntity, compoundTag);
            }

            if (((HangingEntity)hangingEntity).survives()) {
                if (!level.isClientSide) {
                    ((HangingEntity)hangingEntity).playPlacementSound();
                    level.gameEvent(player, GameEvent.ENTITY_PLACE, ((HangingEntity)hangingEntity).position());
                    level.addFreshEntity((Entity)hangingEntity);
                    if (((HangingEntity) hangingEntity).getType().equals(EntityType.PAINTING)){
                        ((IPainting) hangingEntity).mframev$setPWoodVariant(this.mframevWoodType);}
                    if (((HangingEntity) hangingEntity).getType().equals(EntityType.ITEM_FRAME)||((HangingEntity) hangingEntity).getType().equals(EntityType.GLOW_ITEM_FRAME)){
                        ((IItemFrame) hangingEntity).mframev$setIFWoodVariant(this.mframevWoodType);}
                }

                itemStack.shrink(1);
                return InteractionResult.sidedSuccess(level.isClientSide);
            } else {
                return InteractionResult.CONSUME;
            }
        }
    }

    @Override
    protected boolean mayPlace(Player player, Direction direction, ItemStack hangingEntityStack, BlockPos pos) {
        if (this.type == EntityType.ITEM_FRAME || this.type == EntityType.GLOW_ITEM_FRAME) {
            return !player.level().isOutsideBuildHeight(pos) && player.mayUseItemAt(pos, direction, hangingEntityStack);
        } else {
            return !direction.getAxis().isVertical() && player.mayUseItemAt(pos, direction, hangingEntityStack);
        }
    }
}