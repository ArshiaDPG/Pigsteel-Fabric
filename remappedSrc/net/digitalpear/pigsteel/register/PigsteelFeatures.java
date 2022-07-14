package net.digitalpear.pigsteel.register;

import net.digitalpear.pigsteel.PigsteelMod;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class PigsteelFeatures {
    public static final String MOD_ID = PigsteelMod.MOD_ID;

    public static void init(){
        RegistryKey<PlacedFeature> nether_pigsteel_ore = RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                new Identifier(MOD_ID, "ore_pigsteel_nether"));
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, nether_pigsteel_ore);
    }
}
