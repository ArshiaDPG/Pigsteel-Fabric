package net.digitalpear.pigsteel.specialblocks.waxed;

import net.digitalpear.pigsteel.PigsteelMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WaxedZombifiedCutPigsteel extends Block {
    public WaxedZombifiedCutPigsteel(Settings settings) {
        super(settings);
    }
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit){
        if (player.getMainHandStack().getItem() instanceof AxeItem) {
            player.swingHand(hand);
            world.setBlockState(pos, PigsteelMod.ZOMBIFIED_CUT_PIGSTEEL.getDefaultState());
            world.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_AXE_WAX_OFF, SoundCategory.BLOCKS, 1.0F, 1.0F + world.random.nextFloat() * 0.4F);
            if (player != null && !player.isCreative()) {
                player.getMainHandStack().damage(1, world.random, (ServerPlayerEntity) player);
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
}