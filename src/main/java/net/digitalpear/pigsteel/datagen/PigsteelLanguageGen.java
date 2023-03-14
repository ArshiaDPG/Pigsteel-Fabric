package net.digitalpear.pigsteel.datagen;

import net.digitalpear.pigsteel.PigsteelMod;
import net.digitalpear.pigsteel.register.PigsteelBlocks;
import net.digitalpear.pigsteel.register.PigsteelItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.Registries;
import org.apache.commons.lang3.StringUtils;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class PigsteelLanguageGen extends FabricLanguageProvider {
    public PigsteelLanguageGen(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {

        translationBuilder.add(PigsteelBlocks.PIGSTEEL_BLOCK, "Block of Pigsteel");
        translationBuilder.add(PigsteelBlocks.WAXED_PIGSTEEL_BLOCK, "Waxed Block of Pigsteel");

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


        translationBuilder.add(PigsteelItems.PIGSTEEL_INGOT, "Pigsteel Ingot");
        translationBuilder.add(PigsteelItems.PIGSTEEL_NUGGET, "Pigsteel Nugget");

        translationBuilder.add(PigsteelBlocks.PIGSTEEL_LANTERN, "Pigsteel Lantern");
        translationBuilder.add(PigsteelBlocks.PIGSTEEL_SOUL_LANTERN, "Pigsteel Soul Lantern");

        translationBuilder.add(PigsteelBlocks.PIGSTEEL_ORE, "Pigsteel Ore");
        translationBuilder.add(PigsteelBlocks.STONE_PIGSTEEL_ORE, "Stone Pigsteel Ore");
        translationBuilder.add(PigsteelBlocks.DEEPSLATE_PIGSTEEL_ORE, "Deepslate Pigsteel Ore");
        translationBuilder.add("block.pigsteel.brimstone_pigsteel_ore", "Brimstone Pigsteel Ore");
        translationBuilder.add("block.pigsteel.blue_pigsteel_ore", "Blue Pigsteel Ore");

        try {
            Path existingFilePath = dataOutput.getModContainer().findPath("assets/" + PigsteelMod.MOD_ID + "/lang/en_us.existing.json").get();
            translationBuilder.add(existingFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add existing language file!", e);
        }
    }
    public static void makeTranslation(ItemConvertible itemConvertible, TranslationBuilder translationBuilder){
        translationBuilder.add(itemConvertible.asItem().getTranslationKey(), generateName(itemConvertible));
    }

    public static String generateName(ItemConvertible itemConvertible){
        List<String> name_list = List.of(Registries.ITEM.getId(itemConvertible.asItem()).getPath().split("_"));
        final String[] final_name = {""};
        name_list.forEach(s -> {
            StringUtils.capitalize(s);
            if (Objects.equals(final_name[0], "")){
                final_name[0] = final_name[0] + s;
            }
            else{
                final_name[0] = final_name[0] + " " + s;
            }
        });
        return final_name[0];
    }
}
