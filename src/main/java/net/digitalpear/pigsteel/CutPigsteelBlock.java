package net.digitalpear.pigsteel;

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

public class CutPigsteelBlock extends Block {

    private BlockState rustingResult;
    private BlockState axingResult;
    private BlockState waxingResult;


    public CutPigsteelBlock(Settings settings, BlockState rustingResult, BlockState axingResult, BlockState waxingResult) {
        super(settings);
        this.rustingResult = rustingResult;
        this.axingResult = axingResult;
        this.waxingResult = waxingResult;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isClient) {
            if (this.rustingResult != null) {
                if (world.getDimension().isBedWorking()) {
                    world.setBlockState(pos, this.rustingResult);
                }
            }
        }
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.getStackInHand(hand).getItem() == Items.HONEYCOMB && this.waxingResult != null) {
            player.swingHand(hand);
            world.setBlockState(pos, this.waxingResult);
            world.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_HONEYCOMB_WAX_ON, SoundCategory.BLOCKS, 1.0F, 1.0F + world.random.nextFloat() * 0.4F);
        }
        else if (player.getMainHandStack().getItem() instanceof AxeItem && this.axingResult != null) {
            player.swingHand(hand);
            world.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0F, 1.0F + world.random.nextFloat() * 0.4F);
            if (player != null && !player.isCreative()) {
                player.getMainHandStack().damage(1, world.random, (ServerPlayerEntity) player);
            }
            world.setBlockState(pos, this.axingResult);
        }
        return ActionResult.PASS;
    }
}