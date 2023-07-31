package net.digitalpear.pigsteel.datagen;

import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.digitalpear.pigsteel.init.PigsteelItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.Registries;

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

        translationBuilder.add(PigsteelBlocks.CUT_PIGSTEEL_STAIRS, "Cut Pigsteel Stairs");
        translationBuilder.add(PigsteelBlocks.INFECTED_CUT_PIGSTEEL_STAIRS, "Infected Cut Pigsteel Stairs");
        translationBuilder.add(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_STAIRS, "Corrupted Cut Pigsteel Stairs");
        translationBuilder.add(PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_STAIRS, "Zombified Cut Pigsteel Stairs");

        translationBuilder.add(PigsteelBlocks.WAXED_PIGSTEEL_BLOCK, "Waxed Block of Pigsteel");
        translationBuilder.add(PigsteelBlocks.WAXED_INFECTED_PIGSTEEL, "Waxed Infected Pigsteel");
        translationBuilder.add(PigsteelBlocks.WAXED_CORRUPTED_PIGSTEEL, "Waxed Corrupted Pigsteel");
        translationBuilder.add(PigsteelBlocks.WAXED_ZOMBIFIED_PIGSTEEL, "Waxed Zombified Pigsteel");

        translationBuilder.add(PigsteelBlocks.WAXED_CUT_PIGSTEEL_STAIRS, "Waxed Cut Pigsteel Stairs");
        translationBuilder.add(PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL_STAIRS, "Waxed Infected Cut Pigsteel Stairs");
        translationBuilder.add(PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS, "Waxed Corrupted Cut Pigsteel Stairs");
        translationBuilder.add(PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS, "Waxed Zombified Cut Pigsteel Stairs");

        translationBuilder.add(PigsteelItems.RAW_PIGSTEEL, "Raw Pigsteel");
        translationBuilder.add(PigsteelItems.PIGSTEEL_INGOT, "Pigsteel Ingot");
        translationBuilder.add(PigsteelItems.PIGSTEEL_NUGGET, "Pigsteel Nugget");

        translationBuilder.add(PigsteelBlocks.PIGSTEEL_LANTERN, "Pigsteel Lantern");
        translationBuilder.add(PigsteelBlocks.PIGSTEEL_SOUL_LANTERN, "Pigsteel Soul Lantern");

        PigsteelBlocks.pigsteelBars.getAllBlocks().forEach(block -> translationBuilder.add(block, formatString(Registries.BLOCK.getId(block).getPath())));
        PigsteelBlocks.cutPigsteel.getAllBlocks().forEach(block -> translationBuilder.add(block, formatString(Registries.BLOCK.getId(block).getPath())));
        PigsteelBlocks.cutPigsteelSlabs.getAllBlocks().forEach(block -> translationBuilder.add(block, formatString(Registries.BLOCK.getId(block).getPath())));

        translationBuilder.add("trim_material.pigsteel.pigsteel", "Pigsteel");
    }


    public static String formatString(String input) {
        String[] words = input.split("_"); // Split the input string at underscores
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                // Capitalize the first letter of each word and append it to the result
                result.append(Character.toUpperCase(word.charAt(0)));
                if (word.length() > 1) {
                    // Append the rest of the word (excluding the first letter)
                    result.append(word.substring(1));
                }
                result.append(" "); // Append a space after each word
            }
        }

        return result.toString().trim(); // Trim any leading/trailing spaces and return the formatted string
    }
}
