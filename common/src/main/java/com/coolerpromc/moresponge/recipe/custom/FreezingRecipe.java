package com.coolerpromc.moresponge.recipe.custom;

import com.coolerpromc.moresponge.recipe.MSRecipes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public record FreezingRecipe(Ingredient input, ItemStackTemplate output, int cookingTime, float experience, String group) implements Recipe<SingleRecipeInput>{
    public static final MapCodec<FreezingRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            Ingredient.CODEC.fieldOf("input").forGetter(FreezingRecipe::input),
            ItemStackTemplate.CODEC.fieldOf("output").forGetter(FreezingRecipe::output),
            Codec.INT.fieldOf("cookingTime").forGetter(FreezingRecipe::cookingTime),
            Codec.FLOAT.fieldOf("experience").forGetter(FreezingRecipe::experience),
            Codec.STRING.optionalFieldOf("group", "").forGetter(FreezingRecipe::group)
    ).apply(i, FreezingRecipe::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, FreezingRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC,
            FreezingRecipe::input,
            ItemStackTemplate.STREAM_CODEC,
            FreezingRecipe::output,
            ByteBufCodecs.INT,
            FreezingRecipe::cookingTime,
            ByteBufCodecs.FLOAT,
            FreezingRecipe::experience,
            ByteBufCodecs.STRING_UTF8,
            FreezingRecipe::group,
            FreezingRecipe::new
    );
    public static final RecipeSerializer<FreezingRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    @Override
    public boolean matches(SingleRecipeInput input, Level level) {
        return this.input.test(input.item());
    }

    @Override
    public ItemStack assemble(SingleRecipeInput input) {
        return output.create();
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return "";
    }

    @Override
    public RecipeSerializer<? extends Recipe<SingleRecipeInput>> getSerializer() {
        return MSRecipes.FREEZER_SERIALIZER.get();
    }

    @Override
    public RecipeType<? extends Recipe<SingleRecipeInput>> getType() {
        return MSRecipes.FREEZER_TYPE.get();
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.create(input);
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return null;
    }
}
