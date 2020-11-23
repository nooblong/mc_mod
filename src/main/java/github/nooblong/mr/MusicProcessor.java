package github.nooblong.mr;

import github.nooblong.mr.net.MessageMusic;
import github.nooblong.mr.net.MessagePartialMusic;
import github.nooblong.mr.net.SimpleNetworkHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.io.*;
import java.util.UUID;

public class MusicProcessor {

    public static void sendMusic(UUID uuid, byte[] bytes) {
        if (bytes.length < 30000) {
            SimpleNetworkHandler.INSTANCE.sendToServer(new MessagePartialMusic(uuid, 0, bytes.length, bytes));
        } else {
            int bufferProgress = 0;
            byte[] currentBuffer = new byte[30000];
            for (int i = 0; i < bytes.length; i++) {
                if (bufferProgress >= currentBuffer.length) {
                    SimpleNetworkHandler.INSTANCE.sendToServer(new MessagePartialMusic(uuid, i - currentBuffer.length, bytes.length, currentBuffer));
                    bufferProgress = 0;
                    currentBuffer = new byte[currentBuffer.length];
                }
                currentBuffer[bufferProgress] = bytes[i];
                bufferProgress++;
            }
            if (bufferProgress > 0) {
                byte[] rest = new byte[bufferProgress];
                System.arraycopy(currentBuffer, 0, rest, 0, bufferProgress);
                SimpleNetworkHandler.INSTANCE.sendToServer(new MessagePartialMusic(uuid, bytes.length - rest.length, bytes.length, rest));
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void sendMusic(String path){
        File toSend = new File(path);
        byte[] music;
        ClientPlayerEntity player = Minecraft.getInstance().player;
        if (!toSend.exists()){
            if (player != null) {
                player.sendChatMessage("file not found!");
            }
        } else {
            try {
                InputStream in = new BufferedInputStream(new FileInputStream(toSend));
                music = new byte[(int) toSend.length()];
                int read = in.read(music, 0, (int) toSend.length());
                if (player != null) {
                    player.sendChatMessage("read " + read + " bytes");
                }
                UUID uuid = UUID.randomUUID();
                sendMusic(uuid, music);
                if (player != null) {
                    player.sendChatMessage("sending...");
                }
            } catch (FileNotFoundException e) {
                if (player != null) {
                    player.sendChatMessage("send error");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void sendMusicThreaded(UUID uuid, byte[] bytes) {
        new Thread(() -> sendMusic(uuid, bytes), "ProcessMusicThread").start();
    }


}
