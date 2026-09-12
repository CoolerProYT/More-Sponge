package com.coolerpromc.moresponge.trade;

import com.coolerpromc.moresponge.Constants;
import com.coolerpromc.moresponge.tag.MSVillagerTradeTags;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.trading.TradeSet;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProvider;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

import java.util.Optional;

public class MSTradeSets {
    public static final ResourceKey<TradeSet> SPONGE_TRADER_WATER_BUYING = resourceKey("sponge_trader/water_buying");
    public static final ResourceKey<TradeSet> SPONGE_TRADER_WATER_COMMON = resourceKey("sponge_trader/water_common");
    public static final ResourceKey<TradeSet> SPONGE_TRADER_WATER_UNCOMMON = resourceKey("sponge_trader/water_uncommon");
    public static final ResourceKey<TradeSet> SPONGE_TRADER_LAVA_BUYING = resourceKey("sponge_trader/lava_buying");
    public static final ResourceKey<TradeSet> SPONGE_TRADER_LAVA_COMMON = resourceKey("sponge_trader/lava_common");
    public static final ResourceKey<TradeSet> SPONGE_TRADER_LAVA_UNCOMMON = resourceKey("sponge_trader/lava_uncommon");
    public static final ResourceKey<TradeSet> SPONGE_TRADER_FIRE_BUYING = resourceKey("sponge_trader/fire_buying");
    public static final ResourceKey<TradeSet> SPONGE_TRADER_FIRE_COMMON = resourceKey("sponge_trader/fire_common");
    public static final ResourceKey<TradeSet> SPONGE_TRADER_FIRE_UNCOMMON = resourceKey("sponge_trader/fire_uncommon");
    public static final ResourceKey<TradeSet> SPONGE_TRADER_SNOW_BUYING = resourceKey("sponge_trader/snow_buying");
    public static final ResourceKey<TradeSet> SPONGE_TRADER_SNOW_COMMON = resourceKey("sponge_trader/snow_common");
    public static final ResourceKey<TradeSet> SPONGE_TRADER_SNOW_UNCOMMON = resourceKey("sponge_trader/snow_uncommon");

    public static void bootstrap(BootstrapContext<TradeSet> context) {
        register(context, SPONGE_TRADER_WATER_BUYING, MSVillagerTradeTags.SPONGE_TRADER_WATER_BUYING);
        register(context, SPONGE_TRADER_WATER_COMMON, MSVillagerTradeTags.SPONGE_TRADER_WATER_COMMON);
        register(context, SPONGE_TRADER_WATER_UNCOMMON, MSVillagerTradeTags.SPONGE_TRADER_WATER_UNCOMMON, ContextIntProviders.exactly(1));
        register(context, SPONGE_TRADER_LAVA_BUYING, MSVillagerTradeTags.SPONGE_TRADER_LAVA_BUYING);
        register(context, SPONGE_TRADER_LAVA_COMMON, MSVillagerTradeTags.SPONGE_TRADER_LAVA_COMMON);
        register(context, SPONGE_TRADER_LAVA_UNCOMMON, MSVillagerTradeTags.SPONGE_TRADER_LAVA_UNCOMMON, ContextIntProviders.exactly(1));
        register(context, SPONGE_TRADER_FIRE_BUYING, MSVillagerTradeTags.SPONGE_TRADER_FIRE_BUYING);
        register(context, SPONGE_TRADER_FIRE_COMMON, MSVillagerTradeTags.SPONGE_TRADER_FIRE_COMMON);
        register(context, SPONGE_TRADER_FIRE_UNCOMMON, MSVillagerTradeTags.SPONGE_TRADER_FIRE_UNCOMMON, ContextIntProviders.exactly(1));
        register(context, SPONGE_TRADER_SNOW_BUYING, MSVillagerTradeTags.SPONGE_TRADER_SNOW_BUYING);
        register(context, SPONGE_TRADER_SNOW_COMMON, MSVillagerTradeTags.SPONGE_TRADER_SNOW_COMMON);
        register(context, SPONGE_TRADER_SNOW_UNCOMMON, MSVillagerTradeTags.SPONGE_TRADER_SNOW_UNCOMMON, ContextIntProviders.exactly(1));
    }

    public static void register(BootstrapContext<TradeSet> context, ResourceKey<TradeSet> resourceKey, TagKey<VillagerTrade> tradeTag) {
        register(context, resourceKey, tradeTag, ContextIntProviders.exactly(2));
    }

    public static void register(BootstrapContext<TradeSet> context, ResourceKey<TradeSet> resourceKey, TagKey<VillagerTrade> tradeTag, Holder<ContextIntProvider> numberProvider) {
        context.register(resourceKey, new TradeSet(context.lookup(Registries.VILLAGER_TRADE).getOrThrow(tradeTag), numberProvider, false, Optional.of(resourceKey.identifier().withPrefix("trade_set/"))));
    }

    public static ResourceKey<TradeSet> resourceKey(String path) {
        return ResourceKey.create(Registries.TRADE_SET, Constants.id(path));
    }
}
