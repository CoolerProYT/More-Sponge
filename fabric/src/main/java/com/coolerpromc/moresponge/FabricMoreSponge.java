package com.coolerpromc.moresponge;

import com.coolerpromc.moresponge.block.entity.MSBlockEntities;
import com.coolerpromc.moresponge.block.entity.custom.FreezerBlockEntity;
import com.coolerpromc.moresponge.recipe.MSRecipes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.recipe.v1.sync.RecipeSynchronization;
import net.fabricmc.fabric.api.transfer.v1.item.ContainerStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.CombinedSlottedStorage;
import net.minecraft.core.Direction;
import net.minecraft.world.SimpleContainer;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class FabricMoreSponge implements ModInitializer {
    @Override
    public void onInitialize() {
        MoreSponge.init();
        ItemStorage.SIDED.registerForBlockEntity(this::onRegisterCapabilities, MSBlockEntities.FREEZER.get());
        RecipeSynchronization.synchronizeRecipeSerializer(MSRecipes.FREEZER_SERIALIZER.get());
    }

    private Storage<ItemVariant> onRegisterCapabilities(FreezerBlockEntity blockEntity, @Nullable Direction direction) {
        SimpleContainer container = blockEntity.getCapabilityBySide(direction);
        if (container == null){
            return new CombinedSlottedStorage<>(List.of(ContainerStorage.of(blockEntity.getInputSlot(), null), ContainerStorage.of(blockEntity.getFuelSlot(), null), ContainerStorage.of(blockEntity.getResultSlot(), null)));
        }
        return ContainerStorage.of(container, direction);
    }
}
