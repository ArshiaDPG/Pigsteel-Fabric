package net.digitalpear.pigsteel.specialblocks;

import net.digitalpear.pigsteel.PigsteelMod;
import net.digitalpear.pigsteel.registering.PigsteelBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
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
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit){
        if (player.getStackInHand(hand).getItem() == Items.HONEYCOMB) {
            player.swingHand(hand);
            world.setBlockState(pos, PigsteelBlocks.WAXED_PIGSTEEL_BLOCK.getDefaultState());
            world.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_HONEYCOMB_WAX_ON, SoundCategory.BLOCKS, 1.0F, 1.0F + world.random.nextFloat() * 0.4F);
        }
        return ActionResult.PASS;
    }
    
}
