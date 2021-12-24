package net.digitalpear.pigsteel.blocks.cutblocks;

import net.digitalpear.pigsteel.PigsteelMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class CorruptedCutPigsteel extends Block{
    public CorruptedCutPigsteel(Settings settings) {
        super(settings.ticksRandomly());
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isClient) {
            if (world.getDimension().isBedWorking()) {
                world.setBlockState(pos, PigsteelMod.ZOMBIFIED_CUT_PIGSTEEL.getDefaultState());
            }
        }
    }

}
