package net.digitalpear.pigsteel.common.blocks;

import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.digitalpear.pigsteel.init.tags.PigsteelBlockTags;
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
        if (canZombify(world, state)){
            if (getZombificationLevel() == ZombificationLevel.UNAFFECTED){
                chance -= 0.1;
            }
            for (BlockPos blockPos : BlockPos.iterate(pos.add(-1, -1, -1), pos.add(1, 1, 1))) {
                if (world.getBlockState(blockPos).isIn(PigsteelBlockTags.WARM_BLOCKS)){
                    chance -= blockInfluence;
                }
                else if (world.getBlockState(blockPos).isIn(PigsteelBlockTags.COLD_BLOCKS)){
                    chance += blockInfluence;
                }
            }

            if (world.getRandom().nextFloat() < chance){
                world.setBlockState(pos, PigsteelBlocks.PIGSTEEL_ZOMBIFYING_MAP.get(state.getBlock()).getStateWithProperties(state));
            }
        }


    }

    default int zombificationChance(){
        return 5;
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
        String name;
        MapColor mapColor;

        public MapColor getMapColor() {
            return mapColor;
        }

        @Override
        public String asString() {
            return this.name;
        }
    }
}
