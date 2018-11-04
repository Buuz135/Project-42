package com.buuz135.project42.item;

import com.buuz135.project42.Project42;
import com.buuz135.project42.gui.GuiCategoryList;
import com.buuz135.project42.manual.ManualInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

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
            if (heldItem.hasTagCompound()) {
                NBTTagCompound compound = heldItem.getTagCompound();
                if (compound.hasKey("Id")) {
                    String id = compound.getString("Id");
                    if (ManualInfo.MANUALS.keySet().contains(id)) {
                        Minecraft.getMinecraft().displayGuiScreen(new GuiCategoryList(null, ManualInfo.MANUALS.get(id)));
                    }
                }
            }
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        if (stack.hasTagCompound()) {
            NBTTagCompound compound = stack.getTagCompound();
            if (compound.hasKey("Id")) {
                String id = compound.getString("Id");
                if (ManualInfo.MANUALS.keySet().contains(id)) {
                    return ManualInfo.MANUALS.get(id).getDisplayName();
                }
            }
        }
        return super.getItemStackDisplayName(stack);
    }
}
