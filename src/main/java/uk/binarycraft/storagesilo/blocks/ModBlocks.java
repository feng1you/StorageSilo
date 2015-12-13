package uk.binarycraft.storagesilo.blocks;

import net.minecraft.block.Block;
import uk.binarycraft.storagesilo.blocks.craftingsilo.BlockCraftingSilo;
import uk.binarycraft.storagesilo.blocks.storagesilo.BlockStorageSilo;

public class ModBlocks
{

	public static Block storageSilo;
	public static Block craftingSilo;


	public static void init()
	{
		storageSilo = new BlockStorageSilo();
		craftingSilo = new BlockCraftingSilo();

		storageSilo.setBlockName("StorageSilo");
		craftingSilo.setBlockName("CraftingSilo");
	}
}