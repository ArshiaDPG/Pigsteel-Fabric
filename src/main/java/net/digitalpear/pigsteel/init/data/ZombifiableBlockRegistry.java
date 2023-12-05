package net.digitalpear.pigsteel.init.data;

import net.digitalpear.pigsteel.Pigsteel;
import net.digitalpear.pigsteel.common.blocks.Zombifiable;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.*;

public class ZombifiableBlockRegistry {

    private final Class<? extends Block> baseBlockClass;
    private final Class<? extends Block> waxedBlockClass;
    private final String baseName;
    private AbstractBlock.Settings settings = null;

    private Block unaffectedBlock;
    private Block infectedBlock;
    private Block corruptedBlock;
    private Block zombifiedBlock;

    private Block waxedUnaffectedBlock;
    private Block waxedInfectedBlock;
    private Block waxedCorruptedBlock;
    private Block waxedZombifiedBlock;

    public ZombifiableBlockRegistry(String baseName, Class<? extends Block> baseBlockClass, Class<? extends Block> waxedBlockClass, AbstractBlock.Settings settings) {
        this.settings = settings;
        this.baseName = baseName;
        this.baseBlockClass = baseBlockClass;
        this.waxedBlockClass = waxedBlockClass;

        this.unaffectedBlock = registerBlock(Zombifiable.ZombificationLevel.UNAFFECTED);
        this.infectedBlock = registerBlock(Zombifiable.ZombificationLevel.INFECTED);
        this.corruptedBlock = registerBlock(Zombifiable.ZombificationLevel.CORRUPTED);
        this.zombifiedBlock = registerBlock(Zombifiable.ZombificationLevel.ZOMBIFIED);

        this.waxedUnaffectedBlock = registerWaxedBlock(Zombifiable.ZombificationLevel.UNAFFECTED);
        this.waxedInfectedBlock = registerWaxedBlock(Zombifiable.ZombificationLevel.INFECTED);
        this.waxedCorruptedBlock = registerWaxedBlock(Zombifiable.ZombificationLevel.CORRUPTED);
        this.waxedZombifiedBlock = registerWaxedBlock(Zombifiable.ZombificationLevel.ZOMBIFIED);
    }
    public ZombifiableBlockRegistry(String baseName, Class<? extends Block> baseBlockClass, Class<? extends Block> waxedBlockClass) {
        this.baseName = baseName;
        this.baseBlockClass = baseBlockClass;
        this.waxedBlockClass = waxedBlockClass;

        this.unaffectedBlock = registerBlock(Zombifiable.ZombificationLevel.UNAFFECTED);
        this.infectedBlock = registerBlock(Zombifiable.ZombificationLevel.INFECTED);
        this.corruptedBlock = registerBlock(Zombifiable.ZombificationLevel.CORRUPTED);
        this.zombifiedBlock = registerBlock(Zombifiable.ZombificationLevel.ZOMBIFIED);

        this.waxedUnaffectedBlock = registerWaxedBlock(Zombifiable.ZombificationLevel.UNAFFECTED);
        this.waxedInfectedBlock = registerWaxedBlock(Zombifiable.ZombificationLevel.INFECTED);
        this.waxedCorruptedBlock = registerWaxedBlock(Zombifiable.ZombificationLevel.CORRUPTED);
        this.waxedZombifiedBlock = registerWaxedBlock(Zombifiable.ZombificationLevel.ZOMBIFIED);

    }
    private Block registerWaxedBlock(Zombifiable.ZombificationLevel level){
        Identifier blockName;
        if (level.equals(Zombifiable.ZombificationLevel.UNAFFECTED)){
            blockName = new Identifier(Pigsteel.MOD_ID, baseName).withPrefixedPath("waxed_");
        }
        else{
            blockName = new Identifier(Pigsteel.MOD_ID, level.asString() + "_" + baseName).withPrefixedPath("waxed_");
        }
        Block block;
        if (this.settings == null){
            block = createWaxedBlock(level.getMapColor());

        }
        else{
            block = createCustomWaxedBlock(level.getMapColor());
        }

        Registry.register(Registries.ITEM, blockName, new BlockItem(block, new Item.Settings().fireproof()));
        return Registry.register(Registries.BLOCK, blockName, block);
    }
    private Block registerBlock(Zombifiable.ZombificationLevel level){
        Identifier blockName;
        if (level.equals(Zombifiable.ZombificationLevel.UNAFFECTED)){
            blockName = new Identifier(Pigsteel.MOD_ID, baseName);
        }
        else{
            blockName = new Identifier(Pigsteel.MOD_ID, level.asString() + "_" + baseName);
        }
        Block block;
        if (this.settings == null){
            block = createBlock(level.getMapColor());
        }
        else{
            block = createCustomBlock(level.getMapColor());
        }

        Registry.register(Registries.ITEM, blockName, new BlockItem(block, new Item.Settings().fireproof()));
        return Registry.register(Registries.BLOCK, blockName, block);
    }
    private Block createCustomWaxedBlock(MapColor mapColor) {
        try {
            return waxedBlockClass.getConstructor(AbstractBlock.Settings.class).newInstance(settings.mapColor(mapColor));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private Block createCustomBlock(MapColor mapColor) {
        try {
            return baseBlockClass.getConstructor(AbstractBlock.Settings.class).newInstance(settings.mapColor(mapColor).ticksRandomly());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private Block createBlock(MapColor mapColor) {
        try {
            return baseBlockClass.getConstructor(AbstractBlock.Settings.class).newInstance(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.NETHERITE).mapColor(mapColor).ticksRandomly());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private Block createWaxedBlock(MapColor mapColor) {
        try {
            return waxedBlockClass.getConstructor(AbstractBlock.Settings.class).newInstance(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.NETHERITE).mapColor(mapColor));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Block getUnaffectedBlock() {
        return unaffectedBlock;
    }

    public Block getInfectedBlock() {
        return infectedBlock;
    }

    public Block getCorruptedBlock() {
        return corruptedBlock;
    }

    public Block getZombifiedBlock() {
        return zombifiedBlock;
    }

    public String getBaseName() {
        return baseName;
    }
    public List<Block> getZombifiables(){
        return List.of(unaffectedBlock, infectedBlock, corruptedBlock, zombifiedBlock);
    }


    public Block getWaxedUnaffectedBlock() {
        return waxedUnaffectedBlock;
    }

    public Block getWaxedInfectedBlock() {
        return waxedInfectedBlock;
    }

    public Block getWaxedCorruptedBlock() {
        return waxedCorruptedBlock;
    }

    public Block getWaxedZombifiedBlock() {
        return waxedZombifiedBlock;
    }

    public Map<Block, Block> getBlockToWaxedMap(){
        Map<Block, Block> map = new HashMap<>();
        map.put(getUnaffectedBlock(), getWaxedUnaffectedBlock());
        map.put(getInfectedBlock(), getWaxedInfectedBlock());
        map.put(getCorruptedBlock(), getWaxedCorruptedBlock());
        map.put(getZombifiedBlock(), getWaxedZombifiedBlock());

        return map;
    }
    public List<Block> getWaxed(){
        return List.of(waxedUnaffectedBlock, waxedInfectedBlock, waxedCorruptedBlock, waxedZombifiedBlock);
    }

    public List<Block> getAllBlocks(){
        return List.of(unaffectedBlock, infectedBlock, corruptedBlock, zombifiedBlock, waxedUnaffectedBlock, waxedInfectedBlock, waxedCorruptedBlock, waxedZombifiedBlock);
    }

    public void addToItemGroup(RegistryKey<ItemGroup> itemGroup){
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register(entries -> getAllBlocks().forEach(entries::add));
    }
    public void addToItemGroup(RegistryKey<ItemGroup> itemGroup, Item afterItem){
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register(entries -> {
            List<ItemStack> list = new ArrayList<>();
            getAllBlocks().forEach(block -> {
                list.add(new ItemStack(block));
            });

            entries.addAfter(afterItem, list);
        });
    }
}
