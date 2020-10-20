import github.nooblong.mr.util.OperateFile;

import java.io.*;
import java.util.Arrays;

public class TestJava {
    public static void main(String[] args) throws Exception {
        System.out.println(
                OperateFile.readOgg("D:\\FFOutput\\bdth.ogg")
        );

//        File file = new File("D:\\FFOutput\\bdth.ogg");
//        InputStream in = new BufferedInputStream(new FileInputStream(file));
//        System.out.println(in.available());
    }
}
