package net.digitalpear.pigsteel.mixin;


import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.digitalpear.pigsteel.PigsteelMod;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoneycombItem;
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

@Mixin(HoneycombItem.class)
public class HoneycombItemMixin {
    private static final Supplier<BiMap<Block, Block>> UNWAX_TO_WAX = Suppliers.memoize(() -> ((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)((ImmutableBiMap.Builder)ImmutableBiMap.builder().put(PigsteelMod.CUT_PIGSTEEL, PigsteelMod.WAXED_CUT_PIGSTEEL)).put(PigsteelMod.INFECTED_CUT_PIGSTEEL, PigsteelMod.WAXED_INFECTED_CUT_PIGSTEEL)).put(PigsteelMod.CORRUPTED_CUT_PIGSTEEL, PigsteelMod.WAXED_CORRUPTED_CUT_PIGSTEEL)).put(PigsteelMod.ZOMBIFIED_CUT_PIGSTEEL, PigsteelMod.WAXED_ZOMBIFIED_CUT_PIGSTEEL)).put(PigsteelMod.CUT_PIGSTEEL_STAIRS, PigsteelMod.WAXED_CUT_PIGSTEEL_STAIRS)).put(PigsteelMod.INFECTED_CUT_PIGSTEEL_STAIRS, PigsteelMod.WAXED_INFECTED_CUT_PIGSTEEL_STAIRS).put(PigsteelMod.CORRUPTED_CUT_PIGSTEEL_STAIRS, PigsteelMod.WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS)).put(PigsteelMod.ZOMBIFIED_CUT_PIGSTEEL_STAIRS, PigsteelMod.WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS)).put(PigsteelMod.CUT_PIGSTEEL_SLAB, PigsteelMod.WAXED_CUT_PIGSTEEL_SLAB)).put(PigsteelMod.INFECTED_CUT_PIGSTEEL_SLAB, PigsteelMod.WAXED_INFECTED_CUT_PIGSTEEL_SLAB)).put(PigsteelMod.CORRUPTED_CUT_PIGSTEEL_SLAB, PigsteelMod.WAXED_CORRUPTED_CUT_PIGSTEEL_SLAB)).put(PigsteelMod.ZOMBIFIED_CUT_PIGSTEEL_SLAB, PigsteelMod.WAXED_ZOMBIFIED_CUT_PIGSTEEL_SLAB)))))))).build());

    @Inject(method = "useOnBlock",at = @At("HEAD"))
    public void useOnBlockInject(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        PlayerEntity playerEntity = context.getPlayer();
        BlockState blockState = world.getBlockState(blockPos);
        ItemStack itemStack = context.getStack();
        Optional<BlockState> optional4 = Optional.empty();
        Optional<BlockState> optional5 = Optional.ofNullable((Block)((BiMap) UNWAX_TO_WAX.get()).get(blockState.getBlock())).map((block) -> {
            return block.getStateWithProperties(blockState);
        });
        if (optional5.isPresent()) {
            world.playSound(playerEntity, blockPos, SoundEvents.ITEM_HONEYCOMB_WAX_ON, SoundCategory.BLOCKS, 1.0F, 1.0F);
            playerEntity.swingHand(Hand.MAIN_HAND);
            itemStack.decrement(1);
            world.syncWorldEvent(playerEntity, 3003, blockPos, 0);
            optional4 = optional5;
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
