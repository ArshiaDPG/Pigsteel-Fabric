package net.digitalpear.pigsteel.datagen.structure;

import net.digitalpear.pigsteel.init.worldgen.structure.PigsteelStructureSets;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.structure.StructureSet;

import java.util.concurrent.CompletableFuture;

public class PigsteelStructureSetProvider extends FabricDynamicRegistryProvider {
    public PigsteelStructureSetProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        PigsteelStructureSets.structures.forEach(configuredFeatureRegistryKey -> add(registries, entries, configuredFeatureRegistryKey));
    }


    private void add(RegistryWrapper.WrapperLookup registries, Entries entries, RegistryKey<StructureSet> resourceKey) {
        RegistryWrapper.Impl<StructureSet> configuredFeatureRegistryLookup = registries.getWrapperOrThrow(RegistryKeys.STRUCTURE_SET);

        entries.add(resourceKey, configuredFeatureRegistryLookup.getOrThrow(resourceKey).value());
    }
    @Override
    public String getName() {
        return "worldgen/structure_set";
    }
}