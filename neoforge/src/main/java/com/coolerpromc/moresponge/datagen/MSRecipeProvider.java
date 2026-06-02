package com.coolerpromc.moresponge.datagen;

import com.coolerpromc.moresponge.Constants;
import com.coolerpromc.moresponge.block.MSBlocks;
import com.coolerpromc.moresponge.platform.util.RegistryHandler;
import com.coolerpromc.moresponge.recipe.builder.FreezingRecipeBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class MSRecipeProvider extends RecipeProvider {
    protected MSRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        circleSurrounded(MSBlocks.FREEZER, Ingredient.of(items.getOrThrow(ItemTags.STONE_CRAFTING_MATERIALS)), Blocks.PACKED_ICE);
        circleSurrounded(MSBlocks.LAVA_SPONGE, Blocks.ICE, Blocks.SPONGE);
        circleSurrounded(MSBlocks.FIRE_SPONGE, Blocks.SNOW_BLOCK, Blocks.SPONGE);
        circleSurrounded(MSBlocks.SNOW_SPONGE, Blocks.MAGMA_BLOCK, Blocks.SPONGE);

        compressedSpongeRecipes(Blocks.SPONGE, MSBlocks.COMPRESSED_SPONGE, MSBlocks.COMPRESSED_SPONGE_2X, MSBlocks.COMPRESSED_SPONGE_3X, MSBlocks.COMPRESSED_SPONGE_4X, MSBlocks.COMPRESSED_SPONGE_5X);
        compressedSpongeRecipes(MSBlocks.LAVA_SPONGE, MSBlocks.COMPRESSED_LAVA_SPONGE, MSBlocks.COMPRESSED_LAVA_SPONGE_2X, MSBlocks.COMPRESSED_LAVA_SPONGE_3X, MSBlocks.COMPRESSED_LAVA_SPONGE_4X, MSBlocks.COMPRESSED_LAVA_SPONGE_5X);
        compressedSpongeRecipes(MSBlocks.FIRE_SPONGE, MSBlocks.COMPRESSED_FIRE_SPONGE, MSBlocks.COMPRESSED_FIRE_SPONGE_2X, MSBlocks.COMPRESSED_FIRE_SPONGE_3X, MSBlocks.COMPRESSED_FIRE_SPONGE_4X, MSBlocks.COMPRESSED_FIRE_SPONGE_5X);
        compressedSpongeRecipes(MSBlocks.SNOW_SPONGE, MSBlocks.COMPRESSED_SNOW_SPONGE, MSBlocks.COMPRESSED_SNOW_SPONGE_2X, MSBlocks.COMPRESSED_SNOW_SPONGE_3X, MSBlocks.COMPRESSED_SNOW_SPONGE_4X, MSBlocks.COMPRESSED_SNOW_SPONGE_5X);

        freezingRecipe(MSBlocks.HOT_LAVA_SPONGE, MSBlocks.LAVA_SPONGE);
        freezingRecipe(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE, MSBlocks.COMPRESSED_LAVA_SPONGE);
        freezingRecipe(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE_2X, MSBlocks.COMPRESSED_LAVA_SPONGE_2X);
        freezingRecipe(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE_3X, MSBlocks.COMPRESSED_LAVA_SPONGE_3X);
        freezingRecipe(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE_4X, MSBlocks.COMPRESSED_LAVA_SPONGE_4X);
        freezingRecipe(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE_5X, MSBlocks.COMPRESSED_LAVA_SPONGE_5X);

        freezingRecipe(MSBlocks.BURNT_FIRE_SPONGE, MSBlocks.FIRE_SPONGE);
        freezingRecipe(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE, MSBlocks.COMPRESSED_FIRE_SPONGE);
        freezingRecipe(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE_2X, MSBlocks.COMPRESSED_FIRE_SPONGE_2X);
        freezingRecipe(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE_3X, MSBlocks.COMPRESSED_FIRE_SPONGE_3X);
        freezingRecipe(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE_4X, MSBlocks.COMPRESSED_FIRE_SPONGE_4X);
        freezingRecipe(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE_5X, MSBlocks.COMPRESSED_FIRE_SPONGE_5X);

        smeltingRecipe(MSBlocks.FROZEN_SNOW_SPONGE, MSBlocks.SNOW_SPONGE);
        smeltingRecipe(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE, MSBlocks.COMPRESSED_SNOW_SPONGE);
        smeltingRecipe(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE_2X, MSBlocks.COMPRESSED_SNOW_SPONGE_2X);
        smeltingRecipe(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE_3X, MSBlocks.COMPRESSED_SNOW_SPONGE_3X);
        smeltingRecipe(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE_4X, MSBlocks.COMPRESSED_SNOW_SPONGE_4X);
        smeltingRecipe(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE_5X, MSBlocks.COMPRESSED_SNOW_SPONGE_5X);

        smeltingRecipe(MSBlocks.WET_COMPRESSED_SPONGE, MSBlocks.COMPRESSED_SPONGE);
        smeltingRecipe(MSBlocks.WET_COMPRESSED_SPONGE_2X, MSBlocks.COMPRESSED_SPONGE_2X);
        smeltingRecipe(MSBlocks.WET_COMPRESSED_SPONGE_3X, MSBlocks.COMPRESSED_SPONGE_3X);
        smeltingRecipe(MSBlocks.WET_COMPRESSED_SPONGE_4X, MSBlocks.COMPRESSED_SPONGE_4X);
        smeltingRecipe(MSBlocks.WET_COMPRESSED_SPONGE_5X, MSBlocks.COMPRESSED_SPONGE_5X);
    }

    private <T extends Block, R extends Block> void freezingRecipe(RegistryHandler.Blocks<T> input, RegistryHandler.Blocks<R> result){
        FreezingRecipeBuilder.freezing(Ingredient.of(input), new ItemStackTemplate(result.asItem()), (float) 0.15, 200).unlockedBy(getHasName(input), has(input)).save(output);
    }

    private void smeltingRecipe(ItemLike ingredient, ItemLike result){
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.BUILDING_BLOCKS, CookingBookCategory.BLOCKS, result, (float) 0.15, 200).unlockedBy(getHasName(ingredient), has(ingredient)).save(this.output, key(getSmeltingRecipeName(result)));
    }
    
    private <T extends Block> void compressedSpongeRecipes(ItemLike base, RegistryHandler.Blocks<T> compressed1x, RegistryHandler.Blocks<T> compressed2x, RegistryHandler.Blocks<T> compressed3x, RegistryHandler.Blocks<T> compressed4x, RegistryHandler.Blocks<T> compressed5x){
        twoByTwoPackerWithUnpacker(compressed1x, base);
        twoByTwoPackerWithUnpacker(compressed2x, compressed1x);
        twoByTwoPackerWithUnpacker(compressed3x, compressed2x);
        twoByTwoPackerWithUnpacker(compressed4x, compressed3x);
        twoByTwoPackerWithUnpacker(compressed5x, compressed4x);
    }
    
    private <T extends Block> void twoByTwoPackerWithUnpacker(RegistryHandler.Blocks<T> result, ItemLike ingredient){
        twoByTwoPacker(RecipeCategory.BUILDING_BLOCKS, result, ingredient);
        shapeless(RecipeCategory.BUILDING_BLOCKS, ingredient, 4).requires(result).unlockedBy(getHasName(result), has(result)).save(output, key(getFromName(result, ingredient)));
    }

    private <T extends Block> void circleSurrounded(RegistryHandler.Blocks<T> result, ItemLike ingredient, ItemLike middleIngredient){
        circleSurrounded(result, Ingredient.of(ingredient), middleIngredient);
    }

    private <T extends Block> void circleSurrounded(RegistryHandler.Blocks<T> result, Ingredient ingredient, ItemLike middleIngredient){
        shaped(RecipeCategory.BUILDING_BLOCKS, result).define('#', ingredient).define('M', middleIngredient).pattern("###").pattern("#M#").pattern("###").unlockedBy(getHasName(middleIngredient), has(middleIngredient)).save(output);
    }

    @SuppressWarnings("deprecation")
    private <T extends Block> String getFromName(RegistryHandler.Blocks<T> ingredient, ItemLike result){
        return result.asItem().builtInRegistryHolder().key().identifier().getPath() + "_from_" + ingredient.id().getPath();
    }

    private ResourceKey<Recipe<?>> key(String path){
        return ResourceKey.create(Registries.RECIPE, Constants.id(path));
    }

    public static class Runner extends RecipeProvider.Runner {
        protected Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider provider, @NonNull RecipeOutput recipeOutput) {
            return new MSRecipeProvider(provider, recipeOutput);
        }

        @Override
        public @NonNull String getName() {
            return "More Sponge Recipes";
        }
    }
}
