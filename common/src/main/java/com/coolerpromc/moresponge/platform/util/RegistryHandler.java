package com.coolerpromc.moresponge.platform.util;

import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public interface RegistryHandler<R, T extends R> extends Supplier<T> {
    Holder<R> holder();

    default ResourceKey<R> key(){
        return holder().unwrapKey().orElse(null);
    }

    default Identifier id(){
        return key().identifier();
    }

    interface Items<I extends Item> extends RegistryHandler<Item, I>, ItemLike {
        default ItemStack toStack() {
            return get().getDefaultInstance();
        }

        @Override
        default Item asItem(){
            return get();
        }
    }

    interface Blocks<B extends Block> extends RegistryHandler<Block, B>, ItemLike {
        @Override
        default Item asItem(){
            return get().asItem();
        }

        default ItemStack toStack() {
            return asItem().getDefaultInstance();
        }
    }

    interface Entities<E extends Entity> extends RegistryHandler<EntityType<?>, EntityType<E>>{}
}