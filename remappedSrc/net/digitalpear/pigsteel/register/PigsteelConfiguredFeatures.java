package net.digitalpear.pigsteel.register;

import net.digitalpear.pigsteel.PigsteelMod;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class PigsteelConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_PIGSTEEL = of("ore_pigsteel");
    public static RegistryKey<ConfiguredFeature<?, ?>> of(String id) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(PigsteelMod.MOD_ID, id));
    }
    public static void init(){

    }
}
