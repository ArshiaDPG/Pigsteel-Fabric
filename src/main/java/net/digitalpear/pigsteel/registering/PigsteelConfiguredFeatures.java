package net.digitalpear.pigsteel.registering;

import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;

public final class PigsteelConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_PIGSTEEL =
            parseKey("ore_pigsteel");

    private static RegistryKey<ConfiguredFeature<?, ?>> parseKey(String featureId) {
        return RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, Id.mod(featureId));
    }
    private static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(String id, ConfiguredFeature<FC, ?> configuredFeature) {
        return (ConfiguredFeature)Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, id, configuredFeature);
    }

    protected static final class Decorators {
        public static final RangeDecoratorConfig BOTTOM_TO_TOP;
        protected Decorators() {}
        static {
            BOTTOM_TO_TOP = new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.getBottom(), YOffset.getTop()));
        }
    }

    public static final ConfiguredFeature<?, ?> ORE_PIGSTEEL_NETHER = register("ore_pigsteel_nether", (ConfiguredFeature)((ConfiguredFeature)((ConfiguredFeature) Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.NETHERRACK, PigsteelBlocks.PIGSTEEL_ORE.getDefaultState(), 7)).range(Decorators.BOTTOM_TO_TOP)).spreadHorizontally()).repeat(20));
}
