package net.digitalpear.pigsteel.init;

import net.digitalpear.pigsteel.Pigsteel;
import net.digitalpear.pigsteel.common.blocks.*;
import net.digitalpear.pigsteel.init.data.ZombifiableBlockRegistry;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;

import java.util.HashMap;
import java.util.Map;


@SuppressWarnings("unused")
public class PigsteelBlocks {
    public static Map<Block, Block> PIGSTEEL_WAXING_MAP = new HashMap<>();
    public static Map<Block, Block> PIGSTEEL_ZOMBIFYING_MAP = new HashMap<>();

    public static BlockItem createBlockItem(String blockID, Block block) {
        return Registry.register(Registries.ITEM,Pigsteel.getModId(blockID), new BlockItem(block, new Item.Settings().fireproof()));
    }
    public static Block createBlockWithItem(String blockID, Block block) {
        createBlockItem(blockID, block);
        return Registry.register(Registries.BLOCK, Pigsteel.getModId(blockID), block);
    }
    public static final Block PORKSLAG = createBlockWithItem("porkslag", new ExperienceDroppingBlock(AbstractBlock.Settings.create()
            .sounds(BlockSoundGroup.POLISHED_DEEPSLATE)
            .strength(2.5f)
            .mapColor(MapColor.PALE_PURPLE)
            .requiresTool()));


    public static final Block PIGSTEEL_CHUNK_BLOCK = createBlockWithItem("pigsteel_chunk_block",
            new Block(AbstractBlock.Settings.copy(Blocks.RAW_IRON_BLOCK).mapColor(MapColor.PALE_PURPLE)));

    public static Block pigsteelBlockSettings(Zombifiable.ZombificationLevel level){
        return new ZombifiableBlock(level, AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.NETHERITE).ticksRandomly());
    }
    public static AbstractBlock.Settings lanternsSettings(int lightValue){
        return AbstractBlock.Settings.create().solid().requiresTool().strength(3.5F).sounds(BlockSoundGroup.LANTERN).nonOpaque().pistonBehavior(PistonBehavior.DESTROY).luminance(state -> lightValue);
    }


    public static final ZombifiableBlockRegistry refinedPigsteel = new ZombifiableBlockRegistry("refined_pigsteel", settingsLevelPair -> new ZombifiableBlock(settingsLevelPair.getRight(), settingsLevelPair.getLeft()), settingsLevelPair -> new Block(settingsLevelPair.getLeft()));
    public static final ZombifiableBlockRegistry cutPigsteel = new ZombifiableBlockRegistry("cut_pigsteel", settingsLevelPair -> new ZombifiableBlock(settingsLevelPair.getRight(), settingsLevelPair.getLeft()), settingsZombificationLevelPair -> new Block(settingsZombificationLevelPair.getLeft()));
    public static final ZombifiableBlockRegistry cutPigsteelStairs = new ZombifiableBlockRegistry("cut_pigsteel_stairs", settingsLevelPair -> new ZombifiableStairsBlock(settingsLevelPair.getRight(), cutPigsteel.getZombifiables().get(settingsLevelPair.getRight().ordinal()).getDefaultState(), settingsLevelPair.getLeft()), settingsLevelPair -> new StairsBlock(cutPigsteel.getWaxed().get(settingsLevelPair.getRight().ordinal()).getDefaultState(),settingsLevelPair.getLeft()));
    public static final ZombifiableBlockRegistry cutPigsteelSlabs = new ZombifiableBlockRegistry("cut_pigsteel_slab", settingsLevelPair -> new ZombifiableSlabBlock(settingsLevelPair.getRight(), settingsLevelPair.getLeft()), settingsZombificationLevelPair -> new SlabBlock(settingsZombificationLevelPair.getLeft()));
    public static final ZombifiableBlockRegistry pigsteelLanterns = new ZombifiableBlockRegistry("pigsteel_lantern", settingsLevelPair -> new ZombifiableLanternBlock(settingsLevelPair.getRight(), settingsLevelPair.getLeft()), settingsZombificationLevelPair -> new PigsteelLanternBlock(settingsZombificationLevelPair.getLeft()), lanternsSettings(15));
    public static final ZombifiableBlockRegistry pigsteelSoulLanterns = new ZombifiableBlockRegistry("pigsteel_soul_lantern", settingsLevelPair -> new ZombifiableLanternBlock(settingsLevelPair.getRight(), settingsLevelPair.getLeft()), settingsZombificationLevelPair -> new PigsteelLanternBlock(settingsZombificationLevelPair.getLeft()), lanternsSettings(10));

    public static void init(){
        PIGSTEEL_WAXING_MAP.forEach(OxidizableBlocksRegistry::registerWaxableBlockPair);
        PIGSTEEL_ZOMBIFYING_MAP.forEach(OxidizableBlocksRegistry::registerOxidizableBlockPair);

        Pigsteel.LOGGER.info(PIGSTEEL_WAXING_MAP);
        Pigsteel.LOGGER.info(PIGSTEEL_ZOMBIFYING_MAP);

        ResourceManagerHelper.registerBuiltinResourcePack(Pigsteel.getModId("pigsteel_ore"),
                FabricLoader.getInstance().getModContainer(Pigsteel.MOD_ID).orElseThrow(), ResourcePackActivationType.NORMAL);
    }
}