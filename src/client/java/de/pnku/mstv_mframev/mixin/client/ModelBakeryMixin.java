package de.pnku.mstv_mframev.mixin.client;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

import static de.pnku.mstv_mframev.MoreFrameVariants.MOD_ID;
import static java.util.Map.entry;

@Mixin(ModelBakery.class)
public abstract class ModelBakeryMixin {

    @Mutable
    @Final
    @Shadow private static Map<ResourceLocation, StateDefinition<Block, BlockState>> STATIC_DEFINITIONS;

    @Final
    @Shadow private static StateDefinition<Block, BlockState> ITEM_FRAME_FAKE_DEFINITION;

    static {
        STATIC_DEFINITIONS = ImmutableMap.ofEntries(entry(new ResourceLocation("item_frame"), ITEM_FRAME_FAKE_DEFINITION), entry(new ResourceLocation("glow_item_frame"), ITEM_FRAME_FAKE_DEFINITION),
                entry(new ResourceLocation(MOD_ID,"acacia_item_frame"), ITEM_FRAME_FAKE_DEFINITION), entry(new ResourceLocation(MOD_ID, "acacia_glow_item_frame"), ITEM_FRAME_FAKE_DEFINITION),
                entry(new ResourceLocation(MOD_ID,"bamboo_item_frame"), ITEM_FRAME_FAKE_DEFINITION), entry(new ResourceLocation(MOD_ID, "bamboo_glow_item_frame"), ITEM_FRAME_FAKE_DEFINITION),
                entry(new ResourceLocation(MOD_ID,"cherry_item_frame"), ITEM_FRAME_FAKE_DEFINITION), entry(new ResourceLocation(MOD_ID, "cherry_glow_item_frame"), ITEM_FRAME_FAKE_DEFINITION),
                entry(new ResourceLocation(MOD_ID,"crimson_item_frame"), ITEM_FRAME_FAKE_DEFINITION), entry(new ResourceLocation(MOD_ID, "crimson_glow_item_frame"), ITEM_FRAME_FAKE_DEFINITION),
                entry(new ResourceLocation(MOD_ID,"dark_oak_item_frame"), ITEM_FRAME_FAKE_DEFINITION), entry(new ResourceLocation(MOD_ID, "dark_oak_glow_item_frame"), ITEM_FRAME_FAKE_DEFINITION),
                entry(new ResourceLocation(MOD_ID,"jungle_item_frame"), ITEM_FRAME_FAKE_DEFINITION), entry(new ResourceLocation(MOD_ID, "jungle_glow_item_frame"), ITEM_FRAME_FAKE_DEFINITION),
                entry(new ResourceLocation(MOD_ID,"mangrove_item_frame"), ITEM_FRAME_FAKE_DEFINITION), entry(new ResourceLocation(MOD_ID, "mangrove_glow_item_frame"), ITEM_FRAME_FAKE_DEFINITION),
                entry(new ResourceLocation(MOD_ID,"oak_item_frame"), ITEM_FRAME_FAKE_DEFINITION), entry(new ResourceLocation(MOD_ID, "oak_glow_item_frame"), ITEM_FRAME_FAKE_DEFINITION),
                entry(new ResourceLocation(MOD_ID,"spruce_item_frame"), ITEM_FRAME_FAKE_DEFINITION), entry(new ResourceLocation(MOD_ID, "spruce_glow_item_frame"), ITEM_FRAME_FAKE_DEFINITION),
                entry(new ResourceLocation(MOD_ID,"warped_item_frame"), ITEM_FRAME_FAKE_DEFINITION), entry(new ResourceLocation(MOD_ID, "warped_glow_item_frame"), ITEM_FRAME_FAKE_DEFINITION));
    }
}
