package net.runix.loot_tables;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class CustomLootTables {
    public static final Identifier GRASS_LOOT_TABLE = Blocks.GRASS.getLootTableId();
    public static final Identifier WHEAT_LOOT_TABLE = Blocks.WHEAT.getLootTableId();

    public static final Identifier CARROTS_LOOT_TABLE = Blocks.CARROTS.getLootTableId();

    public static void ModifyLootTable(Identifier loot_table, Item item,float chance){
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            LootPool.Builder poolBuilder =LootPool.builder()
                    .with(ItemEntry.builder(item))
                    .conditionally(RandomChanceLootCondition.builder(chance))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,2.0f)));
        });
    }
}
