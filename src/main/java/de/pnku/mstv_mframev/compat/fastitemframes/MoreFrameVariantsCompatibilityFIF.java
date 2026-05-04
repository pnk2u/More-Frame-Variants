package de.pnku.mstv_mframev.compat.fastitemframes;

import com.google.common.collect.ImmutableSet;
import de.pnku.mstv_mframev.MoreFrameVariants;
import de.pnku.mstv_mframev.compat.fastitemframes.util.ICompatFIF;
import fuzs.fastitemframes.capability.ItemFrameColorCapability;
import fuzs.fastitemframes.init.ModRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import com.google.common.collect.ImmutableSet;

import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;
import java.util.Map;
import java.util.Optional;

import java.util.OptionalInt;

public class MoreFrameVariantsCompatibilityFIF {
    public static final WoodTypeProperty WOOD_TYPE = new WoodTypeProperty();

        public static void attachCompatFifDataToEntity(DyeableLeatherItem dyeableLeatherItem, ItemStack itemInHand, ItemFrame itemFrame) {
            ItemFrameColorCapability capability = ModRegistry.ITEM_FRAME_COLOR_CAPABILITY.get(itemFrame);
            capability.setColor(dyeableLeatherItem.getColor(itemInHand));
            capability.setChanged();
        }

    public static OptionalInt getCompatFifDyedEntityColor(ItemFrame itemFrame) {
        OptionalInt fif_color_data = ((ItemFrameColorCapability)ModRegistry.ITEM_FRAME_COLOR_CAPABILITY.get(itemFrame)).getColor();
            if (fif_color_data.isEmpty()) {
                // Read Color from fabric attachments
                int color_nbt = ((ICompatFIF) itemFrame).mframev$getCompatFIFColor();
                if (color_nbt != 10511680) { // Default color is 10511680 (0xA06540)
                    return OptionalInt.of(color_nbt);
                }
            }
            return fif_color_data;
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
        public @NotNull Collection<WoodTypeValue> getPossibleValues() {
            return this.values;
        }

        @Override
        public @NotNull String getName(WoodTypeValue value) {
            return value.getSerializedName();
        }

        @Override
        public @NotNull Optional<WoodTypeValue> getValue(String name) {
            return Optional.ofNullable(this.names.get(name));
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
