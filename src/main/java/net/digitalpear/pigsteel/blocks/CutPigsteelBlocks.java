package net.digitalpear.pigsteel.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class CutPigsteelBlocks extends Block {
    private final BlockState resultBlock;

    public CutPigsteelBlocks(BlockState resultBlock, Settings settings) {
        super(settings.ticksRandomly());
        this.resultBlock = resultBlock;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isClient) {
            if (world.getDimension().isBedWorking()) {
                if (random.nextInt(10) > 6) {
                    world.setBlockState(pos, this.resultBlock);
                }
            }
        }
    }
}
