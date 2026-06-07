package com.coolerpromc.moresponge.block;

import com.coolerpromc.moresponge.Constants;
import com.coolerpromc.moresponge.block.custom.BurntFireSpongeBlock;
import com.coolerpromc.moresponge.block.custom.FireSpongeBlock;
import com.coolerpromc.moresponge.block.custom.FreezerBlock;
import com.coolerpromc.moresponge.block.custom.FrozenSnowSpongeBlock;
import com.coolerpromc.moresponge.block.custom.LavaSpongeBlock;
import com.coolerpromc.moresponge.block.custom.SnowSpongeBlock;
import com.coolerpromc.moresponge.block.custom.WaterSpongeBlock;
import com.coolerpromc.moresponge.block.custom.HotLavaSpongeBlock;
import com.coolerpromc.moresponge.block.custom.WetWaterSpongeBlock;
import com.coolerpromc.moresponge.platform.Services;
import com.coolerpromc.moresponge.platform.util.RegistryHandler;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class MSBlocks {
    public static RegistryHandler.Blocks<WaterSpongeBlock> COMPRESSED_SPONGE;
    public static RegistryHandler.Blocks<WaterSpongeBlock> COMPRESSED_SPONGE_2X;
    public static RegistryHandler.Blocks<WaterSpongeBlock> COMPRESSED_SPONGE_3X;
    public static RegistryHandler.Blocks<WaterSpongeBlock> COMPRESSED_SPONGE_4X;
    public static RegistryHandler.Blocks<WaterSpongeBlock> COMPRESSED_SPONGE_5X;

    public static RegistryHandler.Blocks<WetWaterSpongeBlock> WET_COMPRESSED_SPONGE;
    public static RegistryHandler.Blocks<WetWaterSpongeBlock> WET_COMPRESSED_SPONGE_2X;
    public static RegistryHandler.Blocks<WetWaterSpongeBlock> WET_COMPRESSED_SPONGE_3X;
    public static RegistryHandler.Blocks<WetWaterSpongeBlock> WET_COMPRESSED_SPONGE_4X;
    public static RegistryHandler.Blocks<WetWaterSpongeBlock> WET_COMPRESSED_SPONGE_5X;

    public static RegistryHandler.Blocks<LavaSpongeBlock> LAVA_SPONGE;
    public static RegistryHandler.Blocks<LavaSpongeBlock> COMPRESSED_LAVA_SPONGE;
    public static RegistryHandler.Blocks<LavaSpongeBlock> COMPRESSED_LAVA_SPONGE_2X;
    public static RegistryHandler.Blocks<LavaSpongeBlock> COMPRESSED_LAVA_SPONGE_3X;
    public static RegistryHandler.Blocks<LavaSpongeBlock> COMPRESSED_LAVA_SPONGE_4X;
    public static RegistryHandler.Blocks<LavaSpongeBlock> COMPRESSED_LAVA_SPONGE_5X;

    public static RegistryHandler.Blocks<HotLavaSpongeBlock> HOT_LAVA_SPONGE;
    public static RegistryHandler.Blocks<HotLavaSpongeBlock> HOT_COMPRESSED_LAVA_SPONGE;
    public static RegistryHandler.Blocks<HotLavaSpongeBlock> HOT_COMPRESSED_LAVA_SPONGE_2X;
    public static RegistryHandler.Blocks<HotLavaSpongeBlock> HOT_COMPRESSED_LAVA_SPONGE_3X;
    public static RegistryHandler.Blocks<HotLavaSpongeBlock> HOT_COMPRESSED_LAVA_SPONGE_4X;
    public static RegistryHandler.Blocks<HotLavaSpongeBlock> HOT_COMPRESSED_LAVA_SPONGE_5X;

    public static RegistryHandler.Blocks<SnowSpongeBlock> SNOW_SPONGE;
    public static RegistryHandler.Blocks<SnowSpongeBlock> COMPRESSED_SNOW_SPONGE;
    public static RegistryHandler.Blocks<SnowSpongeBlock> COMPRESSED_SNOW_SPONGE_2X;
    public static RegistryHandler.Blocks<SnowSpongeBlock> COMPRESSED_SNOW_SPONGE_3X;
    public static RegistryHandler.Blocks<SnowSpongeBlock> COMPRESSED_SNOW_SPONGE_4X;
    public static RegistryHandler.Blocks<SnowSpongeBlock> COMPRESSED_SNOW_SPONGE_5X;

    public static RegistryHandler.Blocks<FrozenSnowSpongeBlock> FROZEN_SNOW_SPONGE;
    public static RegistryHandler.Blocks<FrozenSnowSpongeBlock> FROZEN_COMPRESSED_SNOW_SPONGE;
    public static RegistryHandler.Blocks<FrozenSnowSpongeBlock> FROZEN_COMPRESSED_SNOW_SPONGE_2X;
    public static RegistryHandler.Blocks<FrozenSnowSpongeBlock> FROZEN_COMPRESSED_SNOW_SPONGE_3X;
    public static RegistryHandler.Blocks<FrozenSnowSpongeBlock> FROZEN_COMPRESSED_SNOW_SPONGE_4X;
    public static RegistryHandler.Blocks<FrozenSnowSpongeBlock> FROZEN_COMPRESSED_SNOW_SPONGE_5X;

    public static RegistryHandler.Blocks<FireSpongeBlock> FIRE_SPONGE;
    public static RegistryHandler.Blocks<FireSpongeBlock> COMPRESSED_FIRE_SPONGE;
    public static RegistryHandler.Blocks<FireSpongeBlock> COMPRESSED_FIRE_SPONGE_2X;
    public static RegistryHandler.Blocks<FireSpongeBlock> COMPRESSED_FIRE_SPONGE_3X;
    public static RegistryHandler.Blocks<FireSpongeBlock> COMPRESSED_FIRE_SPONGE_4X;
    public static RegistryHandler.Blocks<FireSpongeBlock> COMPRESSED_FIRE_SPONGE_5X;

    public static RegistryHandler.Blocks<BurntFireSpongeBlock> BURNT_FIRE_SPONGE;
    public static RegistryHandler.Blocks<BurntFireSpongeBlock> BURNT_COMPRESSED_FIRE_SPONGE;
    public static RegistryHandler.Blocks<BurntFireSpongeBlock> BURNT_COMPRESSED_FIRE_SPONGE_2X;
    public static RegistryHandler.Blocks<BurntFireSpongeBlock> BURNT_COMPRESSED_FIRE_SPONGE_3X;
    public static RegistryHandler.Blocks<BurntFireSpongeBlock> BURNT_COMPRESSED_FIRE_SPONGE_4X;
    public static RegistryHandler.Blocks<BurntFireSpongeBlock> BURNT_COMPRESSED_FIRE_SPONGE_5X;

    public static RegistryHandler.Blocks<FreezerBlock> FREEZER;

    static {
        COMPRESSED_SPONGE = registerBlock("compressed_sponge", p -> new WaterSpongeBlock(p, 10, 512, () -> WET_COMPRESSED_SPONGE), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
        COMPRESSED_SPONGE_2X = registerBlock("compressed_sponge_2x", p -> new WaterSpongeBlock(p, 16, 4096, () -> WET_COMPRESSED_SPONGE_2X), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
        COMPRESSED_SPONGE_3X = registerBlock("compressed_sponge_3x", p -> new WaterSpongeBlock(p, 22, 32768, () -> WET_COMPRESSED_SPONGE_3X), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
        COMPRESSED_SPONGE_4X = registerBlock("compressed_sponge_4x", p -> new WaterSpongeBlock(p, 28, 262144, () -> WET_COMPRESSED_SPONGE_4X), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
        COMPRESSED_SPONGE_5X = registerBlock("compressed_sponge_5x", p -> new WaterSpongeBlock(p, 34, 2097152, () -> WET_COMPRESSED_SPONGE_5X), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));

        WET_COMPRESSED_SPONGE = registerBlock("wet_compressed_sponge", p -> new WetWaterSpongeBlock(p, COMPRESSED_SPONGE), BlockBehaviour.Properties.ofFullCopy(Blocks.WET_SPONGE));
        WET_COMPRESSED_SPONGE_2X = registerBlock("wet_compressed_sponge_2x", p -> new WetWaterSpongeBlock(p, COMPRESSED_SPONGE_2X), BlockBehaviour.Properties.ofFullCopy(Blocks.WET_SPONGE));
        WET_COMPRESSED_SPONGE_3X = registerBlock("wet_compressed_sponge_3x", p -> new WetWaterSpongeBlock(p, COMPRESSED_SPONGE_3X), BlockBehaviour.Properties.ofFullCopy(Blocks.WET_SPONGE));
        WET_COMPRESSED_SPONGE_4X = registerBlock("wet_compressed_sponge_4x", p -> new WetWaterSpongeBlock(p, COMPRESSED_SPONGE_4X), BlockBehaviour.Properties.ofFullCopy(Blocks.WET_SPONGE));
        WET_COMPRESSED_SPONGE_5X = registerBlock("wet_compressed_sponge_5x", p -> new WetWaterSpongeBlock(p, COMPRESSED_SPONGE_5X), BlockBehaviour.Properties.ofFullCopy(Blocks.WET_SPONGE));

        LAVA_SPONGE = registerBlock("lava_sponge", p -> new LavaSpongeBlock(p, 6, 65, () -> HOT_LAVA_SPONGE), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
        HOT_LAVA_SPONGE = registerBlock("hot_lava_sponge", p -> new HotLavaSpongeBlock(p, LAVA_SPONGE), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));

        COMPRESSED_LAVA_SPONGE = registerBlock("compressed_lava_sponge", p -> new LavaSpongeBlock(p, 10, 512, () -> HOT_COMPRESSED_LAVA_SPONGE), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
        COMPRESSED_LAVA_SPONGE_2X = registerBlock("compressed_lava_sponge_2x", p -> new LavaSpongeBlock(p, 16, 4096, () -> HOT_COMPRESSED_LAVA_SPONGE_2X), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
        COMPRESSED_LAVA_SPONGE_3X = registerBlock("compressed_lava_sponge_3x", p -> new LavaSpongeBlock(p, 22, 32768, () -> HOT_COMPRESSED_LAVA_SPONGE_3X), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
        COMPRESSED_LAVA_SPONGE_4X = registerBlock("compressed_lava_sponge_4x", p -> new LavaSpongeBlock(p, 28, 262144, () -> HOT_COMPRESSED_LAVA_SPONGE_4X), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
        COMPRESSED_LAVA_SPONGE_5X = registerBlock("compressed_lava_sponge_5x", p -> new LavaSpongeBlock(p, 34, 2097152, () -> HOT_COMPRESSED_LAVA_SPONGE_5X), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));

        HOT_COMPRESSED_LAVA_SPONGE = registerBlock("hot_compressed_lava_sponge", p -> new HotLavaSpongeBlock(p, COMPRESSED_LAVA_SPONGE), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
        HOT_COMPRESSED_LAVA_SPONGE_2X = registerBlock("hot_compressed_lava_sponge_2x", p -> new HotLavaSpongeBlock(p, COMPRESSED_LAVA_SPONGE_2X), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
        HOT_COMPRESSED_LAVA_SPONGE_3X = registerBlock("hot_compressed_lava_sponge_3x", p -> new HotLavaSpongeBlock(p, COMPRESSED_LAVA_SPONGE_3X), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
        HOT_COMPRESSED_LAVA_SPONGE_4X = registerBlock("hot_compressed_lava_sponge_4x", p -> new HotLavaSpongeBlock(p, COMPRESSED_LAVA_SPONGE_4X), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
        HOT_COMPRESSED_LAVA_SPONGE_5X = registerBlock("hot_compressed_lava_sponge_5x", p -> new HotLavaSpongeBlock(p, COMPRESSED_LAVA_SPONGE_5X), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));

        FREEZER = registerBlock("freezer", FreezerBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.FURNACE).mapColor(DyeColor.LIGHT_BLUE));

        SNOW_SPONGE = registerBlock("snow_sponge", p -> new SnowSpongeBlock(p, 7, 125, () -> FROZEN_SNOW_SPONGE), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
        FROZEN_SNOW_SPONGE = registerBlock("frozen_snow_sponge", p -> new FrozenSnowSpongeBlock(p, SNOW_SPONGE), BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK));

        COMPRESSED_SNOW_SPONGE = registerBlock("compressed_snow_sponge", p -> new SnowSpongeBlock(p, 10, 512, () -> FROZEN_COMPRESSED_SNOW_SPONGE), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
        COMPRESSED_SNOW_SPONGE_2X = registerBlock("compressed_snow_sponge_2x", p -> new SnowSpongeBlock(p, 16, 4096, () -> FROZEN_COMPRESSED_SNOW_SPONGE_2X), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
        COMPRESSED_SNOW_SPONGE_3X = registerBlock("compressed_snow_sponge_3x", p -> new SnowSpongeBlock(p, 22, 32768, () -> FROZEN_COMPRESSED_SNOW_SPONGE_3X), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
        COMPRESSED_SNOW_SPONGE_4X = registerBlock("compressed_snow_sponge_4x", p -> new SnowSpongeBlock(p, 28, 262144, () -> FROZEN_COMPRESSED_SNOW_SPONGE_4X), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
        COMPRESSED_SNOW_SPONGE_5X = registerBlock("compressed_snow_sponge_5x", p -> new SnowSpongeBlock(p, 34, 2097152, () -> FROZEN_COMPRESSED_SNOW_SPONGE_5X), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));

        FROZEN_COMPRESSED_SNOW_SPONGE = registerBlock("frozen_compressed_snow_sponge", p -> new FrozenSnowSpongeBlock(p, COMPRESSED_SNOW_SPONGE), BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK));
        FROZEN_COMPRESSED_SNOW_SPONGE_2X = registerBlock("frozen_compressed_snow_sponge_2x", p -> new FrozenSnowSpongeBlock(p, COMPRESSED_SNOW_SPONGE_2X), BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK));
        FROZEN_COMPRESSED_SNOW_SPONGE_3X = registerBlock("frozen_compressed_snow_sponge_3x", p -> new FrozenSnowSpongeBlock(p, COMPRESSED_SNOW_SPONGE_3X), BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK));
        FROZEN_COMPRESSED_SNOW_SPONGE_4X = registerBlock("frozen_compressed_snow_sponge_4x", p -> new FrozenSnowSpongeBlock(p, COMPRESSED_SNOW_SPONGE_4X), BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK));
        FROZEN_COMPRESSED_SNOW_SPONGE_5X = registerBlock("frozen_compressed_snow_sponge_5x", p -> new FrozenSnowSpongeBlock(p, COMPRESSED_SNOW_SPONGE_5X), BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK));

        FIRE_SPONGE = registerBlock("fire_sponge", p -> new FireSpongeBlock(p, 6, 65, () -> BURNT_FIRE_SPONGE), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
        BURNT_FIRE_SPONGE = registerBlock("burnt_fire_sponge", p -> new BurntFireSpongeBlock(p, FIRE_SPONGE), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));

        COMPRESSED_FIRE_SPONGE = registerBlock("compressed_fire_sponge", p -> new FireSpongeBlock(p, 10, 512, () -> BURNT_COMPRESSED_FIRE_SPONGE), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
        COMPRESSED_FIRE_SPONGE_2X = registerBlock("compressed_fire_sponge_2x", p -> new FireSpongeBlock(p, 16, 4096, () -> BURNT_COMPRESSED_FIRE_SPONGE_2X), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
        COMPRESSED_FIRE_SPONGE_3X = registerBlock("compressed_fire_sponge_3x", p -> new FireSpongeBlock(p, 22, 32768, () -> BURNT_COMPRESSED_FIRE_SPONGE_3X), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
        COMPRESSED_FIRE_SPONGE_4X = registerBlock("compressed_fire_sponge_4x", p -> new FireSpongeBlock(p, 28, 262144, () -> BURNT_COMPRESSED_FIRE_SPONGE_4X), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
        COMPRESSED_FIRE_SPONGE_5X = registerBlock("compressed_fire_sponge_5x", p -> new FireSpongeBlock(p, 34, 2097152, () -> BURNT_COMPRESSED_FIRE_SPONGE_5X), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));

        BURNT_COMPRESSED_FIRE_SPONGE = registerBlock("burnt_compressed_fire_sponge", p -> new BurntFireSpongeBlock(p, COMPRESSED_FIRE_SPONGE), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
        BURNT_COMPRESSED_FIRE_SPONGE_2X = registerBlock("burnt_compressed_fire_sponge_2x", p -> new BurntFireSpongeBlock(p, COMPRESSED_FIRE_SPONGE_2X), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
        BURNT_COMPRESSED_FIRE_SPONGE_3X = registerBlock("burnt_compressed_fire_sponge_3x", p -> new BurntFireSpongeBlock(p, COMPRESSED_FIRE_SPONGE_3X), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
        BURNT_COMPRESSED_FIRE_SPONGE_4X = registerBlock("burnt_compressed_fire_sponge_4x", p -> new BurntFireSpongeBlock(p, COMPRESSED_FIRE_SPONGE_4X), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
        BURNT_COMPRESSED_FIRE_SPONGE_5X = registerBlock("burnt_compressed_fire_sponge_5x", p -> new BurntFireSpongeBlock(p, COMPRESSED_FIRE_SPONGE_5X), BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE));
    }

    private static <T extends Block> RegistryHandler.Blocks<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> func, BlockBehaviour.Properties properties){
        RegistryHandler.Blocks<T> blocks = Services.REGISTRY.registerBlock(name, func, properties);
        Services.REGISTRY.registerBlockItem(name, blocks, (block, properties1) -> new BlockItem(block, properties1.useBlockDescriptionPrefix()));
        return blocks;
    }

    public static void init(){
        Constants.LOGGER.info("Registering More Sponge blocks.");
    }
}
