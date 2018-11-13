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
package com.buuz135.project42.item;

import com.buuz135.project42.Project42;
import com.buuz135.project42.manual.ManualInfo;
import com.buuz135.project42.util.ManualHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemManual extends Item {

    public final static ResourceLocation MODEL_LOCATION = new ResourceLocation(Project42.MOD_ID, "manual");
    private final static CreativeTabs DEFAULT_TAB = Project42.TAB;

    private final String name;
    private ResourceLocation modelLocation;

    public ItemManual(String name) {
        this.name = name;
        this.modelLocation = MODEL_LOCATION;
        setRegistryName(Project42.MOD_ID, name);
        setTranslationKey(Project42.MOD_ID + ":" + name);
        setCreativeTab(DEFAULT_TAB);
        setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (worldIn.isRemote) {
            ManualInfo info = ManualHelper.getManualFromName(name);
            if (info != null) {
                info.openGui();
            }
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        ManualInfo info = ManualHelper.getManualFromName(name);
        if (info != null) {
            return info.getDisplayName();
        }
        return super.getItemStackDisplayName(stack);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if (!this.isInCreativeTab(DEFAULT_TAB)) return;
        ManualInfo info = ManualHelper.getManualFromName(name);
        if (info != null) {
            tooltip.add(TextFormatting.GRAY + "Added by: " + TextFormatting.BLUE + info.getModName());
        }
    }

    public String getName() {
        return name;
    }

    public ResourceLocation getModelLocation() {
        return modelLocation;
    }

    public void setModelLocation(ResourceLocation modelLocation) {
        this.modelLocation = modelLocation;
    }
}
