package org.weiliangchen551.com.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PathGandW {
    public static String getFilePathFromLocal(String property) {
        String filePath = "resources/config.properties";

        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            Properties properties = new Properties();
            properties.load(inputStream);

            String value = properties.getProperty(property);
            return value;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getFilePathFromConfig(String property) {
        Properties properties = new Properties();
        String filePath = null;

        try (InputStream input = PathGandW.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return null;
            }
            properties.load(input);
//            filePath = properties.getProperty("file.save.path");
            filePath = properties.getProperty(property);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return filePath;
    }

    public static void writeToFile(String filePath, String content, String fileName) {
        try (
                // 使用 OutputStreamWriter 来显式指定 UTF-8 编码
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(filePath + "/" + fileName), StandardCharsets.UTF_8)
                )
        ) {
            writer.write(content);
            writer.newLine();  // 写入一个换行符
            System.out.println("File written successfully at: " + filePath + "/" + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
