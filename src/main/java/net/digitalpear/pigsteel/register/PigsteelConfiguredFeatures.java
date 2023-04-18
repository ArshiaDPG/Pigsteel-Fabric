package net.digitalpear.pigsteel.register;

import net.digitalpear.pigsteel.PigsteelMod;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.List;

public class PigsteelConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_PIGSTEEL = of("ore_pigsteel");

    public static RegistryKey<ConfiguredFeature<?, ?>> of(String id) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(PigsteelMod.MOD_ID, id));
    }

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> featureRegisterable) {
        RuleTest netherrackRule = new BlockMatchRuleTest(Blocks.NETHERRACK);
        RuleTest ruleTest2 = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest ruleTest3 = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        List<OreFeatureConfig.Target> list = List.of(
                OreFeatureConfig.createTarget(netherrackRule, PigsteelBlocks.PIGSTEEL_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(ruleTest3, PigsteelBlocks.DEEPSLATE_PIGSTEEL_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(ruleTest2, PigsteelBlocks.STONE_PIGSTEEL_ORE.getDefaultState()));

        ConfiguredFeatures.register(featureRegisterable, ORE_PIGSTEEL, Feature.ORE, new OreFeatureConfig(list, 7));
    }
    public static void init(){

    }
}
