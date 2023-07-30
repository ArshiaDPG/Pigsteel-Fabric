package net.digitalpear.pigsteel.init.data;

import net.digitalpear.pigsteel.Pigsteel;
import net.digitalpear.pigsteel.common.blocks.Zombifiable;
import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.List;

public class ZombifiableBlockRegistry {

    private final Class<? extends Block> baseBlockClass;
    private final Class<? extends Block> waxedBlockClass;
    private final String baseName;

    private Block unaffectedBlock;
    private Block infectedBlock;
    private Block corruptedBlock;
    private Block zombifiedBlock;

    private Block waxedUnaffectedBlock;
    private Block waxedInfectedBlock;
    private Block waxedCorruptedBlock;
    private Block waxedZombifiedBlock;

    public ZombifiableBlockRegistry(String baseName, Class<? extends Block> baseBlockClass, Class<? extends Block> waxedBlockClass) {
        this.baseName = baseName;
        this.baseBlockClass = baseBlockClass;
        this.waxedBlockClass = waxedBlockClass;

        this.unaffectedBlock = registerBlock(Zombifiable.ZombificationLevel.UNAFFECTED);
        this.infectedBlock = registerBlock(Zombifiable.ZombificationLevel.INFECTED);
        this.corruptedBlock = registerBlock(Zombifiable.ZombificationLevel.CORRUPTED);
        this.zombifiedBlock = registerBlock(Zombifiable.ZombificationLevel.ZOMBIFIED);

        this.waxedUnaffectedBlock = PigsteelBlocks.createBlockWithItem(Registries.BLOCK.getId(unaffectedBlock).withPrefixedPath("waxed_").getPath(), createWaxedBlock(unaffectedBlock.getDefaultMapColor()));
        this.waxedInfectedBlock = PigsteelBlocks.createBlockWithItem(Registries.BLOCK.getId(infectedBlock).withPrefixedPath("waxed_").getPath(), createWaxedBlock(infectedBlock.getDefaultMapColor()));
        this.waxedCorruptedBlock = PigsteelBlocks.createBlockWithItem(Registries.BLOCK.getId(corruptedBlock).withPrefixedPath("waxed_").getPath(), createWaxedBlock(corruptedBlock.getDefaultMapColor()));
        this.waxedZombifiedBlock = PigsteelBlocks.createBlockWithItem(Registries.BLOCK.getId(zombifiedBlock).withPrefixedPath("waxed_").getPath(), createWaxedBlock(zombifiedBlock.getDefaultMapColor()));

    }

    private Block registerBlock(Zombifiable.ZombificationLevel level){
        Identifier blockName;
        if (level.equals(Zombifiable.ZombificationLevel.UNAFFECTED)){
            blockName = new Identifier(Pigsteel.MOD_ID, baseName);
        }
        else{
            blockName = new Identifier(Pigsteel.MOD_ID, level.asString() + "_" + baseName);
        }
        Block block = createBlock(level.getMapColor());
        Registry.register(Registries.ITEM, blockName, new BlockItem(block, new Item.Settings()));
        return Registry.register(Registries.BLOCK, blockName, block);
    }
    private Block createWaxedBlock(MapColor mapColor) {
        try {
            return waxedBlockClass.getConstructor(AbstractBlock.Settings.class).newInstance(AbstractBlock.Settings.create().mapColor(mapColor));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private Block createBlock(MapColor mapColor) {
        try {
            return baseBlockClass.getConstructor(AbstractBlock.Settings.class).newInstance(AbstractBlock.Settings.create().mapColor(mapColor));
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
    public List<Block> getWaxed(){
        return List.of(waxedUnaffectedBlock, waxedInfectedBlock, waxedCorruptedBlock, waxedZombifiedBlock);
    }

    public List<Block> getAllBlocks(){
        return List.of(unaffectedBlock, infectedBlock, corruptedBlock, zombifiedBlock, waxedUnaffectedBlock, waxedInfectedBlock, waxedCorruptedBlock, waxedZombifiedBlock);
    }
}
