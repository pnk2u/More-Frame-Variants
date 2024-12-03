package de.pnku.mstv_mframev.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.*;
import net.minecraft.core.Registry;
import de.pnku.mstv_mframev.MoreFrameVariants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MoreFrameVariantItems {

    // Paintings
    public static final Item ACACIA_PAINTING = new MoreFrameVariantItem("acacia", EntityType.PAINTING, new Item.Properties());
    public static final Item BAMBOO_PAINTING = new MoreFrameVariantItem("bamboo", EntityType.PAINTING, new Item.Properties());
    public static final Item BIRCH_PAINTING = new MoreFrameVariantItem("birch", EntityType.PAINTING, new Item.Properties());
    public static final Item CHERRY_PAINTING = new MoreFrameVariantItem("cherry", EntityType.PAINTING, new Item.Properties());
    public static final Item CRIMSON_PAINTING = new MoreFrameVariantItem("crimson", EntityType.PAINTING, new Item.Properties().fireResistant());
    public static final Item DARK_OAK_PAINTING = new MoreFrameVariantItem("dark_oak", EntityType.PAINTING, new Item.Properties());
    public static final Item JUNGLE_PAINTING = new MoreFrameVariantItem("jungle", EntityType.PAINTING, new Item.Properties());
    public static final Item MANGROVE_PAINTING = new MoreFrameVariantItem("mangrove", EntityType.PAINTING, new Item.Properties());
    public static final Item OAK_PAINTING = new MoreFrameVariantItem("oak", EntityType.PAINTING, new Item.Properties());
    public static final Item SPRUCE_PAINTING = new MoreFrameVariantItem("spruce", EntityType.PAINTING, new Item.Properties());
    public static final Item WARPED_PAINTING = new MoreFrameVariantItem("warped", EntityType.PAINTING, new Item.Properties().fireResistant());

    // Item Frames
    public static final Item ACACIA_ITEM_FRAME = new MoreFrameVariantItem("acacia", EntityType.ITEM_FRAME, new Item.Properties());
    public static final Item BAMBOO_ITEM_FRAME = new MoreFrameVariantItem("bamboo", EntityType.ITEM_FRAME, new Item.Properties());
    public static final Item CHERRY_ITEM_FRAME = new MoreFrameVariantItem("cherry", EntityType.ITEM_FRAME, new Item.Properties());
    public static final Item CRIMSON_ITEM_FRAME = new MoreFrameVariantItem("crimson", EntityType.ITEM_FRAME, new Item.Properties().fireResistant());
    public static final Item DARK_OAK_ITEM_FRAME = new MoreFrameVariantItem("dark_oak", EntityType.ITEM_FRAME, new Item.Properties());
    public static final Item JUNGLE_ITEM_FRAME = new MoreFrameVariantItem("jungle", EntityType.ITEM_FRAME, new Item.Properties());
    public static final Item MANGROVE_ITEM_FRAME = new MoreFrameVariantItem("mangrove", EntityType.ITEM_FRAME, new Item.Properties());
    public static final Item OAK_ITEM_FRAME = new MoreFrameVariantItem("oak", EntityType.ITEM_FRAME, new Item.Properties());
    public static final Item SPRUCE_ITEM_FRAME = new MoreFrameVariantItem("spruce", EntityType.ITEM_FRAME, new Item.Properties());
    public static final Item WARPED_ITEM_FRAME = new MoreFrameVariantItem("warped", EntityType.ITEM_FRAME, new Item.Properties().fireResistant());

    // Glow Item Frames
    public static final Item ACACIA_GLOW_ITEM_FRAME = new MoreFrameVariantItem("acacia", EntityType.GLOW_ITEM_FRAME, new Item.Properties());
    public static final Item BAMBOO_GLOW_ITEM_FRAME = new MoreFrameVariantItem("bamboo", EntityType.GLOW_ITEM_FRAME, new Item.Properties());
    public static final Item CHERRY_GLOW_ITEM_FRAME = new MoreFrameVariantItem("cherry", EntityType.GLOW_ITEM_FRAME, new Item.Properties());
    public static final Item CRIMSON_GLOW_ITEM_FRAME = new MoreFrameVariantItem("crimson", EntityType.GLOW_ITEM_FRAME, new Item.Properties().fireResistant());
    public static final Item DARK_OAK_GLOW_ITEM_FRAME = new MoreFrameVariantItem("dark_oak", EntityType.GLOW_ITEM_FRAME, new Item.Properties());
    public static final Item JUNGLE_GLOW_ITEM_FRAME = new MoreFrameVariantItem("jungle", EntityType.GLOW_ITEM_FRAME, new Item.Properties());
    public static final Item MANGROVE_GLOW_ITEM_FRAME = new MoreFrameVariantItem("mangrove", EntityType.GLOW_ITEM_FRAME, new Item.Properties());
    public static final Item OAK_GLOW_ITEM_FRAME = new MoreFrameVariantItem("oak", EntityType.GLOW_ITEM_FRAME, new Item.Properties());
    public static final Item SPRUCE_GLOW_ITEM_FRAME = new MoreFrameVariantItem("spruce", EntityType.GLOW_ITEM_FRAME, new Item.Properties());
    public static final Item WARPED_GLOW_ITEM_FRAME = new MoreFrameVariantItem("warped", EntityType.GLOW_ITEM_FRAME, new Item.Properties().fireResistant());

    public static final List<Item> more_frame_variants = new ArrayList<>();
    public static final List<Item> more_paintings = new ArrayList<>();
    public static final List<Item> more_item_frames = new ArrayList<>();
    public static final List<Item> more_glow_item_frames = new ArrayList<>();
    public static final List<Item> more_all_item_frames = new ArrayList<>();
    public static final Map<Item, Item> more_item_frame_from_glow_map = new HashMap<>();

    public static void registerFrameItems() {
        registerPaintingItem(ACACIA_PAINTING, Items.PAINTING);
        registerPaintingItem(BAMBOO_PAINTING, ACACIA_PAINTING);
        registerPaintingItem(BIRCH_PAINTING, BAMBOO_PAINTING);
        registerPaintingItem(CHERRY_PAINTING, BIRCH_PAINTING);
        registerPaintingItem(CRIMSON_PAINTING, CHERRY_PAINTING);
        registerPaintingItem(DARK_OAK_PAINTING, CRIMSON_PAINTING);
        registerPaintingItem(JUNGLE_PAINTING, DARK_OAK_PAINTING);
        registerPaintingItem(MANGROVE_PAINTING, JUNGLE_PAINTING);
        registerPaintingItem(OAK_PAINTING, MANGROVE_PAINTING);
        registerPaintingItem(SPRUCE_PAINTING, OAK_PAINTING);
        registerPaintingItem(WARPED_PAINTING, SPRUCE_PAINTING);
        registerItemFramesItem(ACACIA_ITEM_FRAME, Items.ITEM_FRAME, ACACIA_GLOW_ITEM_FRAME, Items.GLOW_ITEM_FRAME);
        registerItemFramesItem(BAMBOO_ITEM_FRAME, ACACIA_ITEM_FRAME, BAMBOO_GLOW_ITEM_FRAME, ACACIA_GLOW_ITEM_FRAME);
        registerItemFramesItem(CHERRY_ITEM_FRAME, BAMBOO_ITEM_FRAME, CHERRY_GLOW_ITEM_FRAME, BAMBOO_GLOW_ITEM_FRAME);
        registerItemFramesItem(CRIMSON_ITEM_FRAME, CHERRY_ITEM_FRAME, CRIMSON_GLOW_ITEM_FRAME, CHERRY_GLOW_ITEM_FRAME);
        registerItemFramesItem(DARK_OAK_ITEM_FRAME, CRIMSON_ITEM_FRAME, DARK_OAK_GLOW_ITEM_FRAME, CRIMSON_GLOW_ITEM_FRAME);
        registerItemFramesItem(JUNGLE_ITEM_FRAME, DARK_OAK_ITEM_FRAME, JUNGLE_GLOW_ITEM_FRAME, DARK_OAK_GLOW_ITEM_FRAME);
        registerItemFramesItem(MANGROVE_ITEM_FRAME, JUNGLE_ITEM_FRAME, MANGROVE_GLOW_ITEM_FRAME, JUNGLE_GLOW_ITEM_FRAME);
        registerItemFramesItem(OAK_ITEM_FRAME, MANGROVE_ITEM_FRAME, OAK_GLOW_ITEM_FRAME, MANGROVE_GLOW_ITEM_FRAME);
        registerItemFramesItem(SPRUCE_ITEM_FRAME, OAK_ITEM_FRAME, SPRUCE_GLOW_ITEM_FRAME, OAK_GLOW_ITEM_FRAME);
        registerItemFramesItem(WARPED_ITEM_FRAME, SPRUCE_ITEM_FRAME, WARPED_GLOW_ITEM_FRAME, SPRUCE_GLOW_ITEM_FRAME);
    }

    private static void registerPaintingItem(Item paintingItem, Item paintingAfter) {
        String paintingName = ((MoreFrameVariantItem) paintingItem).mframevWoodType + "_painting";
        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.tryBuild(MoreFrameVariants.MOD_ID, paintingName), paintingItem);
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> entries.addAfter(paintingAfter, paintingItem));
        more_paintings.add(paintingItem);
        more_frame_variants.add(paintingItem);
        MoreFrameVariants.LOGGER.info("Registered: " + paintingName);
    }
    private static void registerItemFramesItem(Item itemFrameItem, Item itemFrameAfter, Item glowItemFrameItem, Item glowItemFrameAfter) {
        String itemFrameName = ((MoreFrameVariantItem) itemFrameItem).mframevWoodType + "_item_frame";
        String glowItemFrameName = ((MoreFrameVariantItem) glowItemFrameItem).mframevWoodType + "_glow_item_frame";
        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.tryBuild(MoreFrameVariants.MOD_ID, itemFrameName), itemFrameItem);
        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.tryBuild(MoreFrameVariants.MOD_ID, glowItemFrameName), glowItemFrameItem);
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> entries.addAfter(itemFrameAfter, itemFrameItem));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> entries.addAfter(glowItemFrameAfter, glowItemFrameItem));
        more_item_frames.add(itemFrameItem);
        more_glow_item_frames.add(glowItemFrameItem);
        more_all_item_frames.add(itemFrameItem);
        more_all_item_frames.add(glowItemFrameItem);
        more_frame_variants.add(itemFrameItem);
        more_frame_variants.add(glowItemFrameItem);
        more_item_frame_from_glow_map.put(glowItemFrameItem, itemFrameItem);
        MoreFrameVariants.LOGGER.info("Registered: " + itemFrameName + ", " + glowItemFrameName);
    }
}
