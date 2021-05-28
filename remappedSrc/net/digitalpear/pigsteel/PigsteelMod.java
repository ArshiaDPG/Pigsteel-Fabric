package net.digitalpear.pigsteel;

import net.digitalpear.pigsteel.SpecialBlocks.PigsteelBlock;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PigsteelMod implements ModInitializer {
	
    public static final Block PIGSTEEL_BLOCK = new PigsteelBlock(FabricBlockSettings.copy(Blocks.IRON_BLOCK));
	public static final Block CUT_PIGSTEEL = new Block(FabricBlockSettings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.NETHERITE));
	public static final Block PIGSTEEL_ORE = new Block(FabricBlockSettings.copy(Blocks.NETHER_GOLD_ORE));
	public static final Block STONE_PIGSTEEL_ORE = new Block(FabricBlockSettings.copy(Blocks.IRON_ORE));

	@Override
	public void onInitialize() {

		Registry.register(Registry.BLOCK, new Identifier("pigsteel", "pigsteel_block"), PIGSTEEL_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("pigsteel", "pigsteel_block"), new BlockItem(PIGSTEEL_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
		
		Registry.register(Registry.BLOCK, new Identifier("pigsteel", "cut_pigsteel"), CUT_PIGSTEEL);
        Registry.register(Registry.ITEM, new Identifier("pigsteel", "cut_pigsteel"), new BlockItem(CUT_PIGSTEEL, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
		
		Registry.register(Registry.BLOCK, new Identifier("pigsteel", "pigsteel_ore"), PIGSTEEL_ORE);
        Registry.register(Registry.ITEM, new Identifier("pigsteel", "pigsteel_ore"), new BlockItem(PIGSTEEL_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

		Registry.register(Registry.BLOCK, new Identifier("pigsteel", "stone_pigsteel_ore"), STONE_PIGSTEEL_ORE);
        Registry.register(Registry.ITEM, new Identifier("pigsteel", "stone_pigsteel_ore"), new BlockItem(STONE_PIGSTEEL_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
		
		Registry.register(Registry.ITEM, new Identifier("pigsteel", "pigsteel_ingot"), new Item(new FabricItemSettings().group(ItemGroup.MISC)));
		Registry.register(Registry.ITEM, new Identifier("pigsteel", "pigsteel_nugget"), new Item(new FabricItemSettings().group(ItemGroup.MISC)));
		
		System.out.println("Hello Fabric world!");
	}
}
