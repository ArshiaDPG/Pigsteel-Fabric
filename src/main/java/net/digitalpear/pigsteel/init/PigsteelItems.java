package net.digitalpear.pigsteel.init;

import net.digitalpear.pigsteel.Pigsteel;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;


public class PigsteelItems {
    public static Item registerItem(String id, Item item){
        return Registry.register(Registries.ITEM, Pigsteel.getModId(id), item);
    }

    public static final Item PIGSTEEL_CHUNK = registerItem("pigsteel_chunk", new Item(new Item.Settings().fireproof()));

//    public static final Item MUSIC_DISC_MOLTEN = registerItem("music_disc_molten", new Item(new Item.Settings().rarity(Rarity.RARE).jukeboxPlayable(JukeboxSongs.CREATOR_MUSIC_BOX).maxCount(1)));
//    public static final Item DISC_FRAGMENT_MOLTEN = registerItem("disc_fragment_molten", new DiscFragmentItem(new Item.Settings()));


    public static void init(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
                entries.addBefore(Items.ANCIENT_DEBRIS, PigsteelBlocks.PORKSLAG);
                entries.addAfter(Items.RAW_IRON_BLOCK, PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
                entries.addAfter(Items.RAW_IRON, PigsteelItems.PIGSTEEL_CHUNK);
//                entries.addAfter(Items.DISC_FRAGMENT_5, DISC_FRAGMENT_MOLTEN);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
//            entries.addAfter(Items.MUSIC_DISC_PIGSTEP, MUSIC_DISC_MOLTEN);
        });

        PigsteelBlocks.pigsteelSoulLanterns.addToItemGroup(ItemGroups.FUNCTIONAL, Items.SOUL_LANTERN);
        PigsteelBlocks.pigsteelLanterns.addToItemGroup(ItemGroups.FUNCTIONAL, Items.SOUL_LANTERN);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
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
