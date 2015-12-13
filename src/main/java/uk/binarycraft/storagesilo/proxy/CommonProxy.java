package uk.binarycraft.storagesilo.proxy;
import uk.binarycraft.storagesilo.StorageSilo;
import uk.binarycraft.storagesilo.gui.GuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class CommonProxy {

	public void initCommon() {
	    NetworkRegistry.INSTANCE.registerGuiHandler(StorageSilo.instance, new GuiHandler());
	}

	public void initClient() {
	}

	public void initServer() {
	}

}
