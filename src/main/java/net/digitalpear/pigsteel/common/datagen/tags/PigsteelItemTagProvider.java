package net.digitalpear.pigsteel.common.datagen.tags;

import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.digitalpear.pigsteel.init.PigsteelItems;
import net.digitalpear.pigsteel.init.tags.PigsteelItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class PigsteelItemTagProvider extends FabricTagProvider.ItemTagProvider {


    public PigsteelItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture, new PigsteelBlockTagProvider(output, registriesFuture));
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getTagBuilder(PigsteelItemTags.PIGSTEEL_ORES)
                .add(getId(PigsteelBlocks.PORKSLAG.asItem()));

        getTagBuilder(ConventionalItemTags.ORES)
                .addTag(PigsteelItemTags.PIGSTEEL_ORES.id());

        PigsteelBlocks.PIGSTEEL_LANTERNS.getAllBlocks().forEach(block -> {
            valueLookupBuilder(ItemTags.LANTERNS).add(block.asItem());
        });
        PigsteelBlocks.PIGSTEEL_SOUL_LANTERNS.getAllBlocks().forEach(block -> {
            valueLookupBuilder(ItemTags.LANTERNS).add(block.asItem());
        });
    }
    public static Identifier getId(Item block){
        return Registries.ITEM.getId(block);
    }
}
