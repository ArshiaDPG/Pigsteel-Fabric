package net.digitalpear.pigsteel.client;

import net.digitalpear.pigsteel.init.PigsteelBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;


@Environment(EnvType.CLIENT)
public class PigsteelClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        PigsteelBlocks.PIGSTEEL_LANTERNS.getAllBlocks().forEach(block -> BlockRenderLayerMap.putBlock(block, BlockRenderLayer.CUTOUT));
        PigsteelBlocks.PIGSTEEL_SOUL_LANTERNS.getAllBlocks().forEach(block -> BlockRenderLayerMap.putBlock(block, BlockRenderLayer.CUTOUT));
    }
}
