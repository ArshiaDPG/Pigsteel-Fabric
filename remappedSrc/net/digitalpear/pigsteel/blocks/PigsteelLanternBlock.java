package net.digitalpear.pigsteel.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class PigsteelLanternBlock extends LanternBlock {

    protected static final VoxelShape STANDING_SHAPE;
    protected static final VoxelShape HANGING_SHAPE;
    public PigsteelLanternBlock(Settings settings) {
        super(settings);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return (Boolean)state.get(HANGING) ? HANGING_SHAPE : STANDING_SHAPE;
    }

    static {
        STANDING_SHAPE = VoxelShapes.union(Block.createCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 9.0D, 12.0D), Block.createCuboidShape(7.0D, 9.0D, 7.0D, 9.0D, 10.0D, 9.0D));
        HANGING_SHAPE = VoxelShapes.union(Block.createCuboidShape(4.0D, 3.0D, 4.0D, 12.0D, 12.0D, 12.0D), Block.createCuboidShape(7.0D, 12.0D, 7.0D, 9.0D, 13.0D, 9.0D));
    }
}
