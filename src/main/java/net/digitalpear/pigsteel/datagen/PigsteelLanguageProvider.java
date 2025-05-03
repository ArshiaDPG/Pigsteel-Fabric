package net.digitalpear.pigsteel.datagen;

import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.digitalpear.pigsteel.init.PigsteelItems;
import net.digitalpear.pigsteel.init.tags.PigsteelBiomeTags;
import net.digitalpear.pigsteel.init.tags.PigsteelBlockTags;
import net.digitalpear.pigsteel.init.tags.PigsteelItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;

public class PigsteelLanguageProvider extends FabricLanguageProvider {
    public PigsteelLanguageProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {

        translationBuilder.add(PigsteelBlocks.PORKSLAG, "Porkslag");

        translationBuilder.add(PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK, "Block of Pigsteel Chunks");

        translationBuilder.add(PigsteelItems.PIGSTEEL_CHUNK, "Pigsteel Chunk");

        makeTagTranslation(translationBuilder, PigsteelItemTags.PIGSTEEL_ORES);

        makeTagTranslation(translationBuilder, PigsteelBlockTags.PIGSTEEL_BLOCKS);
        makeTagTranslation(translationBuilder, PigsteelBlockTags.PIGSTEEL_ORES);
        makeTagTranslation(translationBuilder, PigsteelBlockTags.ZOMBIFICATION_ACCELERATION);
        makeTagTranslation(translationBuilder, PigsteelBlockTags.ZOMBIFICATION_DECELERATION);

        makeTagTranslation(translationBuilder, PigsteelBiomeTags.HAS_EXTRA_PIGSTEEL);
        makeTagTranslation(translationBuilder, PigsteelBiomeTags.HAS_NO_PIGSTEEL);

        PigsteelBlocks.refinedPigsteel.getAllBlocks().forEach(block -> translationBuilder.add(block, formatString(Registries.BLOCK.getId(block).getPath())));
        PigsteelBlocks.cutPigsteel.getAllBlocks().forEach(block -> translationBuilder.add(block, formatString(Registries.BLOCK.getId(block).getPath())));
        PigsteelBlocks.cutPigsteelStairs.getAllBlocks().forEach(block -> translationBuilder.add(block, formatString(Registries.BLOCK.getId(block).getPath())));
        PigsteelBlocks.cutPigsteelSlabs.getAllBlocks().forEach(block -> translationBuilder.add(block, formatString(Registries.BLOCK.getId(block).getPath())));
        PigsteelBlocks.pigsteelLanterns.getAllBlocks().forEach(block -> translationBuilder.add(block, formatString(Registries.BLOCK.getId(block).getPath())));
        PigsteelBlocks.pigsteelSoulLanterns.getAllBlocks().forEach(block -> translationBuilder.add(block, formatString(Registries.BLOCK.getId(block).getPath())));

        translationBuilder.add("trim_material.pigsteel.pigsteel", "Pigsteel");
    }

    public static void makeTagTranslation(TranslationBuilder builder, TagKey<?> tagKey){
        builder.add(tagKey.id().toTranslationKey("tag." + tagKey.registry().getValue().getPath()), formatString(tagKey.id().getPath()));
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
