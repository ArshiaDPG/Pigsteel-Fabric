package net.digitalpear.pigsteel;


import net.digitalpear.pigsteel.common.worldgen.PigsteelFeature;
import net.digitalpear.pigsteel.init.PigsteelArmorTrimMaterials;
import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.digitalpear.pigsteel.init.PigsteelItems;
import net.digitalpear.pigsteel.init.data.PigsteelData;
import net.digitalpear.pigsteel.init.worldgen.PigsteelConfiguredFeatures;
import net.digitalpear.pigsteel.init.worldgen.PigsteelPlacedFeatures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@SuppressWarnings({"unused"})
public class Pigsteel implements ModInitializer {
	public static final String MOD_ID = "pigsteel";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	public static Identifier getModId(String id){
		return Identifier.of(MOD_ID, id);
	}

	@Override
	public void onInitialize() {

		PigsteelBlocks.init();
		PigsteelItems.init();
		PigsteelFeature.init();
		PigsteelConfiguredFeatures.init();
		PigsteelPlacedFeatures.init();
		PigsteelData.init();
		PigsteelArmorTrimMaterials.init();


		ResourceManagerHelper.registerBuiltinResourcePack(Identifier.of(MOD_ID, "pigsteel_ore"),
				FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(), ResourcePackActivationType.NORMAL);


		LOGGER.info("Let there be pigsteel!");
	}
}
