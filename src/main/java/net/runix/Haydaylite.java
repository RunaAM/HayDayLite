package net.runix;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.*;
import net.minecraft.loot.LootTables;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.runix.blocks.CustomCropBlocks;
import net.runix.items.CustomCrops;
import net.runix.items.CustomFood;
import net.runix.loot_tables.CustomLootTables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Haydaylite implements ModInitializer {
    public static final String MOD_ID = "haydaylite";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


    private static void RegisterCrop(Item crop,String path,int fuelValue, float compostingValue){
        FuelRegistry.INSTANCE.add(crop,fuelValue);
        CompostingChanceRegistry.INSTANCE.add(crop, compostingValue);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content ->{
            //todo: add items before potions in creative inventory
            content.add(crop);
        });
        Registry.register(Registries.ITEM, new Identifier(MOD_ID,path), crop);


    }
    @Override
    public void onInitialize() {
        System.out.println(FoodComponents.GOLDEN_CARROT.getSaturationModifier());
        CustomLootTables.ModifyLootTable(CustomLootTables.GRASS_LOOT_TABLE, CustomCrops.BroccoliItem,0.15f);
        CustomLootTables.ModifyLootTable(CustomLootTables.WHEAT_LOOT_TABLE, CustomCrops.CornItem,0.05f);
        CustomLootTables.ModifyLootTable(CustomLootTables.CARROTS_LOOT_TABLE, CustomCrops.TomatoItem,0.15f);


        for(Identifier LootTable : CustomLootTables.VILLAGE_LOOT_TABLES){
            CustomLootTables.ModifyLootTable(LootTable, CustomCrops.CornItem,0.05f);
            CustomLootTables.ModifyLootTable(LootTable, CustomCrops.OnionItem,0.05f);
            CustomLootTables.ModifyLootTable(LootTable, CustomCrops.CabbageItem,0.05f);
            CustomLootTables.ModifyLootTable(LootTable, CustomCrops.TomatoItem,0.05f);
            CustomLootTables.ModifyLootTable(LootTable, CustomCrops.BroccoliItem,0.05f);

        }


        RegisterCrop(CustomCrops.CornItem,"corn",100,0.2f);
        Registry.register(Registries.BLOCK, new Identifier(MOD_ID,"corn_crop_block"), CustomCropBlocks.CornCropBlock);
        RegisterCrop(CustomCrops.BroccoliItem,"broccoli",20,0.3f);
        Registry.register(Registries.BLOCK, new Identifier(MOD_ID,"broccoli_crop_block"), CustomCropBlocks.BroccoliCropBlock);
        RegisterCrop(CustomCrops.TomatoItem,"tomato",10,0.3f);
        Registry.register(Registries.BLOCK,new Identifier(MOD_ID,"tomato_crop_block"), CustomCropBlocks.TomatoCropBlock);
        RegisterCrop(CustomCrops.OnionItem,"onion",200,0.3f);
        Registry.register(Registries.BLOCK,new Identifier(MOD_ID,"onion_crop_block"),CustomCropBlocks.OnionCropBlock);
        RegisterCrop(CustomCrops.CabbageItem,"cabbage",150,0.4f);
        Registry.register(Registries.BLOCK,new Identifier(MOD_ID,"cabbage_crop_block"),CustomCropBlocks.CabbageCropBlock);

        Registry.register(Registries.ITEM,new Identifier(MOD_ID,"mixed_salad"),CustomFood.MixedSalad);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content ->{
            content.add(CustomFood.MixedSalad);
        });

        Registry.register(Registries.ITEM,new Identifier(MOD_ID,"sandwich"), CustomFood.Sandwich);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content ->{
            content.add(CustomFood.Sandwich);
        });

        Registry.register(Registries.ITEM,new Identifier(MOD_ID,"tomato_sauce"),CustomFood.TomatoSauce);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content->{
            content.add(CustomFood.TomatoSauce);
        });

        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "polenta"), CustomFood.Polenta);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content->{
            content.add(CustomFood.Polenta);
        });

        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "broth"),CustomFood.Broth);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content->{
            content.add(CustomFood.Broth);
        });

        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "chocolate_milk_bottle"),CustomFood.ChocolateMilkBottle);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content->{
            content.add(CustomFood.ChocolateMilkBottle);
        });


    }
}