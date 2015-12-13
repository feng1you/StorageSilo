package uk.binarycraft.storagesilo;


import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import uk.binarycraft.storagesilo.blocks.ModBlocks;
import uk.binarycraft.storagesilo.blocks.craftingsilo.TileEntityCraftingSilo;
import uk.binarycraft.storagesilo.blocks.storagesilo.TileEntityStorageSilo;
import uk.binarycraft.storagesilo.proxy.CommonProxy;

@Mod(modid = Reference.MODID, version = Reference.VERSION, name = Reference.MODNAME)
public class StorageSilo
{

	public static int siloCapacity;
	public static CreativeTab storageSiloCreativeTab;
	@Instance(Reference.MODID)
	public static StorageSilo instance;
	@SidedProxy(clientSide = "ClientProxy", serverSide = "CommonProxy")
	public static CommonProxy proxy;
	private ItemStack storageSilo = new ItemStack(ModBlocks.storageSilo);


	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		ModBlocks.init();
		registerTileEntities();
		getConfiguration(event);
		storageSiloCreativeTab = new CreativeTab("StorageSilo");
	}


	private void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityStorageSilo.class, "tileDeepStorageChest");
		GameRegistry.registerTileEntity(TileEntityCraftingSilo.class, "tileEntityCraftingSilo");
	}


	private void getConfiguration(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		siloCapacity = config.getInt("StorageSiloCapacity", Configuration.CATEGORY_GENERAL, 999, 54, 999, "The number of available slots in each StorageSilo");
		config.save();
	}


	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.initCommon();
		proxy.initServer();
		proxy.initClient();
	}


	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		new CraftingRecipes().init();
	}


	@EventHandler
	public void serverStopping(FMLServerStoppingEvent event)
	{
	}


	@EventHandler
	public void serverStarted(FMLServerStartedEvent event)
	{
	}

}
