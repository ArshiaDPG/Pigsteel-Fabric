package net.digitalpear.pigsteel.init.worldgen;

import net.digitalpear.pigsteel.Pigsteel;
import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.ArrayList;
import java.util.List;

public class PigsteelConfiguredFeatures {

    public static List<RegistryKey<ConfiguredFeature<?, ?>>> features = new ArrayList<>();

    public static RegistryKey<ConfiguredFeature<?, ?>> of(String id) {
        RegistryKey<ConfiguredFeature<?, ?>> featureRegistryKey = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(Pigsteel.MOD_ID, id));
        features.add(featureRegistryKey);
        return featureRegistryKey;
    }
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_PIGSTEEL = of("ore_pigsteel");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_PIGSTEEL_EXTRA = of("ore_pigsteel_extra");




    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> featureRegisterable) {
        RuleTest netherrackRule = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);

        ConfiguredFeatures.register(featureRegisterable, ORE_PIGSTEEL, Feature.ORE, new OreFeatureConfig(netherrackRule, PigsteelBlocks.PORKSLAG.getDefaultState(), 5, 0.5f));
        ConfiguredFeatures.register(featureRegisterable, ORE_PIGSTEEL_EXTRA, Feature.ORE, new OreFeatureConfig(netherrackRule, PigsteelBlocks.PORKSLAG.getDefaultState(), 7));
    }

    public static void init(){

    }
}
