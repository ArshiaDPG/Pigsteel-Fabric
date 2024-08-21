package net.digitalpear.pigsteel;


import net.digitalpear.pigsteel.init.PigsteelArmorTrimMaterials;
import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.digitalpear.pigsteel.init.PigsteelItems;
import net.digitalpear.pigsteel.init.data.PigsteelData;
import net.digitalpear.pigsteel.init.worldgen.PigsteelConfiguredFeatures;
import net.digitalpear.pigsteel.init.worldgen.PigsteelPlacedFeatures;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@SuppressWarnings("unused")
public class Pigsteel implements ModInitializer {
	public static final String MOD_ID = "pigsteel";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	public static Identifier getModId(String name){
		return Identifier.of(MOD_ID, name);
	}
	@Override
	public void onInitialize() {
		PigsteelBlocks.init();
		PigsteelItems.init();
		PigsteelConfiguredFeatures.init();
		PigsteelPlacedFeatures.init();
		PigsteelData.init();
		PigsteelArmorTrimMaterials.init();

		LOGGER.info("Let there be pigsteel!");
	}
}
