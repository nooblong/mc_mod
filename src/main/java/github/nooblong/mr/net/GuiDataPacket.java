package github.nooblong.mr.net;

import github.nooblong.mr.tileentity.Mp3TileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class GuiDataPacket {

    private BlockPos blockPos;
    private boolean enablePlay;
    private int pos1x;
    private int pos1y;
    private int pos1z;
    private int pos2x;
    private int pos2y;
    private int pos2z;

    public GuiDataPacket(boolean enablePlay, int pos1x, int pos1y, int pos1z, int pos2x, int pos2y, int pos2z, BlockPos blockPos) {
        this.blockPos = blockPos;
        this.enablePlay = enablePlay;
        this.pos1x = pos1x;
        this.pos1y = pos1y;
        this.pos1z = pos1z;
        this.pos2x = pos2x;
        this.pos2y = pos2y;
        this.pos2z = pos2z;
    }

    public GuiDataPacket(PacketBuffer packetBuffer) {
        decode(packetBuffer);
    }

    public void encode(final PacketBuffer packetBuffer) {
        packetBuffer.writeBlockPos(blockPos);
        packetBuffer.writeBoolean(enablePlay);
        packetBuffer.writeInt(pos1x);
        packetBuffer.writeInt(pos1y);
        packetBuffer.writeInt(pos1z);
        packetBuffer.writeInt(pos2x);
        packetBuffer.writeInt(pos2y);
        packetBuffer.writeInt(pos2z);

    }

    public void decode(final PacketBuffer packetBuffer) {
        blockPos = packetBuffer.readBlockPos();
        enablePlay = packetBuffer.readBoolean();
        pos1x = packetBuffer.readInt();
        pos1y = packetBuffer.readInt();
        pos1z = packetBuffer.readInt();
        pos2x = packetBuffer.readInt();
        pos2y = packetBuffer.readInt();
        pos2z = packetBuffer.readInt();
    }

    public static void handle(GuiDataPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            World world;
            try {
                //客户端发到服务器
                ServerPlayerEntity sender = ctx.get().getSender();
                world = sender.world;
                System.out.println("have pointer");
            } catch (NullPointerException e){
                //服务器发到客户端
                System.out.println("null pointer");
                world = getClientWorld();
            }
            if (!world.isRemote) {
                System.out.println("server receive gui packet");
                if (world.getTileEntity(msg.blockPos) instanceof Mp3TileEntity) {
                    Mp3TileEntity mp3TileEntity = ((Mp3TileEntity) world.getTileEntity(msg.blockPos));
                    mp3TileEntity.setEnablePlay(msg.enablePlay);
                    mp3TileEntity.setPos1x(msg.pos1x);
                    mp3TileEntity.setPos1y(msg.pos1y);
                    mp3TileEntity.setPos1z(msg.pos1z);
                    mp3TileEntity.setPos1x(msg.pos1x);
                    mp3TileEntity.setPos1y(msg.pos1y);
                    mp3TileEntity.setPos1z(msg.pos1z);
                    mp3TileEntity.markDirty();
                }
            }
            if (world.isRemote){
                System.out.println("client receive gui packet");
                if (world.getTileEntity(msg.blockPos) instanceof Mp3TileEntity) {
                    Mp3TileEntity mp3TileEntity = ((Mp3TileEntity) world.getTileEntity(msg.blockPos));
                    mp3TileEntity.setEnablePlay(msg.enablePlay);
                    mp3TileEntity.setPos1x(msg.pos1x);
                    mp3TileEntity.setPos1y(msg.pos1y);
                    mp3TileEntity.setPos1z(msg.pos1z);
                    mp3TileEntity.setPos1x(msg.pos1x);
                    mp3TileEntity.setPos1y(msg.pos1y);
                    mp3TileEntity.setPos1z(msg.pos1z);
                    mp3TileEntity.markDirty();
                }
            }

        });
        ctx.get().setPacketHandled(true);
    }

    @OnlyIn(Dist.CLIENT)
    private static World getClientWorld(){
        return Minecraft.getInstance().world;
    }


}
