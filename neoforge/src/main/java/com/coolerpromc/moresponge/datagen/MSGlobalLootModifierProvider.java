package com.coolerpromc.moresponge.datagen;

import com.coolerpromc.moresponge.Constants;
import com.coolerpromc.moresponge.block.MSBlocks;
import com.coolerpromc.moresponge.datagen.loot.MSLootModifier;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.concurrent.CompletableFuture;

public class MSGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIER_SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Constants.MODID);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<MSLootModifier>> MS_LOOT_MODIFIER = GLOBAL_LOOT_MODIFIER_SERIALIZERS.register("ms_loot_modifier", () -> MSLootModifier.CODEC);

    public MSGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, Constants.MODID);
    }

    @Override
    protected void start() {
        this.add("snow_sponge_chest_loot_modifier", new MSLootModifier(new LootItemCondition[]{
                AnyOfCondition.anyOf(
                        new LootTableIdCondition.Builder(Identifier.withDefaultNamespace("chests/ancient_city_ice_box")),
                        new LootTableIdCondition.Builder(Identifier.withDefaultNamespace("chests/igloo_chest")),
                        new LootTableIdCondition.Builder(Identifier.withDefaultNamespace("chests/village/village_snowy_house"))
                ).build()
        }, MSBlocks.SNOW_SPONGE.asItem(), 0, 4));

        this.add("lava_sponge_chest_loot_modifier", new MSLootModifier(new LootItemCondition[]{
                AnyOfCondition.anyOf(
                        new LootTableIdCondition.Builder(Identifier.withDefaultNamespace("chests/bastion_bridge")),
                        new LootTableIdCondition.Builder(Identifier.withDefaultNamespace("chests/bastion_hoglin_stable")),
                        new LootTableIdCondition.Builder(Identifier.withDefaultNamespace("chests/bastion_other")),
                        new LootTableIdCondition.Builder(Identifier.withDefaultNamespace("chests/bastion_treasure")),
                        new LootTableIdCondition.Builder(Identifier.withDefaultNamespace("chests/nether_bridge"))
                ).build()
        }, MSBlocks.LAVA_SPONGE.asItem(), 0, 4));

        this.add("fire_sponge_chest_loot_modifier", new MSLootModifier(new LootItemCondition[]{
                AnyOfCondition.anyOf(
                        new LootTableIdCondition.Builder(Identifier.withDefaultNamespace("chests/ruined_portal")),
                        new LootTableIdCondition.Builder(Identifier.withDefaultNamespace("chests/desert_pyramid")),
                        new LootTableIdCondition.Builder(Identifier.withDefaultNamespace("chests/village/village_desert_house"))
                ).build()
        }, MSBlocks.FIRE_SPONGE.asItem(), 0, 4));

        this.add("water_sponge_chest_loot_modifier", new MSLootModifier(new LootItemCondition[]{
                AnyOfCondition.anyOf(
                        new LootTableIdCondition.Builder(Identifier.withDefaultNamespace("chests/buried_treasure")),
                        new LootTableIdCondition.Builder(Identifier.withDefaultNamespace("chests/shipwreck_map")),
                        new LootTableIdCondition.Builder(Identifier.withDefaultNamespace("chests/shipwreck_supply")),
                        new LootTableIdCondition.Builder(Identifier.withDefaultNamespace("chests/shipwreck_treasure"))
                ).build()
        }, Items.SPONGE, 0, 4));

        this.add("snow_sponge_entity_loot_modifier", new MSLootModifier(new LootItemCondition[]{
                AnyOfCondition.anyOf(
                        new LootTableIdCondition.Builder(Identifier.withDefaultNamespace("entities/warden"))
                ).build()
        }, MSBlocks.COMPRESSED_SNOW_SPONGE_2X.asItem(), 1, 2));

        this.add("lava_sponge_entity_loot_modifier", new MSLootModifier(new LootItemCondition[]{
                AnyOfCondition.anyOf(
                        new LootTableIdCondition.Builder(Identifier.withDefaultNamespace("entities/ender_dragon"))
                ).build()
        }, MSBlocks.COMPRESSED_LAVA_SPONGE_2X.asItem(), 1, 2));

        this.add("fire_sponge_entity_loot_modifier", new MSLootModifier(new LootItemCondition[]{
                AnyOfCondition.anyOf(
                        new LootTableIdCondition.Builder(Identifier.withDefaultNamespace("entities/wither"))
                ).build()
        }, MSBlocks.COMPRESSED_FIRE_SPONGE_2X.asItem(), 1, 2));

        this.add("water_sponge_entity_loot_modifier", new MSLootModifier(new LootItemCondition[]{
                AnyOfCondition.anyOf(
                        new LootTableIdCondition.Builder(Identifier.withDefaultNamespace("entities/elder_guardian"))
                ).build()
        }, MSBlocks.COMPRESSED_SPONGE_2X.asItem(), 1, 2));
    }
}
