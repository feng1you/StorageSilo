package org.walterweight.storagesilo;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.walterweight.storagesilo.blocks.storagesilo.BlockStorageSilo;

public class CreativeTab extends CreativeTabs
{

	public CreativeTab(String label)
	{
		super(label);
	}


	@Override
	public Item getTabIconItem()
	{
		BlockStorageSilo bss = new BlockStorageSilo();
		ItemStack is = new ItemStack(bss);
		return is.getItem();
	}
}
