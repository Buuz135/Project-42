import com.buuz135.project42.Project42;
import com.buuz135.project42.api.annotation.ProjectManual;
import com.buuz135.project42.api.manual.CategoryEntry;
import com.buuz135.project42.api.manual.IManual;
import com.buuz135.project42.api.manual.design.IBackgroundDesign;
import com.buuz135.project42.api.manual.design.IDrawableLocationTextureHovereable;
import com.buuz135.project42.api.manual.design.IManualDesign;
import com.buuz135.project42.api.manual.design.display.ICategoryEntryDisplay;
import com.buuz135.project42.api.manual.impl.category.BasicCategory;
import com.buuz135.project42.api.manual.impl.category.display.ItemStackCategoryDisplay;
import com.buuz135.project42.api.manual.impl.content.TextContent;
import com.buuz135.project42.api.manual.impl.design.DefaultDrawableLocationTexture;
import com.buuz135.project42.manual.ManualInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

import static com.buuz135.project42.api.manual.impl.design.DefaultBackgroundDesign.EXTRAS;

@ProjectManual(value = Project42.MOD_ID + "Custom Design", modName = Project42.MOD_NAME)
public class TestManualCustomDesign implements IManual {

    @Override
    public void registerCategories(ManualInfo info) {
        info.registerCategory(new BasicCategory("Basic Blocks", new ItemStackCategoryDisplay(new ItemStack(Blocks.STONE)), "This is a stone block", "I'm a cube")
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
        info.registerCategory(new BasicCategory("Woody Stuff", new ItemStackCategoryDisplay(new ItemStack(Blocks.SAPLING)), "I can be planted"));
        info.registerCategory(new BasicCategory("Crafting Stuff", new ItemStackCategoryDisplay(new ItemStack(Blocks.PLANKS)), "I can be obtained by crafting"));
        info.registerCategory(new BasicCategory("Crafting Stuff?", new ItemStackCategoryDisplay(new ItemStack(Blocks.CRAFTING_TABLE)), "The origin of everything"));
        info.registerCategory(new BasicCategory("Food", new ItemStackCategoryDisplay(new ItemStack(Blocks.HAY_BLOCK)), "WHEAAAAAAAAAT"));
        info.registerCategory(new BasicCategory("Nether Star Options", new ItemStackCategoryDisplay(new ItemStack(Blocks.BEACON)), "Oh! Expensive"));
        info.registerCategory(new BasicCategory("DING!", new ItemStackCategoryDisplay(new ItemStack(Blocks.ANVIL)), "Compress those mobs"));
        info.registerCategory(new BasicCategory("The End LUL", new ItemStackCategoryDisplay(new ItemStack(Blocks.END_ROD)), "Useless things in modded minecraft"));
        info.setCategoryXSize(6);
        info.setDesign(new CustomManualDesign());
        info.setDisplayName("Test Manual Custom Design");
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
            public double getScale() {
                return 1;
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
