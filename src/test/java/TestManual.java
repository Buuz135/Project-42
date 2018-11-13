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
import com.buuz135.project42.api.manual.IManual;
import com.buuz135.project42.api.manual.impl.category.BasicCategory;
import com.buuz135.project42.api.manual.impl.category.BasicCategoryEntry;
import com.buuz135.project42.api.manual.impl.category.display.ItemStackCategoryDisplay;
import com.buuz135.project42.api.manual.impl.content.RecipeContent;
import com.buuz135.project42.api.manual.impl.content.TextContent;
import com.buuz135.project42.item.ItemManual;
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
                        .addContent(new TextContent("test.project42.lore", 123))
                        .addContent(new RecipeContent(ForgeRegistries.RECIPES.getValue(new ResourceLocation("crafting_table")))))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test1"), new BasicCategoryEntry(new ItemStack(Blocks.END_ROD)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test2"), new BasicCategoryEntry(new ItemStack(Blocks.BEACON)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test3"), new BasicCategoryEntry(new ItemStack(Blocks.SAPLING)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test4"), new BasicCategoryEntry(new ItemStack(Blocks.REDSTONE_BLOCK)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test5"), new BasicCategoryEntry(new ItemStack(Blocks.STONE)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test3"), new BasicCategoryEntry(new ItemStack(Blocks.WOOL)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test4"), new BasicCategoryEntry(new ItemStack(Blocks.PISTON)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test5"), new BasicCategoryEntry(new ItemStack(Blocks.COAL_BLOCK)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test1"), new BasicCategoryEntry(new ItemStack(Blocks.END_ROD)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test2"), new BasicCategoryEntry(new ItemStack(Blocks.BEACON)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test3"), new BasicCategoryEntry(new ItemStack(Blocks.SAPLING)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test4"), new BasicCategoryEntry(new ItemStack(Blocks.RED_FLOWER)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test5"), new BasicCategoryEntry(new ItemStack(Blocks.STONE)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test3"), new BasicCategoryEntry(new ItemStack(Blocks.WOOL)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test4"), new BasicCategoryEntry(new ItemStack(Blocks.PISTON)))
                .addEntry(new ResourceLocation(Project42.MOD_ID, "test5"), new BasicCategoryEntry(new ItemStack(Blocks.COAL_BLOCK)))
        );
        info.registerCategory(new BasicCategory("Woody Stuff", new ItemStackCategoryDisplay(new ItemStack(Blocks.SAPLING)), "I can be planted"));
        info.registerCategory(new BasicCategory("Crafting Stuff", new ItemStackCategoryDisplay(new ItemStack(Blocks.PLANKS)), "I can be obtained by crafting"));
        info.registerCategory(new BasicCategory("Crafting Stuff?", new ItemStackCategoryDisplay(new ItemStack(Blocks.CRAFTING_TABLE)), "The origin of everything"));
        info.registerCategory(new BasicCategory("Food", new ItemStackCategoryDisplay(new ItemStack(Blocks.HAY_BLOCK)), "WHEAAAAAAAAAT"));
        info.registerCategory(new BasicCategory("Nether Star Options", new ItemStackCategoryDisplay(new ItemStack(Blocks.BEACON)), "Oh! Expensive"));
        info.registerCategory(new BasicCategory("DING!", new ItemStackCategoryDisplay(new ItemStack(Blocks.ANVIL)), "Compress those mobs"));
        info.registerCategory(new BasicCategory("The End LUL", new ItemStackCategoryDisplay(new ItemStack(Blocks.END_ROD)), "Useless things in modded minecraft"));
        info.setDisplayName("Test Manual");
    }

    @Override
    public void onManualItemCreation(ManualInfo info, ItemManual manual) {

    }

}
