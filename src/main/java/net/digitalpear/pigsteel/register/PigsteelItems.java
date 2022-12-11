package net.digitalpear.pigsteel.register;

import net.digitalpear.pigsteel.PigsteelMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


@SuppressWarnings("unused")
public class PigsteelItems {
    public static final String MOD_ID = PigsteelMod.MOD_ID;






    public static Item registerItem(String id, Item item){
        return Registry.register(Registries.ITEM, new Identifier(MOD_ID, id), item);
    }

    public static final Item PIGSTEEL_INGOT = registerItem("pigsteel_ingot", new Item(new FabricItemSettings()));
    public static final Item PIGSTEEL_NUGGET = registerItem("pigsteel_nugget", new Item(new FabricItemSettings()));



    public static final ItemGroup ITEM_GROUP = FabricItemGroup.builder(new Identifier(PigsteelMod.MOD_ID, "pigsteel_item_group"))
            .icon(() ->new ItemStack(PigsteelBlocks.PIGSTEEL_BLOCK))
            .displayName(Text.translatable("itemGroup.pigsteel"))
            .entries((enabledFeatures, entries, operatorEnabled) -> {
                entries.add(new ItemStack(PIGSTEEL_INGOT));
                entries.add(new ItemStack(PIGSTEEL_NUGGET));

                entries.add(new ItemStack(PigsteelBlocks.PIGSTEEL_ORE));
                entries.add(new ItemStack(PigsteelBlocks.STONE_PIGSTEEL_ORE));
                entries.add(new ItemStack(PigsteelBlocks.DEEPSLATE_PIGSTEEL_ORE));
                entries.add(new ItemStack(PigsteelBlocks.BLUE_PIGSTEEL_ORE));
                entries.add(new ItemStack(PigsteelBlocks.BRIMSTONE_PIGSTEEL_ORE));

                entries.add(new ItemStack(PigsteelBlocks.PIGSTEEL_BLOCK));

                entries.add(new ItemStack(PigsteelBlocks.CUT_PIGSTEEL));
                entries.add(new ItemStack(PigsteelBlocks.CUT_PIGSTEEL_SLAB));
                entries.add(new ItemStack(PigsteelBlocks.CUT_PIGSTEEL_STAIRS));

                entries.add(new ItemStack(PigsteelBlocks.INFECTED_CUT_PIGSTEEL));
                entries.add(new ItemStack(PigsteelBlocks.INFECTED_CUT_PIGSTEEL_SLAB));
                entries.add(new ItemStack(PigsteelBlocks.INFECTED_CUT_PIGSTEEL_STAIRS));

                entries.add(new ItemStack(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL));
                entries.add(new ItemStack(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_SLAB));
                entries.add(new ItemStack(PigsteelBlocks.CORRUPTED_CUT_PIGSTEEL_STAIRS));

                entries.add(new ItemStack(PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL));
                entries.add(new ItemStack(PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_SLAB));
                entries.add(new ItemStack(PigsteelBlocks.ZOMBIFIED_CUT_PIGSTEEL_STAIRS));

                entries.add(new ItemStack(PigsteelBlocks.WAXED_PIGSTEEL_BLOCK));

                entries.add(new ItemStack(PigsteelBlocks.WAXED_CUT_PIGSTEEL));
                entries.add(new ItemStack(PigsteelBlocks.WAXED_CUT_PIGSTEEL_SLAB));
                entries.add(new ItemStack(PigsteelBlocks.WAXED_CUT_PIGSTEEL_STAIRS));

                entries.add(new ItemStack(PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL));
                entries.add(new ItemStack(PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL_SLAB));
                entries.add(new ItemStack(PigsteelBlocks.WAXED_INFECTED_CUT_PIGSTEEL_STAIRS));

                entries.add(new ItemStack(PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL));
                entries.add(new ItemStack(PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL_SLAB));
                entries.add(new ItemStack(PigsteelBlocks.WAXED_CORRUPTED_CUT_PIGSTEEL_STAIRS));

                entries.add(new ItemStack(PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL));
                entries.add(new ItemStack(PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL_SLAB));
                entries.add(new ItemStack(PigsteelBlocks.WAXED_ZOMBIFIED_CUT_PIGSTEEL_STAIRS));

                entries.add(new ItemStack(PigsteelBlocks.PIGSTEEL_LANTERN));
                entries.add(new ItemStack(PigsteelBlocks.PIGSTEEL_SOUL_LANTERN));
            })
            .build();
    public static void init(){


    }
}
