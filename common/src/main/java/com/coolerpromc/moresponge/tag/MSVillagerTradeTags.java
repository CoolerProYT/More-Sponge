package com.coolerpromc.moresponge.tag;

import com.coolerpromc.moresponge.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.trading.VillagerTrade;

public class MSVillagerTradeTags {
    public static final TagKey<VillagerTrade> SPONGE_TRADER_WATER_BUYING = create("sponge_trader/water_buying");
    public static final TagKey<VillagerTrade> SPONGE_TRADER_WATER_UNCOMMON = create("sponge_trader/water_uncommon");
    public static final TagKey<VillagerTrade> SPONGE_TRADER_WATER_COMMON = create("sponge_trader/water_common");
    public static final TagKey<VillagerTrade> SPONGE_TRADER_LAVA_BUYING = create("sponge_trader/lava_buying");
    public static final TagKey<VillagerTrade> SPONGE_TRADER_LAVA_UNCOMMON = create("sponge_trader/lava_uncommon");
    public static final TagKey<VillagerTrade> SPONGE_TRADER_LAVA_COMMON = create("sponge_trader/lava_common");
    public static final TagKey<VillagerTrade> SPONGE_TRADER_FIRE_BUYING = create("sponge_trader/fire_buying");
    public static final TagKey<VillagerTrade> SPONGE_TRADER_FIRE_UNCOMMON = create("sponge_trader/fire_uncommon");
    public static final TagKey<VillagerTrade> SPONGE_TRADER_FIRE_COMMON = create("sponge_trader/fire_common");
    public static final TagKey<VillagerTrade> SPONGE_TRADER_SNOW_BUYING = create("sponge_trader/snow_buying");
    public static final TagKey<VillagerTrade> SPONGE_TRADER_SNOW_UNCOMMON = create("sponge_trader/snow_uncommon");
    public static final TagKey<VillagerTrade> SPONGE_TRADER_SNOW_COMMON = create("sponge_trader/snow_common");

    private static TagKey<VillagerTrade> create(String name) {
        return TagKey.create(Registries.VILLAGER_TRADE, Constants.id(name));
    }
}
