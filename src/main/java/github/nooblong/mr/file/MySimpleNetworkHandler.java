package github.nooblong.mr.file;

import github.nooblong.mr.MusicRestaurant;
import io.netty.handler.codec.socks.SocksProtocolVersion;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.core.net.Protocol;

import java.awt.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class MySimpleNetworkHandler {

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MusicRestaurant.MOD_ID, "main"),
            () -> "1.15.2",
            "1.15.2"::equals,
            "1.15.2"::equals
    );

    public static void registerMessage(){
        MusicRestaurant.LOGGER.info("MySimpleNetworkHandler.registerMessage");
        int id = 0;
        INSTANCE.registerMessage(
                ++id,
                MusicPacket.class,
                MusicPacket::encode,
                MusicPacket::new,
                MusicPacket::handle
        );
    }

    public static void sendToServer(String name, byte[] bytes){
        int length = bytes.length;
        MusicPacket packet = new MusicPacket(name, length, bytes);
        INSTANCE.sendToServer(packet);
    }

}
