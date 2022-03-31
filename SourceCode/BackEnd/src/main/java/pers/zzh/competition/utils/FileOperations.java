package pers.zzh.competition.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 保存文件
 *
 * @author zhangguapi
 */
public class FileOperations {

    static final String HEAD_PATH = System.getProperty("user.home") + "/.ReviewAllot";

    public static void saveJsonFile(Map<String, Integer> map, String afterPath) {
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

    public static Boolean readJson(String requestKey) {

        return false;
    }

    public static StringBuilder readJsonFile(String afterPath) {
        String fullPath = HEAD_PATH + afterPath;
        System.out.println(fullPath);
        try {
            File file = new File(fullPath);
            if (file.exists()) {
                FileReader fileReader = new FileReader(file);
                Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
                int ch = 0;
                StringBuilder stringBuffer = new StringBuilder();
                while ((ch = reader.read()) != -1) {
                    stringBuffer.append((char) ch);
                }
                fileReader.close();
                reader.close();
                return stringBuffer;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}





