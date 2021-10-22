package net.digitalpear.pigsteel.registering;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;

public class PigsteelBiomeFeatures {
    public static void init() {
        // Feature additions
        BiomeModifications.addFeature(
                BiomeSelectors.categories(Biome.Category.NETHER),
                GenerationStep.Feature.UNDERGROUND_ORES,
                PigsteelConfiguredFeatures.ORE_PIGSTEEL
        );

        // Biomes
    }
}
