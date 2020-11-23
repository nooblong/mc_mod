package github.nooblong.mr;

import github.nooblong.mr.MusicRestaurant;
import github.nooblong.mr.gui.RubyChestGui;
import github.nooblong.mr.init.ModContainerTypes;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = MusicRestaurant.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvent {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event){
        ScreenManager.registerFactory(ModContainerTypes.RUBY_CHEST.get(), RubyChestGui::new);
    }
}
