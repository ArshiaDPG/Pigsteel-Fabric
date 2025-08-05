package net.digitalpear.pigsteel.init;

import net.digitalpear.pigsteel.Pigsteel;
import net.digitalpear.pigsteel.common.blocks.*;
import net.digitalpear.pigsteel.init.data.ZombifiableBlockRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityType;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;



@SuppressWarnings("unused")
public class PigsteelBlocks {

    public static final BlockSetType PIGSTEEL_BLOCK_SET_TYPE = BlockSetTypeBuilder.copyOf(BlockSetType.COPPER)
            .openableByWindCharge(false)
            .build(Pigsteel.id("pigsteel"));

    @SuppressWarnings("all")
    public static Item createBlockItem(Block block, BiFunction<Block, Item.Settings, Item> factory) {
        Item.Settings settings = new Item.Settings().fireproof();
        return createBlockItem(block, factory, settings);
    }
    public static Item createBlockItem(Block block, BiFunction<Block, Item.Settings, Item> factory, Item.Settings settings) {
        return Items.register(block, factory, settings);
    }

    private static RegistryKey<Block> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.BLOCK, Pigsteel.id(id));
    }
    public static Block createBlockWithItem(String blockID, AbstractBlock.Settings settings) {
        return createBlockWithItem(blockID, Block::new, settings);
    }
    public static Block createBlockWithItem(String blockID, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        Block block = Blocks.register(keyOf(blockID), factory, settings);
        createBlockItem(block, BlockItem::new);
        return block;
    }


    public static final Block PORKSLAG = createBlockWithItem("porkslag",
            settings -> new ExperienceDroppingBlock(UniformIntProvider.create(0, 0), settings),
            AbstractBlock.Settings.create()
                .ticksRandomly()
                .sounds(BlockSoundGroup.POLISHED_DEEPSLATE)
                .strength(2.5f)
                .mapColor(MapColor.PURPLE)
                .requiresTool());


    public static final Block PIGSTEEL_CHUNK_BLOCK = createBlockWithItem("pigsteel_chunk_block",
            AbstractBlock.Settings.copy(Blocks.RAW_IRON_BLOCK).mapColor(MapColor.PALE_PURPLE));

    public static AbstractBlock.Settings lanternsSettings(int lightValue){
        return AbstractBlock.Settings.create().solid().requiresTool().strength(3.5F).sounds(BlockSoundGroup.LANTERN).nonOpaque().pistonBehavior(PistonBehavior.DESTROY).luminance(state -> lightValue);
    }

    public static final ZombifiableBlockRegistry REFINED_PIGSTEEL = new ZombifiableBlockRegistry.Builder("refined_pigsteel", ZombifiableBlock::new, (level, settings) -> new Block(settings)).build();
    public static final ZombifiableBlockRegistry CUT_PIGSTEEL = new ZombifiableBlockRegistry.Builder("cut_pigsteel", ZombifiableBlock::new, (level, settings) -> new Block(settings)).build();
    public static final ZombifiableBlockRegistry CUT_PIGSTEEL_STAIRS = new ZombifiableBlockRegistry.Builder("cut_pigsteel_stairs", (level, settings) -> new ZombifiableStairsBlock(level, CUT_PIGSTEEL.getBlockFromLevel(level).getDefaultState(), settings), (level, settings) -> new StairsBlock(CUT_PIGSTEEL.getWaxedBlockFromLevel(level).getDefaultState(), settings)).build();
    public static final ZombifiableBlockRegistry CUT_PIGSTEEL_SLABS = new ZombifiableBlockRegistry.Builder("cut_pigsteel_slab", ZombifiableSlabBlock::new, (level, settings) -> new SlabBlock(settings)).build();
    public static final ZombifiableBlockRegistry PIGSTEEL_LANTERNS = new ZombifiableBlockRegistry.Builder("pigsteel_lantern", ZombifiableLanternBlock::new, (level, settings) -> new PigsteelLanternBlock(settings)).setBlockSettings(lanternsSettings(15)).build();
    public static final ZombifiableBlockRegistry PIGSTEEL_SOUL_LANTERNS = new ZombifiableBlockRegistry.Builder("pigsteel_soul_lantern", ZombifiableLanternBlock::new, (level, settings) -> new PigsteelLanternBlock(settings)).setBlockSettings(lanternsSettings(10)).build();

    public static final ZombifiableBlockRegistry PIGSTEEL_DOORS = new ZombifiableBlockRegistry.Builder("pigsteel_door", (level, settings) -> new ZombifiableDoorBlock(PIGSTEEL_BLOCK_SET_TYPE, level, settings), (level, settings) -> new DoorBlock(PIGSTEEL_BLOCK_SET_TYPE, settings)).build();
    public static final ZombifiableBlockRegistry PIGSTEEL_TRAPDOORS = new ZombifiableBlockRegistry.Builder("pigsteel_trapdoor", (level, settings) -> new ZombifiableTrapdoorBlock(PIGSTEEL_BLOCK_SET_TYPE, level, settings), (level, settings) -> new TrapdoorBlock(PIGSTEEL_BLOCK_SET_TYPE, settings)).build();
    public static final ZombifiableBlockRegistry CHISELED_PIGSTEEL = new ZombifiableBlockRegistry.Builder("chiseled_pigsteel", ZombifiableBlock::new, (level, settings) -> new Block(settings)).build();
    public static final ZombifiableBlockRegistry PIGSTEEL_BARRELS = new ZombifiableBlockRegistry.Builder("pigsteel_barrel", ZombifiableBarrelBlock::new, (level, settings) -> new BarrelBlock(settings)).build();
    public static final ZombifiableBlockRegistry PIGSTEEL_BARS = new ZombifiableBlockRegistry.Builder("pigsteel_bars", ZombifiablePaneBlock::new, (level, settings) -> new PaneBlock(settings)).setBlockSettings(AbstractBlock.Settings.copy(Blocks.IRON_BARS).sounds(BlockSoundGroup.COPPER)).build();
    public static final ZombifiableBlockRegistry PIGSTEEL_GRATE = new ZombifiableBlockRegistry.Builder("pigsteel_grate", ZombifiableBlock::new, (level, settings) -> new Block(settings)).setBlockSettings(AbstractBlock.Settings.copy(Blocks.COPPER_GRATE)).build();

    public static void init(){
        var barrelEntity = (FabricBlockEntityType) BlockEntityType.BARREL;
        PigsteelBlocks.PIGSTEEL_BARRELS.getAllBlocks().forEach(barrelEntity::addSupportedBlock);

        ZombifiableBlockRegistry.registerWaxingAndZombifications();

        List<String> ORE_NAMES = List.of(
                "pigsteel_ore",
                "stone_pigsteel_ore",
                "deepslate_pigsteel_ore",
                "blue_pigsteel_ore"
        );
        ORE_NAMES.forEach(s -> Registries.BLOCK.addAlias(Pigsteel.id(s), Registries.BLOCK.getId(PORKSLAG)));
        Registries.BLOCK.addAlias(Pigsteel.id("pigsteel_block"), Registries.BLOCK.getId(PIGSTEEL_CHUNK_BLOCK));
    }
}