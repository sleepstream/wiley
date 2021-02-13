package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

    private static FileInputStream fileInputStream;
    private static Properties PROPERTIES;

    static {
        try {
            PROPERTIES = new Properties();

            //load environment properties
            fileInputStream = new FileInputStream("target/classes/properties/environment.properties");
            PROPERTIES.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getTestProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

    public static void setTestProperty(String key, String value) {
        PROPERTIES.setProperty(key, value);
    }

    public static Integer getIntProperty(String key) {
        return Integer.valueOf(PROPERTIES.getProperty(key));
    }

    public static String getTestPropertyDomain(String propertiesUrl) {
        String url = getTestProperty(propertiesUrl);
        return url.replaceAll("(http://)|(https://)", "");
    }
}