package net.digitalpear.pigsteel.datagen;

import net.digitalpear.pigsteel.PigsteelMod;
import net.digitalpear.pigsteel.register.PigsteelBlocks;
import net.digitalpear.pigsteel.register.PigsteelItems;
import net.digitalpear.pigsteel.register.tags.PigsteelBlockTags;
import net.digitalpear.pigsteel.register.tags.PigsteelItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PigsteelItemTagGen extends FabricTagProvider<Item> {
    /**
     * Construct a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided. For example @see BlockTagProvider
     *
     * @param dataGenerator The data generator instance
     */
    public PigsteelItemTagGen(FabricDataGenerator dataGenerator) {
        super(dataGenerator, Registry.ITEM);
    }

    @Override
    protected void generateTags() {
        getOrCreateTagBuilder(PigsteelItemTags.IRONS)
                .add(Items.IRON_INGOT)
                .add(PigsteelItems.PIGSTEEL_INGOT);

        getOrCreateTagBuilder(PigsteelItemTags.IRON_NUGGETS)
                .add(Items.IRON_NUGGET)
                .add(PigsteelItems.PIGSTEEL_NUGGET);

        getOrCreateTagBuilder(PigsteelItemTags.IRON_BLOCKS)
                .add(Items.IRON_BLOCK)
                .add(PigsteelBlocks.PIGSTEEL_BLOCK.asItem());

        getOrCreateTagBuilder(PigsteelItemTags.PIGSTEEL_ORES)
                .add(PigsteelBlocks.PIGSTEEL_ORE.asItem())
                .add(PigsteelBlocks.STONE_PIGSTEEL_ORE.asItem())
                .add(PigsteelBlocks.DEEPSLATE_PIGSTEEL_ORE.asItem())
                .addOptional(new Identifier(PigsteelMod.MOD_ID, "blue_pigsteel_ore"))
                .addOptional(new Identifier(PigsteelMod.MOD_ID, "brimstone_pigsteel_ore"));
    }
}
