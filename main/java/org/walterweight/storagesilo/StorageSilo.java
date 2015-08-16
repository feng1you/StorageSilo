package org.walterweight.storagesilo;


import org.walterweight.storagesilo.blocks.ModBlocks;
import org.walterweight.storagesilo.blocks.storagesilo.TileEntityStorageSilo;
import org.walterweight.storagesilo.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MODID, version = Reference.VERSION, name = Reference.MODNAME)
public class StorageSilo
{

    @Instance(Reference.MODID)
    public static StorageSilo instance;

    @SidedProxy(clientSide = "org.walterweight.storagesilo.proxy.ClientProxy", serverSide = "org.walterweight.storagesilo.proxy.CommonProxy")
    public static CommonProxy proxy;
  
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ModBlocks.init();
        registerTileEntities();
    }

    //todo: maybe this should be moved into its own thing
    private void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityStorageSilo.class, "tileDeepStorageChest");
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
    public void serverStopping(FMLServerStoppingEvent event){
    }
    
    @EventHandler
    public void serverStarted(FMLServerStartedEvent event){
    }

}
