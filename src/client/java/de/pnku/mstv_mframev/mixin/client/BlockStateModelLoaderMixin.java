package de.pnku.mstv_mframev.mixin.client;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.resources.model.BlockStateModelLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

import static de.pnku.mstv_mframev.MoreFrameVariants.*;
import static java.util.Map.entry;

@Mixin(BlockStateModelLoader.class)
public abstract class BlockStateModelLoaderMixin {

    @Mutable
    @Final
    @Shadow private static Map<ResourceLocation, StateDefinition<Block, BlockState>> STATIC_DEFINITIONS;

    @Final
    @Shadow private static StateDefinition<Block, BlockState> ITEM_FRAME_FAKE_DEFINITION;

    static {
        STATIC_DEFINITIONS = ImmutableMap.ofEntries(entry(ResourceLocation.withDefaultNamespace("item_frame"), ITEM_FRAME_FAKE_DEFINITION), entry(ResourceLocation.withDefaultNamespace("glow_item_frame"), ITEM_FRAME_FAKE_DEFINITION),
                entry(asId("acacia_item_frame"), ITEM_FRAME_FAKE_DEFINITION), entry(asId( "acacia_glow_item_frame"), ITEM_FRAME_FAKE_DEFINITION),
                entry(asId("bamboo_item_frame"), ITEM_FRAME_FAKE_DEFINITION), entry(asId( "bamboo_glow_item_frame"), ITEM_FRAME_FAKE_DEFINITION),
                entry(asId("cherry_item_frame"), ITEM_FRAME_FAKE_DEFINITION), entry(asId( "cherry_glow_item_frame"), ITEM_FRAME_FAKE_DEFINITION),
                entry(asId("crimson_item_frame"), ITEM_FRAME_FAKE_DEFINITION), entry(asId( "crimson_glow_item_frame"), ITEM_FRAME_FAKE_DEFINITION),
                entry(asId("dark_oak_item_frame"), ITEM_FRAME_FAKE_DEFINITION), entry(asId( "dark_oak_glow_item_frame"), ITEM_FRAME_FAKE_DEFINITION),
                entry(asId("jungle_item_frame"), ITEM_FRAME_FAKE_DEFINITION), entry(asId( "jungle_glow_item_frame"), ITEM_FRAME_FAKE_DEFINITION),
                entry(asId("mangrove_item_frame"), ITEM_FRAME_FAKE_DEFINITION), entry(asId( "mangrove_glow_item_frame"), ITEM_FRAME_FAKE_DEFINITION),
                entry(asId("oak_item_frame"), ITEM_FRAME_FAKE_DEFINITION), entry(asId( "oak_glow_item_frame"), ITEM_FRAME_FAKE_DEFINITION),
                entry(asId("spruce_item_frame"), ITEM_FRAME_FAKE_DEFINITION), entry(asId( "spruce_glow_item_frame"), ITEM_FRAME_FAKE_DEFINITION),
                entry(asId("warped_item_frame"), ITEM_FRAME_FAKE_DEFINITION), entry(asId( "warped_glow_item_frame"), ITEM_FRAME_FAKE_DEFINITION));
    }
}
