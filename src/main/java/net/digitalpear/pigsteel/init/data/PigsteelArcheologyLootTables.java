package net.digitalpear.pigsteel.init.data;

import net.digitalpear.pigsteel.Pigsteel;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class PigsteelArcheologyLootTables {
    public static final RegistryKey<LootTable> PIGSTEEL_MINE_ARCHAEOLOGY = register("archeology/pigsteel_mine_archaeology");

    private static RegistryKey<LootTable> register(String id) {
        return RegistryKey.of(RegistryKeys.LOOT_TABLE, Pigsteel.getModId(id));
    }
}
