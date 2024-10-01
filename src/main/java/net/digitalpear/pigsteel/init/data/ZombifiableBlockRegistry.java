package net.digitalpear.pigsteel.init.data;

import net.digitalpear.pigsteel.Pigsteel;
import net.digitalpear.pigsteel.common.blocks.Zombifiable;
import net.digitalpear.pigsteel.init.PigsteelItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ZombifiableBlockRegistry {
    public static Map<Block, Block> PIGSTEEL_WAXING_MAP = new HashMap<>();
    public static Map<Block, Block> PIGSTEEL_ZOMBIFYING_MAP = new HashMap<>();

    public static final AbstractBlock.Settings basePigsteelSettings = AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.NETHERITE);

    public static final List<ZombifiableBlockRegistry> REGISTRIES = new ArrayList<>();
    private Function<AbstractBlock.Settings, Block> blockFunction;
    private Function<AbstractBlock.Settings, Block> waxedBlockFunction;
    private String baseName;
    public AbstractBlock.Settings settings;

    private Block unaffectedBlock;
    private Block infectedBlock;
    private Block corruptedBlock;
    private Block zombifiedBlock;

    private Block waxedUnaffectedBlock;
    private Block waxedInfectedBlock;
    private Block waxedCorruptedBlock;
    private Block waxedZombifiedBlock;

    public ZombifiableBlockRegistry(String baseName, Function<AbstractBlock.Settings, Block> baseBlockClass, Function<AbstractBlock.Settings, Block> waxedBlockFunction, AbstractBlock.Settings settings) {
        this.settings = settings;
        defineBlocks(baseName, baseBlockClass, waxedBlockFunction);
        mapWaxingAndAxing();
        REGISTRIES.add(this);
    }
    public ZombifiableBlockRegistry(String baseName, Function<AbstractBlock.Settings, Block> baseBlockClass, Function<AbstractBlock.Settings, Block> waxedBlockClass) {
        this(baseName, baseBlockClass, waxedBlockClass, basePigsteelSettings);
    }

    private void defineBlocks(String baseName, Function<AbstractBlock.Settings, Block> blockFunction, Function<AbstractBlock.Settings, Block> waxedBlockFunction){
        this.baseName = baseName;
        this.blockFunction = blockFunction;
        this.waxedBlockFunction = waxedBlockFunction;

        this.unaffectedBlock = registerBlock(Zombifiable.ZombificationLevel.UNAFFECTED);
        this.infectedBlock = registerBlock(Zombifiable.ZombificationLevel.INFECTED);
        this.corruptedBlock = registerBlock(Zombifiable.ZombificationLevel.CORRUPTED);
        this.zombifiedBlock = registerBlock(Zombifiable.ZombificationLevel.ZOMBIFIED);

        this.waxedUnaffectedBlock = registerBlock(Zombifiable.ZombificationLevel.UNAFFECTED, true);
        this.waxedInfectedBlock = registerBlock(Zombifiable.ZombificationLevel.INFECTED, true);
        this.waxedCorruptedBlock = registerBlock(Zombifiable.ZombificationLevel.CORRUPTED, true);
        this.waxedZombifiedBlock = registerBlock(Zombifiable.ZombificationLevel.ZOMBIFIED, true);
    }

    public static Item createBlockItem(String id, Block block, BiFunction<Block, Item.Settings, Item> factory) {
        Item.Settings settings = new Item.Settings().fireproof();
        return createBlockItem(id, block, factory, settings);
    }
    public static Item createBlockItem(String id, Block block, BiFunction<Block, Item.Settings, Item> factory, Item.Settings settings) {
        Item item = factory.apply(block, settings.registryKey(PigsteelItems.keyOf(id)));
        return Registry.register(Registries.ITEM, PigsteelItems.keyOf(id), item);
    }
    private Block registerBlock(Zombifiable.ZombificationLevel level){
        return registerBlock(level, false);
    }
    private Block registerBlock(Zombifiable.ZombificationLevel level, boolean waxed){
        Identifier blockName;
        Block block;
        if (level.equals(Zombifiable.ZombificationLevel.UNAFFECTED)){
            blockName = Pigsteel.getModId(baseName);
        }
        else{
            blockName = Pigsteel.getModId(level.asString() + "_" + baseName);
        }
        if (waxed){
            blockName = blockName.withPrefixedPath("waxed_");
            block = blockFunction.apply(settings.mapColor(level.getMapColor()).registryKey(keyOf(blockName.getPath())));
        }
        else {
            block = waxedBlockFunction.apply(settings.mapColor(level.getMapColor()).registryKey(keyOf(blockName.getPath())));
        }
        
        createBlockItem(blockName.getPath(), block, BlockItem::new);
        return Registry.register(Registries.BLOCK, blockName, block);
    }
    private static RegistryKey<Block> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.BLOCK, Pigsteel.getModId(id));
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
        PIGSTEEL_WAXING_MAP.putAll(this.getBlockToWaxedMap());
        PIGSTEEL_ZOMBIFYING_MAP.put(this.getUnaffectedBlock(), this.getInfectedBlock());
        PIGSTEEL_ZOMBIFYING_MAP.put(this.getInfectedBlock(), this.getCorruptedBlock());
        PIGSTEEL_ZOMBIFYING_MAP.put(this.getCorruptedBlock(), this.getZombifiedBlock());
    }
    public static void addWaxableBlock(Block input ,Block result){
        PIGSTEEL_WAXING_MAP.put(input, result);
    }
    public static void addZombifiableBlock(Block input, Block result){
        PIGSTEEL_ZOMBIFYING_MAP.put(input, result);
    }

    public static Map<Block, Block> getPigsteelWaxingMap() {
        return PIGSTEEL_WAXING_MAP;
    }

    public static Map<Block, Block> getPigsteelZombifyingMap() {
        return PIGSTEEL_ZOMBIFYING_MAP;
    }

    public static void registerWaxingAndZombifications(){
        PIGSTEEL_WAXING_MAP.forEach(OxidizableBlocksRegistry::registerWaxableBlockPair);
        PIGSTEEL_ZOMBIFYING_MAP.forEach(OxidizableBlocksRegistry::registerOxidizableBlockPair);
    }
}
