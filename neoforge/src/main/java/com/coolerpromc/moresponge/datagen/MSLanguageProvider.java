package com.coolerpromc.moresponge.datagen;

import com.coolerpromc.moresponge.Constants;
import com.coolerpromc.moresponge.block.MSBlocks;
import com.coolerpromc.moresponge.entity.MSEntities;
import com.coolerpromc.moresponge.item.MSItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class MSLanguageProvider extends LanguageProvider {
    public MSLanguageProvider(PackOutput output) {
        super(output, Constants.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.more_sponge", "More Sponge");
        add("gui.jei.category.freezing", "Freezing");
        add("gui.jei.category.freezing_fuel", "Freezing Fuel");
        add("gui.jei.category.fuel.freezeCount.single", "Freezes 1 item");
        add("gui.jei.category.fuel.freezeCount", "Freezes %s items");

        add(MSBlocks.FREEZER.get(), "Freezer");

        add(MSBlocks.COMPRESSED_SPONGE.get(), "Compressed Sponge 1x");
        add(MSBlocks.COMPRESSED_SPONGE_2X.get(), "Compressed Sponge 2x");
        add(MSBlocks.COMPRESSED_SPONGE_3X.get(), "Compressed Sponge 3x");
        add(MSBlocks.COMPRESSED_SPONGE_4X.get(), "Compressed Sponge 4x");
        add(MSBlocks.COMPRESSED_SPONGE_5X.get(), "Compressed Sponge 5x");

        add(MSBlocks.WET_COMPRESSED_SPONGE.get(), "Wet Compressed Sponge 1x");
        add(MSBlocks.WET_COMPRESSED_SPONGE_2X.get(), "Wet Compressed Sponge 2x");
        add(MSBlocks.WET_COMPRESSED_SPONGE_3X.get(), "Wet Compressed Sponge 3x");
        add(MSBlocks.WET_COMPRESSED_SPONGE_4X.get(), "Wet Compressed Sponge 4x");
        add(MSBlocks.WET_COMPRESSED_SPONGE_5X.get(), "Wet Compressed Sponge 5x");

        add(MSBlocks.LAVA_SPONGE.get(), "Lava Sponge");
        add(MSBlocks.HOT_LAVA_SPONGE.get(), "Hot Lava Sponge");

        add(MSBlocks.COMPRESSED_LAVA_SPONGE.get(), "Compressed Lava Sponge 1x");
        add(MSBlocks.COMPRESSED_LAVA_SPONGE_2X.get(), "Compressed Lava Sponge 2x");
        add(MSBlocks.COMPRESSED_LAVA_SPONGE_3X.get(), "Compressed Lava Sponge 3x");
        add(MSBlocks.COMPRESSED_LAVA_SPONGE_4X.get(), "Compressed Lava Sponge 4x");
        add(MSBlocks.COMPRESSED_LAVA_SPONGE_5X.get(), "Compressed Lava Sponge 5x");

        add(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE.get(), "Hot Compressed Lava Sponge 1x");
        add(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE_2X.get(), "Hot Compressed Lava Sponge 2x");
        add(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE_3X.get(), "Hot Compressed Lava Sponge 3x");
        add(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE_4X.get(), "Hot Compressed Lava Sponge 4x");
        add(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE_5X.get(), "Hot Compressed Lava Sponge 5x");

        add(MSBlocks.SNOW_SPONGE.get(), "Snow Sponge");
        add(MSBlocks.FROZEN_SNOW_SPONGE.get(), "Frozen Snow Sponge");

        add(MSBlocks.COMPRESSED_SNOW_SPONGE.get(), "Compressed Snow Sponge 1x");
        add(MSBlocks.COMPRESSED_SNOW_SPONGE_2X.get(), "Compressed Snow Sponge 2x");
        add(MSBlocks.COMPRESSED_SNOW_SPONGE_3X.get(), "Compressed Snow Sponge 3x");
        add(MSBlocks.COMPRESSED_SNOW_SPONGE_4X.get(), "Compressed Snow Sponge 4x");
        add(MSBlocks.COMPRESSED_SNOW_SPONGE_5X.get(), "Compressed Snow Sponge 5x");

        add(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE.get(), "Frozen Compressed Snow Sponge 1x");
        add(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE_2X.get(), "Frozen Compressed Snow Sponge 2x");
        add(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE_3X.get(), "Frozen Compressed Snow Sponge 3x");
        add(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE_4X.get(), "Frozen Compressed Snow Sponge 4x");
        add(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE_5X.get(), "Frozen Compressed Snow Sponge 5x");

        add(MSBlocks.FIRE_SPONGE.get(), "Fire Sponge");
        add(MSBlocks.BURNT_FIRE_SPONGE.get(), "Burnt Fire Sponge");

        add(MSBlocks.COMPRESSED_FIRE_SPONGE.get(), "Compressed Fire Sponge 1x");
        add(MSBlocks.COMPRESSED_FIRE_SPONGE_2X.get(), "Compressed Fire Sponge 2x");
        add(MSBlocks.COMPRESSED_FIRE_SPONGE_3X.get(), "Compressed Fire Sponge 3x");
        add(MSBlocks.COMPRESSED_FIRE_SPONGE_4X.get(), "Compressed Fire Sponge 4x");
        add(MSBlocks.COMPRESSED_FIRE_SPONGE_5X.get(), "Compressed Fire Sponge 5x");

        add(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE.get(), "Burnt Compressed Fire Sponge 1x");
        add(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE_2X.get(), "Burnt Compressed Fire Sponge 2x");
        add(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE_3X.get(), "Burnt Compressed Fire Sponge 3x");
        add(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE_4X.get(), "Burnt Compressed Fire Sponge 4x");
        add(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE_5X.get(), "Burnt Compressed Fire Sponge 5x");

        add(MSEntities.WATER_SPONGE_TRADER.get(), "Water Sponge Trader");
        add(MSEntities.LAVA_SPONGE_TRADER.get(), "Lava Sponge Trader");
        add(MSEntities.FIRE_SPONGE_TRADER.get(), "Fire Sponge Trader");
        add(MSEntities.SNOW_SPONGE_TRADER.get(), "Snow Sponge Trader");

        add(MSItems.WATER_SPONGE_TRADER_SPAWN_EGG.get(), "Water Sponge Trader Spawn Egg");
        add(MSItems.LAVA_SPONGE_TRADER_SPAWN_EGG.get(), "Lava Sponge Trader Spawn Egg");
        add(MSItems.FIRE_SPONGE_TRADER_SPAWN_EGG.get(), "Fire Sponge Trader Spawn Egg");
        add(MSItems.SNOW_SPONGE_TRADER_SPAWN_EGG.get(), "Snow Sponge Trader Spawn Egg");

        add("gamerule.moresponge.spawn_sponge_traders", "Spawn Sponge Traders");
    }
}
