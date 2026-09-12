package com.coolerpromc.moresponge.datagen;

import com.coolerpromc.moresponge.Constants;
import com.coolerpromc.moresponge.trade.MSTradeSets;
import com.coolerpromc.moresponge.trade.MSVillagerTrades;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;

@EventBusSubscriber(modid = Constants.MODID)
public class MSDataGenerator {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        event.createProvider(MSModelProvider::new);
        event.createProvider(MSLanguageProvider::new);
        event.createReloadableRegistryObjects(new RegistrySetBuilder()
                .add(Registries.LOOT_TABLE, new MSLootTableProvider())
                .add(RecipeProvider.asBootstrap(MSRecipeProvider::new)),
            Set.of("minecraft", Constants.MODID)
        );
        event.createWorldRegistryObjects(new RegistrySetBuilder()
                .add(Registries.VILLAGER_TRADE, MSVillagerTrades::bootstrap)
                .add(Registries.TRADE_SET, MSTradeSets::bootstrap),
            Set.of("minecraft", Constants.MODID)
        );
        event.createProvider(MSBlockTagProvider::new);
        event.createProvider(MSVillagerTradeTagProvider::new);
        event.createProvider(MSBiomeTagProvider::new);
    }

    @SubscribeEvent
    public static void gatherServer(GatherDataEvent.Server event) {
        event.createProvider(MSGlobalLootModifierProvider::new);
    }
}
