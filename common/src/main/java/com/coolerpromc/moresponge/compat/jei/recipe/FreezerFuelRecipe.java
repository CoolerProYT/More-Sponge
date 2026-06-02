package com.coolerpromc.moresponge.compat.jei.recipe;

import mezz.jei.api.recipe.vanilla.IJeiFuelingRecipe;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public record FreezerFuelRecipe(List<ItemStack> inputs, int burnTime) implements IJeiFuelingRecipe {
    @Override
    public @Unmodifiable List<ItemStack> getInputs() {
        return inputs;
    }

    @Override
    public int getBurnTime() {
        return burnTime;
    }
}
