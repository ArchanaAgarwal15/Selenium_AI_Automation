package com.example;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {

    public static String getApikey()
    {
        try {
            Properties prop = new Properties();
            
            // Try multiple locations to find config.properties
            String[] possiblePaths = {
                "src/test/java/com/example/config.properties",
                "demo/src/test/java/com/example/config.properties",
                "target/test-classes/com/example/config.properties"
            };
            
            InputStream inputStream = null;
            
            // First, try loading from classpath (works when running from compiled classes)
            inputStream = Config.class.getClassLoader().getResourceAsStream("com/example/config.properties");
            
            // If not found in classpath, try file system paths
            if (inputStream == null) {
                for (String pathStr : possiblePaths) {
                    Path path = Paths.get(pathStr);
                    if (Files.exists(path)) {
                        inputStream = new FileInputStream(path.toFile());
                        break;
                    }
                }
            }
            
            if (inputStream == null) {
                throw new IOException("config.properties not found in classpath or file system. Tried paths: " + 
                                    String.join(", ", possiblePaths));
            }
            
            prop.load(inputStream);
            inputStream.close();
            
            String apiKey = prop.getProperty("OPENAI_API_KEY");
            if (apiKey == null || apiKey.trim().isEmpty()) {
                throw new RuntimeException("OPENAI_API_KEY not found or empty in config.properties");
            }
            
            return apiKey.trim();
            
        } catch (IOException e) {
            throw new RuntimeException("Failed to load API key from config.properties: " + e.getMessage(), e);
        }
    }
}
