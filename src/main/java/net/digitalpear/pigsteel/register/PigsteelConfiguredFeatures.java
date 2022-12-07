package net.digitalpear.pigsteel.register;

import net.digitalpear.pigsteel.PigsteelMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;

import java.util.List;

public class PigsteelConfiguredFeatures {
    
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_PIGSTEEL = register("ore_pigsteel", Feature.ORE,
            new OreFeatureConfig(List.of(
                    OreFeatureConfig.createTarget(OreConfiguredFeatures.NETHERRACK, PigsteelBlocks.PIGSTEEL_ORE.getDefaultState()),
                    OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, PigsteelBlocks.STONE_PIGSTEEL_ORE.getDefaultState()),
                    OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, PigsteelBlocks.DEEPSLATE_PIGSTEEL_ORE.getDefaultState())
            ), 7));


    public static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<FC, ?>> register(String id, F feature, FC config) {
        return BuiltinRegistries.addCasted(BuiltinRegistries.CONFIGURED_FEATURE, PigsteelMod.MOD_ID + ":" + id, new ConfiguredFeature(feature, config));
    }
    public static void init() {
    }
}
