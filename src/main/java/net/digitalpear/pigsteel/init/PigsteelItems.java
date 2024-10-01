package net.digitalpear.pigsteel.init;

import net.digitalpear.pigsteel.Pigsteel;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Function;


public class PigsteelItems {
    public static RegistryKey<Item> keyOf(RegistryKey<Block> blockKey) {
        return RegistryKey.of(RegistryKeys.ITEM, blockKey.getValue());
    }
    public static RegistryKey<Item> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.ITEM, Pigsteel.getModId(id));
    }
    public static Item registerItem(String id){
        return registerItem(id, new Item.Settings());
    }
    public static Item registerItem(String id, Item.Settings settings){
        return registerItem(id, Item::new, settings);
    }
    public static Item registerItem(String id, Function<Item.Settings, Item> factory, Item.Settings settings){
        Item item = factory.apply(settings.registryKey(keyOf(id)));
        return Registry.register(Registries.ITEM, Pigsteel.getModId(id), item);
    }

    public static final Item PIGSTEEL_CHUNK = registerItem("pigsteel_chunk", new Item.Settings().fireproof());

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
                entries.add(PigsteelBlocks.cutPigsteelStairs.getUnaffectedBlock());
                entries.add(PigsteelBlocks.cutPigsteelSlabs.getUnaffectedBlock());

                entries.add(PigsteelBlocks.refinedPigsteel.getInfectedBlock());
                entries.add(PigsteelBlocks.cutPigsteel.getInfectedBlock());
                entries.add(PigsteelBlocks.cutPigsteelStairs.getInfectedBlock());
                entries.add(PigsteelBlocks.cutPigsteelSlabs.getInfectedBlock());

                entries.add(PigsteelBlocks.refinedPigsteel.getCorruptedBlock());
                entries.add(PigsteelBlocks.cutPigsteel.getCorruptedBlock());
                entries.add(PigsteelBlocks.cutPigsteelStairs.getCorruptedBlock());
                entries.add(PigsteelBlocks.cutPigsteelSlabs.getCorruptedBlock());

                entries.add(PigsteelBlocks.refinedPigsteel.getZombifiedBlock());
                entries.add(PigsteelBlocks.cutPigsteel.getZombifiedBlock());
                entries.add(PigsteelBlocks.cutPigsteelStairs.getZombifiedBlock());
                entries.add(PigsteelBlocks.cutPigsteelSlabs.getZombifiedBlock());

                entries.add(PigsteelBlocks.refinedPigsteel.getWaxedUnaffectedBlock());
                entries.add(PigsteelBlocks.cutPigsteel.getWaxedUnaffectedBlock());
                entries.add(PigsteelBlocks.cutPigsteelStairs.getWaxedUnaffectedBlock());
                entries.add(PigsteelBlocks.cutPigsteelSlabs.getWaxedUnaffectedBlock());

                entries.add(PigsteelBlocks.refinedPigsteel.getWaxedInfectedBlock());
                entries.add(PigsteelBlocks.cutPigsteel.getWaxedInfectedBlock());
                entries.add(PigsteelBlocks.cutPigsteelStairs.getWaxedInfectedBlock());
                entries.add(PigsteelBlocks.cutPigsteelSlabs.getWaxedInfectedBlock());

                entries.add(PigsteelBlocks.refinedPigsteel.getWaxedCorruptedBlock());
                entries.add(PigsteelBlocks.cutPigsteel.getWaxedCorruptedBlock());
                entries.add(PigsteelBlocks.cutPigsteelStairs.getWaxedCorruptedBlock());
                entries.add(PigsteelBlocks.cutPigsteelSlabs.getWaxedCorruptedBlock());

                entries.add(PigsteelBlocks.refinedPigsteel.getWaxedZombifiedBlock());
                entries.add(PigsteelBlocks.cutPigsteel.getWaxedZombifiedBlock());
                entries.add(PigsteelBlocks.cutPigsteelStairs.getWaxedZombifiedBlock());
                entries.add(PigsteelBlocks.cutPigsteelSlabs.getWaxedZombifiedBlock());
        });
    }
}
