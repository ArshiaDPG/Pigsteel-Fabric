package net.digitalpear.pigsteel.init.data;

import net.digitalpear.pigsteel.Pigsteel;
import net.digitalpear.pigsteel.common.blocks.Zombifiable;
import net.digitalpear.pigsteel.init.PigsteelBlocks;
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
import java.util.stream.Collectors;

public class ZombifiableBlockRegistry {
    
    private Class<? extends Block> baseBlockClass;
    private Class<? extends Block> waxedBlockClass;
    private String baseName;
    private AbstractBlock.Settings settings = AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.NETHERITE);

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
        defineBlocks(baseName, baseBlockClass, waxedBlockClass);
    }
    public ZombifiableBlockRegistry(String baseName, Class<? extends Block> baseBlockClass, Class<? extends Block> waxedBlockClass) {
        defineBlocks(baseName, baseBlockClass, waxedBlockClass);
        mapWaxingAndAxing();
    }
    private void defineBlocks(String baseName, Class<? extends Block> baseBlockClass, Class<? extends Block> waxedBlockClass){
        this.baseName = baseName;
        this.baseBlockClass = baseBlockClass;
        this.waxedBlockClass = waxedBlockClass;

        this.unaffectedBlock = registerBlock(Zombifiable.ZombificationLevel.UNAFFECTED);
        this.infectedBlock = registerBlock(Zombifiable.ZombificationLevel.INFECTED);
        this.corruptedBlock = registerBlock(Zombifiable.ZombificationLevel.CORRUPTED);
        this.zombifiedBlock = registerBlock(Zombifiable.ZombificationLevel.ZOMBIFIED);

        this.waxedUnaffectedBlock = registerBlock(Zombifiable.ZombificationLevel.UNAFFECTED, true);
        this.waxedInfectedBlock = registerBlock(Zombifiable.ZombificationLevel.INFECTED, true);
        this.waxedCorruptedBlock = registerBlock(Zombifiable.ZombificationLevel.CORRUPTED, true);
        this.waxedZombifiedBlock = registerBlock(Zombifiable.ZombificationLevel.ZOMBIFIED, true);
    }

    private Block registerBlock(Zombifiable.ZombificationLevel level){
        return registerBlock(level, false);
    }
    private Block registerBlock(Zombifiable.ZombificationLevel level, boolean waxed){
        Identifier blockName;
        if (level.equals(Zombifiable.ZombificationLevel.UNAFFECTED)){
            blockName = Pigsteel.getModId(baseName);
        }
        else{
            blockName = Pigsteel.getModId(level.asString() + "_" + baseName);
        }
        if (waxed){
            blockName = blockName.withPrefixedPath("waxed_");
        }
        Block block = createBlock(level.getMapColor(), waxed);
        Registry.register(Registries.ITEM, blockName, new BlockItem(block, new Item.Settings().fireproof()));
        return Registry.register(Registries.BLOCK, blockName, block);
    }
    private Block createBlock(MapColor mapColor, boolean waxed) {
        Class<? extends Block> blockClass = waxed ? baseBlockClass : waxedBlockClass;
        try {
            if (waxed){
                return blockClass.getConstructor(AbstractBlock.Settings.class).newInstance(settings.mapColor(mapColor));
            }
            return blockClass.getConstructor(AbstractBlock.Settings.class).newInstance(settings.mapColor(mapColor).ticksRandomly());
        } catch (Exception e) {
            Pigsteel.LOGGER.error("Block settings for " + getBaseName() + " could not be registered.");
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
    public List<Block> getZombifiables(){
        return List.of(unaffectedBlock, infectedBlock, corruptedBlock, zombifiedBlock);
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
            List<ItemStack> list = getAllBlocks().stream().map(ItemStack::new).collect(Collectors.toList());

            entries.addAfter(afterItem, list);
        });
    }
    private void mapWaxingAndAxing(){
        PigsteelBlocks.PIGSTEEL_WAXING_MAP.putAll(this.getBlockToWaxedMap());
        PigsteelBlocks.PIGSTEEL_ZOMBIFYING_MAP.put(this.getUnaffectedBlock(), this.getInfectedBlock());
        PigsteelBlocks.PIGSTEEL_ZOMBIFYING_MAP.put(this.getInfectedBlock(), this.getCorruptedBlock());
        PigsteelBlocks.PIGSTEEL_ZOMBIFYING_MAP.put(this.getCorruptedBlock(), this.getZombifiedBlock());
    }
}
