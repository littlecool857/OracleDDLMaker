import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    public static void main(String[] args) {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }

            Properties prop = new Properties();
            prop.load(input);

            String filePath = prop.getProperty("file.excel.path");
            System.out.println("File path: " + filePath);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
