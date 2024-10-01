package net.digitalpear.pigsteel.common.blocks;

import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Waterloggable;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;

import java.util.Optional;


public class ZombifiableLanternBlock extends PigsteelLanternBlock implements Zombifiable {

    private ZombificationLevel zombificationLevel;

    public ZombifiableLanternBlock(Settings settings) {
        super(settings);
        this.zombificationLevel = ZombificationLevel.UNAFFECTED;
    }

    public ZombifiableLanternBlock(Zombifiable.ZombificationLevel zombificationLevel, Settings settings) {
        super(settings);
        this.zombificationLevel = zombificationLevel;
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return getDegradationLevel() != ZombificationLevel.ZOMBIFIED;
    }
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isClient()){
            tickDegradation(state, world, pos, random);
        }
    }

    @Override
    public Optional<BlockState> getDegradationResult(BlockState state) {
        return Optional.of(PigsteelBlocks.PIGSTEEL_ZOMBIFYING_MAP.get(state.getBlock()).getStateWithProperties(state));
    }

    @Override
    public float getDegradationChanceMultiplier() {
        return 0;
    }

    @Override
    public ZombificationLevel getDegradationLevel() {
        return zombificationLevel;
    }
}
