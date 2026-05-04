package de.pnku.mstv_mframev.compat.fastitemframes;

import de.pnku.mstv_mframev.MoreFrameVariants;
import fuzs.fastitemframes.init.ModRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jetbrains.annotations.NotNull;

import com.google.common.collect.ImmutableSet;

import java.util.*;

public class MoreFrameVariantsCompatibilityFIF {
    public static final WoodTypeProperty WOOD_TYPE = new WoodTypeProperty();

    public static void attachCompatFifDataToEntity(ItemStack itemInHand, HangingEntity itemFrame) {
        int rgb = Objects.requireNonNull(itemInHand.get(DataComponents.DYED_COLOR)).rgb();
        ModRegistry.ITEM_FRAME_COLOR_ATTACHMENT_TYPE.set(itemFrame, rgb);
    }

    public static boolean getCompatFifIsDyedEntity(ItemFrame itemFrame) {
        return ModRegistry.ITEM_FRAME_COLOR_ATTACHMENT_TYPE.has(itemFrame);
    }

    public static void registerCompatFifDatapack() {
        ResourceManagerHelper.registerBuiltinResourcePack(
                MoreFrameVariants.withModId("mframev_fifcompat"),
                FabricLoader.getInstance().getModContainer(MoreFrameVariants.MOD_ID).orElseThrow(),
                Component.literal("Fast Item Frames Compatibility"),
                ResourcePackActivationType.ALWAYS_ENABLED);
    }

    public static class WoodTypeProperty extends Property<WoodTypeValue> {
        private final ImmutableSet<WoodTypeValue> values;
        private final Map<String, WoodTypeValue> names = new HashMap<>();

        public WoodTypeProperty() {
            super("wood_type", WoodTypeValue.class);
            this.values = WoodType.values()
                    .map(WoodTypeValue::new)
                    .collect(ImmutableSet.toImmutableSet());
            for (WoodTypeValue value : this.values) {
                if (this.names.put(value.getSerializedName(), value) != null) {
                    throw new IllegalArgumentException("Multiple wood types have the same name '" + value.getSerializedName() + "'");
                }
            }
        }

        @Override
        public @NotNull Class<WoodTypeValue> getValueClass() {
            return WoodTypeValue.class;
        }

        @Override
        public @NotNull List<WoodTypeValue> getPossibleValues() {
            return this.values.asList();
        }

        @Override
        public @NotNull String getName(WoodTypeValue value) {
            return value.getSerializedName();
        }

        @Override
        public @NotNull Optional<WoodTypeValue> getValue(String name) {
            return Optional.ofNullable(this.names.get(name));
        }

        @Override
        public int getInternalIndex(WoodTypeValue value) {
            return getPossibleValues().indexOf(value);
        }

        public static WoodTypeValue getByName(String name) {
            return WOOD_TYPE.getValue(name).orElseGet(() -> new WoodTypeValue(WoodType.BIRCH));
        }
    }

    public record WoodTypeValue(WoodType woodType) implements Comparable<WoodTypeValue>, StringRepresentable {
        public WoodTypeValue {
            woodType = Objects.requireNonNull(woodType);
        }

        @Override
        public int compareTo(WoodTypeValue other) {
            return this.woodType.name().compareTo(other.woodType.name());
        }

        @Override
        public @NotNull String getSerializedName() {
            return this.woodType.name();
        }
    }
}
