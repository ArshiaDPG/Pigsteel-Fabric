package net.digitalpear.pigsteel.blocks;

import net.digitalpear.pigsteel.PigsteelMod;
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

    //Rusting
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isClient) {
            if (world.getDimension().isBedWorking()) {
                if (random.nextInt(10) > PigsteelMod.pigsteelRustingChance) {
                    world.setBlockState(pos, this.resultBlock);
                }
            }
        }
    }
}
