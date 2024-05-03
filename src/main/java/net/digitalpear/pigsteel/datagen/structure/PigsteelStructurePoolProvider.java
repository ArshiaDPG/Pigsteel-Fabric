package net.digitalpear.pigsteel.datagen.structure;

import net.digitalpear.pigsteel.init.worldgen.structure.PigsteelTemplatePools;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.structure.pool.StructurePool;

import java.util.concurrent.CompletableFuture;

public class PigsteelStructurePoolProvider extends FabricDynamicRegistryProvider {
    public PigsteelStructurePoolProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        PigsteelTemplatePools.templates.forEach(configuredFeatureRegistryKey -> add(registries, entries, configuredFeatureRegistryKey));
    }


    private void add(RegistryWrapper.WrapperLookup registries, Entries entries, RegistryKey<StructurePool> resourceKey) {
        RegistryWrapper.Impl<StructurePool> configuredFeatureRegistryLookup = registries.getWrapperOrThrow(RegistryKeys.TEMPLATE_POOL);

        entries.add(resourceKey, configuredFeatureRegistryLookup.getOrThrow(resourceKey).value());
    }
    @Override
    public String getName() {
        return "worldgen/template_pool";
    }
}