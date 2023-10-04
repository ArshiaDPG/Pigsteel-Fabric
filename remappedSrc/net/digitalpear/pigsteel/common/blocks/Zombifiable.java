package net.digitalpear.pigsteel.common.blocks;

import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.digitalpear.pigsteel.init.tags.PigsteelBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface Zombifiable {

    float blockInfluence = 0.1F;
    float baseChance = 0.9F;


    default boolean canZombify(World world, BlockState state){
        return !world.getDimension().ultrawarm() && PigsteelBlocks.PIGSTEEL_ZOMBIFYING_MAP.containsKey(state.getBlock());
    }

    default void tryZombify(World world, BlockState state, BlockPos pos){
        float chance = baseChance;
        if (canZombify(world, state) && world.getRandom().nextInt(9) < 2){
            if (getZombificationLevel() == ZombificationLevel.UNAFFECTED){
                chance -= 0.1;
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
            chance -= (float) state.getLuminance() / 10;
            if (world.getRandom().nextFloat() < chance){
                world.setBlockState(pos, PigsteelBlocks.PIGSTEEL_ZOMBIFYING_MAP.get(state.getBlock()).getStateWithProperties(state), Block.NOTIFY_ALL);
            }
        }
    }

    default boolean isZombifiablePigsteelBlock(BlockState state){
        return PigsteelBlocks.PIGSTEEL_WAXING_MAP.containsKey(state.getBlock());
    }

    ZombificationLevel getZombificationLevel();

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
