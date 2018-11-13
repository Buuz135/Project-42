package com.buuz135.project42.util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

public class ItemStackUtils {

    public static NonNullList<ItemStack> getRealOredictedItems(ItemStack ore) {
        NonNullList<ItemStack> stacks = NonNullList.create();
        if (ore.getMetadata() == OreDictionary.WILDCARD_VALUE && ore.getItem().getCreativeTab() != null)
            ore.getItem().getSubItems(ore.getItem().getCreativeTab(), stacks);
        else {
            stacks.add(ore);
        }
        return stacks;
    }
}
