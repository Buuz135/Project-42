package com.buuz135.project42.api.manual.impl.content;

import com.buuz135.project42.api.manual.IAdvancedContent;
import com.buuz135.project42.api.manual.IContent;
import com.buuz135.project42.api.manual.impl.design.DefaultBackgroundDesign;
import com.buuz135.project42.api.manual.impl.design.DefaultDrawableLocationTexture;
import com.buuz135.project42.gui.GuiManualBase;
import com.buuz135.project42.gui.button.DrawableButton;
import com.buuz135.project42.util.ItemStackUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.stream.Collectors;

public class FurnaceContent implements IContent, IAdvancedContent {

    private DrawableButton leftArrow;
    private DrawableButton rightArrow;
    private int pointer;
    private List<ItemStack> inputs;
    private ItemStack output;

    public FurnaceContent(ItemStack output) {
        this.output = output;
        this.pointer = 0;
        this.inputs = FurnaceRecipes.instance().getSmeltingList().keySet().stream().filter(stack -> FurnaceRecipes.instance().getSmeltingResult(stack).isItemEqual(output)).collect(Collectors.toList());
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
    public int getSizeY() {
        return 62;
    }

    @Override
    public int getSizeX() {
        return 90;
    }

    @Override
    public void renderBack(Minecraft mc, int x, int y, int mouseX, int mouseY) {
        GlStateManager.color(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(DefaultBackgroundDesign.EXTRAS);
        mc.currentScreen.drawTexturedModalRect(x, y, 0, 64, 90, 62);
        List<ItemStack> stacks = ItemStackUtils.getRealOredictedItems(this.inputs.get(pointer));
        ItemStack input = stacks.get((int) (mc.world.getTotalWorldTime() / 30 % stacks.size()));
        RenderHelper.enableGUIStandardItemLighting();
        mc.getRenderItem().renderItemAndEffectIntoGUI(input, x + 5, y + 5);
        mc.getRenderItem().renderItemAndEffectIntoGUI(output, x + 64, y + 23);
        RenderHelper.disableStandardItemLighting();
    }

    @Override
    public void renderFront(Minecraft mc, int x, int y, int mouseX, int mouseY) {
        GlStateManager.color(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(DefaultBackgroundDesign.EXTRAS);
        int fireY = 14 - (int) (mc.world.getTotalWorldTime() / 15 % 14);
        mc.currentScreen.drawTexturedModalRect(x + 5, y + 6 + 18 + 14 - fireY, 91, 64 + 14 - fireY, 14, fireY);
        int arrowX = (int) (mc.world.getTotalWorldTime() / 15 % 24);
        mc.currentScreen.drawTexturedModalRect(x + 28, y + 6 + 16, 91, 78, arrowX, 17);
        List<ItemStack> stacks = ItemStackUtils.getRealOredictedItems(this.inputs.get(pointer));
        ItemStack input = stacks.get((int) (mc.world.getTotalWorldTime() / 30 % stacks.size()));
        if (mouseX > x + 5 && mouseX < x + 5 + 18 && mouseY > y + 5 && mouseY < y + 5 + 18) {
            mc.currentScreen.drawHoveringText(mc.currentScreen.getItemToolTip(input), mouseX, mouseY);
        }
        if (mouseX > x + 64 && mouseX < x + 64 + 18 && mouseY > y + 23 && mouseY < y + 23 + 18) {
            mc.currentScreen.drawHoveringText(mc.currentScreen.getItemToolTip(output), mouseX, mouseY);
        }
    }

    @Override
    public void onAdded(Minecraft mc, GuiManualBase base, int contentX, int contentY) {
        int x = 60;
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
        if (pointer < inputs.size() - 1) {
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
