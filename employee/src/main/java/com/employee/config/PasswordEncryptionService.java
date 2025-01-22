package com.employee.config;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class PasswordEncryptionService {

    private static final String ALGORITHM = "AES";
    private final SecretKey key;

    public PasswordEncryptionService() {
        String encodedKey = "wEp5grGYykyb+be0W+dHpv6YoptdezvKXdbWnzUS2h0=";
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        this.key = new SecretKeySpec(decodedKey, ALGORITHM);
    }

    public String encryptPassword(String plainPassword) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plainPassword.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decryptPassword(String encryptedPassword) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedPassword = Base64.getDecoder().decode(encryptedPassword);
        byte[] decryptedBytes = cipher.doFinal(decodedPassword);
        return new String(decryptedBytes);
    }
}

//import org.springframework.stereotype.Service;
//
//import javax.crypto.Cipher;
//import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;
//import java.util.Base64;
//import java.util.Arrays;
//
//@Service
//public class PasswordEncryptionService {
//
//    private static final String ALGORITHM = "AES/ECB/PKCS5Padding"; // Explicit padding
//    private final SecretKey key;
//
//    public PasswordEncryptionService() {
//        String encodedKey = "wEp5grGYykyb+be0W+dHpv6YoptdezvKXdbWnzUS2h0=";
//        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
//        this.key = new SecretKeySpec(decodedKey, "AES");
//    }
//
//    public String encryptPassword(String plainPassword) throws Exception {
//        Cipher cipher = Cipher.getInstance(ALGORITHM);
//        cipher.init(Cipher.ENCRYPT_MODE, key);
//        byte[] encryptedBytes = cipher.doFinal(plainPassword.getBytes());
//        return Base64.getEncoder().encodeToString(encryptedBytes);
//    }
//
//    public String decryptPassword(String encryptedPassword) throws Exception {
//        System.out.println("Encrypted Password: " + encryptedPassword);
//
//        Cipher cipher = Cipher.getInstance(ALGORITHM);
//        cipher.init(Cipher.DECRYPT_MODE, key);
//
//        byte[] decodedPassword = Base64.getDecoder().decode(encryptedPassword);
//        System.out.println("Decoded Password Length: " + decodedPassword.length);
//        System.out.println("Decoded Password Bytes: " + Arrays.toString(decodedPassword));
//
//        byte[] decryptedBytes = cipher.doFinal(decodedPassword);
//        return new String(decryptedBytes);
//    }
//}
