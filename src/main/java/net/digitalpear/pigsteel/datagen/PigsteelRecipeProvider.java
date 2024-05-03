package net.digitalpear.pigsteel.datagen;

import net.digitalpear.pigsteel.Pigsteel;
import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.digitalpear.pigsteel.init.PigsteelItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PigsteelRecipeProvider extends FabricRecipeProvider {
    public PigsteelRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    public static void offerWaxingRecipes(RecipeExporter exporter) {
        PigsteelBlocks.PIGSTEEL_WAXING_MAP.forEach((input, output) ->
                ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output)
                        .input(input)
                        .input(Items.HONEYCOMB)
                        .group(getItemPath(output))
                        .criterion(hasItem(input), conditionsFromItem(input))
                        .offerTo(exporter, convertBetween(output, Items.HONEYCOMB)));
    }

    public static void makeCutRecipes(RecipeExporter exporter, Block base, Block cut, Block stairs, Block slab){

        RecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, cut, base, 4);
        RecipeProvider.createCutCopperRecipe(RecipeCategory.BUILDING_BLOCKS, cut, Ingredient.ofItems(base)).criterion(hasItem(base), conditionsFromItem(base)).offerTo(exporter);

        RecipeProvider.createStairsRecipe(stairs, Ingredient.ofItems(cut)).criterion(hasItem(cut), conditionsFromItem(cut)).offerTo(exporter);
        RecipeProvider.createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, slab, Ingredient.ofItems(cut)).criterion(hasItem(cut), conditionsFromItem(cut)).offerTo(exporter);

        RecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stairs, base, 4);
        RecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, slab, base, 8);

        RecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stairs, cut);
        RecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, slab, cut, 2);
    }

    public static void makeLantern(RecipeExporter exporter, Block output, Item torch){
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, output)
                .input('#', PigsteelItems.PIGSTEEL_CHUNK)
                .input('X', torch)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .criterion("has_torch", conditionsFromItem(torch))
                .offerTo(exporter);
    }
    public static void offerReversibleCompactingIngotRecipes(RecipeExporter exporter, RecipeCategory reverseCategory, ItemConvertible baseItem, RecipeCategory compactingCategory, ItemConvertible compactItem, @Nullable String compactingGroup, @Nullable String reverseGroup) {
        ShapelessRecipeJsonBuilder.create(reverseCategory, baseItem, 9).input(compactItem).group(reverseGroup).criterion(hasItem(compactItem),
                conditionsFromItem(compactItem)).offerTo(exporter, new Identifier(Pigsteel.MOD_ID, Registries.ITEM.getId(baseItem.asItem()).getPath() +"_from_" + Registries.ITEM.getId(compactItem.asItem()).getPath()));

        ShapedRecipeJsonBuilder.create(compactingCategory, compactItem)
                .input('#', baseItem)
                .pattern("###")
                .pattern("###")
                .pattern("###").group(compactingGroup)
                .criterion(hasItem(baseItem), conditionsFromItem(baseItem)).offerTo(exporter, new Identifier(Pigsteel.MOD_ID, Registries.ITEM.getId(compactItem.asItem()).getPath() +"_from_" + Registries.ITEM.getId(baseItem.asItem()).getPath()));
    }
    public static void makeSmeltnBlast(RecipeExporter exporter, List<ItemConvertible> inputs, RecipeCategory category, ItemConvertible output, float experience, int cookingTime, String group){
        RecipeProvider.offerSmelting(exporter, inputs, category, output, experience, cookingTime, group);
        RecipeProvider.offerBlasting(exporter, inputs, category, output, experience, cookingTime/2, group);
    }




    @Override
    public void generate(RecipeExporter exporter) {
        offerReversibleCompactingIngotRecipes(exporter, RecipeCategory.MISC, PigsteelItems.PIGSTEEL_CHUNK, RecipeCategory.BUILDING_BLOCKS, PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK, null, null);

        offerWaxingRecipes(exporter);
        makeSmeltnBlast(exporter, List.of(PigsteelItems.PIGSTEEL_CHUNK), RecipeCategory.MISC, Items.IRON_NUGGET, 0.7f, 200, "iron_nugget");
        makeSmeltnBlast(exporter, List.of(PigsteelBlocks.PORKSLAG), RecipeCategory.MISC, Items.IRON_INGOT, 0.7f, 200, "iron_ingot");

        makeCutRecipes(exporter, PigsteelBlocks.refinedPigsteel.getUnaffectedBlock(), PigsteelBlocks.cutPigsteel.getUnaffectedBlock(), PigsteelBlocks.CUT_PIGSTEEL_STAIRS, PigsteelBlocks.cutPigsteelSlabs.getUnaffectedBlock());
        makeCutRecipes(exporter, PigsteelBlocks.refinedPigsteel.getInfectedBlock(), PigsteelBlocks.cutPigsteel.getInfectedBlock(), PigsteelBlocks.INFECTED_CUT_PIGSTEEL_STAIRS, PigsteelBlocks.cutPigsteelSlabs.getInfectedBlock());
        makeCutRecipes(exporter, PigsteelBlocks.refinedPigsteel.getCorruptedBlock(), PigsteelBlocks.cutPigsteel.getCorruptedBlock(), PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_STAIRS, PigsteelBlocks.cutPigsteelSlabs.getCorruptedBlock());
        makeCutRecipes(exporter, PigsteelBlocks.refinedPigsteel.getZombifiedBlock(), PigsteelBlocks.cutPigsteel.getZombifiedBlock(), PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_STAIRS, PigsteelBlocks.cutPigsteelSlabs.getZombifiedBlock());

        makeCutRecipes(exporter, PigsteelBlocks.refinedPigsteel.getWaxedUnaffectedBlock(), PigsteelBlocks.cutPigsteel.getWaxedUnaffectedBlock(), PigsteelBlocks.WAXED_CUT_PIGSTEEL_STAIRS, PigsteelBlocks.cutPigsteelSlabs.getWaxedUnaffectedBlock());
        makeCutRecipes(exporter, PigsteelBlocks.refinedPigsteel.getWaxedInfectedBlock(), PigsteelBlocks.cutPigsteel.getWaxedInfectedBlock(), PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL_STAIRS, PigsteelBlocks.cutPigsteelSlabs.getWaxedInfectedBlock());
        makeCutRecipes(exporter, PigsteelBlocks.refinedPigsteel.getWaxedCorruptedBlock(), PigsteelBlocks.cutPigsteel.getWaxedCorruptedBlock(), PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS, PigsteelBlocks.cutPigsteelSlabs.getWaxedCorruptedBlock());
        makeCutRecipes(exporter, PigsteelBlocks.refinedPigsteel.getWaxedZombifiedBlock(), PigsteelBlocks.cutPigsteel.getWaxedZombifiedBlock(), PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS, PigsteelBlocks.cutPigsteelSlabs.getWaxedZombifiedBlock());


        makeLantern(exporter, PigsteelBlocks.pigsteelLanterns.getUnaffectedBlock(), Items.TORCH);
        makeLantern(exporter, PigsteelBlocks.pigsteelSoulLanterns.getUnaffectedBlock(), Items.SOUL_TORCH);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, PigsteelBlocks.refinedPigsteel.getUnaffectedBlock()).pattern("##").pattern("##").input('#', PigsteelItems.PIGSTEEL_CHUNK).criterion(hasItem(PigsteelItems.PIGSTEEL_CHUNK), conditionsFromItem(PigsteelItems.PIGSTEEL_CHUNK)).offerTo(exporter);
    }


}
