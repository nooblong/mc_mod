package github.nooblong.mr.net;

import github.nooblong.mr.MusicRestaurant;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class SimpleNetworkHandler {

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MusicRestaurant.MOD_ID, "main"),
            () -> "1.15.2",
            "1.15.2"::equals,
            "1.15.2"::equals
    );

    public static void registerMessage() {
        MusicRestaurant.LOGGER.info("MySimpleNetworkHandler.registerMessage");
        int id = 0;
        INSTANCE.registerMessage(
                ++id,
                MessagePartialMusic.class,
                MessagePartialMusic::toBytes,
                packetBuffer -> new MessagePartialMusic().fromBytes(packetBuffer),
                (messagePartialMusic, contextSupplier) -> messagePartialMusic.executeServerSide(contextSupplier.get())
        );
        INSTANCE.registerMessage(
                ++id,
                DownloadPartialMusic.class,
                DownloadPartialMusic::toBytes,
                packetBuffer -> new DownloadPartialMusic().fromBytes(packetBuffer),
                (downloadPartialMusic, contextSupplier) -> downloadPartialMusic.executeServerSide(contextSupplier.get())
        );
        INSTANCE.registerMessage(
                ++id,
                MessageCommandUpload.class,
                MessageCommandUpload::toBytes,
                packetBuffer -> new MessageCommandUpload().fromBytes(packetBuffer),
                ((messageCommandUpload, contextSupplier) -> messageCommandUpload.executeClientSide(contextSupplier.get()))
        );
        INSTANCE.registerMessage(
                ++id,
                GuiDataPacket.class,
                GuiDataPacket::encode,
                GuiDataPacket::new,
                GuiDataPacket::handle
        );
        INSTANCE.registerMessage(
                ++id,
                MessageRequestMusicList.class,
                MessageRequestMusicList::toBytes,
                packetBuffer -> new MessageRequestMusicList().fromBytes(packetBuffer),
                ((messageRequestMusicList, contextSupplier) -> messageRequestMusicList.executeServerSide(contextSupplier.get()))
        );
        INSTANCE.registerMessage(
                ++id,
                MessageMusicList.class,
                MessageMusicList::toBytes,
                packetBuffer -> new MessageMusicList().fromBytes(packetBuffer),
                ((messageMusicList, contextSupplier) -> messageMusicList.executeClientSide(contextSupplier.get()))
        );
    }
}
