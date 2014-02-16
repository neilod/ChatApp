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

    private PersistenceService persistenceService;
    private PasswordService passwordService;

    private static ChatAppContext instance;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            this.persistenceService = new PersistenceService();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not initialise persistence service!");
        }
        this.passwordService = new PasswordService();

        instance = this;
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        //To change body of implemented methods use File | Settings | File Templates.
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
