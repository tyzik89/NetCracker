package ru.sukmanov.properties_reader;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Считыватель свойств
 */
public class PropertyReader {

    /**
     * @param str имя свойства в конфиге
     * @return значение свойства в конфиге
     */
    public static String readProperty(String str) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/main/resources/conf.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(str);
    }
}
