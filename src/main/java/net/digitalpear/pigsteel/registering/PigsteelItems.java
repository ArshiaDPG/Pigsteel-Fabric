package net.digitalpear.pigsteel.registering;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public final class PigsteelItems {

    public static final Supplier<Item.Settings> DEFAULT =
            FabricItemSettings::new;
    public static final Supplier<Item.Settings> BUILDING_BLOCKS =
            () -> DEFAULT.get().group(ItemGroup.BUILDING_BLOCKS);
    public static final Supplier<Item.Settings> ITEMS =
            () -> DEFAULT.get().group(ItemGroup.MISC);
    private static final Map<Item, String> REGISTRY = new HashMap<>();


    public static final Item PIGSTEEL_INGOT = register("pigsteel_ingot",
            new Item(ITEMS.get()));
    public static final Item PIGSTEEL_NUGGET = register("pigsteel_nugget",
            new Item(ITEMS.get()));

    static <I extends Item> I register(String id, I item) {
        REGISTRY.putIfAbsent(item, id);
        return item;
    }

    public static void init() {
        REGISTRY.forEach((item, id) ->
                Registry.register(Registry.ITEM, Id.mod(id), item)
        );
    }

}
