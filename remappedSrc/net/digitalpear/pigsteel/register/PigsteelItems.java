package net.digitalpear.pigsteel.register;

import net.digitalpear.pigsteel.Pigsteel;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
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
                    entries.addAfter(Items.NETHER_GOLD_ORE, PigsteelBlocks.DEEPSLATE_PIGSTEEL_ORE);
                    entries.addAfter(Items.NETHER_GOLD_ORE, PigsteelBlocks.STONE_PIGSTEEL_ORE);
                    if (FabricLoader.getInstance().isModLoaded("byg")) {
                        entries.addAfter(Items.NETHER_GOLD_ORE, PigsteelBlocks.BRIMSTONE_PIGSTEEL_ORE);
                        entries.addAfter(Items.NETHER_GOLD_ORE, PigsteelBlocks.BLUE_PIGSTEEL_ORE);
                    }
                    entries.addAfter(Items.NETHER_GOLD_ORE, PigsteelBlocks.PIGSTEEL_ORE);

                    entries.addAfter(Items.ANCIENT_DEBRIS, PigsteelBlocks.PORKSLAG);
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
                    entries.add(PigsteelBlocks.INFECTED_PIGSTEEL);
                    entries.add(PigsteelBlocks.CORRUPTED_PIGSTEEL);
                    entries.add(PigsteelBlocks.ZOMBIFIED_PIGSTEEL);

                    entries.add(PigsteelBlocks.CUT_PIGSTEEL);
                    entries.add(PigsteelBlocks.CUT_PIGSTEEL_SLAB);
                    entries.add(PigsteelBlocks.CUT_PIGSTEEL_STAIRS);

                    entries.add(PigsteelBlocks.INFECTED_CUT_PIGSTEEL);
                    entries.add(PigsteelBlocks.INFECTED_CUT_PIGSTEEL_SLAB);
                    entries.add(PigsteelBlocks.INFECTED_CUT_PIGSTEEL_STAIRS);

                    entries.add(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL);
                    entries.add(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_SLAB);
                    entries.add(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_STAIRS);

                    entries.add(PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL);
                    entries.add(PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_SLAB);
                    entries.add(PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_STAIRS);

                    entries.add(PigsteelBlocks.WAXED_PIGSTEEL_BLOCK);
                    entries.add(PigsteelBlocks.WAXED_INFECTED_PIGSTEEL);
                    entries.add(PigsteelBlocks.WAXED_CORRUPTED_PIGSTEEL);
                    entries.add(PigsteelBlocks.WAXED_ZOMBIFIED_PIGSTEEL);

                    entries.add(PigsteelBlocks.WAXED_CUT_PIGSTEEL);
                    entries.add(PigsteelBlocks.WAXED_CUT_PIGSTEEL_SLAB);
                    entries.add(PigsteelBlocks.WAXED_CUT_PIGSTEEL_STAIRS);

                    entries.add(PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL);
                    entries.add(PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL_SLAB);
                    entries.add(PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL_STAIRS);

                    entries.add(PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL);
                    entries.add(PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL_SLAB);
                    entries.add(PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS);

                    entries.add(PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL);
                    entries.add(PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL_SLAB);
                    entries.add(PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS);
                });

    }
}
