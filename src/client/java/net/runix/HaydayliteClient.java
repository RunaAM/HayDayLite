package net.runix;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.runix.blocks.CustomCropBlocks;

public class HaydayliteClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), CustomCropBlocks.CornCropBlock);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), CustomCropBlocks.BroccoliCropBlock);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), CustomCropBlocks.TomatoCropBlock);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),CustomCropBlocks.OnionCropBlock);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),CustomCropBlocks.CabbageCropBlock);
    }
}
//Testing my modifications