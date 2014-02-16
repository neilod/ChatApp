package ie.neilod.chat.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import javax.persistence.Id;

/**
 * User: neilod
 * Date: 15/02/2014
 * TIME: 19:20
 */
@DatabaseTable(tableName = "user")
public class User {

    @DatabaseField(generatedId = true)
    private int id;

    @Id
    @DatabaseField(canBeNull = false)
    private String email;

    @DatabaseField(canBeNull = false)
    private String password;

    @DatabaseField(canBeNull = false)
    private String screenName;

    public User() {

    }

    public User(String email, String password, String screenName) {
        this.email = email;
        this.password = password;
        this.screenName = screenName;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getScreenName() {
        return screenName;
    }
}
