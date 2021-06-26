package net.digitalpear.pigsteel.specialblocks.waxed;

import net.digitalpear.pigsteel.PigsteelMod;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WaxedCutPigsteel extends Block {
    public WaxedCutPigsteel(Settings settings) {
        super(settings);
    }
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit){
        if (player.getStackInHand(hand).getItem() == FabricToolTags.AXES) {
            world.setBlockState(pos, PigsteelMod.CUT_PIGSTEEL.getDefaultState());
            world.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_AXE_WAX_OFF, SoundCategory.BLOCKS, 1.0F, 1.0F + world.random.nextFloat() * 0.4F);
        }
        return ActionResult.PASS;
    }
}