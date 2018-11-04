package com.buuz135.project42.api.manual.impl.content;

import com.buuz135.project42.api.manual.IContent;
import com.buuz135.project42.api.manual.impl.design.DefaultBackgroundDesign;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import org.apache.commons.lang3.tuple.Pair;

public class RecipeContent implements IContent {

    private final IRecipe recipe;

    public RecipeContent(IRecipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public int getSizeY() {
        return 62;
    }

    @Override
    public int getSizeX() {
        return 124;
    }

    @Override
    public void render(Minecraft mc, int x, int y, int mouseX, int mouseY) {
        GlStateManager.color(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(DefaultBackgroundDesign.EXTRAS);
        mc.currentScreen.drawTexturedModalRect(x, y, 45, 1, 124, 62);
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
//                if (mouseX > posX && mouseX < posX +18 && mouseY > posY && mouseY < posY + 18) {
//                    mc.currentScreen.drawHoveringText(mc.currentScreen.getItemToolTip(stack),mouseX, mouseY);
//                }
            }
            ++pos;
        }
        mc.getRenderItem().renderItemIntoGUI(recipe.getRecipeOutput(), x + 99, y + 23);
        RenderHelper.disableStandardItemLighting();
    }

    @Override
    public void renderFront(Minecraft mc, int x, int y, int mouseX, int mouseY) {
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

    @Override
    public boolean canBeSplitted() {
        return false;
    }

    @Override
    public Pair<IContent, IContent> split(int y) {
        return null;
    }

    public int getSize() {
        return recipe.canFit(2, 2) ? 2 : 3;
    }
}
