package net.digitalpear.pigsteel.common.blocks;

import net.digitalpear.pigsteel.PigsteelMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class CutPigsteelSlabBlocks extends SlabBlock {
    private final BlockState resultBlock;

    public CutPigsteelSlabBlocks(BlockState resultBlock, Settings settings) {
        super(settings.ticksRandomly());
        this.resultBlock = resultBlock;
    }

    //Rusting
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isClient) {
            if (world.getDimension().isBedWorking()) {
                if (random.nextInt(10) > PigsteelMod.pigsteelRustingChance) {
                    SlabType half = state.get(Properties.SLAB_TYPE);
                    Boolean watered = state.get(Properties.WATERLOGGED);

                    world.setBlockState(pos, this.resultBlock.with(Properties.WATERLOGGED, watered).with(Properties.SLAB_TYPE, half));
                }
            }
        }
    }
}
