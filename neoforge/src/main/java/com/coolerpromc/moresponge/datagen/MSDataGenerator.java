package com.coolerpromc.moresponge.datagen;

import com.coolerpromc.moresponge.Constants;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = Constants.MODID)
public class MSDataGenerator {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event){
        event.createProvider(MSModelProvider::new);
        event.createProvider(MSLanguageProvider::new);
        event.createProvider(MSRecipeProvider.Runner::new);
        event.createProvider(MSBlockTagProvider::new);
        event.createProvider(MSLootTableProvider::new);
    }
}
