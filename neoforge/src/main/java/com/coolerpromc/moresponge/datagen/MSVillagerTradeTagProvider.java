package com.coolerpromc.moresponge.datagen;

import com.coolerpromc.moresponge.tag.MSVillagerTradeTags;
import com.coolerpromc.moresponge.trade.MSVillagerTrades;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.VillagerTradesTagsProvider;

import java.util.concurrent.CompletableFuture;

public class MSVillagerTradeTagProvider extends VillagerTradesTagsProvider {
    public MSVillagerTradeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(MSVillagerTradeTags.SPONGE_TRADER_WATER_BUYING).add(
                MSVillagerTrades.SPONGE_TRADER_SPONGE_EMERALD,
                MSVillagerTrades.SPONGE_TRADER_WET_SPONGE_EMERALD,
                MSVillagerTrades.SPONGE_TRADER_COMPRESSED_SPONGE_EMERALD,
                MSVillagerTrades.SPONGE_TRADER_WET_COMPRESSED_SPONGE_EMERALD
        );

        tag(MSVillagerTradeTags.SPONGE_TRADER_WATER_COMMON).add(
                MSVillagerTrades.SPONGE_TRADER_EMERALD_SPONGE,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_WET_SPONGE,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_COMPRESSED_SPONGE,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_WET_COMPRESSED_SPONGE
        );

        tag(MSVillagerTradeTags.SPONGE_TRADER_WATER_UNCOMMON).add(
                MSVillagerTrades.SPONGE_TRADER_EMERALD_COMPRESSED_SPONGE_2X,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_COMPRESSED_SPONGE_3X,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_COMPRESSED_SPONGE_4X,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_COMPRESSED_SPONGE_5X,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_WET_COMPRESSED_SPONGE_2X,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_WET_COMPRESSED_SPONGE_3X,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_WET_COMPRESSED_SPONGE_4X,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_WET_COMPRESSED_SPONGE_5X
        );

        tag(MSVillagerTradeTags.SPONGE_TRADER_LAVA_BUYING).add(
                MSVillagerTrades.SPONGE_TRADER_LAVA_SPONGE_EMERALD,
                MSVillagerTrades.SPONGE_TRADER_HOT_LAVA_SPONGE_EMERALD,
                MSVillagerTrades.SPONGE_TRADER_COMPRESSED_LAVA_SPONGE_EMERALD,
                MSVillagerTrades.SPONGE_TRADER_HOT_COMPRESSED_LAVA_SPONGE_EMERALD
        );

        tag(MSVillagerTradeTags.SPONGE_TRADER_LAVA_COMMON).add(
                MSVillagerTrades.SPONGE_TRADER_EMERALD_LAVA_SPONGE,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_HOT_LAVA_SPONGE,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_COMPRESSED_LAVA_SPONGE,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_HOT_COMPRESSED_LAVA_SPONGE
        );

        tag(MSVillagerTradeTags.SPONGE_TRADER_LAVA_UNCOMMON).add(
                MSVillagerTrades.SPONGE_TRADER_EMERALD_COMPRESSED_LAVA_SPONGE_2X,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_COMPRESSED_LAVA_SPONGE_3X,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_COMPRESSED_LAVA_SPONGE_4X,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_COMPRESSED_LAVA_SPONGE_5X,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_HOT_COMPRESSED_LAVA_SPONGE_2X,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_HOT_COMPRESSED_LAVA_SPONGE_3X,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_HOT_COMPRESSED_LAVA_SPONGE_4X,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_HOT_COMPRESSED_LAVA_SPONGE_5X
        );

        tag(MSVillagerTradeTags.SPONGE_TRADER_FIRE_BUYING).add(
                MSVillagerTrades.SPONGE_TRADER_FIRE_SPONGE_EMERALD,
                MSVillagerTrades.SPONGE_TRADER_BURNT_FIRE_SPONGE_EMERALD,
                MSVillagerTrades.SPONGE_TRADER_COMPRESSED_FIRE_SPONGE_EMERALD,
                MSVillagerTrades.SPONGE_TRADER_BURNT_COMPRESSED_FIRE_SPONGE_EMERALD
        );

        tag(MSVillagerTradeTags.SPONGE_TRADER_FIRE_COMMON).add(
                MSVillagerTrades.SPONGE_TRADER_EMERALD_FIRE_SPONGE,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_BURNT_FIRE_SPONGE,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_COMPRESSED_FIRE_SPONGE,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_BURNT_COMPRESSED_FIRE_SPONGE
        );

        tag(MSVillagerTradeTags.SPONGE_TRADER_FIRE_UNCOMMON).add(
                MSVillagerTrades.SPONGE_TRADER_EMERALD_COMPRESSED_FIRE_SPONGE_2X,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_COMPRESSED_FIRE_SPONGE_3X,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_COMPRESSED_FIRE_SPONGE_4X,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_COMPRESSED_FIRE_SPONGE_5X,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_BURNT_COMPRESSED_FIRE_SPONGE_2X,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_BURNT_COMPRESSED_FIRE_SPONGE_3X,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_BURNT_COMPRESSED_FIRE_SPONGE_4X,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_BURNT_COMPRESSED_FIRE_SPONGE_5X
        );

        tag(MSVillagerTradeTags.SPONGE_TRADER_SNOW_BUYING).add(
                MSVillagerTrades.SPONGE_TRADER_SNOW_SPONGE_EMERALD,
                MSVillagerTrades.SPONGE_TRADER_FROZEN_SNOW_SPONGE_EMERALD,
                MSVillagerTrades.SPONGE_TRADER_COMPRESSED_SNOW_SPONGE_EMERALD,
                MSVillagerTrades.SPONGE_TRADER_FROZEN_COMPRESSED_SNOW_SPONGE_EMERALD
        );

        tag(MSVillagerTradeTags.SPONGE_TRADER_SNOW_COMMON).add(
                MSVillagerTrades.SPONGE_TRADER_EMERALD_SNOW_SPONGE,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_FROZEN_SNOW_SPONGE,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_COMPRESSED_SNOW_SPONGE,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_FROZEN_COMPRESSED_SNOW_SPONGE
        );

        tag(MSVillagerTradeTags.SPONGE_TRADER_SNOW_UNCOMMON).add(
                MSVillagerTrades.SPONGE_TRADER_EMERALD_COMPRESSED_SNOW_SPONGE_2X,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_COMPRESSED_SNOW_SPONGE_3X,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_COMPRESSED_SNOW_SPONGE_4X,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_COMPRESSED_SNOW_SPONGE_5X,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_FROZEN_COMPRESSED_SNOW_SPONGE_2X,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_FROZEN_COMPRESSED_SNOW_SPONGE_3X,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_FROZEN_COMPRESSED_SNOW_SPONGE_4X,
                MSVillagerTrades.SPONGE_TRADER_EMERALD_FROZEN_COMPRESSED_SNOW_SPONGE_5X
        );
    }
}
