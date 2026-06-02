package com.coolerpromc.moresponge.screen.slot;

import com.coolerpromc.moresponge.block.entity.custom.FreezerBlockEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class FreezerResultSlot extends Slot {
    private final Player player;
    private int removeCount;
    private final FreezerBlockEntity blockEntity;

    public FreezerResultSlot(Player player, FreezerBlockEntity blockEntity, Container container, int slot, int x, int y) {
        super(container, slot, x, y);
        this.player = player;
        this.blockEntity = blockEntity;
    }

    @Override
    public boolean mayPlace(ItemStack itemStack) {
        return false;
    }

    @Override
    public ItemStack remove(int amount) {
        if (this.hasItem()) {
            this.removeCount = this.removeCount + Math.min(amount, this.getItem().getCount());
        }

        return super.remove(amount);
    }

    @Override
    public void onTake(Player player, ItemStack carried) {
        this.checkTakeAchievements(carried);
        super.onTake(player, carried);
    }

    @Override
    protected void onQuickCraft(ItemStack picked, int count) {
        this.removeCount += count;
        this.checkTakeAchievements(picked);
    }

    @Override
    protected void checkTakeAchievements(ItemStack carried) {
        carried.onCraftedBy(this.player, this.removeCount);
        if (this.player instanceof ServerPlayer serverPlayer) {
            blockEntity.awardUsedRecipesAndPopExperience(serverPlayer);
        }

        this.removeCount = 0;
    }
}
