package net.digitalpear.pigsteel.register;

import net.digitalpear.pigsteel.PigsteelMod;
import net.digitalpear.pigsteel.common.blocks.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;


@SuppressWarnings("unused")
public class PigsteelBlocks {
    public static Map<Block, Block> PIGSTEEL_WAXING_MAP = new HashMap<>();
    public static Map<Block, Block> PIGSTEEL_ZOMBIFYING_MAP = new HashMap<>();

    public static BlockItem createBlockItem(String blockID, Block block, ItemGroup group) {
        return Registry.register(Registry.ITEM, new Identifier(PigsteelMod.MOD_ID, blockID), new BlockItem(block, new FabricItemSettings().group(group)));
    }
    public static Block createOreBlock(String blockID, Block baseBlock) {
        return createBlockWithItem(blockID, new OreBlock(FabricBlockSettings.copy(baseBlock).mapColor(MapColor.PALE_PURPLE)), ItemGroup.BUILDING_BLOCKS);
    }

    private static Block createBlockWithItem(String blockID, Block block, ItemGroup group) {
        createBlockItem(blockID, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(PigsteelMod.MOD_ID, blockID), block);
    }

    public static AbstractBlock.Settings pigsteelBlockSettings(MapColor color) {
        return AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.NETHERITE).mapColor(color);
    }

    public static AbstractBlock.Settings registerZombifiableBLock(Oxidizable.OxidationLevel oxidationLevel) {
//        MapColor color = MapColor.PURPLE;
//        if (oxidationLevel.equals(Oxidizable.OxidationLevel.EXPOSED)) {
//            color = MapColor.PALE_GREEN;
//        } else if (oxidationLevel.equals(Oxidizable.OxidationLevel.WEATHERED)) {
//            color = MapColor.GREEN;
//        } else if (oxidationLevel.equals(Oxidizable.OxidationLevel.OXIDIZED)) {
//            color = MapColor.DARK_GREEN;
//        }

        MapColor color;
        switch (oxidationLevel){
            case UNAFFECTED -> {
                color = MapColor.PURPLE;
                break;
            }
            case EXPOSED -> {
                color = MapColor.PALE_GREEN;
                break;
            }
            case WEATHERED -> {
                color = MapColor.GREEN;
                break;
            }
            case OXIDIZED -> {
                color = MapColor.DARK_GREEN;
                break;
            }
            default -> {
                color = MapColor.PURPLE;
            }
        }
        return pigsteelBlockSettings(color);
    }


    public static final Block PIGSTEEL_ORE = createOreBlock("pigsteel_ore", Blocks.NETHER_GOLD_ORE);
    public static final Block STONE_PIGSTEEL_ORE = createOreBlock("stone_pigsteel_ore", Blocks.IRON_ORE);
    public static final Block DEEPSLATE_PIGSTEEL_ORE = createOreBlock("deepslate_pigsteel_ore", Blocks.DEEPSLATE_IRON_ORE);

    //Oh the biomes you'll go
    public static Block BLUE_PIGSTEEL_ORE;
    public static Block BRIMSTONE_PIGSTEEL_ORE;

    public static final Block PIGSTEEL_BLOCK = createBlockWithItem("pigsteel_block", new PigsteelBlock(pigsteelBlockSettings(MapColor.PURPLE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block WAXED_PIGSTEEL_BLOCK = createBlockWithItem("waxed_pigsteel_block", new Block(pigsteelBlockSettings(MapColor.PURPLE)), ItemGroup.BUILDING_BLOCKS);

    public static final Block CUT_PIGSTEEL = createBlockWithItem("cut_pigsteel", new ZombifiableBlock(Oxidizable.OxidationLevel.UNAFFECTED,registerZombifiableBLock(Oxidizable.OxidationLevel.UNAFFECTED)), ItemGroup.BUILDING_BLOCKS);
    public static final Block INFECTED_CUT_PIGSTEEL = createBlockWithItem("infected_cut_pigsteel", new ZombifiableBlock(Oxidizable.OxidationLevel.EXPOSED,registerZombifiableBLock(Oxidizable.OxidationLevel.EXPOSED)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CORRUPTED_CUT_PIGSTEEL = createBlockWithItem("corrupted_cut_pigsteel", new ZombifiableBlock(Oxidizable.OxidationLevel.WEATHERED,registerZombifiableBLock(Oxidizable.OxidationLevel.WEATHERED)), ItemGroup.BUILDING_BLOCKS);
    public static final Block ZOMBIFIED_CUT_PIGSTEEL = createBlockWithItem("zombified_cut_pigsteel", new ZombifiableBlock(Oxidizable.OxidationLevel.OXIDIZED,registerZombifiableBLock(Oxidizable.OxidationLevel.OXIDIZED)), ItemGroup.BUILDING_BLOCKS);

    public static final Block WAXED_CUT_PIGSTEEL = createBlockWithItem("waxed_cut_pigsteel", new Block(registerZombifiableBLock(Oxidizable.OxidationLevel.UNAFFECTED)), ItemGroup.BUILDING_BLOCKS);
    public static final Block WAXED_INFECTED_CUT_PIGSTEEL = createBlockWithItem("waxed_infected_cut_pigsteel", new Block(registerZombifiableBLock(Oxidizable.OxidationLevel.EXPOSED)), ItemGroup.BUILDING_BLOCKS);
    public static final Block WAXED_CORRUPTED_CUT_PIGSTEEL = createBlockWithItem("waxed_corrupted_cut_pigsteel", new Block(registerZombifiableBLock(Oxidizable.OxidationLevel.WEATHERED)), ItemGroup.BUILDING_BLOCKS);
    public static final Block WAXED_ZOMBIFIED_CUT_PIGSTEEL = createBlockWithItem("waxed_zombified_cut_pigsteel", new Block(registerZombifiableBLock(Oxidizable.OxidationLevel.OXIDIZED)), ItemGroup.BUILDING_BLOCKS);

    public static final Block CUT_PIGSTEEL_STAIRS = createBlockWithItem("cut_pigsteel_stairs", new ZombifiableStairsBlock(Oxidizable.OxidationLevel.UNAFFECTED, CUT_PIGSTEEL.getDefaultState(), registerZombifiableBLock(Oxidizable.OxidationLevel.UNAFFECTED)), ItemGroup.BUILDING_BLOCKS);
    public static final Block INFECTED_CUT_PIGSTEEL_STAIRS = createBlockWithItem("infected_cut_pigsteel_stairs", new ZombifiableStairsBlock(Oxidizable.OxidationLevel.EXPOSED, INFECTED_CUT_PIGSTEEL.getDefaultState(), registerZombifiableBLock(Oxidizable.OxidationLevel.EXPOSED)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CORRUPTED_CUT_PIGSTEEL_STAIRS = createBlockWithItem("corrupted_cut_pigsteel_stairs", new ZombifiableStairsBlock(Oxidizable.OxidationLevel.WEATHERED,CORRUPTED_CUT_PIGSTEEL.getDefaultState(), registerZombifiableBLock(Oxidizable.OxidationLevel.WEATHERED)), ItemGroup.BUILDING_BLOCKS);
    public static final Block ZOMBIFIED_CUT_PIGSTEEL_STAIRS = createBlockWithItem("zombified_cut_pigsteel_stairs", new ZombifiableStairsBlock(Oxidizable.OxidationLevel.OXIDIZED,ZOMBIFIED_CUT_PIGSTEEL.getDefaultState(), registerZombifiableBLock(Oxidizable.OxidationLevel.OXIDIZED)), ItemGroup.BUILDING_BLOCKS);

    public static final Block WAXED_CUT_PIGSTEEL_STAIRS = createBlockWithItem("waxed_cut_pigsteel_stairs", new StairsBlock(WAXED_CUT_PIGSTEEL.getDefaultState(), registerZombifiableBLock(Oxidizable.OxidationLevel.UNAFFECTED)), ItemGroup.BUILDING_BLOCKS);
    public static final Block WAXED_INFECTED_CUT_PIGSTEEL_STAIRS = createBlockWithItem("waxed_infected_cut_pigsteel_stairs", new StairsBlock(WAXED_INFECTED_CUT_PIGSTEEL.getDefaultState(), registerZombifiableBLock(Oxidizable.OxidationLevel.EXPOSED)), ItemGroup.BUILDING_BLOCKS);
    public static final Block WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS = createBlockWithItem("waxed_corrupted_cut_pigsteel_stairs", new StairsBlock(WAXED_CORRUPTED_CUT_PIGSTEEL.getDefaultState(), registerZombifiableBLock(Oxidizable.OxidationLevel.WEATHERED)), ItemGroup.BUILDING_BLOCKS);
    public static final Block WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS = createBlockWithItem("waxed_zombified_cut_pigsteel_stairs", new StairsBlock(WAXED_ZOMBIFIED_CUT_PIGSTEEL.getDefaultState(), registerZombifiableBLock(Oxidizable.OxidationLevel.OXIDIZED)), ItemGroup.BUILDING_BLOCKS);

    public static final Block CUT_PIGSTEEL_SLAB = createBlockWithItem("cut_pigsteel_slab", new ZombifiableSlabBlock(Oxidizable.OxidationLevel.UNAFFECTED, registerZombifiableBLock(Oxidizable.OxidationLevel.UNAFFECTED)), ItemGroup.BUILDING_BLOCKS);
    public static final Block INFECTED_CUT_PIGSTEEL_SLAB = createBlockWithItem("infected_cut_pigsteel_slab", new ZombifiableSlabBlock(Oxidizable.OxidationLevel.EXPOSED, registerZombifiableBLock(Oxidizable.OxidationLevel.EXPOSED)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CORRUPTED_CUT_PIGSTEEL_SLAB = createBlockWithItem("corrupted_cut_pigsteel_slab", new ZombifiableSlabBlock(Oxidizable.OxidationLevel.WEATHERED, registerZombifiableBLock(Oxidizable.OxidationLevel.WEATHERED)), ItemGroup.BUILDING_BLOCKS);
    public static final Block ZOMBIFIED_CUT_PIGSTEEL_SLAB = createBlockWithItem("zombified_cut_pigsteel_slab", new ZombifiableSlabBlock(Oxidizable.OxidationLevel.OXIDIZED, registerZombifiableBLock(Oxidizable.OxidationLevel.OXIDIZED)), ItemGroup.BUILDING_BLOCKS);

    public static final Block WAXED_CUT_PIGSTEEL_SLAB = createBlockWithItem("waxed_cut_pigsteel_slab", new SlabBlock(registerZombifiableBLock(Oxidizable.OxidationLevel.OXIDIZED)), ItemGroup.BUILDING_BLOCKS);
    public static final Block WAXED_INFECTED_CUT_PIGSTEEL_SLAB = createBlockWithItem("waxed_infected_cut_pigsteel_slab", new SlabBlock(registerZombifiableBLock(Oxidizable.OxidationLevel.WEATHERED)), ItemGroup.BUILDING_BLOCKS);
    public static final Block WAXED_CORRUPTED_CUT_PIGSTEEL_SLAB = createBlockWithItem("waxed_corrupted_cut_pigsteel_slab", new SlabBlock(registerZombifiableBLock(Oxidizable.OxidationLevel.EXPOSED)), ItemGroup.BUILDING_BLOCKS);
    public static final Block WAXED_ZOMBIFIED_CUT_PIGSTEEL_SLAB = createBlockWithItem("waxed_zombified_cut_pigsteel_slab", new SlabBlock(registerZombifiableBLock(Oxidizable.OxidationLevel.UNAFFECTED)), ItemGroup.BUILDING_BLOCKS);

    public static final Block PIGSTEEL_LANTERN = createBlockWithItem("pigsteel_lantern", new PigsteelLanternBlock(FabricBlockSettings.copy(Blocks.LANTERN).luminance(state -> 15).nonOpaque().mapColor(MapColor.PURPLE)), ItemGroup.DECORATIONS);
    public static final Block PIGSTEEL_SOUL_LANTERN = createBlockWithItem("pigsteel_soul_lantern", new PigsteelLanternBlock(FabricBlockSettings.copy(Blocks.LANTERN).luminance(state -> 10).nonOpaque().mapColor(MapColor.PURPLE)), ItemGroup.DECORATIONS);


    public static void init(){

        if (FabricLoader.getInstance().isModLoaded("byg")){
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
