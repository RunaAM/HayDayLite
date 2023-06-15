package net.runix;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Haydaylite implements ModInitializer {
    public static final String MOD_ID = "haydaylite";
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final FoodComponent CUSTOM_ITEM_FOOD = new FoodComponent.Builder().hunger(3).saturationModifier(0.3f).build();
    public static final CustomItem CUSTOM_ITEM = new CustomItem(new FabricItemSettings().maxCount(16));
    private static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .icon(()->new ItemStack(CUSTOM_ITEM))
            .displayName(Text.translatable("itemGroup.haydaylite.test_group"))
            .build();

    private static void RegisterCrop(Item crop,String path,int fuelValue, float compostingValue){
        FuelRegistry.INSTANCE.add(crop,fuelValue);
        CompostingChanceRegistry.INSTANCE.add(crop, compostingValue);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content ->{
            content.add(crop);
        });
        Registry.register(Registries.ITEM, new Identifier(MOD_ID,path), crop);


    }
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        LOGGER.info("Hello Fabric world!");
        FuelRegistry.INSTANCE.add(CUSTOM_ITEM,300);
        CompostingChanceRegistry.INSTANCE.add(CUSTOM_ITEM,20.f);

        Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID,"test_group"),ITEM_GROUP);


        Registry.register(Registries.ITEM,new Identifier(MOD_ID,"custom_item"),CUSTOM_ITEM);

        //RegisterCrop(CustomCrops.BroccoliItem,"broccoli",20,3.f);
        RegisterCrop(CustomCrops.CornItem,"corn",100,0.2f);
        Registry.register(Registries.BLOCK, new Identifier(MOD_ID,"custom_crop_block"), CustomCrops.CustomCropBlock);
        RegisterCrop(CustomCrops.BroccoliItem,"broccoli",20,0.3f);



    }
}