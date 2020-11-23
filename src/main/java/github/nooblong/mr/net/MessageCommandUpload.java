package github.nooblong.mr.net;

import github.nooblong.mr.MusicProcessor;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class MessageCommandUpload implements Message {

    String path;

    public MessageCommandUpload(){}

    public MessageCommandUpload(String path){
        this.path = path;
    }

    @Override
    public void executeServerSide(NetworkEvent.Context context) {

    }

    @Override
    public void executeClientSide(NetworkEvent.Context context) {
        MusicProcessor.sendMusic(path);
    }

    @Override
    public MessageCommandUpload fromBytes(PacketBuffer buf) {
        this.path = buf.readString();
        return this;
    }

    @Override
    public void toBytes(PacketBuffer buf) {
        buf.writeString(this.path);
    }
}
