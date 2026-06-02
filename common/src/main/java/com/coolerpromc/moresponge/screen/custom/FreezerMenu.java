package com.coolerpromc.moresponge.screen.custom;

import com.coolerpromc.moresponge.block.MSBlocks;
import com.coolerpromc.moresponge.block.entity.custom.FreezerBlockEntity;
import com.coolerpromc.moresponge.screen.MSMenus;
import com.coolerpromc.moresponge.screen.slot.FreezerFuelSlot;
import com.coolerpromc.moresponge.screen.slot.FreezerInputSlot;
import com.coolerpromc.moresponge.screen.slot.FreezerResultSlot;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class FreezerMenu extends AbstractContainerMenu {
    private final ContainerData data;
    private final FreezerBlockEntity blockEntity;
    private final Level level;

    public FreezerMenu(int containerId, Inventory inventory, BlockPos pos) {
        this(containerId, inventory, blockEntityAt(inventory, pos));
    }

    private FreezerMenu(int containerId, Inventory inventory, FreezerBlockEntity be) {
        this(containerId, inventory, be, be.getData());
    }

    public FreezerMenu(int containerId, Inventory inventory, FreezerBlockEntity blockEntity, ContainerData data){
        super(MSMenus.FREEZER.get(), containerId);
        this.data = data;
        this.blockEntity = blockEntity;
        this.level = inventory.player.level();

        this.addSlot(new FreezerInputSlot(blockEntity.getInputSlot(), 0, 56, 17));
        this.addSlot(new FreezerFuelSlot(this, blockEntity.getFuelSlot(), 0, 56, 53));
        this.addSlot(new FreezerResultSlot(inventory.player, blockEntity, blockEntity.getResultSlot(), 0, 116, 35));
        this.addStandardInventorySlots(inventory, 8, 84);
        this.addDataSlots(data);
    }

    @Override
    public @NonNull ItemStack quickMoveStack(@NonNull Player player, int slotIndex) {
        ItemStack clicked = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);
        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            clicked = stack.copy();
            if (slotIndex == 2) {
                if (!this.moveItemStackTo(stack, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(stack, clicked);
            } else if (slotIndex != 1 && slotIndex != 0) {
                if (this.canSmelt(stack)) {
                    if (!this.moveItemStackTo(stack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.isFuel(stack)) {
                    if (!this.moveItemStackTo(stack, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (slotIndex >= 3 && slotIndex < 30) {
                    if (!this.moveItemStackTo(stack, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (slotIndex >= 30 && slotIndex < 39 && !this.moveItemStackTo(stack, 3, 30, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(stack, 3, 39, false)) {
                return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == clicked.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack);
        }

        return clicked;
    }

    @Override
    public boolean stillValid(@NonNull Player player) {
        return stillValid(ContainerLevelAccess.create(this.level, blockEntity.getBlockPos()), player, MSBlocks.FREEZER.get());
    }

    private static FreezerBlockEntity blockEntityAt(Inventory inv, BlockPos pos) {
        return (FreezerBlockEntity) inv.player.level().getBlockEntity(pos);
    }

    protected boolean canSmelt(ItemStack itemStack) {
        return this.blockEntity.getInputSlot().canAddItem(itemStack);
    }

    public boolean isFuel(ItemStack itemStack) {
        return FreezerBlockEntity.FUEL_VALUES.getOrDefault(itemStack.getItem(), -1) > 0;
    }

    public float getBurnProgress() {
        int current = this.data.get(2);
        int total = this.data.get(3);
        return total != 0 && current != 0 ? Mth.clamp((float)current / total, 0.0F, 1.0F) : 0.0F;
    }

    public float getLitProgress() {
        int litDuration = this.data.get(1);
        if (litDuration == 0) {
            litDuration = 200;
        }

        return Mth.clamp((float)this.data.get(0) / litDuration, 0.0F, 1.0F);
    }

    public boolean isLit() {
        return this.data.get(0) > 0;
    }
}
