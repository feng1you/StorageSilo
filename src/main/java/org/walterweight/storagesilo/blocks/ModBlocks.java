package org.walterweight.storagesilo.blocks;


import org.walterweight.storagesilo.blocks.craftingsilo.BlockCraftingSilo;
import org.walterweight.storagesilo.blocks.storagesilo.BlockStorageSilo;
import net.minecraft.block.Block;

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
