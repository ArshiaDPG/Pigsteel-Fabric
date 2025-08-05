package net.digitalpear.pigsteel.init;

import net.digitalpear.pigsteel.Pigsteel;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.particle.EmotionParticle;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Function;


public class PigsteelItems {
    public static RegistryKey<Item> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.ITEM, Pigsteel.id(id));
    }
    public static Item registerItem(String id, Item.Settings settings){
        return registerItem(id, Item::new, settings);
    }
    public static Item registerItem(String id, Function<Item.Settings, Item> factory, Item.Settings settings){
        return Items.register(keyOf(id), factory, settings);
    }

    public static final Item PIGSTEEL_CHUNK = registerItem("pigsteel_chunk", new Item.Settings().fireproof());

    public static void init(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
                entries.addBefore(Items.ANCIENT_DEBRIS, PigsteelBlocks.PORKSLAG);
                entries.addAfter(Items.RAW_IRON_BLOCK, PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
                entries.addAfter(Items.RAW_IRON, PigsteelItems.PIGSTEEL_CHUNK);
        });

        PigsteelBlocks.PIGSTEEL_SOUL_LANTERNS.addToItemGroup(ItemGroups.FUNCTIONAL, Items.SOUL_LANTERN);
        PigsteelBlocks.PIGSTEEL_LANTERNS.addToItemGroup(ItemGroups.FUNCTIONAL, Items.SOUL_LANTERN);
        PigsteelBlocks.PIGSTEEL_BARRELS.addToItemGroup(ItemGroups.FUNCTIONAL, Items.BARREL);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(PigsteelBlocks.REFINED_PIGSTEEL.getUnaffectedBlock());
            entries.add(PigsteelBlocks.CHISELED_PIGSTEEL.getUnaffectedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_GRATE.getUnaffectedBlock());
            entries.add(PigsteelBlocks.CUT_PIGSTEEL.getUnaffectedBlock());
            entries.add(PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getUnaffectedBlock());
            entries.add(PigsteelBlocks.CUT_PIGSTEEL_SLABS.getUnaffectedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_DOORS.getUnaffectedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_TRAPDOORS.getUnaffectedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_BARS.getUnaffectedBlock());

            entries.add(PigsteelBlocks.REFINED_PIGSTEEL.getInfectedBlock());
            entries.add(PigsteelBlocks.CHISELED_PIGSTEEL.getInfectedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_GRATE.getInfectedBlock());
            entries.add(PigsteelBlocks.CUT_PIGSTEEL.getInfectedBlock());
            entries.add(PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getInfectedBlock());
            entries.add(PigsteelBlocks.CUT_PIGSTEEL_SLABS.getInfectedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_DOORS.getInfectedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_TRAPDOORS.getInfectedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_BARS.getInfectedBlock());

            entries.add(PigsteelBlocks.REFINED_PIGSTEEL.getCorruptedBlock());
            entries.add(PigsteelBlocks.CHISELED_PIGSTEEL.getCorruptedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_GRATE.getCorruptedBlock());
            entries.add(PigsteelBlocks.CUT_PIGSTEEL.getCorruptedBlock());
            entries.add(PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getCorruptedBlock());
            entries.add(PigsteelBlocks.CUT_PIGSTEEL_SLABS.getCorruptedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_DOORS.getCorruptedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_TRAPDOORS.getCorruptedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_BARS.getCorruptedBlock());

            entries.add(PigsteelBlocks.REFINED_PIGSTEEL.getZombifiedBlock());
            entries.add(PigsteelBlocks.CHISELED_PIGSTEEL.getZombifiedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_GRATE.getZombifiedBlock());
            entries.add(PigsteelBlocks.CUT_PIGSTEEL.getZombifiedBlock());
            entries.add(PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getZombifiedBlock());
            entries.add(PigsteelBlocks.CUT_PIGSTEEL_SLABS.getZombifiedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_DOORS.getZombifiedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_TRAPDOORS.getZombifiedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_BARS.getZombifiedBlock());

            entries.add(PigsteelBlocks.REFINED_PIGSTEEL.getWaxedUnaffectedBlock());
            entries.add(PigsteelBlocks.CHISELED_PIGSTEEL.getWaxedUnaffectedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_GRATE.getWaxedUnaffectedBlock());
            entries.add(PigsteelBlocks.CUT_PIGSTEEL.getWaxedUnaffectedBlock());
            entries.add(PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getWaxedUnaffectedBlock());
            entries.add(PigsteelBlocks.CUT_PIGSTEEL_SLABS.getWaxedUnaffectedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_DOORS.getWaxedUnaffectedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_TRAPDOORS.getWaxedUnaffectedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_BARS.getWaxedUnaffectedBlock());

            entries.add(PigsteelBlocks.REFINED_PIGSTEEL.getWaxedInfectedBlock());
            entries.add(PigsteelBlocks.CHISELED_PIGSTEEL.getWaxedInfectedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_GRATE.getWaxedInfectedBlock());
            entries.add(PigsteelBlocks.CUT_PIGSTEEL.getWaxedInfectedBlock());
            entries.add(PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getWaxedInfectedBlock());
            entries.add(PigsteelBlocks.CUT_PIGSTEEL_SLABS.getWaxedInfectedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_DOORS.getWaxedInfectedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_TRAPDOORS.getWaxedInfectedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_BARS.getWaxedInfectedBlock());

            entries.add(PigsteelBlocks.REFINED_PIGSTEEL.getWaxedCorruptedBlock());
            entries.add(PigsteelBlocks.CHISELED_PIGSTEEL.getWaxedCorruptedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_GRATE.getWaxedCorruptedBlock());
            entries.add(PigsteelBlocks.CUT_PIGSTEEL.getWaxedCorruptedBlock());
            entries.add(PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getWaxedCorruptedBlock());
            entries.add(PigsteelBlocks.CUT_PIGSTEEL_SLABS.getWaxedCorruptedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_DOORS.getWaxedCorruptedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_TRAPDOORS.getWaxedCorruptedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_BARS.getWaxedCorruptedBlock());

            entries.add(PigsteelBlocks.REFINED_PIGSTEEL.getWaxedZombifiedBlock());
            entries.add(PigsteelBlocks.CHISELED_PIGSTEEL.getWaxedZombifiedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_GRATE.getWaxedZombifiedBlock());
            entries.add(PigsteelBlocks.CUT_PIGSTEEL.getWaxedZombifiedBlock());
            entries.add(PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getWaxedZombifiedBlock());
            entries.add(PigsteelBlocks.CUT_PIGSTEEL_SLABS.getWaxedZombifiedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_DOORS.getWaxedZombifiedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_TRAPDOORS.getWaxedZombifiedBlock());
            entries.add(PigsteelBlocks.PIGSTEEL_BARS.getWaxedZombifiedBlock());
        });
    }
}
