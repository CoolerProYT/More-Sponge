package com.coolerpromc.moresponge.gamerule;

import com.coolerpromc.moresponge.Constants;
import com.coolerpromc.moresponge.platform.Services;
import com.coolerpromc.moresponge.platform.util.RegistryHandler;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.serialization.Codec;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import net.minecraft.world.level.gamerules.GameRuleType;
import net.minecraft.world.level.gamerules.GameRuleTypeVisitor;

public class MSGameRules {
    public static final RegistryHandler<GameRule<?>, GameRule<Boolean>> SPAWN_SPONGE_TRADERS = Services.REGISTRY.registerGameRule("spawn_sponge_traders",
            new GameRule<>(GameRuleCategory.SPAWNING, GameRuleType.BOOL, BoolArgumentType.bool(), GameRuleTypeVisitor::visitBoolean, Codec.BOOL, (b) -> b ? 1 : 0, true, FeatureFlagSet.of()));

    public static void init() {
        Constants.LOGGER.info("Registering More Sponge Game Rules.");
    }
}
