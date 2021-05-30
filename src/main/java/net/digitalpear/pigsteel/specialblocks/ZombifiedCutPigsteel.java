package net.digitalpear.pigsteel.specialblocks;

import net.digitalpear.pigsteel.PigsteelMod;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class ZombifiedCutPigsteel extends Block{

    public ZombifiedCutPigsteel(Settings settings) {
        super(FabricBlockSettings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.NETHERITE).ticksRandomly());
    }
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit){
        if (player.getStackInHand(hand).getItem() == Items.HONEYCOMB) {
            world.setBlockState(pos, PigsteelMod.WAXED_ZOMBIFIED_CUT_PIGSTEEL.getDefaultState());
            world.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_HONEY_BLOCK_PLACE, SoundCategory.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
        }
        return ActionResult.PASS;
    }
}
