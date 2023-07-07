package net.digitalpear.pigsteel.register;

import com.google.common.collect.ImmutableMap;
import net.digitalpear.pigsteel.Pigsteel;
import net.digitalpear.pigsteel.common.blocks.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;


@SuppressWarnings("unused")
public class PigsteelBlocks {
    public static Map<Block, Block> PIGSTEEL_WAXING_MAP = new HashMap<>();
    public static Map<Block, Block> PIGSTEEL_ZOMBIFYING_MAP = new HashMap<>();

    public static BlockItem createBlockItem(String blockID, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Pigsteel.MOD_ID, blockID), new BlockItem(block, new Item.Settings().fireproof()));
    }
    public static Block createOreBlock(String blockID, Block baseBlock) {
        return createBlockWithItem(blockID, new ExperienceDroppingBlock(FabricBlockSettings.copy(baseBlock).mapColor(MapColor.PALE_PURPLE)));
    }

    private static Block createBlockWithItem(String blockID, Block block) {
        createBlockItem(blockID, block);
        return Registry.register(Registries.BLOCK, new Identifier(Pigsteel.MOD_ID, blockID), block);
    }

    public static final ImmutableMap COLOR_MAP = new ImmutableMap.Builder()
            .put(Oxidizable.OxidationLevel.UNAFFECTED, MapColor.PURPLE)
            .put(Oxidizable.OxidationLevel.EXPOSED, MapColor.PALE_GREEN)
            .put(Oxidizable.OxidationLevel.WEATHERED, MapColor.GREEN)
            .put(Oxidizable.OxidationLevel.OXIDIZED, MapColor.DARK_GREEN).build();

    public static final ImmutableMap NAME_MAP = new ImmutableMap.Builder()
            .put(Oxidizable.OxidationLevel.UNAFFECTED, "")
            .put(Oxidizable.OxidationLevel.EXPOSED, "infected_")
            .put(Oxidizable.OxidationLevel.WEATHERED, "corrupted_")
            .put(Oxidizable.OxidationLevel.OXIDIZED, "zombified_").build();

    public static Block makeCutBlocks(Block base, Oxidizable.OxidationLevel oxidationLevel){
        MapColor color = (MapColor) COLOR_MAP.get(oxidationLevel);
        String name = NAME_MAP.get(oxidationLevel) + Registries.BLOCK.getId(base).getPath();
        AbstractBlock.Settings settings = AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.NETHERITE).mapColor(color);

        if (base instanceof StairsBlock){
            return createBlockWithItem(name, new ZombifiableStairsBlock(oxidationLevel, base.getDefaultState(), settings));
        }
        else if (base instanceof SlabBlock){
            return createBlockWithItem(name, new ZombifiableSlabBlock(oxidationLevel, settings));
        }
        else{
            return createBlockWithItem(name, new ZombifiableBlock(oxidationLevel, settings));
        }
    }
    public static Block createWaxed(Block base){
        Block blockSettings;
        if (base instanceof StairsBlock){
            blockSettings = new StairsBlock(base.getDefaultState(),AbstractBlock.Settings.copy(base));
        }
        else if (base instanceof SlabBlock){
            blockSettings = new SlabBlock(AbstractBlock.Settings.copy(base));
        }
        else{
            blockSettings = new Block(AbstractBlock.Settings.copy(base));
        }
        return createBlockWithItem("waxed_" + Registries.BLOCK.getId(base).getPath(), blockSettings);
    }
    public static Block createPaneBlock(Oxidizable.OxidationLevel oxidationLevel){
        return new ZombifiablePaneBlock(oxidationLevel, AbstractBlock.Settings.copy(Blocks.IRON_BARS).sounds(BlockSoundGroup.NETHERITE).nonOpaque().ticksRandomly().mapColor((MapColor) COLOR_MAP.get(oxidationLevel)));
    }
    public static Block createWaxedPaneBlock(Oxidizable.OxidationLevel oxidationLevel){
        return new PaneBlock(AbstractBlock.Settings.copy(Blocks.IRON_BARS).sounds(BlockSoundGroup.NETHERITE).nonOpaque().mapColor((MapColor) COLOR_MAP.get(oxidationLevel)));
    }

    public static final Block PORKSLAG = createBlockWithItem("porkslag", new Block(AbstractBlock.Settings.create()
            .sounds(BlockSoundGroup.POLISHED_DEEPSLATE)
            .strength(1.5f)
            .mapColor(MapColor.BLACK)
            .requiresTool()));


    public static final Block RAW_PIGSTEEL_BLOCK = createBlockWithItem("raw_pigsteel_block", new Block(AbstractBlock.Settings.copy(Blocks.RAW_IRON_BLOCK)));

    public static Block pigsteelBlockSettings(Oxidizable.OxidationLevel level){
        return new ZombifiableBlock(level, AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.NETHERITE).ticksRandomly().mapColor((MapColor) COLOR_MAP.get(level)));
    }

    public static final Block PIGSTEEL_BLOCK = createBlockWithItem("pigsteel_block", pigsteelBlockSettings(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block INFECTED_PIGSTEEL = createBlockWithItem("infected_pigsteel", pigsteelBlockSettings(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block CORRUPTED_PIGSTEEL = createBlockWithItem("corrupted_pigsteel", pigsteelBlockSettings(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block ZOMBIFIED_PIGSTEEL = createBlockWithItem("zombified_pigsteel", pigsteelBlockSettings(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_PIGSTEEL_BLOCK = createBlockWithItem("waxed_pigsteel_block", new Block(AbstractBlock.Settings.copy(PIGSTEEL_BLOCK)));
    public static final Block WAXED_INFECTED_PIGSTEEL = createWaxed(INFECTED_PIGSTEEL);
    public static final Block WAXED_CORRUPTED_PIGSTEEL = createWaxed(CORRUPTED_PIGSTEEL);
    public static final Block WAXED_ZOMBIFIED_PIGSTEEL = createWaxed(ZOMBIFIED_PIGSTEEL);

    public static final Block CUT_PIGSTEEL = createBlockWithItem("cut_pigsteel", pigsteelBlockSettings(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block INFECTED_CUT_PIGSTEEL = makeCutBlocks(CUT_PIGSTEEL, Oxidizable.OxidationLevel.EXPOSED);
    public static final Block CORRUPTED_CUT_PIGSTEEL = makeCutBlocks(CUT_PIGSTEEL, Oxidizable.OxidationLevel.WEATHERED);
    public static final Block ZOMBIFIED_CUT_PIGSTEEL = makeCutBlocks(CUT_PIGSTEEL, Oxidizable.OxidationLevel.OXIDIZED);

    public static final Block WAXED_CUT_PIGSTEEL = createWaxed(CUT_PIGSTEEL);
    public static final Block WAXED_INFECTED_CUT_PIGSTEEL = createWaxed(INFECTED_CUT_PIGSTEEL);
    public static final Block WAXED_CORRUPTED_CUT_PIGSTEEL = createWaxed(CORRUPTED_CUT_PIGSTEEL);
    public static final Block WAXED_ZOMBIFIED_CUT_PIGSTEEL = createWaxed(ZOMBIFIED_CUT_PIGSTEEL);

    public static final Block CUT_PIGSTEEL_STAIRS = createBlockWithItem("cut_pigsteel_stairs", new ZombifiableStairsBlock(Oxidizable.OxidationLevel.UNAFFECTED, CUT_PIGSTEEL.getDefaultState(), AbstractBlock.Settings.copy(CUT_PIGSTEEL)));
    public static final Block INFECTED_CUT_PIGSTEEL_STAIRS = makeCutBlocks(CUT_PIGSTEEL_STAIRS, Oxidizable.OxidationLevel.EXPOSED);
    public static final Block CORRUPTED_CUT_PIGSTEEL_STAIRS = makeCutBlocks(CUT_PIGSTEEL_STAIRS, Oxidizable.OxidationLevel.WEATHERED);
    public static final Block ZOMBIFIED_CUT_PIGSTEEL_STAIRS = makeCutBlocks(CUT_PIGSTEEL_STAIRS, Oxidizable.OxidationLevel.OXIDIZED);

    public static final Block WAXED_CUT_PIGSTEEL_STAIRS = createWaxed(CUT_PIGSTEEL_STAIRS);
    public static final Block WAXED_INFECTED_CUT_PIGSTEEL_STAIRS = createWaxed(INFECTED_CUT_PIGSTEEL_STAIRS);
    public static final Block WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS = createWaxed(CORRUPTED_CUT_PIGSTEEL_STAIRS);
    public static final Block WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS = createWaxed(ZOMBIFIED_CUT_PIGSTEEL_STAIRS);

    public static final Block CUT_PIGSTEEL_SLAB = createBlockWithItem("cut_pigsteel_slab", new ZombifiableSlabBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.copy(CUT_PIGSTEEL)));
    public static final Block INFECTED_CUT_PIGSTEEL_SLAB = makeCutBlocks(CUT_PIGSTEEL_SLAB, Oxidizable.OxidationLevel.EXPOSED);
    public static final Block CORRUPTED_CUT_PIGSTEEL_SLAB = makeCutBlocks(CUT_PIGSTEEL_SLAB, Oxidizable.OxidationLevel.WEATHERED);
    public static final Block ZOMBIFIED_CUT_PIGSTEEL_SLAB = makeCutBlocks(CUT_PIGSTEEL_SLAB, Oxidizable.OxidationLevel.OXIDIZED);

    public static final Block WAXED_CUT_PIGSTEEL_SLAB = createWaxed(CUT_PIGSTEEL_SLAB);
    public static final Block WAXED_INFECTED_CUT_PIGSTEEL_SLAB = createWaxed(INFECTED_CUT_PIGSTEEL_SLAB);
    public static final Block WAXED_CORRUPTED_CUT_PIGSTEEL_SLAB = createWaxed(CORRUPTED_CUT_PIGSTEEL_SLAB);
    public static final Block WAXED_ZOMBIFIED_CUT_PIGSTEEL_SLAB = createWaxed(ZOMBIFIED_CUT_PIGSTEEL_SLAB);

    public static final Block PIGSTEEL_LANTERN = createBlockWithItem("pigsteel_lantern", new PigsteelLanternBlock(FabricBlockSettings.copy(Blocks.LANTERN).luminance(state -> 15).nonOpaque().mapColor(MapColor.PURPLE)));
    public static final Block PIGSTEEL_SOUL_LANTERN = createBlockWithItem("pigsteel_soul_lantern", new PigsteelLanternBlock(FabricBlockSettings.copy(Blocks.LANTERN).luminance(state -> 10).nonOpaque().mapColor(MapColor.PURPLE)));

    public static final Block PIGSTEEL_BARS = createBlockWithItem("pigsteel_bars", createPaneBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block INFECTED_PIGSTEEL_BARS = createBlockWithItem("infected_pigsteel_bars", createPaneBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block CORRUPTED_PIGSTEEL_BARS = createBlockWithItem("corrupted_pigsteel_bars", createPaneBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block ZOMBIFIED_PIGSTEEL_BARS = createBlockWithItem("zombified_pigsteel_bars", createPaneBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static final Block WAXED_PIGSTEEL_BARS = createBlockWithItem("waxed_pigsteel_bars", createWaxedPaneBlock(Oxidizable.OxidationLevel.UNAFFECTED));
    public static final Block WAXED_INFECTED_PIGSTEEL_BARS = createBlockWithItem("waxed_infected_pigsteel_bars", createWaxedPaneBlock(Oxidizable.OxidationLevel.EXPOSED));
    public static final Block WAXED_CORRUPTED_PIGSTEEL_BARS = createBlockWithItem("waxed_corrupted_pigsteel_bars", createWaxedPaneBlock(Oxidizable.OxidationLevel.WEATHERED));
    public static final Block WAXED_ZOMBIFIED_PIGSTEEL_BARS = createBlockWithItem("waxed_zombified_pigsteel_bars", createWaxedPaneBlock(Oxidizable.OxidationLevel.OXIDIZED));

    public static void init(){
        PIGSTEEL_WAXING_MAP.put(PIGSTEEL_BLOCK, WAXED_PIGSTEEL_BLOCK);
        PIGSTEEL_WAXING_MAP.put(INFECTED_PIGSTEEL, WAXED_INFECTED_PIGSTEEL);
        PIGSTEEL_WAXING_MAP.put(CORRUPTED_PIGSTEEL, WAXED_CORRUPTED_PIGSTEEL);
        PIGSTEEL_WAXING_MAP.put(ZOMBIFIED_PIGSTEEL, WAXED_ZOMBIFIED_PIGSTEEL);

        PIGSTEEL_WAXING_MAP.put(CUT_PIGSTEEL, WAXED_CUT_PIGSTEEL);
        PIGSTEEL_WAXING_MAP.put(INFECTED_CUT_PIGSTEEL, WAXED_INFECTED_CUT_PIGSTEEL);
        PIGSTEEL_WAXING_MAP.put(CORRUPTED_CUT_PIGSTEEL, WAXED_CORRUPTED_CUT_PIGSTEEL);
        PIGSTEEL_WAXING_MAP.put(ZOMBIFIED_CUT_PIGSTEEL, WAXED_ZOMBIFIED_CUT_PIGSTEEL);

        PIGSTEEL_WAXING_MAP.put(CUT_PIGSTEEL_STAIRS, WAXED_CUT_PIGSTEEL_STAIRS);
        PIGSTEEL_WAXING_MAP.put(INFECTED_CUT_PIGSTEEL_STAIRS, WAXED_INFECTED_CUT_PIGSTEEL_STAIRS);
        PIGSTEEL_WAXING_MAP.put(CORRUPTED_CUT_PIGSTEEL_STAIRS, WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS);
        PIGSTEEL_WAXING_MAP.put(ZOMBIFIED_CUT_PIGSTEEL_STAIRS, WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS);

        PIGSTEEL_WAXING_MAP.put(CUT_PIGSTEEL_SLAB, WAXED_CUT_PIGSTEEL_SLAB);
        PIGSTEEL_WAXING_MAP.put(INFECTED_CUT_PIGSTEEL_SLAB, WAXED_INFECTED_CUT_PIGSTEEL_SLAB);
        PIGSTEEL_WAXING_MAP.put(CORRUPTED_CUT_PIGSTEEL_SLAB, WAXED_CORRUPTED_CUT_PIGSTEEL_SLAB);
        PIGSTEEL_WAXING_MAP.put(ZOMBIFIED_CUT_PIGSTEEL_SLAB, WAXED_ZOMBIFIED_CUT_PIGSTEEL_SLAB);

        PIGSTEEL_WAXING_MAP.put(PIGSTEEL_BARS, WAXED_PIGSTEEL_BARS);
        PIGSTEEL_WAXING_MAP.put(INFECTED_PIGSTEEL_BARS, WAXED_INFECTED_PIGSTEEL_BARS);
        PIGSTEEL_WAXING_MAP.put(CORRUPTED_PIGSTEEL_BARS, WAXED_CORRUPTED_PIGSTEEL_BARS);
        PIGSTEEL_WAXING_MAP.put(ZOMBIFIED_PIGSTEEL_BARS, WAXED_ZOMBIFIED_PIGSTEEL_BARS);



        PIGSTEEL_ZOMBIFYING_MAP.put(PIGSTEEL_BLOCK, INFECTED_PIGSTEEL);
        PIGSTEEL_ZOMBIFYING_MAP.put(INFECTED_PIGSTEEL, CORRUPTED_PIGSTEEL);
        PIGSTEEL_ZOMBIFYING_MAP.put(CORRUPTED_PIGSTEEL, ZOMBIFIED_PIGSTEEL);

        PIGSTEEL_ZOMBIFYING_MAP.put(CUT_PIGSTEEL, INFECTED_CUT_PIGSTEEL);
        PIGSTEEL_ZOMBIFYING_MAP.put(INFECTED_CUT_PIGSTEEL, CORRUPTED_CUT_PIGSTEEL);
        PIGSTEEL_ZOMBIFYING_MAP.put(CORRUPTED_CUT_PIGSTEEL, ZOMBIFIED_CUT_PIGSTEEL);

        PIGSTEEL_ZOMBIFYING_MAP.put(CUT_PIGSTEEL_STAIRS, INFECTED_CUT_PIGSTEEL_STAIRS);
        PIGSTEEL_ZOMBIFYING_MAP.put(INFECTED_CUT_PIGSTEEL_STAIRS, CORRUPTED_CUT_PIGSTEEL_STAIRS);
        PIGSTEEL_ZOMBIFYING_MAP.put(CORRUPTED_CUT_PIGSTEEL_STAIRS, ZOMBIFIED_CUT_PIGSTEEL_STAIRS);

        PIGSTEEL_ZOMBIFYING_MAP.put(CUT_PIGSTEEL_SLAB, INFECTED_CUT_PIGSTEEL_SLAB);
        PIGSTEEL_ZOMBIFYING_MAP.put(INFECTED_CUT_PIGSTEEL_SLAB, CORRUPTED_CUT_PIGSTEEL_SLAB);
        PIGSTEEL_ZOMBIFYING_MAP.put(CORRUPTED_CUT_PIGSTEEL_SLAB, ZOMBIFIED_CUT_PIGSTEEL_SLAB);

        PIGSTEEL_ZOMBIFYING_MAP.put(PIGSTEEL_BARS, INFECTED_PIGSTEEL_BARS);
        PIGSTEEL_ZOMBIFYING_MAP.put(INFECTED_PIGSTEEL_BARS, CORRUPTED_PIGSTEEL_BARS);
        PIGSTEEL_ZOMBIFYING_MAP.put(CORRUPTED_PIGSTEEL_BARS, ZOMBIFIED_PIGSTEEL_BARS);



        PIGSTEEL_WAXING_MAP.forEach(OxidizableBlocksRegistry::registerWaxableBlockPair);
        PIGSTEEL_ZOMBIFYING_MAP.forEach(OxidizableBlocksRegistry::registerOxidizableBlockPair);

    }
}