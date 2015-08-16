package org.walterweight.storagesilo.proxy;

import org.walterweight.storagesilo.StorageSilo;
import org.walterweight.storagesilo.gui.GuiHandler;
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
