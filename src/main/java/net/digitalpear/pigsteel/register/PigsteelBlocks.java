package net.digitalpear.pigsteel.register;

import com.ibm.icu.text.UnicodeSetSpanner;
import net.digitalpear.pigsteel.PigsteelMod;
import net.digitalpear.pigsteel.common.blocks.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.fabricmc.loader.api.FabricLoader;
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
        return Registry.register(Registries.ITEM, new Identifier(PigsteelMod.MOD_ID, blockID), new BlockItem(block, new Item.Settings()));
    }
    public static Block createOreBlock(String blockID, Block baseBlock) {
        return createBlockWithItem(blockID, new ExperienceDroppingBlock(FabricBlockSettings.copy(baseBlock).mapColor(MapColor.PALE_PURPLE)));
    }

    private static Block createBlockWithItem(String blockID, Block block) {
        createBlockItem(blockID, block);
        return Registry.register(Registries.BLOCK, new Identifier(PigsteelMod.MOD_ID, blockID), block);
    }


    public static Block makeCutBlocks(Block base, Oxidizable.OxidationLevel oxidationLevel){
        Map<Oxidizable.OxidationLevel, MapColor> COLOR_MAP = new HashMap<>();
        COLOR_MAP.put(Oxidizable.OxidationLevel.UNAFFECTED, MapColor.PURPLE);
        COLOR_MAP.put(Oxidizable.OxidationLevel.EXPOSED, MapColor.PALE_GREEN);
        COLOR_MAP.put(Oxidizable.OxidationLevel.WEATHERED, MapColor.GREEN);
        COLOR_MAP.put(Oxidizable.OxidationLevel.OXIDIZED, MapColor.DARK_GREEN);

        Map<Oxidizable.OxidationLevel, String> NAME_MAP = new HashMap<>();
        NAME_MAP.put(Oxidizable.OxidationLevel.UNAFFECTED, "");
        NAME_MAP.put(Oxidizable.OxidationLevel.EXPOSED, "infected_");
        NAME_MAP.put(Oxidizable.OxidationLevel.WEATHERED, "corrupted_");
        NAME_MAP.put(Oxidizable.OxidationLevel.OXIDIZED, "zombified_");

        MapColor color = COLOR_MAP.get(oxidationLevel);
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
    public static Block makeWaxed(Block base){
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

    public static final Block PIGSTEEL_ORE = createOreBlock("pigsteel_ore", Blocks.NETHER_GOLD_ORE);
    public static final Block STONE_PIGSTEEL_ORE = createOreBlock("stone_pigsteel_ore", Blocks.IRON_ORE);
    public static final Block DEEPSLATE_PIGSTEEL_ORE = createOreBlock("deepslate_pigsteel_ore", Blocks.DEEPSLATE_IRON_ORE);

    //Oh the biomes you'll go
    public static Block BLUE_PIGSTEEL_ORE;
    public static Block BRIMSTONE_PIGSTEEL_ORE;


    public static final Block PIGSTEEL_BLOCK = createBlockWithItem("pigsteel_block", new PigsteelBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.NETHERITE).mapColor(MapColor.PURPLE)));
    public static final Block WAXED_PIGSTEEL_BLOCK = createBlockWithItem("waxed_pigsteel_block", new Block(AbstractBlock.Settings.copy(PIGSTEEL_BLOCK)));

    public static final Block CUT_PIGSTEEL = createBlockWithItem("cut_pigsteel", new ZombifiableBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.NETHERITE).mapColor(MapColor.PURPLE)));
    public static final Block INFECTED_CUT_PIGSTEEL = makeCutBlocks(CUT_PIGSTEEL, Oxidizable.OxidationLevel.EXPOSED);
    public static final Block CORRUPTED_CUT_PIGSTEEL = makeCutBlocks(CUT_PIGSTEEL, Oxidizable.OxidationLevel.WEATHERED);
    public static final Block ZOMBIFIED_CUT_PIGSTEEL = makeCutBlocks(CUT_PIGSTEEL, Oxidizable.OxidationLevel.OXIDIZED);

    public static final Block WAXED_CUT_PIGSTEEL = makeWaxed(CUT_PIGSTEEL);
    public static final Block WAXED_INFECTED_CUT_PIGSTEEL = makeWaxed(INFECTED_CUT_PIGSTEEL);
    public static final Block WAXED_CORRUPTED_CUT_PIGSTEEL = makeWaxed(CORRUPTED_CUT_PIGSTEEL);
    public static final Block WAXED_ZOMBIFIED_CUT_PIGSTEEL = makeWaxed(ZOMBIFIED_CUT_PIGSTEEL);

    public static final Block CUT_PIGSTEEL_STAIRS = createBlockWithItem("cut_pigsteel_stairs", new ZombifiableStairsBlock(Oxidizable.OxidationLevel.UNAFFECTED, CUT_PIGSTEEL.getDefaultState(), AbstractBlock.Settings.copy(CUT_PIGSTEEL)));
    public static final Block INFECTED_CUT_PIGSTEEL_STAIRS = makeCutBlocks(CUT_PIGSTEEL_STAIRS, Oxidizable.OxidationLevel.EXPOSED);
    public static final Block CORRUPTED_CUT_PIGSTEEL_STAIRS = makeCutBlocks(CUT_PIGSTEEL_STAIRS, Oxidizable.OxidationLevel.WEATHERED);
    public static final Block ZOMBIFIED_CUT_PIGSTEEL_STAIRS = makeCutBlocks(CUT_PIGSTEEL_STAIRS, Oxidizable.OxidationLevel.OXIDIZED);

    public static final Block WAXED_CUT_PIGSTEEL_STAIRS = makeWaxed(CUT_PIGSTEEL_STAIRS);
    public static final Block WAXED_INFECTED_CUT_PIGSTEEL_STAIRS = makeWaxed(INFECTED_CUT_PIGSTEEL_STAIRS);
    public static final Block WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS = makeWaxed(CORRUPTED_CUT_PIGSTEEL_STAIRS);
    public static final Block WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS = makeWaxed(ZOMBIFIED_CUT_PIGSTEEL_STAIRS);

    public static final Block CUT_PIGSTEEL_SLAB = createBlockWithItem("cut_pigsteel_slab", new ZombifiableSlabBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.copy(CUT_PIGSTEEL)));
    public static final Block INFECTED_CUT_PIGSTEEL_SLAB = makeCutBlocks(CUT_PIGSTEEL_SLAB, Oxidizable.OxidationLevel.EXPOSED);
    public static final Block CORRUPTED_CUT_PIGSTEEL_SLAB = makeCutBlocks(CUT_PIGSTEEL_SLAB, Oxidizable.OxidationLevel.WEATHERED);
    public static final Block ZOMBIFIED_CUT_PIGSTEEL_SLAB = makeCutBlocks(CUT_PIGSTEEL_SLAB, Oxidizable.OxidationLevel.OXIDIZED);

    public static final Block WAXED_CUT_PIGSTEEL_SLAB = makeWaxed(CUT_PIGSTEEL_SLAB);
    public static final Block WAXED_INFECTED_CUT_PIGSTEEL_SLAB = makeWaxed(INFECTED_CUT_PIGSTEEL_SLAB);
    public static final Block WAXED_CORRUPTED_CUT_PIGSTEEL_SLAB = makeWaxed(CORRUPTED_CUT_PIGSTEEL_SLAB);
    public static final Block WAXED_ZOMBIFIED_CUT_PIGSTEEL_SLAB = makeWaxed(ZOMBIFIED_CUT_PIGSTEEL_SLAB);

    public static final Block PIGSTEEL_LANTERN = createBlockWithItem("pigsteel_lantern", new PigsteelLanternBlock(FabricBlockSettings.copy(Blocks.LANTERN).luminance(state -> 15).nonOpaque().mapColor(MapColor.PURPLE)));
    public static final Block PIGSTEEL_SOUL_LANTERN = createBlockWithItem("pigsteel_soul_lantern", new PigsteelLanternBlock(FabricBlockSettings.copy(Blocks.LANTERN).luminance(state -> 10).nonOpaque().mapColor(MapColor.PURPLE)));



    public static void init(){

        if (FabricLoader.getInstance().isModLoaded("byg")) {
            BLUE_PIGSTEEL_ORE = createOreBlock("blue_pigsteel_ore", Blocks.NETHER_GOLD_ORE);
            BRIMSTONE_PIGSTEEL_ORE = createOreBlock("brimstone_pigsteel_ore", Blocks.NETHER_GOLD_ORE);
        }
        PIGSTEEL_WAXING_MAP.put(PIGSTEEL_BLOCK, WAXED_PIGSTEEL_BLOCK);

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

        PIGSTEEL_ZOMBIFYING_MAP.put(CUT_PIGSTEEL, INFECTED_CUT_PIGSTEEL);
        PIGSTEEL_ZOMBIFYING_MAP.put(INFECTED_CUT_PIGSTEEL, CORRUPTED_CUT_PIGSTEEL);
        PIGSTEEL_ZOMBIFYING_MAP.put(CORRUPTED_CUT_PIGSTEEL, ZOMBIFIED_CUT_PIGSTEEL);

        PIGSTEEL_ZOMBIFYING_MAP.put(CUT_PIGSTEEL_STAIRS, INFECTED_CUT_PIGSTEEL_STAIRS);
        PIGSTEEL_ZOMBIFYING_MAP.put(INFECTED_CUT_PIGSTEEL_STAIRS, CORRUPTED_CUT_PIGSTEEL_STAIRS);
        PIGSTEEL_ZOMBIFYING_MAP.put(CORRUPTED_CUT_PIGSTEEL_STAIRS, ZOMBIFIED_CUT_PIGSTEEL_STAIRS);

        PIGSTEEL_ZOMBIFYING_MAP.put(CUT_PIGSTEEL_SLAB, INFECTED_CUT_PIGSTEEL_SLAB);
        PIGSTEEL_ZOMBIFYING_MAP.put(INFECTED_CUT_PIGSTEEL_SLAB, CORRUPTED_CUT_PIGSTEEL_SLAB);
        PIGSTEEL_ZOMBIFYING_MAP.put(CORRUPTED_CUT_PIGSTEEL_SLAB, ZOMBIFIED_CUT_PIGSTEEL_SLAB);


        PIGSTEEL_WAXING_MAP.forEach(OxidizableBlocksRegistry::registerWaxableBlockPair);
        PIGSTEEL_ZOMBIFYING_MAP.forEach(OxidizableBlocksRegistry::registerOxidizableBlockPair);

    }
}
