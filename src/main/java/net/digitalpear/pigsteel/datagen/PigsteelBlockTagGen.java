package net.digitalpear.pigsteel.datagen;

import net.digitalpear.pigsteel.PigsteelMod;
import net.digitalpear.pigsteel.register.PigsteelBlocks;
import net.digitalpear.pigsteel.register.tags.PigsteelBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PigsteelBlockTagGen extends FabricTagProvider<Block> {
    /**
     * Construct a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided. For example @see BlockTagProvider
     *
     * @param dataGenerator The data generator instance
     */
    public PigsteelBlockTagGen(FabricDataGenerator dataGenerator) {
        super(dataGenerator, Registry.BLOCK);
    }

    @Override
    protected void generateTags() {
        getOrCreateTagBuilder(PigsteelBlockTags.PIGSTEEL_ORES)
                .add(PigsteelBlocks.PIGSTEEL_ORE)
                .add(PigsteelBlocks.STONE_PIGSTEEL_ORE)
                .add(PigsteelBlocks.DEEPSLATE_PIGSTEEL_ORE)
                .addOptional(new Identifier(PigsteelMod.MOD_ID, "blue_pigsteel_ore"))
                .addOptional(new Identifier(PigsteelMod.MOD_ID, "brimstone_pigsteel_ore"));
    }
}
