package net.digitalpear.pigsteel.common.worldgen;

import net.digitalpear.pigsteel.Pigsteel;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class PigsteelFeature {
    public static final Feature<OreSlagFeatureConfig> SCATTERED_SLAG_ORE = register("scattered_slag_ore", new ScatteredSlagOreFeature(OreSlagFeatureConfig.CODEC));

    private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        return Registry.register(Registries.FEATURE, Pigsteel.getModId(name), feature);
    }

    public static void init(){}
}
