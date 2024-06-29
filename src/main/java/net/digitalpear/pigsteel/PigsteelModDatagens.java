package net.digitalpear.pigsteel;

import net.digitalpear.pigsteel.common.datagen.PigsteelLanguageProvider;
import net.digitalpear.pigsteel.common.datagen.PigsteelModelProvider;
import net.digitalpear.pigsteel.common.datagen.PigsteelRecipeProvider;
import net.digitalpear.pigsteel.common.datagen.PigsteelTrimMaterialProvider;
import net.digitalpear.pigsteel.common.datagen.loot.PigsteelArcheologyLootTableProvider;
import net.digitalpear.pigsteel.common.datagen.loot.PigsteelBlockLootTableProvider;
import net.digitalpear.pigsteel.common.datagen.tags.PigsteelBiomeTagProvider;
import net.digitalpear.pigsteel.common.datagen.tags.PigsteelBlockTagProvider;
import net.digitalpear.pigsteel.common.datagen.tags.PigsteelItemTagProvider;
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
        pack.addProvider(PigsteelArcheologyLootTableProvider::new);

        pack.addProvider(PigsteelBlockTagProvider::new);
        pack.addProvider(PigsteelItemTagProvider::new);
        pack.addProvider(PigsteelBiomeTagProvider::new);

        pack.addProvider(PigsteelLanguageProvider::new);
        pack.addProvider(PigsteelModelProvider::new);

        pack.addProvider(PigsteelTrimMaterialProvider::new);

        pack.addProvider(PigsteelConfiguredFeatureProvider::new);
        pack.addProvider(PigsteelPlacedFeatureProvider::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.TRIM_MATERIAL, PigsteelArmorTrimMaterials::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, PigsteelConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, PigsteelPlacedFeatures::bootstrap);
    }
}
