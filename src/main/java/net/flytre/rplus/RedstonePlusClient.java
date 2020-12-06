package net.flytre.rplus;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.flytre.rplus.util.BlockRegistry;
import net.minecraft.client.render.RenderLayer;

public class RedstonePlusClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.DIODE, RenderLayer.getCutout());
    }
}
