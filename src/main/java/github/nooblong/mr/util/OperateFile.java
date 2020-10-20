package github.nooblong.mr.util;

import github.nooblong.mr.MusicRestaurant;
import net.minecraft.client.Minecraft;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class OperateFile {
    public static void saveOgg(String name, List<byte[]> bytesList) {
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
            for (byte[] b : bytesList){
                out.write(b);
            }
            out.flush();
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

    public static List<byte[]> readOgg(String path) {
        File file;
        try {
            file = new File(path);
            List<byte[]> bytes = splitBySize(file, 20000);
            return bytes;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String getFileName(String path) {
        File file = new File(path);
        return file.getName();
    }

    public static List<byte[]> splitBySize(File file, int byteSize) throws IOException {
        List<byte[]> parts = new ArrayList<>();
        RandomAccessFile rf = new RandomAccessFile(file, "r");
        long length = rf.length();
        long offset = 0;
        //总共要分成几块
        int count = (int) Math.ceil(length / (double) byteSize);
        for (int i = 0; i < count-1; i++) {
            byte[] bytes = new byte[byteSize];
            rf.read(bytes);
            offset = (i + 1) * byteSize;
            rf.seek(offset);
            parts.add(bytes);
        }
        long leftSize = length - offset;
        byte[] bytes = new byte[(int)leftSize];
        rf.read(bytes);
        parts.add(bytes);
        return parts;
    }

    public static void main(String[] args) {
        File file = new File("D:\\FFOutput\\bdth.ogg");
        try {
            List<byte[]> bytes = splitBySize(file, 5000);
            System.out.println(bytes.size());

            File out = new File("D:\\FFOutput\\bdth2.ogg");
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(out));
            for (byte[] b : bytes){
                outputStream.write(b);
            }
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
