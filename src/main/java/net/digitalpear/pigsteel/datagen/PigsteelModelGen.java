package net.digitalpear.pigsteel.datagen;

import net.digitalpear.pigsteel.PigsteelMod;
import net.digitalpear.pigsteel.common.blocks.PigsteelLanternBlock;
import net.digitalpear.pigsteel.register.PigsteelBlocks;
import net.digitalpear.pigsteel.register.PigsteelItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.Properties;
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


        blockStateModelGenerator.registerSimpleCubeAll(PigsteelBlocks.PIGSTEEL_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(PigsteelBlocks.WAXED_PIGSTEEL_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(PigsteelBlocks.CUT_PIGSTEEL);
        blockStateModelGenerator.registerSimpleCubeAll(PigsteelBlocks.INFECTED_CUT_PIGSTEEL);
        blockStateModelGenerator.registerSimpleCubeAll(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL);
        blockStateModelGenerator.registerSimpleCubeAll(PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL);

        blockStateModelGenerator.registerSimpleCubeAll(PigsteelBlocks.WAXED_CUT_PIGSTEEL);
        blockStateModelGenerator.registerSimpleCubeAll(PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL);
        blockStateModelGenerator.registerSimpleCubeAll(PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL);
        blockStateModelGenerator.registerSimpleCubeAll(PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL);

        createStairs(blockStateModelGenerator, PigsteelBlocks.CUT_PIGSTEEL, PigsteelBlocks.WAXED_CUT_PIGSTEEL_STAIRS);
        createStairs(blockStateModelGenerator, PigsteelBlocks.INFECTED_CUT_PIGSTEEL, PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL_STAIRS);
        createStairs(blockStateModelGenerator, PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL, PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS);
        createStairs(blockStateModelGenerator, PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL, PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS);

        createSlab(blockStateModelGenerator, PigsteelBlocks.CUT_PIGSTEEL, PigsteelBlocks.CUT_PIGSTEEL_SLAB);
        createSlab(blockStateModelGenerator, PigsteelBlocks.INFECTED_CUT_PIGSTEEL, PigsteelBlocks.INFECTED_CUT_PIGSTEEL_SLAB);
        createSlab(blockStateModelGenerator, PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL, PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_SLAB);
        createSlab(blockStateModelGenerator, PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL, PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_SLAB);

        createSlab(blockStateModelGenerator, PigsteelBlocks.CUT_PIGSTEEL, PigsteelBlocks.WAXED_CUT_PIGSTEEL_SLAB);
        createSlab(blockStateModelGenerator, PigsteelBlocks.INFECTED_CUT_PIGSTEEL, PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL_SLAB);
        createSlab(blockStateModelGenerator, PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL, PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL_SLAB);
        createSlab(blockStateModelGenerator, PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL, PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL_SLAB);

        registerLantern(blockStateModelGenerator, PigsteelBlocks.PIGSTEEL_LANTERN);
        registerLantern(blockStateModelGenerator, PigsteelBlocks.PIGSTEEL_SOUL_LANTERN);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(PigsteelItems.PIGSTEEL_INGOT, Models.GENERATED);
        itemModelGenerator.register(PigsteelItems.PIGSTEEL_NUGGET, Models.GENERATED);
    }

    public static final Model PIGSTEEL_LANTERN_BASE = block("pigsteel_lantern_base", TextureKey.ALL);
    private static Model block(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(new Identifier(PigsteelMod.MOD_ID, "block/" + parent)), Optional.empty(), requiredTextureKeys);
    }
    public final void registerLantern(BlockStateModelGenerator blockStateModelGenerator, Block lantern) {
        Identifier identifier = PIGSTEEL_LANTERN_BASE.upload(lantern, TextureMap.all(lantern), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.registerItemModel(lantern.asItem());
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(lantern, BlockStateVariant.create().put(VariantSettings.MODEL, identifier))
                .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates()));
    }



    public static void createStairs(BlockStateModelGenerator blockStateModelGenerator, Block textureBase, Block stairs){
        Identifier STAIRS = Models.STAIRS.upload(stairs, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier INNER_STAIRS = Models.INNER_STAIRS.upload(stairs, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier OUTER_STAIRS = Models.OUTER_STAIRS.upload(stairs, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createStairsBlockState(stairs,
                INNER_STAIRS, STAIRS, OUTER_STAIRS));
    }
    public static void createSlab(BlockStateModelGenerator blockStateModelGenerator, Block textureBase, Block slab){
        Identifier SLAB = Models.SLAB.upload(slab, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier SLAB_TOP = Models.SLAB_TOP.upload(slab, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSlabBlockState(slab,
                SLAB, SLAB_TOP, getId(textureBase)));
    }
    public static void makeButton(BlockStateModelGenerator blockStateModelGenerator, Block textureBase, Block button){
        Identifier BUTTON = Models.BUTTON.upload(button, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier BUTTON_PRESSED = Models.BUTTON_PRESSED.upload(button, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier BUTTON_INVENTORY = Models.BUTTON_INVENTORY.upload(button, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createButtonBlockState(button,
                BUTTON, BUTTON_PRESSED));
        blockStateModelGenerator.registerParentedItemModel(button.asItem(), BUTTON_INVENTORY);
    }

    public static void makePressurePlate(BlockStateModelGenerator blockStateModelGenerator, Block textureBase, Block plate){
        Identifier PRESSURE_PLATE_DOWN = Models.PRESSURE_PLATE_DOWN.upload(plate, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier PRESSURE_PLATE_UP = Models.PRESSURE_PLATE_UP.upload(plate, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createPressurePlateBlockState(plate,
                PRESSURE_PLATE_UP, PRESSURE_PLATE_DOWN));
    }
    public static void fence(BlockStateModelGenerator blockStateModelGenerator, Block textureBase, Block fenceBlock) {
        Identifier identifier = Models.FENCE_POST.upload(fenceBlock, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier identifier2 = Models.FENCE_SIDE.upload(fenceBlock, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createFenceBlockState(fenceBlock, identifier, identifier2));
        Identifier identifier3 = Models.FENCE_INVENTORY.upload(fenceBlock, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.registerParentedItemModel(fenceBlock, identifier3);
    }
    public static void fenceGate(BlockStateModelGenerator blockStateModelGenerator, Block textureBase, Block fenceGateBlock) {
        Identifier identifier = Models.TEMPLATE_FENCE_GATE_OPEN.upload(fenceGateBlock, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier identifier2 = Models.TEMPLATE_FENCE_GATE.upload(fenceGateBlock, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier identifier3 = Models.TEMPLATE_FENCE_GATE_WALL_OPEN.upload(fenceGateBlock, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier identifier4 = Models.TEMPLATE_FENCE_GATE_WALL.upload(fenceGateBlock, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createFenceGateBlockState(fenceGateBlock, identifier, identifier2, identifier3, identifier4, true));
    }
    public final void registerPearBlock(BlockStateModelGenerator blockStateModelGenerator, Block pearBlock) {
        Identifier outerID = Models.TEMPLATE_SINGLE_FACE.upload(pearBlock, TextureMap.texture(pearBlock), blockStateModelGenerator.modelCollector);
        Identifier insideID = Models.TEMPLATE_SINGLE_FACE.upload(pearBlock, "_inside", TextureMap.texture(getId(pearBlock, "_inside")), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(MultipartBlockStateSupplier.create(pearBlock).with(When.create().set(Properties.NORTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, outerID)).with(When.create().set(Properties.EAST, true), BlockStateVariant.create().put(VariantSettings.MODEL, outerID).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, true)).with(When.create().set(Properties.SOUTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, outerID).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, true)).with(When.create().set(Properties.WEST, true), BlockStateVariant.create().put(VariantSettings.MODEL, outerID).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, true)).with(When.create().set(Properties.UP, true), BlockStateVariant.create().put(VariantSettings.MODEL, outerID).put(VariantSettings.X, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, true)).with(When.create().set(Properties.DOWN, true), BlockStateVariant.create().put(VariantSettings.MODEL, outerID).put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, true)).with(When.create().set(Properties.NORTH, false), BlockStateVariant.create().put(VariantSettings.MODEL, insideID)).with(When.create().set(Properties.EAST, false), BlockStateVariant.create().put(VariantSettings.MODEL, insideID).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, false)).with(When.create().set(Properties.SOUTH, false), BlockStateVariant.create().put(VariantSettings.MODEL, insideID).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, false)).with(When.create().set(Properties.WEST, false), BlockStateVariant.create().put(VariantSettings.MODEL, insideID).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, false)).with(When.create().set(Properties.UP, false), BlockStateVariant.create().put(VariantSettings.MODEL, insideID).put(VariantSettings.X, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, false)).with(When.create().set(Properties.DOWN, false), BlockStateVariant.create().put(VariantSettings.MODEL, insideID).put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, false)));
        blockStateModelGenerator.registerParentedItemModel(pearBlock, TexturedModel.CUBE_ALL.upload(pearBlock, "_inventory", blockStateModelGenerator.modelCollector));
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
