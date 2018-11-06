package com.buuz135.project42.gui.button;

import com.buuz135.project42.api.manual.design.IDrawableLocationTextureHovereable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class DrawableButton extends GuiButton {

    private final IDrawableLocationTextureHovereable texture;

    public DrawableButton(int buttonId, int posX, int posY, IDrawableLocationTextureHovereable texture) {
        super(buttonId, texture.getPosX() + posX, texture.getPosY() + posY, texture.getTextureWidth(), texture.getTextureHeight(), "");
        this.texture = texture;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            mc.getTextureManager().bindTexture(texture.getTexture());
            GlStateManager.color(1, 1, 1, 1);
            drawTexturedModalRect(x, y, isMouseOver() ? texture.getHoveredX() : texture.getTextureX(), isMouseOver() ? texture.getHoveredY() : texture.getTextureY(), texture.getTextureWidth(), texture.getTextureHeight());
        }
    }
}
