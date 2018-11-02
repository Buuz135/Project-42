package com.buuz135.project42.gui.button;

import com.buuz135.project42.api.manual.design.IDrawableLocationTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class DrawableButton extends GuiButton {

    private final IDrawableLocationTexture texture;

    public DrawableButton(int buttonId, int posX, int posY, IDrawableLocationTexture texture) {
        super(buttonId, texture.getPosX() + posX, texture.getPosY() + posY, texture.getTextureWidth(), texture.getTextureHeight(), "");
        this.texture = texture;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            mc.getTextureManager().bindTexture(texture.getTexture());
            drawTexturedModalRect(x, y, isMouseOver() ? texture.getHoveredX() : texture.getTextureX(), isMouseOver() ? texture.getHoveredY() : texture.getTextureY(), texture.getTextureWidth(), texture.getTextureHeight());
        }
    }
}
