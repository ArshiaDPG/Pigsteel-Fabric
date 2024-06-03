package net.digitalpear.pigsteel.init.worldgen.structure;

import net.digitalpear.pigsteel.Pigsteel;
import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.digitalpear.pigsteel.init.data.PigsteelArcheologyLootTables;
import net.digitalpear.pigsteel.init.tags.PigsteelBlockTags;
import net.minecraft.block.Blocks;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.processor.*;
import net.minecraft.structure.rule.AlwaysTruePosRuleTest;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.structure.rule.blockentity.AppendLootRuleBlockEntityModifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;

import java.util.ArrayList;
import java.util.List;

public class PigsteelProcessorLists {
    public static List<RegistryKey<StructureProcessorList>> structures = new ArrayList<>();
    public static RegistryKey<StructureProcessorList> of(String id) {
        RegistryKey<StructureProcessorList> poolRegistryKey = RegistryKey.of(RegistryKeys.PROCESSOR_LIST, Pigsteel.getModId(id));
        structures.add(poolRegistryKey);
        return poolRegistryKey;
    }

    public static final RegistryKey<StructureProcessorList> PIGSTEEL_MINE = of("pigsteel_mine");
    public static void bootstrap(Registerable<StructureProcessorList> processorListRegisterable) {

//        register(processorListRegisterable, PIGSTEEL_MINE, List.of(new RuleStructureProcessor(
//                List.of(
//                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.GRAVEL, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.NETHERRACK.getDefaultState()),
//                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.LEVER, 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.NETHERRACK.getDefaultState()),
//                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.LEVER, 0.1F), AlwaysTrueRuleTest.INSTANCE, PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK.getDefaultState()),
//                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.GRAVEL, 0.05F), AlwaysTrueRuleTest.INSTANCE, PigsteelBlocks.PORKSLAG.getDefaultState()),
//                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.NETHER_BRICKS, 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_NETHER_BRICKS.getDefaultState()),
//                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CRIMSON_PLANKS, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.GRAVEL.getDefaultState()),
//                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CRIMSON_PLANKS, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.NETHERRACK.getDefaultState()),
//                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.NETHER_BRICKS, 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.NETHERRACK.getDefaultState()),
//                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.RED_NETHER_BRICKS, 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.NETHERRACK.getDefaultState()),
//                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.RED_NETHER_BRICK_STAIRS, 0.4F), AlwaysTrueRuleTest.INSTANCE, Blocks.NETHERRACK.getDefaultState()),
//                        new StructureProcessorRule(new RandomBlockMatchRuleTest(PigsteelBlocks.pigsteelLanterns.getUnaffectedBlock(), 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.NETHERRACK.getDefaultState()),
//                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.RAIL, 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.NETHERRACK.getDefaultState()),
//                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.NETHERRACK, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.GRAVEL.getDefaultState()),
//                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.NETHERRACK, 0.01F), AlwaysTrueRuleTest.INSTANCE, PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK.getDefaultState()),
//                        new StructureProcessorRule(new RandomBlockMatchRuleTest(PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK, 0.4F), AlwaysTrueRuleTest.INSTANCE, Blocks.ANCIENT_DEBRIS.getDefaultState()),
//                        new StructureProcessorRule(new RandomBlockMatchRuleTest(PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK, 0.2F), AlwaysTrueRuleTest.INSTANCE, PigsteelBlocks.PORKSLAG.getDefaultState()),
//                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.NETHERRACK, 0.05F), AlwaysTrueRuleTest.INSTANCE, PigsteelBlocks.PORKSLAG.getDefaultState()))),
//                createTrailRuinsTowerTopProcessor(PigsteelArcheologyLootTables.PIGSTEEL_MINE_ARCHAEOLOGY, 64)));

        register(processorListRegisterable, PIGSTEEL_MINE, List.of(new RuleStructureProcessor(
                List.of(
                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.LEVER, 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),

                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.NETHER_BRICKS, 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.NETHER_BRICKS, 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_NETHER_BRICKS.getDefaultState()),
                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.NETHER_BRICKS, 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.NETHERRACK.getDefaultState()),

                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.RED_NETHER_BRICKS, 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.RED_NETHER_BRICKS, 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.NETHERRACK.getDefaultState()),
                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.RED_NETHER_BRICK_STAIRS, 0.4F), AlwaysTrueRuleTest.INSTANCE, Blocks.NETHERRACK.getDefaultState()),

                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CRIMSON_PLANKS, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.GRAVEL.getDefaultState()),
                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CRIMSON_PLANKS, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.NETHERRACK.getDefaultState()),
                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CRIMSON_PLANKS, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),


                        new StructureProcessorRule(new RandomBlockMatchRuleTest(PigsteelBlocks.pigsteelLanterns.getUnaffectedBlock(), 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.RAIL, 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),

                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.NETHERRACK, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.GRAVEL.getDefaultState()),
                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.NETHERRACK, 0.01F), AlwaysTrueRuleTest.INSTANCE, PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK.getDefaultState()),

                        new StructureProcessorRule(new RandomBlockMatchRuleTest(PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK, 0.4F), AlwaysTrueRuleTest.INSTANCE, Blocks.ANCIENT_DEBRIS.getDefaultState()),
                        new StructureProcessorRule(new RandomBlockMatchRuleTest(PigsteelBlocks.PIGSTEEL_CHUNK_BLOCK, 0.2F), AlwaysTrueRuleTest.INSTANCE, PigsteelBlocks.PORKSLAG.getDefaultState()),

                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.NETHERRACK, 0.05F), AlwaysTrueRuleTest.INSTANCE, PigsteelBlocks.PORKSLAG.getDefaultState()))),
                createTrailRuinsTowerTopProcessor(PigsteelArcheologyLootTables.PIGSTEEL_MINE_ARCHAEOLOGY, 64)));
    }
    private static void register(Registerable<StructureProcessorList> processorListRegisterable, RegistryKey<StructureProcessorList> key, List<StructureProcessor> processors) {
        processorListRegisterable.register(key, new StructureProcessorList(processors));
    }
    private static CappedStructureProcessor createTrailRuinsTowerTopProcessor(RegistryKey<LootTable> lootTable, int limit) {
        return new CappedStructureProcessor(new RuleStructureProcessor(
                List.of(new StructureProcessorRule(
                        new TagMatchRuleTest(PigsteelBlockTags.PIGSTEEL_MINE_REPLACEABLE),
                        AlwaysTrueRuleTest.INSTANCE,
                        AlwaysTruePosRuleTest.INSTANCE,
                        Blocks.SUSPICIOUS_GRAVEL.getDefaultState(),
                        new AppendLootRuleBlockEntityModifier(lootTable)))),
                ConstantIntProvider.create(limit));
    }
}
