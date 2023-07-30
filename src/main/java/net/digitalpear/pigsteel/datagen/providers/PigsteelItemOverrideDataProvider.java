package net.digitalpear.pigsteel.datagen.providers;

import dhyces.trimmed.api.data.ItemOverrideDataProvider;
import net.digitalpear.pigsteel.Pigsteel;
import net.digitalpear.pigsteel.init.PigsteelArmorTrimMaterials;
import net.minecraft.data.DataOutput;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Util;

import java.util.LinkedList;
import java.util.List;

public class PigsteelItemOverrideDataProvider extends ItemOverrideDataProvider {

    public PigsteelItemOverrideDataProvider(DataOutput output) {
        super(output, Pigsteel.MOD_ID);
    }

    @Override
    protected void addItemOverrides() {
        final List<Item> armorItems = Util.make(new LinkedList<>(), list -> Registries.ITEM.stream().filter(item -> item instanceof ArmorItem).forEach(list::add));

        for (Item item : armorItems) {
            addTrimOverride(item, PigsteelArmorTrimMaterials.PIGSTEEL);
        }
    }
}