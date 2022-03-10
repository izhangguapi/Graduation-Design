package pers.zzh.competition.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 保存文件
 *
 * @author zhangguapi
 */
public class SaveFile {

    static final String HEAD_PATH = System.getProperty("user.home") + "/.ReviewAllot";

    public static void saveJson(Map<String, Integer> map, String afterPath) {
        String fullPath = HEAD_PATH + afterPath;
        System.out.println(fullPath);
        ObjectMapper mapper = new ObjectMapper();
        try {
            // 创建一个文件对象
            File file = new File(fullPath);
            // 如果父目录不存在，创建父目录
            if (!file.getParentFile().exists()) {
                System.out.println(file.getParentFile().mkdirs());
            }
            // 如果文件已存在，则删除旧文件
            if (file.exists()) {
                System.out.println(file.delete());
            }
            System.out.println(file.createNewFile());
            // 创建json文件
            Writer write = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
            // 将map写入json文件
            mapper.writeValue(file, map);
            write.flush();
            write.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public Boolean readJson(String requestKey) {

        return false;
    }
}





