package net.digitalpear.pigsteel.register.tags;

import net.digitalpear.pigsteel.PigsteelMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PigsteelItemTags {
    public static final TagKey<Item> IRONS = of("irons");
    public static final TagKey<Item> IRON_NUGGETS = of("iron_nuggets");
    public static final TagKey<Item> IRON_BLOCKS = of("iron_blocks");
    public static final TagKey<Item> PIGSTEEL_ORES = of("pigsteel_ores");


    private static TagKey<Item> of(String id) {
        return TagKey.of(Registry.ITEM_KEY, new Identifier(PigsteelMod.MOD_ID, id));
    }
}
