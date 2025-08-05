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
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.Optional;

public class PigsteelModelProvider extends FabricModelProvider {
    private static final BlockStateVariantMap<ModelVariantOperator> NORTH_DEFAULT_HORIZONTAL_ROTATION_OPERATIONS = BlockStateVariantMap.operations(Properties.HORIZONTAL_FACING)
            .register(Direction.EAST, BlockStateModelGenerator.ROTATE_Y_90)
            .register(Direction.SOUTH, BlockStateModelGenerator.ROTATE_Y_180)
            .register(Direction.WEST, BlockStateModelGenerator.ROTATE_Y_270)
            .register(Direction.NORTH, BlockStateModelGenerator.NO_OP);
    private static final BlockStateVariantMap<ModelVariantOperator> UP_DEFAULT_ROTATION_OPERATIONS = BlockStateVariantMap.operations(Properties.FACING)
            .register(Direction.DOWN, BlockStateModelGenerator.ROTATE_X_180)
            .register(Direction.UP, BlockStateModelGenerator.NO_OP)
            .register(Direction.NORTH, BlockStateModelGenerator.ROTATE_X_90)
            .register(Direction.SOUTH, BlockStateModelGenerator.ROTATE_X_90.then(BlockStateModelGenerator.ROTATE_Y_180))
            .register(Direction.WEST, BlockStateModelGenerator.ROTATE_X_90.then(BlockStateModelGenerator.ROTATE_Y_270))
            .register(Direction.EAST, BlockStateModelGenerator.ROTATE_X_90.then(BlockStateModelGenerator.ROTATE_Y_90));


    public PigsteelModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

        PigsteelBlocks.PIGSTEEL_BARRELS.getBlockToWaxedMap().forEach((block, block2) -> {
            registerBarrel(blockStateModelGenerator, block, block2);
        });

        blockStateModelGenerator.registerSingleton(PigsteelBlocks.PORKSLAG, TexturedModel.CUBE_COLUMN);

        blockStateModelGenerator.registerSimpleCubeAll(PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK);

        PigsteelBlocks.REFINED_PIGSTEEL.getBlockToWaxedMap().forEach((block, waxed) -> {
            blockStateModelGenerator.registerSimpleCubeAll(block);
            blockStateModelGenerator.registerParented(block, waxed);
        });
        PigsteelBlocks.PIGSTEEL_GRATE.getBlockToWaxedMap().forEach((block, waxed) -> {
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

        PigsteelBlocks.PIGSTEEL_BARS.getBlockToWaxedMap().forEach((block, block2) -> {
            registerBars(blockStateModelGenerator, block, block2);
        });

        blockStateModelGenerator.registerCubeAllModelTexturePool(PigsteelBlocks.CUT_PIGSTEEL.getUnaffectedBlock())
                .family(PigsteelBlockFamilies.PIGSTEEL)
                .family(PigsteelBlockFamilies.WAXED_PIGSTEEL)
                .parented(PigsteelBlocks.CUT_PIGSTEEL.getUnaffectedBlock(), PigsteelBlocks.CUT_PIGSTEEL.getWaxedUnaffectedBlock());

        blockStateModelGenerator.registerCubeAllModelTexturePool(PigsteelBlocks.CUT_PIGSTEEL.getInfectedBlock())
                .family(PigsteelBlockFamilies.INFECTED_PIGSTEEL)
                .family(PigsteelBlockFamilies.WAXED_INFECTED_PIGSTEEL)
                .parented(PigsteelBlocks.CUT_PIGSTEEL.getInfectedBlock(), PigsteelBlocks.CUT_PIGSTEEL.getWaxedInfectedBlock());

        blockStateModelGenerator.registerCubeAllModelTexturePool(PigsteelBlocks.CUT_PIGSTEEL.getCorruptedBlock())
                .family(PigsteelBlockFamilies.CORRUPTED_PIGSTEEL)
                .family(PigsteelBlockFamilies.WAXED_CORRUPTED_PIGSTEEL)
                .parented(PigsteelBlocks.CUT_PIGSTEEL.getCorruptedBlock(), PigsteelBlocks.CUT_PIGSTEEL.getWaxedCorruptedBlock());

        blockStateModelGenerator.registerCubeAllModelTexturePool(PigsteelBlocks.CUT_PIGSTEEL.getZombifiedBlock())
                .family(PigsteelBlockFamilies.ZOMBIFIED_PIGSTEEL)
                .family(PigsteelBlockFamilies.WAXED_ZOMBIFIED_PIGSTEEL)
                .parented(PigsteelBlocks.CUT_PIGSTEEL.getZombifiedBlock(), PigsteelBlocks.CUT_PIGSTEEL.getWaxedZombifiedBlock());
    }
    private void registerBars(BlockStateModelGenerator blockStateModelGenerator, Block block, Block block2) {
        TextureMap textureMap = TextureMap.method_73141(block);
        Identifier identifier = Models.TEMPLATE_BARS_POST_ENDS.upload(block, textureMap, blockStateModelGenerator.modelCollector);
        Identifier identifier2 = Models.TEMPLATE_BARS_POST.upload(block, textureMap, blockStateModelGenerator.modelCollector);
        Identifier identifier3 = Models.TEMPLATE_BARS_CAP.upload(block, textureMap, blockStateModelGenerator.modelCollector);
        Identifier identifier4 = Models.TEMPLATE_BARS_CAP_ALT.upload(block, textureMap, blockStateModelGenerator.modelCollector);
        Identifier identifier5 = Models.TEMPLATE_BARS_SIDE.upload(block, textureMap, blockStateModelGenerator.modelCollector);
        Identifier identifier6 = Models.TEMPLATE_BARS_SIDE_ALT.upload(block, textureMap, blockStateModelGenerator.modelCollector);
        method_73137(blockStateModelGenerator, block, identifier, identifier2, identifier3, identifier4, identifier5, identifier6);
        method_73137(blockStateModelGenerator, block2, identifier, identifier2, identifier3, identifier4, identifier5, identifier6);
        blockStateModelGenerator.registerItemModel(block);
        blockStateModelGenerator.itemModelOutput.acceptAlias(block.asItem(), block2.asItem());
    }
    private void method_73137(BlockStateModelGenerator blockStateModelGenerator, Block block, Identifier identifier, Identifier identifier2, Identifier identifier3, Identifier identifier4, Identifier identifier5, Identifier identifier6) {
        WeightedVariant weightedVariant = BlockStateModelGenerator.createWeightedVariant(identifier);
        WeightedVariant weightedVariant2 = BlockStateModelGenerator.createWeightedVariant(identifier2);
        WeightedVariant weightedVariant3 = BlockStateModelGenerator.createWeightedVariant(identifier3);
        WeightedVariant weightedVariant4 = BlockStateModelGenerator.createWeightedVariant(identifier4);
        WeightedVariant weightedVariant5 = BlockStateModelGenerator.createWeightedVariant(identifier5);
        WeightedVariant weightedVariant6 = BlockStateModelGenerator.createWeightedVariant(identifier6);
        blockStateModelGenerator.blockStateCollector.accept(MultipartBlockModelDefinitionCreator.create(block).with(weightedVariant).with(BlockStateModelGenerator.createMultipartConditionBuilder().put(Properties.NORTH, false).put(Properties.EAST, false).put(Properties.SOUTH, false).put(Properties.WEST, false), weightedVariant2).with(BlockStateModelGenerator.createMultipartConditionBuilder().put(Properties.NORTH, true).put(Properties.EAST, false).put(Properties.SOUTH, false).put(Properties.WEST, false), weightedVariant3).with(BlockStateModelGenerator.createMultipartConditionBuilder().put(Properties.NORTH, false).put(Properties.EAST, true).put(Properties.SOUTH, false).put(Properties.WEST, false), weightedVariant3.apply(BlockStateModelGenerator.ROTATE_Y_90)).with(BlockStateModelGenerator.createMultipartConditionBuilder().put(Properties.NORTH, false).put(Properties.EAST, false).put(Properties.SOUTH, true).put(Properties.WEST, false), weightedVariant4).with(BlockStateModelGenerator.createMultipartConditionBuilder().put(Properties.NORTH, false).put(Properties.EAST, false).put(Properties.SOUTH, false).put(Properties.WEST, true), weightedVariant4.apply(BlockStateModelGenerator.ROTATE_Y_90)).with(BlockStateModelGenerator.createMultipartConditionBuilder().put(Properties.NORTH, true), weightedVariant5).with(BlockStateModelGenerator.createMultipartConditionBuilder().put(Properties.EAST, true), weightedVariant5.apply(BlockStateModelGenerator.ROTATE_Y_90)).with(BlockStateModelGenerator.createMultipartConditionBuilder().put(Properties.SOUTH, true), weightedVariant6).with(BlockStateModelGenerator.createMultipartConditionBuilder().put(Properties.WEST, true), weightedVariant6.apply(BlockStateModelGenerator.ROTATE_Y_90)));
    }
    private void registerBarrel(BlockStateModelGenerator blockStateModelGenerator, Block textureBase, Block waxed) {
        Identifier identifier = TextureMap.getSubId(textureBase, "_top_open");
        WeightedVariant weightedVariant = BlockStateModelGenerator.createWeightedVariant(TexturedModel.CUBE_BOTTOM_TOP.upload(textureBase, blockStateModelGenerator.modelCollector));
        WeightedVariant weightedVariant2 = BlockStateModelGenerator.createWeightedVariant(TexturedModel.CUBE_BOTTOM_TOP.get(textureBase).textures((textureMap) -> {
            textureMap.put(TextureKey.TOP, identifier);
        }).upload(textureBase, "_open", blockStateModelGenerator.modelCollector));
        blockStateModelGenerator.registerParentedItemModel(waxed, TextureMap.getId(textureBase.asItem()));
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockModelDefinitionCreator.of(textureBase).with(BlockStateVariantMap.models(Properties.OPEN).register(false, weightedVariant).register(true, weightedVariant2)).coordinate(UP_DEFAULT_ROTATION_OPERATIONS));
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockModelDefinitionCreator.of(waxed).with(BlockStateVariantMap.models(Properties.OPEN).register(false, weightedVariant).register(true, weightedVariant2)).coordinate(UP_DEFAULT_ROTATION_OPERATIONS));
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
