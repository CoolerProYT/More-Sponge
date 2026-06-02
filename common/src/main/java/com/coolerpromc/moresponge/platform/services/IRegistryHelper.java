package com.coolerpromc.moresponge.platform.services;

import com.coolerpromc.moresponge.Constants;
import com.coolerpromc.moresponge.platform.util.BlockEntityTypeFactory;
import com.coolerpromc.moresponge.platform.util.CreativeTabOutput;
import com.coolerpromc.moresponge.platform.util.MenuFactory;
import com.coolerpromc.moresponge.platform.util.RegistryHandler;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public interface IRegistryHelper {
    default <T extends Block> RegistryHandler.Blocks<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> func){
        return registerBlock(name, func, BlockBehaviour.Properties.of());
    }
    <T extends Block> RegistryHandler.Blocks<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> func, BlockBehaviour.Properties p);
    default <T extends Item> RegistryHandler.Items<T> registerItem(String name, Function<Item.Properties, T> func){
        return registerItem(name, func, new Item.Properties());
    }
    <T extends Item> RegistryHandler.Items<T> registerItem(String name, Function<Item.Properties, T> func, Item.Properties p);
    <T extends BlockItem> RegistryHandler.Items<T> registerBlockItem(String name, RegistryHandler.Blocks<? extends Block> block, BiFunction<Block, Item.Properties, T> item);
    RegistryHandler<CreativeModeTab, CreativeModeTab> registerCreativeTab(String name, Supplier<ItemStack> icon, Component title, BiConsumer<CreativeTabOutput, CreativeModeTab.ItemDisplayParameters> entries);
    <T extends BlockEntity> RegistryHandler<BlockEntityType<?>, BlockEntityType<T>> registerBlockEntityType(String name, BlockEntityTypeFactory<T> factory, Supplier<? extends Block>... blocks);
    <T extends Recipe<?>> RegistryHandler<RecipeType<?>, RecipeType<T>> registerRecipeType(String name);
    <T extends Recipe<?>> RegistryHandler<RecipeSerializer<?>, RecipeSerializer<T>> registerRecipeSerializer(String name, RecipeSerializer<T> serializer);
    <T extends AbstractContainerMenu, D> RegistryHandler<MenuType<?>, MenuType<T>> registerMenuType(String name, MenuFactory<T, D> factory, StreamCodec<? super RegistryFriendlyByteBuf, D> data);

    static ResourceKey<Block> blockKey(String name) {
        return ResourceKey.create(Registries.BLOCK, Constants.id(name));
    }
    static ResourceKey<Item> itemKey(String name) {
        return ResourceKey.create(Registries.ITEM, Constants.id(name));
    }
}