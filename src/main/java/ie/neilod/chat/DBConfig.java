package ie.neilod.chat;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * User: neilod
 * Date: 17/02/2014
 * TIME: 18:48
 */
public class DBConfig {
    private static final String dbProperties = "/app/local/db.properties";

    private Properties properties;

    public DBConfig() {
        properties = new Properties();
        try {
            properties.load(new FileReader(new File(dbProperties)));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not load db config");
        }
    }

    public String getDBUrl() {
        return get("db.url");
    }

    public String getDBUser() {
        return get("db.user");
    }

    public String getDBPassword() {
        return get("db.password");
    }

    private String get(String key) {
        return properties.getProperty(key);
    }
}
