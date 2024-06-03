package net.digitalpear.pigsteel.init.worldgen.structure;

import net.digitalpear.pigsteel.Pigsteel;
import net.digitalpear.pigsteel.init.tags.PigsteelBiomeTags;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.structure.StructureLiquidSettings;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Pool;
import net.minecraft.world.StructureSpawns;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.StructureTerrainAdaptation;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.heightprovider.ConstantHeightProvider;
import net.minecraft.world.gen.structure.JigsawStructure;
import net.minecraft.world.gen.structure.Structure;

import java.util.*;
import java.util.stream.Collectors;

public class PigsteelStructures {

    public static List<RegistryKey<Structure>> structures = new ArrayList<>();
    public static RegistryKey<Structure> of(String id) {
        RegistryKey<Structure> poolRegistryKey = RegistryKey.of(RegistryKeys.STRUCTURE, Pigsteel.getModId(id));
        structures.add(poolRegistryKey);
        return poolRegistryKey;
    }

    public static final RegistryKey<Structure> PIGSTEEL_MINE = of("pigsteel_mine");


    public static Structure.Config createConfig(RegistryEntryList<Biome> biomes, Map<SpawnGroup, StructureSpawns> spawns, GenerationStep.Feature featureStep, StructureTerrainAdaptation terrainAdaptation) {
        return new Structure.Config(biomes, spawns, featureStep, terrainAdaptation);
    }

    private static Structure.Config createConfig(RegistryEntryList<Biome> biomes, GenerationStep.Feature featureStep, StructureTerrainAdaptation terrainAdaptation) {
        return createConfig(biomes, Map.of(), featureStep, terrainAdaptation);
    }

    private static Structure.Config createConfig(RegistryEntryList<Biome> biomes, StructureTerrainAdaptation terrainAdaptation) {
        return createConfig(biomes, Map.of(), GenerationStep.Feature.SURFACE_STRUCTURES, terrainAdaptation);
    }
    public static void bootstrap(Registerable<Structure> structureRegisterable) {
        RegistryEntryLookup<Biome> registryEntryLookup = structureRegisterable.getRegistryLookup(RegistryKeys.BIOME);
        RegistryEntryLookup<StructurePool> registryEntryLookup2 = structureRegisterable.getRegistryLookup(RegistryKeys.TEMPLATE_POOL);

        structureRegisterable.register(PIGSTEEL_MINE, new JigsawStructure(createConfig(registryEntryLookup.getOrThrow(PigsteelBiomeTags.HAS_PIGSTEEL_MINE),
                Arrays.stream(SpawnGroup.values()).collect(Collectors.toMap((spawnGroup) -> spawnGroup, (spawnGroup) ->
                        new StructureSpawns(StructureSpawns.BoundingBox.STRUCTURE, Pool.empty()))),
                GenerationStep.Feature.UNDERGROUND_STRUCTURES, StructureTerrainAdaptation.BURY),
                registryEntryLookup2.getOrThrow(PigsteelTemplatePools.MINE_ENTRANCE),
                Optional.of(Identifier.of(Pigsteel.MOD_ID, "pigsteel_mine_entrance")),
                16,
                ConstantHeightProvider.create(YOffset.fixed(110)),
                false,
                Optional.empty(),
                116,
                List.of(),
                JigsawStructure.DEFAULT_DIMENSION_PADDING,
                JigsawStructure.DEFAULT_LIQUID_SETTINGS
                ));
    }
}
