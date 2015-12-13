package uk.binarycraft.storagesilo.blocks.storagesilo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import uk.binarycraft.storagesilo.StorageSilo;
import uk.binarycraft.storagesilo.blocks.BlockContainerBase;
import uk.binarycraft.storagesilo.gui.GuiHandler.GUI;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockStorageSilo extends BlockContainerBase {

	@SideOnly(Side.CLIENT)
	protected IIcon icon_sides;
	@SideOnly(Side.CLIENT)
	protected IIcon icon_topbottom;
	
	public BlockStorageSilo() {
		super(Material.iron, "storagesilo", 2.5f, null);
		setStepSound(soundTypeMetal);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister ir) {
		this.icon_topbottom = ir.registerIcon(getTexture("storagesilo/storagesilo_topbottom"));
		this.icon_sides = ir.registerIcon(getTexture("storagesilo/storagesilo_sides"));
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		int frontSide = getOrientation(meta, false);
		//if (side == frontSide) {
		if (side == 0 || side == 1) {
			return icon_topbottom;
		}

		return this.icon_sides;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
		int frontSide = determineOrientation(world, x, y, z, entity);
		world.setBlockMetadataWithNotify(x, y, z, frontSide, 2);

	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p6, float p7, float p8,
			float p9) {
		player.openGui(StorageSilo.instance, GUI.STORAGESILO.ordinal, world, x, y, z);
		return true;

	}

	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	};

	@Override
	public TileEntity createNewTileEntity(World world, int p1) {
		return new TileEntityStorageSilo();
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		TileEntityStorageSilo tileentity = (TileEntityStorageSilo) world.getTileEntity(x, y, z);

		dropInventory(world, x, y, z, block, tileentity);

		super.breakBlock(world, x, y, z, block, meta);
	}
}
