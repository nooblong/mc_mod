package github.nooblong.mr.net;

import github.nooblong.mr.MusicRestaurant;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.List;

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
        INSTANCE.registerMessage(
                ++id,
                MusicListPacket.class,
                MusicListPacket::encode,
                MusicListPacket::new,
                MusicListPacket::handle
        );
        INSTANCE.registerMessage(
                ++id,
                GuiDataPacket.class,
                GuiDataPacket::encode,
                GuiDataPacket::new,
                GuiDataPacket::handle
        );
    }

    public static void sendToServer(String name, byte[] bytes, int packageNum, int lastPackageSize){
        int length = bytes.length;
        MusicPacket packet = new MusicPacket(name, length, bytes, packageNum, lastPackageSize);
        INSTANCE.sendToServer(packet);
    }

    public static void sendToPlayer(ServerPlayerEntity playerEntity, List<String> fileList){
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> playerEntity), new MusicListPacket(fileList));
    }

}
