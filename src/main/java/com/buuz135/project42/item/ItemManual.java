package com.buuz135.project42.item;

import com.buuz135.project42.Project42;
import com.buuz135.project42.gui.GuiCategoryList;
import com.buuz135.project42.manual.ManualInfo;
import com.buuz135.project42.util.ManualHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemManual extends Item {

    public ItemManual() {
        setRegistryName(Project42.MOD_ID, "manual");
        setTranslationKey(Project42.MOD_ID + ":" + "manual");
        setCreativeTab(Project42.TAB);
        setHasSubtypes(true);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (isInCreativeTab(tab)) {
            for (String id : ManualInfo.MANUALS.keySet()) {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setString("Id", id);
                ItemStack stack = new ItemStack(this);
                stack.setTagCompound(tagCompound);
                items.add(stack);
            }
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (worldIn.isRemote) {
            ItemStack heldItem = playerIn.getHeldItem(handIn);
            ManualInfo info = ManualHelper.getManualInfoFromStack(heldItem);
            if (info != null) {
                Minecraft.getMinecraft().displayGuiScreen(new GuiCategoryList(null, info));
            }
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        ManualInfo info = ManualHelper.getManualInfoFromStack(stack);
        if (info != null) {
            return info.getDisplayName();
        }
        return super.getItemStackDisplayName(stack);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        ManualInfo info = ManualHelper.getManualInfoFromStack(stack);
        if (info != null) {
            tooltip.add(TextFormatting.GRAY + "Added by: " + TextFormatting.BLUE + info.getModName());
        }
    }
}
