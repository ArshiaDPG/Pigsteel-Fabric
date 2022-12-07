package net.digitalpear.pigsteel.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class PigsteelModDatagens implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(PigsteelItemTagGen::new);
        fabricDataGenerator.addProvider(PigsteelBlockTagGen::new);
        fabricDataGenerator.addProvider(PigsteelRecipeGen::new);
    }
}
