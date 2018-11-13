/*
 * This file is part of Project 42.
 *
 * Copyright 2018, Buuz135
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in the
 * Software without restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the
 * following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.buuz135.project42.api.manual.impl.content;

import com.buuz135.project42.api.manual.IAdvancedContent;
import com.buuz135.project42.api.manual.IContent;
import com.buuz135.project42.api.manual.impl.design.DefaultBackgroundDesign;
import com.buuz135.project42.api.manual.impl.design.DefaultDrawableLocationTexture;
import com.buuz135.project42.gui.GuiManualBase;
import com.buuz135.project42.gui.button.DrawableButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeContent implements IContent, IAdvancedContent {

    private List<IRecipe> recipe;
    private int pointer;
    private DrawableButton leftArrow;
    private DrawableButton rightArrow;

    @Override
    public int getSizeY() {
        return 62;
    }

    @Override
    public int getSizeX() {
        return 124;
    }

    public RecipeContent(IRecipe recipe) {
        this.recipe = new ArrayList<>();
        this.recipe.add(recipe);
        this.pointer = 0;
    }

    public RecipeContent(ItemStack recipeOutput) {
        this.recipe = ForgeRegistries.RECIPES.getValuesCollection().stream().filter(iRecipe -> iRecipe.getRecipeOutput().isItemEqual(recipeOutput)).collect(Collectors.toList());
        this.pointer = 0;
    }

    @Override
    public boolean canBeSplitted() {
        return false;
    }

    @Override
    public Pair<IContent, IContent> split(int y) {
        return null;
    }

    @Override
    public void renderBack(Minecraft mc, int x, int y, int mouseX, int mouseY) {
        GlStateManager.color(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(DefaultBackgroundDesign.EXTRAS);
        mc.currentScreen.drawTexturedModalRect(x, y, 45, 1, 124, 62);
        IRecipe recipe = this.recipe.get(pointer);
        int pos = 0;
        int recipeSize = getSize();
        RenderHelper.enableGUIStandardItemLighting();
        for (Ingredient ingredient : recipe.getIngredients()) {
            if (ingredient != null && ingredient.getMatchingStacks().length > 0) {
                int ing = (int) (mc.world.getTotalWorldTime() / 30 % ingredient.getMatchingStacks().length);
                int posX = x + 5 + (pos % recipeSize) * 18;
                int posY = y + 5 + (pos / recipeSize) * 18;
                ItemStack stack = ingredient.getMatchingStacks()[ing];
                mc.getRenderItem().renderItemIntoGUI(stack, posX, posY);
            }
            ++pos;
        }
        mc.getRenderItem().renderItemAndEffectIntoGUI(recipe.getRecipeOutput(), x + 99, y + 23);
        mc.getRenderItem().renderItemOverlays(mc.fontRenderer, recipe.getRecipeOutput(), x + 99, y + 23);
        RenderHelper.disableStandardItemLighting();
    }

    @Override
    public void renderFront(Minecraft mc, int x, int y, int mouseX, int mouseY) {
        IRecipe recipe = this.recipe.get(pointer);
        int pos = 0;
        int recipeSize = getSize();
        for (Ingredient ingredient : recipe.getIngredients()) {
            if (ingredient != null && ingredient.getMatchingStacks().length > 0) {
                int ing = (int) (mc.world.getTotalWorldTime() / 30 % ingredient.getMatchingStacks().length);
                int posX = x + 5 + (pos % recipeSize) * 18;
                int posY = y + 5 + (pos / recipeSize) * 18;
                ItemStack stack = ingredient.getMatchingStacks()[ing];
                if (mouseX > posX && mouseX < posX + 18 && mouseY > posY && mouseY < posY + 18) {
                    mc.currentScreen.drawHoveringText(mc.currentScreen.getItemToolTip(stack), mouseX, mouseY);
                }
            }
            ++pos;
        }
        if (mouseX > x + 99 && mouseX < x + 99 + 18 && mouseY > y + 23 && mouseY < y + 23 + 18) {
            mc.currentScreen.drawHoveringText(mc.currentScreen.getItemToolTip(recipe.getRecipeOutput()), mouseX, mouseY);
        }
    }

    public int getSize() {
        return recipe.get(pointer).canFit(2, 2) ? 2 : 3;
    }

    @Override
    public void onAdded(Minecraft mc, GuiManualBase base, int contentX, int contentY) {
        int x = 94;
        int y = 46;
        if (pointer > 0) {
            leftArrow = new DrawableButton(-1000, contentX + x, contentY + y, new DefaultDrawableLocationTexture(0, 0, DefaultBackgroundDesign.EXTRAS, 170, 1, 12, 10, 183, 1)) {
                @Override
                public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
                    if (isMouseOver()) {
                        --pointer;
                        onRemoved(mc, base, contentX, contentY);
                        onAdded(mc, base, contentX, contentY);
                    }
                    return super.mousePressed(mc, mouseX, mouseY);
                }
            };
            base.getButtonList().add(leftArrow);
        }
        if (pointer < recipe.size() - 1) {
            rightArrow = new DrawableButton(-1001, contentX + 14 + x, contentY + y, new DefaultDrawableLocationTexture(0, 0, DefaultBackgroundDesign.EXTRAS, 170, 12, 12, 10, 183, 12)) {
                @Override
                public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
                    if (isMouseOver()) {
                        ++pointer;
                        onRemoved(mc, base, contentX, contentY);
                        onAdded(mc, base, contentX, contentY);
                    }
                    return super.mousePressed(mc, mouseX, mouseY);
                }
            };
            base.getButtonList().add(rightArrow);
        }
    }

    @Override
    public void onRemoved(Minecraft mc, GuiManualBase base, int contentX, int contentY) {
        if (leftArrow != null) {
            base.getButtonList().remove(leftArrow);
            leftArrow = null;
        }
        if (rightArrow != null) {
            base.getButtonList().remove(rightArrow);
            rightArrow = null;
        }
    }
}
