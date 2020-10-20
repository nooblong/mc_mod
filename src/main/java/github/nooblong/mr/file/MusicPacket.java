package github.nooblong.mr.file;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class MusicPacket {

    private String name;
    private int length;
    private byte[] bytes;

    public MusicPacket(String name, int length, byte[] bytes) {
        this.name = name;
        this.bytes = bytes;
        this.length = bytes.length;
    }

    public MusicPacket(PacketBuffer packetBuffer){
        decode(packetBuffer);
    }

    public void encode(final PacketBuffer packetBuffer){
        packetBuffer.writeString(name);
        packetBuffer.writeInt(length);
        packetBuffer.writeBytes(bytes);
    }

    private void decode(final PacketBuffer packetBuffer){
        this.name = packetBuffer.readString(100);
        this.length = packetBuffer.readInt();
        bytes = new byte[length];
        packetBuffer.readBytes(bytes);
    }

    public static void handle(MusicPacket msg, Supplier<NetworkEvent.Context> ctx){
        ctx.get().enqueueWork(()->{
            System.out.println("msg: " + msg.name + " " + msg.length + " " + msg.bytes);
        });
        ctx.get().setPacketHandled(true);
    }

}
