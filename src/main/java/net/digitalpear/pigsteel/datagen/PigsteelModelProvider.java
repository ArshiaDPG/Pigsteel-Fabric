package net.digitalpear.pigsteel.datagen;

import net.digitalpear.pigsteel.Pigsteel;
import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.digitalpear.pigsteel.init.PigsteelItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class PigsteelModelProvider extends FabricModelProvider {
    public PigsteelModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

        blockStateModelGenerator.registerSingleton(PigsteelBlocks.PORKSLAG, TexturedModel.CUBE_COLUMN);

        blockStateModelGenerator.registerSimpleCubeAll(PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK);

        PigsteelBlocks.refinedPigsteel.getBlockToWaxedMap().forEach((block, waxed) -> {
            blockStateModelGenerator.registerSimpleCubeAll(block);
            blockStateModelGenerator.registerParented(block, waxed);
        });
        PigsteelBlocks.pigsteelLanterns.getBlockToWaxedMap().forEach((block, waxed) -> {
            createLantern(blockStateModelGenerator, block);
            createLantern(blockStateModelGenerator, waxed, block);
        });
        PigsteelBlocks.pigsteelSoulLanterns.getBlockToWaxedMap().forEach((block, waxed) -> {
            createLantern(blockStateModelGenerator, block);
            createLantern(blockStateModelGenerator, waxed, block);
        });

        for (int i = 0; i < 4; i++){
            createWaxable(blockStateModelGenerator, PigsteelBlocks.cutPigsteel.getZombifiables().get(i), PigsteelBlocks.cutPigsteel.getWaxed().get(i));
            createWaxableSlab(blockStateModelGenerator, PigsteelBlocks.cutPigsteel.getZombifiables().get(i), PigsteelBlocks.cutPigsteelSlabs.getZombifiables().get(i),PigsteelBlocks.cutPigsteelSlabs.getWaxed().get(i));
        }


        createWaxableStairs(blockStateModelGenerator, PigsteelBlocks.cutPigsteel.getUnaffectedBlock(), PigsteelBlocks.CUT_PIGSTEEL_STAIRS, PigsteelBlocks.WAXED_CUT_PIGSTEEL_STAIRS);
        createWaxableStairs(blockStateModelGenerator, PigsteelBlocks.cutPigsteel.getInfectedBlock(), PigsteelBlocks.INFECTED_CUT_PIGSTEEL_STAIRS, PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL_STAIRS);
        createWaxableStairs(blockStateModelGenerator, PigsteelBlocks.cutPigsteel.getCorruptedBlock(), PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_STAIRS, PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS);
        createWaxableStairs(blockStateModelGenerator, PigsteelBlocks.cutPigsteel.getZombifiedBlock(), PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_STAIRS, PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(PigsteelItems.PIGSTEEL_CHUNK, Models.GENERATED);
    }


    private static Model block(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(new Identifier(Pigsteel.MOD_ID, "block/" + parent)), Optional.empty(), requiredTextureKeys);
    }
    public final void createLantern(BlockStateModelGenerator blockStateModelGenerator, Block lantern) {
        Identifier identifier = block("template_pigsteel_lantern", TextureKey.ALL).upload(lantern, TextureMap.all(lantern), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.registerItemModel(lantern.asItem());
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(lantern, BlockStateVariant.create().put(VariantSettings.MODEL, identifier))
                .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates()));
    }
    public final void createLantern(BlockStateModelGenerator blockStateModelGenerator, Block lantern, Block baseModel) {
        Identifier identifier = getId(baseModel);
        blockStateModelGenerator.registerParentedItemModel(lantern.asItem(), getItemId(baseModel));
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(lantern, BlockStateVariant.create().put(VariantSettings.MODEL, identifier))
                .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates()));
    }


    public final void createWaxable(BlockStateModelGenerator blockStateModelGenerator, Block block, Block waxed) {
        Identifier model = TexturedModel.CUBE_ALL.upload(block, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, model));
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(waxed, model));
        blockStateModelGenerator.registerParentedItemModel(waxed, model);
    }
    public static void createWaxableStairs(BlockStateModelGenerator blockStateModelGenerator, Block textureBase, Block stairs, Block waxed) {
        createStairs(blockStateModelGenerator, textureBase, stairs);
        createWaxedStairs(blockStateModelGenerator, stairs, waxed);
    }
    public static void createWaxableSlab(BlockStateModelGenerator blockStateModelGenerator, Block textureBase, Block slab, Block waxed){
        createSlab(blockStateModelGenerator, textureBase, slab);
        createWaxedSlab(blockStateModelGenerator, textureBase, slab, waxed);
    }



    public static void createStairs(BlockStateModelGenerator blockStateModelGenerator, Block textureBase, Block stairs){
        Identifier STAIRS = Models.STAIRS.upload(stairs, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier INNER_STAIRS = Models.INNER_STAIRS.upload(stairs, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier OUTER_STAIRS = Models.OUTER_STAIRS.upload(stairs, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createStairsBlockState(stairs,
                INNER_STAIRS, STAIRS, OUTER_STAIRS));
    }
    public static void createWaxedStairs(BlockStateModelGenerator blockStateModelGenerator, Block stairs, Block waxed){
        Identifier STAIRS = getId(stairs);
        Identifier INNER_STAIRS = getId(stairs, "_inner");
        Identifier OUTER_STAIRS = getId(stairs, "_outer");
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createStairsBlockState(waxed,
                INNER_STAIRS, STAIRS, OUTER_STAIRS));
        blockStateModelGenerator.registerParentedItemModel(waxed, STAIRS);
    }




    public static void createSlab(BlockStateModelGenerator blockStateModelGenerator, Block textureBase, Block slab){
        Identifier SLAB = Models.SLAB.upload(slab, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier SLAB_TOP = Models.SLAB_TOP.upload(slab, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSlabBlockState(slab,
                SLAB, SLAB_TOP, getId(textureBase)));
    }
    public static void createWaxedSlab(BlockStateModelGenerator blockStateModelGenerator, Block textureBase, Block slab, Block waxed){
        Identifier SLAB = getId(slab);
        Identifier SLAB_TOP = getId(slab, "_top");
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSlabBlockState(waxed,
                SLAB, SLAB_TOP, getId(textureBase)));
        blockStateModelGenerator.registerParentedItemModel(waxed, SLAB);
    }

    public static Identifier getItemId(Block block) {
        Identifier identifier = Registries.BLOCK.getId(block);
        return identifier.withPrefixedPath("item/");
    }
    public static Identifier getId(Block block) {
        Identifier identifier = Registries.BLOCK.getId(block);
        return identifier.withPrefixedPath("block/");
    }
    public static Identifier getId(Block block, String suffix) {
        Identifier identifier = Registries.BLOCK.getId(block);
        identifier = identifier.withSuffixedPath(suffix);
        return identifier.withPrefixedPath("block/");
    }
    public static Identifier getId(String prefix, Block block) {
        Identifier identifier = Registries.BLOCK.getId(block);
        identifier = identifier.withPrefixedPath(prefix);
        return identifier.withPrefixedPath("block/");
    }
}
