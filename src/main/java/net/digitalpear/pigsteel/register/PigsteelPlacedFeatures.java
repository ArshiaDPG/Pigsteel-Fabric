package net.digitalpear.pigsteel.register;

import net.digitalpear.pigsteel.PigsteelMod;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class PigsteelPlacedFeatures {

    public static RegistryEntry<PlacedFeature> makeOreFeature(String id,RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> configuredFeature, int count, PlacementModifier heightModifier){
        return register(id, configuredFeature, modifiersWithCount(count, heightModifier));
    }




    public static final RegistryEntry<PlacedFeature> ORE_PIGSTEEL = makeOreFeature("ore_pigsteel", PigsteelConfiguredFeatures.ORE_PIGSTEEL, 20,
            HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(128)));



    public static void init(){
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_PIGSTEEL.getKey().get());
    }

    public static RegistryEntry<PlacedFeature> register(String id, RegistryEntry<? extends ConfiguredFeature<?, ?>> registryEntry, List<PlacementModifier> modifiers) {
        return BuiltinRegistries.add(BuiltinRegistries.PLACED_FEATURE, PigsteelMod.MOD_ID + ":" + id, new PlacedFeature(RegistryEntry.upcast(registryEntry), List.copyOf(modifiers)));
    }

    private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }
    private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }
    private static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
    }
}
