package github.nooblong.mr.net;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class MusicListPacket {
    private List<String> list;
    private int num;

    public MusicListPacket(List<String> list) {
        this.list = list;
        this.num = list.size();
    }

    public MusicListPacket(PacketBuffer packetBuffer){
        decode(packetBuffer);
    }

    public void encode(final PacketBuffer packetBuffer){
        packetBuffer.writeInt(num);
        for (String s : list){
            packetBuffer.writeString(s);
        }
    }

    public void decode(final PacketBuffer packetBuffer){
        list = new ArrayList<>();
        int num = packetBuffer.readInt();
        for (int i = 0; i < num; i++) {
            String s = packetBuffer.readString();
            list.add(s);
        }
    }

    public static void handle(MusicListPacket msg, Supplier<NetworkEvent.Context> ctx){
        ctx.get().enqueueWork(()->{
            System.out.println("get music list");
            System.out.println(msg.list);
        });
        ctx.get().setPacketHandled(true);
    }
}
