package net.digitalpear.pigsteel.init;

import net.digitalpear.pigsteel.Pigsteel;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class PigsteelItems {
    public static final String MOD_ID = Pigsteel.MOD_ID;

    public static Item registerItem(String id, Item item){
        return Registry.register(Registries.ITEM, new Identifier(MOD_ID, id), item);
    }

    public static final Item RAW_PIGSTEEL = registerItem("raw_pigsteel", new Item(new Item.Settings().fireproof()));

    public static final Item PIGSTEEL_INGOT = registerItem("pigsteel_ingot", new Item(new Item.Settings().fireproof()));
    public static final Item PIGSTEEL_NUGGET = registerItem("pigsteel_nugget", new Item(new Item.Settings().fireproof()));


    public static void init(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL)
                .register(entries -> {
                    entries.addBefore(Items.ANCIENT_DEBRIS, PigsteelBlocks.PORKSLAG);
                    entries.addAfter(Items.RAW_IRON_BLOCK, PigsteelBlocks.RAW_PIGSTEEL_BLOCK);
                });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
                .register(entries -> {
                    entries.addAfter(Items.IRON_INGOT, PIGSTEEL_INGOT);
                    entries.addAfter(Items.IRON_NUGGET, PIGSTEEL_NUGGET);
                    entries.addAfter(Items.RAW_IRON, PigsteelItems.RAW_PIGSTEEL);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL)
                .register(entries -> {
                    entries.addAfter(Items.SOUL_LANTERN, PigsteelBlocks.PIGSTEEL_SOUL_LANTERN);
                    entries.addAfter(Items.SOUL_LANTERN, PigsteelBlocks.PIGSTEEL_LANTERN);
        });


        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
                .register(entries -> {
                    entries.add(PigsteelBlocks.PIGSTEEL_BLOCK);
                    entries.add(PigsteelBlocks.cutPigsteel.getUnaffectedBlock());
                    entries.add(PigsteelBlocks.CUT_PIGSTEEL_STAIRS);
                    entries.add(PigsteelBlocks.cutPigsteelSlabs.getUnaffectedBlock());

                    entries.add(PigsteelBlocks.INFECTED_PIGSTEEL);
                    entries.add(PigsteelBlocks.cutPigsteel.getInfectedBlock());
                    entries.add(PigsteelBlocks.INFECTED_CUT_PIGSTEEL_STAIRS);
                    entries.add(PigsteelBlocks.cutPigsteelSlabs.getInfectedBlock());

                    entries.add(PigsteelBlocks.CORRUPTED_PIGSTEEL);
                    entries.add(PigsteelBlocks.cutPigsteel.getCorruptedBlock());
                    entries.add(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_STAIRS);
                    entries.add(PigsteelBlocks.cutPigsteelSlabs.getCorruptedBlock());

                    entries.add(PigsteelBlocks.ZOMBIFIED_PIGSTEEL);
                    entries.add(PigsteelBlocks.cutPigsteel.getZombifiedBlock());
                    entries.add(PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_STAIRS);
                    entries.add(PigsteelBlocks.cutPigsteelSlabs.getZombifiedBlock());

                    entries.add(PigsteelBlocks.WAXED_PIGSTEEL_BLOCK);
                    entries.add(PigsteelBlocks.cutPigsteel.getWaxedUnaffectedBlock());
                    entries.add(PigsteelBlocks.WAXED_CUT_PIGSTEEL_STAIRS);
                    entries.add(PigsteelBlocks.cutPigsteelSlabs.getWaxedUnaffectedBlock());

                    entries.add(PigsteelBlocks.WAXED_INFECTED_PIGSTEEL);
                    entries.add(PigsteelBlocks.cutPigsteel.getWaxedInfectedBlock());
                    entries.add(PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL_STAIRS);
                    entries.add(PigsteelBlocks.cutPigsteelSlabs.getWaxedInfectedBlock());

                    entries.add(PigsteelBlocks.WAXED_CORRUPTED_PIGSTEEL);
                    entries.add(PigsteelBlocks.cutPigsteel.getWaxedCorruptedBlock());
                    entries.add(PigsteelBlocks.cutPigsteelSlabs.getWaxedCorruptedBlock());
                    entries.add(PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS);

                    entries.add(PigsteelBlocks.WAXED_ZOMBIFIED_PIGSTEEL);
                    entries.add(PigsteelBlocks.cutPigsteel.getWaxedZombifiedBlock());
                    entries.add(PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS);
                    entries.add(PigsteelBlocks.cutPigsteelSlabs.getWaxedZombifiedBlock());

                    PigsteelBlocks.pigsteelBars.getAllBlocks().forEach(entries::add);
                });

    }
}
