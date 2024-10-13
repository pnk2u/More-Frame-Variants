package de.pnku.mstv_mframev.datagen;

import de.pnku.mstv_base.item.MoreStickVariantItem;
import de.pnku.mstv_mframev.MoreFrameVariants;
import de.pnku.mstv_mframev.item.MoreFrameVariantItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static de.pnku.mstv_mframev.item.MoreFrameVariantItems.*;

public class MoreFrameVariantRecipeGenerator extends FabricRecipeProvider {
    public MoreFrameVariantRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> exporter) {
        for (Item paintingVariant : more_paintings) {
            String planksWood = ((MoreFrameVariantItem) paintingVariant).mframevWoodType;
            Item stickVariant = MoreStickVariantItem.getStickItem(planksWood);
            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, paintingVariant)
                    .group("paintings")
                    .unlockedBy("has_stick", has(stickVariant))
                    .define('/', stickVariant)
                    .define('W', ItemTags.WOOL)
                    .pattern("///")
                    .pattern("/W/")
                    .pattern("///")
                    .save(exporter, MoreFrameVariants.asId(planksWood + "_painting"));
        }
        for (Item itemFrameVariant : more_item_frames) {
            String planksWood = ((MoreFrameVariantItem) itemFrameVariant).mframevWoodType;
            Item stickVariant = MoreStickVariantItem.getStickItem(planksWood);
            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, itemFrameVariant)
                    .group("item_frames")
                    .unlockedBy("has_stick", has(stickVariant))
                    .define('/', stickVariant)
                    .define('L', Items.LEATHER)
                    .pattern("///")
                    .pattern("/L/")
                    .pattern("///")
                    .save(exporter, MoreFrameVariants.asId(planksWood + "_item_frame"));
        }
        for (Item glowItemFrameVariant : more_glow_item_frames) {
            String planksWood = ((MoreFrameVariantItem) glowItemFrameVariant).mframevWoodType;
            Item itemFrameIngredient = more_item_frame_from_glow_map.get(glowItemFrameVariant);
            ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, glowItemFrameVariant)
                    .group("glow_item_frames")
                    .unlockedBy("has_glow_ink_sac", has(Items.GLOW_INK_SAC))
                    .requires(itemFrameIngredient)
                    .requires(Items.GLOW_INK_SAC)
                    .save(exporter, MoreFrameVariants.asId(planksWood + "_glow_item_frame"));
        }
    }
}
