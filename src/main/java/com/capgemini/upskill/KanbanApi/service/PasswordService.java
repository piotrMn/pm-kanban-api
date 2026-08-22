package com.capgemini.upskill.KanbanApi.service;

import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

@Service
public class PasswordService {

    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 128;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";

    static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (args.length != 1) {
            throw new RuntimeException();
        }
        String arg = args[0];
        PasswordService passwordService = new PasswordService();
        byte[] salt = passwordService.generateSalt();
        String hashPassword = passwordService.hashPassword(arg, salt);
        System.out.printf(hashPassword);
    }

    public byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
        byte[] hash = factory.generateSecret(spec).getEncoded();

        // Encode salt and hash as Base64 for storage
        String saltBase64 = Base64.getEncoder().encodeToString(salt);
        String hashBase64 = Base64.getEncoder().encodeToString(hash);

        // Store both salt and hash (e.g., "salt:hash")
        return saltBase64 + ":" + hashBase64;

    }

    public boolean validatePassword(String clearPassword, String hashedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String saltEncoded = hashedPassword.split(":")[0];
        byte[] salt = Base64.getDecoder().decode(saltEncoded);
        String hashed = hashPassword(clearPassword, salt);
        return hashed.equals(hashedPassword);
    }

}
