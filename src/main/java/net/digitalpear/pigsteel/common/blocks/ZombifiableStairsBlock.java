package net.digitalpear.pigsteel.common.blocks;

import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.digitalpear.pigsteel.init.data.ZombifiableBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.block.StairsBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

import java.util.Map;
import java.util.Optional;

public class ZombifiableStairsBlock extends StairsBlock implements Zombifiable {

    private ZombificationLevel zombificationLevel;
    private static final Map<ZombificationLevel, Block> levelToBlockMap = Map.of(
            ZombificationLevel.UNAFFECTED, PigsteelBlocks.cutPigsteel.getUnaffectedBlock(),
            ZombificationLevel.INFECTED, PigsteelBlocks.cutPigsteel.getInfectedBlock(),
            ZombificationLevel.CORRUPTED, PigsteelBlocks.cutPigsteel.getCorruptedBlock(),
            ZombificationLevel.ZOMBIFIED, PigsteelBlocks.cutPigsteel.getZombifiedBlock()
    );
    public ZombifiableStairsBlock(Settings settings) {
        super(levelToBlockMap.get(ZombificationLevel.UNAFFECTED).getDefaultState(), settings);
        this.zombificationLevel = ZombificationLevel.UNAFFECTED;
    }
    public ZombifiableStairsBlock(ZombificationLevel zombificationLevel, Settings settings) {
        super(levelToBlockMap.get(zombificationLevel).getDefaultState(), settings);
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
