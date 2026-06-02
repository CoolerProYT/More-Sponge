package com.coolerpromc.moresponge.datagen.loot;

import com.coolerpromc.moresponge.Constants;
import com.coolerpromc.moresponge.block.MSBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class MSBlockLootSubProvider extends BlockLootSubProvider {
    public MSBlockLootSubProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(MSBlocks.COMPRESSED_SPONGE.get());
        dropSelf(MSBlocks.COMPRESSED_SPONGE_2X.get());
        dropSelf(MSBlocks.COMPRESSED_SPONGE_3X.get());
        dropSelf(MSBlocks.COMPRESSED_SPONGE_4X.get());
        dropSelf(MSBlocks.COMPRESSED_SPONGE_5X.get());
        dropSelf(MSBlocks.WET_COMPRESSED_SPONGE.get());
        dropSelf(MSBlocks.WET_COMPRESSED_SPONGE_2X.get());
        dropSelf(MSBlocks.WET_COMPRESSED_SPONGE_3X.get());
        dropSelf(MSBlocks.WET_COMPRESSED_SPONGE_4X.get());
        dropSelf(MSBlocks.WET_COMPRESSED_SPONGE_5X.get());
        dropSelf(MSBlocks.LAVA_SPONGE.get());
        dropSelf(MSBlocks.COMPRESSED_LAVA_SPONGE.get());
        dropSelf(MSBlocks.COMPRESSED_LAVA_SPONGE_2X.get());
        dropSelf(MSBlocks.COMPRESSED_LAVA_SPONGE_3X.get());
        dropSelf(MSBlocks.COMPRESSED_LAVA_SPONGE_4X.get());
        dropSelf(MSBlocks.COMPRESSED_LAVA_SPONGE_5X.get());
        dropSelf(MSBlocks.HOT_LAVA_SPONGE.get());
        dropSelf(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE.get());
        dropSelf(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE_2X.get());
        dropSelf(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE_3X.get());
        dropSelf(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE_4X.get());
        dropSelf(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE_5X.get());
        dropSelf(MSBlocks.SNOW_SPONGE.get());
        dropSelf(MSBlocks.COMPRESSED_SNOW_SPONGE.get());
        dropSelf(MSBlocks.COMPRESSED_SNOW_SPONGE_2X.get());
        dropSelf(MSBlocks.COMPRESSED_SNOW_SPONGE_3X.get());
        dropSelf(MSBlocks.COMPRESSED_SNOW_SPONGE_4X.get());
        dropSelf(MSBlocks.COMPRESSED_SNOW_SPONGE_5X.get());
        dropSelf(MSBlocks.FROZEN_SNOW_SPONGE.get());
        dropSelf(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE.get());
        dropSelf(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE_2X.get());
        dropSelf(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE_3X.get());
        dropSelf(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE_4X.get());
        dropSelf(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE_5X.get());
        dropSelf(MSBlocks.FIRE_SPONGE.get());
        dropSelf(MSBlocks.COMPRESSED_FIRE_SPONGE.get());
        dropSelf(MSBlocks.COMPRESSED_FIRE_SPONGE_2X.get());
        dropSelf(MSBlocks.COMPRESSED_FIRE_SPONGE_3X.get());
        dropSelf(MSBlocks.COMPRESSED_FIRE_SPONGE_4X.get());
        dropSelf(MSBlocks.COMPRESSED_FIRE_SPONGE_5X.get());
        dropSelf(MSBlocks.BURNT_FIRE_SPONGE.get());
        dropSelf(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE.get());
        dropSelf(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE_2X.get());
        dropSelf(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE_3X.get());
        dropSelf(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE_4X.get());
        dropSelf(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE_5X.get());
        dropSelf(MSBlocks.FREEZER.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BuiltInRegistries.BLOCK.stream().filter(block -> Optional.of(BuiltInRegistries.BLOCK.getKey(block)).filter(key -> key.getNamespace().equals(Constants.MODID)).isPresent()).collect(Collectors.toSet());
    }
}
