package net.digitalpear.pigsteel.register;

import net.digitalpear.pigsteel.PigsteelMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PigsteelItems {
    public static final String MOD_ID = PigsteelMod.MOD_ID;
    //Items
    public static final Item PIGSTEEL_INGOT = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final Item PIGSTEEL_NUGGET = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));

    public static void init(){
        //Items
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "pigsteel_ingot"), PIGSTEEL_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "pigsteel_nugget"), PIGSTEEL_NUGGET);
    }
}
