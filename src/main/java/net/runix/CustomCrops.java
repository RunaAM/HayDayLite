package net.runix;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.CropBlock;
import net.minecraft.block.PlantBlock;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.MinecartItem;
import net.minecraft.sound.BlockSoundGroup;

public class CustomCrops {
    public static final FoodComponent BroccoliFoodComp = new FoodComponent.Builder().hunger(1).saturationModifier(0.2f).build();

    public static final CropBlock CustomCropBlock = new CustomCropBlock(AbstractBlock.Settings.create().nonOpaque().noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP));

    public static final Item BroccoliItem = new AliasedBlockItem(CustomCropBlock,  new FabricItemSettings().food(BroccoliFoodComp));

    public static final FoodComponent CornFoodComp = new FoodComponent.Builder().hunger(3).saturationModifier(0.4f).build();
    public static final Item CornItem = new Item(new FabricItemSettings().food(CornFoodComp));
}
