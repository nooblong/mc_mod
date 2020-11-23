package github.nooblong.mr.net;

import github.nooblong.mr.util.MusicTools;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PacketManager {

    private Map<UUID, byte[]> clientDataMap;//片段的

    private Map<UUID, byte[]> musicCache;//完整的

    private Map<UUID, Long> times;

    public PacketManager(){
        this.clientDataMap = new HashMap<>();
        this.musicCache = new HashMap<>();
        this.times = new HashMap<>();
    }

    public void addBytes(ServerPlayerEntity playerMP, UUID musicUUID, int offset, int length, byte[] bytes) {
        byte[] data;
        if (!clientDataMap.containsKey(musicUUID)) {
            data = new byte[length];
        } else {
            data = clientDataMap.get(musicUUID);
        }

        System.arraycopy(bytes, 0, data, offset, bytes.length);

        clientDataMap.put(musicUUID, data);

        if (offset + bytes.length >= data.length) {
            //完整传输
            try {
                byte[] goodMusic = completeMusic(musicUUID);
                if (goodMusic == null) {
                    throw new IOException("music incomplete");
                }
                musicCache.put(musicUUID, goodMusic);

                new Thread(() -> {
                    try {
                        MusicTools.saveMusic(playerMP, musicUUID, goodMusic);
                        playerMP.sendMessage(new StringTextComponent("Server get full music"));
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }, "SaveMusicThread").start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public byte[] completeMusic(UUID musicUUID) {
        byte[] data = clientDataMap.get(musicUUID);
        if (data == null) {
            return null;
        }
        try {
            //检查文件完整
            clientDataMap.remove(musicUUID);
            return data;
        } catch (Exception e) {
            return null;
        }
    }

}
