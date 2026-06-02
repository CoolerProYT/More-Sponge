package com.coolerpromc.moresponge.screen.slot;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class FreezerInputSlot extends Slot {
    private final int slotIndex;

    public FreezerInputSlot(Container container, int slot, int x, int y) {
        super(container, slot, x, y);
        this.slotIndex = slot;
    }

    @Override
    public boolean mayPlace(ItemStack itemStack) {
        return container.canPlaceItem(slotIndex, itemStack);
    }
}
