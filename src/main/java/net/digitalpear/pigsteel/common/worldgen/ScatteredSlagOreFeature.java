package net.digitalpear.pigsteel.common.worldgen;

import com.mojang.serialization.Codec;
import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Objects;

public class ScatteredSlagOreFeature extends Feature<OreSlagFeatureConfig> {

    public ScatteredSlagOreFeature(Codec<OreSlagFeatureConfig> configCodec) {
        super(configCodec);
    }
    public boolean generate(FeatureContext<OreSlagFeatureConfig> context) {
        StructureWorldAccess structureWorldAccess = context.getWorld();
        Random random = context.getRandom();
        OreSlagFeatureConfig oreFeatureConfig = context.getConfig();
        BlockPos blockPos = context.getOrigin();
        int i = random.nextInt(oreFeatureConfig.size + 1);
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for (int j = 0; j < i; ++j) {
            this.setPos(mutable, random, blockPos, Math.min(j, 7));
            BlockState blockState = structureWorldAccess.getBlockState(mutable);

            for (OreFeatureConfig.Target target : oreFeatureConfig.targets) {
                Objects.requireNonNull(structureWorldAccess);
                if (OreFeature.shouldPlace(blockState, structureWorldAccess::getBlockState, random, oreFeatureConfig, target, mutable) || structureWorldAccess.getBlockState(mutable).isOf(PigsteelBlocks.PORKSLAG)) {
                    if (random.nextFloat() < oreFeatureConfig.slagChance){
                        generateSlag(context, mutable);
                    }

                    structureWorldAccess.setBlockState(mutable, target.state, 2);
                    break;
                }
            }
        }

        return true;
    }

    private void generateSlag(FeatureContext<OreSlagFeatureConfig> context, BlockPos initialPos){
        StructureWorldAccess world = context.getWorld();
        Random random = context.getRandom();
        OreSlagFeatureConfig config = context.getConfig();

        for(int i = 0; i < random.nextBetween(1, config.slagCountMax); ++i) {
            int x = random.nextBetween(1, config.slagWidthMax);
            int y = random.nextBetween(1, config.slagWidthMax);
            int z = random.nextBetween(1, config.slagWidthMax);

            float f = (float)(x + y + z) * 0.333F + 0.5F;
            for (OreSlagFeatureConfig.Target target : config.slagTargets) {
                Objects.requireNonNull(world);
                for (BlockPos currentPost : BlockPos.iterate(initialPos.add(-x, -y, -z), initialPos.add(x, y, z))) {
                    if (currentPost.getSquaredDistance(initialPos) <= (double) (f * f) && OreFeature.shouldPlace(world.getBlockState(currentPost), world::getBlockState, random, config, target, currentPost.mutableCopy())) {
                        world.setBlockState(currentPost, target.state, 3);
                    }
                }
                break;
            }

            initialPos = initialPos.add(random.nextBetween(-1, 1), random.nextBetween(-1, 1), random.nextBetween(-1, 1));
        }
    }

    private void setPos(BlockPos.Mutable mutable, Random random, BlockPos origin, int spread) {
        int i = this.getSpread(random, spread);
        int j = this.getSpread(random, spread);
        int k = this.getSpread(random, spread);
        mutable.set(origin, i, j, k);
    }

    private int getSpread(Random random, int spread) {
        return Math.round((random.nextFloat() - random.nextFloat()) * (float)spread);
    }
}
