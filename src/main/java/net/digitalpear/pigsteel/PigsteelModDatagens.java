package net.digitalpear.pigsteel;

import net.digitalpear.pigsteel.datagen.*;
import net.digitalpear.pigsteel.register.PigsteelArmorTrimMaterials;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.impl.biome.modification.BuiltInRegistryKeys;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class PigsteelModDatagens implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {

        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(PigsteelRecipeGen::new);
        pack.addProvider(PigsteelBlockTagGen::new);
        pack.addProvider(PigsteelItemTagGen::new);
        pack.addProvider(PigsteelLanguageGen::new);
        pack.addProvider(PigsteelModelGen::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.TRIM_MATERIAL, PigsteelArmorTrimMaterials::oneTwentyBootstrap);
    }
}
