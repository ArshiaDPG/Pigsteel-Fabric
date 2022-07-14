package net.digitalpear.pigsteel;


import net.digitalpear.pigsteel.register.PigsteelBlocks;
import net.digitalpear.pigsteel.register.PigsteelFeatures;
import net.digitalpear.pigsteel.register.PigsteelItems;
import net.digitalpear.pigsteel.register.PigsteelRustables;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@SuppressWarnings({"unused"})
public class PigsteelMod implements ModInitializer {
	public static final String MOD_ID = "pigsteel";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
	public static final int pigsteelRustingChance = 6;

	@Override
	public void onInitialize() {
		PigsteelBlocks.init();
		PigsteelItems.init();
		PigsteelFeatures.init();
		PigsteelRustables.init();


		LOGGER.info("Let there be pigsteel!");
	}
}
