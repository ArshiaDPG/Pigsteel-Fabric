package net.digitalpear.pigsteel.common.datagen;

import net.digitalpear.pigsteel.Pigsteel;
import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.digitalpear.pigsteel.init.PigsteelItems;
import net.digitalpear.pigsteel.init.data.PigsteelBlockFamilies;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.client.data.*;
import net.minecraft.client.render.model.json.ModelVariantOperator;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;

import java.util.Optional;

public class PigsteelModelProvider extends FabricModelProvider {
    private static final BlockStateVariantMap<ModelVariantOperator> NORTH_DEFAULT_HORIZONTAL_ROTATION_OPERATIONS = BlockStateVariantMap.operations(Properties.HORIZONTAL_FACING)
            .register(Direction.EAST, BlockStateModelGenerator.ROTATE_Y_90)
            .register(Direction.SOUTH, BlockStateModelGenerator.ROTATE_Y_180)
            .register(Direction.WEST, BlockStateModelGenerator.ROTATE_Y_270)
            .register(Direction.NORTH, BlockStateModelGenerator.NO_OP);

    public PigsteelModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSingleton(PigsteelBlocks.PORKSLAG, TexturedModel.CUBE_COLUMN);

        blockStateModelGenerator.registerSimpleCubeAll(PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK);

        PigsteelBlocks.REFINED_PIGSTEEL.getBlockToWaxedMap().forEach((block, waxed) -> {
            blockStateModelGenerator.registerSimpleCubeAll(block);
            blockStateModelGenerator.registerParented(block, waxed);
        });
        PigsteelBlocks.PIGSTEEL_LANTERNS.getBlockToWaxedMap().forEach((block, waxed) -> {
            registerLantern(blockStateModelGenerator, block);
            registerParentedLantern(blockStateModelGenerator, waxed, block);
        });
        PigsteelBlocks.PIGSTEEL_SOUL_LANTERNS.getBlockToWaxedMap().forEach((block, waxed) -> {
            registerLantern(blockStateModelGenerator, block);
            registerParentedLantern(blockStateModelGenerator, waxed, block);
        });

        blockStateModelGenerator.registerCubeAllModelTexturePool(PigsteelBlocks.CUT_PIGSTEEL.getUnaffectedBlock())
                .family(PigsteelBlockFamilies.CUT_PIGSTEEL)
                .family(PigsteelBlockFamilies.WAXED_CUT_PIGSTEEL)
                .parented(PigsteelBlocks.CUT_PIGSTEEL.getUnaffectedBlock(), PigsteelBlocks.CUT_PIGSTEEL.getWaxedUnaffectedBlock());

        blockStateModelGenerator.registerCubeAllModelTexturePool(PigsteelBlocks.CUT_PIGSTEEL.getInfectedBlock())
                .family(PigsteelBlockFamilies.INFECTED_CUT_PIGSTEEL)
                .family(PigsteelBlockFamilies.WAXED_INFECTED_CUT_PIGSTEEL)
                .parented(PigsteelBlocks.CUT_PIGSTEEL.getInfectedBlock(), PigsteelBlocks.CUT_PIGSTEEL.getWaxedInfectedBlock());

        blockStateModelGenerator.registerCubeAllModelTexturePool(PigsteelBlocks.CUT_PIGSTEEL.getCorruptedBlock())
                .family(PigsteelBlockFamilies.CORRUPTED_CUT_PIGSTEEL)
                .family(PigsteelBlockFamilies.WAXED_CORRUPTED_CUT_PIGSTEEL)
                .parented(PigsteelBlocks.CUT_PIGSTEEL.getCorruptedBlock(), PigsteelBlocks.CUT_PIGSTEEL.getWaxedCorruptedBlock());

        blockStateModelGenerator.registerCubeAllModelTexturePool(PigsteelBlocks.CUT_PIGSTEEL.getZombifiedBlock())
                .family(PigsteelBlockFamilies.ZOMBIFIED_CUT_PIGSTEEL)
                .family(PigsteelBlockFamilies.WAXED_ZOMBIFIED_CUT_PIGSTEEL)
                .parented(PigsteelBlocks.CUT_PIGSTEEL.getZombifiedBlock(), PigsteelBlocks.CUT_PIGSTEEL.getWaxedZombifiedBlock());
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(PigsteelItems.PIGSTEEL_CHUNK, Models.GENERATED);
    }


    private static Model block(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(Pigsteel.id("block/" + parent)), Optional.empty(), requiredTextureKeys);
    }
    public static void registerLantern(BlockStateModelGenerator blockStateModelGenerator, Block lantern) {
        WeightedVariant weightedVariant = BlockStateModelGenerator.createWeightedVariant(block("template_pigsteel_lantern", TextureKey.ALL).upload(lantern, TextureMap.all(lantern), blockStateModelGenerator.modelCollector));
        blockStateModelGenerator.registerItemModel(lantern.asItem());
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockModelDefinitionCreator.of(lantern, weightedVariant).coordinate(NORTH_DEFAULT_HORIZONTAL_ROTATION_OPERATIONS));
    }
    public static void registerParentedLantern(BlockStateModelGenerator blockStateModelGenerator, Block lantern, Block baseModel) {
        blockStateModelGenerator.registerParentedItemModel(lantern, TextureMap.getId(baseModel.asItem()));
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockModelDefinitionCreator.of(lantern, BlockStateModelGenerator.createWeightedVariant(TextureMap.getId(baseModel))).coordinate(NORTH_DEFAULT_HORIZONTAL_ROTATION_OPERATIONS));
    }
}
