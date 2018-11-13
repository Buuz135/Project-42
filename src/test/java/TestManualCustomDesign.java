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
import com.buuz135.project42.Project42;
import com.buuz135.project42.api.annotation.ProjectManual;
import com.buuz135.project42.api.manual.CategoryEntry;
import com.buuz135.project42.api.manual.IManual;
import com.buuz135.project42.api.manual.design.IBackgroundDesign;
import com.buuz135.project42.api.manual.design.IDrawableLocationTextureHovereable;
import com.buuz135.project42.api.manual.design.IManualDesign;
import com.buuz135.project42.api.manual.design.IManualItemDesign;
import com.buuz135.project42.api.manual.design.display.ICategoryEntryDisplay;
import com.buuz135.project42.api.manual.impl.category.BasicCategory;
import com.buuz135.project42.api.manual.impl.category.display.ItemStackCategoryDisplay;
import com.buuz135.project42.api.manual.impl.content.TextContent;
import com.buuz135.project42.api.manual.impl.design.DefaultDrawableLocationTexture;
import com.buuz135.project42.item.ItemManual;
import com.buuz135.project42.manual.ManualInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

import static com.buuz135.project42.api.manual.impl.design.DefaultBackgroundDesign.EXTRAS;

@ProjectManual(value = Project42.MOD_ID + "Custom_Design", modName = Project42.MOD_NAME)
public class TestManualCustomDesign implements IManual {

    @Override
    public void registerCategories(ManualInfo info) {
        double scale = 2.5;
        info.registerCategory(new BasicCategory("Basic Blocks", new ItemStackCategoryDisplay(new ItemStack(Blocks.STONE), scale), "This is a stone block", "I'm a cube")
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test0"), new CustomCategoryEntry(new ItemStack(Blocks.COBBLESTONE))
                        .addContent(new TextContent("Lorem ipsum dolor sit amet consectetur adipiscing elit augue, platea tincidunt gravida aptent sodales torquent senectus, consequat a eget tempus curae dis cras. Lobortis vel duis morbi luctus a etiam ad, faucibus mattis viverra sociosqu eget eleifend. Blandit nascetur at id est erat himenaeos hac magnis suscipit volutpat, placerat pretium fusce aliquam quam nullam velit urna et integer, sagittis curabitur turpis euismod quisque mattis massa vitae gravida.\n" +
                                "Curae suspendisse ultrices ornare litora neque cras ultricies auctor eleifend magna sollicitudin, hendrerit lacinia dignissim per aliquam quam rhoncus ante id velit, primis eu lectus vehicula vel purus cum non aptent dui. Lobortis nisl nunc quam consequat aptent scelerisque, sed cum dignissim nulla pulvinar purus, duis diam mi at blandit. Pharetra senectus parturient in dictumst enim turpis augue cras fusce, donec dui per integer eget sociosqu magna mi porttitor vestibulum, congue aenean suspendisse convallis tellus non nulla taciti.", 222)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test1"), new CustomCategoryEntry(new ItemStack(Blocks.FURNACE)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test2"), new CustomCategoryEntry(new ItemStack(Blocks.STONE)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test3"), new CustomCategoryEntry(new ItemStack(Blocks.DIRT)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test4"), new CustomCategoryEntry(new ItemStack(Blocks.CRAFTING_TABLE)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test5"), new CustomCategoryEntry(new ItemStack(Blocks.BEACON)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test01"), new CustomCategoryEntry(new ItemStack(Blocks.COBBLESTONE)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test11"), new CustomCategoryEntry(new ItemStack(Blocks.FURNACE)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test21"), new CustomCategoryEntry(new ItemStack(Blocks.STONE)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test31"), new CustomCategoryEntry(new ItemStack(Blocks.DIRT)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test41"), new CustomCategoryEntry(new ItemStack(Blocks.CRAFTING_TABLE)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test51"), new CustomCategoryEntry(new ItemStack(Blocks.BEACON)))
        );
        info.registerCategory(new BasicCategory("Woody Stuff", new ItemStackCategoryDisplay(new ItemStack(Blocks.SAPLING), scale), "I can be planted"));
        info.registerCategory(new BasicCategory("Crafting Stuff", new ItemStackCategoryDisplay(new ItemStack(Blocks.PLANKS), scale), "I can be obtained by crafting"));
        info.registerCategory(new BasicCategory("Crafting Stuff?", new ItemStackCategoryDisplay(new ItemStack(Blocks.CRAFTING_TABLE), scale), "The origin of everything"));
        info.registerCategory(new BasicCategory("Food", new ItemStackCategoryDisplay(new ItemStack(Blocks.HAY_BLOCK), scale), "WHEAAAAAAAAAT"));
        info.registerCategory(new BasicCategory("Nether Star Options", new ItemStackCategoryDisplay(new ItemStack(Blocks.BEACON), scale), "Oh! Expensive"));
        info.registerCategory(new BasicCategory("DING!", new ItemStackCategoryDisplay(new ItemStack(Blocks.ANVIL), scale), "Compress those mobs"));
        info.registerCategory(new BasicCategory("The End LUL", new ItemStackCategoryDisplay(new ItemStack(Blocks.END_ROD), scale), "Useless things in modded minecraft"));
        info.setDesign(new CustomManualDesign());
        info.setManualItemDesign(new CustomManualItem());
        info.setDisplayName("Test Manual Custom Design");
    }

    @Override
    public void onManualItemCreation(ManualInfo info, ItemManual manual) {
        CreativeTabs tabs = new CreativeTabs("customDesign") {
            @Override
            public ItemStack createIcon() {
                return new ItemStack(manual);
            }
        };
        manual.setCreativeTab(tabs);
        manual.setModelLocation(Items.APPLE.getRegistryName());
    }

    public static class CustomCategoryEntry extends CategoryEntry {

        private final ItemStack itemStack;

        public CustomCategoryEntry(ItemStack itemStack) {
            this.itemStack = itemStack;
        }

        @Override
        public ICategoryEntryDisplay getDisplay() {
            return new CategoryEntryDisplay(itemStack);
        }
    }

    public static class CustomManualItem implements IManualItemDesign {

        @Override
        public int getCoverColor() {
            return 0xcdfc8d;
        }

        @Override
        public int getBorderColor() {
            return 0xaf9b00;
        }

        @Override
        public int getLetterColor() {
            return 0xaf9b00;
        }
    }

    public static class CategoryEntryDisplay implements ICategoryEntryDisplay {

        private final ItemStack itemStack;

        public CategoryEntryDisplay(ItemStack itemStack) {
            this.itemStack = itemStack;
        }

        @Override
        public void render(Minecraft mc, int x, int y, boolean isHovered) {
            GlStateManager.pushMatrix();
            String name = itemStack.getDisplayName();
            Color color = Color.CYAN.darker();
            if (isHovered) color = color.darker();
            mc.fontRenderer.drawString(name, x + getSizeX() / 2 - mc.fontRenderer.getStringWidth(name) / 2, y + 3, color.getRGB(), false);
            Gui.drawRect(x + 1, y + 3 + mc.fontRenderer.FONT_HEIGHT + 1, x + getSizeX() - 1, y + 3 + mc.fontRenderer.FONT_HEIGHT + 2, color.darker().getRGB());
            double scale = 1.8;
            GlStateManager.scale(scale, scale, scale);
            RenderHelper.enableGUIStandardItemLighting();
            mc.getRenderItem().renderItemIntoGUI(itemStack, (int) ((x + getSizeX() / 2D - 15) / scale), (int) ((y + 16) / scale));
            GlStateManager.popMatrix();
        }

        @Override
        public int getSizeX() {
            return 74;
        }

        @Override
        public int getSizeY() {
            return 44;
        }
    }

    public static class CustomManualDesign implements IManualDesign {

        @Override
        public IBackgroundDesign getCategoryDesign() {
            return new CustomBackgroundDesign();
        }

        @Override
        public int getDisplayColor() {
            return 0x021868;
        }

        @Override
        public int getTextColor() {
            return 0xc2d7f9;
        }

        @Override
        public IBackgroundDesign getCategoryEntryDesign() {
            return new CustomBackgroundDesign();
        }

        @Override
        public IBackgroundDesign getPageDesign() {
            return new CustomBackgroundDesign();
        }


        public static class CustomBackgroundDesign implements IBackgroundDesign {

            public static final ResourceLocation BOOK_BACK = new ResourceLocation(Project42.MOD_ID, "textures/gui/book_back_other.png");

            @Override
            public ResourceLocation getTexture() {
                return BOOK_BACK;
            }

            @Override
            public int getTopPadding() {
                return 17;
            }

            @Override
            public int getBottomPadding() {
                return 17;
            }

            @Override
            public int getLeftPadding() {
                return 17;
            }

            @Override
            public int getRightPadding() {
                return 17;
            }


            @Override
            public IDrawableLocationTextureHovereable getPrevPageTexture() {
                return new DefaultDrawableLocationTexture(6, 162, EXTRAS, 1, 14, 18, 10, 24, 14);
            }

            @Override
            public IDrawableLocationTextureHovereable getNextPageTexture() {
                return new DefaultDrawableLocationTexture(232, 162, EXTRAS, 1, 1, 18, 10, 24, 1);
            }

            @Override
            public IDrawableLocationTextureHovereable getBackTexture() {
                return new DefaultDrawableLocationTexture(6, 6, EXTRAS, 1, 27, 18, 10, 24, 27);
            }

            @Override
            public int getTextureX() {
                return 0;
            }

            @Override
            public int getTextureY() {
                return 0;
            }

            @Override
            public int getTextureWidth() {
                return 256;
            }

            @Override
            public int getTextureHeight() {
                return 176;
            }
        }

    }
}
