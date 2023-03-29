package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class Utility {
    public static String readProperties(String key) {
        String result = null;
        try {
            Properties prop = new Properties();
            FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
            prop.load(file);
            result = prop.getProperty(key);
        } catch (Exception e) {
            System.out.println("Read exception: " + e);
        }
        return result;
    }
}
