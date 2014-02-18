package ie.neilod.chat.services;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import ie.neilod.chat.DBConfig;
import ie.neilod.chat.model.User;

import java.sql.SQLException;

/**
 * User: neilod
 * Date: 15/02/2014
 * TIME: 20:47
 */
public class PersistenceService {
    private static final long MINUTE_MILLIS = 60*1000;

    private Dao<User, String> userDao;
    private JdbcPooledConnectionSource connectionSource;

    public PersistenceService(DBConfig dbConfig) throws SQLException {
        this.connectionSource = new JdbcPooledConnectionSource(dbConfig.getDBUrl(), dbConfig.getDBUser(), dbConfig.getDBPassword());
        this.connectionSource.setMaxConnectionsFree(5);
        this.connectionSource.setCheckConnectionsEveryMillis(MINUTE_MILLIS);
        this.userDao = DaoManager.createDao(connectionSource, User.class);
    }

    public User getUser(String email) throws SQLException {
        QueryBuilder<User, String> queryBuilder = userDao.queryBuilder();
        queryBuilder.where().eq("email", email);
        return userDao.queryForFirst(queryBuilder.prepare());
    }

    public int createUser(User user) throws SQLException {
        return userDao.create(user);
    }

    public void destroy() throws SQLException {
        this.connectionSource.close();
        this.connectionSource = null;
    }
}
