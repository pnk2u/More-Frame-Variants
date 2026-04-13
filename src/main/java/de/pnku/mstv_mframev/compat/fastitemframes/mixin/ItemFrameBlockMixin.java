/* This class is commented out as FastItemFrames has at this time not been updated to 26.1 yet.
package de.pnku.mstv_mframev.compat.fastitemframes.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import de.pnku.mstv_mframev.compat.fastitemframes.MoreFrameVariantsCompatibilityFIF;
import de.pnku.mstv_mframev.item.MoreFrameVariantItem;
import de.pnku.mstv_mframev.item.MoreFrameVariantItems;
import fuzs.fastitemframes.world.level.block.ItemFrameBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static de.pnku.mstv_mframev.item.MoreFrameVariantItem.stackFromIFWoodVariant;
import static fuzs.fastitemframes.world.level.block.ItemFrameBlock.ROTATION;
import static fuzs.fastitemframes.world.level.block.ItemFrameBlock.WAXED;

@Mixin(ItemFrameBlock.class)
public abstract class ItemFrameBlockMixin extends BaseEntityBlock {
    protected ItemFrameBlockMixin(Properties properties) {super(properties);}
    @Unique
    private static final EnumProperty<MoreFrameVariantsCompatibilityFIF.WoodType> WOOD_TYPE = MoreFrameVariantsCompatibilityFIF.WOOD_TYPE;

    @Shadow @Final
    public static BooleanProperty MAP;
    @Shadow @Final
    public static BooleanProperty DYED;
    @Shadow @Final
    public static BooleanProperty INVISIBLE;
    @Shadow @Final
    public static BooleanProperty WATERLOGGED;
    @Shadow @Final
    public static EnumProperty<Direction> FACING;


    @Inject(method = "<init>", at = @At(value = "TAIL"), remap = false)
    public void injectedInitAtTail(Item item, Properties properties, CallbackInfo ci) {
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.FALSE).setValue(INVISIBLE, Boolean.FALSE).setValue(MAP, Boolean.FALSE).setValue(DYED, Boolean.FALSE).setValue(WOOD_TYPE, MoreFrameVariantsCompatibilityFIF.WoodType.BIRCH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, ROTATION, INVISIBLE, WAXED, MAP, WATERLOGGED, DYED, WOOD_TYPE);
    }

    @ModifyReturnValue(method = "getCloneItemStack", at = @At("RETURN"), remap = false)
    public ItemStack wrappedGetCloneItemStackAtGetItem(ItemStack original, LevelReader level, BlockPos pos) {
        String woodVariant = level.getBlockEntity(pos).getBlockState().getValue(WOOD_TYPE).toString().toLowerCase();
        return stackFromIFWoodVariant(woodVariant, MoreFrameVariantItems.more_glow_item_frames.contains(original.getItem()) || original.getItem() == Items.GLOW_ITEM_FRAME, original);
    }

    @WrapOperation(method = "getCloneItemStack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getItem()Lnet/minecraft/world/item/Item;"), remap = false)
    public Item redirectedGetCloneItemStackGetItem(ItemStack instance, Operation<Item> original) {
        if (instance.getItem() instanceof MoreFrameVariantItem) {
            return Items.ITEM_FRAME;
        } else return original.call(instance);
    }

}

*/