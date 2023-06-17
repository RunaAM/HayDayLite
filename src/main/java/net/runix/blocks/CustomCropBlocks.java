package net.runix.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.CropBlock;
import net.minecraft.sound.BlockSoundGroup;

public class CustomCropBlocks {
    public static final CropBlock CornCropBlock = new CustomCropBlock(AbstractBlock.Settings
            .create().nonOpaque().noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP));

    public static final CropBlock BroccoliCropBlock = new CustomCropBlock(AbstractBlock.Settings
            .create().nonOpaque().noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP));

    public static final CropBlock TomatoCropBlock = new CustomCropBlock(AbstractBlock.Settings
            .create().nonOpaque().noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP));

    public static final CropBlock OnionCropBlock = new CustomCropBlock(AbstractBlock.Settings
            .create().nonOpaque().noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP));

    public static final CropBlock CabbageCropBlock = new CustomCropBlock(AbstractBlock.Settings
            .create().nonOpaque().noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP));
}
