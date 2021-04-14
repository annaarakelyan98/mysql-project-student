package com.students.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configs {
    private static final String PATH = "src/main/resources/connection.properties";
    private static Properties properties;

    private static Properties getInstance() {
        if (properties == null) {
            loadProperties(PATH);
        }
        return properties;
    }

    public static String getConfig(String key) {
        return getInstance().getProperty(key);
    }

    private static void loadProperties(String path) {
        properties = new Properties();
        try {
            InputStream is = new FileInputStream(PATH);
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
