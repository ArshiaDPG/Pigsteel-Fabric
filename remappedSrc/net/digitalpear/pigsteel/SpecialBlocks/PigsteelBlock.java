package net.digitalpear.pigsteel.SpecialBlocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class PigsteelBlock extends Block{

    public PigsteelBlock(Settings settings) {
        super(FabricBlockSettings.copy(Blocks.IRON_BLOCK));
    }
    
}
