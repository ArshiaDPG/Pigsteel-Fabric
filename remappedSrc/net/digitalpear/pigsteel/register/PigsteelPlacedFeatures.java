package net.digitalpear.pigsteel.init;

import net.digitalpear.pigsteel.Pigsteel;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class PigsteelPlacedFeatures {


    public static final RegistryKey<PlacedFeature> ORE_PIGSTEEL = of("ore_pigsteel_nether");

    public static RegistryKey<PlacedFeature> of(String id) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(Pigsteel.MOD_ID, id));
    }

    public static void bootstrap(Registerable<PlacedFeature> featureRegisterable) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        RegistryEntry<ConfiguredFeature<?, ?>> pigsteelOre = registryEntryLookup.getOrThrow(PigsteelConfiguredFeatures.ORE_PIGSTEEL);

        PlacedFeatures.register(featureRegisterable, ORE_PIGSTEEL, pigsteelOre, modifiersWithCount(20, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(128))));
    }
    private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }

    public static void init() {
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_PIGSTEEL);
    }
}
