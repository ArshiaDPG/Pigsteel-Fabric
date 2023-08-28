package net.digitalpear.pigsteel.init.worldgen;

import net.digitalpear.pigsteel.Pigsteel;
import net.digitalpear.pigsteel.common.worldgen.MoltenRemainsFeatureConfig;
import net.digitalpear.pigsteel.common.worldgen.PigsteelFeature;
import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.minecraft.block.Blocks;
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

public class PigsteelConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_PIGSTEEL = of("ore_pigsteel");

    public static RegistryKey<ConfiguredFeature<?, ?>> of(String id) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(Pigsteel.MOD_ID, id));
    }

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> featureRegisterable) {
        RuleTest netherrackRule = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);

        ConfiguredFeatures.register(featureRegisterable, ORE_PIGSTEEL, Feature.ORE, new OreFeatureConfig(netherrackRule, PigsteelBlocks.PORKSLAG.getDefaultState(), 7));
    }

    public static void init(){

    }
}
