package net.digitalpear.pigsteel.common.datagen.tags;

import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.digitalpear.pigsteel.init.tags.PigsteelBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class PigsteelBlockTagProvider extends FabricTagProvider<Block> {

    /**
     * Constructs a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output           the {@link FabricDataOutput} instance
     * @param registriesFuture the backing registry for the tag type
     */
    public PigsteelBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, Registries.BLOCK.getKey(), registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {

        getOrCreateTagBuilder(PigsteelBlockTags.PIGSTEEL_ORES)
                .add(PigsteelBlocks.PORKSLAG);


        getOrCreateTagBuilder(PigsteelBlockTags.ZOMBIFICATION_DECELERATION)
                .add(Blocks.FIRE)
                .add(Blocks.SOUL_FIRE)
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
                .add(PigsteelBlocks.PORKSLAG)
                .add(PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK);

        PigsteelBlocks.REFINED_PIGSTEEL.getAllBlocks().forEach(block -> {
            getOrCreateTagBuilder(PigsteelBlockTags.PIGSTEEL_BLOCKS).add(block);
        });
        PigsteelBlocks.CUT_PIGSTEEL.getAllBlocks().forEach(block -> {
            getOrCreateTagBuilder(PigsteelBlockTags.PIGSTEEL_BLOCKS).add(block);
        });
        PigsteelBlocks.CUT_PIGSTEEL_SLABS.getAllBlocks().forEach(block -> {
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
            getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL).add(block);
        });

        PigsteelBlocks.PIGSTEEL_LANTERNS.getAllBlocks().forEach(block -> {
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
        });
        PigsteelBlocks.PIGSTEEL_SOUL_LANTERNS.getAllBlocks().forEach(block -> {
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
            getOrCreateTagBuilder(BlockTags.PIGLIN_REPELLENTS).add(block);
        });
        PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getAllBlocks().forEach(block -> {
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
            getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL).add(block);
        });


        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .forceAddTag(PigsteelBlockTags.PIGSTEEL_BLOCKS)
                .forceAddTag(PigsteelBlockTags.PIGSTEEL_ORES)
                .add(PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK);

        getOrCreateTagBuilder(BlockTags.BEACON_BASE_BLOCKS).forceAddTag(PigsteelBlockTags.PIGSTEEL_BLOCKS);

        getOrCreateTagBuilder(ConventionalBlockTags.ORES).forceAddTag(PigsteelBlockTags.PIGSTEEL_ORES);
    }
}
