package com.coolerpromc.moresponge.platform.util;

import net.minecraft.world.level.ItemLike;

@FunctionalInterface
public interface CreativeTabOutput {
    void accept(ItemLike itemLike);
}