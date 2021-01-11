package Security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordManager {

    public static String hashPassword(String userPasswordInput) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] userPasswordEncodedBytes = digest.digest(userPasswordInput.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(userPasswordEncodedBytes);
    }

}
