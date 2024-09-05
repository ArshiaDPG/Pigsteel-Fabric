package net.digitalpear.pigsteel.init;

import net.digitalpear.pigsteel.Pigsteel;
import net.digitalpear.pigsteel.common.blocks.*;
import net.digitalpear.pigsteel.init.data.ZombifiableBlockRegistry;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import java.util.HashMap;
import java.util.Map;


@SuppressWarnings("unused")
public class PigsteelBlocks {
    public static Map<Block, Block> PIGSTEEL_WAXING_MAP = new HashMap<>();
    public static Map<Block, Block> PIGSTEEL_ZOMBIFYING_MAP = new HashMap<>();

    public static BlockItem createBlockItem(String blockID, Block block) {
        return Registry.register(Registries.ITEM, Pigsteel.getModId(blockID), new BlockItem(block, new Item.Settings().fireproof()));
    }

    public static Block createBlockWithItem(String blockID, Block block) {
        createBlockItem(blockID, block);
        return Registry.register(Registries.BLOCK, Pigsteel.getModId(blockID), block);
    }



    public static final Block PORKSLAG = createBlockWithItem("porkslag",
            new ExperienceDroppingBlock(UniformIntProvider.create(0, 0),
            AbstractBlock.Settings.create()
                .ticksRandomly()
                .sounds(BlockSoundGroup.POLISHED_DEEPSLATE)
                .strength(2.5f)
                .mapColor(MapColor.PURPLE)
                .requiresTool()));


    public static final Block PIGSTEEL_CHUNK_BLOCK = createBlockWithItem("pigsteel_chunk_block",
            new Block(AbstractBlock.Settings.copy(Blocks.RAW_IRON_BLOCK).mapColor(MapColor.PALE_PURPLE)));

    public static Block pigsteelBlockSettings(Zombifiable.ZombificationLevel level){
        return new ZombifiableBlock(level, AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.NETHERITE).ticksRandomly());
    }
    public static AbstractBlock.Settings lanternsSettings(int lightValue){
        return AbstractBlock.Settings.create().solid().requiresTool().strength(3.5F).sounds(BlockSoundGroup.LANTERN).nonOpaque().pistonBehavior(PistonBehavior.DESTROY).luminance(state -> lightValue);
    }


    public static final ZombifiableBlockRegistry refinedPigsteel = new ZombifiableBlockRegistry("refined_pigsteel", ZombifiableBlock.class, Block.class);
    public static final ZombifiableBlockRegistry cutPigsteel = new ZombifiableBlockRegistry("cut_pigsteel", ZombifiableBlock.class, Block.class);
    public static final ZombifiableBlockRegistry cutPigsteelSlabs = new ZombifiableBlockRegistry("cut_pigsteel_slab", ZombifiableSlabBlock.class, SlabBlock.class);
    public static final ZombifiableBlockRegistry pigsteelLanterns = new ZombifiableBlockRegistry("pigsteel_lantern", ZombifiableLanternBlock.class, PigsteelLanternBlock.class, lanternsSettings(15));
    public static final ZombifiableBlockRegistry pigsteelSoulLanterns = new ZombifiableBlockRegistry("pigsteel_soul_lantern", ZombifiableLanternBlock.class, PigsteelLanternBlock.class, lanternsSettings(10));

    public static final Block CUT_PIGSTEEL_STAIRS = createBlockWithItem("cut_pigsteel_stairs", new ZombifiableStairsBlock(cutPigsteel.getUnaffectedBlock().getDefaultState(), AbstractBlock.Settings.copy(cutPigsteel.getUnaffectedBlock())));
    public static final Block INFECTED_CUT_PIGSTEEL_STAIRS = createBlockWithItem("infected_cut_pigsteel_stairs", new ZombifiableStairsBlock(Zombifiable.ZombificationLevel.INFECTED, cutPigsteel.getInfectedBlock().getDefaultState(), AbstractBlock.Settings.copy(cutPigsteel.getInfectedBlock())));
    public static final Block CORRUPTED_CUT_PIGSTEEL_STAIRS = createBlockWithItem("corrupted_cut_pigsteel_stairs", new ZombifiableStairsBlock(Zombifiable.ZombificationLevel.CORRUPTED, cutPigsteel.getCorruptedBlock().getDefaultState(), AbstractBlock.Settings.copy(cutPigsteel.getCorruptedBlock())));
    public static final Block ZOMBIFIED_CUT_PIGSTEEL_STAIRS = createBlockWithItem("zombified_cut_pigsteel_stairs", new ZombifiableStairsBlock(Zombifiable.ZombificationLevel.ZOMBIFIED, cutPigsteel.getCorruptedBlock().getDefaultState(), AbstractBlock.Settings.copy(cutPigsteel.getZombifiedBlock())));

    public static final Block WAXED_CUT_PIGSTEEL_STAIRS = createBlockWithItem("waxed_cut_pigsteel_stairs", new StairsBlock(cutPigsteel.getWaxedUnaffectedBlock().getDefaultState(), AbstractBlock.Settings.copy(cutPigsteel.getWaxedUnaffectedBlock())));
    public static final Block WAXED_INFECTED_CUT_PIGSTEEL_STAIRS = createBlockWithItem("waxed_infected_cut_pigsteel_stairs", new StairsBlock(cutPigsteel.getWaxedInfectedBlock().getDefaultState(), AbstractBlock.Settings.copy(cutPigsteel.getWaxedInfectedBlock())));
    public static final Block WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS = createBlockWithItem("waxed_corrupted_cut_pigsteel_stairs", new StairsBlock(cutPigsteel.getWaxedCorruptedBlock().getDefaultState(), AbstractBlock.Settings.copy(cutPigsteel.getWaxedCorruptedBlock())));
    public static final Block WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS = createBlockWithItem("waxed_zombified_cut_pigsteel_stairs", new StairsBlock(cutPigsteel.getWaxedZombifiedBlock().getDefaultState(), AbstractBlock.Settings.copy(cutPigsteel.getWaxedZombifiedBlock())));

    public static void init(){
        PIGSTEEL_WAXING_MAP.put(CUT_PIGSTEEL_STAIRS, WAXED_CUT_PIGSTEEL_STAIRS);
        PIGSTEEL_WAXING_MAP.put(INFECTED_CUT_PIGSTEEL_STAIRS, WAXED_INFECTED_CUT_PIGSTEEL_STAIRS);
        PIGSTEEL_WAXING_MAP.put(CORRUPTED_CUT_PIGSTEEL_STAIRS, WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS);
        PIGSTEEL_WAXING_MAP.put(ZOMBIFIED_CUT_PIGSTEEL_STAIRS, WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS);

        PIGSTEEL_ZOMBIFYING_MAP.put(CUT_PIGSTEEL_STAIRS, INFECTED_CUT_PIGSTEEL_STAIRS);
        PIGSTEEL_ZOMBIFYING_MAP.put(INFECTED_CUT_PIGSTEEL_STAIRS, CORRUPTED_CUT_PIGSTEEL_STAIRS);
        PIGSTEEL_ZOMBIFYING_MAP.put(CORRUPTED_CUT_PIGSTEEL_STAIRS, ZOMBIFIED_CUT_PIGSTEEL_STAIRS);

        PIGSTEEL_WAXING_MAP.forEach(OxidizableBlocksRegistry::registerWaxableBlockPair);
        PIGSTEEL_ZOMBIFYING_MAP.forEach(OxidizableBlocksRegistry::registerOxidizableBlockPair);
    }
}