import com.buuz135.project42.Project42;
import com.buuz135.project42.api.annotation.ProjectManual;
import com.buuz135.project42.api.manual.IManual;
import com.buuz135.project42.api.manual.impl.category.BasicCategory;
import com.buuz135.project42.api.manual.impl.category.display.ItemStackCategoryDisplay;
import com.buuz135.project42.manual.ManualInfo;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

@ProjectManual(value = Project42.MOD_ID, displayName = "Test Manual", displayColor = 0x00FFFF)
public class TestManual implements IManual {

    @Override
    public void registerCategories(ManualInfo info) {
        info.registerCategory(new BasicCategory("test 1", new ItemStackCategoryDisplay(new ItemStack(Blocks.STONE))));
        info.registerCategory(new BasicCategory("test 2", new ItemStackCategoryDisplay(new ItemStack(Blocks.SAPLING))));
        info.registerCategory(new BasicCategory("test 3", new ItemStackCategoryDisplay(new ItemStack(Blocks.PLANKS))));
        info.registerCategory(new BasicCategory("test 4", new ItemStackCategoryDisplay(new ItemStack(Blocks.CRAFTING_TABLE))));
        info.registerCategory(new BasicCategory("test 5", new ItemStackCategoryDisplay(new ItemStack(Blocks.HAY_BLOCK))));
        info.registerCategory(new BasicCategory("test 6", new ItemStackCategoryDisplay(new ItemStack(Blocks.BEACON))));
        info.registerCategory(new BasicCategory("test 7", new ItemStackCategoryDisplay(new ItemStack(Blocks.ANVIL))));
        info.registerCategory(new BasicCategory("test 8", new ItemStackCategoryDisplay(new ItemStack(Blocks.END_ROD))));
        info.setCategorySize(4, 10);
    }

}
