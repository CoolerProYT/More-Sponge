package com.coolerpromc.moresponge.tag;

import com.coolerpromc.moresponge.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class MSBiomeTags {
    public static final TagKey<Biome> CAN_SPAWN_WATER_SPONGE_TRADER = create("can_spawn_water_sponge_trader");
    public static final TagKey<Biome> CAN_SPAWN_LAVA_SPONGE_TRADER = create("can_spawn_lava_sponge_trader");
    public static final TagKey<Biome> CAN_SPAWN_FIRE_SPONGE_TRADER = create("can_spawn_fire_sponge_trader");
    public static final TagKey<Biome> CAN_SPAWN_SNOW_SPONGE_TRADER = create("can_spawn_snow_sponge_trader");

    private static TagKey<Biome> create(String name) {
        return TagKey.create(Registries.BIOME, Constants.id(name));
    }
}
