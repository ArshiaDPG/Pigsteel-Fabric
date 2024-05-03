package net.digitalpear.pigsteel.datagen.worldgen;

import net.digitalpear.pigsteel.init.PigsteelArmorTrimMaterials;
import net.digitalpear.pigsteel.init.PigsteelConfiguredFeatures;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.item.trim.ArmorTrimMaterial;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.concurrent.CompletableFuture;

public class PigsteelTrimMaterialProvider extends FabricDynamicRegistryProvider {
    public PigsteelTrimMaterialProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        add(registries, entries, PigsteelArmorTrimMaterials.PIGSTEEL);

    }


    private void add(RegistryWrapper.WrapperLookup registries, Entries entries, RegistryKey<ArmorTrimMaterial> resourceKey) {
        RegistryWrapper.Impl<ArmorTrimMaterial> configuredFeatureRegistryLookup = registries.getWrapperOrThrow(RegistryKeys.TRIM_MATERIAL);

        entries.add(resourceKey, configuredFeatureRegistryLookup.getOrThrow(resourceKey).value());
    }
    @Override
    public String getName() {
        return "trim_material";
    }
}