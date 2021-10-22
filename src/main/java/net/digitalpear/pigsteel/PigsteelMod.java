package net.digitalpear.pigsteel;

import net.digitalpear.pigsteel.registering.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@SuppressWarnings("deprecation")
public class PigsteelMod implements ModInitializer {
	public static final String MOD_ID = "pigsteel";
	private static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	public static Logger getLogger() {
		return LOGGER;
	}

	private static ConfiguredFeature<?, ?> PIGSTEEL_ORE_NETHER = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.NETHERRACK, PigsteelBlocks.PIGSTEEL_ORE.getDefaultState(), 10)).uniformRange(YOffset.fixed(0), YOffset.getTop()).spreadHorizontally().repeat(20);

	@Override
	public void onInitialize() {

		PigsteelBlocks.init();
		PigsteelItems.init();


		RegistryKey<ConfiguredFeature<?, ?>> pigsteelOreNether = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MOD_ID, "ore_pigsteel"));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, pigsteelOreNether.getValue(), PIGSTEEL_ORE_NETHER);

		BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, pigsteelOreNether);

		System.out.println("Let there be pigsteel!");
	}
}
