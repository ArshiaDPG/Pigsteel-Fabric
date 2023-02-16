package net.digitalpear.pigsteel.register;

import net.digitalpear.pigsteel.PigsteelMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Arrays;


@SuppressWarnings("unused")
public class PigsteelItems {
    public static final String MOD_ID = PigsteelMod.MOD_ID;

    public static Item registerItem(String id, Item item){
        return Registry.register(Registries.ITEM, new Identifier(MOD_ID, id), item);
    }

    public static final Item PIGSTEEL_INGOT = registerItem("pigsteel_ingot", new Item(new FabricItemSettings()));
    public static final Item PIGSTEEL_NUGGET = registerItem("pigsteel_nugget", new Item(new FabricItemSettings()));

    public static void addItemsToGroup(ItemGroup itemGroup, Item... items){
        ItemGroupEvents.modifyEntriesEvent(itemGroup)
                .register(entries -> Arrays.stream(items).toList().forEach(entries::add));
    }
    public static void addItemsToGroup(ItemGroup itemGroup, Block... blocks){
        ItemGroupEvents.modifyEntriesEvent(itemGroup)
                .register(entries -> Arrays.stream(blocks).toList().forEach(entries::add));
    }

    public static void addOres(Block... blocks){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL)
                .register(entries -> Arrays.stream(blocks).toList().forEach(block -> entries.addAfter(Items.NETHER_QUARTZ_ORE, block)));
    }

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
                });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
                .register(entries -> {
                    entries.addAfter(Items.IRON_INGOT, PIGSTEEL_INGOT);
                    entries.addAfter(Items.IRON_NUGGET, PIGSTEEL_NUGGET);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL)
                .register(entries -> {
                    entries.addAfter(Items.SOUL_LANTERN, PigsteelBlocks.PIGSTEEL_SOUL_LANTERN);
                    entries.addAfter(Items.SOUL_LANTERN, PigsteelBlocks.PIGSTEEL_LANTERN);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
                .register(entries -> {
                    entries.add(new ItemStack(PigsteelBlocks.PIGSTEEL_BLOCK));

                    entries.add(new ItemStack(PigsteelBlocks.CUT_PIGSTEEL));
                    entries.add(new ItemStack(PigsteelBlocks.CUT_PIGSTEEL_SLAB));
                    entries.add(new ItemStack(PigsteelBlocks.CUT_PIGSTEEL_STAIRS));

                    entries.add(new ItemStack(PigsteelBlocks.INFECTED_CUT_PIGSTEEL));
                    entries.add(new ItemStack(PigsteelBlocks.INFECTED_CUT_PIGSTEEL_SLAB));
                    entries.add(new ItemStack(PigsteelBlocks.INFECTED_CUT_PIGSTEEL_STAIRS));

                    entries.add(new ItemStack(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL));
                    entries.add(new ItemStack(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_SLAB));
                    entries.add(new ItemStack(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_STAIRS));

                    entries.add(new ItemStack(PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL));
                    entries.add(new ItemStack(PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_SLAB));
                    entries.add(new ItemStack(PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_STAIRS));

                    entries.add(new ItemStack(PigsteelBlocks.WAXED_PIGSTEEL_BLOCK));

                    entries.add(new ItemStack(PigsteelBlocks.WAXED_CUT_PIGSTEEL));
                    entries.add(new ItemStack(PigsteelBlocks.WAXED_CUT_PIGSTEEL_SLAB));
                    entries.add(new ItemStack(PigsteelBlocks.WAXED_CUT_PIGSTEEL_STAIRS));

                    entries.add(new ItemStack(PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL));
                    entries.add(new ItemStack(PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL_SLAB));
                    entries.add(new ItemStack(PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL_STAIRS));

                    entries.add(new ItemStack(PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL));
                    entries.add(new ItemStack(PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL_SLAB));
                    entries.add(new ItemStack(PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS));

                    entries.add(new ItemStack(PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL));
                    entries.add(new ItemStack(PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL_SLAB));
                    entries.add(new ItemStack(PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS));
                });


    }
}
