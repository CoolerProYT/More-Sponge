package com.coolerpromc.moresponge.screen;

import com.coolerpromc.moresponge.Constants;
import com.coolerpromc.moresponge.platform.Services;
import com.coolerpromc.moresponge.platform.util.RegistryHandler;
import com.coolerpromc.moresponge.screen.custom.FreezerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;

public class MSMenus {
    public static final RegistryHandler<MenuType<?>, MenuType<FreezerMenu>> FREEZER = Services.REGISTRY.registerMenuType("freezer", FreezerMenu::new, BlockPos.STREAM_CODEC);

    public static void init() {
        Constants.LOGGER.info("Registering More Sponge menus.");
    }
}
