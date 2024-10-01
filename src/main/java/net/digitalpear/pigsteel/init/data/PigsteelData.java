package net.digitalpear.pigsteel.init.data;

import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.digitalpear.pigsteel.init.PigsteelItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class PigsteelData {
    public static void init(){
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (key == LootTables.PIGLIN_BARTERING_GAMEPLAY) {
                tableBuilder.modifyPools(builder -> {
                    builder.with(ItemEntry.builder(PigsteelItems.PIGSTEEL_CHUNK).weight(16)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 6.0f)));
                });
            } else if (key == LootTables.BASTION_TREASURE_CHEST) {
                tableBuilder.modifyPools(builder -> {
                    builder.with(ItemEntry.builder(PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK).weight(4)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)));
                });
            } else if (key == LootTables.BASTION_BRIDGE_CHEST) {
                tableBuilder.modifyPools(builder -> {
                    builder.with(ItemEntry.builder(PigsteelItems.PIGSTEEL_CHUNK).weight(4)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(6.0f, 16.0f)));
                });
            } else if (key == LootTables.NETHER_BRIDGE_CHEST) {
                tableBuilder.modifyPools(builder -> {
                    builder.with(ItemEntry.builder(PigsteelItems.PIGSTEEL_CHUNK).weight(4)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(6.0f, 16.0f)));
                    builder.with(ItemEntry.builder(PigsteelBlocks.pigsteelLanterns.getUnaffectedBlock()).weight(5)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)));
                });
            }
        });
    }
}
