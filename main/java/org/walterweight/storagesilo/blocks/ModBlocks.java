package org.walterweight.storagesilo.blocks;


import org.walterweight.storagesilo.blocks.storagesilo.BlockStorageSilo;
import net.minecraft.block.Block;

public class ModBlocks
{
    public static Block storageSilo;

    public static void init()
    {
        storageSilo = new BlockStorageSilo();
    }

}
