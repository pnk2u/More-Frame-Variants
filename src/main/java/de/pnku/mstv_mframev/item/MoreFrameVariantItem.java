package de.pnku.mstv_mframev.item;

import de.pnku.mstv_mframev.MoreFrameVariants;
import de.pnku.mstv_mframev.compat.fastitemframes.MoreFrameVariantsCompatibilityFIF;
import de.pnku.mstv_mframev.compat.fastitemframes.util.ICompatFIF;
import de.pnku.mstv_mframev.util.IItemFrame;
import de.pnku.mstv_mframev.util.IPainting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.GlowItemFrame;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HangingEntityItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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

            CompoundTag compoundTag = itemStack.getTag();
            if (compoundTag != null) {
                EntityType.updateCustomEntityTag(level, player, (Entity)hangingEntity, compoundTag);
            }

            if (hangingEntity.survives()) {
                if (!level.isClientSide()) {
                    hangingEntity.playPlacementSound();
                    level.gameEvent(player, GameEvent.ENTITY_PLACE, hangingEntity.position());
                    level.addFreshEntity(hangingEntity);
                    if ((hangingEntity.getType().equals(EntityType.ITEM_FRAME) || hangingEntity.getType().equals(EntityType.GLOW_ITEM_FRAME)) && hangingEntity instanceof ItemFrame itemFrame && MoreFrameVariants.isFifLoaded) {
                        if (itemStack.getItem() instanceof DyeableLeatherItem item && item.hasCustomColor(itemStack)) {
                            MoreFrameVariantsCompatibilityFIF.attachCompatFifDataToEntity(item, itemStack, itemFrame);
                            ((ICompatFIF) itemFrame).mframev$setCompatFIFColor(item.getColor(itemStack));
                        }
                    }
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
        } else {
            switch (woodVariant) {
                case "birch" -> {
                    outputStack = isGlow ? new ItemStack(Items.GLOW_ITEM_FRAME) : new ItemStack(Items.ITEM_FRAME);
                }
                case "acacia" -> {
                    outputStack = isGlow ? new ItemStack(MoreFrameVariantItems.ACACIA_GLOW_ITEM_FRAME) : new ItemStack(MoreFrameVariantItems.ACACIA_ITEM_FRAME);
                }
                case "bamboo" -> {
                    outputStack = isGlow ? new ItemStack(MoreFrameVariantItems.BAMBOO_GLOW_ITEM_FRAME) : new ItemStack(MoreFrameVariantItems.BAMBOO_ITEM_FRAME);
                }
                case "cherry" -> {
                    outputStack = isGlow ? new ItemStack(MoreFrameVariantItems.CHERRY_GLOW_ITEM_FRAME) : new ItemStack(MoreFrameVariantItems.CHERRY_ITEM_FRAME);
                }
                case "crimson" -> {
                    outputStack = isGlow ? new ItemStack(MoreFrameVariantItems.CRIMSON_GLOW_ITEM_FRAME) : new ItemStack(MoreFrameVariantItems.CRIMSON_ITEM_FRAME);
                }
                case "dark_oak" -> {
                    outputStack = isGlow ? new ItemStack(MoreFrameVariantItems.DARK_OAK_GLOW_ITEM_FRAME) : new ItemStack(MoreFrameVariantItems.DARK_OAK_ITEM_FRAME);
                }
                case "jungle" -> {
                    outputStack = isGlow ? new ItemStack(MoreFrameVariantItems.JUNGLE_GLOW_ITEM_FRAME) : new ItemStack(MoreFrameVariantItems.JUNGLE_ITEM_FRAME);
                }
                case "mangrove" -> {
                    outputStack = isGlow ? new ItemStack(MoreFrameVariantItems.MANGROVE_GLOW_ITEM_FRAME) : new ItemStack(MoreFrameVariantItems.MANGROVE_ITEM_FRAME);
                }
                case "oak" -> {
                    outputStack = isGlow ? new ItemStack(MoreFrameVariantItems.OAK_GLOW_ITEM_FRAME) : new ItemStack(MoreFrameVariantItems.OAK_ITEM_FRAME);
                }
                case "spruce" -> {
                    outputStack = isGlow ? new ItemStack(MoreFrameVariantItems.SPRUCE_GLOW_ITEM_FRAME) : new ItemStack(MoreFrameVariantItems.SPRUCE_ITEM_FRAME);
                }
                case "warped" -> {
                    outputStack = isGlow ? new ItemStack(MoreFrameVariantItems.WARPED_GLOW_ITEM_FRAME) : new ItemStack(MoreFrameVariantItems.WARPED_ITEM_FRAME);
                }
                default -> {
                    outputStack = isGlow ? new ItemStack(Items.GLOW_ITEM_FRAME) : new ItemStack(Items.ITEM_FRAME);
                }
            }
            if (inputStack.hasTag()) {
                outputStack.setTag(inputStack.getTag().copy());
            }
        }
        return  outputStack;
    }
}