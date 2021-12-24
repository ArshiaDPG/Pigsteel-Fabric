package net.digitalpear.pigsteel;


import net.digitalpear.pigsteel.blocks.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@SuppressWarnings({"unused"})
public class PigsteelMod implements ModInitializer {
	public static final String MOD_ID = "pigsteel";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	//Ores
	public static final Block PIGSTEEL_ORE = new PigsteelOre(FabricBlockSettings.copy(Blocks.NETHER_GOLD_ORE));
	public static final Block STONE_PIGSTEEL_ORE = new PigsteelOre(FabricBlockSettings.copy(Blocks.IRON_ORE));
	public static final Block DEEPSLATE_PIGSTEEL_ORE = new PigsteelOre(FabricBlockSettings.copy(Blocks.DEEPSLATE_IRON_ORE));

	//Pigsteel Block
	public static final Block PIGSTEEL_BLOCK = new PigsteelBlock(FabricBlockSettings.copy(Blocks.IRON_BLOCK));
	public static final Block WAXED_PIGSTEEL_BLOCK = new Block(FabricBlockSettings.copy(Blocks.IRON_BLOCK));

	//Cut Blocks
	public static final Block ZOMBIFIED_CUT_PIGSTEEL = new Block(FabricBlockSettings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.NETHERITE));
	public static final Block CORRUPTED_CUT_PIGSTEEL = new CutPigsteelBlocks(ZOMBIFIED_CUT_PIGSTEEL.getDefaultState(), FabricBlockSettings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.NETHERITE));
	public static final Block INFECTED_CUT_PIGSTEEL = new CutPigsteelBlocks(CORRUPTED_CUT_PIGSTEEL.getDefaultState(), FabricBlockSettings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.NETHERITE));
	public static final Block CUT_PIGSTEEL = new CutPigsteelBlocks(INFECTED_CUT_PIGSTEEL.getDefaultState(), FabricBlockSettings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.NETHERITE));


	public static final Block WAXED_CUT_PIGSTEEL = new Block(FabricBlockSettings.copy(CUT_PIGSTEEL));
	public static final Block WAXED_INFECTED_CUT_PIGSTEEL = new Block(FabricBlockSettings.copy(CUT_PIGSTEEL));
	public static final Block WAXED_CORRUPTED_CUT_PIGSTEEL = new Block(FabricBlockSettings.copy(CUT_PIGSTEEL));
	public static final Block WAXED_ZOMBIFIED_CUT_PIGSTEEL = new Block(FabricBlockSettings.copy(CUT_PIGSTEEL));


	public static final Block ZOMBIFIED_CUT_PIGSTEEL_STAIRS = new PigsteelStairs(ZOMBIFIED_CUT_PIGSTEEL.getDefaultState(),
			FabricBlockSettings.copy(CUT_PIGSTEEL));
	public static final Block CORRUPTED_CUT_PIGSTEEL_STAIRS = new CutPigsteelStairsBlock(ZOMBIFIED_CUT_PIGSTEEL_STAIRS.getDefaultState(),CORRUPTED_CUT_PIGSTEEL.getDefaultState(),
			FabricBlockSettings.copy(CUT_PIGSTEEL));
	public static final Block INFECTED_CUT_PIGSTEEL_STAIRS = new CutPigsteelStairsBlock(CORRUPTED_CUT_PIGSTEEL_STAIRS.getDefaultState(),INFECTED_CUT_PIGSTEEL.getDefaultState(),
			FabricBlockSettings.copy(CUT_PIGSTEEL));
	public static final Block CUT_PIGSTEEL_STAIRS = new CutPigsteelStairsBlock(INFECTED_CUT_PIGSTEEL_STAIRS.getDefaultState(),CUT_PIGSTEEL.getDefaultState(),
			FabricBlockSettings.copy(CUT_PIGSTEEL));


	public static final Block WAXED_CUT_PIGSTEEL_STAIRS = new PigsteelStairs(WAXED_CUT_PIGSTEEL.getDefaultState(),
			FabricBlockSettings.copy(CUT_PIGSTEEL));
	public static final Block WAXED_INFECTED_CUT_PIGSTEEL_STAIRS = new PigsteelStairs(WAXED_INFECTED_CUT_PIGSTEEL.getDefaultState(),
			FabricBlockSettings.copy(CUT_PIGSTEEL));
	public static final Block WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS = new PigsteelStairs(WAXED_CORRUPTED_CUT_PIGSTEEL.getDefaultState(),
			FabricBlockSettings.copy(CUT_PIGSTEEL));
	public static final Block WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS = new PigsteelStairs(WAXED_ZOMBIFIED_CUT_PIGSTEEL.getDefaultState(),
			FabricBlockSettings.copy(CUT_PIGSTEEL));

	public static final Block ZOMBIFIED_CUT_PIGSTEEL_SLAB = new SlabBlock(FabricBlockSettings.copy(CUT_PIGSTEEL));
	public static final Block CORRUPTED_CUT_PIGSTEEL_SLAB = new CutPigsteelSlabBlock(ZOMBIFIED_CUT_PIGSTEEL_SLAB.getDefaultState(), FabricBlockSettings.copy(CUT_PIGSTEEL));
	public static final Block INFECTED_CUT_PIGSTEEL_SLAB = new CutPigsteelSlabBlock(CORRUPTED_CUT_PIGSTEEL_SLAB.getDefaultState(), FabricBlockSettings.copy(CUT_PIGSTEEL));
	public static final Block CUT_PIGSTEEL_SLAB = new CutPigsteelSlabBlock(INFECTED_CUT_PIGSTEEL_SLAB.getDefaultState(), FabricBlockSettings.copy(CUT_PIGSTEEL));

	public static final Block WAXED_CUT_PIGSTEEL_SLAB = new SlabBlock(FabricBlockSettings.copy(CUT_PIGSTEEL));
	public static final Block WAXED_INFECTED_CUT_PIGSTEEL_SLAB = new SlabBlock(FabricBlockSettings.copy(CUT_PIGSTEEL));
	public static final Block WAXED_CORRUPTED_CUT_PIGSTEEL_SLAB = new SlabBlock(FabricBlockSettings.copy(CUT_PIGSTEEL));
	public static final Block WAXED_ZOMBIFIED_CUT_PIGSTEEL_SLAB = new SlabBlock(FabricBlockSettings.copy(CUT_PIGSTEEL));

	//Items
	public static final Item PIGSTEEL_INGOT = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
	public static final Item PIGSTEEL_NUGGET = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));


	private static ConfiguredFeature<?, ?> NETHER_PIGSTEEL_ORE_CONFIGURED_FEATURE = Feature.ORE
			.configure(new OreFeatureConfig(
					OreConfiguredFeatures.NETHERRACK,
					PIGSTEEL_ORE.getDefaultState(),
					7)); // vein size

	public static PlacedFeature NETHER_PIGSTEEL_ORE_PLACED_FEATURE = NETHER_PIGSTEEL_ORE_CONFIGURED_FEATURE.withPlacement(
			CountPlacementModifier.of(20), // number of veins per chunk
			SquarePlacementModifier.of(), // spreading horizontally
			HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.getTop())); // height

		@Override
		public void onInitialize() {

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


			//Items
			Registry.register(Registry.ITEM, new Identifier(MOD_ID, "pigsteel_ingot"), PIGSTEEL_INGOT);
			Registry.register(Registry.ITEM, new Identifier(MOD_ID, "pigsteel_nugget"), PIGSTEEL_NUGGET);


			Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
					new Identifier(MOD_ID, "pigsteel_ore_nether"), NETHER_PIGSTEEL_ORE_CONFIGURED_FEATURE);
			Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(MOD_ID, "pigsteel_ore_nether"),
					NETHER_PIGSTEEL_ORE_PLACED_FEATURE);
			BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES,
					RegistryKey.of(Registry.PLACED_FEATURE_KEY,
							new Identifier(MOD_ID, "pigsteel_ore_nether")));

			LOGGER.info("Let there be pigsteel!");
		}
}
