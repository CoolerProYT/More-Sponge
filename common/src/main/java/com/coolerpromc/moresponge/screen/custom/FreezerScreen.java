package com.coolerpromc.moresponge.screen.custom;

import com.coolerpromc.moresponge.Constants;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

public class FreezerScreen extends AbstractContainerScreen<FreezerMenu> {
    private static final Identifier LIT_PROGRESS_SPRITE = Constants.id("container/freezer/lit_progress");
    private static final Identifier BURN_PROGRESS_SPRITE = Identifier.withDefaultNamespace("container/furnace/burn_progress");
    private static final Identifier TEXTURE = Identifier.withDefaultNamespace("textures/gui/container/furnace.png");

    public FreezerScreen(FreezerMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public void init() {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        int xo = this.leftPos;
        int yo = this.topPos;
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, xo, yo, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);
        if (this.menu.isLit()) {
            int litProgressHeight = Mth.ceil(this.menu.getLitProgress() * 13.0F) + 1;
            graphics.blitSprite(
                    RenderPipelines.GUI_TEXTURED,
                    LIT_PROGRESS_SPRITE,
                    14,
                    14,
                    0,
                    14 - litProgressHeight,
                    xo + 56,
                    yo + 36 + 14 - litProgressHeight,
                    14,
                    litProgressHeight
            );
        }

        int burnProgressWidth = Mth.ceil(this.menu.getBurnProgress() * 24.0F);
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, BURN_PROGRESS_SPRITE, 24, 16, 0, 0, xo + 79, yo + 34, burnProgressWidth, 16);
    }
}
