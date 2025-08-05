package net.digitalpear.pigsteel.common.datagen;

import net.digitalpear.pigsteel.Pigsteel;
import net.digitalpear.pigsteel.common.blocks.Zombifiable;
import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.digitalpear.pigsteel.init.PigsteelItems;
import net.digitalpear.pigsteel.init.data.PigsteelBlockFamilies;
import net.digitalpear.pigsteel.init.data.ZombifiableBlockRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.data.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.resource.featuretoggle.FeatureFlag;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.resource.featuretoggle.FeatureSet;
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



            public void cutRecipeIterator(ZombifiableBlockRegistry baseRegistry, ZombifiableBlockRegistry cutRegistry, ZombifiableBlockRegistry stairsRegistry, ZombifiableBlockRegistry slabRegistry, ZombifiableBlockRegistry chiseledRegistry){
                for (Zombifiable.ZombificationLevel i : Zombifiable.ZombificationLevel.values()){
                    makeCutRecipes(baseRegistry.getBlockFromLevel(i), cutRegistry.getBlockFromLevel(i), stairsRegistry.getBlockFromLevel(i), slabRegistry.getBlockFromLevel(i), chiseledRegistry.getBlockFromLevel(i));
                    makeCutRecipes(baseRegistry.getWaxedBlockFromLevel(i), cutRegistry.getBlockFromLevel(i), stairsRegistry.getWaxedBlockFromLevel(i), slabRegistry.getWaxedBlockFromLevel(i), chiseledRegistry.getWaxedBlockFromLevel(i));
                }
            }

            public void makeCutRecipes(Block base, Block cut, Block stairs, Block slab, Block chiseledBlock){
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, cut, base, 4);

                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, stairs, cut);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, slab, cut, 2);

                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, stairs, base, 4);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, slab, base, 8);

                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, chiseledBlock, base, 4);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, chiseledBlock, cut);
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
                for (Zombifiable.ZombificationLevel i : Zombifiable.ZombificationLevel.values()){
                    ShapedRecipeJsonBuilder.create(this.registries.getOrThrow(RegistryKeys.ITEM), RecipeCategory.COMBAT, PigsteelBlocks.PIGSTEEL_BARRELS.getBlockFromLevel(i)).input('c', PigsteelBlocks.CUT_PIGSTEEL.getBlockFromLevel(i)).input('s', PigsteelBlocks.CUT_PIGSTEEL_SLABS.getBlockFromLevel(i)).pattern("csc").pattern("c c").pattern("csc").criterion(hasItem(PigsteelBlocks.CUT_PIGSTEEL.getBlockFromLevel(i)), conditionsFromItem(PigsteelBlocks.CUT_PIGSTEEL.getBlockFromLevel(i))).offerTo(exporter);
                    ShapedRecipeJsonBuilder.create(this.registries.getOrThrow(RegistryKeys.ITEM), RecipeCategory.COMBAT, PigsteelBlocks.PIGSTEEL_BARRELS.getWaxedBlockFromLevel(i)).input('c', PigsteelBlocks.CUT_PIGSTEEL.getWaxedBlockFromLevel(i)).input('s', PigsteelBlocks.CUT_PIGSTEEL_SLABS.getWaxedBlockFromLevel(i)).pattern("csc").pattern("c c").pattern("csc").criterion(hasItem(PigsteelBlocks.CUT_PIGSTEEL.getWaxedBlockFromLevel(i)), conditionsFromItem(PigsteelBlocks.CUT_PIGSTEEL.getWaxedBlockFromLevel(i))).offerTo(exporter);
                }

                generateFamily(PigsteelBlockFamilies.PIGSTEEL, FeatureFlags.VANILLA_FEATURES);
                generateFamily(PigsteelBlockFamilies.INFECTED_PIGSTEEL, FeatureFlags.VANILLA_FEATURES);
                generateFamily(PigsteelBlockFamilies.CORRUPTED_PIGSTEEL, FeatureFlags.VANILLA_FEATURES);
                generateFamily(PigsteelBlockFamilies.ZOMBIFIED_PIGSTEEL, FeatureFlags.VANILLA_FEATURES);
                generateFamily(PigsteelBlockFamilies.WAXED_PIGSTEEL, FeatureFlags.VANILLA_FEATURES);
                generateFamily(PigsteelBlockFamilies.WAXED_INFECTED_PIGSTEEL, FeatureFlags.VANILLA_FEATURES);
                generateFamily(PigsteelBlockFamilies.WAXED_CORRUPTED_PIGSTEEL, FeatureFlags.VANILLA_FEATURES);
                generateFamily(PigsteelBlockFamilies.WAXED_ZOMBIFIED_PIGSTEEL, FeatureFlags.VANILLA_FEATURES);

                offerReversibleCompactingIngotRecipes(exporter, RecipeCategory.MISC, PigsteelItems.PIGSTEEL_CHUNK, RecipeCategory.BUILDING_BLOCKS, PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK, null, null);

                offerWaxingRecipes(exporter);
                makeSmeltnBlast(exporter, List.of(PigsteelItems.PIGSTEEL_CHUNK), RecipeCategory.MISC, Items.IRON_NUGGET, 0.7f, 200, "iron_nugget");
                makeSmeltnBlast(exporter, List.of(PigsteelBlocks.PORKSLAG), RecipeCategory.MISC, Items.IRON_INGOT, 0.7f, 200, "iron_ingot");

                cutRecipeIterator(PigsteelBlocks.REFINED_PIGSTEEL, PigsteelBlocks.CUT_PIGSTEEL, PigsteelBlocks.CUT_PIGSTEEL_STAIRS, PigsteelBlocks.CUT_PIGSTEEL_SLABS, PigsteelBlocks.CHISELED_PIGSTEEL);


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
