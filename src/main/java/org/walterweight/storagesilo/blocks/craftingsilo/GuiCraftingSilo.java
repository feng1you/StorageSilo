package org.walterweight.storagesilo.blocks.craftingsilo;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.walterweight.storagesilo.inventory.SlotSearchable;

import java.util.*;

public class GuiCraftingSilo extends GuiContainer
{

	private static final ResourceLocation guiTexture = new ResourceLocation(
			"storagesilo:textures/gui/craftingsilo.png");
	private float currentScroll;
	private boolean isScrolling;
	private boolean wasClicking;
	private GuiTextField searchField;
	// private boolean unknownBool;
	public TileEntityCraftingSilo tileEntityCraftingSilo;
	private ContainerCraftingSilo container;
	private ItemSorter sorter = new ItemSorter();
	private List<SlotSearchable> items = new ArrayList();
	private boolean hasBeenDrawn = false;


	public GuiCraftingSilo(EntityPlayer player, TileEntityCraftingSilo tileEntity)
	{
		super(new ContainerCraftingSilo(player, tileEntity));

		this.ySize = 222;
		this.xSize = 195;
		this.tileEntityCraftingSilo = tileEntity;
		this.allowUserInput = true;

	}


	@Override
	public void initGui()
	{
		super.initGui();
		this.container = ((ContainerCraftingSilo) this.inventorySlots);
		Keyboard.enableRepeatEvents(true);
		this.searchField = new GuiTextField(this.fontRendererObj, this.guiLeft + 81, this.guiTop + 7, 85,
				this.fontRendererObj.FONT_HEIGHT);
		this.searchField.setMaxStringLength(22);
		this.searchField.setEnableBackgroundDrawing(false);
		this.searchField.setVisible(true);
		this.searchField.setTextColor(16777215);
		this.searchField.setCanLoseFocus(false);
		this.searchField.setFocused(true);

		buttonList.add(new GuiButton(1, guiLeft + 86, guiTop + 79, 8, 8, "x"));
	}


	protected void actionPerformed(GuiButton guiButton)
	{
		if (guiButton.id != 1)
			return;

		clearCraftingGrid();
	}


	public void onGuiClosed()
	{
		super.onGuiClosed();
		clearCraftingGrid();
		Keyboard.enableRepeatEvents(false);
	}


	public void drawScreen(int par1, int par2, float par3)
	{
		if (!hasBeenDrawn)
		{
			// this will just sort the inventory for the first time it is opened
			hasBeenDrawn = true;
			updateSearch(true);
		}

		boolean flag = Mouse.isButtonDown(0);
		int k = this.guiLeft;
		int l = this.guiTop;
		int i1 = k + 175;
		int j1 = l + 22;
		int k1 = i1 + 12;
		int l1 = j1 + 108;

		if (!this.wasClicking && flag && par1 >= i1 && par2 >= j1 && par1 < k1 && par2 < l1)
		{
			this.isScrolling = this.needsScrollBars();
		}

		if (!flag)
		{
			this.isScrolling = false;
		}

		this.wasClicking = flag;

		if (this.isScrolling)
		{
			this.currentScroll = ((float) (par2 - j1) - 7.5F) / ((float) (l1 - j1) - 15.0F);

			if (this.currentScroll < 0.0F)
			{
				this.currentScroll = 0.0F;
			}

			if (this.currentScroll > 1.0F)
			{
				this.currentScroll = 1.0F;
			}

			scrollTo(this.currentScroll);
		}
		this.updateScreen();

		super.drawScreen(par1, par2, par3);
	}


	private void clearCraftingGrid()
	{
		System.out.println("Clearing Crafting Grid");
		for (int craftingInventoryLoop = 0; craftingInventoryLoop < 10; craftingInventoryLoop++)
		{
			ItemStack itemStackToClear = container.craftMatrix.getStackInSlot(craftingInventoryLoop);

			if (itemStackToClear == null)
				continue;

//			itemStackToClear = AttemptToMoveCraftingGridSlotStackToInventory(itemStackToClear);

			if (itemStackToClear.stackSize > 0)
				DumpCraftingFridSlotStackToWorld(itemStackToClear);
		}
	}


	private void DumpCraftingFridSlotStackToWorld(ItemStack itemStackToClear)
	{
		Random rand = new Random();
		double randX = rand.nextFloat() * 0.8F + 0.1F;
		double randY = rand.nextFloat() * 0.8F + 0.1F;
		double randZ = rand.nextFloat() * 0.8F + 0.1F;
		World world = tileEntityCraftingSilo.getWorldObj();
		int x = tileEntityCraftingSilo.xCoord;
		int y = tileEntityCraftingSilo.yCoord;
		int z = tileEntityCraftingSilo.zCoord;

		if (!world.isRemote)
		{
			System.out.println("Dumping stack of " + itemStackToClear.getDisplayName() + " to world");
			EntityItem itemsToDrop = new EntityItem(world, x + randX, y + randY, z + randZ, itemStackToClear);
			world.spawnEntityInWorld(itemsToDrop);
		}
	}


	private ItemStack AttemptToMoveCraftingGridSlotStackToInventory(ItemStack itemStackToClear)
	{
		for (int invSlotLoop = 0; invSlotLoop < tileEntityCraftingSilo.getSizeInventory(); invSlotLoop++)
		{
			ItemStack invStack = tileEntityCraftingSilo.getStackInSlot(invSlotLoop);

			if (invStack == null)
				return MoveItemsStackToEmptyInventorySlot(itemStackToClear, invSlotLoop, invStack);

			if (invStack.getDisplayName().equals(itemStackToClear.getDisplayName()))
				return MoveItemsToInventorySlot(itemStackToClear, invSlotLoop, invStack);

		}
		return itemStackToClear;
	}


	private ItemStack MoveItemsStackToEmptyInventorySlot(ItemStack itemStackToClear, int invSlotLoop, ItemStack invStack)
	{
		System.out.println("Found empty slot in inventory");
		TransferAllItemsInStackToInventoryStack(itemStackToClear, invSlotLoop, invStack);
		itemStackToClear.stackSize = 0;
		return itemStackToClear;
	}


	private ItemStack MoveItemsToInventorySlot(ItemStack itemStackToClear, int invSlotLoop, ItemStack invStack)
	{
		System.out.println("Found inventorySlot with same items");
		if (invStack.stackSize + itemStackToClear.stackSize > invStack.getMaxStackSize())
			itemStackToClear = FillRemainderOfInventoryItemStack(itemStackToClear, invStack);
		else
			itemStackToClear = TransferAllItemsInStackToInventoryStack(itemStackToClear, invSlotLoop, invStack);

		tileEntityCraftingSilo.setInventorySlotContents(invSlotLoop, invStack);
		tileEntityCraftingSilo.markDirty();

		return itemStackToClear;
	}


	private ItemStack TransferAllItemsInStackToInventoryStack(ItemStack itemStackToClear, int invSlotLoop, ItemStack invStack)
	{
		System.out.println("Removing " + itemStackToClear.getDisplayName() + " from crafting grid");

		//	tileEntityCraftingSilo.setInventorySlotContents(invSlotLoop, invStack);
		//	tileEntityCraftingSilo.markDirty();

		itemStackToClear.stackSize = 0;

		if (invStack == null)
			invStack = new ItemStack(itemStackToClear.getItem());

		invStack.stackSize += itemStackToClear.stackSize;

		tileEntityCraftingSilo.setInventorySlotContents(invSlotLoop, invStack);
		tileEntityCraftingSilo.markDirty();

		return itemStackToClear;
	}


	private ItemStack FillRemainderOfInventoryItemStack(ItemStack itemStackToClear, ItemStack invStack)
	{
		System.out.println("Filling existing InventoryStack with " + itemStackToClear.getDisplayName() + " from crafting grid");
		itemStackToClear.stackSize -= (invStack.stackSize + itemStackToClear.stackSize - invStack.getMaxStackSize());
		invStack.stackSize = invStack.getMaxStackSize();

		return itemStackToClear;
	}


	private boolean needsScrollBars()
	{
		return tileEntityCraftingSilo.getSizeInventory() > 27; //Changed value from 54 to 27, as
	}


	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(guiTexture);
		int var5 = (this.width - this.xSize) / 2;
		int var6 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var5, var6, 20, 0, this.xSize, this.ySize);

		int i1 = this.guiLeft + 175;
		int k = this.guiTop + 22;
		int l = k + 108;
		if (this.needsScrollBars())
		{
			int x1 = i1;
			// int x2 = x1 + 12;
			int y1 = k + (int) ((float) (l - k - 17) * this.currentScroll);
			// int y2 = y1 + 15;

			this.drawTexturedModalRect(x1, y1, 0, 0, 12, 15);
		} else
		{
			this.drawTexturedModalRect(i1, k, 0, 16, 12, 15);
		}

		this.searchField.drawTextBox();

		if (tileEntityCraftingSilo.isDirty)
		{
			updateSearch(false);
			tileEntityCraftingSilo.isDirty = false;
		}

	}


	public boolean doesGuiPauseGame()
	{
		return false;
	}


	public void setInventorySlotContents(int i, ItemStack itemStack)
	{

		tileEntityCraftingSilo.setInventorySlotContents(i, itemStack);

		tileEntityCraftingSilo.markDirty();
	}


	@Override
	protected void keyTyped(char p_73869_1_, int p_73869_2_)
	{

		if (!this.checkHotbarKeys(p_73869_2_))
		{
			if (this.searchField.textboxKeyTyped(p_73869_1_, p_73869_2_))
			{
				this.updateSearch(true);
			} else
			{
				super.keyTyped(p_73869_1_, p_73869_2_);
			}
		}
	}


	public void handleMouseInput()
	{

		// removed because it was too laggy
		// if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) ||
		// Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
		// super.handleMouseInput();
		// return;
		// }

		int i = Mouse.getEventDWheel();

		if (i != 0 && this.needsScrollBars())
		{

			int j = tileEntityCraftingSilo.getSizeInventory() / 9 - 3; //changed from 6 to 3

			if (i > 0)
			{
				i = items.size() > 1000 ? 6 : 2;
			}
			if (i < 0)
			{
				i = items.size() > 1000 ? -6 : -2;
			}

			this.currentScroll = (float) ((double) this.currentScroll - (double) i / (double) j);
			if (this.currentScroll < 0.0F)
			{
				this.currentScroll = 0.0F;
			}
			if (this.currentScroll > 1.0F)
			{
				this.currentScroll = 1.0F;
			}
			this.scrollTo(this.currentScroll);
		} else
		{
			super.handleMouseInput();
		}
	}


	private void scrollTo(float position)
	{

		int numItems = items.size();

		for (int i = 0; i < tileEntityCraftingSilo.getSizeInventory(); i++)
		{
			SlotSearchable slot = (SlotSearchable) container.getSlot(i);
			slot.xDisplayPosition = -10000;
			slot.yDisplayPosition = -10000;
			slot.drawSlot = false;
		}

		// start is the row that should be drawn first
		int start = (int) Math.floor(position * Math.ceil((numItems / 9 - 3) / 1.0F)); // 6 changed to 3
		start = Math.max(start, 0);
		int startIndex = start * 9;
		int endIndex = startIndex + 26; // changed from 53 to 26
		for (int i = 0; i < numItems; i++)
		{
			SlotSearchable slot = items.get(i);
			if (i >= startIndex && i <= endIndex)
			{
				int x = (i - startIndex) % 9;
				int y = (i - startIndex - x) / 9;
				slot.xDisplayPosition = 8 + x * 18;
				slot.yDisplayPosition = 22 + y * 18;
				slot.drawSlot = true;
			}
		}
	}


	private void updateSearch(boolean resetScroll)
	{
		items.clear();
		for (int i = 0; i < tileEntityCraftingSilo.getSizeInventory(); i++)
		{
			SlotSearchable slot = (SlotSearchable) container.getSlot(i);
			slot.setMatchesSearch(searchField.getText());
			if (slot.getMatchesSearch())
			{
				items.add(slot);
			}
		}
		Collections.sort(items, sorter);
		if (resetScroll)
			this.currentScroll = 0;
		scrollTo(this.currentScroll);
	}


	public static class ItemSorter implements Comparator<Slot>
	{

		public int compare(Slot slot1, Slot slot2)
		{
			if (!slot1.getHasStack())
			{
				if (!slot2.getHasStack())
				{
					return 0;
				}
				return 1;
			}
			if (!slot2.getHasStack())
			{
				return -1;
			}
			// both have a stack if we reach this
			int id1 = Item.getIdFromItem(slot1.getStack().getItem());
			int id2 = Item.getIdFromItem(slot2.getStack().getItem());

			int t = intCompare(id1, id2);

			if (t == 0)
			{
				int m1 = slot1.getStack().getItemDamage();
				int m2 = slot2.getStack().getItemDamage();

				return intCompare(m1, m2);
			}

			return t;
		}


		public int intCompare(int a, int b)
		{
			if (a == b)
			{
				return 0;
			}
			if (a > b)
			{
				return -1;
			}
			return 1;
		}

	}

}