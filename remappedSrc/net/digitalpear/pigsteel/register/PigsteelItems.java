package net.digitalpear.pigsteel.register;

import net.digitalpear.pigsteel.PigsteelMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


@SuppressWarnings("unused")
public class PigsteelItems {
    public static final String MOD_ID = PigsteelMod.MOD_ID;

    public static Item registerItem(String id, Item item){
        return Registry.register(Registry.ITEM, new Identifier(MOD_ID, id), item);
    }
    public static final Item PIGSTEEL_INGOT = registerItem("pigsteel_ingot", new Item(new FabricItemSettings().group(ItemGroup.MATERIALS)));
    public static final Item PIGSTEEL_NUGGET = registerItem("pigsteel_nugget", new Item(new FabricItemSettings().group(ItemGroup.MATERIALS)));

    public static void init(){
    }
}
