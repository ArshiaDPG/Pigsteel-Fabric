package net.digitalpear.pigsteel.register.tags;

import net.digitalpear.pigsteel.PigsteelMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class PigsteelItemTags {

    public static final TagKey<Item> IRON_INGOTS = of("irons");
    public static final TagKey<Item> IRON_NUGGETS = of("iron_nuggets");
    public static final TagKey<Item> IRON_BLOCKS = of("iron_blocks");
    public static final TagKey<Item> PIGSTEEL_ORES = of("pigsteel_ores");

    public static final TagKey<Item> C_ORES = of("c","ores");
    public static final TagKey<Item> C_IRONS = of("c","iron_ingots");
    public static final TagKey<Item> C_IRON_BLOCKS = of("c","iron_blocks");


    private static TagKey<Item> of(String id) {
        return TagKey.of(Registries.ITEM.getKey(), new Identifier(PigsteelMod.MOD_ID, id));
    }
    private static TagKey<Item> of(String modid,String id) {
        return TagKey.of(Registries.ITEM.getKey(), new Identifier(modid, id));
    }
}
