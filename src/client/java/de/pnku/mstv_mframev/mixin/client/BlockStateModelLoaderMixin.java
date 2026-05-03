package de.pnku.mstv_mframev.mixin.client;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.client.resources.model.BlockStateModelLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.spongepowered.asm.mixin.*;

import java.util.HashMap;
import java.util.Map;

import static de.pnku.mstv_mframev.MoreFrameVariants.asId;
import static de.pnku.mstv_mframev.MoreFrameVariants.isFifLoaded;
import static de.pnku.mstv_mframev.item.MoreFrameVariantItems.more_item_frame_wood_types;

@Mixin(BlockStateModelLoader.class)
public abstract class BlockStateModelLoaderMixin {

    @Mutable
    @Final
    @Shadow private static Map<ResourceLocation, StateDefinition<Block, BlockState>> STATIC_DEFINITIONS;

    @Final
    @Shadow private static StateDefinition<Block, BlockState> ITEM_FRAME_FAKE_DEFINITION;

    @Unique
    private static final Map<ResourceLocation, StateDefinition<Block, BlockState>> item_frame_variant_definitions = new ImmutableMap.Builder<ResourceLocation, StateDefinition<Block, BlockState>>()
            .put(ResourceLocation.withDefaultNamespace("item_frame"), ITEM_FRAME_FAKE_DEFINITION)
            .put(ResourceLocation.withDefaultNamespace("glow_item_frame"), ITEM_FRAME_FAKE_DEFINITION)
            .putAll(getItemFrameVariantDefinitions())
            .build();

    @Unique
    private static Map<ResourceLocation, StateDefinition<Block, BlockState>> getItemFrameVariantDefinitions() {
        Map<ResourceLocation, StateDefinition<Block, BlockState>> map = new HashMap<>();
        for (String woodType : more_item_frame_wood_types) {
            String itemFramePath = woodType + "_item_frame";
            String glowItemFramePath = woodType + "_glow_item_frame";
            map.put(asId(itemFramePath), ITEM_FRAME_FAKE_DEFINITION);
            map.put(asId(glowItemFramePath), ITEM_FRAME_FAKE_DEFINITION);
            if (isFifLoaded) {
                map.put(ResourceLocation.fromNamespaceAndPath("fastitemframes", itemFramePath), ITEM_FRAME_FAKE_DEFINITION);
                map.put(ResourceLocation.fromNamespaceAndPath("fastitemframes", glowItemFramePath), ITEM_FRAME_FAKE_DEFINITION);
            }
        }
        return map;
    }

    static {
        STATIC_DEFINITIONS = ImmutableMap.copyOf(item_frame_variant_definitions);
    }
}
