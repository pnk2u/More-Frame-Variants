package de.pnku.mstv_mframev.mixin.client;

import net.minecraft.client.resources.model.BlockStateDefinitions;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.HashMap;
import java.util.Map;

import static de.pnku.mstv_mframev.MoreFrameVariants.asId;
import static de.pnku.mstv_mframev.item.MoreFrameVariantItems.more_item_frame_wood_types;
import static java.util.Map.entry;

@Mixin(BlockStateDefinitions.class)
public abstract class BlockStateDefinitionsMixin {

    @Mutable
    @Final
    @Shadow
    public static Map<Identifier, StateDefinition<Block, BlockState>> STATIC_DEFINITIONS;

    @Final
    @Shadow
    private static StateDefinition<Block, BlockState> ITEM_FRAME_FAKE_DEFINITION;
    @Final
    @Shadow
    private static StateDefinition<Block, BlockState> GLOW_ITEM_FRAME_FAKE_DEFINITION;

    @Final
    @Shadow
    private static Identifier ITEM_FRAME_LOCATION;
    @Final
    @Shadow
    private static Identifier GLOW_ITEM_FRAME_LOCATION;

    static {
        Map<Identifier, StateDefinition<Block, BlockState>> definitions = new HashMap<>(Map.ofEntries(
                entry(ITEM_FRAME_LOCATION, ITEM_FRAME_FAKE_DEFINITION),
                entry(GLOW_ITEM_FRAME_LOCATION, GLOW_ITEM_FRAME_FAKE_DEFINITION)
        ));

        for (String woodType : more_item_frame_wood_types) {
            definitions.put(asId(woodType + "_item_frame"), BlockStateDefinitions.createItemFrameFakeState());
            definitions.put(asId(woodType + "_glow_item_frame"), BlockStateDefinitions.createItemFrameFakeState());
        }

        STATIC_DEFINITIONS = definitions;
    }
}
