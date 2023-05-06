package net.digitalpear.pigsteel.common.features;

import com.mojang.serialization.Codec;
import net.digitalpear.pigsteel.register.PigsteelBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class PigsteelSpireFeature extends Feature<DefaultFeatureConfig> {
    public PigsteelSpireFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    private final Block STONE = Blocks.BASALT;
    private final Block ORE = PigsteelBlocks.PORKSLAG;

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {

        BlockPos start = context.getOrigin();
        StructureWorldAccess world = context.getWorld();
        Random random = context.getRandom();
        Direction direction = Direction.DOWN;

        int width = random.nextBetween(6, 10);

        int obstructionCount = 0;
        Iterable<BlockPos> area = BlockPos.iterate(start.add(-width/2, 0, -width/2), start.add(width/2, 0, width/2));
        for (BlockPos pos : area) {
            if (world.getBlockState(pos).isSolidBlock(world, pos)) {
                obstructionCount++;
            }
        }

        if (obstructionCount > 50 || world.getBottomY() >= start.getY() - 10 || world.getTopY() < start.getY()){
            return false;
        }
        for (int y = 0; y < width; y++){
            if (!world.getBlockState(start.offset(direction.getOpposite(), y)).isSolidBlock(world, start.offset(direction.getOpposite(), y))){
                return false;
            }
        }

        BlockPos currentPos = start;
        makeLayer(world, currentPos, random, direction, width, width);
        currentPos = currentPos.offset(direction);
        while (width > 0) {
            if (currentPos.getY() <= world.getBottomY()){
                break;
            }
            for (int y = 0; y < random.nextBetween(2, 3); y++){
                makeLayer(world, currentPos, random, direction, width, 1);
                currentPos = currentPos.offset(direction);
            }

            width -= 1;
        }

        return true;
    }

    public void makeLayer(StructureWorldAccess world, BlockPos blockPos, Random random, Direction direction, int width, int height){
        int x = width + random.nextBetween(-1, 1);
        int y = height + random.nextInt(2);
        int z = width + random.nextBetween(-1, 1);
        float f = (float)(x + y + z) * 0.333F + 0.5F;

        for (BlockPos blockPos2 : BlockPos.iterate(blockPos.add(-x, -y, -z), blockPos.add(x, y, z))) {
            if (blockPos2.getSquaredDistance(blockPos) <= (double) (f * f) && !world.getBlockState(blockPos2).isIn(BlockTags.FEATURES_CANNOT_REPLACE)) {
                int blockNumber = random.nextBetween(1, 4);
                world.setBlockState(blockPos2, blockNumber < 2 ? ORE.getDefaultState() : STONE.getDefaultState(), 3);
                if (random.nextBoolean()){
                    world.setBlockState(blockPos2.offset(direction), blockNumber < 2 ? ORE.getDefaultState() : STONE.getDefaultState(), 3);

                }
            }
        }
    }
}
