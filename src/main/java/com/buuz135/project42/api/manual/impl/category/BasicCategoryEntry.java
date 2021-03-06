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
package com.buuz135.project42.api.manual.impl.category;

import com.buuz135.project42.api.manual.CategoryEntry;
import com.buuz135.project42.api.manual.design.display.ICategoryEntryDisplay;
import com.buuz135.project42.api.manual.impl.category.display.ItemStackCategoryEntryDisplay;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BasicCategoryEntry extends CategoryEntry {

    private final ItemStack itemStack;

    public BasicCategoryEntry(ItemStack itemStack) {
        super();
        this.itemStack = itemStack;
    }

    public BasicCategoryEntry(Item item) {
        this(new ItemStack(item));
    }

    public BasicCategoryEntry(Block block) {
        this(new ItemStack(block));
    }

    public ICategoryEntryDisplay getDisplay() {
        return new ItemStackCategoryEntryDisplay(itemStack);
    }

}
