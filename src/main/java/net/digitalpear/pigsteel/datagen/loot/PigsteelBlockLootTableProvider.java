package net.digitalpear.pigsteel.datagen.loot;

import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.digitalpear.pigsteel.init.PigsteelItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class PigsteelBlockLootTableProvider extends FabricBlockLootTableProvider {


    public PigsteelBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        PigsteelBlocks.PIGSTEEL_WAXING_MAP.forEach((normal, waxed) -> {
            if (normal instanceof SlabBlock){
                addDrop(normal, slabDrops(normal));
                addDrop(waxed, slabDrops(waxed));

            }
            else{
                addDrop(normal);
                addDrop(waxed);
            }
        });
        addDrop(PigsteelBlocks.PORKSLAG, dropsWithSilkTouch(PigsteelBlocks.PORKSLAG, (LootPoolEntry.Builder)this.applyExplosionDecay(PigsteelBlocks.PORKSLAG, ((LeafEntry.Builder)ItemEntry.builder(PigsteelItems.PIGSTEEL_CHUNK).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 6.0f)))).apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))));
        addDrop(PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK);
    }
}
