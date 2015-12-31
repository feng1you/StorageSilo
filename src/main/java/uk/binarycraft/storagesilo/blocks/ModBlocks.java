package uk.binarycraft.storagesilo.blocks;

import net.minecraft.block.Block;
import uk.binarycraft.storagesilo.StorageSilo;
import uk.binarycraft.storagesilo.blocks.craftingsilo.BlockCraftingSilo;
import uk.binarycraft.storagesilo.blocks.storagesilo.BlockStorageSilo;

public class ModBlocks
{

	public static Block storageSilo;
	public static Block craftingSilo;


	public static void preInit()
	{
		if (StorageSilo.storageSiloEnabled)
			storageSilo = new BlockStorageSilo();

		if (StorageSilo.craftingSiloEnabled)
			craftingSilo = new BlockCraftingSilo();
	}
}