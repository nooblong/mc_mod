package github.nooblong.mr.net;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.io.IOException;
import java.util.UUID;

public class MessageMusic implements Message {

    private UUID musicUUID;
    private byte[] bytes;

    public MessageMusic(){}

    public MessageMusic(UUID musicUUID, byte[] bytes) throws IOException{
        this.musicUUID = musicUUID;
        this.bytes = bytes;
        if (bytes.length > 100_0000) {
            throw new IOException("Music too large: " + bytes.length + " bytes (max 100 0000)");
        }
    }

    @Override
    public void executeServerSide(NetworkEvent.Context context) {

    }

    @Override
    public void executeClientSide(NetworkEvent.Context context) {

    }

    @Override
    public Object fromBytes(PacketBuffer buf) {
        musicUUID = buf.readUniqueId();
        bytes = buf.readByteArray();
        return this;
    }

    @Override
    public void toBytes(PacketBuffer buf) {
        buf.writeUniqueId(musicUUID);
        buf.writeByteArray(bytes);
    }
}
