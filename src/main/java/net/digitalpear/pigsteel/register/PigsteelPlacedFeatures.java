package net.digitalpear.pigsteel.register;

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
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class PigsteelPlacedFeatures {


    public static final RegistryKey<PlacedFeature> ORE_PIGSTEEL = of("ore_pigsteel_nether");
    public static final RegistryKey<PlacedFeature> PIGSTEEL_SPIRE = of("pigsteel_spire");
    public static final RegistryKey<PlacedFeature> PIGSTEEL_SPIRE_COMMON = of("pigsteel_spire_common");

    public static RegistryKey<PlacedFeature> of(String id) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(Pigsteel.MOD_ID, id));
    }

    public static void bootstrap(Registerable<PlacedFeature> featureRegisterable) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        RegistryEntry<ConfiguredFeature<?, ?>> pigsteelOre = registryEntryLookup.getOrThrow(PigsteelConfiguredFeatures.ORE_PIGSTEEL);
        RegistryEntry<ConfiguredFeature<?, ?>> pigsteelSpire = registryEntryLookup.getOrThrow(PigsteelConfiguredFeatures.PIGSTEEL_SPIRE);

        PlacedFeatures.register(featureRegisterable, ORE_PIGSTEEL, pigsteelOre,
                modifiersWithCount(20,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(128))));


        PlacedFeatures.register(featureRegisterable, PIGSTEEL_SPIRE, pigsteelSpire,
                RarityFilterPlacementModifier.of(20),
                SquarePlacementModifier.of(),
                HeightRangePlacementModifier.uniform(YOffset.fixed(90), YOffset.fixed(128)),
                BiomePlacementModifier.of());

        PlacedFeatures.register(featureRegisterable, PIGSTEEL_SPIRE, pigsteelSpire, spireSettings(20));
        PlacedFeatures.register(featureRegisterable, PIGSTEEL_SPIRE_COMMON, pigsteelSpire, spireSettings(8));
    }

    private static List<PlacementModifier> spireSettings(int commonality) {
        return List.of(RarityFilterPlacementModifier.of(commonality),
                SquarePlacementModifier.of(),
                HeightRangePlacementModifier.uniform(YOffset.fixed(90), YOffset.fixed(128)),
                BiomePlacementModifier.of());
    }

    private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }

    public static void init() {
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_PIGSTEEL);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether().and(BiomeSelectors.excludeByKey(BiomeKeys.BASALT_DELTAS)), GenerationStep.Feature.UNDERGROUND_DECORATION, PIGSTEEL_SPIRE);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.BASALT_DELTAS), GenerationStep.Feature.UNDERGROUND_DECORATION, PIGSTEEL_SPIRE_COMMON);
    }
}
