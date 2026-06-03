package com.coolerpromc.moresponge.datagen;

import com.coolerpromc.moresponge.Constants;
import com.coolerpromc.moresponge.block.MSBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class MSBlockTagProvider extends BlockTagsProvider {
    public MSBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Constants.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_HOE).add(
                MSBlocks.COMPRESSED_SPONGE.key(),
                MSBlocks.COMPRESSED_SPONGE_2X.key(),
                MSBlocks.COMPRESSED_SPONGE_3X.key(),
                MSBlocks.COMPRESSED_SPONGE_4X.key(),
                MSBlocks.COMPRESSED_SPONGE_5X.key(),
                MSBlocks.WET_COMPRESSED_SPONGE.key(),
                MSBlocks.WET_COMPRESSED_SPONGE_2X.key(),
                MSBlocks.WET_COMPRESSED_SPONGE_3X.key(),
                MSBlocks.WET_COMPRESSED_SPONGE_4X.key(),
                MSBlocks.WET_COMPRESSED_SPONGE_5X.key(),
                MSBlocks.LAVA_SPONGE.key(),
                MSBlocks.COMPRESSED_LAVA_SPONGE.key(),
                MSBlocks.COMPRESSED_LAVA_SPONGE_2X.key(),
                MSBlocks.COMPRESSED_LAVA_SPONGE_3X.key(),
                MSBlocks.COMPRESSED_LAVA_SPONGE_4X.key(),
                MSBlocks.COMPRESSED_LAVA_SPONGE_5X.key(),
                MSBlocks.HOT_LAVA_SPONGE.key(),
                MSBlocks.HOT_COMPRESSED_LAVA_SPONGE.key(),
                MSBlocks.HOT_COMPRESSED_LAVA_SPONGE_2X.key(),
                MSBlocks.HOT_COMPRESSED_LAVA_SPONGE_3X.key(),
                MSBlocks.HOT_COMPRESSED_LAVA_SPONGE_4X.key(),
                MSBlocks.HOT_COMPRESSED_LAVA_SPONGE_5X.key(),
                MSBlocks.SNOW_SPONGE.key(),
                MSBlocks.COMPRESSED_SNOW_SPONGE.key(),
                MSBlocks.COMPRESSED_SNOW_SPONGE_2X.key(),
                MSBlocks.COMPRESSED_SNOW_SPONGE_3X.key(),
                MSBlocks.COMPRESSED_SNOW_SPONGE_4X.key(),
                MSBlocks.COMPRESSED_SNOW_SPONGE_5X.key(),
                MSBlocks.FROZEN_SNOW_SPONGE.key(),
                MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE.key(),
                MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE_2X.key(),
                MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE_3X.key(),
                MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE_4X.key(),
                MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE_5X.key(),
                MSBlocks.FIRE_SPONGE.key(),
                MSBlocks.COMPRESSED_FIRE_SPONGE.key(),
                MSBlocks.COMPRESSED_FIRE_SPONGE_2X.key(),
                MSBlocks.COMPRESSED_FIRE_SPONGE_3X.key(),
                MSBlocks.COMPRESSED_FIRE_SPONGE_4X.key(),
                MSBlocks.COMPRESSED_FIRE_SPONGE_5X.key(),
                MSBlocks.BURNT_FIRE_SPONGE.key(),
                MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE.key(),
                MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE_2X.key(),
                MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE_3X.key(),
                MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE_4X.key(),
                MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE_5X.key()
        );

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MSBlocks.FREEZER.key());
    }
}
