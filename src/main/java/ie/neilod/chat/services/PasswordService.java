package ie.neilod.chat.services;

import org.jasypt.util.password.StrongPasswordEncryptor;

/**
 * User: neilod
 * Date: 15/02/2014
 * TIME: 20:30
 */
public class PasswordService {
    private StrongPasswordEncryptor encryptor;

    public PasswordService() {
        this.encryptor = new StrongPasswordEncryptor();
    }

    public boolean isCorrectPassword(String plainText, String stored) {
        return encryptor.checkPassword(plainText, stored);
    }

    public String encryptPassword(String plainText) {
        return encryptor.encryptPassword(plainText);
    }
}
