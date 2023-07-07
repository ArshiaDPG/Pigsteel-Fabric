package net.digitalpear.pigsteel.common.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.FeatureConfig;

public class MoltenRemainsFeatureConfig implements FeatureConfig {
    public static final Codec<MoltenRemainsFeatureConfig> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                    BlockState.CODEC.fieldOf("rock").forGetter(config -> config.rock),
                    BlockState.CODEC.fieldOf("ore").forGetter(config -> config.ore),
                    BlockState.CODEC.fieldOf("rawBlock").forGetter(config -> config.rawBlock)
            ).apply(instance, MoltenRemainsFeatureConfig::new)
    );

    private final BlockState rock;
    private final BlockState ore;
    private final BlockState rawBlock;

    public MoltenRemainsFeatureConfig(BlockState rock, BlockState ore, BlockState rawBlock) {
        this.rock = rock;
        this.ore = ore;
        this.rawBlock = rawBlock;
    }
    public MoltenRemainsFeatureConfig(Block rock, Block ore, Block rawBlock) {
        this.rock = rock.getDefaultState();
        this.ore = ore.getDefaultState();
        this.rawBlock = rawBlock.getDefaultState();
    }

    public BlockState getRock() {
        return rock;
    }

    public BlockState getOre() {
        return ore;
    }

    public BlockState getRawBlock() {
        return rawBlock;
    }
}