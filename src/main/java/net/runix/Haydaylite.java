package net.runix;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
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
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final Identifier GRASS_LOOT_TABLE = Blocks.GRASS.getLootTableId();
    public static final Identifier WHEAT_LOOT_TABLE = Blocks.WHEAT.getLootTableId();

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

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if(source.isBuiltin()&& GRASS_LOOT_TABLE.equals(id)){
                LootPool.Builder poolBuilder = LootPool.builder()
                        .with(ItemEntry.builder(CustomCrops.BroccoliItem))
                        .conditionally(RandomChanceLootCondition.builder(0.15f))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,2.0f)).build());
                tableBuilder.pool(poolBuilder);
            }
        });
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if(source.isBuiltin()&&WHEAT_LOOT_TABLE.equals(id)){
                LootPool.Builder poolBuider = LootPool.builder()
                        .with(ItemEntry.builder(CustomCrops.CornItem))
                        .conditionally(RandomChanceLootCondition.builder(0.05f))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,2.0f)));
                tableBuilder.pool(poolBuider);
            }
        });


        RegisterCrop(CustomCrops.CornItem,"corn",100,0.2f);
        Registry.register(Registries.BLOCK, new Identifier(MOD_ID,"corn_crop_block"), CustomCrops.CornCropBlock);
        RegisterCrop(CustomCrops.BroccoliItem,"broccoli",20,0.3f);
        Registry.register(Registries.BLOCK, new Identifier(MOD_ID,"broccoli_crop_block"),CustomCrops.BroccoliCropBlock);


        //TODO: fix loot tables for crop blocks
    }
}