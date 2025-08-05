package net.digitalpear.pigsteel.init.data;

import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;

public class PigsteelBlockFamilies {

    public static final BlockFamily PIGSTEEL = BlockFamilies.register(PigsteelBlocks.REFINED_PIGSTEEL.getUnaffectedBlock())
            .cut(PigsteelBlocks.CUT_PIGSTEEL.getUnaffectedBlock())
            .stairs(PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getUnaffectedBlock())
            .slab(PigsteelBlocks.CUT_PIGSTEEL_SLABS.getUnaffectedBlock())
            .door(PigsteelBlocks.PIGSTEEL_DOORS.getUnaffectedBlock())
            .trapdoor(PigsteelBlocks.PIGSTEEL_TRAPDOORS.getUnaffectedBlock())
            .chiseled(PigsteelBlocks.CHISELED_PIGSTEEL.getUnaffectedBlock())
            .group("cut_pigsteel")
            .build();

    public static final BlockFamily INFECTED_PIGSTEEL = BlockFamilies.register(PigsteelBlocks.REFINED_PIGSTEEL.getInfectedBlock())
            .cut(PigsteelBlocks.CUT_PIGSTEEL.getInfectedBlock())
            .stairs(PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getInfectedBlock())
            .slab(PigsteelBlocks.CUT_PIGSTEEL_SLABS.getInfectedBlock())
            .door(PigsteelBlocks.PIGSTEEL_DOORS.getInfectedBlock())
            .trapdoor(PigsteelBlocks.PIGSTEEL_TRAPDOORS.getInfectedBlock())
            .chiseled(PigsteelBlocks.CHISELED_PIGSTEEL.getInfectedBlock())
            .group("infected_cut_pigsteel")
            .build();

    public static final BlockFamily CORRUPTED_PIGSTEEL = BlockFamilies.register(PigsteelBlocks.REFINED_PIGSTEEL.getCorruptedBlock())
            .cut(PigsteelBlocks.CUT_PIGSTEEL.getCorruptedBlock())
            .stairs(PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getCorruptedBlock())
            .slab(PigsteelBlocks.CUT_PIGSTEEL_SLABS.getCorruptedBlock())
            .door(PigsteelBlocks.PIGSTEEL_DOORS.getCorruptedBlock())
            .trapdoor(PigsteelBlocks.PIGSTEEL_TRAPDOORS.getCorruptedBlock())
            .chiseled(PigsteelBlocks.CHISELED_PIGSTEEL.getCorruptedBlock())
            .group("corrupted_cut_pigsteel")
            .build();

    public static final BlockFamily ZOMBIFIED_PIGSTEEL = BlockFamilies.register(PigsteelBlocks.REFINED_PIGSTEEL.getZombifiedBlock())
            .cut(PigsteelBlocks.CUT_PIGSTEEL.getZombifiedBlock())
            .stairs(PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getZombifiedBlock())
            .slab(PigsteelBlocks.CUT_PIGSTEEL_SLABS.getZombifiedBlock())
            .door(PigsteelBlocks.PIGSTEEL_DOORS.getZombifiedBlock())
            .trapdoor(PigsteelBlocks.PIGSTEEL_TRAPDOORS.getZombifiedBlock())
            .chiseled(PigsteelBlocks.CHISELED_PIGSTEEL.getZombifiedBlock())
            .group("zombified_cut_pigsteel")
            .build();

    public static final BlockFamily WAXED_PIGSTEEL = BlockFamilies.register(PigsteelBlocks.REFINED_PIGSTEEL.getWaxedUnaffectedBlock())
            .cut(PigsteelBlocks.CUT_PIGSTEEL.getWaxedUnaffectedBlock())
            .stairs(PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getWaxedUnaffectedBlock())
            .slab(PigsteelBlocks.CUT_PIGSTEEL_SLABS.getWaxedUnaffectedBlock())
            .door(PigsteelBlocks.PIGSTEEL_DOORS.getWaxedUnaffectedBlock())
            .trapdoor(PigsteelBlocks.PIGSTEEL_TRAPDOORS.getWaxedUnaffectedBlock())
            .chiseled(PigsteelBlocks.CHISELED_PIGSTEEL.getWaxedUnaffectedBlock())
            .group("waxed_cut_pigsteel")
            .build();

    public static final BlockFamily WAXED_INFECTED_PIGSTEEL = BlockFamilies.register(PigsteelBlocks.REFINED_PIGSTEEL.getWaxedInfectedBlock())
            .cut(PigsteelBlocks.CUT_PIGSTEEL.getWaxedInfectedBlock())
            .stairs(PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getWaxedInfectedBlock())
            .slab(PigsteelBlocks.CUT_PIGSTEEL_SLABS.getWaxedInfectedBlock())
            .door(PigsteelBlocks.PIGSTEEL_DOORS.getWaxedInfectedBlock())
            .trapdoor(PigsteelBlocks.PIGSTEEL_TRAPDOORS.getWaxedInfectedBlock())
            .chiseled(PigsteelBlocks.CHISELED_PIGSTEEL.getWaxedInfectedBlock())
            .group("waxed_infected_cut_pigsteel")
            .build();

    public static final BlockFamily WAXED_CORRUPTED_PIGSTEEL = BlockFamilies.register(PigsteelBlocks.REFINED_PIGSTEEL.getWaxedCorruptedBlock())
            .cut(PigsteelBlocks.CUT_PIGSTEEL.getWaxedCorruptedBlock())
            .stairs(PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getWaxedCorruptedBlock())
            .slab(PigsteelBlocks.CUT_PIGSTEEL_SLABS.getWaxedCorruptedBlock())
            .door(PigsteelBlocks.PIGSTEEL_DOORS.getWaxedCorruptedBlock())
            .trapdoor(PigsteelBlocks.PIGSTEEL_TRAPDOORS.getWaxedCorruptedBlock())
            .chiseled(PigsteelBlocks.CHISELED_PIGSTEEL.getWaxedCorruptedBlock())
            .group("waxed_corrupted_cut_pigsteel")
            .build();

    public static final BlockFamily WAXED_ZOMBIFIED_PIGSTEEL = BlockFamilies.register(PigsteelBlocks.REFINED_PIGSTEEL.getWaxedZombifiedBlock())
            .cut(PigsteelBlocks.CUT_PIGSTEEL.getWaxedZombifiedBlock())
            .stairs(PigsteelBlocks.CUT_PIGSTEEL_STAIRS.getWaxedZombifiedBlock())
            .slab(PigsteelBlocks.CUT_PIGSTEEL_SLABS.getWaxedZombifiedBlock())
            .door(PigsteelBlocks.PIGSTEEL_DOORS.getWaxedZombifiedBlock())
            .trapdoor(PigsteelBlocks.PIGSTEEL_TRAPDOORS.getWaxedZombifiedBlock())
            .chiseled(PigsteelBlocks.CHISELED_PIGSTEEL.getWaxedZombifiedBlock())
            .group("waxed_zombified_cut_pigsteel")
            .build();
}
