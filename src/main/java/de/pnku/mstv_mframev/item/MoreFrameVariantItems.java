package de.pnku.mstv_mframev.item;

import com.mojang.datafixers.util.Pair;
import de.pnku.mstv_mframev.MoreFrameVariants;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.pnku.mstv_mframev.MoreFrameVariants.LOGGER;
import static de.pnku.mstv_mframev.MoreFrameVariants.withModId;


public class MoreFrameVariantItems {

    // Paintings
    public static final Item ACACIA_PAINTING = new MoreFrameVariantItem("acacia", EntityTypes.PAINTING, new Item.Properties());
    public static final Item BAMBOO_PAINTING = new MoreFrameVariantItem("bamboo", EntityTypes.PAINTING, new Item.Properties());
    public static final Item BIRCH_PAINTING = new MoreFrameVariantItem("birch", EntityTypes.PAINTING, new Item.Properties());
    public static final Item CHERRY_PAINTING = new MoreFrameVariantItem("cherry", EntityTypes.PAINTING, new Item.Properties());
    public static final Item CRIMSON_PAINTING = new MoreFrameVariantItem("crimson", EntityTypes.PAINTING, new Item.Properties().fireResistant());
    public static final Item DARK_OAK_PAINTING = new MoreFrameVariantItem("dark_oak", EntityTypes.PAINTING, new Item.Properties());
    public static final Item PALE_OAK_PAINTING = new MoreFrameVariantItem("pale_oak", EntityTypes.PAINTING, new Item.Properties());
    public static final Item JUNGLE_PAINTING = new MoreFrameVariantItem("jungle", EntityTypes.PAINTING, new Item.Properties());
    public static final Item MANGROVE_PAINTING = new MoreFrameVariantItem("mangrove", EntityTypes.PAINTING, new Item.Properties());
    public static final Item OAK_PAINTING = new MoreFrameVariantItem("oak", EntityTypes.PAINTING, new Item.Properties());
    public static final Item SPRUCE_PAINTING = new MoreFrameVariantItem("spruce", EntityTypes.PAINTING, new Item.Properties());
    public static final Item WARPED_PAINTING = new MoreFrameVariantItem("warped", EntityTypes.PAINTING, new Item.Properties().fireResistant());

    // Item Frames
    public static final Item ACACIA_ITEM_FRAME = new MoreFrameVariantItem("acacia", EntityTypes.ITEM_FRAME, new Item.Properties());
    public static final Item BAMBOO_ITEM_FRAME = new MoreFrameVariantItem("bamboo", EntityTypes.ITEM_FRAME, new Item.Properties());
    public static final Item CHERRY_ITEM_FRAME = new MoreFrameVariantItem("cherry", EntityTypes.ITEM_FRAME, new Item.Properties());
    public static final Item CRIMSON_ITEM_FRAME = new MoreFrameVariantItem("crimson", EntityTypes.ITEM_FRAME, new Item.Properties().fireResistant());
    public static final Item DARK_OAK_ITEM_FRAME = new MoreFrameVariantItem("dark_oak", EntityTypes.ITEM_FRAME, new Item.Properties());
    public static final Item PALE_OAK_ITEM_FRAME = new MoreFrameVariantItem("pale_oak", EntityTypes.ITEM_FRAME, new Item.Properties());
    public static final Item JUNGLE_ITEM_FRAME = new MoreFrameVariantItem("jungle", EntityTypes.ITEM_FRAME, new Item.Properties());
    public static final Item MANGROVE_ITEM_FRAME = new MoreFrameVariantItem("mangrove", EntityTypes.ITEM_FRAME, new Item.Properties());
    public static final Item OAK_ITEM_FRAME = new MoreFrameVariantItem("oak", EntityTypes.ITEM_FRAME, new Item.Properties());
    public static final Item SPRUCE_ITEM_FRAME = new MoreFrameVariantItem("spruce", EntityTypes.ITEM_FRAME, new Item.Properties());
    public static final Item WARPED_ITEM_FRAME = new MoreFrameVariantItem("warped", EntityTypes.ITEM_FRAME, new Item.Properties().fireResistant());

    // Glow Item Frames
    public static final Item ACACIA_GLOW_ITEM_FRAME = new MoreFrameVariantItem("acacia", EntityTypes.GLOW_ITEM_FRAME, new Item.Properties());
    public static final Item BAMBOO_GLOW_ITEM_FRAME = new MoreFrameVariantItem("bamboo", EntityTypes.GLOW_ITEM_FRAME, new Item.Properties());
    public static final Item CHERRY_GLOW_ITEM_FRAME = new MoreFrameVariantItem("cherry", EntityTypes.GLOW_ITEM_FRAME, new Item.Properties());
    public static final Item CRIMSON_GLOW_ITEM_FRAME = new MoreFrameVariantItem("crimson", EntityTypes.GLOW_ITEM_FRAME, new Item.Properties().fireResistant());
    public static final Item DARK_OAK_GLOW_ITEM_FRAME = new MoreFrameVariantItem("dark_oak", EntityTypes.GLOW_ITEM_FRAME, new Item.Properties());
    public static final Item PALE_OAK_GLOW_ITEM_FRAME = new MoreFrameVariantItem("pale_oak", EntityTypes.GLOW_ITEM_FRAME, new Item.Properties());
    public static final Item JUNGLE_GLOW_ITEM_FRAME = new MoreFrameVariantItem("jungle", EntityTypes.GLOW_ITEM_FRAME, new Item.Properties());
    public static final Item MANGROVE_GLOW_ITEM_FRAME = new MoreFrameVariantItem("mangrove", EntityTypes.GLOW_ITEM_FRAME, new Item.Properties());
    public static final Item OAK_GLOW_ITEM_FRAME = new MoreFrameVariantItem("oak", EntityTypes.GLOW_ITEM_FRAME, new Item.Properties());
    public static final Item SPRUCE_GLOW_ITEM_FRAME = new MoreFrameVariantItem("spruce", EntityTypes.GLOW_ITEM_FRAME, new Item.Properties());
    public static final Item WARPED_GLOW_ITEM_FRAME = new MoreFrameVariantItem("warped", EntityTypes.GLOW_ITEM_FRAME, new Item.Properties().fireResistant());

    public static final List<Item> more_frame_variants = new ArrayList<>();
    public static final List<Item> more_paintings = new ArrayList<>();
    public static final Map<String, Item> more_paintings_by_wood_type = new HashMap<>();
    public static final List<Item> more_item_frames = new ArrayList<>();
    public static final List<Item> more_glow_item_frames = new ArrayList<>();
    public static final List<Item> more_all_item_frames = new ArrayList<>();
    public static final Map<String, Pair<Item, Item>> more_item_frames_by_wood_type = new HashMap<>();
    public static final Map<Item, Item> more_item_frame_from_glow_map = new HashMap<>();

    public static void registerFrameItems() {
        registerPaintingItem(ACACIA_PAINTING, Items.PAINTING);
        registerPaintingItem(BAMBOO_PAINTING, ACACIA_PAINTING);
        registerPaintingItem(BIRCH_PAINTING, BAMBOO_PAINTING);
        registerPaintingItem(CHERRY_PAINTING, BIRCH_PAINTING);
        registerPaintingItem(CRIMSON_PAINTING, CHERRY_PAINTING);
        registerPaintingItem(DARK_OAK_PAINTING, CRIMSON_PAINTING);
        registerPaintingItem(PALE_OAK_PAINTING, DARK_OAK_PAINTING);
        registerPaintingItem(JUNGLE_PAINTING, PALE_OAK_PAINTING);
        registerPaintingItem(MANGROVE_PAINTING, JUNGLE_PAINTING);
        registerPaintingItem(OAK_PAINTING, MANGROVE_PAINTING);
        registerPaintingItem(SPRUCE_PAINTING, OAK_PAINTING);
        registerPaintingItem(WARPED_PAINTING, SPRUCE_PAINTING);
        registerItemFramesItem(ACACIA_ITEM_FRAME, Items.ITEM_FRAME, ACACIA_GLOW_ITEM_FRAME, Items.GLOW_ITEM_FRAME);
        registerItemFramesItem(BAMBOO_ITEM_FRAME, ACACIA_ITEM_FRAME, BAMBOO_GLOW_ITEM_FRAME, ACACIA_GLOW_ITEM_FRAME);
        registerItemFramesItem(CHERRY_ITEM_FRAME, BAMBOO_ITEM_FRAME, CHERRY_GLOW_ITEM_FRAME, BAMBOO_GLOW_ITEM_FRAME);
        registerItemFramesItem(CRIMSON_ITEM_FRAME, CHERRY_ITEM_FRAME, CRIMSON_GLOW_ITEM_FRAME, CHERRY_GLOW_ITEM_FRAME);
        registerItemFramesItem(DARK_OAK_ITEM_FRAME, CRIMSON_ITEM_FRAME, DARK_OAK_GLOW_ITEM_FRAME, CRIMSON_GLOW_ITEM_FRAME);
        registerItemFramesItem(PALE_OAK_ITEM_FRAME, DARK_OAK_ITEM_FRAME, PALE_OAK_GLOW_ITEM_FRAME, DARK_OAK_GLOW_ITEM_FRAME);
        registerItemFramesItem(JUNGLE_ITEM_FRAME, PALE_OAK_ITEM_FRAME, JUNGLE_GLOW_ITEM_FRAME, PALE_OAK_GLOW_ITEM_FRAME);
        registerItemFramesItem(MANGROVE_ITEM_FRAME, JUNGLE_ITEM_FRAME, MANGROVE_GLOW_ITEM_FRAME, JUNGLE_GLOW_ITEM_FRAME);
        registerItemFramesItem(OAK_ITEM_FRAME, MANGROVE_ITEM_FRAME, OAK_GLOW_ITEM_FRAME, MANGROVE_GLOW_ITEM_FRAME);
        registerItemFramesItem(SPRUCE_ITEM_FRAME, OAK_ITEM_FRAME, SPRUCE_GLOW_ITEM_FRAME, OAK_GLOW_ITEM_FRAME);
        registerItemFramesItem(WARPED_ITEM_FRAME, SPRUCE_ITEM_FRAME, WARPED_GLOW_ITEM_FRAME, SPRUCE_GLOW_ITEM_FRAME);
    }

    private static void registerPaintingItem(Item paintingItem, Item paintingAfter) {
        String paintingName = ((MoreFrameVariantItem) paintingItem).mframevWoodType + "_painting";
        Registry.register(BuiltInRegistries.ITEM, Identifier.tryBuild(MoreFrameVariants.MOD_ID, paintingName), paintingItem);
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> entries.insertAfter(paintingAfter, paintingItem));
        more_paintings.add(paintingItem);
        more_paintings_by_wood_type.put(((MoreFrameVariantItem) paintingItem).mframevWoodType, paintingItem);
        more_frame_variants.add(paintingItem);
        // MoreFrameVariants.LOGGER.info("Registered: " + paintingName);
    }
    private static void registerItemFramesItem(Item itemFrameItem, Item itemFrameAfter, Item glowItemFrameItem, Item glowItemFrameAfter) {
        String itemFrameWoodType = ((MoreFrameVariantItem) itemFrameItem).mframevWoodType;
        String glowItemFrameWoodType = ((MoreFrameVariantItem) glowItemFrameItem).mframevWoodType;
        if (!itemFrameWoodType.equals(glowItemFrameWoodType)) LOGGER.warn("Wood type mismatch between item frame and glow item frame: " + itemFrameWoodType + " <-> " + glowItemFrameWoodType);
        String itemFrameName = itemFrameWoodType + "_item_frame";
        String glowItemFrameName = glowItemFrameWoodType + "_glow_item_frame";
        Registry.register(BuiltInRegistries.ITEM, withModId(itemFrameName), itemFrameItem);
        Registry.register(BuiltInRegistries.ITEM, withModId(glowItemFrameName), glowItemFrameItem);
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> entries.insertAfter(itemFrameAfter, itemFrameItem));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> entries.insertAfter(glowItemFrameAfter, glowItemFrameItem));
        more_item_frames.add(itemFrameItem);
        more_glow_item_frames.add(glowItemFrameItem);
        more_all_item_frames.add(itemFrameItem);
        more_all_item_frames.add(glowItemFrameItem);
        more_frame_variants.add(itemFrameItem);
        more_frame_variants.add(glowItemFrameItem);
        more_item_frames_by_wood_type.putIfAbsent(itemFrameWoodType, Pair.of(itemFrameItem, glowItemFrameItem));
        more_item_frame_from_glow_map.put(glowItemFrameItem, itemFrameItem);
        // MoreFrameVariants.LOGGER.info("Registered: " + itemFrameName + ", " + glowItemFrameName);
    }
}
