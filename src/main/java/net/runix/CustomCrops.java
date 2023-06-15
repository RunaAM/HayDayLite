package net.runix;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.CropBlock;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.runix.blocks.CustomCropBlock;

public class CustomCrops {


    public static final CropBlock CornCropBlock = new CustomCropBlock(AbstractBlock.Settings
            .create().nonOpaque().noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP));

    public static final CropBlock BroccoliCropBlock = new CustomCropBlock(AbstractBlock.Settings
            .create().nonOpaque().noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP));

    public static final Item CornItem = new AliasedBlockItem(CornCropBlock,  new FabricItemSettings()
            .food(new FoodComponent.Builder().hunger(4).saturationModifier(0.5f).build()));
    public static final Item BroccoliItem = new AliasedBlockItem(BroccoliCropBlock,new FabricItemSettings()
            .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.2f).build()));
}
