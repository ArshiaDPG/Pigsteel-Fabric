package net.digitalpear.pigsteel.common.blocks;

import net.digitalpear.pigsteel.init.data.ZombifiableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

import java.util.Optional;

public class ZombifiableTrapdoorBlock extends TrapdoorBlock implements Zombifiable {

    private ZombificationLevel zombificationLevel;

    public ZombifiableTrapdoorBlock(BlockSetType type, Zombifiable.ZombificationLevel level, Settings settings) {
        super(type, settings);
        this.zombificationLevel = level;
    }


    @Override
    public MapColor getDefaultMapColor() {
        return getDegradationLevel().getMapColor();
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        if (!world.isClient()){
            tickDegradation(state, world, pos, random);
        }
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return getDegradationLevel() != ZombificationLevel.ZOMBIFIED;
    }

    @Override
    public Optional<BlockState> getDegradationResult(BlockState state) {
        return Optional.of(ZombifiableBlockRegistry.getPigsteelZombifyingMap().get(state.getBlock()).getStateWithProperties(state));
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
