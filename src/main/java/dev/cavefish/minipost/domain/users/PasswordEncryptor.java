package dev.cavefish.minipost.domain.users;


import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

@Service
public class PasswordEncryptor {
    public String encryptPassword(String user, String password) {
        String toEncrypt = user + ":" + password;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(toEncrypt.getBytes());
            byte[] digested = digest.digest();
            Formatter fmt = new Formatter();
            for (byte b : digested) {
                fmt.format("%02x", b);
            }
            String hashedPassword = fmt.toString();
            fmt.close();
            return hashedPassword;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
