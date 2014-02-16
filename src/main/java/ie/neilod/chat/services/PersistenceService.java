package ie.neilod.chat.services;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import ie.neilod.chat.model.User;

import java.sql.SQLException;

/**
 * User: neilod
 * Date: 15/02/2014
 * TIME: 20:47
 */
public class PersistenceService {

    private Dao<User, String> userDao;
    private ConnectionSource connectionSource;

    public PersistenceService() throws SQLException {
        this.connectionSource = new JdbcConnectionSource("jdbc:mysql://localhost:3306/chatz", "root", "");
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
}
