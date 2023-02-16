package net.digitalpear.pigsteel.datagen;

import net.digitalpear.pigsteel.PigsteelMod;
import net.digitalpear.pigsteel.register.PigsteelBlocks;
import net.digitalpear.pigsteel.register.PigsteelItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class PigsteelRecipeGen extends FabricRecipeProvider {
    public PigsteelRecipeGen(FabricDataOutput output) {
        super(output);
    }

    public static void offerWaxingRecipes(Consumer<RecipeJsonProvider> exporter) {
        PigsteelBlocks.PIGSTEEL_WAXING_MAP.forEach((input, output) -> {
            ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output).input(input).input(Items.HONEYCOMB).group(getItemPath(output)).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter, convertBetween(output, Items.HONEYCOMB));
        });
    }

    public static void makeCutRecipes(Consumer<RecipeJsonProvider> exporter, Block base, Block stairs, Block slab){
        RecipeProvider.createStairsRecipe(stairs, Ingredient.ofItems(base)).group("pigsteel_stairs");
        RecipeProvider.createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, stairs, Ingredient.ofItems(base)).group("pigsteel_slabs");

        SingleItemRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(base), RecipeCategory.BUILDING_BLOCKS, slab, 2)
                .criterion(hasItem(base), conditionsFromItem(base))
                .group("pigsteel_slabs")
                .offerTo(exporter, convertBetween(slab, base) + "_stonecutting");

        SingleItemRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(base), RecipeCategory.BUILDING_BLOCKS, stairs, 1)
                .criterion(hasItem(base), conditionsFromItem(base))
                .group("pigsteel_stairs")
                .offerTo(exporter, convertBetween(stairs, base) + "_stonecutting");
    }

    public static void makeLantern(Consumer<RecipeJsonProvider> exporter, Block output, Item torch){
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, output)
                .input('#', PigsteelItems.PIGSTEEL_NUGGET)
                .input('X', torch)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .group("pigsteel_lantern")
                .criterion("has_torch", conditionsFromItem(torch))
                .offerTo(exporter);
    }
    public static void offerReversibleCompactingIngotRecipes(Consumer<RecipeJsonProvider> exporter, RecipeCategory reverseCategory, ItemConvertible baseItem, RecipeCategory compactingCategory, ItemConvertible compactItem, @Nullable String compactingGroup, @Nullable String reverseGroup) {
        ShapelessRecipeJsonBuilder.create(reverseCategory, baseItem, 9).input(compactItem).group(reverseGroup).criterion(hasItem(compactItem),
                conditionsFromItem(compactItem)).offerTo(exporter, new Identifier(PigsteelMod.MOD_ID, Registries.ITEM.getId(baseItem.asItem()).getPath()));

        ShapedRecipeJsonBuilder.create(compactingCategory, compactItem)
                .input('#', baseItem)
                .pattern("###")
                .pattern("###")
                .pattern("###").group(compactingGroup)
                .criterion(hasItem(baseItem), conditionsFromItem(baseItem)).offerTo(exporter, new Identifier(PigsteelMod.MOD_ID, Registries.ITEM.getId(compactItem.asItem()).getPath() +"_from_" + Registries.ITEM.getId(baseItem.asItem()).getPath()));
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerWaxingRecipes(exporter);

        RecipeProvider.offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, PigsteelItems.PIGSTEEL_INGOT, RecipeCategory.BUILDING_BLOCKS, PigsteelBlocks.PIGSTEEL_BLOCK);
        offerReversibleCompactingIngotRecipes(exporter, RecipeCategory.MISC, PigsteelItems.PIGSTEEL_NUGGET, RecipeCategory.MISC, PigsteelItems.PIGSTEEL_INGOT, null, null);


        RecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, PigsteelBlocks.CUT_PIGSTEEL, PigsteelBlocks.PIGSTEEL_BLOCK, 4);
        RecipeProvider.createCutCopperRecipe(RecipeCategory.BUILDING_BLOCKS, PigsteelBlocks.CUT_PIGSTEEL, Ingredient.ofItems(PigsteelBlocks.PIGSTEEL_BLOCK));

        makeCutRecipes(exporter, PigsteelBlocks.CUT_PIGSTEEL, PigsteelBlocks.CUT_PIGSTEEL_STAIRS, PigsteelBlocks.CUT_PIGSTEEL_SLAB);
        makeCutRecipes(exporter, PigsteelBlocks.INFECTED_CUT_PIGSTEEL, PigsteelBlocks.INFECTED_CUT_PIGSTEEL_STAIRS, PigsteelBlocks.INFECTED_CUT_PIGSTEEL_SLAB);
        makeCutRecipes(exporter, PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL, PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_STAIRS, PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_SLAB);
        makeCutRecipes(exporter, PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL, PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_STAIRS, PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_SLAB);

        makeLantern(exporter, PigsteelBlocks.PIGSTEEL_LANTERN, Items.TORCH);
        makeLantern(exporter, PigsteelBlocks.PIGSTEEL_SOUL_LANTERN, Items.SOUL_TORCH);
    }
}
