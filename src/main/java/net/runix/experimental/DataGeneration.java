package net.runix.experimental;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.runix.items.CustomCrops;
import net.runix.items.CustomFood;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static net.runix.experimental.DataGeneration.MyTagGenerator.*;

public class DataGeneration implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(MyTagGenerator::new);
        pack.addProvider(MyRecipeGenerator::new);
        pack.addProvider(EnglishLangProvider::new);
        pack.addProvider(MyModelGenerator::new);


    }
    protected static class MyTagGenerator extends FabricTagProvider.ItemTagProvider{
        public MyTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture){
            super(output, completableFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup arg){


            getOrCreateTagBuilder(SANDWICH_INGREDIENTS)
                    .add(CustomCrops.TomatoItem)
                    .add(CustomCrops.CabbageItem);



        }
        public static final TagKey<Item> SANDWICH_INGREDIENTS = TagKey.of(RegistryKeys.ITEM,new Identifier("haydaylite:sandwich_ingredients"));

    }


    private static class MyRecipeGenerator extends FabricRecipeProvider{
        private MyRecipeGenerator(FabricDataOutput generator){
            super(generator);
        }

        @Override
        public void generate(Consumer<RecipeJsonProvider> exporter) {
            ShapelessRecipeJsonBuilder RecipeMixedSalad = ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD,CustomFood.MixedSalad);
            ArrayList<ItemForGenerator> IngredientsMixedSalad = new ArrayList<>(Arrays.asList(
                    new ItemForGenerator(CustomCrops.TomatoItem,2),
                    new ItemForGenerator(CustomCrops.BroccoliItem),
                    new ItemForGenerator(CustomCrops.CabbageItem),
                    new ItemForGenerator(CustomCrops.OnionItem),
                    new ItemForGenerator(Items.BOWL)
            ));

            for(ItemForGenerator Ingredient : IngredientsMixedSalad){
                for(short counter = 1;counter <=Ingredient.count;counter++) RecipeMixedSalad = RecipeMixedSalad.input(Ingredient.item);
                    RecipeMixedSalad = RecipeMixedSalad.criterion(FabricRecipeProvider.hasItem(Ingredient.item),
                            FabricRecipeProvider.conditionsFromItem(Ingredient.item));
            }
            RecipeMixedSalad.offerTo(exporter,new Identifier(FabricRecipeProvider.getRecipeName(CustomFood.MixedSalad)));



            ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD,CustomFood.Sandwich)
                    .pattern(" B ")
                    .pattern("CMC")
                    .pattern(" B ")
                    .input('B', Items.BREAD)
                    .input('M', Items.COOKED_PORKCHOP)
                    .input('C', SANDWICH_INGREDIENTS)
                    .criterion(FabricRecipeProvider.hasItem(Items.BREAD),
                            FabricRecipeProvider.conditionsFromItem(Items.BREAD))
                    .criterion(FabricRecipeProvider.hasItem(Items.COOKED_PORKCHOP),
                            FabricRecipeProvider.conditionsFromItem(Items.COOKED_PORKCHOP))
                    .criterion(FabricRecipeProvider.hasItem(CustomCrops.CabbageItem),
                            FabricRecipeProvider.conditionsFromItem(CustomCrops.CabbageItem))
                    .criterion(FabricRecipeProvider.hasItem(CustomCrops.TomatoItem),
                            FabricRecipeProvider.conditionsFromItem(CustomCrops.TomatoItem))
                    .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(CustomFood.Sandwich)));

            ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD,CustomFood.TomatoSauce)
                    .pattern("TTT")
                    .pattern("TTT")
                    .pattern(" B ")
                    .input('T',CustomCrops.TomatoItem)
                    .input('B',Items.BOWL)
                    .criterion(FabricRecipeProvider.hasItem(CustomCrops.TomatoItem),
                            FabricRecipeProvider.conditionsFromItem(CustomCrops.TomatoItem))
                    .criterion(FabricRecipeProvider.hasItem(Items.BOWL),
                            FabricRecipeProvider.conditionsFromItem(Items.BOWL))
                    .offerTo(exporter,new Identifier(FabricRecipeProvider.getRecipeName(CustomFood.TomatoSauce)));
        }
    }

    private static class EnglishLangProvider extends FabricLanguageProvider{
        private EnglishLangProvider(FabricDataOutput dataGenerator){
            super(dataGenerator,"en_us");
        }
        @Override
        public void generateTranslations(TranslationBuilder translationBuilder) {
            translationBuilder.add(CustomFood.MixedSalad, "Mixed Salad");
            translationBuilder.add(CustomCrops.BroccoliItem, "Broccoli");
            translationBuilder.add(CustomCrops.CabbageItem,"Cabbage");
            translationBuilder.add(CustomCrops.OnionItem,"Onion");
            translationBuilder.add(CustomCrops.TomatoItem,"Tomato");
            translationBuilder.add(CustomCrops.CornItem,"Crop");
            translationBuilder.add(CustomFood.Sandwich, "Sandwich");
            translationBuilder.add(CustomFood.TomatoSauce,"Tomato sauce");

        }
    }

    private static class MyModelGenerator extends FabricModelProvider {
        private MyModelGenerator(FabricDataOutput generator){
            super(generator);
        }
         @Override
        public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator){

         }

         @Override
        public void generateItemModels(ItemModelGenerator itemModelGenerator){
            itemModelGenerator.register(CustomFood.Sandwich, Models.GENERATED);
            itemModelGenerator.register(CustomFood.TomatoSauce,Models.GENERATED);
         }
    }

}

//TODO: REFACTOR THIS MESSY CODE
