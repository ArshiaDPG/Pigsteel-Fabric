package net.digitalpear.pigsteel.common.datagen.tags;

import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.digitalpear.pigsteel.init.tags.PigsteelBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class PigsteelBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public PigsteelBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {

        valueLookupBuilder(PigsteelBlockTags.PIGSTEEL_ORES)
                .add(getId(PigsteelBlocks.PORKSLAG));


        valueLookupBuilder(PigsteelBlockTags.ZOMBIFICATION_DECELERATION)
                .add(getId(Blocks.FIRE))
                .add(getId(Blocks.SOUL_FIRE))
                .add(getId(Blocks.CRIMSON_NYLIUM))
                .add(getId(Blocks.CRIMSON_ROOTS))
                .add(getId(Blocks.CRIMSON_FUNGUS))
                .add(getId(Blocks.POTTED_CRIMSON_FUNGUS))
                .add(getId(Blocks.POTTED_CRIMSON_ROOTS));


        valueLookupBuilder(PigsteelBlockTags.ZOMBIFICATION_ACCELERATION)
                .add(getId(Blocks.WARPED_NYLIUM))
                .add(getId(Blocks.WARPED_ROOTS))
                .add(getId(Blocks.WARPED_FUNGUS))
                .add(getId(Blocks.POTTED_WARPED_FUNGUS))
                .add(getId(Blocks.POTTED_WARPED_ROOTS));



        valueLookupBuilder(BlockTags.PICKAXE_MINEABLE)
                .forceAddTag(PigsteelBlockTags.PIGSTEEL_ORES)
                .forceAddTag(PigsteelBlockTags.PIGSTEEL_BLOCKS)
                .add(PigsteelBlocks.PORKSLAG)
                .add(PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK);

        PigsteelBlocks.REFINED_PIGSTEEL.getAllBlocks().forEach(block -> {
            valueLookupBuilder(PigsteelBlockTags.PIGSTEEL_BLOCKS).add(block);
        });
        PigsteelBlocks.CUT_PIGSTEEL.getAllBlocks().forEach(block -> {
            valueLookupBuilder(PigsteelBlockTags.PIGSTEEL_BLOCKS).add(block);
        });
        PigsteelBlocks.CUT_PIGSTEEL_SLABS.getAllBlocks().forEach(block -> {
            valueLookupBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
            valueLookupBuilder(BlockTags.NEEDS_STONE_TOOL).add(block);
        });

        PigsteelBlocks.PIGSTEEL_LANTERNS.getAllBlocks().forEach(block -> {
            valueLookupBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
            valueLookupBuilder(BlockTags.LANTERNS).add(block);
        });
        PigsteelBlocks.PIGSTEEL_SOUL_LANTERNS.getAllBlocks().forEach(block -> {
            valueLookupBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
            valueLookupBuilder(BlockTags.PIGLIN_REPELLENTS).add(block);
            valueLookupBuilder(BlockTags.LANTERNS).add(block);
        });
        PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getAllBlocks().forEach(block -> {
            valueLookupBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
            valueLookupBuilder(BlockTags.NEEDS_STONE_TOOL).add(block);
        });


        PigsteelBlocks.CHISELED_PIGSTEEL.getAllBlocks().forEach(block -> {
            valueLookupBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
            valueLookupBuilder(BlockTags.NEEDS_STONE_TOOL).add(block);
        });
        PigsteelBlocks.PIGSTEEL_DOORS.getAllBlocks().forEach(block -> {
            valueLookupBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
            valueLookupBuilder(BlockTags.NEEDS_STONE_TOOL).add(block);
        });
        PigsteelBlocks.PIGSTEEL_TRAPDOORS.getAllBlocks().forEach(block -> {
            valueLookupBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
            valueLookupBuilder(BlockTags.NEEDS_STONE_TOOL).add(block);
        });
        PigsteelBlocks.PIGSTEEL_BARRELS.getAllBlocks().forEach(block -> {
            valueLookupBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
            valueLookupBuilder(BlockTags.NEEDS_STONE_TOOL).add(block);
        });
        PigsteelBlocks.PIGSTEEL_BARS.getAllBlocks().forEach(block -> {
            valueLookupBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
            valueLookupBuilder(BlockTags.NEEDS_STONE_TOOL).add(block);
            valueLookupBuilder(BlockTags.BARS).add(block);
        });

        valueLookupBuilder(BlockTags.NEEDS_STONE_TOOL)
                .forceAddTag(PigsteelBlockTags.PIGSTEEL_BLOCKS)
                .forceAddTag(PigsteelBlockTags.PIGSTEEL_ORES)
                .add(PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK);

        valueLookupBuilder(BlockTags.BEACON_BASE_BLOCKS).forceAddTag(PigsteelBlockTags.PIGSTEEL_BLOCKS);

        valueLookupBuilder(ConventionalBlockTags.ORES).forceAddTag(PigsteelBlockTags.PIGSTEEL_ORES);
    }

    public static Block getId(Block block){
        return block;
    }
}
