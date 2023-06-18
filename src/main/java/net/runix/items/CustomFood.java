package net.runix.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.runix.experimental.ItemForGenerator;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomFood {

    public static final Item MixedSalad = new Item(new FabricItemSettings()
            .food(new FoodComponent.Builder().hunger(5).saturationModifier(2.0f).build()).maxCount(1));

    public static final Item Sandwich = new Item(new FabricItemSettings()
            .food(new FoodComponent.Builder().hunger(6).saturationModifier(5.0f).build()).maxCount(16));

    public static final Item TomatoSauce = new Item(new FabricItemSettings()
            .food(new FoodComponent.Builder().hunger(3).saturationModifier(1.5f).build()).maxCount(1));


}
