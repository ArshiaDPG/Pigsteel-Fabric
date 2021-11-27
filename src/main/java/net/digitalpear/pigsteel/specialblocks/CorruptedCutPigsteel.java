package net.digitalpear.pigsteel.specialblocks;

import net.digitalpear.pigsteel.PigsteelMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class CorruptedCutPigsteel extends Block{
    public CorruptedCutPigsteel(Settings settings) {
        super(settings.ticksRandomly());
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isClient) {
            if (world.getDimension().isBedWorking()) {
                world.setBlockState(pos, PigsteelMod.ZOMBIFIED_CUT_PIGSTEEL.getDefaultState());
            }
        }
    }
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit){
        if (player.getStackInHand(hand).getItem() == Items.HONEYCOMB) {
            player.swingHand(hand);
            world.setBlockState(pos, PigsteelMod.WAXED_CORRUPTED_CUT_PIGSTEEL.getDefaultState());
            world.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_HONEYCOMB_WAX_ON, SoundCategory.BLOCKS, 1.0F, 1.0F + world.random.nextFloat() * 0.4F);
        }
        else if (player.getMainHandStack().getItem() instanceof AxeItem) {
            player.swingHand(hand);
            world.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0F, 1.0F + world.random.nextFloat() * 0.4F);
            if (player != null && !player.isCreative()) {
                player.getMainHandStack().damage(1, world.random, (ServerPlayerEntity) player);
            }
            world.setBlockState(pos, PigsteelMod.INFECTED_CUT_PIGSTEEL.getDefaultState());
        }
        return ActionResult.PASS;
    }
}
