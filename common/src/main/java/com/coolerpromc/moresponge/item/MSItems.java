package com.coolerpromc.moresponge.item;

import com.coolerpromc.moresponge.Constants;
import com.coolerpromc.moresponge.entity.MSEntities;
import com.coolerpromc.moresponge.platform.Services;
import com.coolerpromc.moresponge.platform.util.RegistryHandler;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

import java.util.function.Function;

public class MSItems {
    public static final RegistryHandler.Items<SpawnEggItem> WATER_SPONGE_TRADER_SPAWN_EGG = registerItem("water_sponge_trader_spawn_egg", p -> new SpawnEggItem(p.spawnEgg(MSEntities.WATER_SPONGE_TRADER.get())));
    public static final RegistryHandler.Items<SpawnEggItem> LAVA_SPONGE_TRADER_SPAWN_EGG = registerItem("lava_sponge_trader_spawn_egg", p -> new SpawnEggItem(p.spawnEgg(MSEntities.LAVA_SPONGE_TRADER.get())));
    public static final RegistryHandler.Items<SpawnEggItem> FIRE_SPONGE_TRADER_SPAWN_EGG = registerItem("fire_sponge_trader_spawn_egg", p -> new SpawnEggItem(p.spawnEgg(MSEntities.FIRE_SPONGE_TRADER.get())));
    public static final RegistryHandler.Items<SpawnEggItem> SNOW_SPONGE_TRADER_SPAWN_EGG = registerItem("snow_sponge_trader_spawn_egg", p -> new SpawnEggItem(p.spawnEgg(MSEntities.SNOW_SPONGE_TRADER.get())));

    public static <T extends Item> RegistryHandler.Items<T> registerItem(String name, Function<Item.Properties, T> function){
        return Services.REGISTRY.registerItem(name, function);
    }

    public static void init() {
        Constants.LOGGER.info("Registering More Sponge Items.");
    }
}
