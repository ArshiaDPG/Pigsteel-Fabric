package net.digitalpear.pigsteel.registering;

import net.digitalpear.pigsteel.protectedclasses.ModdedStairs;
import net.digitalpear.pigsteel.specialblocks.*;
import net.digitalpear.pigsteel.specialslabs.CorruptedCutPigsteelSlab;
import net.digitalpear.pigsteel.specialslabs.CutPigsteelSlab;
import net.digitalpear.pigsteel.specialslabs.InfectedCutPigsteelSlab;
import net.digitalpear.pigsteel.specialslabs.ZombifiedCutPigsteelSlab;
import net.digitalpear.pigsteel.specialstairs.CorruptedCutPigsteelStairs;
import net.digitalpear.pigsteel.specialstairs.CutPigsteelStairs;
import net.digitalpear.pigsteel.specialstairs.InfectedCutPigsteelStairs;
import net.digitalpear.pigsteel.specialstairs.ZombifiedCutPigsteelStairs;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@SuppressWarnings("unused")
public final class PigsteelBlocks {


    private static final Map<Block, String> REGISTRY = new HashMap<>();

    //Ores
    public static final Block PIGSTEEL_ORE = register("pigsteel_ore",
            new PigsteelOre(copyOf(Blocks.NETHER_GOLD_ORE)
                    .mapColor(MapColor.PALE_PURPLE)));
    public static final Block STONE_PIGSTEEL_ORE = register("stone_pigsteel_ore",
            new PigsteelOre(copyOf(Blocks.IRON_ORE)
                    .mapColor(MapColor.PALE_PURPLE)));
    public static final Block DEEPSLATE_PIGSTEEL_ORE = register("deepslate_pigsteel_ore",
            new PigsteelOre(copyOf(Blocks.DEEPSLATE_IRON_ORE)
                    .mapColor(MapColor.PALE_PURPLE)));

    //Pigsteel Block
    public static final Block PIGSTEEL_BLOCK = register("pigsteel_block",
            new PigsteelBlock(copyOf(Blocks.IRON_BLOCK)
                            .mapColor(MapColor.DARK_GREEN)
                            .requiresTool()
                            .sounds(BlockSoundGroup.NETHERITE)));
    public static final Block WAXED_PIGSTEEL_BLOCK = register("waxed_pigsteel_block",
            new Block(copyOf(Blocks.IRON_BLOCK)
                    .mapColor(MapColor.DARK_GREEN)
                    .requiresTool()
                    .sounds(BlockSoundGroup.NETHERITE)));

    //Cut Pigsteel
    public static final Block ZOMBIFIED_CUT_PIGSTEEL = register("zombified_cut_pigsteel",
            new ZombifiedCutPigsteel(copyOf(Blocks.IRON_BLOCK)
                            .mapColor(MapColor.DARK_GREEN)
                            .requiresTool()
                            .sounds(BlockSoundGroup.NETHERITE)));
    public static final Block CORRUPTED_CUT_PIGSTEEL = register("corrupted_cut_pigsteel",
            new CorruptedCutPigsteel(copyOf(Blocks.IRON_BLOCK)
                            .mapColor(MapColor.DARK_GREEN)
                            .requiresTool()
                            .sounds(BlockSoundGroup.NETHERITE)));
    public static final Block INFECTED_CUT_PIGSTEEL = register("infected_cut_pigsteel",
            new InfectedCutPigsteel(copyOf(Blocks.IRON_BLOCK)
                            .mapColor(MapColor.DARK_GREEN)
                            .requiresTool()
                            .sounds(BlockSoundGroup.NETHERITE)));
    public static final Block CUT_PIGSTEEL = register("cut_pigsteel",
            new CutPigsteel(copyOf(Blocks.IRON_BLOCK)
                            .mapColor(MapColor.DARK_GREEN)
                            .requiresTool()
                            .sounds(BlockSoundGroup.NETHERITE)));

    public static final Block ZOMBIFIED_CUT_PIGSTEEL_STAIRS = register("zombified_cut_pigsteel_stairs",
            new ZombifiedCutPigsteelStairs(ZOMBIFIED_CUT_PIGSTEEL.getDefaultState(),
                    copyOf(Blocks.IRON_BLOCK)
                            .mapColor(MapColor.DARK_GREEN)
                            .requiresTool()
                            .sounds(BlockSoundGroup.NETHERITE)));
    public static final Block CORRUPTED_CUT_PIGSTEEL_STAIRS = register("corrupted_cut_pigsteel_stairs",
            new CorruptedCutPigsteelStairs(CORRUPTED_CUT_PIGSTEEL.getDefaultState(),
                    copyOf(Blocks.IRON_BLOCK)
                            .mapColor(MapColor.DARK_GREEN)
                            .requiresTool()
                            .sounds(BlockSoundGroup.NETHERITE)));
    public static final Block INFECTED_CUT_PIGSTEEL_STAIRS = register("infected_cut_pigsteel_stairs",
            new InfectedCutPigsteelStairs(INFECTED_CUT_PIGSTEEL.getDefaultState(),
                    copyOf(Blocks.IRON_BLOCK)
                            .mapColor(MapColor.DARK_GREEN)
                            .requiresTool()
                            .sounds(BlockSoundGroup.NETHERITE)));
    public static final Block CUT_PIGSTEEL_STAIRS = register("cut_pigsteel_stairs",
            new CutPigsteelStairs(CUT_PIGSTEEL.getDefaultState(),
                    copyOf(Blocks.IRON_BLOCK)
                            .mapColor(MapColor.DARK_GREEN)
                            .requiresTool()
                            .sounds(BlockSoundGroup.NETHERITE)));

    public static final Block ZOMBIFIED_CUT_PIGSTEEL_SLAB = register("zombified_cut_pigsteel_slab",
            new ZombifiedCutPigsteelSlab(copyOf(ZOMBIFIED_CUT_PIGSTEEL)));
    public static final Block CORRUPTED_CUT_PIGSTEEL_SLAB = register("corrupted_cut_pigsteel_slab",
            new CorruptedCutPigsteelSlab(copyOf(CORRUPTED_CUT_PIGSTEEL)));
    public static final Block INFECTED_CUT_PIGSTEEL_SLAB = register("infected_cut_pigsteel_slab",
            new InfectedCutPigsteelSlab(copyOf(INFECTED_CUT_PIGSTEEL)));
    public static final Block CUT_PIGSTEEL_SLAB = register("cut_pigsteel_slab",
            new CutPigsteelSlab(copyOf(CUT_PIGSTEEL)));

    public static final Block WAXED_ZOMBIFIED_CUT_PIGSTEEL = register("waxed_zombified_cut_pigsteel",
            new Block(copyOf(ZOMBIFIED_CUT_PIGSTEEL)));
    public static final Block WAXED_CORRUPTED_CUT_PIGSTEEL = register("waxed_corrupted_cut_pigsteel",
            new Block(copyOf(CORRUPTED_CUT_PIGSTEEL)));
    public static final Block WAXED_INFECTED_CUT_PIGSTEEL = register("waxed_infected_cut_pigsteel",
            new Block(copyOf(INFECTED_CUT_PIGSTEEL)));
    public static final Block WAXED_CUT_PIGSTEEL = register("waxed_cut_pigsteel",
            new Block(copyOf(CUT_PIGSTEEL)));

    public static final Block WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS = register("waxed_zombified_cut_pigsteel_stairs",
            new ModdedStairs(ZOMBIFIED_CUT_PIGSTEEL.getDefaultState(), copyOf(ZOMBIFIED_CUT_PIGSTEEL_STAIRS)));
    public static final Block WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS = register("waxed_corrupted_cut_pigsteel_stairs",
            new ModdedStairs(CORRUPTED_CUT_PIGSTEEL.getDefaultState(), copyOf(CORRUPTED_CUT_PIGSTEEL_STAIRS)));
    public static final Block WAXED_INFECTED_CUT_PIGSTEEL_STAIRS = register("waxed_infected_cut_pigsteel_stairs",
            new ModdedStairs(INFECTED_CUT_PIGSTEEL.getDefaultState(), copyOf(INFECTED_CUT_PIGSTEEL_STAIRS)));
    public static final Block WAXED_CUT_PIGSTEEL_STAIRS = register("waxed_cut_pigsteel_stairs",
            new ModdedStairs(CUT_PIGSTEEL.getDefaultState(), copyOf(CUT_PIGSTEEL_STAIRS)));

    public static final Block WAXED_ZOMBIFIED_CUT_PIGSTEEL_SLAB = register("waxed_zombified_cut_pigsteel_slab",
            new SlabBlock(copyOf(ZOMBIFIED_CUT_PIGSTEEL_SLAB)));
    public static final Block WAXED_CORRUPTED_CUT_PIGSTEEL_SLAB = register("waxed_corrupted_cut_pigsteel_slab",
            new SlabBlock(copyOf(CORRUPTED_CUT_PIGSTEEL_SLAB)));
    public static final Block WAXED_INFECTED_CUT_PIGSTEEL_SLAB = register("waxed_infected_cut_pigsteel_slab",
            new SlabBlock(copyOf(INFECTED_CUT_PIGSTEEL_SLAB)));
    public static final Block WAXED_CUT_PIGSTEEL_SLAB = register("waxed_cut_pigsteel_slab",
            new SlabBlock(copyOf(CUT_PIGSTEEL_SLAB)));

    private static <B extends Block> B register(String id, B block, Function<Item.Settings, Item.Settings> modifier) {
        Item.Settings defaultSettings = PigsteelItems.BUILDING_BLOCKS.get();
        PigsteelItems.register(id, new BlockItem(block, modifier.apply(defaultSettings)));
        return registerNoItem(id, block);
    }

    private static <B extends Block> B register(String id, B block) {
        return register(id, block, Function.identity());
    }

    private static <B extends Block> B registerNoItem(String id, B block) {
        REGISTRY.putIfAbsent(block, id);
        return block;
    }
    private static Boolean never(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return false;
    }

    private static AbstractBlock.Settings copyOf(AbstractBlock block) {
        return AbstractBlock.Settings.copy(block);
    }

    public static void init() {
        REGISTRY.forEach((block, id) ->
                Registry.register(Registry.BLOCK, Id.mod(id), block)
        );
    }


    static Identifier mc(String path) {
        return new Identifier(path);
    }
}
