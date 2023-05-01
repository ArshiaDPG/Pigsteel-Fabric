package net.digitalpear.pigsteel.datagen;

import net.digitalpear.pigsteel.PigsteelMod;
import net.digitalpear.pigsteel.register.PigsteelBlocks;
import net.digitalpear.pigsteel.register.tags.PigsteelBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class PigsteelBlockTagGen extends FabricTagProvider<Block> {

    /**
     * Constructs a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output           the {@link FabricDataOutput} instance
     * @param registriesFuture the backing registry for the tag type
     */
    public PigsteelBlockTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, Registries.BLOCK.getKey(), registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(PigsteelBlockTags.PIGSTEEL_ORES)
                .add(PigsteelBlocks.PIGSTEEL_ORE)
                .add(PigsteelBlocks.STONE_PIGSTEEL_ORE)
                .add(PigsteelBlocks.DEEPSLATE_PIGSTEEL_ORE)
                .add(PigsteelBlocks.BLUE_PIGSTEEL_ORE)
                .add(PigsteelBlocks.BRIMSTONE_PIGSTEEL_ORE);


        getOrCreateTagBuilder(PigsteelBlockTags.PIGSTEEL_BLOCKS)
                .add(PigsteelBlocks.PIGSTEEL_BLOCK).add(PigsteelBlocks.WAXED_PIGSTEEL_BLOCK)
                .add(PigsteelBlocks.INFECTED_PIGSTEEL).add(PigsteelBlocks.WAXED_INFECTED_PIGSTEEL)
                .add(PigsteelBlocks.CORRUPTED_PIGSTEEL).add(PigsteelBlocks.WAXED_CORRUPTED_PIGSTEEL)
                .add(PigsteelBlocks.ZOMBIFIED_PIGSTEEL).add(PigsteelBlocks.WAXED_ZOMBIFIED_PIGSTEEL)
                .add(PigsteelBlocks.PIGSTEEL_BLOCK).add(PigsteelBlocks.WAXED_PIGSTEEL_BLOCK)
                .add(PigsteelBlocks.CUT_PIGSTEEL).add(PigsteelBlocks.WAXED_CUT_PIGSTEEL)
                .add(PigsteelBlocks.INFECTED_CUT_PIGSTEEL).add(PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL)
                .add(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL).add(PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL)
                .add(PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL).add(PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .forceAddTag(PigsteelBlockTags.PIGSTEEL_ORES)
                .forceAddTag(PigsteelBlockTags.PIGSTEEL_BLOCKS)
                .add(PigsteelBlocks.CUT_PIGSTEEL_STAIRS).add(PigsteelBlocks.WAXED_CUT_PIGSTEEL_STAIRS)
                .add(PigsteelBlocks.INFECTED_CUT_PIGSTEEL_STAIRS).add(PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL_STAIRS)
                .add(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_STAIRS).add(PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS)
                .add(PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_STAIRS).add(PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS)
                .add(PigsteelBlocks.CUT_PIGSTEEL_SLAB).add(PigsteelBlocks.WAXED_CUT_PIGSTEEL_SLAB)
                .add(PigsteelBlocks.INFECTED_CUT_PIGSTEEL_SLAB).add(PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL_SLAB)
                .add(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_SLAB).add(PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL_SLAB)
                .add(PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_SLAB).add(PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL_SLAB)
                .add(PigsteelBlocks.PIGSTEEL_LANTERN).add(PigsteelBlocks.PIGSTEEL_SOUL_LANTERN);


        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .forceAddTag(PigsteelBlockTags.PIGSTEEL_BLOCKS)
                .add(PigsteelBlocks.CUT_PIGSTEEL_STAIRS).add(PigsteelBlocks.WAXED_CUT_PIGSTEEL_STAIRS)
                .add(PigsteelBlocks.INFECTED_CUT_PIGSTEEL_STAIRS).add(PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL_STAIRS)
                .add(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_STAIRS).add(PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS)
                .add(PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_STAIRS).add(PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS)
                .add(PigsteelBlocks.CUT_PIGSTEEL_SLAB).add(PigsteelBlocks.WAXED_CUT_PIGSTEEL_SLAB)
                .add(PigsteelBlocks.INFECTED_CUT_PIGSTEEL_SLAB).add(PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL_SLAB)
                .add(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_SLAB).add(PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL_SLAB)
                .add(PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_SLAB).add(PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL_SLAB);

        getOrCreateTagBuilder(BlockTags.BEACON_BASE_BLOCKS).forceAddTag(PigsteelBlockTags.PIGSTEEL_BLOCKS);

        getOrCreateTagBuilder(PigsteelBlockTags.C_IRON_BLOCKS).add(PigsteelBlocks.PIGSTEEL_BLOCK);
        getOrCreateTagBuilder(PigsteelBlockTags.C_ORES).forceAddTag(PigsteelBlockTags.PIGSTEEL_ORES);
    }
}
