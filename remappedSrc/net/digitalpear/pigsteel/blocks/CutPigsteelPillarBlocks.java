package net.digitalpear.pigsteel.common.blocks;

import net.digitalpear.pigsteel.PigsteelMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.Random;

public class CutPigsteelPillarBlocks extends PillarBlock {
    private final BlockState resultBlock;

    public CutPigsteelPillarBlocks(BlockState resultBlock, Settings settings) {
        super(settings.ticksRandomly());
        this.resultBlock = resultBlock;
    }

    //Rusting
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isClient) {
            if (world.getDimension().isBedWorking()) {
                if (random.nextInt(10) > PigsteelMod.pigsteelRustingChance) {
                    Direction direction = state.get(Properties.HORIZONTAL_FACING);
                    world.setBlockState(pos, this.resultBlock.with(Properties.HORIZONTAL_FACING, direction));
                }
            }
        }
    }
}
