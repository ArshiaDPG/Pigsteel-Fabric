package net.digitalpear.pigsteel.register.tags;

import net.digitalpear.pigsteel.PigsteelMod;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class PigsteelItemTags {

    public static final TagKey<Item> IRON_INGOTS = of("irons");
    public static final TagKey<Item> IRON_NUGGETS = of("iron_nuggets");
    public static final TagKey<Item> IRON_BLOCKS = of("iron_blocks");
    public static final TagKey<Item> PIGSTEEL_ORES = of("pigsteel_ores");


    private static TagKey<Item> of(String id) {
        return TagKey.of(Registries.ITEM.getKey(), new Identifier(PigsteelMod.MOD_ID, id));
    }
}
