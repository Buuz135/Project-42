import com.buuz135.project42.Project42;
import com.buuz135.project42.api.annotation.ProjectManual;
import com.buuz135.project42.api.manual.IManual;
import com.buuz135.project42.api.manual.impl.category.BasicCategory;
import com.buuz135.project42.api.manual.impl.category.BasicCategoryEntry;
import com.buuz135.project42.api.manual.impl.category.display.ItemStackCategoryDisplay;
import com.buuz135.project42.api.manual.impl.content.RecipeContent;
import com.buuz135.project42.api.manual.impl.content.TextContent;
import com.buuz135.project42.manual.ManualInfo;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@ProjectManual(value = Project42.MOD_ID, modName = Project42.MOD_NAME)
public class TestManual implements IManual {

    @Override
    public void registerCategories(ManualInfo info) {
        info.registerCategory(new BasicCategory("Basic Blocks", new ItemStackCategoryDisplay(new ItemStack(Blocks.STONE)), "This is a stone block", "I'm a cube")
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test"), new BasicCategoryEntry(new ItemStack(Blocks.COBBLESTONE))
                        .addContent(new TextContent("Lorem ipsum dolor sit amet consectetur adipiscing elit augue, platea tincidunt gravida aptent sodales torquent senectus, consequat a eget tempus curae dis cras. Lobortis vel duis morbi luctus a etiam ad, faucibus mattis viverra sociosqu eget eleifend. Blandit nascetur at id est erat himenaeos hac magnis suscipit volutpat, placerat pretium fusce aliquam quam nullam velit urna et integer, sagittis curabitur turpis euismod quisque mattis massa vitae gravida.\n" +
                                "Curae suspendisse ultrices ornare litora neque cras ultricies auctor eleifend magna sollicitudin, hendrerit lacinia dignissim per aliquam quam rhoncus ante id velit, primis eu lectus vehicula vel purus cum non aptent dui. Lobortis nisl nunc quam consequat aptent scelerisque, sed cum dignissim nulla pulvinar purus, duis diam mi at blandit. Pharetra senectus parturient in dictumst enim turpis augue cras fusce, donec dui per integer eget sociosqu magna mi porttitor vestibulum, congue aenean suspendisse convallis tellus non nulla taciti.", 114))
                        .addContent(new RecipeContent(ForgeRegistries.RECIPES.getValue(new ResourceLocation("crafting_table")))))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test1"), new BasicCategoryEntry(new ItemStack(Blocks.COBBLESTONE)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test2"), new BasicCategoryEntry(new ItemStack(Blocks.COBBLESTONE)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test3"), new BasicCategoryEntry(new ItemStack(Blocks.COBBLESTONE)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test4"), new BasicCategoryEntry(new ItemStack(Blocks.COBBLESTONE)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test5"), new BasicCategoryEntry(new ItemStack(Blocks.COBBLESTONE)))
        );
        info.registerCategory(new BasicCategory("Woody Stuff", new ItemStackCategoryDisplay(new ItemStack(Blocks.SAPLING)), "I can be planted"));
        info.registerCategory(new BasicCategory("Crafting Stuff", new ItemStackCategoryDisplay(new ItemStack(Blocks.PLANKS)), "I can be obtained by crafting"));
        info.registerCategory(new BasicCategory("Crafting Stuff?", new ItemStackCategoryDisplay(new ItemStack(Blocks.CRAFTING_TABLE)), "The origin of everything"));
        info.registerCategory(new BasicCategory("Food", new ItemStackCategoryDisplay(new ItemStack(Blocks.HAY_BLOCK)), "WHEAAAAAAAAAT"));
        info.registerCategory(new BasicCategory("Nether Star Options", new ItemStackCategoryDisplay(new ItemStack(Blocks.BEACON)), "Oh! Expensive"));
        info.registerCategory(new BasicCategory("DING!", new ItemStackCategoryDisplay(new ItemStack(Blocks.ANVIL)), "Compress those mobs"));
        info.registerCategory(new BasicCategory("The End LUL", new ItemStackCategoryDisplay(new ItemStack(Blocks.END_ROD)), "Useless things in modded minecraft"));
        info.setCategorySize(4, 10);
        info.setDisplayName("Test Manual");
    }

}
