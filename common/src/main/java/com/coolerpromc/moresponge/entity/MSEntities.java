package com.coolerpromc.moresponge.entity;

import com.coolerpromc.moresponge.Constants;
import com.coolerpromc.moresponge.entity.custom.SpongeTrader;
import com.coolerpromc.moresponge.platform.Services;
import com.coolerpromc.moresponge.platform.util.RegistryHandler;
import net.minecraft.world.entity.MobCategory;

public class MSEntities {
    public static final RegistryHandler.Entities<SpongeTrader> WATER_SPONGE_TRADER = registerTrader("water_sponge_trader", SpongeTrader.Type.WATER);
    public static final RegistryHandler.Entities<SpongeTrader> LAVA_SPONGE_TRADER = registerTrader("lava_sponge_trader", SpongeTrader.Type.LAVA);
    public static final RegistryHandler.Entities<SpongeTrader> FIRE_SPONGE_TRADER = registerTrader("fire_sponge_trader", SpongeTrader.Type.FIRE);
    public static final RegistryHandler.Entities<SpongeTrader> SNOW_SPONGE_TRADER = registerTrader("snow_sponge_trader", SpongeTrader.Type.SNOW);

    private static RegistryHandler.Entities<SpongeTrader> registerTrader(String name, SpongeTrader.Type type){
        return Services.REGISTRY.registerEntity(name, (entityType, level) -> new SpongeTrader(entityType, level, type), MobCategory.CREATURE, builder -> builder.sized(0.6F, 1.95F).eyeHeight(1.62F).clientTrackingRange(10));
    }

    public static void init() {
        Constants.LOGGER.info("Registering More Sponge entities.");
    }
}
