package net.digitalpear.pigsteel.common.datagen;

import net.digitalpear.pigsteel.Pigsteel;
import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.digitalpear.pigsteel.init.PigsteelItems;
import net.digitalpear.pigsteel.init.data.ZombifiableBlockRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.data.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PigsteelRecipeProvider extends FabricRecipeProvider {
    public PigsteelRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            public void offerWaxingRecipes(RecipeExporter exporter) {
                ZombifiableBlockRegistry.getPigsteelWaxingMap().forEach((unwaxed, waxed) -> {
                    createShapeless(RecipeCategory.BUILDING_BLOCKS, waxed).input(unwaxed).input(Items.HONEYCOMB).group(getItemPath(waxed)).criterion(hasItem(unwaxed), this.conditionsFromItem(unwaxed)).offerTo(exporter, Pigsteel.MOD_ID + ":" + convertBetween(waxed, Items.HONEYCOMB));
                });
            }

            public void makeCutRecipes(RecipeExporter exporter, Block base, Block cut, Block stairs, Block slab){
                createCutCopperRecipe(RecipeCategory.BUILDING_BLOCKS, cut, Ingredient.ofItems(base)).criterion(hasItem(cut), conditionsFromItem(cut)).offerTo(exporter);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, cut, base, 4);

                createStairsRecipe(stairs, Ingredient.ofItems(cut)).criterion(hasItem(cut), conditionsFromItem(cut)).offerTo(exporter);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, stairs, cut);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, slab, cut, 2);

                offerSlabRecipe(RecipeCategory.BUILDING_BLOCKS, slab, cut);

                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, stairs, base, 4);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, slab, base, 8);
            }

            public void makeLantern(RecipeExporter exporter, Block output, Item torch){
                createShaped(RecipeCategory.DECORATIONS, output)
                        .input('#', PigsteelItems.PIGSTEEL_CHUNK)
                        .input('X', torch)
                        .pattern("###")
                        .pattern("#X#")
                        .pattern("###")
                        .criterion("has_torch", this.conditionsFromItem(torch))
                        .offerTo(exporter);
            }
            public void offerReversibleCompactingIngotRecipes(RecipeExporter exporter, RecipeCategory reverseCategory, ItemConvertible baseItem, RecipeCategory compactingCategory, ItemConvertible compactItem, @Nullable String compactingGroup, @Nullable String reverseGroup) {
                createShapeless(reverseCategory, baseItem, 9).input(compactItem).group(reverseGroup).criterion(hasItem(compactItem),
                        conditionsFromItem(compactItem)).offerTo(exporter, keyOf(getItemPath(baseItem) +"_from_" + getItemPath(compactItem)));

                createShaped(compactingCategory, compactItem)
                        .input('#', baseItem)
                        .pattern("###")
                        .pattern("###")
                        .pattern("###").group(compactingGroup)
                        .criterion(hasItem(baseItem), this.conditionsFromItem(baseItem)).offerTo(exporter, keyOf(getItemPath(compactItem) +"_from_" + getItemPath(baseItem)));
            }
            public void makeSmeltnBlast(RecipeExporter exporter, List<ItemConvertible> inputs, RecipeCategory category, ItemConvertible output, float experience, int cookingTime, String group){
                for (ItemConvertible item : inputs){
                    CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItem(item), category, output, experience, cookingTime).group(group).criterion(hasItem(item), conditionsFromItem(item)).offerTo(exporter, keyOf(getSmeltingItemPath(output) + "_" + getItemPath(item)));
                    CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItem(item), category, output, experience, cookingTime/2).group(group).criterion(hasItem(item), conditionsFromItem(item)).offerTo(exporter, keyOf(getBlastingItemPath(output) + "_" + getItemPath(item)));
                }
            }

            public RegistryKey<Recipe<?>> keyOf(String name){
                return RegistryKey.of(RegistryKeys.RECIPE, Pigsteel.id(name));
            }

            @Override
            public void generate() {
                offerReversibleCompactingIngotRecipes(exporter, RecipeCategory.MISC, PigsteelItems.PIGSTEEL_CHUNK, RecipeCategory.BUILDING_BLOCKS, PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK, null, null);

                offerWaxingRecipes(exporter);
                makeSmeltnBlast(exporter, List.of(PigsteelItems.PIGSTEEL_CHUNK), RecipeCategory.MISC, Items.IRON_NUGGET, 0.7f, 200, "iron_nugget");
                makeSmeltnBlast(exporter, List.of(PigsteelBlocks.PORKSLAG), RecipeCategory.MISC, Items.IRON_INGOT, 0.7f, 200, "iron_ingot");

                makeCutRecipes(exporter, PigsteelBlocks.REFINED_PIGSTEEL.getUnaffectedBlock(), PigsteelBlocks.CUT_PIGSTEEL.getUnaffectedBlock(), PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getUnaffectedBlock(), PigsteelBlocks.CUT_PIGSTEEL_SLABS.getUnaffectedBlock());
                makeCutRecipes(exporter, PigsteelBlocks.REFINED_PIGSTEEL.getInfectedBlock(), PigsteelBlocks.CUT_PIGSTEEL.getInfectedBlock(), PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getInfectedBlock(), PigsteelBlocks.CUT_PIGSTEEL_SLABS.getInfectedBlock());
                makeCutRecipes(exporter, PigsteelBlocks.REFINED_PIGSTEEL.getCorruptedBlock(), PigsteelBlocks.CUT_PIGSTEEL.getCorruptedBlock(), PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getCorruptedBlock(), PigsteelBlocks.CUT_PIGSTEEL_SLABS.getCorruptedBlock());
                makeCutRecipes(exporter, PigsteelBlocks.REFINED_PIGSTEEL.getZombifiedBlock(), PigsteelBlocks.CUT_PIGSTEEL.getZombifiedBlock(), PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getZombifiedBlock(), PigsteelBlocks.CUT_PIGSTEEL_SLABS.getZombifiedBlock());

                makeCutRecipes(exporter, PigsteelBlocks.REFINED_PIGSTEEL.getWaxedUnaffectedBlock(), PigsteelBlocks.CUT_PIGSTEEL.getWaxedUnaffectedBlock(), PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getWaxedUnaffectedBlock(), PigsteelBlocks.CUT_PIGSTEEL_SLABS.getWaxedUnaffectedBlock());
                makeCutRecipes(exporter, PigsteelBlocks.REFINED_PIGSTEEL.getWaxedInfectedBlock(), PigsteelBlocks.CUT_PIGSTEEL.getWaxedInfectedBlock(), PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getWaxedInfectedBlock(), PigsteelBlocks.CUT_PIGSTEEL_SLABS.getWaxedInfectedBlock());
                makeCutRecipes(exporter, PigsteelBlocks.REFINED_PIGSTEEL.getWaxedCorruptedBlock(), PigsteelBlocks.CUT_PIGSTEEL.getWaxedCorruptedBlock(), PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getWaxedCorruptedBlock(), PigsteelBlocks.CUT_PIGSTEEL_SLABS.getWaxedCorruptedBlock());
                makeCutRecipes(exporter, PigsteelBlocks.REFINED_PIGSTEEL.getWaxedZombifiedBlock(), PigsteelBlocks.CUT_PIGSTEEL.getWaxedZombifiedBlock(), PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getWaxedZombifiedBlock(), PigsteelBlocks.CUT_PIGSTEEL_SLABS.getWaxedZombifiedBlock());


                makeLantern(exporter, PigsteelBlocks.PIGSTEEL_LANTERNS.getUnaffectedBlock(), Items.TORCH);
                makeLantern(exporter, PigsteelBlocks.PIGSTEEL_SOUL_LANTERNS.getUnaffectedBlock(), Items.SOUL_TORCH);
                createShaped(RecipeCategory.MISC, PigsteelBlocks.REFINED_PIGSTEEL.getUnaffectedBlock()).pattern("##").pattern("##").input('#', PigsteelItems.PIGSTEEL_CHUNK).criterion(hasItem(PigsteelItems.PIGSTEEL_CHUNK), conditionsFromItem(PigsteelItems.PIGSTEEL_CHUNK)).offerTo(exporter);
            }
        };
    }


    @Override
    public String getName() {
        return "recipe";
    }
}
