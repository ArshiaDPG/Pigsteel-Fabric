package net.digitalpear.pigsteel.client;

import net.digitalpear.pigsteel.register.PigsteelBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;


@Environment(EnvType.CLIENT)
public class PigsteelModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), PigsteelBlocks.PIGSTEEL_LANTERN, PigsteelBlocks.PIGSTEEL_SOUL_LANTERN);
    }
}
