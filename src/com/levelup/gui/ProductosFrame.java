package com.levelup.gui;

import javax.swing.*;
import java.awt.*;
import com.levelup.model.Usuario;

public class ProductosFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    public ProductosFrame(Usuario usuario) {
        setTitle("Gestión de Productos");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lbl = new JLabel(
                "Pantalla de productos - Usuario: " + usuario.getNombreUsuario(),
                SwingConstants.CENTER
        );
        add(lbl, BorderLayout.CENTER);

        setVisible(true);
    }
}
