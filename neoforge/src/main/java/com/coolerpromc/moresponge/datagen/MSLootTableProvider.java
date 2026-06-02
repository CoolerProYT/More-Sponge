package com.coolerpromc.moresponge.datagen;

import com.coolerpromc.moresponge.datagen.loot.MSBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class MSLootTableProvider extends LootTableProvider {
    public MSLootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Set.of(), List.of(new SubProviderEntry(MSBlockLootSubProvider::new, LootContextParamSets.BLOCK)), registries);
    }
}
