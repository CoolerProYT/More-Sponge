package com.coolerpromc.moresponge.recipe;

import com.coolerpromc.moresponge.Constants;
import com.coolerpromc.moresponge.platform.Services;
import com.coolerpromc.moresponge.platform.util.RegistryHandler;
import com.coolerpromc.moresponge.recipe.custom.FreezingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class MSRecipes {
    public static final RegistryHandler<RecipeType<?>, RecipeType<FreezingRecipe>> FREEZER_TYPE = Services.REGISTRY.registerRecipeType("freezing");
    public static final RegistryHandler<RecipeSerializer<?>, RecipeSerializer<FreezingRecipe>> FREEZER_SERIALIZER = Services.REGISTRY.registerRecipeSerializer("freezing", FreezingRecipe.SERIALIZER);

    public static void init() {
        Constants.LOGGER.info("Registering More Sponge recipes.");
    }
}
