package github.nooblong.mr;

import github.nooblong.mr.net.SimpleNetworkHandler;
import github.nooblong.mr.util.OperateFile;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = MusicRestaurant.MOD_ID, value = Dist.DEDICATED_SERVER)
public class ServerEvent {

}
