package net.digitalpear.pigsteel.register;

import net.digitalpear.pigsteel.PigsteelMod;
import net.digitalpear.pigsteel.common.blocks.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;

public class PigsteelBlocks {
    public static final String MOD_ID = PigsteelMod.MOD_ID;
    public static final Integer XP_MIN = 1;
    public static final Integer XP_MAX = 2;

    public static final AbstractBlock.Settings createCutBlock = FabricBlockSettings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.NETHERITE).requiresTool();

    public static Block createOreBlock(Block copy_block){
        return new OreBlock(FabricBlockSettings.copy(copy_block), UniformIntProvider.create(XP_MIN, XP_MAX));
    }

    //Ores
    public static final Block PIGSTEEL_ORE = createOreBlock(Blocks.NETHER_GOLD_ORE);
    public static final Block STONE_PIGSTEEL_ORE = createOreBlock(Blocks.IRON_ORE);
    public static final Block DEEPSLATE_PIGSTEEL_ORE = createOreBlock(Blocks.DEEPSLATE_IRON_ORE);

    //Pigsteel Block
    public static final Block PIGSTEEL_BLOCK = new PigsteelBlock(FabricBlockSettings.copy(Blocks.IRON_BLOCK));
    public static final Block WAXED_PIGSTEEL_BLOCK = new Block(FabricBlockSettings.copy(PIGSTEEL_BLOCK));

    //Cut Blocks
    public static final Block ZOMBIFIED_CUT_PIGSTEEL = new Block(createCutBlock);
    public static final Block CORRUPTED_CUT_PIGSTEEL = new CutPigsteelBlock(ZOMBIFIED_CUT_PIGSTEEL.getDefaultState(), createCutBlock);
    public static final Block INFECTED_CUT_PIGSTEEL = new CutPigsteelBlock(CORRUPTED_CUT_PIGSTEEL.getDefaultState(), createCutBlock);
    public static final Block CUT_PIGSTEEL = new CutPigsteelBlock(INFECTED_CUT_PIGSTEEL.getDefaultState(), createCutBlock);


    public static final Block WAXED_CUT_PIGSTEEL = new Block(FabricBlockSettings.copy(CUT_PIGSTEEL));
    public static final Block WAXED_INFECTED_CUT_PIGSTEEL = new Block(FabricBlockSettings.copy(INFECTED_CUT_PIGSTEEL));
    public static final Block WAXED_CORRUPTED_CUT_PIGSTEEL = new Block(FabricBlockSettings.copy(CORRUPTED_CUT_PIGSTEEL));
    public static final Block WAXED_ZOMBIFIED_CUT_PIGSTEEL = new Block(FabricBlockSettings.copy(ZOMBIFIED_CUT_PIGSTEEL));


    public static final Block ZOMBIFIED_CUT_PIGSTEEL_STAIRS = new PigsteelStairsBlock(ZOMBIFIED_CUT_PIGSTEEL.getDefaultState(),
            FabricBlockSettings.copy(ZOMBIFIED_CUT_PIGSTEEL));
    public static final Block CORRUPTED_CUT_PIGSTEEL_STAIRS = new CutPigsteelStairsBlock(ZOMBIFIED_CUT_PIGSTEEL_STAIRS.getDefaultState(), CORRUPTED_CUT_PIGSTEEL.getDefaultState(),
            FabricBlockSettings.copy(CORRUPTED_CUT_PIGSTEEL));
    public static final Block INFECTED_CUT_PIGSTEEL_STAIRS = new CutPigsteelStairsBlock(CORRUPTED_CUT_PIGSTEEL_STAIRS.getDefaultState(), INFECTED_CUT_PIGSTEEL.getDefaultState(),
            FabricBlockSettings.copy(INFECTED_CUT_PIGSTEEL));
    public static final Block CUT_PIGSTEEL_STAIRS = new CutPigsteelStairsBlock(INFECTED_CUT_PIGSTEEL_STAIRS.getDefaultState(), CUT_PIGSTEEL.getDefaultState(),
            FabricBlockSettings.copy(CUT_PIGSTEEL));


    public static final Block WAXED_CUT_PIGSTEEL_STAIRS = new PigsteelStairsBlock(WAXED_CUT_PIGSTEEL.getDefaultState(),
            FabricBlockSettings.copy(CUT_PIGSTEEL_STAIRS));
    public static final Block WAXED_INFECTED_CUT_PIGSTEEL_STAIRS = new PigsteelStairsBlock(WAXED_INFECTED_CUT_PIGSTEEL.getDefaultState(),
            FabricBlockSettings.copy(INFECTED_CUT_PIGSTEEL_STAIRS));
    public static final Block WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS = new PigsteelStairsBlock(WAXED_CORRUPTED_CUT_PIGSTEEL.getDefaultState(),
            FabricBlockSettings.copy(CORRUPTED_CUT_PIGSTEEL_STAIRS));
    public static final Block WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS = new PigsteelStairsBlock(WAXED_ZOMBIFIED_CUT_PIGSTEEL.getDefaultState(),
            FabricBlockSettings.copy(ZOMBIFIED_CUT_PIGSTEEL_STAIRS));

    public static final Block ZOMBIFIED_CUT_PIGSTEEL_SLAB = new SlabBlock(FabricBlockSettings.copy(ZOMBIFIED_CUT_PIGSTEEL));
    public static final Block CORRUPTED_CUT_PIGSTEEL_SLAB = new CutPigsteelSlabBlock(ZOMBIFIED_CUT_PIGSTEEL_SLAB.getDefaultState(), FabricBlockSettings.copy(CORRUPTED_CUT_PIGSTEEL));
    public static final Block INFECTED_CUT_PIGSTEEL_SLAB = new CutPigsteelSlabBlock(CORRUPTED_CUT_PIGSTEEL_SLAB.getDefaultState(), FabricBlockSettings.copy(INFECTED_CUT_PIGSTEEL));
    public static final Block CUT_PIGSTEEL_SLAB = new CutPigsteelSlabBlock(INFECTED_CUT_PIGSTEEL_SLAB.getDefaultState(), FabricBlockSettings.copy(CUT_PIGSTEEL));

    public static final Block WAXED_CUT_PIGSTEEL_SLAB = new SlabBlock(FabricBlockSettings.copy(CUT_PIGSTEEL_SLAB));
    public static final Block WAXED_INFECTED_CUT_PIGSTEEL_SLAB = new SlabBlock(FabricBlockSettings.copy(INFECTED_CUT_PIGSTEEL_SLAB));
    public static final Block WAXED_CORRUPTED_CUT_PIGSTEEL_SLAB = new SlabBlock(FabricBlockSettings.copy(CORRUPTED_CUT_PIGSTEEL_SLAB));
    public static final Block WAXED_ZOMBIFIED_CUT_PIGSTEEL_SLAB = new SlabBlock(FabricBlockSettings.copy(ZOMBIFIED_CUT_PIGSTEEL_SLAB));



    public static void init(){
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "pigsteel_ore"), PIGSTEEL_ORE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "pigsteel_ore"),
                new BlockItem(PIGSTEEL_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "stone_pigsteel_ore"), STONE_PIGSTEEL_ORE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "stone_pigsteel_ore"),
                new BlockItem(STONE_PIGSTEEL_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "deepslate_pigsteel_ore"), DEEPSLATE_PIGSTEEL_ORE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "deepslate_pigsteel_ore"),
                new BlockItem(DEEPSLATE_PIGSTEEL_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));


        //Pigsteel Blocks
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "pigsteel_block"), PIGSTEEL_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "pigsteel_block"),
                new BlockItem(PIGSTEEL_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "waxed_pigsteel_block"), WAXED_PIGSTEEL_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "waxed_pigsteel_block"),
                new BlockItem(WAXED_PIGSTEEL_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));


        //Cut Pigsteel Blocks
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "cut_pigsteel"), CUT_PIGSTEEL);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "cut_pigsteel"),
                new BlockItem(CUT_PIGSTEEL, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "infected_cut_pigsteel"), INFECTED_CUT_PIGSTEEL);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "infected_cut_pigsteel"),
                new BlockItem(INFECTED_CUT_PIGSTEEL, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "corrupted_cut_pigsteel"), CORRUPTED_CUT_PIGSTEEL);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "corrupted_cut_pigsteel"),
                new BlockItem(CORRUPTED_CUT_PIGSTEEL, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "zombified_cut_pigsteel"), ZOMBIFIED_CUT_PIGSTEEL);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "zombified_cut_pigsteel"),
                new BlockItem(ZOMBIFIED_CUT_PIGSTEEL, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));


        //Waxed Cut Pigsteel Blocks
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "waxed_cut_pigsteel"), WAXED_CUT_PIGSTEEL);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "waxed_cut_pigsteel"),
                new BlockItem(WAXED_CUT_PIGSTEEL, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "waxed_infected_cut_pigsteel"), WAXED_INFECTED_CUT_PIGSTEEL);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "waxed_infected_cut_pigsteel"),
                new BlockItem(WAXED_INFECTED_CUT_PIGSTEEL, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "waxed_corrupted_cut_pigsteel"), WAXED_CORRUPTED_CUT_PIGSTEEL);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "waxed_corrupted_cut_pigsteel"),
                new BlockItem(WAXED_CORRUPTED_CUT_PIGSTEEL, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "waxed_zombified_cut_pigsteel"), WAXED_ZOMBIFIED_CUT_PIGSTEEL);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "waxed_zombified_cut_pigsteel"),
                new BlockItem(WAXED_ZOMBIFIED_CUT_PIGSTEEL, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));


        //Cut Pigsteel Stairs
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "cut_pigsteel_stairs"), CUT_PIGSTEEL_STAIRS);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "cut_pigsteel_stairs"),
                new BlockItem(CUT_PIGSTEEL_STAIRS, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "infected_cut_pigsteel_stairs"), INFECTED_CUT_PIGSTEEL_STAIRS);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "infected_cut_pigsteel_stairs"),
                new BlockItem(INFECTED_CUT_PIGSTEEL_STAIRS, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "corrupted_cut_pigsteel_stairs"), CORRUPTED_CUT_PIGSTEEL_STAIRS);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "corrupted_cut_pigsteel_stairs"),
                new BlockItem(CORRUPTED_CUT_PIGSTEEL_STAIRS, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "zombified_cut_pigsteel_stairs"), ZOMBIFIED_CUT_PIGSTEEL_STAIRS);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "zombified_cut_pigsteel_stairs"),
                new BlockItem(ZOMBIFIED_CUT_PIGSTEEL_STAIRS, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));


        //Waxed Cut Pigsteel Stairs
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "waxed_cut_pigsteel_stairs"), WAXED_CUT_PIGSTEEL_STAIRS);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "waxed_cut_pigsteel_stairs"),
                new BlockItem(WAXED_CUT_PIGSTEEL_STAIRS, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "waxed_infected_cut_pigsteel_stairs"), WAXED_INFECTED_CUT_PIGSTEEL_STAIRS);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "waxed_infected_cut_pigsteel_stairs"),
                new BlockItem(WAXED_INFECTED_CUT_PIGSTEEL_STAIRS, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "waxed_corrupted_cut_pigsteel_stairs"), WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "waxed_corrupted_cut_pigsteel_stairs"),
                new BlockItem(WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "waxed_zombified_cut_pigsteel_stairs"), WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "waxed_zombified_cut_pigsteel_stairs"),
                new BlockItem(WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));


        //Cut Pigsteel SLabs
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "cut_pigsteel_slab"), CUT_PIGSTEEL_SLAB);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "cut_pigsteel_slab"),
                new BlockItem(CUT_PIGSTEEL_SLAB, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "infected_cut_pigsteel_slab"), INFECTED_CUT_PIGSTEEL_SLAB);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "infected_cut_pigsteel_slab"),
                new BlockItem(INFECTED_CUT_PIGSTEEL_SLAB, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "corrupted_cut_pigsteel_slab"), CORRUPTED_CUT_PIGSTEEL_SLAB);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "corrupted_cut_pigsteel_slab"),
                new BlockItem(CORRUPTED_CUT_PIGSTEEL_SLAB, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "zombified_cut_pigsteel_slab"), ZOMBIFIED_CUT_PIGSTEEL_SLAB);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "zombified_cut_pigsteel_slab"),
                new BlockItem(ZOMBIFIED_CUT_PIGSTEEL_SLAB, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));


        //Cut Pigsteel SLabs
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "waxed_cut_pigsteel_slab"), WAXED_CUT_PIGSTEEL_SLAB);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "waxed_cut_pigsteel_slab"),
                new BlockItem(WAXED_CUT_PIGSTEEL_SLAB, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "waxed_infected_cut_pigsteel_slab"), WAXED_INFECTED_CUT_PIGSTEEL_SLAB);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "waxed_infected_cut_pigsteel_slab"),
                new BlockItem(WAXED_INFECTED_CUT_PIGSTEEL_SLAB, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "waxed_corrupted_cut_pigsteel_slab"), WAXED_CORRUPTED_CUT_PIGSTEEL_SLAB);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "waxed_corrupted_cut_pigsteel_slab"),
                new BlockItem(WAXED_CORRUPTED_CUT_PIGSTEEL_SLAB, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "waxed_zombified_cut_pigsteel_slab"), WAXED_ZOMBIFIED_CUT_PIGSTEEL_SLAB);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "waxed_zombified_cut_pigsteel_slab"),
                new BlockItem(WAXED_ZOMBIFIED_CUT_PIGSTEEL_SLAB, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
    }
}
