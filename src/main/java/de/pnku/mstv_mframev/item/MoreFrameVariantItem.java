package de.pnku.mstv_mframev.item;

import de.pnku.mstv_mframev.MoreFrameVariants;
import de.pnku.mstv_mframev.compat.fastitemframes.MoreFrameVariantsCompatibilityFIF;
import de.pnku.mstv_mframev.util.IItemFrame;
import de.pnku.mstv_mframev.util.IPainting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Unique;

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
                    return InteractionResult.sidedSuccess(level.isClientSide);
                }

                hangingEntity = new GlowItemFrame(level, blockPos2, direction);
            }

            CustomData customData = (CustomData)itemStack.getOrDefault(DataComponents.ENTITY_DATA, CustomData.EMPTY);
            if (!customData.isEmpty()) {
                EntityType.updateCustomEntityTag(level, player, hangingEntity, customData);
            }

            if (hangingEntity.survives()) {
                if (!level.isClientSide()) {
                    hangingEntity.playPlacementSound();
                    level.gameEvent(player, GameEvent.ENTITY_PLACE, hangingEntity.position());
                    if ((hangingEntity.getType().equals(EntityType.ITEM_FRAME) || hangingEntity.getType().equals(EntityType.GLOW_ITEM_FRAME)) && itemStack.has(DataComponents.DYED_COLOR) && MoreFrameVariants.isFifLoaded) {
                        MoreFrameVariantsCompatibilityFIF.attachCompatFifDataToEntity(itemStack, hangingEntity);
                    }
                    level.addFreshEntity(hangingEntity);
                    if (hangingEntity.getType().equals(EntityType.PAINTING)){
                        assert hangingEntity instanceof IPainting;
                        ((IPainting) hangingEntity).mframev$setPWoodVariant(this.mframevWoodType);}
                    if (hangingEntity.getType().equals(EntityType.ITEM_FRAME) || hangingEntity.getType().equals(EntityType.GLOW_ITEM_FRAME)){
                        assert hangingEntity instanceof IItemFrame;
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

    @Unique
    public static ItemStack stackFromIFWoodVariant(String woodVariant, Boolean isGlow, ItemStack inputStack) {
        ItemStack outputStack;
        if (inputStack.equals(ItemStack.EMPTY)) {
            outputStack = isGlow ? new ItemStack(Items.GLOW_ITEM_FRAME) : new ItemStack(Items.ITEM_FRAME);
        } else { outputStack = inputStack;}
        switch (woodVariant) {
            case "birch" -> {return isGlow ? outputStack.transmuteCopy(Items.GLOW_ITEM_FRAME) : outputStack.transmuteCopy(Items.ITEM_FRAME);}
            case "acacia" -> {return isGlow ? outputStack.transmuteCopy(MoreFrameVariantItems.ACACIA_GLOW_ITEM_FRAME) : outputStack.transmuteCopy(MoreFrameVariantItems.ACACIA_ITEM_FRAME);}
            case "bamboo" -> {return isGlow ? outputStack.transmuteCopy(MoreFrameVariantItems.BAMBOO_GLOW_ITEM_FRAME) : outputStack.transmuteCopy(MoreFrameVariantItems.BAMBOO_ITEM_FRAME);}
            case "cherry" -> {return isGlow ? outputStack.transmuteCopy(MoreFrameVariantItems.CHERRY_GLOW_ITEM_FRAME) : outputStack.transmuteCopy(MoreFrameVariantItems.CHERRY_ITEM_FRAME);}
            case "crimson" -> {return isGlow ? outputStack.transmuteCopy(MoreFrameVariantItems.CRIMSON_GLOW_ITEM_FRAME) : outputStack.transmuteCopy(MoreFrameVariantItems.CRIMSON_ITEM_FRAME);}
            case "dark_oak" -> {return isGlow ? outputStack.transmuteCopy(MoreFrameVariantItems.DARK_OAK_GLOW_ITEM_FRAME) : outputStack.transmuteCopy(MoreFrameVariantItems.DARK_OAK_ITEM_FRAME);}
            case "jungle" -> {return isGlow ? outputStack.transmuteCopy(MoreFrameVariantItems.JUNGLE_GLOW_ITEM_FRAME) : outputStack.transmuteCopy(MoreFrameVariantItems.JUNGLE_ITEM_FRAME);}
            case "mangrove" -> {return isGlow ? outputStack.transmuteCopy(MoreFrameVariantItems.MANGROVE_GLOW_ITEM_FRAME) : outputStack.transmuteCopy(MoreFrameVariantItems.MANGROVE_ITEM_FRAME);}
            case "oak" -> {return isGlow ? outputStack.transmuteCopy(MoreFrameVariantItems.OAK_GLOW_ITEM_FRAME) : outputStack.transmuteCopy(MoreFrameVariantItems.OAK_ITEM_FRAME);}
            case "spruce" -> {return isGlow ? outputStack.transmuteCopy(MoreFrameVariantItems.SPRUCE_GLOW_ITEM_FRAME) : outputStack.transmuteCopy(MoreFrameVariantItems.SPRUCE_ITEM_FRAME);}
            case "warped" -> {return isGlow ? outputStack.transmuteCopy(MoreFrameVariantItems.WARPED_GLOW_ITEM_FRAME) : outputStack.transmuteCopy(MoreFrameVariantItems.WARPED_ITEM_FRAME);}
            case null, default -> {return isGlow ? outputStack.transmuteCopy(Items.GLOW_ITEM_FRAME) : outputStack.transmuteCopy(Items.ITEM_FRAME);}
        }
    }
}
