package net.digitalpear.pigsteel;

import net.digitalpear.pigsteel.datagen.*;
import net.digitalpear.pigsteel.datagen.providers.worldgen.PigsteelConfiguredFeatureProvider;
import net.digitalpear.pigsteel.datagen.providers.worldgen.PigsteelPlacedFeatureProvider;
import net.digitalpear.pigsteel.datagen.providers.worldgen.PigsteelTrimMaterialProvider;
import net.digitalpear.pigsteel.register.PigsteelArmorTrimMaterials;
import net.digitalpear.pigsteel.register.PigsteelConfiguredFeatures;
import net.digitalpear.pigsteel.register.PigsteelPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class PigsteelModDatagens implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {

        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(PigsteelRecipeGen::new);
        pack.addProvider(PigsteelBlockLootTableGen::new);

        pack.addProvider(PigsteelBlockTagGen::new);
        pack.addProvider(PigsteelItemTagGen::new);

        pack.addProvider(PigsteelLanguageGen::new);
        pack.addProvider(PigsteelModelGen::new);

        pack.addProvider(PigsteelTrimMaterialProvider::new);

        pack.addProvider(PigsteelConfiguredFeatureProvider::new);
        pack.addProvider(PigsteelPlacedFeatureProvider::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.TRIM_MATERIAL, PigsteelArmorTrimMaterials::oneTwentyBootstrap);
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, PigsteelConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, PigsteelPlacedFeatures::bootstrap);
    }
}
