package github.nooblong.mr.util;

import github.nooblong.mr.MusicRestaurant;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;

@Mod.EventBusSubscriber(modid = MusicRestaurant.MOD_ID, value = Dist.DEDICATED_SERVER)
public class ServerEventSubscriber {

    @SubscribeEvent
    public static void serverStarted(FMLServerStartedEvent event) {
        try {
            System.out.println("nettyStart");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
