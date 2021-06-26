package net.digitalpear.pigsteel.specialslabs.waxed;

import net.digitalpear.pigsteel.PigsteelMod;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class WaxedCorruptedCutPigsteelSlab extends SlabBlock{

    public WaxedCorruptedCutPigsteelSlab(Settings settings) {
        super(FabricBlockSettings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.NETHERITE).ticksRandomly());
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit){
        if (player.getStackInHand(hand).getItem() == FabricToolTags.AXES) {
            SlabType half = state.get(Properties.SLAB_TYPE);
            Boolean watered = state.get(Properties.WATERLOGGED);

            world.setBlockState(pos, PigsteelMod.CORRUPTED_CUT_PIGSTEEL_SLAB.getDefaultState().with(Properties.WATERLOGGED, watered).with(Properties.SLAB_TYPE, half));
            world.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_AXE_WAX_OFF, SoundCategory.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
        }
        return ActionResult.PASS;
    }
}
