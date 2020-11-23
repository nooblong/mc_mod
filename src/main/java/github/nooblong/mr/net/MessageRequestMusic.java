package github.nooblong.mr.net;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;

public class MessageRequestMusic implements Message {

    private UUID musicUUID;

    public MessageRequestMusic() {
    }

    public MessageRequestMusic(UUID musicUUID) {
        this.musicUUID = musicUUID;
    }

    @Override
    public void executeServerSide(NetworkEvent.Context context) {

    }

    @Override
    public void executeClientSide(NetworkEvent.Context context) {

    }

    @Override
    public Object fromBytes(PacketBuffer buf) {
        return null;
    }

    @Override
    public void toBytes(PacketBuffer buf) {

    }
}
