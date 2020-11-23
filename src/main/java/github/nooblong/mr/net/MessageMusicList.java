package github.nooblong.mr.net;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;

public class MessageMusicList implements Message {

    private int num;
    private List<String> list = new ArrayList<>();

    public MessageMusicList(){
    }
    public MessageMusicList(int num, List<String> musicList){
        this.num = num;
        this.list = musicList;
    }

    @Override
    public void executeServerSide(NetworkEvent.Context context) {

    }

    @Override
    public void executeClientSide(NetworkEvent.Context context) {

    }

    @Override
    public MessageMusicList fromBytes(PacketBuffer buf) {
        this.num = buf.readInt();
        if (this.num != 0) {
            for (int i = 0; i < num; i++) {
                list.add(buf.readString());
            }
        }
        return this;
    }

    @Override
    public void toBytes(PacketBuffer buf) {
        buf.writeInt(this.num);
        if (this.num != 0) {
            for (int i = 0; i < this.num; i++) {
                buf.writeString(list.get(i));
            }
        }
    }
}
