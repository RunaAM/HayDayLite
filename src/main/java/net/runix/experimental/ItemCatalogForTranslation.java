package net.runix.experimental;

import net.runix.items.CustomCrops;
import net.runix.items.CustomFood;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemCatalogForTranslation {
    ArrayList<ItemForGenerator> CatalogForTranslations = new ArrayList<>(
            Arrays.asList(
                    new ItemForGenerator(CustomFood.MixedSalad, "Mixed Salad"),
                    new ItemForGenerator(CustomFood.Sandwich,"Sandwich"),
                    new ItemForGenerator(CustomFood.TomatoSauce,"Tomato Sauce"),

                    new ItemForGenerator(CustomCrops.TomatoItem,"Tomato"),
                    new ItemForGenerator(CustomCrops.OnionItem, "Onion"),
                    new ItemForGenerator(CustomCrops.CabbageItem,"Onion"),
                    new ItemForGenerator(CustomCrops.BroccoliItem,"Broccoli"),
                    new ItemForGenerator(CustomCrops.CornItem, "Corn")
            )
    );
}
