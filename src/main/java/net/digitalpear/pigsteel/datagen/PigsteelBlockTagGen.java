package net.digitalpear.pigsteel.datagen;

import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.digitalpear.pigsteel.init.tags.PigsteelBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
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

        getOrCreateTagBuilder(PigsteelBlockTags.WARM_BLOCKS).forceAddTag(BlockTags.FIRE).forceAddTag(BlockTags.CAMPFIRES);
        getOrCreateTagBuilder(PigsteelBlockTags.COLD_BLOCKS).forceAddTag(BlockTags.ICE).forceAddTag(BlockTags.SNOW);


        getOrCreateTagBuilder(PigsteelBlockTags.PIGSTEEL_BLOCKS)
                .add(PigsteelBlocks.PIGSTEEL_BLOCK).add(PigsteelBlocks.WAXED_PIGSTEEL_BLOCK)
                .add(PigsteelBlocks.INFECTED_PIGSTEEL).add(PigsteelBlocks.WAXED_INFECTED_PIGSTEEL)
                .add(PigsteelBlocks.CORRUPTED_PIGSTEEL).add(PigsteelBlocks.WAXED_CORRUPTED_PIGSTEEL)
                .add(PigsteelBlocks.ZOMBIFIED_PIGSTEEL).add(PigsteelBlocks.WAXED_ZOMBIFIED_PIGSTEEL)
                .add(PigsteelBlocks.PIGSTEEL_BLOCK).add(PigsteelBlocks.WAXED_PIGSTEEL_BLOCK);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .forceAddTag(PigsteelBlockTags.PIGSTEEL_ORES)
                .forceAddTag(PigsteelBlockTags.PIGSTEEL_BLOCKS)
                .add(PigsteelBlocks.CUT_PIGSTEEL_STAIRS).add(PigsteelBlocks.WAXED_CUT_PIGSTEEL_STAIRS)
                .add(PigsteelBlocks.INFECTED_CUT_PIGSTEEL_STAIRS).add(PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL_STAIRS)
                .add(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_STAIRS).add(PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS)
                .add(PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_STAIRS).add(PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS)
                .add(PigsteelBlocks.PIGSTEEL_LANTERN).add(PigsteelBlocks.PIGSTEEL_SOUL_LANTERN);

        for (int i = 0; i < 4; i++){
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(PigsteelBlocks.cutPigsteel.getZombifiables().get(i));
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(PigsteelBlocks.cutPigsteel.getWaxed().get(i));

            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(PigsteelBlocks.cutPigsteelSlabs.getZombifiables().get(i));
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(PigsteelBlocks.cutPigsteelSlabs.getWaxed().get(i));

            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(PigsteelBlocks.pigsteelBars.getZombifiables().get(i));
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(PigsteelBlocks.pigsteelBars.getWaxed().get(i));



            getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL).add(PigsteelBlocks.cutPigsteel.getZombifiables().get(i));
            getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL).add(PigsteelBlocks.cutPigsteel.getWaxed().get(i));

            getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL).add(PigsteelBlocks.cutPigsteelSlabs.getZombifiables().get(i));
            getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL).add(PigsteelBlocks.cutPigsteelSlabs.getWaxed().get(i));

            getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL).add(PigsteelBlocks.pigsteelBars.getZombifiables().get(i));
            getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL).add(PigsteelBlocks.pigsteelBars.getWaxed().get(i));



            getOrCreateTagBuilder(PigsteelBlockTags.PIGSTEEL_BLOCKS).add(PigsteelBlocks.cutPigsteel.getZombifiables().get(i));
            getOrCreateTagBuilder(PigsteelBlockTags.PIGSTEEL_BLOCKS).add(PigsteelBlocks.cutPigsteel.getWaxed().get(i));
        }

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .forceAddTag(PigsteelBlockTags.PIGSTEEL_BLOCKS)
                .add(PigsteelBlocks.CUT_PIGSTEEL_STAIRS).add(PigsteelBlocks.WAXED_CUT_PIGSTEEL_STAIRS)
                .add(PigsteelBlocks.INFECTED_CUT_PIGSTEEL_STAIRS).add(PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL_STAIRS)
                .add(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_STAIRS).add(PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS)
                .add(PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_STAIRS).add(PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS)
;

        getOrCreateTagBuilder(BlockTags.BEACON_BASE_BLOCKS).forceAddTag(PigsteelBlockTags.PIGSTEEL_BLOCKS);

        getOrCreateTagBuilder(PigsteelBlockTags.C_IRON_BLOCKS).add(PigsteelBlocks.PIGSTEEL_BLOCK);
        getOrCreateTagBuilder(PigsteelBlockTags.C_ORES).forceAddTag(PigsteelBlockTags.PIGSTEEL_ORES);

        getOrCreateTagBuilder(BlockTags.PIGLIN_REPELLENTS).add(PigsteelBlocks.PIGSTEEL_SOUL_LANTERN);
    }
}
