package net.runix.loot_tables;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.loottable.vanilla.VanillaChestLootTableGenerator;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.structure.Structures;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomLootTables {
    public static final Identifier GRASS_LOOT_TABLE = Blocks.GRASS.getLootTableId();
    public static final Identifier WHEAT_LOOT_TABLE = Blocks.WHEAT.getLootTableId();

    public static final Identifier CARROTS_LOOT_TABLE = Blocks.CARROTS.getLootTableId();

    public static final ArrayList<Identifier> VILLAGE_LOOT_TABLES = new ArrayList<>(Arrays.asList(
            LootTables.VILLAGE_ARMORER_CHEST,LootTables.VILLAGE_BUTCHER_CHEST, LootTables.VILLAGE_DESERT_HOUSE_CHEST,
            LootTables.VILLAGE_CARTOGRAPHER_CHEST, LootTables.VILLAGE_FISHER_CHEST, LootTables.VILLAGE_FLETCHER_CHEST,
            LootTables.VILLAGE_MASON_CHEST, LootTables.VILLAGE_PLAINS_CHEST, LootTables.VILLAGE_SHEPARD_CHEST,
            LootTables.VILLAGE_WEAPONSMITH_CHEST,LootTables.VILLAGE_TOOLSMITH_CHEST, LootTables.VILLAGE_TEMPLE_CHEST,
            LootTables.VILLAGE_TANNERY_CHEST, LootTables.VILLAGE_TAIGA_HOUSE_CHEST, LootTables.VILLAGE_SNOWY_HOUSE_CHEST,
            LootTables.VILLAGE_SAVANNA_HOUSE_CHEST
    ));

    public static void ModifyLootTable(Identifier loot_table, Item item,float chance){
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            LootPool.Builder poolBuilder =LootPool.builder()
                    .with(ItemEntry.builder(item))
                    .conditionally(RandomChanceLootCondition.builder(chance))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,2.0f)));

            tableBuilder.pool(poolBuilder);
        });
    }
}
