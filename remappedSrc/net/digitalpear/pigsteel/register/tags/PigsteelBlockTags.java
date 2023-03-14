package net.digitalpear.pigsteel.register.tags;

import net.digitalpear.pigsteel.PigsteelMod;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class PigsteelBlockTags {
    public static final TagKey<Block> PIGSTEEL_ORES = of("pigsteel_ores");
    public static final TagKey<Block> PIGSTEEL_BLOCKS = of("pigsteel_blocks");

    private static TagKey<Block> of(String id) {
        return TagKey.of(Registries.BLOCK.getKey(), new Identifier(PigsteelMod.MOD_ID, id));
    }
}
