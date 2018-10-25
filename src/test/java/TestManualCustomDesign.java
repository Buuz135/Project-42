import com.buuz135.project42.Project42;
import com.buuz135.project42.api.annotation.ProjectManual;
import com.buuz135.project42.api.manual.IManual;
import com.buuz135.project42.api.manual.design.IBackgroundDesign;
import com.buuz135.project42.api.manual.design.IManualDesign;
import com.buuz135.project42.api.manual.impl.category.BasicCategory;
import com.buuz135.project42.api.manual.impl.category.display.ItemStackCategoryDisplay;
import com.buuz135.project42.manual.ManualInfo;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

@ProjectManual(value = Project42.MOD_ID + "Custom Design", displayName = "Test Manual Custom Design")
public class TestManualCustomDesign implements IManual {

    @Override
    public void registerCategories(ManualInfo info) {
        info.registerCategory(new BasicCategory("Basic Blocks", new ItemStackCategoryDisplay(new ItemStack(Blocks.STONE)), "This is a stone block", "I'm a cube"));
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
