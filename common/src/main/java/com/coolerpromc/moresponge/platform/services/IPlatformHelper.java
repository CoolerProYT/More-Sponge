package com.coolerpromc.moresponge.platform.services;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;

public interface IPlatformHelper {
    String getPlatformName();
    boolean isModLoaded(String modId);
    boolean isDevelopmentEnvironment();
    default String getEnvironmentName() {
        return isDevelopmentEnvironment() ? "development" : "production";
    }
    void openMenu(ServerPlayer player, MenuProvider provider, BlockPos pos);
}