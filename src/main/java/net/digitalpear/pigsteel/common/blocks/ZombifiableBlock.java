package net.digitalpear.pigsteel.common.blocks;

import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class ZombifiableBlock extends Block implements Zombifiable {

    private ZombificationLevel zombificationLevel;

    public ZombifiableBlock(Settings settings) {
        super(settings);
        this.zombificationLevel = ZombificationLevel.UNAFFECTED;
    }
    public ZombifiableBlock(ZombificationLevel zombificationLevel, Settings settings) {
        super(settings);
        this.zombificationLevel = zombificationLevel;
    }

    @Override
    public MapColor getDefaultMapColor() {
        return getZombificationLevel().getMapColor();
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
        return getZombificationLevel() != ZombificationLevel.ZOMBIFIED;
    }

    @Override
    public ZombificationLevel getZombificationLevel() {
        return zombificationLevel;
    }
}
