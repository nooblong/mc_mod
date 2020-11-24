package github.nooblong.mr.net;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.ArrayList;
import java.util.Arrays;

public class MessageRequestMusicList implements Message {

    private int fun;

    public MessageRequestMusicList(){}

    public MessageRequestMusicList(int fun){
        this.fun = fun;
    }

    @Override
    public void executeServerSide(NetworkEvent.Context context) {
        System.out.println("read fun " + fun);

        ServerPlayerEntity playerMP = context.getSender();
        MessageMusicList messageMusicList = new MessageMusicList(5, new ArrayList<>(Arrays.asList("aaa","bbb","ccc","ddd","eee")));
        System.out.println("send packet " + messageMusicList);
        SimpleNetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() ->playerMP), messageMusicList);
    }

    @Override
    public void executeClientSide(NetworkEvent.Context context) {

    }

    @Override
    public MessageRequestMusicList fromBytes(PacketBuffer buf) {
        fun = buf.readInt();
        return this;
    }

    @Override
    public void toBytes(PacketBuffer buf) {
        buf.writeInt(fun);
    }
}
