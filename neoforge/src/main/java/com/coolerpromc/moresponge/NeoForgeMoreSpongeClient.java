package com.coolerpromc.moresponge;

import com.coolerpromc.moresponge.screen.MSMenus;
import com.coolerpromc.moresponge.screen.custom.FreezerScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RecipesReceivedEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = Constants.MODID, dist = Dist.CLIENT)
public class NeoForgeMoreSpongeClient {
    public NeoForgeMoreSpongeClient(IEventBus eventBus) {
        MoreSpongeClient.init();

        eventBus.addListener(this::onRegisterMenuScreens);
        NeoForge.EVENT_BUS.addListener(this::onRecipesReceived);
    }

    public void onRegisterMenuScreens(RegisterMenuScreensEvent event) {
        event.register(MSMenus.FREEZER.get(), FreezerScreen::new);
    }

    public void onRecipesReceived(RecipesReceivedEvent event) {
        MoreSpongeClient.syncedRecipes = event.getRecipeMap();
    }
}
