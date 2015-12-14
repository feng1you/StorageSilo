package uk.binarycraft.storagesilo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import uk.binarycraft.storagesilo.blocks.ModBlocks;

public class CreativeTab extends CreativeTabs
{

	public CreativeTab(String label)
	{
		super(label);
	}


	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem()
	{
		return new ItemStack(ModBlocks.storageSilo).getItem();
	}
}
