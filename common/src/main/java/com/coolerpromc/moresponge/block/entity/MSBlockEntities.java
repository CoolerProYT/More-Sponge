package com.coolerpromc.moresponge.block.entity;

import com.coolerpromc.moresponge.Constants;
import com.coolerpromc.moresponge.block.MSBlocks;
import com.coolerpromc.moresponge.block.entity.custom.FreezerBlockEntity;
import com.coolerpromc.moresponge.platform.util.RegistryHandler;
import com.coolerpromc.moresponge.platform.Services;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class MSBlockEntities {
    public static final RegistryHandler<BlockEntityType<?>, BlockEntityType<FreezerBlockEntity>> FREEZER = Services.REGISTRY.registerBlockEntityType("freeezer", FreezerBlockEntity::new, MSBlocks.FREEZER);

    public static void init() {
        Constants.LOGGER.info("Registering More Sponge block entities.");
    }
}
