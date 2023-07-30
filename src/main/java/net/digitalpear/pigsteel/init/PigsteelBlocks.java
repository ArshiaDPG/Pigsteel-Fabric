package net.digitalpear.pigsteel.init;

import net.digitalpear.pigsteel.Pigsteel;
import net.digitalpear.pigsteel.common.blocks.*;
import net.digitalpear.pigsteel.init.data.ZombifiableBlockRegistry;
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

    public static Block createBlockWithItem(String blockID, Block block) {
        createBlockItem(blockID, block);
        return Registry.register(Registries.BLOCK, new Identifier(Pigsteel.MOD_ID, blockID), block);
    }

    public static Block createPaneBlock(Zombifiable.ZombificationLevel oxidationLevel){
        return new ZombifiablePaneBlock(oxidationLevel, AbstractBlock.Settings.copy(Blocks.IRON_BARS).sounds(BlockSoundGroup.NETHERITE).nonOpaque().ticksRandomly());
    }
    public static Block createWaxedPaneBlock(Zombifiable.ZombificationLevel oxidationLevel){
        return new PaneBlock(AbstractBlock.Settings.copy(Blocks.IRON_BARS).sounds(BlockSoundGroup.NETHERITE).nonOpaque());
    }

    public static final Block PORKSLAG = createBlockWithItem("porkslag", new Block(AbstractBlock.Settings.create()
            .sounds(BlockSoundGroup.POLISHED_DEEPSLATE)
            .strength(1.5f)
            .mapColor(MapColor.BLACK)
            .requiresTool()));


    public static final Block RAW_PIGSTEEL_BLOCK = createBlockWithItem("raw_pigsteel_block", new Block(AbstractBlock.Settings.copy(Blocks.RAW_IRON_BLOCK)));

    public static Block pigsteelBlockSettings(Zombifiable.ZombificationLevel level){
        return new ZombifiableBlock(level, AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.NETHERITE).ticksRandomly());
    }

    public static final Block PIGSTEEL_BLOCK = createBlockWithItem("pigsteel_block", pigsteelBlockSettings(Zombifiable.ZombificationLevel.UNAFFECTED));
    public static final Block INFECTED_PIGSTEEL = createBlockWithItem("infected_pigsteel", pigsteelBlockSettings(Zombifiable.ZombificationLevel.INFECTED));
    public static final Block CORRUPTED_PIGSTEEL = createBlockWithItem("corrupted_pigsteel", pigsteelBlockSettings(Zombifiable.ZombificationLevel.CORRUPTED));
    public static final Block ZOMBIFIED_PIGSTEEL = createBlockWithItem("zombified_pigsteel", pigsteelBlockSettings(Zombifiable.ZombificationLevel.ZOMBIFIED));

    public static final Block WAXED_PIGSTEEL_BLOCK = createBlockWithItem("waxed_pigsteel_block", new Block(AbstractBlock.Settings.copy(PIGSTEEL_BLOCK)));
    public static final Block WAXED_INFECTED_PIGSTEEL = createBlockWithItem("waxed_infected_pigsteel", new Block(AbstractBlock.Settings.copy(INFECTED_PIGSTEEL)));
    public static final Block WAXED_CORRUPTED_PIGSTEEL = createBlockWithItem("waxed_corrupted_pigsteel", new Block(AbstractBlock.Settings.copy(CORRUPTED_PIGSTEEL)));
    public static final Block WAXED_ZOMBIFIED_PIGSTEEL = createBlockWithItem("waxed_zombified_pigsteel", new Block(AbstractBlock.Settings.copy(ZOMBIFIED_PIGSTEEL)));

//    public static final ZombifiableBlockRegistry cutPigsteel = new ZombifiableBlockRegistry("cut_pigsteel", ZombifiableBlock.class, Block.class);


    public static final Block CUT_PIGSTEEL = createBlockWithItem("cut_pigsteel", pigsteelBlockSettings(Zombifiable.ZombificationLevel.UNAFFECTED));
    public static final Block INFECTED_CUT_PIGSTEEL = createBlockWithItem("infected_cut_pigsteel", pigsteelBlockSettings(Zombifiable.ZombificationLevel.INFECTED));
    public static final Block CORRUPTED_CUT_PIGSTEEL = createBlockWithItem("corrupted_cut_pigsteel", pigsteelBlockSettings(Zombifiable.ZombificationLevel.CORRUPTED));
    public static final Block ZOMBIFIED_CUT_PIGSTEEL = createBlockWithItem("zombified_cut_pigsteel", pigsteelBlockSettings(Zombifiable.ZombificationLevel.ZOMBIFIED));

    public static final Block WAXED_CUT_PIGSTEEL = createBlockWithItem("waxed_cut_pigsteel", new Block(AbstractBlock.Settings.copy(CUT_PIGSTEEL)));
    public static final Block WAXED_INFECTED_CUT_PIGSTEEL = createBlockWithItem("waxed_infected_cut_pigsteel", new Block(AbstractBlock.Settings.copy(INFECTED_CUT_PIGSTEEL)));
    public static final Block WAXED_CORRUPTED_CUT_PIGSTEEL = createBlockWithItem("waxed_corrupted_cut_pigsteel", new Block(AbstractBlock.Settings.copy(CORRUPTED_CUT_PIGSTEEL)));
    public static final Block WAXED_ZOMBIFIED_CUT_PIGSTEEL = createBlockWithItem("waxed_zombified_cut_pigsteel", new Block(AbstractBlock.Settings.copy(ZOMBIFIED_CUT_PIGSTEEL)));

    public static final Block CUT_PIGSTEEL_STAIRS = createBlockWithItem("cut_pigsteel_stairs", new ZombifiableStairsBlock(CUT_PIGSTEEL.getDefaultState(), AbstractBlock.Settings.copy(CUT_PIGSTEEL)));
    public static final Block INFECTED_CUT_PIGSTEEL_STAIRS = createBlockWithItem("infected_cut_pigsteel_stairs", new ZombifiableStairsBlock(Zombifiable.ZombificationLevel.INFECTED, INFECTED_CUT_PIGSTEEL.getDefaultState(), AbstractBlock.Settings.copy(INFECTED_CUT_PIGSTEEL)));
    public static final Block CORRUPTED_CUT_PIGSTEEL_STAIRS = createBlockWithItem("corrupted_cut_pigsteel_stairs", new ZombifiableStairsBlock(Zombifiable.ZombificationLevel.CORRUPTED, CORRUPTED_CUT_PIGSTEEL.getDefaultState(), AbstractBlock.Settings.copy(CORRUPTED_CUT_PIGSTEEL)));
    public static final Block ZOMBIFIED_CUT_PIGSTEEL_STAIRS = createBlockWithItem("zombified_cut_pigsteel_stairs", new ZombifiableStairsBlock(Zombifiable.ZombificationLevel.ZOMBIFIED, ZOMBIFIED_CUT_PIGSTEEL.getDefaultState(), AbstractBlock.Settings.copy(ZOMBIFIED_CUT_PIGSTEEL)));

    public static final Block WAXED_CUT_PIGSTEEL_STAIRS = createBlockWithItem("waxed_cut_pigsteel_stairs", new StairsBlock(WAXED_CUT_PIGSTEEL.getDefaultState(), AbstractBlock.Settings.copy(WAXED_CUT_PIGSTEEL)));
    public static final Block WAXED_INFECTED_CUT_PIGSTEEL_STAIRS = createBlockWithItem("waxed_infected_cut_pigsteel_stairs", new StairsBlock(WAXED_CUT_PIGSTEEL.getDefaultState(), AbstractBlock.Settings.copy(WAXED_CUT_PIGSTEEL)));
    public static final Block WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS = createBlockWithItem("waxed_corrupted_cut_pigsteel_stairs", new StairsBlock(WAXED_CUT_PIGSTEEL.getDefaultState(), AbstractBlock.Settings.copy(WAXED_CUT_PIGSTEEL)));
    public static final Block WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS = createBlockWithItem("waxed_zombified_cut_pigsteel_stairs", new StairsBlock(WAXED_CUT_PIGSTEEL.getDefaultState(), AbstractBlock.Settings.copy(WAXED_CUT_PIGSTEEL)));

    public static final Block CUT_PIGSTEEL_SLAB = createBlockWithItem("cut_pigsteel_slab", new ZombifiableSlabBlock(AbstractBlock.Settings.copy(CUT_PIGSTEEL)));
    public static final Block INFECTED_CUT_PIGSTEEL_SLAB = createBlockWithItem("infected_cut_pigsteel_slab", new ZombifiableSlabBlock(Zombifiable.ZombificationLevel.INFECTED, AbstractBlock.Settings.copy(INFECTED_PIGSTEEL)));
    public static final Block CORRUPTED_CUT_PIGSTEEL_SLAB = createBlockWithItem("corrupted_cut_pigsteel_slab", new ZombifiableSlabBlock(Zombifiable.ZombificationLevel.CORRUPTED, AbstractBlock.Settings.copy(CORRUPTED_PIGSTEEL)));
    public static final Block ZOMBIFIED_CUT_PIGSTEEL_SLAB = createBlockWithItem("zombified_cut_pigsteel_slab", new ZombifiableSlabBlock(Zombifiable.ZombificationLevel.ZOMBIFIED, AbstractBlock.Settings.copy(ZOMBIFIED_PIGSTEEL)));

    public static final Block WAXED_CUT_PIGSTEEL_SLAB = createBlockWithItem("waxed_cut_pigsteel_slab", new SlabBlock(AbstractBlock.Settings.copy(WAXED_CUT_PIGSTEEL)));
    public static final Block WAXED_INFECTED_CUT_PIGSTEEL_SLAB = createBlockWithItem("waxed_infected_cut_pigsteel_slab", new SlabBlock(AbstractBlock.Settings.copy(WAXED_INFECTED_CUT_PIGSTEEL)));
    public static final Block WAXED_CORRUPTED_CUT_PIGSTEEL_SLAB = createBlockWithItem("waxed_corrupted_cut_pigsteel_slab", new SlabBlock(AbstractBlock.Settings.copy(WAXED_CORRUPTED_CUT_PIGSTEEL)));
    public static final Block WAXED_ZOMBIFIED_CUT_PIGSTEEL_SLAB = createBlockWithItem("waxed_zombified_cut_pigsteel_slab", new SlabBlock(AbstractBlock.Settings.copy(WAXED_ZOMBIFIED_CUT_PIGSTEEL)));

    public static final Block PIGSTEEL_LANTERN = createBlockWithItem("pigsteel_lantern", new PigsteelLanternBlock(FabricBlockSettings.copy(Blocks.LANTERN).luminance(state -> 15).nonOpaque().mapColor(MapColor.PURPLE)));
    public static final Block PIGSTEEL_SOUL_LANTERN = createBlockWithItem("pigsteel_soul_lantern", new PigsteelLanternBlock(FabricBlockSettings.copy(Blocks.LANTERN).luminance(state -> 10).nonOpaque().mapColor(MapColor.PURPLE)));

    public static final ZombifiableBlockRegistry pigsteelBars = new ZombifiableBlockRegistry("pigsteel_bars", ZombifiablePaneBlock.class, PaneBlock.class);

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


        mapWaxingAndAxing(pigsteelBars);

        PIGSTEEL_WAXING_MAP.forEach(OxidizableBlocksRegistry::registerWaxableBlockPair);
        PIGSTEEL_ZOMBIFYING_MAP.forEach(OxidizableBlocksRegistry::registerOxidizableBlockPair);
    }

    private static void mapWaxingAndAxing(ZombifiableBlockRegistry zombifiableBlockRegistry){
        for (int i = 0; i < 4; i++){
            PIGSTEEL_WAXING_MAP.put(zombifiableBlockRegistry.getZombifiables().get(i), zombifiableBlockRegistry.getWaxed().get(i));
        }
        PIGSTEEL_ZOMBIFYING_MAP.put(zombifiableBlockRegistry.getUnaffectedBlock(), zombifiableBlockRegistry.getInfectedBlock());
        PIGSTEEL_ZOMBIFYING_MAP.put(zombifiableBlockRegistry.getInfectedBlock(), zombifiableBlockRegistry.getCorruptedBlock());
        PIGSTEEL_ZOMBIFYING_MAP.put(zombifiableBlockRegistry.getCorruptedBlock(), zombifiableBlockRegistry.getZombifiedBlock());
    }
}