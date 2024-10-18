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

import java.util.function.BiFunction;
import java.util.function.Function;


@SuppressWarnings("unused")
public class PigsteelBlocks {

    public static Item createBlockItem(String id, Block block, BiFunction<Block, Item.Settings, Item> factory) {
        Item.Settings settings = new Item.Settings().fireproof();
        return createBlockItem(id, block, factory, settings);
    }
    public static Item createBlockItem(String id, Block block, BiFunction<Block, Item.Settings, Item> factory, Item.Settings settings) {
        Item item = factory.apply(block, settings.registryKey(PigsteelItems.keyOf(id)));
        return Registry.register(Registries.ITEM, PigsteelItems.keyOf(id), item);
    }

    private static RegistryKey<Block> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.BLOCK, Pigsteel.getModId(id));
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


    public static final ZombifiableBlockRegistry refinedPigsteel = new ZombifiableBlockRegistry("refined_pigsteel", ZombifiableBlock::new, Block::new);
    public static final ZombifiableBlockRegistry cutPigsteel = new ZombifiableBlockRegistry("cut_pigsteel", ZombifiableBlock::new, Block::new);
    public static final ZombifiableBlockRegistry cutPigsteelSlabs = new ZombifiableBlockRegistry("cut_pigsteel_slab", ZombifiableSlabBlock::new, SlabBlock::new);
    public static final ZombifiableBlockRegistry pigsteelLanterns = new ZombifiableBlockRegistry("pigsteel_lantern", ZombifiableLanternBlock::new, PigsteelLanternBlock::new, lanternsSettings(15));
    public static final ZombifiableBlockRegistry pigsteelSoulLanterns = new ZombifiableBlockRegistry("pigsteel_soul_lantern", ZombifiableLanternBlock::new, PigsteelLanternBlock::new, lanternsSettings(10));
    public static final ZombifiableBlockRegistry cutPigsteelStairs = new ZombifiableBlockRegistry("cut_pigsteel_stairs", ZombifiableStairsBlock::new, settings -> new StairsBlock(cutPigsteel.getWaxedUnaffectedBlock().getDefaultState(), settings));

    public static void init(){
        ZombifiableBlockRegistry.registerWaxingAndZombifications();
    }
}