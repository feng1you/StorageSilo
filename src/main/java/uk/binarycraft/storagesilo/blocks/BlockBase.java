package uk.binarycraft.storagesilo.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import uk.binarycraft.storagesilo.StorageSilo;

public class BlockBase extends Block
{

	protected BlockBase(Material material, String name, float hardness, Class<? extends ItemBlock> itemBlock)
	{
		super(material);
		setUnlocalizedName(name);
		setCreativeTab(StorageSilo.storageSiloCreativeTab);
		//setBlockTextureName(getTexture(name));
		setHardness(hardness);
		if (itemBlock != null)
		{
			GameRegistry.registerBlock(this, itemBlock, name);
		} else
		{
			GameRegistry.registerBlock(this, name);
		}

	}


	protected BlockBase(Material material, String name, float hardness, String harvestTool, int harvestLevel, Class<? extends ItemBlock> itemBlock)
	{
		this(material, name, hardness, itemBlock);
		setHarvestLevel(harvestTool, harvestLevel);
	}


	protected String getTexture(String name)
	{
		return "quantumflux:" + name;
	}


	@Override
	public void onBlockPlacedBy(World world, BlockPos blockPos, IBlockState blockState, EntityLivingBase entity, ItemStack itemStack)
	{
	}


	protected int determineOrientation(World world, int x, int y, int z, EntityLivingBase entity)
	{
		if (MathHelper.abs((float) entity.posX - (float) x) < 2.0F && MathHelper.abs((float) entity.posZ - (float) z) < 2.0F)
		{
			double d0 = entity.posY + 1.82D - (double) entity.getYOffset();

			if (d0 - (double) y > 2.0D)
			{
				return 1;
			}

			if ((double) y - d0 > 0.0D)
			{
				return 0;
			}
		}

		int l = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		return l == 0 ? 2 : (l == 1 ? 5 : (l == 2 ? 3 : (l == 3 ? 4 : 0)));

	}


	protected int getOrientation(int meta, boolean allowUpDown)
	{
		int value = meta & 7;

		if (!allowUpDown)
		{
			if (value == 0 || value == 1)
			{
				//todo:determine which direction the player was facing
				value = 3;
			}
		}
		return value;
	}

}
