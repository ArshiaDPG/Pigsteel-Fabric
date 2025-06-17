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

public class PigsteelBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public PigsteelBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {

        getTagBuilder(PigsteelBlockTags.PIGSTEEL_ORES)
                .add(getId(PigsteelBlocks.PORKSLAG));


        getTagBuilder(PigsteelBlockTags.ZOMBIFICATION_DECELERATION)
                .add(getId(Blocks.FIRE))
                .add(getId(Blocks.SOUL_FIRE))
                .add(getId(Blocks.CRIMSON_NYLIUM))
                .add(getId(Blocks.CRIMSON_ROOTS))
                .add(getId(Blocks.CRIMSON_FUNGUS))
                .add(getId(Blocks.POTTED_CRIMSON_FUNGUS))
                .add(getId(Blocks.POTTED_CRIMSON_ROOTS));


        getTagBuilder(PigsteelBlockTags.ZOMBIFICATION_ACCELERATION)
                .add(getId(Blocks.WARPED_NYLIUM))
                .add(getId(Blocks.WARPED_ROOTS))
                .add(getId(Blocks.WARPED_FUNGUS))
                .add(getId(Blocks.POTTED_WARPED_FUNGUS))
                .add(getId(Blocks.POTTED_WARPED_ROOTS));



        getTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .addTag(PigsteelBlockTags.PIGSTEEL_ORES.id())
                .addTag(PigsteelBlockTags.PIGSTEEL_BLOCKS.id())
                .add(getId(PigsteelBlocks.PORKSLAG))
                .add(getId(PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK));

        PigsteelBlocks.REFINED_PIGSTEEL.getAllBlocks().forEach(block -> {
            getTagBuilder(PigsteelBlockTags.PIGSTEEL_BLOCKS).add(getId(block));
        });
        PigsteelBlocks.CUT_PIGSTEEL.getAllBlocks().forEach(block -> {
            getTagBuilder(PigsteelBlockTags.PIGSTEEL_BLOCKS).add(getId(block));
        });
        PigsteelBlocks.CUT_PIGSTEEL_SLABS.getAllBlocks().forEach(block -> {
            getTagBuilder(BlockTags.PICKAXE_MINEABLE).add(getId(block));
            getTagBuilder(BlockTags.NEEDS_STONE_TOOL).add(getId(block));
        });

        PigsteelBlocks.PIGSTEEL_LANTERNS.getAllBlocks().forEach(block -> {
            getTagBuilder(BlockTags.PICKAXE_MINEABLE).add(getId(block));
        });
        PigsteelBlocks.PIGSTEEL_SOUL_LANTERNS.getAllBlocks().forEach(block -> {
            getTagBuilder(BlockTags.PICKAXE_MINEABLE).add(getId(block));
            getTagBuilder(BlockTags.PIGLIN_REPELLENTS).add(getId(block));
        });
        PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getAllBlocks().forEach(block -> {
            getTagBuilder(BlockTags.PICKAXE_MINEABLE).add(getId(block));
            getTagBuilder(BlockTags.NEEDS_STONE_TOOL).add(getId(block));
        });


        getTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .addTag(PigsteelBlockTags.PIGSTEEL_BLOCKS.id())
                .addTag(PigsteelBlockTags.PIGSTEEL_ORES.id())
                .add(getId(PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK));

        getTagBuilder(BlockTags.BEACON_BASE_BLOCKS).addTag(PigsteelBlockTags.PIGSTEEL_BLOCKS.id());

        getTagBuilder(ConventionalBlockTags.ORES).addTag(PigsteelBlockTags.PIGSTEEL_ORES.id());
    }

    public static Identifier getId(Block block){
        return Registries.BLOCK.getId(block);
    }
}
