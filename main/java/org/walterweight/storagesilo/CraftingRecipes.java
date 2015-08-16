package org.walterweight.storagesilo;

import cpw.mods.fml.common.registry.GameRegistry;
import org.walterweight.storagesilo.blocks.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;


public class CraftingRecipes {

	ItemStack storageSilo = new ItemStack(ModBlocks.storageSilo);
	ItemStack enderChest = new ItemStack(Blocks.ender_chest);
	ItemStack chest = new ItemStack(Blocks.chest);

	public void init() {
		GameRegistry.addRecipe(storageSilo, "ccc", "cec", "ccc", 'c', chest, 'e', enderChest);
	}
}
