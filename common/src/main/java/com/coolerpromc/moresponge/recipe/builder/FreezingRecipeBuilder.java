package com.coolerpromc.moresponge.recipe.builder;

import com.coolerpromc.moresponge.recipe.custom.FreezingRecipe;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeUnlockAdvancementBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import org.jspecify.annotations.Nullable;

public class FreezingRecipeBuilder implements RecipeBuilder {
    private final ItemStackTemplate result;
    private final Ingredient input;
    private final float experience;
    private final int cookingTime;
    private final RecipeUnlockAdvancementBuilder advancementBuilder;
    private @Nullable String group;

    private FreezingRecipeBuilder(Ingredient ingredient, ItemStackTemplate result, float experience, int cookingTime){
        this.input = ingredient;
        this.result = result;
        this.experience = experience;
        this.cookingTime = cookingTime;
        this.advancementBuilder = new RecipeUnlockAdvancementBuilder();
    }

    public static FreezingRecipeBuilder freezing(Ingredient ingredient, ItemStackTemplate result, float experience, int cookingTime){
        return new FreezingRecipeBuilder(ingredient, result, experience, cookingTime);
    }

    @Override
    public FreezingRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.advancementBuilder.unlockedBy(name, criterion);
        return this;
    }

    @Override
    public FreezingRecipeBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public ResourceKey<Recipe<?>> defaultId() {
        return ResourceKey.create(Registries.RECIPE, result.typeHolder().unwrapKey().orElseThrow().identifier().withPrefix("freezing/"));
    }

    @Override
    public void save(RecipeOutput output, ResourceKey<Recipe<?>> location) {
        FreezingRecipe recipe = new FreezingRecipe(input, result, cookingTime, experience, group);
        output.accept(location, recipe, advancementBuilder.build(output, location, RecipeCategory.MISC));
    }
}
