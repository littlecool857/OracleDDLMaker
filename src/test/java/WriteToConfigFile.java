import java.io.*;
import java.util.Properties;

public class WriteToConfigFile {
    public static void main(String[] args) {
        String filePath = getFilePathFromConfig();

        String content = "Hello, this is a test string.";

        if (filePath != null) {
            writeToFile(filePath, content);
        } else {
            System.out.println("Error: File path not found in config.");
        }
    }

    private static String getFilePathFromConfig() {
        Properties properties = new Properties();
        String filePath = null;

        try (InputStream input = WriteToConfigFile.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return null;
            }
            properties.load(input);
            filePath = properties.getProperty("file.save.path");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return filePath;
    }

    private static void writeToFile(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath+"/test.txt"))) {
            writer.write(content);
            writer.newLine();
            System.out.println("File written successfully at: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
