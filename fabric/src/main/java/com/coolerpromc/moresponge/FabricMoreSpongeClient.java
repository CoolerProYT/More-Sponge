package com.coolerpromc.moresponge;

import com.coolerpromc.moresponge.screen.MSMenus;
import com.coolerpromc.moresponge.screen.custom.FreezerScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.recipe.v1.sync.ClientRecipeSynchronizedEvent;
import net.fabricmc.fabric.api.recipe.v1.sync.SynchronizedRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.crafting.RecipeMap;

public class FabricMoreSpongeClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MoreSpongeClient.init();
        MenuScreens.register(MSMenus.FREEZER.get(), FreezerScreen::new);
        ClientRecipeSynchronizedEvent.EVENT.register(this::onRecipesReceived);
    }

    private void onRecipesReceived(Minecraft minecraft, SynchronizedRecipes synchronizedRecipes) {
        MoreSpongeClient.syncedRecipes = RecipeMap.create(synchronizedRecipes.recipes());
    }
}
