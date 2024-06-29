package net.digitalpear.pigsteel.common.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.List;

public class OreSlagFeatureConfig extends OreFeatureConfig {
    public static final Codec<OreSlagFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Codec.list(Target.CODEC).fieldOf("slag_targets").forGetter((config) -> config.slagTargets),

            Codec.intRange(1, 64).fieldOf("slag_count_max").forGetter((config) -> config.slagCountMax),

            Codec.intRange(1, 64).fieldOf("slag_width_max").forGetter((config) -> config.slagWidthMax),

            Codec.floatRange(0.0F, 1.0F).fieldOf("slag_chance").forGetter((config) -> config.slagChance),

            Codec.list(Target.CODEC).fieldOf("targets").forGetter((config) -> config.targets),
            Codec.intRange(0, 64).fieldOf("size").forGetter((config) -> config.size),
            Codec.floatRange(0.0F, 1.0F).fieldOf("discard_chance_on_air_exposure").forGetter((config) -> config.discardOnAirChance)

            ).apply(instance, OreSlagFeatureConfig::new));

    List<Target> slagTargets;

    int slagCountMax;
    int slagWidthMax;
    float slagChance;

    public OreSlagFeatureConfig(List<Target> slagTargets, int slagCountMax, int slagWidthMax, float slagChance, List<Target> targets, int size, float discardOnAirChance) {
        super(targets, size, discardOnAirChance);

        this.slagTargets = slagTargets;

        this.slagCountMax = slagCountMax;

        this.slagWidthMax = slagWidthMax;

        this.slagChance = slagChance;
    }
}
