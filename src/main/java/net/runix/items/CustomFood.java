package net.runix.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.CakeBlock;
import net.minecraft.item.*;
import net.runix.experimental.ItemForGenerator;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomFood {

    public static final Item MixedSalad = new StewItem(new FabricItemSettings()
            .food(new FoodComponent.Builder().hunger(5).saturationModifier(2.0f).build()).maxCount(1));

    public static final Item Sandwich = new Item(new FabricItemSettings()
            .food(new FoodComponent.Builder().hunger(6).saturationModifier(5.0f).build()).maxCount(16));

    public static final Item TomatoSauce = new StewItem(new FabricItemSettings()
            .food(new FoodComponent.Builder().hunger(3).saturationModifier(1.5f).build()).maxCount(1));

    public static final Item Polenta = new Item(new FabricItemSettings()
            .food(new FoodComponent.Builder().hunger(4).saturationModifier(0.5f).build()).maxCount(16));

    public static final Item Broth = new StewItem(new FabricItemSettings()
            .food(new FoodComponent.Builder().hunger(6).saturationModifier(0.8f).build()).maxCount(1));


    public static final Item ChocolateMilkBottle = new GlassItem(new FabricItemSettings()
            .food(new FoodComponent.Builder().hunger(4).saturationModifier(1.2f).build()).maxCount(1));

}
