package net.digitalpear.pigsteel.register.tags;

import net.digitalpear.pigsteel.Pigsteel;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class PigsteelBlockTags {
    public static final TagKey<Block> PIGSTEEL_ORES = of("pigsteel_ores");
    public static final TagKey<Block> PIGSTEEL_BLOCKS = of("pigsteel_blocks");

    public static final TagKey<Block> C_ORES = of("c","ores");
    public static final TagKey<Block> C_IRON_BLOCKS = of("c","iron_blocks");

    private static TagKey<Block> of(String id) {
        return TagKey.of(Registries.BLOCK.getKey(), new Identifier(Pigsteel.MOD_ID, id));
    }
    private static TagKey<Block> of(String modid,String id) {
        return TagKey.of(Registries.BLOCK.getKey(), new Identifier(modid, id));
    }
}
