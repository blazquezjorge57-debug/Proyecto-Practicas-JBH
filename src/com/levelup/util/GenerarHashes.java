package com.levelup.util;
//.
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GenerarHashes {

    public static String hashSHA256(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (Exception e) {
            throw new RuntimeException("Error al generar hash: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("Hash de admin123:");
        System.out.println(hashSHA256("admin123"));

        System.out.println("\nHash de empleado123:");
        System.out.println(hashSHA256("empleado123"));
    }
}