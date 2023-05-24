package net.digitalpear.pigsteel.datagen;

import net.digitalpear.pigsteel.Pigsteel;
import net.digitalpear.pigsteel.register.PigsteelBlocks;
import net.digitalpear.pigsteel.register.PigsteelItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;
import net.minecraft.data.client.*;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class PigsteelModelGen extends FabricModelProvider {
    public PigsteelModelGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(PigsteelBlocks.BRIMSTONE_PIGSTEEL_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(PigsteelBlocks.BLUE_PIGSTEEL_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(PigsteelBlocks.PIGSTEEL_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(PigsteelBlocks.STONE_PIGSTEEL_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(PigsteelBlocks.DEEPSLATE_PIGSTEEL_ORE);


        blockStateModelGenerator.registerSimpleCubeAll(PigsteelBlocks.PORKSLAG);

        blockStateModelGenerator.registerSimpleCubeAll(PigsteelBlocks.RAW_PIGSTEEL_BLOCK);

        createWaxable(blockStateModelGenerator, PigsteelBlocks.PIGSTEEL_BLOCK, PigsteelBlocks.WAXED_PIGSTEEL_BLOCK);
        createWaxable(blockStateModelGenerator, PigsteelBlocks.INFECTED_PIGSTEEL, PigsteelBlocks.WAXED_INFECTED_PIGSTEEL);
        createWaxable(blockStateModelGenerator, PigsteelBlocks.CORRUPTED_PIGSTEEL, PigsteelBlocks.WAXED_CORRUPTED_PIGSTEEL);
        createWaxable(blockStateModelGenerator, PigsteelBlocks.ZOMBIFIED_PIGSTEEL, PigsteelBlocks.WAXED_ZOMBIFIED_PIGSTEEL);

        createWaxable(blockStateModelGenerator, PigsteelBlocks.CUT_PIGSTEEL, PigsteelBlocks.WAXED_CUT_PIGSTEEL);
        createWaxable(blockStateModelGenerator, PigsteelBlocks.INFECTED_CUT_PIGSTEEL, PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL);
        createWaxable(blockStateModelGenerator, PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL, PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL);
        createWaxable(blockStateModelGenerator, PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL, PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL);

        createWaxableStairs(blockStateModelGenerator, PigsteelBlocks.CUT_PIGSTEEL, PigsteelBlocks.CUT_PIGSTEEL_STAIRS, PigsteelBlocks.WAXED_CUT_PIGSTEEL_STAIRS);
        createWaxableStairs(blockStateModelGenerator, PigsteelBlocks.INFECTED_CUT_PIGSTEEL, PigsteelBlocks.INFECTED_CUT_PIGSTEEL_STAIRS, PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL_STAIRS);
        createWaxableStairs(blockStateModelGenerator, PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL, PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_STAIRS, PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS);
        createWaxableStairs(blockStateModelGenerator, PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL, PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_STAIRS, PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS);

        createWaxableSlab(blockStateModelGenerator, PigsteelBlocks.CUT_PIGSTEEL, PigsteelBlocks.CUT_PIGSTEEL_SLAB, PigsteelBlocks.WAXED_CUT_PIGSTEEL_SLAB);
        createWaxableSlab(blockStateModelGenerator, PigsteelBlocks.INFECTED_CUT_PIGSTEEL, PigsteelBlocks.INFECTED_CUT_PIGSTEEL_SLAB, PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL_SLAB);
        createWaxableSlab(blockStateModelGenerator, PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL, PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_SLAB, PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL_SLAB);
        createWaxableSlab(blockStateModelGenerator, PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL, PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_SLAB, PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL_SLAB);

        createLantern(blockStateModelGenerator, PigsteelBlocks.PIGSTEEL_LANTERN);
        createLantern(blockStateModelGenerator, PigsteelBlocks.PIGSTEEL_SOUL_LANTERN);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(PigsteelItems.RAW_PIGSTEEL, Models.GENERATED);
        itemModelGenerator.register(PigsteelItems.PIGSTEEL_INGOT, Models.GENERATED);
        itemModelGenerator.register(PigsteelItems.PIGSTEEL_NUGGET, Models.GENERATED);


    }




    private static Model block(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(new Identifier(Pigsteel.MOD_ID, "block/" + parent)), Optional.empty(), requiredTextureKeys);
    }
    public final void createLantern(BlockStateModelGenerator blockStateModelGenerator, Block lantern) {
        Identifier identifier = block("pigsteel_lantern_base", TextureKey.ALL).upload(lantern, TextureMap.all(lantern), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.registerItemModel(lantern.asItem());
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
