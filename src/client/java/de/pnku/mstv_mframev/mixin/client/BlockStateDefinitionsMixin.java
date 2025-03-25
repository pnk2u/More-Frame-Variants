package de.pnku.mstv_mframev.mixin.client;

import net.minecraft.client.resources.model.BlockStateDefinitions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.spongepowered.asm.mixin.*;

import java.util.Map;

import static de.pnku.mstv_mframev.MoreFrameVariants.*;
import static java.util.Map.entry;

@Mixin(BlockStateDefinitions.class)
public abstract class BlockStateDefinitionsMixin {

    @Mutable
    @Final
    @Shadow private static Map<ResourceLocation, StateDefinition<Block, BlockState>> STATIC_DEFINITIONS;

    @Unique private static final ResourceLocation ACACIA_GLOW_ITEM_FRAME_LOC;
    @Unique private static final StateDefinition<Block, BlockState> ACACIA_GLOW_ITEM_FRAME_FAKE_DEFINITION = BlockStateDefinitions.createItemFrameFakeState();
    @Unique private static final ResourceLocation ACACIA_ITEM_FRAME_LOC;
    @Unique private static final StateDefinition<Block, BlockState> ACACIA_ITEM_FRAME_FAKE_DEFINITION = BlockStateDefinitions.createItemFrameFakeState();
    @Unique private static final ResourceLocation BAMBOO_GLOW_ITEM_FRAME_LOC;
    @Unique private static final StateDefinition<Block, BlockState> BAMBOO_GLOW_ITEM_FRAME_FAKE_DEFINITION = BlockStateDefinitions.createItemFrameFakeState();
    @Unique private static final ResourceLocation BAMBOO_ITEM_FRAME_LOC;
    @Unique private static final StateDefinition<Block, BlockState> BAMBOO_ITEM_FRAME_FAKE_DEFINITION = BlockStateDefinitions.createItemFrameFakeState();
    @Unique private static final ResourceLocation CHERRY_GLOW_ITEM_FRAME_LOC;
    @Unique private static final StateDefinition<Block, BlockState> CHERRY_GLOW_ITEM_FRAME_FAKE_DEFINITION = BlockStateDefinitions.createItemFrameFakeState();
    @Unique private static final ResourceLocation CHERRY_ITEM_FRAME_LOC;
    @Unique private static final StateDefinition<Block, BlockState> CHERRY_ITEM_FRAME_FAKE_DEFINITION = BlockStateDefinitions.createItemFrameFakeState();
    @Unique private static final ResourceLocation CRIMSON_GLOW_ITEM_FRAME_LOC;
    @Unique private static final StateDefinition<Block, BlockState> CRIMSON_GLOW_ITEM_FRAME_FAKE_DEFINITION = BlockStateDefinitions.createItemFrameFakeState();
    @Unique private static final ResourceLocation CRIMSON_ITEM_FRAME_LOC;
    @Unique private static final StateDefinition<Block, BlockState> CRIMSON_ITEM_FRAME_FAKE_DEFINITION = BlockStateDefinitions.createItemFrameFakeState();
    @Unique private static final ResourceLocation DARK_OAK_GLOW_ITEM_FRAME_LOC;
    @Unique private static final StateDefinition<Block, BlockState> DARK_OAK_GLOW_ITEM_FRAME_FAKE_DEFINITION = BlockStateDefinitions.createItemFrameFakeState();
    @Unique private static final ResourceLocation DARK_OAK_ITEM_FRAME_LOC;
    @Unique private static final StateDefinition<Block, BlockState> DARK_OAK_ITEM_FRAME_FAKE_DEFINITION = BlockStateDefinitions.createItemFrameFakeState();
    @Unique private static final ResourceLocation PALE_OAK_GLOW_ITEM_FRAME_LOC;
    @Unique private static final StateDefinition<Block, BlockState> PALE_OAK_GLOW_ITEM_FRAME_FAKE_DEFINITION = BlockStateDefinitions.createItemFrameFakeState();
    @Unique private static final ResourceLocation PALE_OAK_ITEM_FRAME_LOC;
    @Unique private static final StateDefinition<Block, BlockState> PALE_OAK_ITEM_FRAME_FAKE_DEFINITION = BlockStateDefinitions.createItemFrameFakeState();
    @Unique private static final ResourceLocation JUNGLE_GLOW_ITEM_FRAME_LOC;
    @Unique private static final StateDefinition<Block, BlockState> JUNGLE_GLOW_ITEM_FRAME_FAKE_DEFINITION = BlockStateDefinitions.createItemFrameFakeState();
    @Unique private static final ResourceLocation JUNGLE_ITEM_FRAME_LOC;
    @Unique private static final StateDefinition<Block, BlockState> JUNGLE_ITEM_FRAME_FAKE_DEFINITION = BlockStateDefinitions.createItemFrameFakeState();
    @Unique private static final ResourceLocation MANGROVE_GLOW_ITEM_FRAME_LOC;
    @Unique private static final StateDefinition<Block, BlockState> MANGROVE_GLOW_ITEM_FRAME_FAKE_DEFINITION = BlockStateDefinitions.createItemFrameFakeState();
    @Unique private static final ResourceLocation MANGROVE_ITEM_FRAME_LOC;
    @Unique private static final StateDefinition<Block, BlockState> MANGROVE_ITEM_FRAME_FAKE_DEFINITION = BlockStateDefinitions.createItemFrameFakeState();
    @Unique private static final ResourceLocation OAK_GLOW_ITEM_FRAME_LOC;
    @Unique private static final StateDefinition<Block, BlockState> OAK_GLOW_ITEM_FRAME_FAKE_DEFINITION = BlockStateDefinitions.createItemFrameFakeState();
    @Unique private static final ResourceLocation OAK_ITEM_FRAME_LOC;
    @Unique private static final StateDefinition<Block, BlockState> OAK_ITEM_FRAME_FAKE_DEFINITION = BlockStateDefinitions.createItemFrameFakeState();
    @Unique private static final ResourceLocation SPRUCE_GLOW_ITEM_FRAME_LOC;
    @Unique private static final StateDefinition<Block, BlockState> SPRUCE_GLOW_ITEM_FRAME_FAKE_DEFINITION = BlockStateDefinitions.createItemFrameFakeState();
    @Unique private static final ResourceLocation SPRUCE_ITEM_FRAME_LOC;
    @Unique private static final StateDefinition<Block, BlockState> SPRUCE_ITEM_FRAME_FAKE_DEFINITION = BlockStateDefinitions.createItemFrameFakeState();
    @Unique private static final ResourceLocation WARPED_GLOW_ITEM_FRAME_LOC;
    @Unique private static final StateDefinition<Block, BlockState> WARPED_GLOW_ITEM_FRAME_FAKE_DEFINITION = BlockStateDefinitions.createItemFrameFakeState();
    @Unique private static final ResourceLocation WARPED_ITEM_FRAME_LOC;
    @Unique private static final StateDefinition<Block, BlockState> WARPED_ITEM_FRAME_FAKE_DEFINITION = BlockStateDefinitions.createItemFrameFakeState();

    @Final @Shadow private static StateDefinition<Block, BlockState> ITEM_FRAME_FAKE_DEFINITION;
    @Final @Shadow private static StateDefinition<Block, BlockState> GLOW_ITEM_FRAME_FAKE_DEFINITION;

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
        STATIC_DEFINITIONS = Map.ofEntries(entry(ITEM_FRAME_LOCATION, ITEM_FRAME_FAKE_DEFINITION), entry(GLOW_ITEM_FRAME_LOCATION, GLOW_ITEM_FRAME_FAKE_DEFINITION),
                entry(ACACIA_ITEM_FRAME_LOC, ACACIA_ITEM_FRAME_FAKE_DEFINITION), entry(ACACIA_GLOW_ITEM_FRAME_LOC, ACACIA_GLOW_ITEM_FRAME_FAKE_DEFINITION),
                entry(BAMBOO_ITEM_FRAME_LOC, BAMBOO_ITEM_FRAME_FAKE_DEFINITION), entry(BAMBOO_GLOW_ITEM_FRAME_LOC, BAMBOO_GLOW_ITEM_FRAME_FAKE_DEFINITION),
                entry(CHERRY_ITEM_FRAME_LOC, CHERRY_ITEM_FRAME_FAKE_DEFINITION), entry(CHERRY_GLOW_ITEM_FRAME_LOC, CHERRY_GLOW_ITEM_FRAME_FAKE_DEFINITION),
                entry(CRIMSON_ITEM_FRAME_LOC, CRIMSON_ITEM_FRAME_FAKE_DEFINITION), entry(CRIMSON_GLOW_ITEM_FRAME_LOC, CRIMSON_GLOW_ITEM_FRAME_FAKE_DEFINITION),
                entry(DARK_OAK_ITEM_FRAME_LOC, DARK_OAK_ITEM_FRAME_FAKE_DEFINITION), entry(DARK_OAK_GLOW_ITEM_FRAME_LOC, DARK_OAK_GLOW_ITEM_FRAME_FAKE_DEFINITION),
                entry(PALE_OAK_ITEM_FRAME_LOC, PALE_OAK_ITEM_FRAME_FAKE_DEFINITION), entry(PALE_OAK_GLOW_ITEM_FRAME_LOC, PALE_OAK_GLOW_ITEM_FRAME_FAKE_DEFINITION),
                entry(JUNGLE_ITEM_FRAME_LOC, JUNGLE_ITEM_FRAME_FAKE_DEFINITION), entry(JUNGLE_GLOW_ITEM_FRAME_LOC, JUNGLE_GLOW_ITEM_FRAME_FAKE_DEFINITION),
                entry(MANGROVE_ITEM_FRAME_LOC, MANGROVE_ITEM_FRAME_FAKE_DEFINITION), entry(MANGROVE_GLOW_ITEM_FRAME_LOC, MANGROVE_GLOW_ITEM_FRAME_FAKE_DEFINITION),
                entry(OAK_ITEM_FRAME_LOC, OAK_ITEM_FRAME_FAKE_DEFINITION), entry(OAK_GLOW_ITEM_FRAME_LOC, OAK_GLOW_ITEM_FRAME_FAKE_DEFINITION),
                entry(SPRUCE_ITEM_FRAME_LOC, SPRUCE_ITEM_FRAME_FAKE_DEFINITION), entry(SPRUCE_GLOW_ITEM_FRAME_LOC, SPRUCE_GLOW_ITEM_FRAME_FAKE_DEFINITION),
                entry(WARPED_ITEM_FRAME_LOC, WARPED_ITEM_FRAME_FAKE_DEFINITION), entry(WARPED_GLOW_ITEM_FRAME_LOC, WARPED_GLOW_ITEM_FRAME_FAKE_DEFINITION));
    }
}
