package com.coolerpromc.moresponge;

import com.coolerpromc.moresponge.entity.MSEntities;
import com.coolerpromc.moresponge.entity.renderer.SpongeTraderRenderer;
import com.coolerpromc.moresponge.screen.MSMenus;
import com.coolerpromc.moresponge.screen.custom.FreezerScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RecipesReceivedEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = Constants.MODID, dist = Dist.CLIENT)
public class NeoForgeMoreSpongeClient {
    public NeoForgeMoreSpongeClient(IEventBus eventBus) {
        MoreSpongeClient.init();

        eventBus.addListener(this::onRegisterMenuScreens);
        eventBus.addListener(this::onRegisterEntityRenderers);
        NeoForge.EVENT_BUS.addListener(this::onRecipesReceived);
    }

    public void onRegisterMenuScreens(RegisterMenuScreensEvent event) {
        event.register(MSMenus.FREEZER.get(), FreezerScreen::new);
    }

    public void onRecipesReceived(RecipesReceivedEvent event) {
        MoreSpongeClient.syncedRecipes = event.getRecipeMap();
    }

    public void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(MSEntities.WATER_SPONGE_TRADER.get(), SpongeTraderRenderer::new);
        event.registerEntityRenderer(MSEntities.LAVA_SPONGE_TRADER.get(), SpongeTraderRenderer::new);
        event.registerEntityRenderer(MSEntities.FIRE_SPONGE_TRADER.get(), SpongeTraderRenderer::new);
        event.registerEntityRenderer(MSEntities.SNOW_SPONGE_TRADER.get(), SpongeTraderRenderer::new);
    }
}
