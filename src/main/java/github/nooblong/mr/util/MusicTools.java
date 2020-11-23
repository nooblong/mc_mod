package github.nooblong.mr.util;

import github.nooblong.mr.MusicRestaurant;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

import java.io.*;
import java.util.UUID;

public class MusicTools {

    private static final int MAX_MUSIC_SIZE = 1000000000;

    public static byte[] toBytes() throws IOException {
        return null;
    }

    public static void saveMusic(ServerPlayerEntity playerMP, UUID musicUUID, byte[] goodMusic) {
        File musicFolder = new File(playerMP.getServerWorld().getSaveHandler().getWorldDirectory(), "mr_music");
        File file = new File(musicFolder, musicUUID.toString() + ".ogg");
        if (file.exists())
            MusicRestaurant.LOGGER.warn("文件存在");
        if (!file.getParentFile().exists()) {
            MusicRestaurant.LOGGER.info("创建目录");
            if (!file.getParentFile().mkdirs())
                MusicRestaurant.LOGGER.error("创建目录失败");
        }
        try {
            OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
            out.write(goodMusic);
            out.flush();
            out.close();
            MusicRestaurant.LOGGER.info("Save ogg " + musicUUID + " at " + file.getAbsolutePath());
        } catch (Exception e){
            MusicRestaurant.LOGGER.error("Save ogg " + musicUUID + " at " + file.getAbsolutePath() + "fail");
        }
    }

    public static File getMusicFile(ServerPlayerEntity playerMP, UUID uuid){
        File musicFolder = new File(playerMP.getServerWorld().getSaveHandler().getWorldDirectory(), "mr_music");
        return new File(musicFolder, uuid.toString() + ".ogg");
    }
}
