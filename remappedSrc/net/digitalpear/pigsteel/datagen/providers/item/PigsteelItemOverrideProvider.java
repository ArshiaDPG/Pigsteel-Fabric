package net.digitalpear.pigsteel.datagen.providers.item;

import dhyces.trimmed.api.data.ItemOverrideDataProvider;
import net.digitalpear.pigsteel.Pigsteel;
import net.digitalpear.pigsteel.init.PigsteelArmorTrimMaterials;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Util;

import java.util.LinkedList;
import java.util.List;

public class PigsteelItemOverrideProvider extends ItemOverrideDataProvider {
    public PigsteelItemOverrideProvider(FabricDataOutput output) {
        super(output, Pigsteel.MOD_ID);
    }

    @Override
    protected void addItemOverrides() {
        final List<Item> armorItems = Util.make(new LinkedList<>(), list -> {
            list.add(Items.CHAINMAIL_BOOTS);
            list.add(Items.CHAINMAIL_LEGGINGS);
            list.add(Items.CHAINMAIL_CHESTPLATE);
            list.add(Items.CHAINMAIL_HELMET);
            list.add(Items.DIAMOND_BOOTS);
            list.add(Items.DIAMOND_LEGGINGS);
            list.add(Items.DIAMOND_CHESTPLATE);
            list.add(Items.DIAMOND_HELMET);
            list.add(Items.GOLDEN_BOOTS);
            list.add(Items.GOLDEN_LEGGINGS);
            list.add(Items.GOLDEN_CHESTPLATE);
            list.add(Items.GOLDEN_HELMET);
            list.add(Items.IRON_BOOTS);
            list.add(Items.IRON_LEGGINGS);
            list.add(Items.IRON_CHESTPLATE);
            list.add(Items.IRON_HELMET);
            list.add(Items.LEATHER_BOOTS);
            list.add(Items.LEATHER_LEGGINGS);
            list.add(Items.LEATHER_CHESTPLATE);
            list.add(Items.LEATHER_HELMET);
            list.add(Items.NETHERITE_BOOTS);
            list.add(Items.NETHERITE_LEGGINGS);
            list.add(Items.NETHERITE_CHESTPLATE);
            list.add(Items.NETHERITE_HELMET);
            list.add(Items.TURTLE_HELMET);
        });
        for (Item item : armorItems) {
            addTrimOverride(item, PigsteelArmorTrimMaterials.PIGSTEEL);
        }
    }
}
