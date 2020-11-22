package github.nooblong.mr.net;

import github.nooblong.mr.util.OperateFile;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class MusicPacket {

    private String name;
    private int length;
    private int packageNum;
    private int lastPackageLength;
    private byte[] bytes;

    public MusicPacket(String name, int length, byte[] bytes, int packageNum, int lastPackageLength) {
        this.name = name;
        this.bytes = bytes;
        this.length = bytes.length;
        this.packageNum = packageNum;
        this.lastPackageLength = lastPackageLength;
    }

    public MusicPacket(PacketBuffer packetBuffer){
        decode(packetBuffer);
    }

    public void encode(final PacketBuffer packetBuffer){
        packetBuffer.writeInt(packageNum);
        packetBuffer.writeInt(lastPackageLength);
        packetBuffer.writeString(name);
        packetBuffer.writeInt(length);
        packetBuffer.writeBytes(bytes);
    }

    private void decode(final PacketBuffer packetBuffer){
        this.packageNum = packetBuffer.readInt();
        this.lastPackageLength = packetBuffer.readInt();
        this.name = packetBuffer.readString(100);
        this.length = packetBuffer.readInt();
        bytes = new byte[length];
        packetBuffer.readBytes(bytes);
    }

    private static List<byte[]> byteList = new ArrayList<>();
    private static MusicPacket lastMusicPacket = null;
    private static String realName;

    public static void handle(MusicPacket msg, Supplier<NetworkEvent.Context> ctx){
        ctx.get().enqueueWork(()->{

            if (msg.name.equals("finish")){
                //一次完整的传输结束
                OperateFile.saveOgg(realName, byteList);
                lastMusicPacket = null;
                byteList = new ArrayList<>();
                System.out.println("完整传输");
            } else if (lastMusicPacket == null){
                //第一次
                lastMusicPacket = msg;
                byteList.add(msg.bytes);
                realName = msg.name;
                System.out.println("第一次传输");
            } else if (msg.name.equals(lastMusicPacket.name)){
                //第2，3，4...次传输
                byteList.add(msg.bytes);
//                System.out.println("同一次传输");
            }
        });
        ctx.get().setPacketHandled(true);
    }

}
