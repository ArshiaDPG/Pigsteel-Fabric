package net.digitalpear.pigsteel.init.data;

import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;

public class PigsteelBlockFamilies {

    public static final BlockFamily CUT_PIGSTEEL = BlockFamilies.register(PigsteelBlocks.CUT_PIGSTEEL.getUnaffectedBlock())
            .stairs(PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getUnaffectedBlock())
            .slab(PigsteelBlocks.CUT_PIGSTEEL_SLABS.getUnaffectedBlock())
            .noGenerateModels()
            .build();

    public static final BlockFamily INFECTED_CUT_PIGSTEEL = BlockFamilies.register(PigsteelBlocks.CUT_PIGSTEEL.getInfectedBlock())
            .stairs(PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getInfectedBlock())
            .slab(PigsteelBlocks.CUT_PIGSTEEL_SLABS.getInfectedBlock())
            .noGenerateModels()
            .build();

    public static final BlockFamily CORRUPTED_CUT_PIGSTEEL = BlockFamilies.register(PigsteelBlocks.CUT_PIGSTEEL.getCorruptedBlock())
            .stairs(PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getCorruptedBlock())
            .slab(PigsteelBlocks.CUT_PIGSTEEL_SLABS.getCorruptedBlock())
            .noGenerateModels()
            .build();

    public static final BlockFamily ZOMBIFIED_CUT_PIGSTEEL = BlockFamilies.register(PigsteelBlocks.CUT_PIGSTEEL.getZombifiedBlock())
            .stairs(PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getZombifiedBlock())
            .slab(PigsteelBlocks.CUT_PIGSTEEL_SLABS.getZombifiedBlock())
            .noGenerateModels()
            .build();

    public static final BlockFamily WAXED_CUT_PIGSTEEL = BlockFamilies.register(PigsteelBlocks.CUT_PIGSTEEL.getWaxedUnaffectedBlock())
            .stairs(PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getWaxedUnaffectedBlock())
            .slab(PigsteelBlocks.CUT_PIGSTEEL_SLABS.getWaxedUnaffectedBlock())
            .noGenerateModels()
            .build();

    public static final BlockFamily WAXED_INFECTED_CUT_PIGSTEEL = BlockFamilies.register(PigsteelBlocks.CUT_PIGSTEEL.getWaxedInfectedBlock())
            .stairs(PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getWaxedInfectedBlock())
            .slab(PigsteelBlocks.CUT_PIGSTEEL_SLABS.getWaxedInfectedBlock())
            .noGenerateModels()
            .build();

    public static final BlockFamily WAXED_CORRUPTED_CUT_PIGSTEEL = BlockFamilies.register(PigsteelBlocks.CUT_PIGSTEEL.getWaxedCorruptedBlock())
            .stairs(PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getWaxedCorruptedBlock())
            .slab(PigsteelBlocks.CUT_PIGSTEEL_SLABS.getWaxedCorruptedBlock())
            .noGenerateModels()
            .build();

    public static final BlockFamily WAXED_ZOMBIFIED_CUT_PIGSTEEL = BlockFamilies.register(PigsteelBlocks.CUT_PIGSTEEL.getWaxedZombifiedBlock())
            .stairs(PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getWaxedZombifiedBlock())
            .slab(PigsteelBlocks.CUT_PIGSTEEL_SLABS.getWaxedZombifiedBlock())
            .noGenerateModels()
            .build();
}
