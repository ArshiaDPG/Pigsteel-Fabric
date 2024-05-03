package net.digitalpear.pigsteel.init.worldgen.structure;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.digitalpear.pigsteel.Pigsteel;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class PigsteelTemplatePools {

    public static List<RegistryKey<StructurePool>> templates = new ArrayList<>();

    public static RegistryKey<StructurePool> of(String id) {
        return RegistryKey.of(RegistryKeys.TEMPLATE_POOL, new Identifier(Pigsteel.MOD_ID, id));
    }
    public static void register(Registerable<StructurePool> structurePoolsRegisterable, RegistryKey<StructurePool> id, StructurePool pool) {
        structurePoolsRegisterable.register(of(id.getValue().getPath()), pool);
    }

    public static final RegistryKey<StructurePool> MINE_ENTRANCE = of(mineName("entrance"));
    public static final RegistryKey<StructurePool> MINE_RAILWAY = of(mineName("railways"));

    public static void bootstrap(Registerable<StructurePool> structurePoolsRegisterable) {
        RegistryEntryLookup<StructurePool> registryEntryLookup = structurePoolsRegisterable.getRegistryLookup(RegistryKeys.TEMPLATE_POOL);

        RegistryEntryLookup<StructureProcessorList> processorListRegistryEntryLookup = structurePoolsRegisterable.getRegistryLookup(RegistryKeys.PROCESSOR_LIST);
        RegistryEntry<StructureProcessorList> processorListRegistryEntry = processorListRegistryEntryLookup.getOrThrow(PigsteelProcessorLists.PIGSTEEL_MINE);

        RegistryEntry<StructurePool> emptyEntry = registryEntryLookup.getOrThrow(StructurePools.EMPTY);


        register(structurePoolsRegisterable, MINE_ENTRANCE, new StructurePool(emptyEntry,
                ImmutableList.of(
                        Pair.of(StructurePoolElement.ofProcessedLegacySingle(Pigsteel.MOD_ID + ":" + mineName("entrance"), processorListRegistryEntry), 1)
                ),
                StructurePool.Projection.RIGID));

        register(structurePoolsRegisterable, MINE_RAILWAY, new StructurePool(emptyEntry,
                ImmutableList.of(
                        Pair.of(StructurePoolElement.ofProcessedLegacySingle(Pigsteel.MOD_ID + ":" + mineName("railway"), processorListRegistryEntry), 12),
                        Pair.of(StructurePoolElement.ofProcessedLegacySingle(Pigsteel.MOD_ID + ":" + mineName("railway_booster"), processorListRegistryEntry), 11),
                        Pair.of(StructurePoolElement.ofProcessedLegacySingle(Pigsteel.MOD_ID + ":" + mineName("railway_intersection"), processorListRegistryEntry), 11),
                        Pair.of(StructurePoolElement.ofProcessedLegacySingle(Pigsteel.MOD_ID + ":" + mineName("furnace_room"), processorListRegistryEntry), 3),
                        Pair.of(StructurePoolElement.ofProcessedLegacySingle(Pigsteel.MOD_ID + ":" + mineName("drill_room"), processorListRegistryEntry), 3),
                        Pair.of(StructurePoolElement.ofProcessedLegacySingle(Pigsteel.MOD_ID + ":" + mineName("explosives_room"), processorListRegistryEntry), 3),
                        Pair.of(StructurePoolElement.ofProcessedLegacySingle(Pigsteel.MOD_ID + ":" + mineName("storage_room"), processorListRegistryEntry), 1)
                ),
                StructurePool.Projection.RIGID));

        templates.add(MINE_ENTRANCE);
        templates.add(MINE_RAILWAY);
    }

    public static String mineName(String name){
        return "pigsteel_mine/" + name;
    }
}
