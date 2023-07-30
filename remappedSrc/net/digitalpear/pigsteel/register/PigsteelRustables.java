package net.digitalpear.pigsteel.init;

import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;

public class PigsteelRustables {
    public static void init(){
        OxidizableBlocksRegistry.registerWaxableBlockPair(PigsteelBlocks.PIGSTEEL_BLOCK, PigsteelBlocks.WAXED_PIGSTEEL_BLOCK);

        OxidizableBlocksRegistry.registerWaxableBlockPair(PigsteelBlocks.CUT_PIGSTEEL, PigsteelBlocks.WAXED_CUT_PIGSTEEL);
        OxidizableBlocksRegistry.registerWaxableBlockPair(PigsteelBlocks.INFECTED_CUT_PIGSTEEL, PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL);
        OxidizableBlocksRegistry.registerWaxableBlockPair(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL, PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL);
        OxidizableBlocksRegistry.registerWaxableBlockPair(PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL, PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL);

        OxidizableBlocksRegistry.registerWaxableBlockPair(PigsteelBlocks.CUT_PIGSTEEL_STAIRS, PigsteelBlocks.WAXED_CUT_PIGSTEEL_STAIRS);
        OxidizableBlocksRegistry.registerWaxableBlockPair(PigsteelBlocks.INFECTED_CUT_PIGSTEEL_STAIRS, PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL_STAIRS);
        OxidizableBlocksRegistry.registerWaxableBlockPair(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_STAIRS, PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS);
        OxidizableBlocksRegistry.registerWaxableBlockPair(PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_STAIRS, PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS);

        OxidizableBlocksRegistry.registerWaxableBlockPair(PigsteelBlocks.CUT_PIGSTEEL_SLAB, PigsteelBlocks.WAXED_CUT_PIGSTEEL_SLAB);
        OxidizableBlocksRegistry.registerWaxableBlockPair(PigsteelBlocks.INFECTED_CUT_PIGSTEEL_SLAB, PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL_SLAB);
        OxidizableBlocksRegistry.registerWaxableBlockPair(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_SLAB, PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL_SLAB);
        OxidizableBlocksRegistry.registerWaxableBlockPair(PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_SLAB, PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL_SLAB);

        OxidizableBlocksRegistry.registerOxidizableBlockPair(PigsteelBlocks.CUT_PIGSTEEL, PigsteelBlocks.INFECTED_CUT_PIGSTEEL);
        OxidizableBlocksRegistry.registerWaxableBlockPair(PigsteelBlocks.INFECTED_CUT_PIGSTEEL, PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL, PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL);

        OxidizableBlocksRegistry.registerOxidizableBlockPair(PigsteelBlocks.CUT_PIGSTEEL_STAIRS, PigsteelBlocks.INFECTED_CUT_PIGSTEEL_STAIRS);
        OxidizableBlocksRegistry.registerWaxableBlockPair(PigsteelBlocks.INFECTED_CUT_PIGSTEEL_STAIRS, PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_STAIRS);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_STAIRS, PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_STAIRS);

        OxidizableBlocksRegistry.registerOxidizableBlockPair(PigsteelBlocks.CUT_PIGSTEEL_SLAB, PigsteelBlocks.INFECTED_CUT_PIGSTEEL_SLAB);
        OxidizableBlocksRegistry.registerWaxableBlockPair(PigsteelBlocks.INFECTED_CUT_PIGSTEEL_SLAB, PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_SLAB);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_SLAB, PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_SLAB);

    }
}
