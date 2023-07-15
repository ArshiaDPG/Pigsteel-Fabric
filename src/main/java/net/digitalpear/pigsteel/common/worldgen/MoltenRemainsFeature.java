package net.digitalpear.pigsteel.common.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class MoltenRemainsFeature extends Feature<MoltenRemainsFeatureConfig> {


    public MoltenRemainsFeature(Codec<MoltenRemainsFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<MoltenRemainsFeatureConfig> context) {
        BlockPos origin = context.getOrigin();
        StructureWorldAccess world = context.getWorld();
        Random random = world.getRandom();

        int i = world.getRandom().nextBetween(6, 10);

        int depth = 0;
        for (int y = 0; y < 20; y++){
//            if (world.getBlockState(origin.down(depth)).isOf(Blocks.BEDROCK)){
//                return true;
//            }
            placeDisk(context, origin.down(y), i);
            placeDisk(context, origin.down(depth), i);
            if (i > 2){
                i = i - random.nextInt(2);
            }
            depth++;
        }

        while (origin.down(depth).getY() > world.getBottomY()){
            placeDisk(context, origin.down(depth), i);
            if (i > 2){
                i = i + random.nextBetween(-1, 1);
            }
            else{
                i++;
            }
            depth++;
        }
        return true;
    }

    public void placeDisk(FeatureContext<MoltenRemainsFeatureConfig> context, BlockPos blockPos, int radius){
        StructureWorldAccess world = context.getWorld();
        Random random = context.getRandom();
        for(int i = 0; i < 3; ++i) {
            int x = radius + random.nextBetween(-1, 1);
            int y = random.nextBetween(2, 3);
            int z = radius + random.nextBetween(-1, 1);
            float r = (float)(x + y + z) * 0.333F + 0.5F;

            for (BlockPos blockPos2 : BlockPos.iterate(blockPos.add(-x, -y, -z), blockPos.add(x, y, z))) {
                if (blockPos2.getSquaredDistance(blockPos) <= (double) (r * r) && !world.getBlockState(blockPos2).isIn(BlockTags.FEATURES_CANNOT_REPLACE)) {
                    world.setBlockState(blockPos2, makeBlock(context), 3);
                }
            }

            blockPos = blockPos.add(1 + random.nextBetween(-2, 2), -random.nextInt(2), 1 + random.nextBetween(-2, 2));
        }
    }

    public BlockState makeBlock(FeatureContext<MoltenRemainsFeatureConfig> context){
        MoltenRemainsFeatureConfig config = context.getConfig();
        int i = context.getRandom().nextInt(30);
        if (i < 3){
            return context.getRandom().nextInt(10) == 0 ? config.getRawBlock() : config.getOre();
        }
        else{
            return config.getRock();
        }
    }
}