package net.digitalpear.pigsteel.register.tags;

import net.digitalpear.pigsteel.PigsteelMod;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PigsteelBlockTags {
    public static final TagKey<Block> PIGSTEEL_ORES = of("pigsteel_ores");

    private static TagKey<Block> of(String id) {
        return TagKey.of(Registry.BLOCK_KEY, new Identifier(PigsteelMod.MOD_ID, id));
    }
}
