package net.digitalpear.pigsteel.datagen;

import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.digitalpear.pigsteel.init.PigsteelItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class PigsteelLanguageGen extends FabricLanguageProvider {
    public PigsteelLanguageGen(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {

        translationBuilder.add(PigsteelBlocks.PORKSLAG, "Porkslag");

        translationBuilder.add(PigsteelBlocks.RAW_PIGSTEEL_BLOCK, "Raw Pigsteel Block");

        translationBuilder.add(PigsteelBlocks.PIGSTEEL_BLOCK, "Block of Pigsteel");
        translationBuilder.add(PigsteelBlocks.INFECTED_PIGSTEEL, "Infected Pigsteel");
        translationBuilder.add(PigsteelBlocks.CORRUPTED_PIGSTEEL, "Corrupted Pigsteel");
        translationBuilder.add(PigsteelBlocks.ZOMBIFIED_PIGSTEEL, "Zombified Pigsteel");

        translationBuilder.add(PigsteelBlocks.CUT_PIGSTEEL, "Cut Pigsteel");
        translationBuilder.add(PigsteelBlocks.INFECTED_CUT_PIGSTEEL, "Infected Cut Pigsteel");
        translationBuilder.add(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL, "Corrupted Cut Pigsteel");
        translationBuilder.add(PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL, "Zombified Cut Pigsteel");

        translationBuilder.add(PigsteelBlocks.CUT_PIGSTEEL_SLAB, "Cut Pigsteel Slab");
        translationBuilder.add(PigsteelBlocks.INFECTED_CUT_PIGSTEEL_SLAB, "Infected Cut Pigsteel Slab");
        translationBuilder.add(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_SLAB, "Corrupted Cut Pigsteel Slab");
        translationBuilder.add(PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_SLAB, "Zombified Cut Pigsteel Slab");

        translationBuilder.add(PigsteelBlocks.CUT_PIGSTEEL_STAIRS, "Cut Pigsteel Stairs");
        translationBuilder.add(PigsteelBlocks.INFECTED_CUT_PIGSTEEL_STAIRS, "Infected Cut Pigsteel Stairs");
        translationBuilder.add(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_STAIRS, "Corrupted Cut Pigsteel Stairs");
        translationBuilder.add(PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_STAIRS, "Zombified Cut Pigsteel Stairs");

        translationBuilder.add(PigsteelBlocks.WAXED_PIGSTEEL_BLOCK, "Waxed Block of Pigsteel");
        translationBuilder.add(PigsteelBlocks.WAXED_INFECTED_PIGSTEEL, "Waxed Infected Pigsteel");
        translationBuilder.add(PigsteelBlocks.WAXED_CORRUPTED_PIGSTEEL, "Waxed Corrupted Pigsteel");
        translationBuilder.add(PigsteelBlocks.WAXED_ZOMBIFIED_PIGSTEEL, "Waxed Zombified Pigsteel");

        translationBuilder.add(PigsteelBlocks.WAXED_CUT_PIGSTEEL, "Waxed Cut Pigsteel");
        translationBuilder.add(PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL, "Waxed Infected Cut Pigsteel");
        translationBuilder.add(PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL, "Waxed Corrupted Cut Pigsteel");
        translationBuilder.add(PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL, "Waxed Zombified Cut Pigsteel");

        translationBuilder.add(PigsteelBlocks.WAXED_CUT_PIGSTEEL_SLAB, "Waxed Cut Pigsteel Slab");
        translationBuilder.add(PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL_SLAB, "Waxed Infected Cut Pigsteel Slab");
        translationBuilder.add(PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL_SLAB, "Waxed Corrupted Cut Pigsteel Slab");
        translationBuilder.add(PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL_SLAB, "Waxed Zombified Cut Pigsteel Slab");

        translationBuilder.add(PigsteelBlocks.WAXED_CUT_PIGSTEEL_STAIRS, "Waxed Cut Pigsteel Stairs");
        translationBuilder.add(PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL_STAIRS, "Waxed Infected Cut Pigsteel Stairs");
        translationBuilder.add(PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS, "Waxed Corrupted Cut Pigsteel Stairs");
        translationBuilder.add(PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS, "Waxed Zombified Cut Pigsteel Stairs");

        translationBuilder.add(PigsteelItems.RAW_PIGSTEEL, "Raw Pigsteel");
        translationBuilder.add(PigsteelItems.PIGSTEEL_INGOT, "Pigsteel Ingot");
        translationBuilder.add(PigsteelItems.PIGSTEEL_NUGGET, "Pigsteel Nugget");

        translationBuilder.add(PigsteelBlocks.PIGSTEEL_LANTERN, "Pigsteel Lantern");
        translationBuilder.add(PigsteelBlocks.PIGSTEEL_SOUL_LANTERN, "Pigsteel Soul Lantern");

        translationBuilder.add(PigsteelBlocks.PIGSTEEL_ORE, "Pigsteel Ore");
        translationBuilder.add(PigsteelBlocks.STONE_PIGSTEEL_ORE, "Stone Pigsteel Ore");
        translationBuilder.add(PigsteelBlocks.DEEPSLATE_PIGSTEEL_ORE, "Deepslate Pigsteel Ore");
        translationBuilder.add(PigsteelBlocks.BRIMSTONE_PIGSTEEL_ORE, "Brimstone Pigsteel Ore");
        translationBuilder.add(PigsteelBlocks.BLUE_PIGSTEEL_ORE, "Blue Pigsteel Ore");


        translationBuilder.add(PigsteelBlocks.PIGSTEEL_BARS, "Pigsteel Bars");

        translationBuilder.add("trim_material.pigsteel.pigsteel", "Pigsteel");
    }
}
