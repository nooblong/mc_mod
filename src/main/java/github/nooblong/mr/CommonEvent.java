package github.nooblong.mr;

import com.mojang.brigadier.CommandDispatcher;
import github.nooblong.mr.command.MrCommand;
import net.minecraft.command.CommandSource;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod.EventBusSubscriber(modid = MusicRestaurant.MOD_ID)
public class CommonEvent {

    @SubscribeEvent
    public static void onServerStartEvent(FMLServerStartingEvent event){
        System.out.println("register command");
        CommandDispatcher<CommandSource> commandDispatcher = event.getCommandDispatcher();
        MrCommand.register(commandDispatcher);
    }
}
