package net.digitalpear.pigsteel.common.datagen;

import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.digitalpear.pigsteel.init.PigsteelItems;
import net.digitalpear.pigsteel.init.data.ZombifiableBlockRegistry;
import net.digitalpear.pigsteel.init.tags.PigsteelBiomeTags;
import net.digitalpear.pigsteel.init.tags.PigsteelBlockTags;
import net.digitalpear.pigsteel.init.tags.PigsteelItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PigsteelLanguageProvider extends FabricLanguageProvider {

    public static List<ZombifiableBlockRegistry> registryList = List.of(
            PigsteelBlocks.cutPigsteel,
            PigsteelBlocks.cutPigsteelStairs,
            PigsteelBlocks.cutPigsteelSlabs,
            PigsteelBlocks.refinedPigsteel,
            PigsteelBlocks.pigsteelLanterns,
            PigsteelBlocks.pigsteelSoulLanterns
    );
    public PigsteelLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {

        translationBuilder.add(PigsteelBlocks.PORKSLAG, "Porkslag");
        translationBuilder.add(PigsteelBlocks.PORKSLAG.asItem(), "Porkslag");

        translationBuilder.add(PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK, "Block of Pigsteel Chunks");
        translationBuilder.add(PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK.asItem(), "Block of Pigsteel Chunks");

        registryList.forEach(zombifiableBlockRegistry -> {
            zombifiableBlockRegistry.getAllBlocks().forEach(block -> {
                translationBuilder.add(block, formatString(Registries.BLOCK.getId(block).getPath()));
                translationBuilder.add(block.asItem(), formatString(Registries.ITEM.getId(block.asItem()).getPath()));
            });
        });

        translationBuilder.add(PigsteelItems.PIGSTEEL_CHUNK, "Pigsteel Chunk");


        makeTagTranslation(translationBuilder, PigsteelItemTags.PIGSTEEL_ORES);

        makeTagTranslation(translationBuilder, PigsteelBlockTags.PIGSTEEL_BLOCKS);
        makeTagTranslation(translationBuilder, PigsteelBlockTags.PIGSTEEL_ORES);
        makeTagTranslation(translationBuilder, PigsteelBlockTags.ZOMBIFICATION_ACCELERATION);
        makeTagTranslation(translationBuilder, PigsteelBlockTags.ZOMBIFICATION_DECELERATION);

        makeTagTranslation(translationBuilder, PigsteelBiomeTags.HAS_EXTRA_PIGSTEEL);
        makeTagTranslation(translationBuilder, PigsteelBiomeTags.HAS_NO_PIGSTEEL);

        translationBuilder.add("trim_material.pigsteel.pigsteel", "Pigsteel");
    }

    public static void makeTagTranslation(TranslationBuilder builder, TagKey<?> tagKey){
        builder.add(tagKey, formatString(tagKey.id().getPath()));
    }

    public static void buildDiscFragment(TranslationBuilder builder, Item item, String songName){
        builder.add(item, "Disc Fragment");
        builder.add(item.getTranslationKey() + ".desc", "Music Disc - " + songName);
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
