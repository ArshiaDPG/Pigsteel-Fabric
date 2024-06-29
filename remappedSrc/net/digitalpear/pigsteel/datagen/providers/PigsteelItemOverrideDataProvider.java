package net.digitalpear.pigsteel.common.datagen.providers;

import dhyces.trimmed.api.data.ItemOverrideDataProvider;
import net.digitalpear.pigsteel.Pigsteel;
import net.digitalpear.pigsteel.init.PigsteelArmorTrimMaterials;
import net.minecraft.data.DataOutput;
import net.minecraft.item.Items;

public class PigsteelItemOverrideDataProvider extends ItemOverrideDataProvider {

    public PigsteelItemOverrideDataProvider(DataOutput output) {
        super(output, Pigsteel.MOD_ID);
    }

    @Override
    protected void addItemOverrides() {
        addTrimOverride(Items.LEATHER_HELMET, PigsteelArmorTrimMaterials.PIGSTEEL);
        addTrimOverride(Items.LEATHER_CHESTPLATE, PigsteelArmorTrimMaterials.PIGSTEEL);
        addTrimOverride(Items.LEATHER_LEGGINGS, PigsteelArmorTrimMaterials.PIGSTEEL);
        addTrimOverride(Items.LEATHER_BOOTS, PigsteelArmorTrimMaterials.PIGSTEEL);

        addTrimOverride(Items.CHAINMAIL_HELMET, PigsteelArmorTrimMaterials.PIGSTEEL);
        addTrimOverride(Items.CHAINMAIL_CHESTPLATE, PigsteelArmorTrimMaterials.PIGSTEEL);
        addTrimOverride(Items.CHAINMAIL_LEGGINGS, PigsteelArmorTrimMaterials.PIGSTEEL);
        addTrimOverride(Items.CHAINMAIL_BOOTS, PigsteelArmorTrimMaterials.PIGSTEEL);

        addTrimOverride(Items.IRON_HELMET, PigsteelArmorTrimMaterials.PIGSTEEL);
        addTrimOverride(Items.IRON_CHESTPLATE, PigsteelArmorTrimMaterials.PIGSTEEL);
        addTrimOverride(Items.IRON_LEGGINGS, PigsteelArmorTrimMaterials.PIGSTEEL);
        addTrimOverride(Items.IRON_BOOTS, PigsteelArmorTrimMaterials.PIGSTEEL);

        addTrimOverride(Items.GOLDEN_HELMET, PigsteelArmorTrimMaterials.PIGSTEEL);
        addTrimOverride(Items.GOLDEN_CHESTPLATE, PigsteelArmorTrimMaterials.PIGSTEEL);
        addTrimOverride(Items.GOLDEN_LEGGINGS, PigsteelArmorTrimMaterials.PIGSTEEL);
        addTrimOverride(Items.GOLDEN_BOOTS, PigsteelArmorTrimMaterials.PIGSTEEL);

        addTrimOverride(Items.DIAMOND_HELMET, PigsteelArmorTrimMaterials.PIGSTEEL);
        addTrimOverride(Items.DIAMOND_CHESTPLATE, PigsteelArmorTrimMaterials.PIGSTEEL);
        addTrimOverride(Items.DIAMOND_LEGGINGS, PigsteelArmorTrimMaterials.PIGSTEEL);
        addTrimOverride(Items.DIAMOND_BOOTS, PigsteelArmorTrimMaterials.PIGSTEEL);

        addTrimOverride(Items.NETHERITE_HELMET, PigsteelArmorTrimMaterials.PIGSTEEL);
        addTrimOverride(Items.NETHERITE_CHESTPLATE, PigsteelArmorTrimMaterials.PIGSTEEL);
        addTrimOverride(Items.NETHERITE_LEGGINGS, PigsteelArmorTrimMaterials.PIGSTEEL);
        addTrimOverride(Items.NETHERITE_BOOTS, PigsteelArmorTrimMaterials.PIGSTEEL);
    }
}