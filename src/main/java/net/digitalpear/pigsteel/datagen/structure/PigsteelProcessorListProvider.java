package net.digitalpear.pigsteel.datagen.structure;

import net.digitalpear.pigsteel.init.worldgen.structure.PigsteelProcessorLists;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.structure.processor.StructureProcessorList;

import java.util.concurrent.CompletableFuture;

public class PigsteelProcessorListProvider extends FabricDynamicRegistryProvider {
    public PigsteelProcessorListProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        PigsteelProcessorLists.structures.forEach(configuredFeatureRegistryKey -> add(registries, entries, configuredFeatureRegistryKey));
    }


    private void add(RegistryWrapper.WrapperLookup registries, Entries entries, RegistryKey<StructureProcessorList> resourceKey) {
        RegistryWrapper.Impl<StructureProcessorList> configuredFeatureRegistryLookup = registries.getWrapperOrThrow(RegistryKeys.PROCESSOR_LIST);

        entries.add(resourceKey, configuredFeatureRegistryLookup.getOrThrow(resourceKey).value());
    }
    @Override
    public String getName() {
        return "worldgen/processor_list";
    }
}