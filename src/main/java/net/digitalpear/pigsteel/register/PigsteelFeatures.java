package net.digitalpear.pigsteel.register;

import net.digitalpear.pigsteel.PigsteelMod;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.*;

public class PigsteelFeatures {
    public static final String MOD_ID = PigsteelMod.MOD_ID;

    private static ConfiguredFeature<?, ?> NETHER_PIGSTEEL_ORE_CONFIGURED_FEATURE = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreConfiguredFeatures.NETHERRACK,
                    PigsteelBlocks.PIGSTEEL_ORE.getDefaultState(),
                    7)); // vein size

    public static PlacedFeature NETHER_PIGSTEEL_ORE_PLACED_FEATURE = NETHER_PIGSTEEL_ORE_CONFIGURED_FEATURE.withPlacement(
            CountPlacementModifier.of(20), // number of veins per chunk
            SquarePlacementModifier.of(), // spreading horizontally
            HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.getTop())); // height

    public static void init(){
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
                new Identifier(MOD_ID, "pigsteel_ore_nether"), NETHER_PIGSTEEL_ORE_CONFIGURED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(MOD_ID, "pigsteel_ore_nether"),
                NETHER_PIGSTEEL_ORE_PLACED_FEATURE);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                        new Identifier(MOD_ID, "pigsteel_ore_nether")));
    }
}
