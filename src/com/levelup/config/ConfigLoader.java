package com.levelup.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private static final Properties properties = new Properties();
    private static final String CONFIG_FILE = "config.properties";

    static {
        cargarConfiguracion();
    }

    private static void cargarConfiguracion() {
        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);
        } catch (Exception e) {
            System.out.println("No se pudo cargar config.properties: " + e.getMessage());
        }
    }

    public static String getOpenRouterApiKey() {
        return properties.getProperty("openrouter.api.key");
    }

    public static String getOpenRouterModel() {
        return properties.getProperty("openrouter.model");
    }

    public static String getDbUrl() {
        return properties.getProperty("db.url");
    }

    public static String getDbUser() {
        return properties.getProperty("db.user");
    }

    public static String getDbPassword() {
        return properties.getProperty("db.password");
    }
}