package com.coolerpromc.moresponge.trade;

import com.coolerpromc.moresponge.Constants;
import com.coolerpromc.moresponge.block.MSBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;

public class MSVillagerTrades {
    // SPONGE_TRADER_BUYING
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_SPONGE_EMERALD = resourceKey("sponge_emerald");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_WET_SPONGE_EMERALD = resourceKey("wet_sponge_emerald");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_COMPRESSED_SPONGE_EMERALD = resourceKey("compressed_sponge_emerald");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_WET_COMPRESSED_SPONGE_EMERALD = resourceKey("wet_compressed_sponge_emerald");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_LAVA_SPONGE_EMERALD = resourceKey("lava_sponge_emerald");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_HOT_LAVA_SPONGE_EMERALD = resourceKey("hot_lava_sponge_emerald");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_COMPRESSED_LAVA_SPONGE_EMERALD = resourceKey("compressed_lava_sponge_emerald");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_HOT_COMPRESSED_LAVA_SPONGE_EMERALD = resourceKey("hot_compressed_lava_sponge_emerald");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_SNOW_SPONGE_EMERALD = resourceKey("snow_sponge_emerald");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_FROZEN_SNOW_SPONGE_EMERALD = resourceKey("frozen_snow_sponge_emerald");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_COMPRESSED_SNOW_SPONGE_EMERALD = resourceKey("compressed_snow_sponge_emerald");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_FROZEN_COMPRESSED_SNOW_SPONGE_EMERALD = resourceKey("frozen_compressed_snow_sponge_emerald");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_FIRE_SPONGE_EMERALD = resourceKey("fire_sponge_emerald");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_BURNT_FIRE_SPONGE_EMERALD = resourceKey("burnt_fire_sponge_emerald");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_COMPRESSED_FIRE_SPONGE_EMERALD = resourceKey("compressed_fire_sponge_emerald");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_BURNT_COMPRESSED_FIRE_SPONGE_EMERALD = resourceKey("burnt_compressed_fire_sponge_emerald");

    // SPONGE_TRADER_COMMON - base and compressed (1x) of each type
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_SPONGE = resourceKey("emerald_sponge");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_COMPRESSED_SPONGE = resourceKey("emerald_compressed_sponge");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_WET_SPONGE = resourceKey("emerald_wet_sponge");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_WET_COMPRESSED_SPONGE = resourceKey("emerald_wet_compressed_sponge");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_LAVA_SPONGE = resourceKey("emerald_lava_sponge");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_COMPRESSED_LAVA_SPONGE = resourceKey("emerald_compressed_lava_sponge");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_HOT_LAVA_SPONGE = resourceKey("emerald_hot_lava_sponge");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_HOT_COMPRESSED_LAVA_SPONGE = resourceKey("emerald_hot_compressed_lava_sponge");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_SNOW_SPONGE = resourceKey("emerald_snow_sponge");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_COMPRESSED_SNOW_SPONGE = resourceKey("emerald_compressed_snow_sponge");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_FROZEN_SNOW_SPONGE = resourceKey("emerald_frozen_snow_sponge");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_FROZEN_COMPRESSED_SNOW_SPONGE = resourceKey("emerald_frozen_compressed_snow_sponge");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_FIRE_SPONGE = resourceKey("emerald_fire_sponge");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_COMPRESSED_FIRE_SPONGE = resourceKey("emerald_compressed_fire_sponge");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_BURNT_FIRE_SPONGE = resourceKey("emerald_burnt_fire_sponge");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_BURNT_COMPRESSED_FIRE_SPONGE = resourceKey("emerald_burnt_compressed_fire_sponge");

    // SPONGE_TRADER_UNCOMMON - 2x to 5x of each type
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_COMPRESSED_SPONGE_2X = resourceKey("emerald_compressed_sponge_2x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_COMPRESSED_SPONGE_3X = resourceKey("emerald_compressed_sponge_3x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_COMPRESSED_SPONGE_4X = resourceKey("emerald_compressed_sponge_4x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_COMPRESSED_SPONGE_5X = resourceKey("emerald_compressed_sponge_5x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_WET_COMPRESSED_SPONGE_2X = resourceKey("emerald_wet_compressed_sponge_2x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_WET_COMPRESSED_SPONGE_3X = resourceKey("emerald_wet_compressed_sponge_3x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_WET_COMPRESSED_SPONGE_4X = resourceKey("emerald_wet_compressed_sponge_4x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_WET_COMPRESSED_SPONGE_5X = resourceKey("emerald_wet_compressed_sponge_5x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_COMPRESSED_LAVA_SPONGE_2X = resourceKey("emerald_compressed_lava_sponge_2x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_COMPRESSED_LAVA_SPONGE_3X = resourceKey("emerald_compressed_lava_sponge_3x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_COMPRESSED_LAVA_SPONGE_4X = resourceKey("emerald_compressed_lava_sponge_4x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_COMPRESSED_LAVA_SPONGE_5X = resourceKey("emerald_compressed_lava_sponge_5x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_HOT_COMPRESSED_LAVA_SPONGE_2X = resourceKey("emerald_hot_compressed_lava_sponge_2x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_HOT_COMPRESSED_LAVA_SPONGE_3X = resourceKey("emerald_hot_compressed_lava_sponge_3x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_HOT_COMPRESSED_LAVA_SPONGE_4X = resourceKey("emerald_hot_compressed_lava_sponge_4x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_HOT_COMPRESSED_LAVA_SPONGE_5X = resourceKey("emerald_hot_compressed_lava_sponge_5x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_COMPRESSED_SNOW_SPONGE_2X = resourceKey("emerald_compressed_snow_sponge_2x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_COMPRESSED_SNOW_SPONGE_3X = resourceKey("emerald_compressed_snow_sponge_3x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_COMPRESSED_SNOW_SPONGE_4X = resourceKey("emerald_compressed_snow_sponge_4x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_COMPRESSED_SNOW_SPONGE_5X = resourceKey("emerald_compressed_snow_sponge_5x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_FROZEN_COMPRESSED_SNOW_SPONGE_2X = resourceKey("emerald_frozen_compressed_snow_sponge_2x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_FROZEN_COMPRESSED_SNOW_SPONGE_3X = resourceKey("emerald_frozen_compressed_snow_sponge_3x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_FROZEN_COMPRESSED_SNOW_SPONGE_4X = resourceKey("emerald_frozen_compressed_snow_sponge_4x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_FROZEN_COMPRESSED_SNOW_SPONGE_5X = resourceKey("emerald_frozen_compressed_snow_sponge_5x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_COMPRESSED_FIRE_SPONGE_2X = resourceKey("emerald_compressed_fire_sponge_2x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_COMPRESSED_FIRE_SPONGE_3X = resourceKey("emerald_compressed_fire_sponge_3x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_COMPRESSED_FIRE_SPONGE_4X = resourceKey("emerald_compressed_fire_sponge_4x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_COMPRESSED_FIRE_SPONGE_5X = resourceKey("emerald_compressed_fire_sponge_5x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_BURNT_COMPRESSED_FIRE_SPONGE_2X = resourceKey("emerald_burnt_compressed_fire_sponge_2x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_BURNT_COMPRESSED_FIRE_SPONGE_3X = resourceKey("emerald_burnt_compressed_fire_sponge_3x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_BURNT_COMPRESSED_FIRE_SPONGE_4X = resourceKey("emerald_burnt_compressed_fire_sponge_4x");
    public static final ResourceKey<VillagerTrade> SPONGE_TRADER_EMERALD_BURNT_COMPRESSED_FIRE_SPONGE_5X = resourceKey("emerald_burnt_compressed_fire_sponge_5x");

    public static void bootstrap(BootstrapContext<VillagerTrade> context) {
        // BUYING
        register(context, SPONGE_TRADER_SPONGE_EMERALD, trade(new TradeCost(Items.SPONGE, 4), new ItemStackTemplate(Items.EMERALD, 1), 5, 1, 0.05F));
        register(context, SPONGE_TRADER_WET_SPONGE_EMERALD, trade(new TradeCost(Items.WET_SPONGE, 4), new ItemStackTemplate(Items.EMERALD, 1), 5, 1, 0.05F));
        register(context, SPONGE_TRADER_COMPRESSED_SPONGE_EMERALD, trade(new TradeCost(MSBlocks.COMPRESSED_SPONGE, 1), new ItemStackTemplate(Items.EMERALD, 5), 5, 1, 0.05F));
        register(context, SPONGE_TRADER_WET_COMPRESSED_SPONGE_EMERALD, trade(new TradeCost(MSBlocks.WET_COMPRESSED_SPONGE, 1), new ItemStackTemplate(Items.EMERALD, 2), 5, 1, 0.05F));
        register(context, SPONGE_TRADER_LAVA_SPONGE_EMERALD, trade(new TradeCost(MSBlocks.LAVA_SPONGE, 1), new ItemStackTemplate(Items.EMERALD, 8), 5, 1, 0.05F));
        register(context, SPONGE_TRADER_HOT_LAVA_SPONGE_EMERALD, trade(new TradeCost(MSBlocks.HOT_LAVA_SPONGE, 1), new ItemStackTemplate(Items.EMERALD, 4), 5, 1, 0.05F));
        register(context, SPONGE_TRADER_COMPRESSED_LAVA_SPONGE_EMERALD, trade(new TradeCost(MSBlocks.COMPRESSED_LAVA_SPONGE, 1), new ItemStackTemplate(Items.EMERALD, 10), 5, 1, 0.05F));
        register(context, SPONGE_TRADER_HOT_COMPRESSED_LAVA_SPONGE_EMERALD, trade(new TradeCost(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE, 1), new ItemStackTemplate(Items.EMERALD, 5), 5, 1, 0.05F));
        register(context, SPONGE_TRADER_SNOW_SPONGE_EMERALD, trade(new TradeCost(MSBlocks.SNOW_SPONGE, 1), new ItemStackTemplate(Items.EMERALD, 6), 5, 1, 0.05F));
        register(context, SPONGE_TRADER_FROZEN_SNOW_SPONGE_EMERALD, trade(new TradeCost(MSBlocks.FROZEN_SNOW_SPONGE, 1), new ItemStackTemplate(Items.EMERALD, 3), 5, 1, 0.05F));
        register(context, SPONGE_TRADER_COMPRESSED_SNOW_SPONGE_EMERALD, trade(new TradeCost(MSBlocks.COMPRESSED_SNOW_SPONGE, 1), new ItemStackTemplate(Items.EMERALD, 8), 5, 1, 0.05F));
        register(context, SPONGE_TRADER_FROZEN_COMPRESSED_SNOW_SPONGE_EMERALD, trade(new TradeCost(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE, 1), new ItemStackTemplate(Items.EMERALD, 4), 5, 1, 0.05F));
        register(context, SPONGE_TRADER_FIRE_SPONGE_EMERALD, trade(new TradeCost(MSBlocks.FIRE_SPONGE, 1), new ItemStackTemplate(Items.EMERALD, 6), 5, 1, 0.05F));
        register(context, SPONGE_TRADER_BURNT_FIRE_SPONGE_EMERALD, trade(new TradeCost(MSBlocks.BURNT_FIRE_SPONGE, 1), new ItemStackTemplate(Items.EMERALD, 3), 5, 1, 0.05F));
        register(context, SPONGE_TRADER_COMPRESSED_FIRE_SPONGE_EMERALD, trade(new TradeCost(MSBlocks.COMPRESSED_FIRE_SPONGE, 1), new ItemStackTemplate(Items.EMERALD, 8), 5, 1, 0.05F));
        register(context, SPONGE_TRADER_BURNT_COMPRESSED_FIRE_SPONGE_EMERALD, trade(new TradeCost(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE, 1), new ItemStackTemplate(Items.EMERALD, 4), 5, 1, 0.05F));

        // COMMON
        register(context, SPONGE_TRADER_EMERALD_SPONGE, trade(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(Items.SPONGE, 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_WET_SPONGE, trade(new TradeCost(Items.EMERALD, 3), new ItemStackTemplate(Items.WET_SPONGE, 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_COMPRESSED_SPONGE, trade(new TradeCost(Items.EMERALD, 20), new ItemStackTemplate(MSBlocks.COMPRESSED_SPONGE.asItem(), 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_WET_COMPRESSED_SPONGE, trade(new TradeCost(Items.EMERALD, 12), new ItemStackTemplate(MSBlocks.WET_COMPRESSED_SPONGE.asItem(), 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_LAVA_SPONGE, trade(new TradeCost(Items.EMERALD, 18), new ItemStackTemplate(MSBlocks.LAVA_SPONGE.asItem(), 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_HOT_LAVA_SPONGE, trade(new TradeCost(Items.EMERALD, 10), new ItemStackTemplate(MSBlocks.HOT_LAVA_SPONGE.asItem(), 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_COMPRESSED_LAVA_SPONGE, trade(new TradeCost(Items.EMERALD, 28), new ItemStackTemplate(MSBlocks.COMPRESSED_LAVA_SPONGE.asItem(), 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_HOT_COMPRESSED_LAVA_SPONGE, trade(new TradeCost(Items.EMERALD, 16), new ItemStackTemplate(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE.asItem(), 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_SNOW_SPONGE, trade(new TradeCost(Items.EMERALD, 14), new ItemStackTemplate(MSBlocks.SNOW_SPONGE.asItem(), 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_FROZEN_SNOW_SPONGE, trade(new TradeCost(Items.EMERALD, 8), new ItemStackTemplate(MSBlocks.FROZEN_SNOW_SPONGE.asItem(), 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_COMPRESSED_SNOW_SPONGE, trade(new TradeCost(Items.EMERALD, 22), new ItemStackTemplate(MSBlocks.COMPRESSED_SNOW_SPONGE.asItem(), 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_FROZEN_COMPRESSED_SNOW_SPONGE, trade(new TradeCost(Items.EMERALD, 12), new ItemStackTemplate(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE.asItem(), 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_FIRE_SPONGE, trade(new TradeCost(Items.EMERALD, 14), new ItemStackTemplate(MSBlocks.FIRE_SPONGE.asItem(), 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_BURNT_FIRE_SPONGE, trade(new TradeCost(Items.EMERALD, 8), new ItemStackTemplate(MSBlocks.BURNT_FIRE_SPONGE.asItem(), 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_COMPRESSED_FIRE_SPONGE, trade(new TradeCost(Items.EMERALD, 22), new ItemStackTemplate(MSBlocks.COMPRESSED_FIRE_SPONGE.asItem(), 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_BURNT_COMPRESSED_FIRE_SPONGE, trade(new TradeCost(Items.EMERALD, 12), new ItemStackTemplate(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE.asItem(), 1), 2, 1, 0.05F));

        // UNCOMMON
        register(context, SPONGE_TRADER_EMERALD_COMPRESSED_SPONGE_2X, trade(new TradeCost(Items.EMERALD, 35), new ItemStackTemplate(MSBlocks.COMPRESSED_SPONGE_2X.asItem(), 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_COMPRESSED_SPONGE_3X, trade(new TradeCost(Items.EMERALD, 55), new ItemStackTemplate(MSBlocks.COMPRESSED_SPONGE_3X.asItem(), 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_COMPRESSED_SPONGE_4X, trade(new TradeCost(Items.EMERALD_BLOCK, 5), new ItemStackTemplate(MSBlocks.COMPRESSED_SPONGE_4X.asItem(), 1), 1, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_COMPRESSED_SPONGE_5X, trade(new TradeCost(Items.EMERALD_BLOCK, 10), new ItemStackTemplate(MSBlocks.COMPRESSED_SPONGE_5X.asItem(), 1), 1, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_WET_COMPRESSED_SPONGE_2X, trade(new TradeCost(Items.EMERALD, 20), new ItemStackTemplate(MSBlocks.WET_COMPRESSED_SPONGE_2X.asItem(), 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_WET_COMPRESSED_SPONGE_3X, trade(new TradeCost(Items.EMERALD, 32), new ItemStackTemplate(MSBlocks.WET_COMPRESSED_SPONGE_3X.asItem(), 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_WET_COMPRESSED_SPONGE_4X, trade(new TradeCost(Items.EMERALD, 48), new ItemStackTemplate(MSBlocks.WET_COMPRESSED_SPONGE_4X.asItem(), 1), 1, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_WET_COMPRESSED_SPONGE_5X, trade(new TradeCost(Items.EMERALD_BLOCK, 6), new ItemStackTemplate(MSBlocks.WET_COMPRESSED_SPONGE_5X.asItem(), 1), 1, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_COMPRESSED_LAVA_SPONGE_2X, trade(new TradeCost(Items.EMERALD, 42), new ItemStackTemplate(MSBlocks.COMPRESSED_LAVA_SPONGE_2X.asItem(), 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_COMPRESSED_LAVA_SPONGE_3X, trade(new TradeCost(Items.EMERALD, 60), new ItemStackTemplate(MSBlocks.COMPRESSED_LAVA_SPONGE_3X.asItem(), 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_COMPRESSED_LAVA_SPONGE_4X, trade(new TradeCost(Items.EMERALD_BLOCK, 6), new ItemStackTemplate(MSBlocks.COMPRESSED_LAVA_SPONGE_4X.asItem(), 1), 1, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_COMPRESSED_LAVA_SPONGE_5X, trade(new TradeCost(Items.EMERALD_BLOCK, 12), new ItemStackTemplate(MSBlocks.COMPRESSED_LAVA_SPONGE_5X.asItem(), 1), 1, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_HOT_COMPRESSED_LAVA_SPONGE_2X, trade(new TradeCost(Items.EMERALD, 24), new ItemStackTemplate(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE_2X.asItem(), 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_HOT_COMPRESSED_LAVA_SPONGE_3X, trade(new TradeCost(Items.EMERALD, 38), new ItemStackTemplate(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE_3X.asItem(), 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_HOT_COMPRESSED_LAVA_SPONGE_4X, trade(new TradeCost(Items.EMERALD, 52), new ItemStackTemplate(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE_4X.asItem(), 1), 1, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_HOT_COMPRESSED_LAVA_SPONGE_5X, trade(new TradeCost(Items.EMERALD_BLOCK, 7), new ItemStackTemplate(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE_5X.asItem(), 1), 1, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_COMPRESSED_SNOW_SPONGE_2X, trade(new TradeCost(Items.EMERALD, 36), new ItemStackTemplate(MSBlocks.COMPRESSED_SNOW_SPONGE_2X.asItem(), 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_COMPRESSED_SNOW_SPONGE_3X, trade(new TradeCost(Items.EMERALD, 52), new ItemStackTemplate(MSBlocks.COMPRESSED_SNOW_SPONGE_3X.asItem(), 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_COMPRESSED_SNOW_SPONGE_4X, trade(new TradeCost(Items.EMERALD_BLOCK, 5), new ItemStackTemplate(MSBlocks.COMPRESSED_SNOW_SPONGE_4X.asItem(), 1), 1, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_COMPRESSED_SNOW_SPONGE_5X, trade(new TradeCost(Items.EMERALD_BLOCK, 9), new ItemStackTemplate(MSBlocks.COMPRESSED_SNOW_SPONGE_5X.asItem(), 1), 1, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_FROZEN_COMPRESSED_SNOW_SPONGE_2X, trade(new TradeCost(Items.EMERALD, 20), new ItemStackTemplate(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE_2X.asItem(), 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_FROZEN_COMPRESSED_SNOW_SPONGE_3X, trade(new TradeCost(Items.EMERALD, 32), new ItemStackTemplate(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE_3X.asItem(), 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_FROZEN_COMPRESSED_SNOW_SPONGE_4X, trade(new TradeCost(Items.EMERALD, 46), new ItemStackTemplate(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE_4X.asItem(), 1), 1, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_FROZEN_COMPRESSED_SNOW_SPONGE_5X, trade(new TradeCost(Items.EMERALD_BLOCK, 6), new ItemStackTemplate(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE_5X.asItem(), 1), 1, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_COMPRESSED_FIRE_SPONGE_2X, trade(new TradeCost(Items.EMERALD, 36), new ItemStackTemplate(MSBlocks.COMPRESSED_FIRE_SPONGE_2X.asItem(), 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_COMPRESSED_FIRE_SPONGE_3X, trade(new TradeCost(Items.EMERALD, 52), new ItemStackTemplate(MSBlocks.COMPRESSED_FIRE_SPONGE_3X.asItem(), 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_COMPRESSED_FIRE_SPONGE_4X, trade(new TradeCost(Items.EMERALD_BLOCK, 5), new ItemStackTemplate(MSBlocks.COMPRESSED_FIRE_SPONGE_4X.asItem(), 1), 1, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_COMPRESSED_FIRE_SPONGE_5X, trade(new TradeCost(Items.EMERALD_BLOCK, 9), new ItemStackTemplate(MSBlocks.COMPRESSED_FIRE_SPONGE_5X.asItem(), 1), 1, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_BURNT_COMPRESSED_FIRE_SPONGE_2X, trade(new TradeCost(Items.EMERALD, 20), new ItemStackTemplate(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE_2X.asItem(), 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_BURNT_COMPRESSED_FIRE_SPONGE_3X, trade(new TradeCost(Items.EMERALD, 32), new ItemStackTemplate(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE_3X.asItem(), 1), 2, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_BURNT_COMPRESSED_FIRE_SPONGE_4X, trade(new TradeCost(Items.EMERALD, 46), new ItemStackTemplate(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE_4X.asItem(), 1), 1, 1, 0.05F));
        register(context, SPONGE_TRADER_EMERALD_BURNT_COMPRESSED_FIRE_SPONGE_5X, trade(new TradeCost(Items.EMERALD_BLOCK, 6), new ItemStackTemplate(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE_5X.asItem(), 1), 1, 1, 0.05F));
    }

    public static VillagerTrade trade(TradeCost wants, ItemStackTemplate gives, int maxUses, int xp, float reputationDiscount){
        return VillagerTrade.builder(wants, gives, maxUses, xp, reputationDiscount).build();
    }

    public static void register(BootstrapContext<VillagerTrade> context, ResourceKey<VillagerTrade> resourceKey, VillagerTrade villagerTrade) {
        context.register(resourceKey, villagerTrade);
    }

    public static ResourceKey<VillagerTrade> resourceKey(String path) {
        return ResourceKey.create(Registries.VILLAGER_TRADE, Constants.id("sponge_trader/" + path));
    }
}
