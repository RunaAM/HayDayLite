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
import net.minecraft.recipe.Recipe;
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

            getOrCreateTagBuilder(SANDWICH_MEAT)
                    .add(Items.COOKED_PORKCHOP)
                    .add(Items.COOKED_BEEF)
                    .add(Items.COOKED_CHICKEN)
                    .add(Items.COOKED_COD)
                    .add(Items.COOKED_SALMON)
                    .add(Items.COOKED_RABBIT)
                    .add(Items.COOKED_MUTTON);

        }
        public static final TagKey<Item> SANDWICH_INGREDIENTS = TagKey.of(RegistryKeys.ITEM,new Identifier("haydaylite:sandwich_ingredients"));
        public static final TagKey<Item> SANDWICH_MEAT = TagKey.of(RegistryKeys.ITEM, new Identifier("haydaylite:sandwich_meat"));
    }


    private static class MyRecipeGenerator extends FabricRecipeProvider{
        private MyRecipeGenerator(FabricDataOutput generator){
            super(generator);
        }

        //generates a recipe using
        private void GenerateShapeLessRecipe(ArrayList<ItemForGenerator> Ingredients,RecipeCategory category,Item result, Consumer<RecipeJsonProvider> exporter){

            ShapelessRecipeJsonBuilder Recipe = ShapelessRecipeJsonBuilder.create(category,result);
            for(ItemForGenerator Ingredient: Ingredients){
                for(short counter = 1;counter <=Ingredient.count;counter++)
                    Recipe = Recipe.input(Ingredient.item);
                Recipe = Recipe.criterion(FabricRecipeProvider.hasItem(Ingredient.item),
                        FabricRecipeProvider.conditionsFromItem(Ingredient.item));
            }
            Recipe.offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(result)));
        }

        @Override
        public void generate(Consumer<RecipeJsonProvider> exporter) {


            ArrayList<ItemForGenerator> IngredientsMixedSalad = new ArrayList<>(Arrays.asList(
                    new ItemForGenerator(CustomCrops.TomatoItem,2),
                    new ItemForGenerator(CustomCrops.BroccoliItem),
                    new ItemForGenerator(CustomCrops.CabbageItem),
                    new ItemForGenerator(CustomCrops.OnionItem),
                    new ItemForGenerator(Items.BOWL)
            ));

            GenerateShapeLessRecipe(IngredientsMixedSalad,RecipeCategory.FOOD,CustomFood.MixedSalad,exporter);

            ArrayList<ItemForGenerator> IngredientsPolenta = new ArrayList<>(Arrays.asList(
                    new ItemForGenerator(CustomCrops.CornItem, 3),
                    new ItemForGenerator(Items.WATER_BUCKET)

            ));

            GenerateShapeLessRecipe(IngredientsPolenta,RecipeCategory.FOOD,CustomFood.Polenta,exporter);


            ArrayList<ItemForGenerator> IngredientsBroth = new ArrayList<>(Arrays.asList(
                    new ItemForGenerator(CustomFood.TomatoSauce),
                    new ItemForGenerator(Items.CARROT,2),
                    new ItemForGenerator(Items.POTATO,2),
                    new ItemForGenerator(Items.BONE,2)
            ));

            GenerateShapeLessRecipe(IngredientsBroth,RecipeCategory.FOOD,CustomFood.Broth,exporter);

            ArrayList<ItemForGenerator> IngredientsChocolateMilkBottle = new ArrayList<>(Arrays.asList(
                    new ItemForGenerator(Items.COCOA_BEANS, 2),
                    new ItemForGenerator(Items.MILK_BUCKET),
                    new ItemForGenerator(Items.SUGAR)
            ));

            GenerateShapeLessRecipe(IngredientsChocolateMilkBottle,RecipeCategory.FOOD,CustomFood.ChocolateMilkBottle,exporter);

            ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD,CustomFood.Sandwich)
                    .pattern(" B ")
                    .pattern("CMC")
                    .pattern(" B ")
                    .input('B', Items.BREAD)
                    .input('M', SANDWICH_MEAT)
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
            translationBuilder.add(CustomFood.Polenta, "Polenta");
            translationBuilder.add(CustomFood.Broth, "Broth");
            translationBuilder.add(CustomFood.ChocolateMilkBottle, "Chocolate Milk Bottle");

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
            itemModelGenerator.register(CustomFood.Polenta, Models.GENERATED);
            itemModelGenerator.register(CustomFood.Broth,Models.GENERATED);
            itemModelGenerator.register(CustomFood.ChocolateMilkBottle,Models.GENERATED);
         }
    }



}

//TODO: REFACTOR THIS MESSY CODE
