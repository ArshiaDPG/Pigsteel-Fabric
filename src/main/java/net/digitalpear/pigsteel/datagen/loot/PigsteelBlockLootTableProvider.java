package net.digitalpear.pigsteel.datagen.loot;

import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.digitalpear.pigsteel.init.PigsteelItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.SlabBlock;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class PigsteelBlockLootTableProvider extends FabricBlockLootTableProvider {

    private RegistryWrapper.WrapperLookup registryLookup;

    public PigsteelBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup){
        super(dataOutput, registryLookup);
        this.registryLookup = registryLookup.join();
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
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);

        addDrop(PigsteelBlocks.PORKSLAG, dropsWithSilkTouch(PigsteelBlocks.PORKSLAG, (LootPoolEntry.Builder)this.applyExplosionDecay(PigsteelBlocks.PORKSLAG, ((LeafEntry.Builder)ItemEntry.builder(PigsteelItems.PIGSTEEL_CHUNK).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 6.0f)))).apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE))))));
        addDrop(PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK);
    }
}
