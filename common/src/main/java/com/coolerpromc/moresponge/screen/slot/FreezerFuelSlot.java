package com.coolerpromc.moresponge.screen.slot;

import com.coolerpromc.moresponge.screen.custom.FreezerMenu;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class FreezerFuelSlot extends Slot {
    private final FreezerMenu menu;
    private final int slotIndex;

    public FreezerFuelSlot(FreezerMenu menu, Container container, int slot, int x, int y) {
        super(container, slot, x, y);
        this.menu = menu;
        this.slotIndex = slot;
    }

    @Override
    public boolean mayPlace(ItemStack itemStack) {
        return this.menu.isFuel(itemStack) || container.canPlaceItem(slotIndex, itemStack);
    }
}
