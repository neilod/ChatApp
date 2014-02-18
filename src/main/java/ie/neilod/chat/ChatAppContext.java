package ie.neilod.chat;


import ie.neilod.chat.services.PasswordService;
import ie.neilod.chat.services.PersistenceService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;

/**
 * User: neilod
 * Date: 15/02/2014
 * TIME: 17:38
 */
public class ChatAppContext implements ServletContextListener {

    private DBConfig dbConfig;
    private PersistenceService persistenceService;
    private PasswordService passwordService;

    private static ChatAppContext instance;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        dbConfig = new DBConfig();
        try {
            this.persistenceService = new PersistenceService(dbConfig);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not initialise persistence service!");
        }
        this.passwordService = new PasswordService();

        instance = this;
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        try {
            this.persistenceService.destroy();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ChatAppContext getInstance() {
        return instance;
    }

    public PasswordService getPasswordService() {
        return passwordService;
    }

    public PersistenceService getPersistenceService() {
        return persistenceService;
    }
}
