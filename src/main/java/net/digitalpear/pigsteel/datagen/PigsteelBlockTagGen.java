package net.digitalpear.pigsteel.datagen;

import net.digitalpear.pigsteel.Pigsteel;
import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.digitalpear.pigsteel.init.tags.PigsteelBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

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
                .add(PigsteelBlocks.PORKSLAG);


        getOrCreateTagBuilder(PigsteelBlockTags.ZOMBIFICATION_DECELERATION)
                .add(Blocks.CRIMSON_NYLIUM)
                .add(Blocks.CRIMSON_ROOTS)
                .add(Blocks.CRIMSON_FUNGUS)
                .add(Blocks.POTTED_CRIMSON_FUNGUS)
                .add(Blocks.POTTED_CRIMSON_ROOTS);


        getOrCreateTagBuilder(PigsteelBlockTags.ZOMBIFICATION_ACCELERATION)
                .add(Blocks.WARPED_NYLIUM)
                .add(Blocks.WARPED_ROOTS)
                .add(Blocks.WARPED_FUNGUS)
                .add(Blocks.POTTED_WARPED_FUNGUS)
                .add(Blocks.POTTED_WARPED_ROOTS);



        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .forceAddTag(PigsteelBlockTags.PIGSTEEL_ORES)
                .forceAddTag(PigsteelBlockTags.PIGSTEEL_BLOCKS)
                .add(PigsteelBlocks.CUT_PIGSTEEL_STAIRS).add(PigsteelBlocks.WAXED_CUT_PIGSTEEL_STAIRS)
                .add(PigsteelBlocks.INFECTED_CUT_PIGSTEEL_STAIRS).add(PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL_STAIRS)
                .add(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_STAIRS).add(PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS)
                .add(PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_STAIRS).add(PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS)
                .add(PigsteelBlocks.PIGSTEEL_LANTERN).add(PigsteelBlocks.PIGSTEEL_SOUL_LANTERN);

        PigsteelBlocks.refinedPigsteel.getAllBlocks().forEach(block -> {
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
            getOrCreateTagBuilder(PigsteelBlockTags.PIGSTEEL_BLOCKS).add(block);
        });
        PigsteelBlocks.cutPigsteel.getAllBlocks().forEach(block -> {
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
            getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL).add(block);
            getOrCreateTagBuilder(PigsteelBlockTags.PIGSTEEL_BLOCKS).add(block);
        });
        PigsteelBlocks.cutPigsteelSlabs.getAllBlocks().forEach(block -> {
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
            getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL).add(block);
        });


        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .forceAddTag(PigsteelBlockTags.PIGSTEEL_BLOCKS)
                .add(PigsteelBlocks.CUT_PIGSTEEL_STAIRS).add(PigsteelBlocks.WAXED_CUT_PIGSTEEL_STAIRS)
                .add(PigsteelBlocks.INFECTED_CUT_PIGSTEEL_STAIRS).add(PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL_STAIRS)
                .add(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_STAIRS).add(PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS)
                .add(PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_STAIRS).add(PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS);

        getOrCreateTagBuilder(BlockTags.BEACON_BASE_BLOCKS).forceAddTag(PigsteelBlockTags.PIGSTEEL_BLOCKS);

        getOrCreateTagBuilder(PigsteelBlockTags.C_ORES).forceAddTag(PigsteelBlockTags.PIGSTEEL_ORES);

        getOrCreateTagBuilder(BlockTags.PIGLIN_REPELLENTS).add(PigsteelBlocks.PIGSTEEL_SOUL_LANTERN);
    }
}
