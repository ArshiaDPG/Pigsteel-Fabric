package net.digitalpear.pigsteel.common.blocks;

import net.digitalpear.pigsteel.init.data.ZombifiableBlockRegistry;
import net.minecraft.block.BarrelBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.Optional;

public class ZombifiableBarrelBlock extends BarrelBlock implements Zombifiable {
    private final ZombificationLevel zombificationLevel;
    public ZombifiableBarrelBlock(ZombificationLevel zombificationLevel, Settings settings) {
        super(settings);
        this.zombificationLevel = zombificationLevel;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (zombificationLevel == ZombificationLevel.ZOMBIFIED){
            return ActionResult.PASS_TO_DEFAULT_BLOCK_ACTION;
        }
        return super.onUse(state, world, pos, player, hit);
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
