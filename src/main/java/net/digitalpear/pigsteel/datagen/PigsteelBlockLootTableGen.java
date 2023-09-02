package net.digitalpear.pigsteel.datagen;

import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.digitalpear.pigsteel.init.PigsteelItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.data.server.loottable.vanilla.VanillaBlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

public class PigsteelBlockLootTableGen extends FabricBlockLootTableProvider {
    public PigsteelBlockLootTableGen(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {

    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> biConsumer) {
        PigsteelBlocks.PIGSTEEL_WAXING_MAP.forEach((normal, waxed) -> {
            if (normal instanceof SlabBlock){
                makeDrop(biConsumer, normal, slabDrops(normal));
                makeDrop(biConsumer, waxed, slabDrops(waxed));
            }
            else{
                makeDrop(biConsumer, normal);
                makeDrop(biConsumer, waxed);
            }
        });

        makeDrop(biConsumer, PigsteelBlocks.PORKSLAG, dropsWithSilkTouch(PigsteelBlocks.PORKSLAG, (LootPoolEntry.Builder)this.applyExplosionDecay(PigsteelBlocks.PORKSLAG, ((LeafEntry.Builder)ItemEntry.builder(PigsteelItems.PIGSTEEL_CHUNK).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 6.0f)))).apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))));
        makeDrop(biConsumer, PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK);

    }
    public void makeDrop(BiConsumer<Identifier, LootTable.Builder> biConsumer, Block block){
        makeDrop(biConsumer, block, this.drops(block));
    }

    public void makeDrop(BiConsumer<Identifier, LootTable.Builder> biConsumer, Block block, net.minecraft.loot.LootTable.Builder builder){
        biConsumer.accept(Registries.BLOCK.getId(block).withPrefixedPath("blocks/"), builder);
    }
    public void makeOre(BiConsumer<Identifier, LootTable.Builder> biConsumer, Block block, ItemConvertible alternativeDrop){
        makeDrop(biConsumer, block, dropsWithSilkTouch(block, this.applyExplosionDecay(block, ItemEntry.builder(alternativeDrop).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 6.0F))).apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))));
    }
    public net.minecraft.loot.LootTable.Builder oreDrops(Block dropWithSilkTouch, Item drop) {
        return dropsWithSilkTouch(dropWithSilkTouch, this.applyExplosionDecay(dropWithSilkTouch, ItemEntry.builder(drop).apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));
    }
}
