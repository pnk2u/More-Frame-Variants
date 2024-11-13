package de.pnku.mstv_mframev.mixin.client;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.resources.model.BlockStateModelLoader;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.spongepowered.asm.mixin.*;

import java.util.Map;

import static de.pnku.mstv_mframev.MoreFrameVariants.*;
import static java.util.Map.entry;

@Mixin(BlockStateModelLoader.class)
public abstract class BlockStateModelLoaderMixin {

    @Mutable
    @Final
    @Shadow private static Map<ResourceLocation, StateDefinition<Block, BlockState>> STATIC_DEFINITIONS;

    @Unique private static final ResourceLocation ACACIA_GLOW_ITEM_FRAME_LOC;
    @Unique private static final ResourceLocation ACACIA_ITEM_FRAME_LOC;
    @Unique private static final ResourceLocation BAMBOO_GLOW_ITEM_FRAME_LOC;
    @Unique private static final ResourceLocation BAMBOO_ITEM_FRAME_LOC;
    @Unique private static final ResourceLocation CHERRY_GLOW_ITEM_FRAME_LOC;
    @Unique private static final ResourceLocation CHERRY_ITEM_FRAME_LOC;
    @Unique private static final ResourceLocation CRIMSON_GLOW_ITEM_FRAME_LOC;
    @Unique private static final ResourceLocation CRIMSON_ITEM_FRAME_LOC;
    @Unique private static final ResourceLocation DARK_OAK_GLOW_ITEM_FRAME_LOC;
    @Unique private static final ResourceLocation DARK_OAK_ITEM_FRAME_LOC;
    @Unique private static final ResourceLocation PALE_OAK_GLOW_ITEM_FRAME_LOC;
    @Unique private static final ResourceLocation PALE_OAK_ITEM_FRAME_LOC;
    @Unique private static final ResourceLocation JUNGLE_GLOW_ITEM_FRAME_LOC;
    @Unique private static final ResourceLocation JUNGLE_ITEM_FRAME_LOC;
    @Unique private static final ResourceLocation MANGROVE_GLOW_ITEM_FRAME_LOC;
    @Unique private static final ResourceLocation MANGROVE_ITEM_FRAME_LOC;
    @Unique private static final ResourceLocation OAK_GLOW_ITEM_FRAME_LOC;
    @Unique private static final ResourceLocation OAK_ITEM_FRAME_LOC;
    @Unique private static final ResourceLocation SPRUCE_GLOW_ITEM_FRAME_LOC;
    @Unique private static final ResourceLocation SPRUCE_ITEM_FRAME_LOC;
    @Unique private static final ResourceLocation WARPED_GLOW_ITEM_FRAME_LOC;
    @Unique private static final ResourceLocation WARPED_ITEM_FRAME_LOC;
    @Unique private static final ModelResourceLocation ACACIA_GLOW_MAP_FRAME_LOC;
    @Unique private static final ModelResourceLocation ACACIA_GLOW_FRAME_LOC;
    @Unique private static final ModelResourceLocation ACACIA_MAP_FRAME_LOC;
    @Unique private static final ModelResourceLocation ACACIA_FRAME_LOC;
    @Unique private static final ModelResourceLocation BAMBOO_GLOW_MAP_FRAME_LOC;
    @Unique private static final ModelResourceLocation BAMBOO_GLOW_FRAME_LOC;
    @Unique private static final ModelResourceLocation BAMBOO_MAP_FRAME_LOC;
    @Unique private static final ModelResourceLocation BAMBOO_FRAME_LOC;
    @Unique private static final ModelResourceLocation CHERRY_GLOW_MAP_FRAME_LOC;
    @Unique private static final ModelResourceLocation CHERRY_GLOW_FRAME_LOC;
    @Unique private static final ModelResourceLocation CHERRY_MAP_FRAME_LOC;
    @Unique private static final ModelResourceLocation CHERRY_FRAME_LOC;
    @Unique private static final ModelResourceLocation CRIMSON_GLOW_MAP_FRAME_LOC;
    @Unique private static final ModelResourceLocation CRIMSON_GLOW_FRAME_LOC;
    @Unique private static final ModelResourceLocation CRIMSON_MAP_FRAME_LOC;
    @Unique private static final ModelResourceLocation CRIMSON_FRAME_LOC;
    @Unique private static final ModelResourceLocation DARK_OAK_GLOW_MAP_FRAME_LOC;
    @Unique private static final ModelResourceLocation DARK_OAK_GLOW_FRAME_LOC;
    @Unique private static final ModelResourceLocation DARK_OAK_MAP_FRAME_LOC;
    @Unique private static final ModelResourceLocation DARK_OAK_FRAME_LOC;
    @Unique private static final ModelResourceLocation PALE_OAK_GLOW_MAP_FRAME_LOC;
    @Unique private static final ModelResourceLocation PALE_OAK_GLOW_FRAME_LOC;
    @Unique private static final ModelResourceLocation PALE_OAK_MAP_FRAME_LOC;
    @Unique private static final ModelResourceLocation PALE_OAK_FRAME_LOC;
    @Unique private static final ModelResourceLocation JUNGLE_GLOW_MAP_FRAME_LOC;
    @Unique private static final ModelResourceLocation JUNGLE_GLOW_FRAME_LOC;
    @Unique private static final ModelResourceLocation JUNGLE_MAP_FRAME_LOC;
    @Unique private static final ModelResourceLocation JUNGLE_FRAME_LOC;
    @Unique private static final ModelResourceLocation MANGROVE_GLOW_MAP_FRAME_LOC;
    @Unique private static final ModelResourceLocation MANGROVE_GLOW_FRAME_LOC;
    @Unique private static final ModelResourceLocation MANGROVE_MAP_FRAME_LOC;
    @Unique private static final ModelResourceLocation MANGROVE_FRAME_LOC;
    @Unique private static final ModelResourceLocation OAK_GLOW_MAP_FRAME_LOC;
    @Unique private static final ModelResourceLocation OAK_GLOW_FRAME_LOC;
    @Unique private static final ModelResourceLocation OAK_MAP_FRAME_LOC;
    @Unique private static final ModelResourceLocation OAK_FRAME_LOC;
    @Unique private static final ModelResourceLocation SPRUCE_GLOW_MAP_FRAME_LOC;
    @Unique private static final ModelResourceLocation SPRUCE_GLOW_FRAME_LOC;
    @Unique private static final ModelResourceLocation SPRUCE_MAP_FRAME_LOC;
    @Unique private static final ModelResourceLocation SPRUCE_FRAME_LOC;
    @Unique private static final ModelResourceLocation WARPED_GLOW_MAP_FRAME_LOC;
    @Unique private static final ModelResourceLocation WARPED_GLOW_FRAME_LOC;
    @Unique private static final ModelResourceLocation WARPED_MAP_FRAME_LOC;
    @Unique private static final ModelResourceLocation WARPED_FRAME_LOC;

    @Final
    @Shadow private static StateDefinition<Block, BlockState> ITEM_FRAME_FAKE_DEFINITION;

    @Shadow @Final private static ResourceLocation ITEM_FRAME_LOCATION;
    @Shadow @Final private static ResourceLocation GLOW_ITEM_FRAME_LOCATION;

    static {
        // ResourceLocations
        ACACIA_GLOW_ITEM_FRAME_LOC = asId("acacia_glow_item_frame");
        ACACIA_ITEM_FRAME_LOC = asId("acacia_item_frame");
        BAMBOO_GLOW_ITEM_FRAME_LOC = asId("bamboo_glow_item_frame");
        BAMBOO_ITEM_FRAME_LOC = asId("bamboo_item_frame");
        CHERRY_GLOW_ITEM_FRAME_LOC = asId("cherry_glow_item_frame");
        CHERRY_ITEM_FRAME_LOC = asId("cherry_item_frame");
        CRIMSON_GLOW_ITEM_FRAME_LOC = asId("crimson_glow_item_frame");
        CRIMSON_ITEM_FRAME_LOC = asId("crimson_item_frame");
        DARK_OAK_GLOW_ITEM_FRAME_LOC = asId("dark_oak_glow_item_frame");
        DARK_OAK_ITEM_FRAME_LOC = asId("dark_oak_item_frame");
        PALE_OAK_GLOW_ITEM_FRAME_LOC = asId("pale_oak_glow_item_frame");
        PALE_OAK_ITEM_FRAME_LOC = asId("pale_oak_item_frame");
        JUNGLE_GLOW_ITEM_FRAME_LOC = asId("jungle_glow_item_frame");
        JUNGLE_ITEM_FRAME_LOC = asId("jungle_item_frame");
        MANGROVE_GLOW_ITEM_FRAME_LOC = asId("mangrove_glow_item_frame");
        MANGROVE_ITEM_FRAME_LOC = asId("mangrove_item_frame");
        OAK_GLOW_ITEM_FRAME_LOC = asId("oak_glow_item_frame");
        OAK_ITEM_FRAME_LOC = asId("oak_item_frame");
        SPRUCE_GLOW_ITEM_FRAME_LOC = asId("spruce_glow_item_frame");
        SPRUCE_ITEM_FRAME_LOC = asId("spruce_item_frame");
        WARPED_GLOW_ITEM_FRAME_LOC = asId("warped_glow_item_frame");
        WARPED_ITEM_FRAME_LOC = asId("warped_item_frame");
        //StaticDefinitions
        STATIC_DEFINITIONS = Map.ofEntries(entry(ITEM_FRAME_LOCATION, ITEM_FRAME_FAKE_DEFINITION), entry(GLOW_ITEM_FRAME_LOCATION, ITEM_FRAME_FAKE_DEFINITION),
                entry(ACACIA_ITEM_FRAME_LOC, ITEM_FRAME_FAKE_DEFINITION), entry(ACACIA_GLOW_ITEM_FRAME_LOC, ITEM_FRAME_FAKE_DEFINITION),
                entry(BAMBOO_ITEM_FRAME_LOC, ITEM_FRAME_FAKE_DEFINITION), entry(BAMBOO_GLOW_ITEM_FRAME_LOC, ITEM_FRAME_FAKE_DEFINITION),
                entry(CHERRY_ITEM_FRAME_LOC, ITEM_FRAME_FAKE_DEFINITION), entry(CHERRY_GLOW_ITEM_FRAME_LOC, ITEM_FRAME_FAKE_DEFINITION),
                entry(CRIMSON_ITEM_FRAME_LOC, ITEM_FRAME_FAKE_DEFINITION), entry(CRIMSON_GLOW_ITEM_FRAME_LOC, ITEM_FRAME_FAKE_DEFINITION),
                entry(DARK_OAK_ITEM_FRAME_LOC, ITEM_FRAME_FAKE_DEFINITION), entry(DARK_OAK_GLOW_ITEM_FRAME_LOC, ITEM_FRAME_FAKE_DEFINITION),
                entry(PALE_OAK_ITEM_FRAME_LOC, ITEM_FRAME_FAKE_DEFINITION), entry(PALE_OAK_GLOW_ITEM_FRAME_LOC, ITEM_FRAME_FAKE_DEFINITION),
                entry(JUNGLE_ITEM_FRAME_LOC, ITEM_FRAME_FAKE_DEFINITION), entry(JUNGLE_GLOW_ITEM_FRAME_LOC, ITEM_FRAME_FAKE_DEFINITION),
                entry(MANGROVE_ITEM_FRAME_LOC, ITEM_FRAME_FAKE_DEFINITION), entry(MANGROVE_GLOW_ITEM_FRAME_LOC, ITEM_FRAME_FAKE_DEFINITION),
                entry(OAK_ITEM_FRAME_LOC, ITEM_FRAME_FAKE_DEFINITION), entry(OAK_GLOW_ITEM_FRAME_LOC, ITEM_FRAME_FAKE_DEFINITION),
                entry(SPRUCE_ITEM_FRAME_LOC, ITEM_FRAME_FAKE_DEFINITION), entry(SPRUCE_GLOW_ITEM_FRAME_LOC, ITEM_FRAME_FAKE_DEFINITION),
                entry(WARPED_ITEM_FRAME_LOC, ITEM_FRAME_FAKE_DEFINITION), entry(WARPED_GLOW_ITEM_FRAME_LOC, ITEM_FRAME_FAKE_DEFINITION));
        //ModelResourceLocations
        ACACIA_GLOW_MAP_FRAME_LOC = new ModelResourceLocation(ACACIA_GLOW_ITEM_FRAME_LOC, "map=true");
        ACACIA_GLOW_FRAME_LOC = new ModelResourceLocation(ACACIA_GLOW_ITEM_FRAME_LOC, "map=false");
        ACACIA_MAP_FRAME_LOC = new ModelResourceLocation(ACACIA_ITEM_FRAME_LOC, "map=true");
        ACACIA_FRAME_LOC = new ModelResourceLocation(ACACIA_ITEM_FRAME_LOC, "map=false");
        BAMBOO_GLOW_MAP_FRAME_LOC = new ModelResourceLocation(BAMBOO_GLOW_ITEM_FRAME_LOC, "map=true");
        BAMBOO_GLOW_FRAME_LOC = new ModelResourceLocation(BAMBOO_GLOW_ITEM_FRAME_LOC, "map=false");
        BAMBOO_MAP_FRAME_LOC = new ModelResourceLocation(BAMBOO_ITEM_FRAME_LOC, "map=true");
        BAMBOO_FRAME_LOC = new ModelResourceLocation(BAMBOO_ITEM_FRAME_LOC, "map=false");
        CHERRY_GLOW_MAP_FRAME_LOC = new ModelResourceLocation(CHERRY_GLOW_ITEM_FRAME_LOC, "map=true");
        CHERRY_GLOW_FRAME_LOC = new ModelResourceLocation(CHERRY_GLOW_ITEM_FRAME_LOC, "map=false");
        CHERRY_MAP_FRAME_LOC = new ModelResourceLocation(CHERRY_ITEM_FRAME_LOC, "map=true");
        CHERRY_FRAME_LOC = new ModelResourceLocation(CHERRY_ITEM_FRAME_LOC, "map=false");
        CRIMSON_GLOW_MAP_FRAME_LOC = new ModelResourceLocation(CRIMSON_GLOW_ITEM_FRAME_LOC, "map=true");
        CRIMSON_GLOW_FRAME_LOC = new ModelResourceLocation(CRIMSON_GLOW_ITEM_FRAME_LOC, "map=false");
        CRIMSON_MAP_FRAME_LOC = new ModelResourceLocation(CRIMSON_ITEM_FRAME_LOC, "map=true");
        CRIMSON_FRAME_LOC = new ModelResourceLocation(CRIMSON_ITEM_FRAME_LOC, "map=false");
        DARK_OAK_GLOW_MAP_FRAME_LOC = new ModelResourceLocation(DARK_OAK_GLOW_ITEM_FRAME_LOC, "map=true");
        DARK_OAK_GLOW_FRAME_LOC = new ModelResourceLocation(DARK_OAK_GLOW_ITEM_FRAME_LOC, "map=false");
        DARK_OAK_MAP_FRAME_LOC = new ModelResourceLocation(DARK_OAK_ITEM_FRAME_LOC, "map=true");
        DARK_OAK_FRAME_LOC = new ModelResourceLocation(DARK_OAK_ITEM_FRAME_LOC, "map=false");
        PALE_OAK_GLOW_MAP_FRAME_LOC = new ModelResourceLocation(PALE_OAK_GLOW_ITEM_FRAME_LOC, "map=true");
        PALE_OAK_GLOW_FRAME_LOC = new ModelResourceLocation(PALE_OAK_GLOW_ITEM_FRAME_LOC, "map=false");
        PALE_OAK_MAP_FRAME_LOC = new ModelResourceLocation(PALE_OAK_ITEM_FRAME_LOC, "map=true");
        PALE_OAK_FRAME_LOC = new ModelResourceLocation(PALE_OAK_ITEM_FRAME_LOC, "map=false");
        JUNGLE_GLOW_MAP_FRAME_LOC = new ModelResourceLocation(JUNGLE_GLOW_ITEM_FRAME_LOC, "map=true");
        JUNGLE_GLOW_FRAME_LOC = new ModelResourceLocation(JUNGLE_GLOW_ITEM_FRAME_LOC, "map=false");
        JUNGLE_MAP_FRAME_LOC = new ModelResourceLocation(JUNGLE_ITEM_FRAME_LOC, "map=true");
        JUNGLE_FRAME_LOC = new ModelResourceLocation(JUNGLE_ITEM_FRAME_LOC, "map=false");
        MANGROVE_GLOW_MAP_FRAME_LOC = new ModelResourceLocation(MANGROVE_GLOW_ITEM_FRAME_LOC, "map=true");
        MANGROVE_GLOW_FRAME_LOC = new ModelResourceLocation(MANGROVE_GLOW_ITEM_FRAME_LOC, "map=false");
        MANGROVE_MAP_FRAME_LOC = new ModelResourceLocation(MANGROVE_ITEM_FRAME_LOC, "map=true");
        MANGROVE_FRAME_LOC = new ModelResourceLocation(MANGROVE_ITEM_FRAME_LOC, "map=false");
        OAK_GLOW_MAP_FRAME_LOC = new ModelResourceLocation(OAK_GLOW_ITEM_FRAME_LOC, "map=true");
        OAK_GLOW_FRAME_LOC = new ModelResourceLocation(OAK_GLOW_ITEM_FRAME_LOC, "map=false");
        OAK_MAP_FRAME_LOC = new ModelResourceLocation(OAK_ITEM_FRAME_LOC, "map=true");
        OAK_FRAME_LOC = new ModelResourceLocation(OAK_ITEM_FRAME_LOC, "map=false");
        SPRUCE_GLOW_MAP_FRAME_LOC = new ModelResourceLocation(SPRUCE_GLOW_ITEM_FRAME_LOC, "map=true");
        SPRUCE_GLOW_FRAME_LOC = new ModelResourceLocation(SPRUCE_GLOW_ITEM_FRAME_LOC, "map=false");
        SPRUCE_MAP_FRAME_LOC = new ModelResourceLocation(SPRUCE_ITEM_FRAME_LOC, "map=true");
        SPRUCE_FRAME_LOC = new ModelResourceLocation(SPRUCE_ITEM_FRAME_LOC, "map=false");
        WARPED_GLOW_MAP_FRAME_LOC = new ModelResourceLocation(WARPED_GLOW_ITEM_FRAME_LOC, "map=true");
        WARPED_GLOW_FRAME_LOC = new ModelResourceLocation(WARPED_GLOW_ITEM_FRAME_LOC, "map=false");
        WARPED_MAP_FRAME_LOC = new ModelResourceLocation(WARPED_ITEM_FRAME_LOC, "map=true");
        WARPED_FRAME_LOC = new ModelResourceLocation(WARPED_ITEM_FRAME_LOC, "map=false");
    }
}
