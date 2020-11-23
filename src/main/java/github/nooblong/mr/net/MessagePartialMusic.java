package github.nooblong.mr.net;

import github.nooblong.mr.MusicRestaurant;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;

public class MessagePartialMusic implements Message {

    private UUID musicUUID;
    private int offset;
    private int length;
    private byte[] bytes;

    public MessagePartialMusic(){}

    public MessagePartialMusic(UUID musicUUID, int offset, int length, byte[] bytes){
        this.bytes = bytes;
        this.musicUUID = musicUUID;
        this.offset = offset;
        this.length = length;
    }

    @Override
    public void executeServerSide(NetworkEvent.Context context) {
        MusicRestaurant.LOGGER.info("server receive message " + musicUUID);
        MusicRestaurant.PACKET_MANAGER.addBytes(context.getSender(), musicUUID, offset, length, bytes);
    }

    @Override
    public void executeClientSide(NetworkEvent.Context context) {

    }

    @Override
    public MessagePartialMusic fromBytes(PacketBuffer buf) {
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
