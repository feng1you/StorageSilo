package uk.binarycraft.storagesilo.blocks.craftingsilo;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import uk.binarycraft.storagesilo.StorageSilo;
import uk.binarycraft.storagesilo.blocks.BlockContainerBase;
import uk.binarycraft.storagesilo.gui.GuiHandler;

//import net.minecraft.client.renderer.texture.IIconRegister;
//import net.minecraft.util.IIcon;

public class BlockCraftingSilo extends BlockContainerBase
{

	//@SideOnly(Side.CLIENT)
	//protected IIcon icon_sides;
	//@SideOnly(Side.CLIENT)
	//protected IIcon icon_topbottom;


	public BlockCraftingSilo()
	{
		super(Material.iron, "craftingsilo", 2.5f, null);
		setStepSound(soundTypeMetal);
		isBlockContainer = true;
	}


//	@SideOnly(Side.CLIENT)
//	@Override
//	public void registerBlockIcons(IIconRegister ir)
//	{
//		this.icon_topbottom = ir.registerIcon(getTexture("craftingsilo/craftingsilo_topbottom"));
//		this.icon_sides = ir.registerIcon(getTexture("craftingsilo/craftingsilo_sides"));
//	}


//	@Override
//	public IIcon getIcon(int side, int meta)
//	{
//		//int frontSide = getOrientation(meta, false);
//		//if (side == frontSide) {
//		if (side == 0 || side == 1)
//		{
//			return icon_topbottom;
//		}
//
//		return this.icon_sides;
//	}


//	@Override
//	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack)
//	{
//		int frontSide = determineOrientation(world, x, y, z, entity);
//		world.setBlockMetadataWithNotify(x, y, z, frontSide, 2);
//
//	}


	@Override
	public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing p6, float p7, float p8, float p9)
	{
		player.openGui(StorageSilo.instance, GuiHandler.GUI.STORAGESILO.ordinal, world, blockPos.getX(), blockPos.getY(), blockPos.getZ());
		return true;

	}


//	@Override
//	public boolean hasTileEntity(int metadata)
//	{
//		return true;
//	}


	@Override
	public TileEntity createNewTileEntity(World world, int p1)
	{
		return new TileEntityCraftingSilo();
	}


	@Override
	public void breakBlock(World world, BlockPos blockPos, IBlockState blockState)
	{
		TileEntityCraftingSilo tileentity = (TileEntityCraftingSilo) world.getTileEntity(blockPos);

		dropInventory(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), blockState.getBlock(), tileentity);

		super.breakBlock(world, blockPos, blockState);
	}


	@Override
	public int getRenderType()
	{
		return 3;
	}
}
