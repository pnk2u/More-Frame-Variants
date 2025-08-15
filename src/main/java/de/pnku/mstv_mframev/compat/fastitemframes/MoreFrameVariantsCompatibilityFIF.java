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
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.NotNull;

public class MoreFrameVariantsCompatibilityFIF {
        public static final EnumProperty<WoodType> WOOD_TYPE = EnumProperty.create("wood_type", WoodType.class);

        public static void attachCompatFifDataToEntity(ItemStack itemInHand, HangingEntity itemFrame) {
            DyedItemColor color = itemInHand.get(DataComponents.DYED_COLOR);
            ModRegistry.ITEM_FRAME_COLOR_ATTACHMENT_TYPE.set(itemFrame, color);
        }

        public static boolean getCompatFifIsDyedEntity(ItemFrame itemFrame) {
            return ModRegistry.ITEM_FRAME_COLOR_ATTACHMENT_TYPE.has(itemFrame);
        }

        public static void registerCompatFifDatapack() {
            ResourceManagerHelper.registerBuiltinResourcePack(
                    MoreFrameVariants.asId("mframev_fifcompat"),
                    FabricLoader.getInstance().getModContainer(MoreFrameVariants.MOD_ID).orElseThrow(),
                    Component.literal("Fast Item Frames Compatibility"),
                    ResourcePackActivationType.ALWAYS_ENABLED);
        }

    public enum WoodType implements StringRepresentable {
        ACACIA("acacia"),
        BAMBOO("bamboo"),
        BIRCH("birch"),
        CHERRY("cherry"),
        CRIMSON("crimson"),
        DARK_OAK("dark_oak"),
        JUNGLE("jungle"),
        MANGROVE("mangrove"),
        OAK("oak"),
        PALE_OAK("pale_oak"),
        SPRUCE("spruce"),
        WARPED("warped");

        private final String name;

        WoodType(final String name) {
            this.name = name;
        }

        public static @NotNull WoodType getEnumByName(String name) {
            switch(name) {
                case "acacia" -> {return ACACIA;}
                case "bamboo" -> {return BAMBOO;}
                case "cherry" -> {return CHERRY;}
                case "crimson" -> {return CRIMSON;}
                case "dark_oak" -> {return DARK_OAK;}
                case "jungle" -> {return JUNGLE;}
                case "mangrove" -> {return MANGROVE;}
                case "oak" -> {return OAK;}
                case "pale_oak" -> {return PALE_OAK;}
                case "spruce" -> {return SPRUCE;}
                case "warped" -> {return WARPED;}
                default -> {return BIRCH;}
            }
        }

        @Override
        public @NotNull String getSerializedName() {
            return this.name;
        }
    }
}
