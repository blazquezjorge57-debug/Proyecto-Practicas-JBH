package com.levelup.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.levelup.model.Cliente;

public class ClienteDaoImpl implements ClienteDAO {

    @Override
    public boolean insertar(Cliente Cliente) {
        String sql = "INSERT INTO clientes (nombre, apellidos, email, telefono, direccion) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, Cliente.getNombre());
            ps.setString(2, Cliente.getApellidos());
            ps.setString(3, Cliente.getEmail());
            ps.setString(4, Cliente.getTelefono());
            ps.setString(5, Cliente.getDireccion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar cliente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Cliente> listarTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getTimestamp("fecha_registro").toLocalDateTime()
                );
                lista.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public Cliente buscarPorId(int idCliente) {
        String sql = "SELECT * FROM clientes WHERE id_cliente = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCliente);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                            rs.getInt("id_cliente"),
                            rs.getString("nombre"),
                            rs.getString("apellidos"),
                            rs.getString("email"),
                            rs.getString("telefono"),
                            rs.getString("direccion"),
                            rs.getTimestamp("fecha_registro").toLocalDateTime()
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar cliente: " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean actualizar(Cliente cliente) {
        String sql = "UPDATE clientes SET nombre = ?, apellidos = ?, email = ?, telefono = ?, direccion = ? WHERE id_cliente = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellidos());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getDireccion());
            ps.setInt(6, cliente.getIdCliente());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int idCliente) {
        String sql = "DELETE FROM clientes WHERE id_cliente = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }
}