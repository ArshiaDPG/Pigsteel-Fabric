package net.digitalpear.pigsteel.common.worldgen;

import net.digitalpear.pigsteel.Pigsteel;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;

public class PigsteelFeature {
    public static final Feature<DefaultFeatureConfig> MOLTEN_REMAINS = register("molten_remains", new MoltenRemainsFeature(DefaultFeatureConfig.CODEC));


    private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        return Registry.register(Registries.FEATURE, new Identifier(Pigsteel.MOD_ID, name), feature);
    }

    public static void init(){}
}
