package net.digitalpear.pigsteel;

import net.digitalpear.pigsteel.datagen.PigsteelBlockTagGen;
import net.digitalpear.pigsteel.datagen.PigsteelItemTagGen;
import net.digitalpear.pigsteel.datagen.PigsteelLanguageGen;
import net.digitalpear.pigsteel.datagen.PigsteelRecipeGen;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class PigsteelModDatagens implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {

        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(PigsteelRecipeGen::new);
        pack.addProvider(PigsteelBlockTagGen::new);
        pack.addProvider(PigsteelItemTagGen::new);
        pack.addProvider(PigsteelLanguageGen::new);
    }
}
