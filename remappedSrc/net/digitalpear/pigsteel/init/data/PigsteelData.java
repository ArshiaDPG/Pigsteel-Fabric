package net.digitalpear.pigsteel.init.data;

import net.digitalpear.pigsteel.init.PigsteelItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class PigsteelData {

    public static void init(){

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin() && id == LootTables.PIGLIN_BARTERING_GAMEPLAY){
                tableBuilder.modifyPools(builder -> {
                    builder.with(ItemEntry.builder(PigsteelItems.PIGSTEEL_CHUNK).weight(16)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 6.0f)));
                });
            }
        });
    }
}
