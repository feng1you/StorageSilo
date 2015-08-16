package org.walterweight.storagesilo.gui;

import org.walterweight.storagesilo.blocks.storagesilo.ContainerStorageSilo;
import org.walterweight.storagesilo.blocks.storagesilo.GuiStorageSilo;
import org.walterweight.storagesilo.blocks.storagesilo.TileEntityStorageSilo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	public enum GUI {

		STORAGESILO(0);

		public int ordinal;

		private GUI(int id) {
			this.ordinal = id;
		}

		private static GUI[] allValues = values();

		public static GUI fromOrdinal(int n) {
			return allValues[n];
		}
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		GUI gui = GUI.fromOrdinal(ID);
		if (gui == GUI.STORAGESILO) {
			TileEntityStorageSilo tile = (TileEntityStorageSilo) world.getTileEntity(x, y, z);
			return new ContainerStorageSilo(player, tile);
		}
		return null;

	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		GUI gui = GUI.fromOrdinal(ID);

		if (gui == GUI.STORAGESILO) {
			TileEntityStorageSilo tile = (TileEntityStorageSilo) world.getTileEntity(x, y, z);
			return new GuiStorageSilo(player, tile);
		}

		return null;
	}

}
