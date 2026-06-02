package com.coolerpromc.moresponge.compat.jei.category;

import com.coolerpromc.moresponge.Constants;
import com.coolerpromc.moresponge.block.MSBlocks;
import com.coolerpromc.moresponge.compat.jei.recipe.FreezerFuelRecipe;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.placement.HorizontalAlignment;
import mezz.jei.api.gui.placement.VerticalAlignment;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;

import java.text.NumberFormat;

public class FreezingFuelCategory extends AbstractRecipeCategory<FreezerFuelRecipe> {
    public static final IRecipeType<FreezerFuelRecipe> TYPE = IRecipeType.create(Constants.id("freezing_fuel"), FreezerFuelRecipe.class);
    private final IGuiHelper guiHelper;

    public FreezingFuelCategory(IGuiHelper guiHelper) {
        super(TYPE, Component.translatable("gui.jei.category.freezing_fuel"), new IconWithFlameOverlay(guiHelper), getMaxWidth(), 34);
        this.guiHelper = guiHelper;
    }

    private static int getMaxWidth() {
        Minecraft minecraft = Minecraft.getInstance();
        Font fontRenderer = minecraft.font;
        Component maxSmeltCountText = createSmeltCountText(10000000 * 200);
        int maxStringWidth = fontRenderer.width(maxSmeltCountText.getString());
        int textPadding = 20;
        return 18 + textPadding + maxStringWidth;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FreezerFuelRecipe recipe, IFocusGroup focuses) {
        builder.addInputSlot(1, 17).setStandardSlotBackground().addItemStacks(recipe.getInputs());
    }

    @Override
    public void createRecipeExtras(IRecipeExtrasBuilder builder, FreezerFuelRecipe recipe, IFocusGroup focuses) {
        int burnTime = recipe.getBurnTime();

        IDrawable flameEmptyDrawable = guiHelper.drawableBuilder(Constants.id("textures/gui/sprites/container/freezer/flame_empty.png"), 0, 0, 14, 14).setTextureSize(14, 14).build();
        builder.addDrawable(flameEmptyDrawable).setPosition(1, 0);

        IDrawableAnimated flameDrawable = guiHelper.drawableBuilder(Constants.id("textures/gui/sprites/container/freezer/flame.png"), 0, 0, 14, 14)
                .setTextureSize(14, 14)
                .buildAnimated(burnTime, IDrawableAnimated.StartDirection.TOP, true);
        builder.addDrawable(flameDrawable).setPosition(1, 0);

        Component smeltCountText = createSmeltCountText(burnTime);
        builder.addText(smeltCountText, getWidth() - 20, getHeight())
                .setPosition(20, 0)
                .setTextAlignment(HorizontalAlignment.CENTER)
                .setTextAlignment(VerticalAlignment.CENTER)
                .setColor(0xFF808080);
    }

    public static Component createSmeltCountText(int burnTime) {
        if (burnTime == 200) {
            return Component.translatable("gui.jei.category.fuel.freezeCount.single");
        } else {
            NumberFormat numberInstance = NumberFormat.getNumberInstance();
            numberInstance.setMaximumFractionDigits(2);
            String smeltCount = numberInstance.format(burnTime / 200f);
            return Component.translatable("gui.jei.category.fuel.freezeCount", smeltCount);
        }
    }

    private record IconWithFlameOverlay(IDrawable icon) implements IDrawable {
        private IconWithFlameOverlay(IGuiHelper guiHelper) {
            this(guiHelper.createDrawableItemLike(MSBlocks.FREEZER));
        }
    
        @Override
        public int getWidth() {
            return 16;
        }

        @Override
        public int getHeight() {
            return 16;
        }

        @Override
        public void draw(GuiGraphicsExtractor guiGraphics, int xOffset, int yOffset) {
            icon.draw(guiGraphics, xOffset, yOffset);

            var poseStack = guiGraphics.pose();
            poseStack.pushMatrix();
            poseStack.translate(8 + xOffset, 8 + yOffset);
            poseStack.scale(0.5f, 0.5f);
            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, Constants.id("container/freezer/flame"), 0, 0, 14, 14);
            poseStack.popMatrix();
        }
    }
}
