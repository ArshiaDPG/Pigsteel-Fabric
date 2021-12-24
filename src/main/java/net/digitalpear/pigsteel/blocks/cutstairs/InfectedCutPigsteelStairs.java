package net.digitalpear.pigsteel.blocks.cutstairs;

import net.digitalpear.pigsteel.PigsteelMod;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.StairShape;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.Random;

public class InfectedCutPigsteelStairs extends StairsBlock{
    public InfectedCutPigsteelStairs(BlockState baseBlockState, Settings settings) {
        super(baseBlockState, FabricBlockSettings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.NETHERITE).ticksRandomly());
    }

    @Override
    //Eroding
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isClient) {
            if (world.getDimension().isBedWorking()) {
                Direction direction = state.get(Properties.HORIZONTAL_FACING);
                Boolean watered = state.get(Properties.WATERLOGGED);
                StairShape shape = state.get(Properties.STAIR_SHAPE);
                BlockHalf half = state.get(Properties.BLOCK_HALF);

                world.setBlockState(pos, PigsteelMod.CORRUPTED_CUT_PIGSTEEL_STAIRS.getDefaultState().with(Properties.HORIZONTAL_FACING, direction).with(Properties.WATERLOGGED, watered).with(Properties.STAIR_SHAPE, shape).with(Properties.BLOCK_HALF, half));
        }
        }
    }
}
