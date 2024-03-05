package net.digitalpear.pigsteel.common.blocks;

import net.digitalpear.pigsteel.Pigsteel;
import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.digitalpear.pigsteel.init.tags.PigsteelBlockTags;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Zombifiable {


    float blockInfluence = 0.1F;

    //Chance for a block to zombify, the smaller this number is the less the chance of zombification.
    float baseChance = 0.9F;


    default boolean canZombify(World world, BlockState state){
        return !world.getDimension().ultrawarm() && PigsteelBlocks.PIGSTEEL_ZOMBIFYING_MAP.containsKey(state.getBlock());
    }

    default void tryZombify(World world, BlockState state, BlockPos pos){
        if (!(canZombify(world, state) && world.getRandom().nextInt(9) < 2)) {
            return;
        }
        
        float chance = baseChance;
        if (getZombificationLevel() == ZombificationLevel.UNAFFECTED){
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
            world.setBlockState(pos, PigsteelBlocks.PIGSTEEL_ZOMBIFYING_MAP.get(state.getBlock()).getStateWithProperties(state), Block.NOTIFY_LISTENERS);
        }

    }

    default boolean isZombifiablePigsteelBlock(BlockState state){
        return state.getBlock() instanceof Zombifiable;
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
