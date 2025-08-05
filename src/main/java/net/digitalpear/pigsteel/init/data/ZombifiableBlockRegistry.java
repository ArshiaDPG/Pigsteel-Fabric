package net.digitalpear.pigsteel.init.data;

import com.google.common.collect.ImmutableMap;
import net.digitalpear.pigsteel.Pigsteel;
import net.digitalpear.pigsteel.common.blocks.Zombifiable;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
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
@SuppressWarnings("unused")
public class ZombifiableBlockRegistry {
    public static Map<Block, Block> PIGSTEEL_WAXING_MAP = new HashMap<>();
    public static Map<Block, Block> PIGSTEEL_ZOMBIFYING_MAP = new HashMap<>();



    public static final List<ZombifiableBlockRegistry> REGISTRIES = new ArrayList<>();
    private final BiFunction<Zombifiable.ZombificationLevel, AbstractBlock.Settings, Block> blockFunction;
    private final BiFunction<Zombifiable.ZombificationLevel, AbstractBlock.Settings, Block> waxedBlockFunction;
    private final String baseName;
    private AbstractBlock.Settings blockSettings;
    private Item.Settings itemSettings;

    private Block unaffectedBlock;
    private Block infectedBlock;
    private Block corruptedBlock;
    private Block zombifiedBlock;

    private Block waxedUnaffectedBlock;
    private Block waxedInfectedBlock;
    private Block waxedCorruptedBlock;
    private Block waxedZombifiedBlock;

    private ZombifiableBlockRegistry(String baseName, BiFunction<Zombifiable.ZombificationLevel, AbstractBlock.Settings, Block> baseBlockFunction, BiFunction<Zombifiable.ZombificationLevel, AbstractBlock.Settings, Block> waxedBlockFunction) {
        this.baseName = baseName;
        this.blockFunction = baseBlockFunction;
        this.waxedBlockFunction = waxedBlockFunction;
    }
    private void defineBlocks(){
        this.unaffectedBlock = registerBlock(Zombifiable.ZombificationLevel.UNAFFECTED);
        this.infectedBlock = registerBlock(Zombifiable.ZombificationLevel.INFECTED);
        this.corruptedBlock = registerBlock(Zombifiable.ZombificationLevel.CORRUPTED);
        this.zombifiedBlock = registerBlock(Zombifiable.ZombificationLevel.ZOMBIFIED);

        this.waxedUnaffectedBlock = registerBlock(Zombifiable.ZombificationLevel.UNAFFECTED, true);
        this.waxedInfectedBlock = registerBlock(Zombifiable.ZombificationLevel.INFECTED, true);
        this.waxedCorruptedBlock = registerBlock(Zombifiable.ZombificationLevel.CORRUPTED, true);
        this.waxedZombifiedBlock = registerBlock(Zombifiable.ZombificationLevel.ZOMBIFIED, true);
        mapWaxingAndAxing();
        REGISTRIES.add(this);
    }

    private Block registerBlock(Zombifiable.ZombificationLevel level){
        return registerBlock(level, false);
    }
    private Block registerBlock(Zombifiable.ZombificationLevel level, boolean waxed){
        Identifier blockName;
        Block block;
        if (level.equals(Zombifiable.ZombificationLevel.UNAFFECTED)){
            blockName = Pigsteel.id(baseName);
        }
        else{
            blockName = Pigsteel.id(level.asString() + "_" + baseName);
        }

        if (waxed){
            block =  Blocks.register(keyOf(blockName.withPrefixedPath("waxed_")), settings1 -> waxedBlockFunction.apply(level, settings1), blockSettings.mapColor(level.getMapColor()));
        }
        else {
            block = Blocks.register(keyOf(blockName), settings1 -> blockFunction.apply(level, settings1), blockSettings.mapColor(level.getMapColor()));
        }
        Items.register(block, BlockItem::new, itemSettings);
        return block;
    }
    private static RegistryKey<Block> keyOf(Identifier id) {
        return RegistryKey.of(RegistryKeys.BLOCK, id);
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

    public Block getBlockFromLevel(Zombifiable.ZombificationLevel level){
        ImmutableMap<Zombifiable.ZombificationLevel, Block> map = ImmutableMap.<Zombifiable.ZombificationLevel, Block>builderWithExpectedSize(4)
                .put(Zombifiable.ZombificationLevel.UNAFFECTED, getUnaffectedBlock())
                .put(Zombifiable.ZombificationLevel.INFECTED, getInfectedBlock())
                .put(Zombifiable.ZombificationLevel.CORRUPTED, getCorruptedBlock())
                .put(Zombifiable.ZombificationLevel.ZOMBIFIED, getZombifiedBlock())
                .build();
        return map.get(level);
    }
    public Block getWaxedBlockFromLevel(Zombifiable.ZombificationLevel level){
        ImmutableMap<Zombifiable.ZombificationLevel, Block> map = ImmutableMap.<Zombifiable.ZombificationLevel, Block>builderWithExpectedSize(4)
                .put(Zombifiable.ZombificationLevel.UNAFFECTED, getWaxedUnaffectedBlock())
                .put(Zombifiable.ZombificationLevel.INFECTED, getWaxedInfectedBlock())
                .put(Zombifiable.ZombificationLevel.CORRUPTED, getWaxedCorruptedBlock())
                .put(Zombifiable.ZombificationLevel.ZOMBIFIED, getWaxedZombifiedBlock())
                .build();
        return map.get(level);
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


    public static class Builder{
        private final ZombifiableBlockRegistry registry;
        private static final AbstractBlock.Settings BASE_PIGSTEEL_BLOCK_SETTINGS = AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.NETHERITE);
        private static final Item.Settings BASE_PIGSTEEL_ITEM_SETTINGS = new Item.Settings().fireproof();

        public Builder(String baseName, BiFunction<Zombifiable.ZombificationLevel, AbstractBlock.Settings, Block> baseBlockFunction, BiFunction<Zombifiable.ZombificationLevel, AbstractBlock.Settings, Block> waxedBlockFunction){
            registry = new ZombifiableBlockRegistry(baseName, baseBlockFunction, waxedBlockFunction);
            registry.itemSettings = BASE_PIGSTEEL_ITEM_SETTINGS;
            registry.blockSettings = BASE_PIGSTEEL_BLOCK_SETTINGS;
        }

        public Builder setBlockSettings(AbstractBlock.Settings settings){
            registry.blockSettings = settings;
            return this;
        }

        public Builder setItemSettings(Item.Settings settings){
            registry.itemSettings = settings;
            return this;
        }

        public Builder applyBlockSettingsFunction(Function<AbstractBlock.Settings, AbstractBlock.Settings> settingsFunction){
            registry.blockSettings = settingsFunction.apply(registry.blockSettings);
            return this;
        }

        public Builder applyItemSettingsFunction(Function<Item.Settings, Item.Settings> settingsFunction){
            registry.itemSettings = settingsFunction.apply(registry.itemSettings);
            return this;
        }

        public ZombifiableBlockRegistry build(){
            registry.defineBlocks();
            return registry;
        }
    }
}
