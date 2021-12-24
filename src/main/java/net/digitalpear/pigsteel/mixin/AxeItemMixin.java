package net.digitalpear.pigsteel.mixin;


import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.digitalpear.pigsteel.PigsteelMod;
import net.digitalpear.pigsteel.blocks.CutPigsteelBlocks;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import java.util.function.Supplier;

@Mixin(AxeItem.class)
public class AxeItemMixin {
    private static final Supplier<BiMap<Block, Block>> ZOMBIFICATION_LEVEL_INCREASES = Suppliers.memoize(() -> ((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)ImmutableBiMap.builder().put(PigsteelMod.CUT_PIGSTEEL, PigsteelMod.INFECTED_CUT_PIGSTEEL)).put(PigsteelMod.INFECTED_CUT_PIGSTEEL, PigsteelMod.CORRUPTED_CUT_PIGSTEEL)).put(PigsteelMod.CORRUPTED_CUT_PIGSTEEL, PigsteelMod.ZOMBIFIED_CUT_PIGSTEEL)).put(PigsteelMod.CUT_PIGSTEEL_STAIRS, PigsteelMod.INFECTED_CUT_PIGSTEEL_STAIRS)).put(PigsteelMod.INFECTED_CUT_PIGSTEEL_STAIRS, PigsteelMod.CORRUPTED_CUT_PIGSTEEL_STAIRS)).put(PigsteelMod.CORRUPTED_CUT_PIGSTEEL_STAIRS, PigsteelMod.ZOMBIFIED_CUT_PIGSTEEL_STAIRS)).put(PigsteelMod.CUT_PIGSTEEL_SLAB, PigsteelMod.INFECTED_CUT_PIGSTEEL_SLAB)).put(PigsteelMod.INFECTED_CUT_PIGSTEEL_SLAB, PigsteelMod.CORRUPTED_CUT_PIGSTEEL_SLAB)).put(PigsteelMod.CORRUPTED_CUT_PIGSTEEL_SLAB, PigsteelMod.ZOMBIFIED_CUT_PIGSTEEL_SLAB))))))))).build());
    Supplier<BiMap<Block, Block>> ZOMBIFICATION_LEVEL_DECREASES = Suppliers.memoize(() -> {
        return ((BiMap) ZOMBIFICATION_LEVEL_INCREASES.get()).inverse();
    });
    private static final Supplier<BiMap<Block, Block>> UNWAX_TO_WAX = Suppliers.memoize(() -> ((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)ImmutableBiMap.builder().put(PigsteelMod.CUT_PIGSTEEL, PigsteelMod.WAXED_CUT_PIGSTEEL)).put(PigsteelMod.INFECTED_CUT_PIGSTEEL, PigsteelMod.WAXED_INFECTED_CUT_PIGSTEEL)).put(PigsteelMod.CORRUPTED_CUT_PIGSTEEL, PigsteelMod.WAXED_CORRUPTED_CUT_PIGSTEEL)).put(PigsteelMod.ZOMBIFIED_CUT_PIGSTEEL, PigsteelMod.WAXED_ZOMBIFIED_CUT_PIGSTEEL)).put(PigsteelMod.CUT_PIGSTEEL_STAIRS, PigsteelMod.WAXED_CUT_PIGSTEEL_STAIRS)).put(PigsteelMod.INFECTED_CUT_PIGSTEEL_STAIRS, PigsteelMod.WAXED_INFECTED_CUT_PIGSTEEL_STAIRS).put(PigsteelMod.CORRUPTED_CUT_PIGSTEEL_STAIRS, PigsteelMod.WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS)).put(PigsteelMod.ZOMBIFIED_CUT_PIGSTEEL_STAIRS, PigsteelMod.WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS)).put(PigsteelMod.CUT_PIGSTEEL_SLAB, PigsteelMod.WAXED_CUT_PIGSTEEL_SLAB)).put(PigsteelMod.INFECTED_CUT_PIGSTEEL_SLAB, PigsteelMod.WAXED_INFECTED_CUT_PIGSTEEL_SLAB)).put(PigsteelMod.CORRUPTED_CUT_PIGSTEEL_SLAB, PigsteelMod.WAXED_CORRUPTED_CUT_PIGSTEEL_SLAB)).put(PigsteelMod.ZOMBIFIED_CUT_PIGSTEEL_SLAB, PigsteelMod.WAXED_ZOMBIFIED_CUT_PIGSTEEL_SLAB)).put(PigsteelMod.PIGSTEEL_BLOCK, PigsteelMod.WAXED_PIGSTEEL_BLOCK))))))).build());
    Supplier<BiMap<Block, Block>> WAX_TO_UNWAX = Suppliers.memoize(() -> {
        return ((BiMap) UNWAX_TO_WAX.get()).inverse();
    });






    @Inject(method = "useOnBlock",at = @At("HEAD"))
    public void useOnBlockInject(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        PlayerEntity playerEntity = context.getPlayer();
        BlockState blockState = world.getBlockState(blockPos);
        ItemStack itemStack = context.getStack();
        Optional<BlockState> optional4 = Optional.empty();
        Optional<BlockState> optional5 = Optional.ofNullable((Block)((BiMap) ZOMBIFICATION_LEVEL_DECREASES.get()).get(blockState.getBlock())).map((block) -> {
            return block.getStateWithProperties(blockState);
        });
        Optional<BlockState> optional6 = Optional.ofNullable((Block)((BiMap) WAX_TO_UNWAX.get()).get(blockState.getBlock())).map((block) -> {
            return block.getStateWithProperties(blockState);
        });
        if (optional5.isPresent()) {
            world.playSound(playerEntity, blockPos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            playerEntity.swingHand(Hand.MAIN_HAND);
            world.syncWorldEvent(playerEntity, 3005, blockPos, 0);
            optional4 = optional5;
        } else if (optional6.isPresent()) {
            world.playSound(playerEntity, blockPos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            playerEntity.swingHand(Hand.MAIN_HAND);
            world.syncWorldEvent(playerEntity, 3004, blockPos, 0);
            optional4 = optional6;
        }

        if (optional4.isPresent()) {
            if (playerEntity instanceof ServerPlayerEntity) {
                Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)playerEntity, blockPos, itemStack);
            }

            world.setBlockState(blockPos, (BlockState)optional4.get(), 11);
            if (playerEntity != null) {
                itemStack.damage(1, playerEntity, (p) -> {
                    p.sendToolBreakStatus(context.getHand());
                });
            }
        }
    }
}

