package net.digitalpear.pigsteel.blocks;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.digitalpear.pigsteel.PigsteelMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.StairShape;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.Random;

public class CutPigsteelStairsBlocks extends StairsBlock {
    private final BlockState resultBlock;

    public CutPigsteelStairsBlocks(BlockState resultBlock, BlockState baseBlockState, Settings settings) {
        super(baseBlockState, settings.ticksRandomly());
        this.resultBlock = resultBlock;
    }

    //Rusting
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isClient) {
            if (world.getDimension().isBedWorking()) {
                if (random.nextInt(10) > PigsteelMod.pigsteelRustingChance) {
                    Direction direction = state.get(Properties.HORIZONTAL_FACING);
                    Boolean watered = state.get(Properties.WATERLOGGED);
                    StairShape shape = state.get(Properties.STAIR_SHAPE);
                    BlockHalf half = state.get(Properties.BLOCK_HALF);

                    world.setBlockState(pos, this.resultBlock.with(Properties.HORIZONTAL_FACING, direction).with(Properties.WATERLOGGED, watered).with(Properties.STAIR_SHAPE, shape).with(Properties.BLOCK_HALF, half));
                }
            }
        }
    }
}
