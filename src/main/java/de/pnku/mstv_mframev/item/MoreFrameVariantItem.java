package de.pnku.mstv_mframev.item;

import de.pnku.mstv_mframev.MoreFrameVariants;
import de.pnku.mstv_mframev.util.IItemFrame;
import de.pnku.mstv_mframev.util.IPainting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static de.pnku.mstv_mframev.MoreFrameVariants.*;

public class MoreFrameVariantItem extends HangingEntityItem {
    public final String mframevWoodType;
    private final EntityType<? extends HangingEntity> type;

    public MoreFrameVariantItem(String mframevWoodType, EntityType<? extends HangingEntity> type, Item.Properties properties) {
        super(type, properties.setId(ResourceKey.create(Registries.ITEM, MoreFrameVariants.asId(mframevWoodType + "_" + type.toShortString()))));
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
            HangingEntity hangingEntity;
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
                    return InteractionResult.SUCCESS;
                }

                hangingEntity = new GlowItemFrame(level, blockPos2, direction);
            }

            CustomData customData = (CustomData)itemStack.getOrDefault(DataComponents.ENTITY_DATA, CustomData.EMPTY);
            if (!customData.isEmpty()) {
                EntityType.updateCustomEntityTag(level, player, hangingEntity, customData);
            }

            if (hangingEntity.survives()) {
                if (!level.isClientSide) {
                    hangingEntity.playPlacementSound();
                    level.gameEvent(player, GameEvent.ENTITY_PLACE, hangingEntity.position());
                    level.addFreshEntity(hangingEntity);
                    if (hangingEntity.getType().equals(EntityType.PAINTING)){
                        assert hangingEntity instanceof IPainting;
                        ((IPainting) hangingEntity).mframev$setPWoodVariant(this.mframevWoodType);}
                    if (hangingEntity.getType().equals(EntityType.ITEM_FRAME)|| hangingEntity.getType().equals(EntityType.GLOW_ITEM_FRAME)){
                        assert hangingEntity instanceof IItemFrame;
                        ((IItemFrame) hangingEntity).mframev$setIFWoodVariant(this.mframevWoodType);}
                }

                itemStack.shrink(1);
                return InteractionResult.SUCCESS;
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
