package com.coolerpromc.moresponge.compat.jei;

import com.coolerpromc.moresponge.Constants;
import com.coolerpromc.moresponge.MoreSpongeClient;
import com.coolerpromc.moresponge.block.MSBlocks;
import com.coolerpromc.moresponge.block.entity.custom.FreezerBlockEntity;
import com.coolerpromc.moresponge.compat.jei.category.FreezingCategory;
import com.coolerpromc.moresponge.compat.jei.category.FreezingFuelCategory;
import com.coolerpromc.moresponge.compat.jei.recipe.FreezerFuelRecipe;
import com.coolerpromc.moresponge.recipe.MSRecipes;
import com.coolerpromc.moresponge.recipe.custom.FreezingRecipe;
import com.coolerpromc.moresponge.screen.custom.FreezerScreen;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.List;

@JeiPlugin
public class MSJEIPlugin implements IModPlugin {
    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addCraftingStation(FreezingCategory.TYPE, MSBlocks.FREEZER);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        List<FreezingRecipe> freezingRecipes = MoreSpongeClient.syncedRecipes.byType(MSRecipes.FREEZER_TYPE.get()).stream().map(RecipeHolder::value).toList();
        List<FreezerFuelRecipe> freezerFuelRecipes = FreezerBlockEntity.FUEL_VALUES.entrySet().stream().map((entry) -> new FreezerFuelRecipe(List.of(entry.getKey().getDefaultInstance()), entry.getValue())).toList();

        registration.addRecipes(FreezingCategory.TYPE, freezingRecipes);
        registration.addRecipes(FreezingFuelCategory.TYPE, freezerFuelRecipes);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new FreezingCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new FreezingFuelCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(FreezerScreen.class, 78, 32, 28, 23, FreezingCategory.TYPE);
    }

    @Override
    public Identifier getPluginUid() {
        return Constants.id("jei_plugin");
    }
}
