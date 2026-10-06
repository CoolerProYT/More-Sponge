package com.coolerpromc.moresponge.config;

import com.coolerpromc.coolerconfig.config.ConfigBuilder;
import com.coolerpromc.coolerconfig.config.ConfigFormat;
import com.coolerpromc.coolerconfig.config.ConfigSpec;
import com.coolerpromc.coolerconfig.config.ConfigValue;
import com.coolerpromc.moresponge.Constants;

public class MSConfig {
    public static ConfigValue<Boolean> spawnSpongeTrader;
    public static ConfigValue<Boolean> enableLootModifier;

    public static ConfigSpec CONFIG;

    public static void init() {
        ConfigBuilder builder = ConfigSpec.builder(Constants.MODID, ConfigFormat.TOML).watchForChanges();

        spawnSpongeTrader = builder.defineBoolean("Sponge Trader.spawnSpongeTrader", true, "Allow sponge traders to spawn naturally");
        enableLootModifier = builder.defineBoolean("Loot.enableLootModifier", true, "Add sponges to vanilla chest and boss loot tables. Requires /reload or a world restart to take effect on Fabric");

        CONFIG = builder.build();
    }

    public static boolean spawnSpongeTrader() {
        return spawnSpongeTrader.get();
    }

    public static boolean enableLootModifier() {
        return enableLootModifier.get();
    }
}
