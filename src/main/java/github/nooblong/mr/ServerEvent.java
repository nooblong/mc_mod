package github.nooblong.mr;

import github.nooblong.mr.MusicRestaurant;
import github.nooblong.mr.net.MySimpleNetworkHandler;
import github.nooblong.mr.util.OperateFile;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MusicRestaurant.MOD_ID, value = Dist.DEDICATED_SERVER)
public class ServerEvent {

    @SubscribeEvent
    public static void playerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        ServerPlayerEntity player = (ServerPlayerEntity)event.getPlayer();
        MySimpleNetworkHandler.sendToPlayer(player, OperateFile.getMusicList());
        System.out.println("send music list" + OperateFile.getMusicList());
    }

}
