package github.nooblong.mr.util;

import github.nooblong.mr.MusicRestaurant;
import net.minecraft.client.Minecraft;

import java.io.*;

public class OperateFile {
    public static void saveOgg(String name, byte[] bytes) {
        File file;
        OutputStream out = null;
        try {
            file = new File("music/" + name);

            if (file.exists())
                System.out.println("文件存在");
            if (!file.getParentFile().exists()) {
                System.out.println("创建目录");
                if (!file.getParentFile().mkdirs())
                    System.out.println("创建目录失败");
            }

            out = new BufferedOutputStream(new FileOutputStream(file));
            out.write(bytes);
            MusicRestaurant.LOGGER.info("Save ogg \"" + name + " at " + file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static byte[] readOgg(String path) {
        File file;
        InputStream in = null;
        try {
            file = new File(path);
            in = new BufferedInputStream(new FileInputStream(file));
            int length = in.available();
            byte[] bytes = new byte[length];
            in.read(bytes);
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String getFileName(String path){
        File file = new File(path);
        return file.getName();
    }
}
