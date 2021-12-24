package net.digitalpear.pigsteel.blocks.cutslabs;

import net.digitalpear.pigsteel.PigsteelMod;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class CorruptedCutPigsteelSlab extends SlabBlock{
    public CorruptedCutPigsteelSlab(Settings settings) {
        super(FabricBlockSettings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.NETHERITE).ticksRandomly());
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isClient) {
            if (world.getDimension().isBedWorking()) {
                SlabType half = state.get(Properties.SLAB_TYPE);
                Boolean watered = state.get(Properties.WATERLOGGED);

                world.setBlockState(pos, PigsteelMod.ZOMBIFIED_CUT_PIGSTEEL_SLAB.getDefaultState().with(Properties.WATERLOGGED, watered).with(Properties.SLAB_TYPE, half));
            }
        }
    }
}
