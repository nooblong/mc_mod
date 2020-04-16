package github.nooblong.lolskill;

import github.nooblong.lolskill.client.CreativeTab;
import github.nooblong.lolskill.client.ModInfo;
import github.nooblong.lolskill.proxy.CommonProxy;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.IEventListener;
import org.apache.logging.log4j.Logger;

@Mod(modid = ModInfo.MODID, name = ModInfo.NAME, version = ModInfo.VERSION)
public class LolSkill {

    private static Logger logger;

    @Mod.Instance("lolskill")
    public static LolSkill instance;

    @SidedProxy(clientSide = ModInfo.CLIENT_PROXY, serverSide = ModInfo.SERVER_PROXY)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        proxy.preInit(event);

        logger = event.getModLog();

        CreativeTab.tabRegister();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

        proxy.init(event);

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        proxy.postInit(event);

    }


}
