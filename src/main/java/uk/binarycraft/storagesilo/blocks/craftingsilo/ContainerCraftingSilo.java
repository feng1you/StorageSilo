package uk.binarycraft.storagesilo.blocks.craftingsilo;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import uk.binarycraft.storagesilo.inventory.ContainerBase;
import uk.binarycraft.storagesilo.inventory.SlotSearchable;

public class ContainerCraftingSilo extends ContainerBase {

	private int numRows;
	private TileEntityCraftingSilo deepStorageChest;
	public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);


	public ContainerCraftingSilo(EntityPlayer player, TileEntityCraftingSilo deepStorageChest) {
		super(deepStorageChest);
		this.deepStorageChest = deepStorageChest;

		this.numRows = deepStorageChest.getSizeInventory() / 9;

		for (int row = 0; row < numRows; ++row) {
			for (int slot = 0; slot < 9; ++slot) {
				this.addSlotToContainer(
						new SlotSearchable(this.deepStorageChest, slot + row * 9, 8 + slot * 18, 22 + row * 18));
			}
		}

		// Add crafting Matrix - commented out, matrix no longer persistent
		//for (int i=0; i<9; i++){
		//	craftMatrix.setInventorySlotContents(i, deepStorageChest.getStackInSlot(deepStorageChest.getSizeInventory()-10+i));
		//}

		// Add crafting output slot
		this.addSlotToContainer(new SlotCrafting(player, craftMatrix, deepStorageChest, 9, 130, 97));
		addCraftingGrid(craftMatrix, 0, 31, 80, 3, 3); //67->17

		addPlayerInventorySlots(player.inventory);
	}

	@Override
	protected void addPlayerInventorySlots(InventoryPlayer inventory) {
		// player inventory
		for (int row = 0; row < 3; ++row) {
			for (int slot = 0; slot < 9; ++slot) {
				this.addSlotToContainer(new Slot(inventory, slot + row * 9 + 9, 8 + slot * 18, 139 + row * 18));
			}
		}

		// player hotbar
		for (int row = 0; row < 9; ++row) {
			this.addSlotToContainer(new Slot(inventory, row, 8 + row * 18, 196 + 1));

		}
	}

	private void addCraftingGrid(IInventory inventory, int startSlot, int x, int y, int width, int height) {
		int i = 0;
		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				this.addSlotToContainer(new Slot(inventory, startSlot + i++, x + (w*18), y + (h*18)));
			}
		}
	}
}