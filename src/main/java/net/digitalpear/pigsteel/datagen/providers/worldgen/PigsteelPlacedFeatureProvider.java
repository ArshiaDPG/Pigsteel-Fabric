package net.digitalpear.pigsteel.datagen.providers.worldgen;

import net.digitalpear.pigsteel.register.worldgen.PigsteelPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.concurrent.CompletableFuture;

public class PigsteelPlacedFeatureProvider extends FabricDynamicRegistryProvider {
    public PigsteelPlacedFeatureProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        add(registries, entries, PigsteelPlacedFeatures.ORE_PIGSTEEL);
        add(registries, entries, PigsteelPlacedFeatures.PIGSTEEL_MOLTEN_REMAINS);
        add(registries, entries, PigsteelPlacedFeatures.GOLD_MOLTEN_REMAINS);
    }


    private void add(RegistryWrapper.WrapperLookup registries, Entries entries, RegistryKey<PlacedFeature> resourceKey) {
        RegistryWrapper.Impl<PlacedFeature> configuredFeatureRegistryLookup = registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE);
        entries.add(resourceKey, configuredFeatureRegistryLookup.getOrThrow(resourceKey).value());
    }

    @Override
    public String getName() {
        return "worldgen/placed_feature";
    }
}