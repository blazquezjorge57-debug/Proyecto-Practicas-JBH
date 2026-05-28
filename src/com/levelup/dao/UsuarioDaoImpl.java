package com.levelup.dao;

//sahdf

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.levelup.dao.DatabaseConnection;
import com.levelup.model.Usuario;

public class UsuarioDaoImpl {

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

    public Usuario login(String nombreUsuario, String password) {
        String passwordHash = hashSHA256(password);

        String sql = "SELECT id_usuario, nombre, contrasena, rol " +
                     "FROM usuarios WHERE nombre = ? AND contrasena = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombreUsuario);
            stmt.setString(2, passwordHash);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nombre"),
                    rs.getString("contrasena"),
                    rs.getString("rol")
                );
            }

        } catch (Exception e) {
            System.out.println("Error en login: " + e.getMessage());
        }

        return null;
    }
}