package com.levelup.gui;

import javax.swing.*;
import java.awt.*;

import com.levelup.model.Usuario;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private Usuario usuario;

    private JButton btnClientes;
    private JButton btnProductos;
    private JButton btnProveedores;
    private JButton btnIA;
    private JButton btnSalir;

    public MainFrame(Usuario usuario) {
        this.usuario = usuario;

        setTitle("LevelUp Arcade - Menú Principal");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel(
                "Bienvenido, " + usuario.getNombreUsuario() + " | Rol: " + usuario.getRol(),
                SwingConstants.CENTER
        );
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(5, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        btnClientes = new JButton("Gestión de Clientes");
        btnProductos = new JButton("Gestión de Productos");
        btnProveedores = new JButton("Gestión de Proveedores");
        btnIA = new JButton("Funciones de IA");
        btnSalir = new JButton("Cerrar sesión");

        panelBotones.add(btnClientes);
        panelBotones.add(btnProductos);
        panelBotones.add(btnProveedores);
        panelBotones.add(btnIA);
        panelBotones.add(btnSalir);

        add(panelBotones, BorderLayout.CENTER);

        aplicarPermisos();
        eventos();

        setVisible(true);
    }

    private void aplicarPermisos() {
        if (usuario.getRol().equalsIgnoreCase("empleado")) {
            btnIA.setEnabled(false);
        }
    }

    private void eventos() {
        btnClientes.addActionListener(e -> new ClientesFrame(usuario));
        btnProductos.addActionListener(e -> new ProductosFrame(usuario));
        btnProveedores.addActionListener(e -> new ProveedoresFrame(usuario));
        btnIA.addActionListener(e -> new IAFrame(usuario));
        btnSalir.addActionListener(e -> cerrarSesion());
    }

    private void cerrarSesion() {
        dispose();
        new LoguinFrame();
    }
}