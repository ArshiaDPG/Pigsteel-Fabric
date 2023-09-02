package net.digitalpear.pigsteel.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;


public class ZombifiableLanternBlock extends PigsteelLanternBlock implements Zombifiable {

    private ZombificationLevel zombificationLevel;

    public ZombifiableLanternBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
        this.zombificationLevel = ZombificationLevel.UNAFFECTED;
    }
    public ZombifiableLanternBlock(Zombifiable.ZombificationLevel zombificationLevel, Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
        this.zombificationLevel = zombificationLevel;
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return getZombificationLevel() != ZombificationLevel.ZOMBIFIED;
    }
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        if (!world.isClient()){
            tryZombify(world, state, pos);
        }
    }

    @Override
    public ZombificationLevel getZombificationLevel() {
        return this.zombificationLevel;
    }
}
