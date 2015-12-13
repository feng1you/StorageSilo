package uk.binarycraft.storagesilo.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import uk.binarycraft.storagesilo.blocks.craftingsilo.BlockCraftingSilo;
import uk.binarycraft.storagesilo.blocks.craftingsilo.ContainerCraftingSilo;
import uk.binarycraft.storagesilo.blocks.craftingsilo.GuiCraftingSilo;
import uk.binarycraft.storagesilo.blocks.craftingsilo.TileEntityCraftingSilo;
import uk.binarycraft.storagesilo.blocks.storagesilo.BlockStorageSilo;
import uk.binarycraft.storagesilo.blocks.storagesilo.ContainerStorageSilo;
import uk.binarycraft.storagesilo.blocks.storagesilo.GuiStorageSilo;
import uk.binarycraft.storagesilo.blocks.storagesilo.TileEntityStorageSilo;

public class GuiHandler implements IGuiHandler
{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		Block block = world.getBlock(x, y, z);
		if (block instanceof BlockStorageSilo)
		{
			TileEntityStorageSilo tile = (TileEntityStorageSilo) world.getTileEntity(x, y, z);
			return new ContainerStorageSilo(player, tile);
		}

		if (block instanceof BlockCraftingSilo)
		{
			TileEntityCraftingSilo tile = (TileEntityCraftingSilo) world.getTileEntity(x, y, z);
			return new ContainerCraftingSilo(player, tile);
		}
		return null;

	}


	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		//GUI gui = GUI.fromOrdinal(ID);
		Block block = world.getBlock(x, y, z);
		if (block instanceof BlockStorageSilo)
		{
			TileEntityStorageSilo tile = (TileEntityStorageSilo) world.getTileEntity(x, y, z);
			return new GuiStorageSilo(player, tile);
		}

		if (block instanceof BlockCraftingSilo)
		{
			TileEntityCraftingSilo tile = (TileEntityCraftingSilo) world.getTileEntity(x, y, z);
			return new GuiCraftingSilo(player, tile);
		}

		return null;
	}


	public enum GUI
	{

		STORAGESILO(0),
		CRAFTINGSILO(1);

		private static GUI[] allValues = values();
		public int ordinal;


		private GUI(int id)
		{
			this.ordinal = id;
		}


		public static GUI fromOrdinal(int n)
		{
			return allValues[n];
		}
	}

}
