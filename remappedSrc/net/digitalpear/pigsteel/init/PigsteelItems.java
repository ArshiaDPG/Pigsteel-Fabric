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

    public static final Item PIGSTEEL_CHUNK = registerItem("pigsteel_chunk", new Item(new Item.Settings().fireproof()));

    public static void init(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL)
                .register(entries -> {
                    entries.addBefore(Items.ANCIENT_DEBRIS, PigsteelBlocks.PORKSLAG);
                    entries.addAfter(Items.RAW_IRON_BLOCK, PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK);
                });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
                .register(entries -> {
                    entries.addAfter(Items.RAW_IRON, PigsteelItems.PIGSTEEL_CHUNK);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL)
                .register(entries -> {
                    entries.addAfter(Items.SOUL_LANTERN,
                            PigsteelBlocks.pigsteelSoulLanterns.getUnaffectedBlock(),
                            PigsteelBlocks.pigsteelSoulLanterns.getInfectedBlock(),
                            PigsteelBlocks.pigsteelSoulLanterns.getCorruptedBlock(),
                            PigsteelBlocks.pigsteelSoulLanterns.getZombifiedBlock(),
                            PigsteelBlocks.pigsteelSoulLanterns.getWaxedUnaffectedBlock(),
                            PigsteelBlocks.pigsteelSoulLanterns.getWaxedInfectedBlock(),
                            PigsteelBlocks.pigsteelSoulLanterns.getWaxedCorruptedBlock(),
                            PigsteelBlocks.pigsteelSoulLanterns.getWaxedZombifiedBlock()
                    );
                    entries.addAfter(Items.SOUL_LANTERN,
                            PigsteelBlocks.pigsteelLanterns.getUnaffectedBlock(),
                            PigsteelBlocks.pigsteelLanterns.getInfectedBlock(),
                            PigsteelBlocks.pigsteelLanterns.getCorruptedBlock(),
                            PigsteelBlocks.pigsteelLanterns.getZombifiedBlock(),
                            PigsteelBlocks.pigsteelLanterns.getWaxedUnaffectedBlock(),
                            PigsteelBlocks.pigsteelLanterns.getWaxedInfectedBlock(),
                            PigsteelBlocks.pigsteelLanterns.getWaxedCorruptedBlock(),
                            PigsteelBlocks.pigsteelLanterns.getWaxedZombifiedBlock()
                    );
        });



        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
                .register(entries -> {
                    entries.add(PigsteelBlocks.refinedPigsteel.getUnaffectedBlock());
                    entries.add(PigsteelBlocks.cutPigsteel.getUnaffectedBlock());
                    entries.add(PigsteelBlocks.CUT_PIGSTEEL_STAIRS);
                    entries.add(PigsteelBlocks.cutPigsteelSlabs.getUnaffectedBlock());

                    entries.add(PigsteelBlocks.refinedPigsteel.getInfectedBlock());
                    entries.add(PigsteelBlocks.cutPigsteel.getInfectedBlock());
                    entries.add(PigsteelBlocks.INFECTED_CUT_PIGSTEEL_STAIRS);
                    entries.add(PigsteelBlocks.cutPigsteelSlabs.getInfectedBlock());

                    entries.add(PigsteelBlocks.refinedPigsteel.getCorruptedBlock());
                    entries.add(PigsteelBlocks.cutPigsteel.getCorruptedBlock());
                    entries.add(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_STAIRS);
                    entries.add(PigsteelBlocks.cutPigsteelSlabs.getCorruptedBlock());

                    entries.add(PigsteelBlocks.refinedPigsteel.getZombifiedBlock());
                    entries.add(PigsteelBlocks.cutPigsteel.getZombifiedBlock());
                    entries.add(PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_STAIRS);
                    entries.add(PigsteelBlocks.cutPigsteelSlabs.getZombifiedBlock());

                    entries.add(PigsteelBlocks.refinedPigsteel.getWaxedUnaffectedBlock());
                    entries.add(PigsteelBlocks.cutPigsteel.getWaxedUnaffectedBlock());
                    entries.add(PigsteelBlocks.WAXED_CUT_PIGSTEEL_STAIRS);
                    entries.add(PigsteelBlocks.cutPigsteelSlabs.getWaxedUnaffectedBlock());

                    entries.add(PigsteelBlocks.refinedPigsteel.getWaxedInfectedBlock());
                    entries.add(PigsteelBlocks.cutPigsteel.getWaxedInfectedBlock());
                    entries.add(PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL_STAIRS);
                    entries.add(PigsteelBlocks.cutPigsteelSlabs.getWaxedInfectedBlock());

                    entries.add(PigsteelBlocks.refinedPigsteel.getWaxedCorruptedBlock());
                    entries.add(PigsteelBlocks.cutPigsteel.getWaxedCorruptedBlock());
                    entries.add(PigsteelBlocks.cutPigsteelSlabs.getWaxedCorruptedBlock());
                    entries.add(PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS);

                    entries.add(PigsteelBlocks.refinedPigsteel.getWaxedZombifiedBlock());
                    entries.add(PigsteelBlocks.cutPigsteel.getWaxedZombifiedBlock());
                    entries.add(PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS);
                    entries.add(PigsteelBlocks.cutPigsteelSlabs.getWaxedZombifiedBlock());
                });

    }
}
