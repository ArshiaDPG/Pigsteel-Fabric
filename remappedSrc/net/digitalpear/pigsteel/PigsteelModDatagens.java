package net.digitalpear.pigsteel;

import net.digitalpear.pigsteel.common.datagen.*;
import net.digitalpear.pigsteel.common.datagen.providers.PigsteelItemOverrideDataProvider;
import net.digitalpear.pigsteel.common.datagen.providers.PigsteelTrimMaterialProvider;
import net.digitalpear.pigsteel.common.datagen.worldgen.PigsteelConfiguredFeatureProvider;
import net.digitalpear.pigsteel.common.datagen.worldgen.PigsteelPlacedFeatureProvider;
import net.digitalpear.pigsteel.init.PigsteelArmorTrimMaterials;
import net.digitalpear.pigsteel.init.worldgen.PigsteelConfiguredFeatures;
import net.digitalpear.pigsteel.init.worldgen.PigsteelPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class PigsteelModDatagens implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {

        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(PigsteelRecipeProvider::new);
        pack.addProvider(PigsteelBlockLootTableProvider::new);

        pack.addProvider(PigsteelBlockTagProvider::new);
        pack.addProvider(PigsteelItemTagProvider::new);
        pack.addProvider(PigsteelBiomeTagProvider::new);

        pack.addProvider(PigsteelLanguageProvider::new);
        pack.addProvider(PigsteelModelProvider::new);

        pack.addProvider(PigsteelTrimMaterialProvider::new);

        pack.addProvider(PigsteelConfiguredFeatureProvider::new);
        pack.addProvider(PigsteelPlacedFeatureProvider::new);


        pack.addProvider((FabricDataGenerator.Pack.Factory<PigsteelItemOverrideDataProvider>) PigsteelItemOverrideDataProvider::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.TRIM_MATERIAL, PigsteelArmorTrimMaterials::oneTwentyBootstrap);
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, PigsteelConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, PigsteelPlacedFeatures::bootstrap);
    }
}
