package de.pnku.mstv_mframev.mixin.client;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.spongepowered.asm.mixin.*;

import java.util.Map;

import static de.pnku.mstv_mframev.MoreFrameVariants.MOD_ID;
import static de.pnku.mstv_mframev.item.MoreFrameVariantItems.more_item_frame_wood_types;

@Mixin(ModelBakery.class)
public abstract class ModelBakeryMixin {

    @Mutable
    @Final
    @Shadow private static Map<ResourceLocation, StateDefinition<Block, BlockState>> STATIC_DEFINITIONS;

    @Final
    @Shadow private static StateDefinition<Block, BlockState> ITEM_FRAME_FAKE_DEFINITION;

    @Unique
    private static Map<ResourceLocation, StateDefinition<Block, BlockState>> createStaticDefinitions() {
        ImmutableMap.Builder<ResourceLocation, StateDefinition<Block, BlockState>> builder = ImmutableMap.builder();
        builder.putAll(STATIC_DEFINITIONS);
        for (String woodType : more_item_frame_wood_types) {
            builder.put(new ResourceLocation(MOD_ID, woodType + "_item_frame"), ITEM_FRAME_FAKE_DEFINITION);
            builder.put(new ResourceLocation(MOD_ID, woodType + "_glow_item_frame"), ITEM_FRAME_FAKE_DEFINITION);
        }
        return builder.build();
    }

    static {
        STATIC_DEFINITIONS = createStaticDefinitions();
    }
}