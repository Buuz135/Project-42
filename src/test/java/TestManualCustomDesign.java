import com.buuz135.project42.Project42;
import com.buuz135.project42.api.annotation.ProjectManual;
import com.buuz135.project42.api.manual.ICategoryEntry;
import com.buuz135.project42.api.manual.IContent;
import com.buuz135.project42.api.manual.IManual;
import com.buuz135.project42.api.manual.design.IBackgroundDesign;
import com.buuz135.project42.api.manual.design.IManualDesign;
import com.buuz135.project42.api.manual.design.display.ICategoryEntryDisplay;
import com.buuz135.project42.api.manual.impl.category.BasicCategory;
import com.buuz135.project42.api.manual.impl.category.display.ItemStackCategoryDisplay;
import com.buuz135.project42.manual.ManualInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.util.List;

@ProjectManual(value = Project42.MOD_ID + "Custom Design", displayName = "Test Manual Custom Design")
public class TestManualCustomDesign implements IManual {

    @Override
    public void registerCategories(ManualInfo info) {
        info.registerCategory(new BasicCategory("Basic Blocks", new ItemStackCategoryDisplay(new ItemStack(Blocks.STONE)), "This is a stone block", "I'm a cube")
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test0"), new CategoryEntry(new ItemStack(Blocks.COBBLESTONE)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test1"), new CategoryEntry(new ItemStack(Blocks.FURNACE)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test2"), new CategoryEntry(new ItemStack(Blocks.STONE)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test3"), new CategoryEntry(new ItemStack(Blocks.DIRT)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test4"), new CategoryEntry(new ItemStack(Blocks.CRAFTING_TABLE)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test5"), new CategoryEntry(new ItemStack(Blocks.BEACON)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test01"), new CategoryEntry(new ItemStack(Blocks.COBBLESTONE)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test11"), new CategoryEntry(new ItemStack(Blocks.FURNACE)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test21"), new CategoryEntry(new ItemStack(Blocks.STONE)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test31"), new CategoryEntry(new ItemStack(Blocks.DIRT)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test41"), new CategoryEntry(new ItemStack(Blocks.CRAFTING_TABLE)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test51"), new CategoryEntry(new ItemStack(Blocks.BEACON)))
        );
        info.registerCategory(new BasicCategory("Woody Stuff", new ItemStackCategoryDisplay(new ItemStack(Blocks.SAPLING)), "I can be planted"));
        info.registerCategory(new BasicCategory("Crafting Stuff", new ItemStackCategoryDisplay(new ItemStack(Blocks.PLANKS)), "I can be obtained by crafting"));
        info.registerCategory(new BasicCategory("Crafting Stuff?", new ItemStackCategoryDisplay(new ItemStack(Blocks.CRAFTING_TABLE)), "The origin of everything"));
        info.registerCategory(new BasicCategory("Food", new ItemStackCategoryDisplay(new ItemStack(Blocks.HAY_BLOCK)), "WHEAAAAAAAAAT"));
        info.registerCategory(new BasicCategory("Nether Star Options", new ItemStackCategoryDisplay(new ItemStack(Blocks.BEACON)), "Oh! Expensive"));
        info.registerCategory(new BasicCategory("DING!", new ItemStackCategoryDisplay(new ItemStack(Blocks.ANVIL)), "Compress those mobs"));
        info.registerCategory(new BasicCategory("The End LUL", new ItemStackCategoryDisplay(new ItemStack(Blocks.END_ROD)), "Useless things in modded minecraft"));
        info.setCategorySize(6, 10);
        info.setDesign(new CustomManualDesign());
    }

    public static class CategoryEntry implements ICategoryEntry {

        private final ItemStack itemStack;

        public CategoryEntry(ItemStack itemStack) {
            this.itemStack = itemStack;
        }

        @Override
        public List<IContent> getContent() {
            return null;
        }

        @Override
        public ICategoryEntryDisplay getDisplay() {
            return new CategoryEntryDisplay(itemStack);
        }
    }

    public static class CategoryEntryDisplay implements ICategoryEntryDisplay {

        private final ItemStack itemStack;

        public CategoryEntryDisplay(ItemStack itemStack) {
            this.itemStack = itemStack;
        }

        @Override
        public void render(Minecraft mc, int x, int y, boolean isHovered) {
            String name = itemStack.getDisplayName();
            Color color = new Color(getColor());
            if (isHovered) color = color.darker();
            mc.fontRenderer.drawString(name, x + sizeX() / 2 - mc.fontRenderer.getStringWidth(name) / 2, y + 3, color.getRGB(), false);
            Gui.drawRect(x + 1, y + 3 + mc.fontRenderer.FONT_HEIGHT + 1, x + sizeX() - 1, y + 3 + mc.fontRenderer.FONT_HEIGHT + 2, color.darker().getRGB());
            double scale = 1.8;
            GlStateManager.scale(scale, scale, scale);
            RenderHelper.enableGUIStandardItemLighting();
            mc.getRenderItem().renderItemIntoGUI(itemStack, (int) ((x + sizeX() / 2D - 16) / scale), (int) ((y + 16) / scale));
            GlStateManager.scale(-scale, -scale, scale);
        }

        @Override
        public int sizeX() {
            return 74;
        }

        @Override
        public int sizeY() {
            return 44;
        }

        @Override
        public int getColor() {
            return Color.CYAN.darker().getRGB();
        }
    }

    public static class CustomManualDesign implements IManualDesign {

        @Override
        public IBackgroundDesign getBackgroundDesign() {
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
        public IBackgroundDesign getCategoryDesign() {
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
            public double getScale() {
                return 1;
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
