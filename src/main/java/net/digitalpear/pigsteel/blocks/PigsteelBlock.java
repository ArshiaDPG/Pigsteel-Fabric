package net.digitalpear.pigsteel.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PigsteelBlock extends Block{

    public PigsteelBlock(Settings settings) {
        super(FabricBlockSettings.copy(Blocks.IRON_BLOCK));
    }


    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (world.getDimension().isBedWorking()) {
           world.setBlockState(pos, Blocks.IRON_BLOCK.getDefaultState(), 3);
           world.syncWorldEvent(2009, pos, 0);
           world.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, (1.0F + world.getRandom().nextFloat() * 0.2F) * 0.7F);
        }
    }
    
}
