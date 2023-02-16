package net.digitalpear.pigsteel.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.OxidizableStairsBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class ZombifiableStairsBlock extends OxidizableStairsBlock {
    public ZombifiableStairsBlock(OxidationLevel oxidationLevel, BlockState baseBlockState, Settings settings) {
        super(oxidationLevel, baseBlockState, settings);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random)  {
        if (!world.isClient) {
            if (world.getDimension().bedWorks()) {
                this.tickDegradation(state, world, pos, random);
            }
        }
    }
}
