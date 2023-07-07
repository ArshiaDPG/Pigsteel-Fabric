package net.digitalpear.pigsteel.register;

import net.digitalpear.pigsteel.Pigsteel;
import net.digitalpear.pigsteel.common.worldgen.MoltenRemainsFeatureConfig;
import net.digitalpear.pigsteel.common.worldgen.PigsteelFeature;
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
    public static final RegistryKey<ConfiguredFeature<?, ?>> PIGSTEEL_MOLTEN_REMAINS = of("pigsteel_molten_remains");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GOLD_MOLTEN_REMAINS = of("gold_molten_remains");

    public static RegistryKey<ConfiguredFeature<?, ?>> of(String id) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(Pigsteel.MOD_ID, id));
    }

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> featureRegisterable) {
        RuleTest netherrackRule = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);

        ConfiguredFeatures.register(featureRegisterable, ORE_PIGSTEEL, Feature.ORE, new OreFeatureConfig(netherrackRule, PigsteelBlocks.PORKSLAG.getDefaultState(), 7));

        ConfiguredFeatures.register(featureRegisterable, PIGSTEEL_MOLTEN_REMAINS, PigsteelFeature.MOLTEN_REMAINS, new MoltenRemainsFeatureConfig(Blocks.BASALT, PigsteelBlocks.PORKSLAG, PigsteelBlocks.RAW_PIGSTEEL_BLOCK));
        ConfiguredFeatures.register(featureRegisterable, GOLD_MOLTEN_REMAINS, PigsteelFeature.MOLTEN_REMAINS, new MoltenRemainsFeatureConfig(Blocks.BLACKSTONE, Blocks.GILDED_BLACKSTONE, Blocks.RAW_GOLD_BLOCK));
    }

    public static void init(){

    }
}
