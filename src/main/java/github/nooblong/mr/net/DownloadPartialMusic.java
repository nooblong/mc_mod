package github.nooblong.mr.net;

import github.nooblong.mr.MusicRestaurant;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;

public class DownloadPartialMusic implements Message {

    private UUID musicUUID;
    private int offset;
    private int length;
    private byte[] bytes;

    public DownloadPartialMusic(){}

    public DownloadPartialMusic(UUID musicUUID, int offset, int length, byte[] bytes){
        this.bytes = bytes;
        this.musicUUID = musicUUID;
        this.offset = offset;
        this.length = length;
    }

    @Override
    public void executeServerSide(NetworkEvent.Context context) {

    }

    @Override
    public void executeClientSide(NetworkEvent.Context context) {
        MusicRestaurant.LOGGER.info("client receive download message " + musicUUID);
        MusicRestaurant.PACKET_MANAGER.addBytes(context.getSender(), musicUUID, offset, length, bytes);
    }

    @Override
    public DownloadPartialMusic fromBytes(PacketBuffer buf) {
        musicUUID = buf.readUniqueId();
        offset = buf.readInt();
        length = buf.readInt();
        bytes = buf.readByteArray();
        return this;
    }

    @Override
    public void toBytes(PacketBuffer buf) {
        buf.writeUniqueId(musicUUID);
        buf.writeInt(offset);
        buf.writeInt(length);
        buf.writeByteArray(bytes);
    }
}
