package net.digitalpear.pigsteel.datagen.loot;

import net.digitalpear.pigsteel.init.PigsteelItems;
import net.digitalpear.pigsteel.init.data.PigsteelArcheologyLootTables;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetDamageLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class PigsteelArcheologyLootTableProvider extends SimpleFabricLootTableProvider {
    public PigsteelArcheologyLootTableProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup, LootContextTypes.ARCHAEOLOGY);
    }

    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> lootTableBiConsumer) {
        lootTableBiConsumer.accept(PigsteelArcheologyLootTables.PIGSTEEL_MINE_ARCHAEOLOGY, LootTable.builder().pool(LootPool.builder()

                .with(ItemEntry.builder(Items.GOLDEN_PICKAXE).weight(6).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.15F, 0.65F))))
                .with(ItemEntry.builder(Items.GOLDEN_AXE).weight(4).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.15F, 0.65F))))
                .with(ItemEntry.builder(Items.GOLDEN_HELMET).weight(4).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.15F, 0.65F))))

                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(5))
                .with(ItemEntry.builder(Items.GOLD_NUGGET).weight(13))

                .with(ItemEntry.builder(Items.COAL).weight(13))
                .with(ItemEntry.builder(Items.SOUL_TORCH).weight(8))

                .with(ItemEntry.builder(Items.NETHER_BRICK).weight(14))
                .with(ItemEntry.builder(Items.STRING).weight(13))
                .with(ItemEntry.builder(Items.RAIL).weight(5))


                .with(ItemEntry.builder(PigsteelItems.DISC_FRAGMENT_MOLTEN).weight(4))

                .build()));
    }
}
