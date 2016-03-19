package uk.binarycraft.storagesilo.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import uk.binarycraft.storagesilo.blocks.ModBlocks;

public class ClientProxy extends CommonProxy
{
	//public static int renderPass;


	@Override
	public void initClient()
	{

	}


	@Override
	public void registerItemModels()
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(ModBlocks.craftingSilo), 0, new ModelResourceLocation("storagesilo:craftingsilo", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(ModBlocks.storageSilo), 0, new ModelResourceLocation("storagesilo:storagesilo", "inventory"));
	}
}
