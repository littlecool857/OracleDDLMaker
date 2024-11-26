import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestgetProperties {
    public static String getFilePathFromLocal() {
        String filePath = "resources/config.properties";

        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            Properties properties = new Properties();
            properties.load(inputStream);

            String value = properties.getProperty("file.save.path");
            System.out.println("file.save.path = " + value);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        getFilePathFromLocal();
    }
}
