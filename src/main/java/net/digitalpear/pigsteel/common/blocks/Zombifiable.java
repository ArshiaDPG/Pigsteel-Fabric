package net.digitalpear.pigsteel.common.blocks;

import net.digitalpear.pigsteel.init.data.ZombifiableBlockRegistry;
import net.digitalpear.pigsteel.init.tags.PigsteelBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Degradable;
import net.minecraft.block.MapColor;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.Optional;

public interface Zombifiable extends Degradable<Zombifiable.ZombificationLevel> {

    //Base amount that a block can influence the speed of zombification.
    float blockInfluence = 0.1F;

    //Chance for a block to zombify, the smaller this number is the less the chance of zombification.
    float baseChance = 0.9F;


    default boolean canZombify(World world, BlockState state){
        return !world.getDimension().ultrawarm() && getDegradationResult(state).isPresent();
    }

    @Override
    default Optional<BlockState> tryDegrade(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!(canZombify(world, state) && random.nextInt(9) < 2)) {
            return tryZombify(state, world, pos);
        }
        else return Optional.empty();
    }

    default Optional<BlockState> tryZombify(BlockState state, World world, BlockPos pos){
        float chance = baseChance;
        if (getDegradationLevel() == ZombificationLevel.UNAFFECTED){
            chance -= 0.1F;
        }
        for (BlockPos blockPos : BlockPos.iterate(pos.add(-1, -1, -1), pos.add(1, 1, 1))) {
            /*
                Slow down or speed up zombification depending on adjacent blocks.
             */
            if (world.getBlockState(blockPos).isIn(PigsteelBlockTags.ZOMBIFICATION_DECELERATION)){
                chance -= blockInfluence;
            }
            else if (world.getBlockState(blockPos).isIn(PigsteelBlockTags.ZOMBIFICATION_ACCELERATION)){
                chance += blockInfluence;
            }
            else if (isZombifiablePigsteelBlock(world.getBlockState(blockPos))){
                chance -= blockInfluence / 2;
            }
        }
        if (world.getRandom().nextFloat() < chance){
            BlockState degradedState = ZombifiableBlockRegistry.getPigsteelZombifyingMap().get(state.getBlock()).getStateWithProperties(state);
            world.setBlockState(pos, degradedState, Block.NOTIFY_LISTENERS);
            return Optional.of(degradedState);
        }
        else {
            return Optional.empty();
        }
    }

    default boolean isZombifiablePigsteelBlock(BlockState state){
        return state.getBlock() instanceof Zombifiable;
    }

    enum ZombificationLevel implements StringIdentifiable {
        UNAFFECTED("", MapColor.PURPLE),
        INFECTED("infected", MapColor.PALE_GREEN),
        CORRUPTED("corrupted", MapColor.GREEN),
        ZOMBIFIED("zombified", MapColor.DARK_GREEN);

        ZombificationLevel(String name, MapColor mapColor){
            this.name = name;
            this.mapColor = mapColor;
        }

        private String name;
        private MapColor mapColor;

        public MapColor getMapColor() {
            return mapColor;
        }

        @Override
        public String asString() {
            return this.name;
        }
    }
}
