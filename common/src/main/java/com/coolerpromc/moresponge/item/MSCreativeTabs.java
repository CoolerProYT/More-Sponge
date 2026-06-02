package com.coolerpromc.moresponge.item;

import com.coolerpromc.moresponge.Constants;
import com.coolerpromc.moresponge.block.MSBlocks;
import com.coolerpromc.moresponge.platform.Services;
import com.coolerpromc.moresponge.platform.util.RegistryHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;

public class MSCreativeTabs {
    public static final RegistryHandler<CreativeModeTab, CreativeModeTab> MORE_SPONGE = Services.REGISTRY.registerCreativeTab(
        "more_sponge",
            Items.SPONGE::getDefaultInstance,
            Component.translatable("itemGroup.more_sponge"),
            (output, parameters) -> {
                output.accept(MSBlocks.FREEZER);
                output.accept(MSBlocks.COMPRESSED_SPONGE);
                output.accept(MSBlocks.WET_COMPRESSED_SPONGE);
                output.accept(MSBlocks.COMPRESSED_SPONGE_2X);
                output.accept(MSBlocks.WET_COMPRESSED_SPONGE_2X);
                output.accept(MSBlocks.COMPRESSED_SPONGE_3X);
                output.accept(MSBlocks.WET_COMPRESSED_SPONGE_3X);
                output.accept(MSBlocks.COMPRESSED_SPONGE_4X);
                output.accept(MSBlocks.WET_COMPRESSED_SPONGE_4X);
                output.accept(MSBlocks.COMPRESSED_SPONGE_5X);
                output.accept(MSBlocks.WET_COMPRESSED_SPONGE_5X);
                output.accept(MSBlocks.LAVA_SPONGE);
                output.accept(MSBlocks.HOT_LAVA_SPONGE);
                output.accept(MSBlocks.COMPRESSED_LAVA_SPONGE);
                output.accept(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE);
                output.accept(MSBlocks.COMPRESSED_LAVA_SPONGE_2X);
                output.accept(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE_2X);
                output.accept(MSBlocks.COMPRESSED_LAVA_SPONGE_3X);
                output.accept(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE_3X);
                output.accept(MSBlocks.COMPRESSED_LAVA_SPONGE_4X);
                output.accept(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE_4X);
                output.accept(MSBlocks.COMPRESSED_LAVA_SPONGE_5X);
                output.accept(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE_5X);
                output.accept(MSBlocks.SNOW_SPONGE);
                output.accept(MSBlocks.FROZEN_SNOW_SPONGE);
                output.accept(MSBlocks.COMPRESSED_SNOW_SPONGE);
                output.accept(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE);
                output.accept(MSBlocks.COMPRESSED_SNOW_SPONGE_2X);
                output.accept(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE_2X);
                output.accept(MSBlocks.COMPRESSED_SNOW_SPONGE_3X);
                output.accept(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE_3X);
                output.accept(MSBlocks.COMPRESSED_SNOW_SPONGE_4X);
                output.accept(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE_4X);
                output.accept(MSBlocks.COMPRESSED_SNOW_SPONGE_5X);
                output.accept(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE_5X);
                output.accept(MSBlocks.FIRE_SPONGE);
                output.accept(MSBlocks.BURNT_FIRE_SPONGE);
                output.accept(MSBlocks.COMPRESSED_FIRE_SPONGE);
                output.accept(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE);
                output.accept(MSBlocks.COMPRESSED_FIRE_SPONGE_2X);
                output.accept(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE_2X);
                output.accept(MSBlocks.COMPRESSED_FIRE_SPONGE_3X);
                output.accept(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE_3X);
                output.accept(MSBlocks.COMPRESSED_FIRE_SPONGE_4X);
                output.accept(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE_4X);
                output.accept(MSBlocks.COMPRESSED_FIRE_SPONGE_5X);
                output.accept(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE_5X);
            }
    );

    public static void init() {
        Constants.LOGGER.info("Registering More Sponge Creative Tabs.");
    }
}
