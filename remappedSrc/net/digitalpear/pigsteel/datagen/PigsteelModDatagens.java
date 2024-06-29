package net.digitalpear.pigsteel.common.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class PigsteelModDatagens implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.createPack().addProvider(PigsteelRecipeGen::new);
        fabricDataGenerator.createPack().addProvider(PigsteelBlockTagGen::new);
        fabricDataGenerator.createPack().addProvider(PigsteelItemTagGen::new);
        fabricDataGenerator.createPack().addProvider(PigsteelLanguageGen::new);
    }
}
