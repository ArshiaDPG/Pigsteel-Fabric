package net.digitalpear.pigsteel.common.blocks;

import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.block.PaneBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

import java.util.Optional;

public class ZombifiablePaneBlock extends PaneBlock implements Zombifiable {
    private ZombificationLevel zombificationLevel;

    public ZombifiablePaneBlock(Settings settings) {
        super(settings);
        this.zombificationLevel = ZombificationLevel.UNAFFECTED;
    }
    public ZombifiablePaneBlock(ZombificationLevel zombificationLevel, Settings settings) {
        super(settings);
        this.zombificationLevel = zombificationLevel;
    }

    @Override
    public MapColor getDefaultMapColor() {
        return getDegradationLevel().getMapColor();
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        if (!world.isClient()){
            tryZombify(world, state, pos);
        }
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return getDegradationLevel() != ZombificationLevel.ZOMBIFIED;
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
