package app.passwords;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;

public class Passwords {
    private static final Random RANDOM = new SecureRandom();
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;

    public static byte[] generateSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return salt;
    }

    public static byte[] generateHash(char[] pwd, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec pbeKeySpec = new PBEKeySpec(pwd, salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        return secretKeyFactory.generateSecret(pbeKeySpec).getEncoded();
    }

    public static boolean checkPassword(char[] pwd, byte[] salt, byte[] hash) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] generatedHash = generateHash(pwd, salt);
        if (generatedHash.length != hash.length) {
            return false;
        }
        return Arrays.compare(generatedHash, hash) == 0;
    }
}
