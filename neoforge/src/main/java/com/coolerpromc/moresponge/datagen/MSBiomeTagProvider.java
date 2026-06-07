package com.coolerpromc.moresponge.datagen;

import com.coolerpromc.moresponge.Constants;
import com.coolerpromc.moresponge.tag.MSBiomeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;

import java.util.concurrent.CompletableFuture;

public class MSBiomeTagProvider extends BiomeTagsProvider {
    public MSBiomeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Constants.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(MSBiomeTags.CAN_SPAWN_WATER_SPONGE_TRADER).addTags(BiomeTags.IS_OCEAN, BiomeTags.IS_DEEP_OCEAN, BiomeTags.IS_RIVER, BiomeTags.IS_BEACH, BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS);
        tag(MSBiomeTags.CAN_SPAWN_FIRE_SPONGE_TRADER).addTag(BiomeTags.IS_BADLANDS).add(Biomes.DESERT);
        tag(MSBiomeTags.CAN_SPAWN_LAVA_SPONGE_TRADER).addTag(BiomeTags.IS_NETHER);
        tag(MSBiomeTags.CAN_SPAWN_SNOW_SPONGE_TRADER).addTag(BiomeTags.SPAWNS_SNOW_FOXES);
    }
}
