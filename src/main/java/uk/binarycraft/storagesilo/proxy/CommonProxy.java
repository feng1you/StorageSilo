package uk.binarycraft.storagesilo.proxy;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import uk.binarycraft.storagesilo.StorageSilo;
import uk.binarycraft.storagesilo.gui.GuiHandler;

public class CommonProxy
{

	public void initCommon()
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(StorageSilo.instance, new GuiHandler());
	}


	public void initClient()
	{
	}


	public void initServer()
	{
	}


	public void registerItemModels()
	{

	}
}
