package net.digitalpear.pigsteel.common.datagen;

import net.digitalpear.pigsteel.Pigsteel;
import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.digitalpear.pigsteel.init.PigsteelItems;
import net.digitalpear.pigsteel.init.data.ZombifiableBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PigsteelRecipeGenerator extends RecipeGenerator {
    protected PigsteelRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        super(registries, exporter);
    }
    public void offerWaxingRecipes(RecipeExporter exporter) {
        ZombifiableBlockRegistry.getPigsteelWaxingMap().forEach((unwaxed, waxed) -> {
            createShapeless(RecipeCategory.BUILDING_BLOCKS, waxed).input(unwaxed).input(Items.HONEYCOMB).group(getItemPath(waxed)).criterion(hasItem(unwaxed), this.conditionsFromItem(unwaxed)).offerTo(exporter, convertBetween(waxed, Items.HONEYCOMB));
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
                conditionsFromItem(compactItem)).offerTo(exporter, Pigsteel.getModId(Registries.ITEM.getId(baseItem.asItem()).getPath() +"_from_" + Registries.ITEM.getId(compactItem.asItem()).getPath()));

        createShaped(compactingCategory, compactItem)
                .input('#', baseItem)
                .pattern("###")
                .pattern("###")
                .pattern("###").group(compactingGroup)
                .criterion(hasItem(baseItem), this.conditionsFromItem(baseItem)).offerTo(exporter, Pigsteel.getModId(Registries.ITEM.getId(compactItem.asItem()).getPath() +"_from_" + Registries.ITEM.getId(baseItem.asItem()).getPath()));
    }
    public void makeSmeltnBlast(RecipeExporter exporter, List<ItemConvertible> inputs, RecipeCategory category, ItemConvertible output, float experience, int cookingTime, String group){
        for (ItemConvertible item : inputs){
            CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItem(item), category, output, experience, cookingTime).group(group).criterion(hasItem(item), conditionsFromItem(item)).offerTo(exporter, getSmeltingItemPath(output) + "_" + getItemPath(item));
            CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItem(item), category, output, experience, cookingTime/2).group(group).criterion(hasItem(item), conditionsFromItem(item)).offerTo(exporter, getBlastingItemPath(output) + "_" + getItemPath(item));
        }
    }

    @Override
    public void generate() {
        offerReversibleCompactingIngotRecipes(exporter, RecipeCategory.MISC, PigsteelItems.PIGSTEEL_CHUNK, RecipeCategory.BUILDING_BLOCKS, PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK, null, null);

        offerWaxingRecipes(exporter);
        makeSmeltnBlast(exporter, List.of(PigsteelItems.PIGSTEEL_CHUNK), RecipeCategory.MISC, Items.IRON_NUGGET, 0.7f, 200, "iron_nugget");
        makeSmeltnBlast(exporter, List.of(PigsteelBlocks.PORKSLAG), RecipeCategory.MISC, Items.IRON_INGOT, 0.7f, 200, "iron_ingot");

        makeCutRecipes(exporter, PigsteelBlocks.refinedPigsteel.getUnaffectedBlock(), PigsteelBlocks.cutPigsteel.getUnaffectedBlock(), PigsteelBlocks.cutPigsteelStairs.getUnaffectedBlock(), PigsteelBlocks.cutPigsteelSlabs.getUnaffectedBlock());
        makeCutRecipes(exporter, PigsteelBlocks.refinedPigsteel.getInfectedBlock(), PigsteelBlocks.cutPigsteel.getInfectedBlock(), PigsteelBlocks.cutPigsteelStairs.getInfectedBlock(), PigsteelBlocks.cutPigsteelSlabs.getInfectedBlock());
        makeCutRecipes(exporter, PigsteelBlocks.refinedPigsteel.getCorruptedBlock(), PigsteelBlocks.cutPigsteel.getCorruptedBlock(), PigsteelBlocks.cutPigsteelStairs.getCorruptedBlock(), PigsteelBlocks.cutPigsteelSlabs.getCorruptedBlock());
        makeCutRecipes(exporter, PigsteelBlocks.refinedPigsteel.getZombifiedBlock(), PigsteelBlocks.cutPigsteel.getZombifiedBlock(), PigsteelBlocks.cutPigsteelStairs.getZombifiedBlock(), PigsteelBlocks.cutPigsteelSlabs.getZombifiedBlock());

        makeCutRecipes(exporter, PigsteelBlocks.refinedPigsteel.getWaxedUnaffectedBlock(), PigsteelBlocks.cutPigsteel.getWaxedUnaffectedBlock(), PigsteelBlocks.cutPigsteelStairs.getWaxedUnaffectedBlock(), PigsteelBlocks.cutPigsteelSlabs.getWaxedUnaffectedBlock());
        makeCutRecipes(exporter, PigsteelBlocks.refinedPigsteel.getWaxedInfectedBlock(), PigsteelBlocks.cutPigsteel.getWaxedInfectedBlock(), PigsteelBlocks.cutPigsteelStairs.getWaxedInfectedBlock(), PigsteelBlocks.cutPigsteelSlabs.getWaxedInfectedBlock());
        makeCutRecipes(exporter, PigsteelBlocks.refinedPigsteel.getWaxedCorruptedBlock(), PigsteelBlocks.cutPigsteel.getWaxedCorruptedBlock(), PigsteelBlocks.cutPigsteelStairs.getWaxedCorruptedBlock(), PigsteelBlocks.cutPigsteelSlabs.getWaxedCorruptedBlock());
        makeCutRecipes(exporter, PigsteelBlocks.refinedPigsteel.getWaxedZombifiedBlock(), PigsteelBlocks.cutPigsteel.getWaxedZombifiedBlock(), PigsteelBlocks.cutPigsteelStairs.getWaxedZombifiedBlock(), PigsteelBlocks.cutPigsteelSlabs.getWaxedZombifiedBlock());


        makeLantern(exporter, PigsteelBlocks.pigsteelLanterns.getUnaffectedBlock(), Items.TORCH);
        makeLantern(exporter, PigsteelBlocks.pigsteelSoulLanterns.getUnaffectedBlock(), Items.SOUL_TORCH);
        createShaped(RecipeCategory.MISC, PigsteelBlocks.refinedPigsteel.getUnaffectedBlock()).pattern("##").pattern("##").input('#', PigsteelItems.PIGSTEEL_CHUNK).criterion(hasItem(PigsteelItems.PIGSTEEL_CHUNK), conditionsFromItem(PigsteelItems.PIGSTEEL_CHUNK)).offerTo(exporter);
    }
}
