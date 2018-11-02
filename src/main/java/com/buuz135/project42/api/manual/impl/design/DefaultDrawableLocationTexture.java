package com.buuz135.project42.api.manual.impl.design;

import com.buuz135.project42.api.manual.design.IDrawableLocationTexture;
import net.minecraft.util.ResourceLocation;

public class DefaultDrawableLocationTexture implements IDrawableLocationTexture {

    private int posX;
    private int posY;
    private ResourceLocation location;
    private int textureX;
    private int textureY;
    private int width;
    private int height;
    private int hoveredX;
    private int hoveredY;

    public DefaultDrawableLocationTexture(int posX, int posY, ResourceLocation location, int textureX, int textureY, int width, int height, int hoveredX, int hoveredY) {
        this.posX = posX;
        this.posY = posY;
        this.location = location;
        this.textureX = textureX;
        this.textureY = textureY;
        this.width = width;
        this.height = height;
        this.hoveredX = hoveredX;
        this.hoveredY = hoveredY;
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

    @Override
    public ResourceLocation getTexture() {
        return location;
    }

    @Override
    public int getTextureX() {
        return textureX;
    }

    @Override
    public int getTextureY() {
        return textureY;
    }

    @Override
    public int getTextureWidth() {
        return width;
    }

    @Override
    public int getTextureHeight() {
        return height;
    }

    @Override
    public int getHoveredX() {
        return hoveredX;
    }

    @Override
    public int getHoveredY() {
        return hoveredY;
    }
}
