package net.digitalpear.pigsteel.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.OxidizableBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class ZombifiableBlock extends OxidizableBlock {
    public ZombifiableBlock(OxidationLevel oxidationLevel, Settings settings) {
        super(oxidationLevel, settings);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random)  {
        if (!world.isClient && !world.getDimension().ultrawarm()) {
            this.tickDegradation(state, world, pos, random);
        }
    }

//    @Override
//    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random)  {
//        if (!world.isClient && !world.getDimension().ultrawarm()) {
//            if (state.isOf(PigsteelBlocks.ZOMBIFIED_PIGSTEEL)){
//                world.setBlockState(pos, Blocks.IRON_BLOCK.getDefaultState(), 3);
//                world.syncWorldEvent(2009, pos, 0);
//            }
//            this.tickDegradation(state, world, pos, random);
//        }
//    }
//
//
//    @Override
//    public boolean hasRandomTicks(BlockState state) {
//        return state.isOf(PigsteelBlocks.ZOMBIFIED_PIGSTEEL) || super.hasRandomTicks(state);
//    }
}

