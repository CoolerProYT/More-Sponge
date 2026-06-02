package com.coolerpromc.moresponge.compat.jei.category;

import com.coolerpromc.moresponge.Constants;
import com.coolerpromc.moresponge.block.MSBlocks;
import com.coolerpromc.moresponge.block.entity.custom.FreezerBlockEntity;
import com.coolerpromc.moresponge.recipe.custom.FreezingRecipe;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.placement.HorizontalAlignment;
import mezz.jei.api.gui.placement.VerticalAlignment;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.network.chat.Component;

public class FreezingCategory extends AbstractRecipeCategory<FreezingRecipe> {
    public static final IRecipeType<FreezingRecipe> TYPE = IRecipeType.create(Constants.id("freezing"), FreezingRecipe.class);

    public FreezingCategory(IGuiHelper guiHelper) {
        super(TYPE, Component.translatable("gui.jei.category.freezing"), guiHelper.createDrawableItemLike(MSBlocks.FREEZER), 82, 54);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FreezingRecipe recipe, IFocusGroup focuses) {
        builder.addInputSlot(1, 1).setStandardSlotBackground().add(recipe.input());
        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 1, 37).setStandardSlotBackground().addItemStacks(FreezerBlockEntity.FUELS);
        builder.addOutputSlot(61, 19).setOutputSlotBackground().add(recipe.output());
    }

    @Override
    public void createRecipeExtras(IRecipeExtrasBuilder builder, FreezingRecipe recipe, IFocusGroup focuses) {
        int cookTime = recipe.cookingTime();
        if (cookTime <= 0) {
            cookTime = 200;
        }
        builder.addAnimatedRecipeArrow(cookTime).setPosition(26, 17);
        builder.addAnimatedRecipeFlame(300).setPosition(1, 20);

        addExperience(builder, recipe);
        addCookTime(builder, recipe);
    }

    protected void addExperience(IRecipeExtrasBuilder builder, FreezingRecipe recipe) {
        float experience = recipe.experience();
        if (experience > 0) {
            Component experienceString = Component.translatable("gui.jei.category.smelting.experience", experience);
            builder.addText(experienceString, getWidth() - 20, 10)
                    .setPosition(0, 0, getWidth(), getHeight(), HorizontalAlignment.RIGHT, VerticalAlignment.TOP)
                    .setTextAlignment(HorizontalAlignment.RIGHT)
                    .setColor(0xFF808080);
        }
    }

    protected void addCookTime(IRecipeExtrasBuilder builder, FreezingRecipe recipe) {
        int cookTime = recipe.cookingTime();
        if (cookTime <= 0) {
            cookTime = 200;
        }
        if (cookTime > 0) {
            int cookTimeSeconds = cookTime / 20;
            Component timeString = Component.translatable("gui.jei.category.smelting.time.seconds", cookTimeSeconds);
            builder.addText(timeString, getWidth() - 20, 10)
                    .setPosition(0, 0, getWidth(), getHeight(), HorizontalAlignment.RIGHT, VerticalAlignment.BOTTOM)
                    .setTextAlignment(HorizontalAlignment.RIGHT)
                    .setTextAlignment(VerticalAlignment.BOTTOM)
                    .setColor(0xFF808080);
        }
    }
}
