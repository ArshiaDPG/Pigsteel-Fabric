package net.digitalpear.pigsteel.datagen.structure;

import net.digitalpear.pigsteel.init.worldgen.structure.PigsteelStructures;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.gen.structure.Structure;

import java.util.concurrent.CompletableFuture;

public class PigsteelStructureProvider extends FabricDynamicRegistryProvider {
    public PigsteelStructureProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        PigsteelStructures.structures.forEach(configuredFeatureRegistryKey -> add(registries, entries, configuredFeatureRegistryKey));
    }


    private void add(RegistryWrapper.WrapperLookup registries, Entries entries, RegistryKey<Structure> resourceKey) {
        RegistryWrapper.Impl<Structure> configuredFeatureRegistryLookup = registries.getWrapperOrThrow(RegistryKeys.STRUCTURE);

        entries.add(resourceKey, configuredFeatureRegistryLookup.getOrThrow(resourceKey).value());
    }
    @Override
    public String getName() {
        return "worldgen/structure";
    }
}