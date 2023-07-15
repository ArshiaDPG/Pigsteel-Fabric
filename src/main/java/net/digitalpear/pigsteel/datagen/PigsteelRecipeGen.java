package net.digitalpear.pigsteel.datagen;

import net.digitalpear.pigsteel.Pigsteel;
import net.digitalpear.pigsteel.register.PigsteelBlocks;
import net.digitalpear.pigsteel.register.PigsteelItems;
import net.digitalpear.pigsteel.register.tags.PigsteelItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;
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

    public static void makeCutRecipes(Consumer<RecipeJsonProvider> exporter, Block base, Block cut, Block stairs, Block slab){

        RecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, cut, base, 4);
        RecipeProvider.createCutCopperRecipe(RecipeCategory.BUILDING_BLOCKS, cut, Ingredient.ofItems(base));

        RecipeProvider.createStairsRecipe(stairs, Ingredient.ofItems(cut));
        RecipeProvider.createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, slab, Ingredient.ofItems(cut));

        RecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stairs, base, 4);
        RecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, slab, base, 8);

        RecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stairs, cut);
        RecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, slab, cut, 2);

    }

    public static void makeVanillaRecipes(Consumer<RecipeJsonProvider> exporter){
        ShapedRecipeJsonBuilder.create(RecipeCategory.TRANSPORTATION, Blocks.ACTIVATOR_RAIL, 6).input('#', Blocks.REDSTONE_TORCH).input('S', Items.STICK).input('X', PigsteelItemTags.C_IRON_INGOTS).pattern("XSX").pattern("X#X").pattern("XSX").criterion("has_rail", conditionsFromItem(Blocks.RAIL)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, Blocks.ANVIL).input('I', PigsteelItemTags.C_IRON_BLOCKS).input('i', PigsteelItemTags.C_IRON_INGOTS).pattern("III").pattern(" i ").pattern("iii").criterion("has_iron_block", conditionsFromTag(PigsteelItemTags.C_IRON_BLOCKS)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.BUCKET).input('#', PigsteelItemTags.C_IRON_INGOTS).pattern("# #").pattern(" # ").criterion("has_iron_ingot", conditionsFromTag(PigsteelItemTags.C_IRON_INGOTS)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BREWING, Blocks.CAULDRON).input('#', PigsteelItemTags.C_IRON_INGOTS).pattern("# #").pattern("# #").pattern("###").criterion("has_water_bucket", conditionsFromItem(Items.WATER_BUCKET)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, Items.COMPASS).input('#', PigsteelItemTags.C_IRON_INGOTS).input('X', Items.REDSTONE).pattern(" # ").pattern("#X#").pattern(" # ").criterion("has_redstone", conditionsFromItem(Items.REDSTONE)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.CROSSBOW).input('~', Items.STRING).input('#', Items.STICK).input('&', PigsteelItemTags.C_IRON_INGOTS).input('$', Blocks.TRIPWIRE_HOOK).pattern("#&#").pattern("~$~").pattern(" # ").criterion("has_string", conditionsFromItem(Items.STRING)).criterion("has_iron_ingot", conditionsFromTag(PigsteelItemTags.C_IRON_INGOTS)).criterion("has_tripwire_hook", conditionsFromItem(Blocks.TRIPWIRE_HOOK)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TRANSPORTATION, Blocks.DETECTOR_RAIL, 6).input('R', Items.REDSTONE).input('#', Blocks.STONE_PRESSURE_PLATE).input('X', PigsteelItemTags.C_IRON_INGOTS).pattern("X X").pattern("X#X").pattern("XRX").criterion("has_rail", conditionsFromItem(Blocks.RAIL)).offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.TOOLS, Items.FLINT_AND_STEEL).input(PigsteelItemTags.C_IRON_INGOTS).input(Items.FLINT).criterion("has_flint", conditionsFromItem(Items.FLINT)).criterion("has_obsidian", conditionsFromItem(Blocks.OBSIDIAN)).offerTo(exporter);
        offerPressurePlateRecipe(exporter, Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE, PigsteelItemTags.C_IRON_INGOTS);
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, Blocks.HOPPER).input('C', Blocks.CHEST).input('I', PigsteelItemTags.C_IRON_INGOTS).pattern("I I").pattern("ICI").pattern(" I ").criterion("has_iron_ingot", conditionsFromTag(PigsteelItemTags.C_IRON_INGOTS)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, Items.IRON_AXE).input('#', Items.STICK).input('X', PigsteelItemTags.C_IRON_INGOTS).pattern("XX").pattern("X#").pattern(" #").criterion("has_iron_ingot", conditionsFromTag(PigsteelItemTags.C_IRON_INGOTS)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, Blocks.IRON_BARS, 16).input('#', PigsteelItemTags.C_IRON_INGOTS).pattern("###").pattern("###").criterion("has_iron_ingot", conditionsFromTag(PigsteelItemTags.C_IRON_INGOTS)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.IRON_BOOTS).input('X', PigsteelItemTags.C_IRON_INGOTS).pattern("X X").pattern("X X").criterion("has_iron_ingot", conditionsFromTag(PigsteelItemTags.C_IRON_INGOTS)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.IRON_CHESTPLATE).input('X', PigsteelItemTags.C_IRON_INGOTS).pattern("X X").pattern("XXX").pattern("XXX").criterion("has_iron_ingot", conditionsFromTag(PigsteelItemTags.C_IRON_INGOTS)).offerTo(exporter);
        createDoorRecipe(Blocks.IRON_DOOR, Ingredient.fromTag(PigsteelItemTags.C_IRON_INGOTS)).criterion("has_iron_ingot", conditionsFromTag(PigsteelItemTags.C_IRON_INGOTS)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.IRON_HELMET).input('X', PigsteelItemTags.C_IRON_INGOTS).pattern("XXX").pattern("X X").criterion("has_iron_ingot", conditionsFromTag(PigsteelItemTags.C_IRON_INGOTS)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, Items.IRON_HOE).input('#', Items.STICK).input('X', PigsteelItemTags.C_IRON_INGOTS).pattern("XX").pattern(" #").pattern(" #").criterion("has_iron_ingot", conditionsFromTag(PigsteelItemTags.C_IRON_INGOTS)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.IRON_LEGGINGS).input('X', PigsteelItemTags.C_IRON_INGOTS).pattern("XXX").pattern("X X").pattern("X X").criterion("has_iron_ingot", conditionsFromTag(PigsteelItemTags.C_IRON_INGOTS)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, Items.IRON_PICKAXE).input('#', Items.STICK).input('X', PigsteelItemTags.C_IRON_INGOTS).pattern("XXX").pattern(" # ").pattern(" # ").criterion("has_iron_ingot", conditionsFromTag(PigsteelItemTags.C_IRON_INGOTS)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, Items.IRON_SHOVEL).input('#', Items.STICK).input('X', PigsteelItemTags.C_IRON_INGOTS).pattern("X").pattern("#").pattern("#").criterion("has_iron_ingot", conditionsFromTag(PigsteelItemTags.C_IRON_INGOTS)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.IRON_SWORD).input('#', Items.STICK).input('X', PigsteelItemTags.C_IRON_INGOTS).pattern("X").pattern("X").pattern("#").criterion("has_iron_ingot", conditionsFromTag(PigsteelItemTags.C_IRON_INGOTS)).offerTo(exporter);
        offer2x2CompactingRecipe(exporter, RecipeCategory.REDSTONE, Blocks.IRON_TRAPDOOR, PigsteelItemTags.C_IRON_INGOTS);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TRANSPORTATION, Items.MINECART).input('#', PigsteelItemTags.C_IRON_INGOTS).pattern("# #").pattern("###").criterion("has_iron_ingot", conditionsFromTag(PigsteelItemTags.C_IRON_INGOTS)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, Blocks.PISTON).input('R', Items.REDSTONE).input('#', Blocks.COBBLESTONE).input('T', ItemTags.PLANKS).input('X', PigsteelItemTags.C_IRON_INGOTS).pattern("TTT").pattern("#X#").pattern("#R#").criterion("has_redstone", conditionsFromItem(Items.REDSTONE)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TRANSPORTATION, Blocks.RAIL, 16).input('#', Items.STICK).input('X', PigsteelItemTags.C_IRON_INGOTS).pattern("X X").pattern("X#X").pattern("X X").criterion("has_minecart", conditionsFromItem(Items.MINECART)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, Items.SHEARS).input('#', PigsteelItemTags.C_IRON_INGOTS).pattern(" #").pattern("# ").criterion("has_iron_ingot", conditionsFromTag(PigsteelItemTags.C_IRON_INGOTS)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.SHIELD).input('W', ItemTags.PLANKS).input('o', PigsteelItemTags.C_IRON_INGOTS).pattern("WoW").pattern("WWW").pattern(" W ").criterion("has_iron_ingot", conditionsFromTag(PigsteelItemTags.C_IRON_INGOTS)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, Blocks.TRIPWIRE_HOOK, 2).input('#', ItemTags.PLANKS).input('S', Items.STICK).input('I', PigsteelItemTags.C_IRON_INGOTS).pattern("I").pattern("S").pattern("#").criterion("has_string", conditionsFromItem(Items.STRING)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, Blocks.BLAST_FURNACE).input('#', Blocks.SMOOTH_STONE).input('X', Blocks.FURNACE).input('I', PigsteelItemTags.C_IRON_INGOTS).pattern("III").pattern("IXI").pattern("###").criterion("has_smooth_stone", conditionsFromItem(Blocks.SMOOTH_STONE)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, Blocks.SMITHING_TABLE).input('#', ItemTags.PLANKS).input('@', PigsteelItemTags.C_IRON_INGOTS).pattern("@@").pattern("##").pattern("##").criterion("has_iron_ingot", conditionsFromTag(PigsteelItemTags.C_IRON_INGOTS)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, Blocks.STONECUTTER).input('I', PigsteelItemTags.C_IRON_INGOTS).input('#', Blocks.STONE).pattern(" I ").pattern("###").criterion("has_stone", conditionsFromItem(Blocks.STONE)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, Blocks.CHAIN).input('I', PigsteelItemTags.C_IRON_INGOTS).input('N', Items.IRON_NUGGET).pattern("N").pattern("I").pattern("N").criterion("has_iron_nugget", conditionsFromItem(Items.IRON_NUGGET)).criterion("has_iron_ingot", conditionsFromTag(PigsteelItemTags.C_IRON_INGOTS)).offerTo(exporter);
    }
    public static void offer2x2CompactingRecipe(Consumer<RecipeJsonProvider> exporter, RecipeCategory category, ItemConvertible output, TagKey<Item> input) {
        ShapedRecipeJsonBuilder.create(category, output, 1).input('#', input).pattern("##").pattern("##").criterion("has_iron_ingot", conditionsFromTag(input)).offerTo(exporter);
    }
    public static void offerPressurePlateRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, TagKey<Item> input) {
        createPressurePlateRecipe(RecipeCategory.REDSTONE, output, Ingredient.fromTag(input)).criterion("has_iron_ingot",conditionsFromTag(input)).offerTo(exporter);
    }

    public static void makeLantern(Consumer<RecipeJsonProvider> exporter, Block output, Item torch){
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, output)
                .input('#', PigsteelItems.PIGSTEEL_NUGGET)
                .input('X', torch)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .criterion("has_torch", conditionsFromItem(torch))
                .offerTo(exporter);
    }
    public static void offerReversibleCompactingIngotRecipes(Consumer<RecipeJsonProvider> exporter, RecipeCategory reverseCategory, ItemConvertible baseItem, RecipeCategory compactingCategory, ItemConvertible compactItem, @Nullable String compactingGroup, @Nullable String reverseGroup) {
        ShapelessRecipeJsonBuilder.create(reverseCategory, baseItem, 9).input(compactItem).group(reverseGroup).criterion(hasItem(compactItem),
                conditionsFromItem(compactItem)).offerTo(exporter, new Identifier(Pigsteel.MOD_ID, Registries.ITEM.getId(baseItem.asItem()).getPath() +"_from_" + Registries.ITEM.getId(compactItem.asItem()).getPath()));

        ShapedRecipeJsonBuilder.create(compactingCategory, compactItem)
                .input('#', baseItem)
                .pattern("###")
                .pattern("###")
                .pattern("###").group(compactingGroup)
                .criterion(hasItem(baseItem), conditionsFromItem(baseItem)).offerTo(exporter, new Identifier(Pigsteel.MOD_ID, Registries.ITEM.getId(compactItem.asItem()).getPath() +"_from_" + Registries.ITEM.getId(baseItem.asItem()).getPath()));
    }
    public static void makeSmeltnBlast(Consumer<RecipeJsonProvider> exporter, List<ItemConvertible> inputs, RecipeCategory category, ItemConvertible output, float experience, int cookingTime, String group){
        RecipeProvider.offerSmelting(exporter, inputs, category, output, experience, cookingTime, group);
        RecipeProvider.offerBlasting(exporter, inputs, category, output, experience, cookingTime/2, group);
    }

    public static void makeBarsRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible bars, ItemConvertible ingredient){
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, bars, 16)
                .input('#', ingredient).pattern("###").pattern("###").criterion(hasItem(ingredient), conditionsFromItem(ingredient))
                .offerTo(exporter);
    }




    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerReversibleCompactingIngotRecipes(exporter, RecipeCategory.MISC, PigsteelItems.PIGSTEEL_INGOT, RecipeCategory.BUILDING_BLOCKS, PigsteelBlocks.PIGSTEEL_BLOCK, null, null);
        offerReversibleCompactingIngotRecipes(exporter, RecipeCategory.MISC, PigsteelItems.PIGSTEEL_NUGGET, RecipeCategory.MISC, PigsteelItems.PIGSTEEL_INGOT, null, null);
        offerReversibleCompactingIngotRecipes(exporter, RecipeCategory.MISC, PigsteelItems.RAW_PIGSTEEL, RecipeCategory.BUILDING_BLOCKS, PigsteelBlocks.RAW_PIGSTEEL_BLOCK, null, null);

        offerWaxingRecipes(exporter);
        makeSmeltnBlast(exporter, List.of(PigsteelItems.RAW_PIGSTEEL, PigsteelBlocks.PORKSLAG), RecipeCategory.MISC, PigsteelItems.PIGSTEEL_INGOT, 0.7f, 200, "pigsteel_ingot");

        makeCutRecipes(exporter, PigsteelBlocks.PIGSTEEL_BLOCK, PigsteelBlocks.CUT_PIGSTEEL, PigsteelBlocks.CUT_PIGSTEEL_STAIRS, PigsteelBlocks.CUT_PIGSTEEL_SLAB);
        makeCutRecipes(exporter, PigsteelBlocks.INFECTED_PIGSTEEL, PigsteelBlocks.INFECTED_CUT_PIGSTEEL, PigsteelBlocks.INFECTED_CUT_PIGSTEEL_STAIRS, PigsteelBlocks.INFECTED_CUT_PIGSTEEL_SLAB);
        makeCutRecipes(exporter, PigsteelBlocks.CORRUPTED_PIGSTEEL, PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL, PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_STAIRS, PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_SLAB);
        makeCutRecipes(exporter, PigsteelBlocks.ZOMBIFIED_PIGSTEEL, PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL, PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_STAIRS, PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_SLAB);

        makeLantern(exporter, PigsteelBlocks.PIGSTEEL_LANTERN, Items.TORCH);
        makeLantern(exporter, PigsteelBlocks.PIGSTEEL_SOUL_LANTERN, Items.SOUL_TORCH);

        makeBarsRecipe(exporter, PigsteelBlocks.ZOMBIFIED_PIGSTEEL_BARS, PigsteelItems.PIGSTEEL_INGOT);


        makeVanillaRecipes(exporter);
    }

}
