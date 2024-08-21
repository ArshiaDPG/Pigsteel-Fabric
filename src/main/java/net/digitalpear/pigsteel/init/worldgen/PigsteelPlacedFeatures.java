package net.digitalpear.pigsteel.init.worldgen;

import net.digitalpear.pigsteel.Pigsteel;
import net.digitalpear.pigsteel.init.tags.PigsteelBiomeTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.ArrayList;
import java.util.List;

public class PigsteelPlacedFeatures {

    public static List<RegistryKey<PlacedFeature>> features = new ArrayList<>();

    public static RegistryKey<PlacedFeature> of(String id) {
        RegistryKey<PlacedFeature> featureRegistryKey = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(Pigsteel.MOD_ID, id));
        features.add(featureRegistryKey);
        return featureRegistryKey;
    }

    public static final RegistryKey<PlacedFeature> ORE_PIGSTEEL = of("ore_pigsteel");
    public static final RegistryKey<PlacedFeature> ORE_PIGSTEEL_EXTRA = of("ore_pigsteel_extra");




    public static void bootstrap(Registerable<PlacedFeature> featureRegisterable) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        RegistryEntry<ConfiguredFeature<?, ?>> pigsteelOre = registryEntryLookup.getOrThrow(PigsteelConfiguredFeatures.ORE_PIGSTEEL);
        RegistryEntry<ConfiguredFeature<?, ?>> pigsteelOreExtra = registryEntryLookup.getOrThrow(PigsteelConfiguredFeatures.ORE_PIGSTEEL_EXTRA);

        PlacedFeatures.register(featureRegisterable, ORE_PIGSTEEL, pigsteelOre, modifiersWithCount(12, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(128))));
        PlacedFeatures.register(featureRegisterable, ORE_PIGSTEEL_EXTRA, pigsteelOreExtra, modifiersWithCount(14, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(128))));
    }

    private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }

    public static void init() {
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether().and(biomeSelectionContext -> !biomeSelectionContext.hasTag(PigsteelBiomeTags.HAS_NO_PIGSTEEL)), GenerationStep.Feature.UNDERGROUND_ORES, ORE_PIGSTEEL);
        BiomeModifications.addFeature(BiomeSelectors.tag(PigsteelBiomeTags.HAS_EXTRA_PIGSTEEL).and(biomeSelectionContext -> !biomeSelectionContext.hasTag(PigsteelBiomeTags.HAS_NO_PIGSTEEL)), GenerationStep.Feature.UNDERGROUND_ORES, ORE_PIGSTEEL_EXTRA);
    }
}
