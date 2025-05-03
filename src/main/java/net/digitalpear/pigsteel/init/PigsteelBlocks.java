package net.digitalpear.pigsteel.init;

import net.digitalpear.pigsteel.Pigsteel;
import net.digitalpear.pigsteel.common.blocks.*;
import net.digitalpear.pigsteel.init.data.ZombifiableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;


@SuppressWarnings("unused")
public class PigsteelBlocks {

    @SuppressWarnings("all")
    public static Item createBlockItem(String id, Block block, BiFunction<Block, Item.Settings, Item> factory) {
        Item.Settings settings = new Item.Settings().fireproof();
        return createBlockItem(id, block, factory, settings);
    }
    public static Item createBlockItem(String id, Block block, BiFunction<Block, Item.Settings, Item> factory, Item.Settings settings) {
        Item item = factory.apply(block, settings.registryKey(PigsteelItems.keyOf(id)));
        return Registry.register(Registries.ITEM, PigsteelItems.keyOf(id), item);
    }

    private static RegistryKey<Block> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.BLOCK, Pigsteel.id(id));
    }
    public static Block createBlockWithItem(String blockID, Function<AbstractBlock.Settings, Block> factory) {
        return createBlockWithItem(blockID, factory, AbstractBlock.Settings.create());
    }
    public static Block createBlockWithItem(String blockID, AbstractBlock.Settings settings) {
        return createBlockWithItem(blockID, Block::new, settings);
    }
    public static Block createBlockWithItem(String blockID, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        Block block = Blocks.register(keyOf(blockID), factory, settings);
        createBlockItem(blockID, block, BlockItem::new);
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

    public static Block pigsteelBlockSettings(Zombifiable.ZombificationLevel level){
        return new ZombifiableBlock(level, AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.NETHERITE).ticksRandomly());
    }
    public static AbstractBlock.Settings lanternsSettings(int lightValue){
        return AbstractBlock.Settings.create().solid().requiresTool().strength(3.5F).sounds(BlockSoundGroup.LANTERN).nonOpaque().pistonBehavior(PistonBehavior.DESTROY).luminance(state -> lightValue);
    }

    public static final ZombifiableBlockRegistry REFINED_PIGSTEEL = new ZombifiableBlockRegistry("refined_pigsteel", ZombifiableBlock::new, (level, settings) -> new Block(settings));
    public static final ZombifiableBlockRegistry CUT_PIGSTEEL = new ZombifiableBlockRegistry("cut_pigsteel", ZombifiableBlock::new, (level, settings) -> new Block(settings));
    public static final ZombifiableBlockRegistry CUT_PIGSTEEL_STAIRS = new ZombifiableBlockRegistry("cut_pigsteel_stairs", (level, settings) -> new ZombifiableStairsBlock(level, CUT_PIGSTEEL.getBlockFromLevel(level).getDefaultState(), settings), (level, settings) -> new StairsBlock(CUT_PIGSTEEL.getWaxedBlockFromLevel(level).getDefaultState(), settings));
    public static final ZombifiableBlockRegistry CUT_PIGSTEEL_SLABS = new ZombifiableBlockRegistry("cut_pigsteel_slab", ZombifiableSlabBlock::new, (level, settings) -> new SlabBlock(settings));
    public static final ZombifiableBlockRegistry PIGSTEEL_LANTERNS = new ZombifiableBlockRegistry("pigsteel_lantern", ZombifiableLanternBlock::new, (level, settings) -> new PigsteelLanternBlock(settings), lanternsSettings(15));
    public static final ZombifiableBlockRegistry PIGSTEEL_SOUL_LANTERNS = new ZombifiableBlockRegistry("pigsteel_soul_lantern", ZombifiableLanternBlock::new, (level, settings) -> new PigsteelLanternBlock(settings), lanternsSettings(10));

    public static void init(){
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