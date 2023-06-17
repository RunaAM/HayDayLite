package net.runix.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.CropBlock;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.runix.blocks.CustomCropBlock;
import net.runix.blocks.CustomCropBlocks;

public class CustomCrops {




    public static final Item CornItem = new AliasedBlockItem(CustomCropBlocks.CornCropBlock,  new FabricItemSettings()
            .food(new FoodComponent.Builder().hunger(4).saturationModifier(0.5f).build()));
    public static final Item BroccoliItem = new AliasedBlockItem(CustomCropBlocks.BroccoliCropBlock,new FabricItemSettings()
            .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.2f).build()));

    public static final Item TomatoItem = new AliasedBlockItem(CustomCropBlocks.TomatoCropBlock,new FabricItemSettings()
            .food(new FoodComponent.Builder().hunger(2).saturationModifier(0.2f).build()));

    public static final Item OnionItem = new AliasedBlockItem(CustomCropBlocks.OnionCropBlock,new FabricItemSettings()
            .food(new FoodComponent.Builder().hunger(3).saturationModifier(0.1f).build()));

    public static final Item CabbageItem = new AliasedBlockItem(CustomCropBlocks.CabbageCropBlock,new FabricItemSettings()
            .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.2f).build()));
}
