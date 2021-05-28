package net.digitalpear.pigsteel.specialslabs;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.sound.BlockSoundGroup;


public class ZombifiedCutPigsteelSlab extends SlabBlock{

    public ZombifiedCutPigsteelSlab(Settings settings) {
        super(FabricBlockSettings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.NETHERITE).ticksRandomly());
    }
}
