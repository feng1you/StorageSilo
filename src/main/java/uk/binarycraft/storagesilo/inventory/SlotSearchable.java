package uk.binarycraft.storagesilo.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class SlotSearchable extends Slot
{

	public boolean drawSlot = true;
	private boolean matchesSearch = true;


	public SlotSearchable(IInventory inventory, int p1, int p2, int p3)
	{
		super(inventory, p1, p2, p3);
	}


	public boolean getMatchesSearch()
	{
		return this.matchesSearch;
	}


	public void setMatchesSearch(String search)
	{
		search = search.trim().toLowerCase();
		if (search.length() < 1)
		{
			matchesSearch = true;
			return;
		}

		String[] terms = search.split(",");
		for (String term : terms)
		{
			term = term.trim();
			if (getHasStack())
			{
				matchesSearch = this.getStack().getDisplayName().toLowerCase().contains(term);
				if (matchesSearch)
				{
					break;

				}
			}
		}
	}

}
