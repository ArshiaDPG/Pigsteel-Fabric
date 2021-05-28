package net.digitalpear.pigsteel.specialstairs;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.StairsBlock;
import net.minecraft.sound.BlockSoundGroup;


public class ZombifiedCutPigsteelStairs extends StairsBlock{

    public ZombifiedCutPigsteelStairs(BlockState baseBlockState, Settings settings) {
        super(baseBlockState, FabricBlockSettings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.NETHERITE).ticksRandomly());
    }
}
