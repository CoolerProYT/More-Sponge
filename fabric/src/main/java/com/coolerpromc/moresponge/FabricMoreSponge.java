package com.coolerpromc.moresponge;

import com.coolerpromc.moresponge.block.MSBlocks;
import com.coolerpromc.moresponge.block.entity.MSBlockEntities;
import com.coolerpromc.moresponge.block.entity.custom.FreezerBlockEntity;
import com.coolerpromc.moresponge.recipe.MSRecipes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.recipe.v1.sync.RecipeSynchronization;
import net.fabricmc.fabric.api.transfer.v1.item.ContainerStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.CombinedSlottedStorage;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class FabricMoreSponge implements ModInitializer {
    private static final Map<Item, Set<Identifier>> LOOT_MAP = Map.of(
            MSBlocks.SNOW_SPONGE.asItem(), Set.of(
                    Identifier.withDefaultNamespace("chests/ancient_city_ice_box"),
                    Identifier.withDefaultNamespace("chests/igloo_chest"),
                    Identifier.withDefaultNamespace("chests/village/village_snowy_house")
            ),
            MSBlocks.LAVA_SPONGE.asItem(), Set.of(
                    Identifier.withDefaultNamespace("chests/bastion_bridge"),
                    Identifier.withDefaultNamespace("chests/bastion_hoglin_stable"),
                    Identifier.withDefaultNamespace("chests/bastion_other"),
                    Identifier.withDefaultNamespace("chests/bastion_treasure"),
                    Identifier.withDefaultNamespace("chests/nether_bridge")
            ),
            MSBlocks.FIRE_SPONGE.asItem(), Set.of(
                    Identifier.withDefaultNamespace("chests/ruined_portal"),
                    Identifier.withDefaultNamespace("chests/desert_pyramid"),
                    Identifier.withDefaultNamespace("chests/village/village_desert_house")
            ),
            Items.SPONGE, Set.of(
                    Identifier.withDefaultNamespace("chests/buried_treasure"),
                    Identifier.withDefaultNamespace("chests/shipwreck_map"),
                    Identifier.withDefaultNamespace("chests/shipwreck_supply"),
                    Identifier.withDefaultNamespace("chests/shipwreck_treasure")
            )
    );

    private static final Map<Item, Set<Identifier>> ENTITY_LOOT_MAP = Map.of(
            MSBlocks.COMPRESSED_SNOW_SPONGE_2X.asItem(), Set.of(
                    Identifier.withDefaultNamespace("entities/warden")
            ),
            MSBlocks.COMPRESSED_LAVA_SPONGE_2X.asItem(), Set.of(
                    Identifier.withDefaultNamespace("entities/ender_dragon")
            ),
            MSBlocks.COMPRESSED_FIRE_SPONGE_2X.asItem(), Set.of(
                    Identifier.withDefaultNamespace("entities/wither")
            ),
            MSBlocks.COMPRESSED_SPONGE_2X.asItem(), Set.of(
                    Identifier.withDefaultNamespace("entities/elder_guardian")
            )
    );
    
    @Override
    public void onInitialize() {
        MoreSponge.init();
        ItemStorage.SIDED.registerForBlockEntity(this::onRegisterCapabilities, MSBlockEntities.FREEZER.get());
        RecipeSynchronization.synchronizeRecipeSerializer(MSRecipes.FREEZER_SERIALIZER.get());

        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            Identifier id = key.identifier();

            LOOT_MAP.forEach((item, tables) -> {
                if (tables.contains(id)) {
                    tableBuilder.withPool(buildPool(item, 0, 4));
                }
            });

            ENTITY_LOOT_MAP.forEach((item, tables) -> {
                if (tables.contains(id)) {
                    tableBuilder.withPool(buildPool(item, 1, 2));
                }
            });
        });
    }

    private Storage<ItemVariant> onRegisterCapabilities(FreezerBlockEntity blockEntity, @Nullable Direction direction) {
        SimpleContainer container = blockEntity.getCapabilityBySide(direction);
        if (container == null){
            return new CombinedSlottedStorage<>(List.of(ContainerStorage.of(blockEntity.getInputSlot(), null), ContainerStorage.of(blockEntity.getFuelSlot(), null), ContainerStorage.of(blockEntity.getResultSlot(), null)));
        }
        return ContainerStorage.of(container, direction);
    }

    private LootPool.Builder buildPool(Item item, int min, int max) {
        return LootPool.lootPool()
                .setRolls(UniformGenerator.between(1, 1))
                .add(LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                );
    }
}
