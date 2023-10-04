package net.digitalpear.pigsteel.init.tags;

import net.digitalpear.pigsteel.Pigsteel;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class PigsteelBiomeTags {

    public static final TagKey<Biome> HAS_EXTRA_PIGSTEEL = of("has_extra_pigsteel");
    public static final TagKey<Biome> HAS_NO_PIGSTEEL = of("has_no_pigsteel");
    private static TagKey<Biome> of(String id) {
        return TagKey.of(RegistryKeys.BIOME, new Identifier(Pigsteel.MOD_ID, id));
    }
}
