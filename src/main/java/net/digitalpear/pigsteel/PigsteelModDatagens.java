package net.digitalpear.pigsteel;

import net.digitalpear.pigsteel.datagen.*;
import net.digitalpear.pigsteel.datagen.PigsteelTrimMaterialProvider;
import net.digitalpear.pigsteel.datagen.loot.PigsteelArcheologyLootTableProvider;
import net.digitalpear.pigsteel.datagen.loot.PigsteelBlockLootTableProvider;
import net.digitalpear.pigsteel.datagen.structure.PigsteelProcessorListProvider;
import net.digitalpear.pigsteel.datagen.structure.PigsteelStructurePoolProvider;
import net.digitalpear.pigsteel.datagen.structure.PigsteelStructureProvider;
import net.digitalpear.pigsteel.datagen.structure.PigsteelStructureSetProvider;
import net.digitalpear.pigsteel.datagen.tags.PigsteelBiomeTagProvider;
import net.digitalpear.pigsteel.datagen.tags.PigsteelBlockTagProvider;
import net.digitalpear.pigsteel.datagen.tags.PigsteelItemTagProvider;
import net.digitalpear.pigsteel.datagen.worldgen.PigsteelConfiguredFeatureProvider;
import net.digitalpear.pigsteel.datagen.worldgen.PigsteelPlacedFeatureProvider;
import net.digitalpear.pigsteel.init.PigsteelArmorTrimMaterials;
import net.digitalpear.pigsteel.init.worldgen.PigsteelConfiguredFeatures;
import net.digitalpear.pigsteel.init.worldgen.PigsteelPlacedFeatures;
import net.digitalpear.pigsteel.init.worldgen.structure.PigsteelProcessorLists;
import net.digitalpear.pigsteel.init.worldgen.structure.PigsteelStructureSets;
import net.digitalpear.pigsteel.init.worldgen.structure.PigsteelTemplatePools;
import net.digitalpear.pigsteel.init.worldgen.structure.PigsteelStructures;
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

        pack.addProvider(PigsteelStructurePoolProvider::new);
        pack.addProvider(PigsteelStructureProvider::new);
        pack.addProvider(PigsteelStructureSetProvider::new);
        pack.addProvider(PigsteelProcessorListProvider::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.TRIM_MATERIAL, PigsteelArmorTrimMaterials::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, PigsteelConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, PigsteelPlacedFeatures::bootstrap);

        registryBuilder.addRegistry(RegistryKeys.STRUCTURE, PigsteelStructures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.TEMPLATE_POOL, PigsteelTemplatePools::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.STRUCTURE_SET, PigsteelStructureSets::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PROCESSOR_LIST, PigsteelProcessorLists::bootstrap);

    }
}
