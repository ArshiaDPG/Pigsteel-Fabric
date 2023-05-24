package net.digitalpear.pigsteel.register;

import net.digitalpear.pigsteel.Pigsteel;
import net.minecraft.block.Blocks;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;

import java.util.List;

public class PigsteelConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_PIGSTEEL = of("ore_pigsteel");

    public static RegistryKey<ConfiguredFeature<?, ?>> of(String id) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(Pigsteel.MOD_ID, id));
    }

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> featureRegisterable) {
        RuleTest netherrackRule = new BlockMatchRuleTest(Blocks.NETHERRACK);
        RuleTest stoneRule = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateRule = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreFeatureConfig.Target> list = List.of(
                OreFeatureConfig.createTarget(netherrackRule, PigsteelBlocks.PIGSTEEL_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateRule, PigsteelBlocks.DEEPSLATE_PIGSTEEL_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(stoneRule, PigsteelBlocks.STONE_PIGSTEEL_ORE.getDefaultState())
        );

        ConfiguredFeatures.register(featureRegisterable, ORE_PIGSTEEL, Feature.ORE, new OreFeatureConfig(list, 7));
    }

    public static void init(){

    }
}
