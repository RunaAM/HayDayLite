package net.runix;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.CropBlock;
import net.minecraft.client.render.RenderLayer;
import net.runix.blocks.CustomCropBlocks;

import java.util.ArrayList;
import java.util.Arrays;

public class HaydayliteClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        ArrayList<CropBlock> CustomCropBlocksForRendering = new ArrayList<>(Arrays.asList(
                CustomCropBlocks.CornCropBlock,CustomCropBlocks.BroccoliCropBlock,
                CustomCropBlocks.TomatoCropBlock, CustomCropBlocks.CabbageCropBlock,
                CustomCropBlocks.OnionCropBlock
        ));

        for(CropBlock CustomCropBlockForRendering : CustomCropBlocksForRendering){
            BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),CustomCropBlockForRendering);
        }

    }
}
//Testing my modifications