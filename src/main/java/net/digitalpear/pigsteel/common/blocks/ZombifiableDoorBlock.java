package net.digitalpear.pigsteel.common.blocks;

import net.digitalpear.pigsteel.init.data.ZombifiableBlockRegistry;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

import java.util.Optional;

public class ZombifiableDoorBlock extends DoorBlock implements Zombifiable {
    private ZombificationLevel zombificationLevel;

    public ZombifiableDoorBlock(BlockSetType type, Zombifiable.ZombificationLevel level, Settings settings) {
        super(type, settings);
        this.zombificationLevel = level;
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return getDegradationLevel() != ZombificationLevel.ZOMBIFIED;
    }

    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(DoorBlock.HALF) == DoubleBlockHalf.LOWER) {
            this.tickDegradation(state, world, pos, random);
        }
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
