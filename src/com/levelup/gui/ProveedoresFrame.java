package com.levelup.gui;

import javax.swing.*;
import java.awt.*;
import com.levelup.model.Usuario;

public class ProveedoresFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    public ProveedoresFrame(Usuario usuario) {
        setTitle("Gestión de Proveedores");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lbl = new JLabel(
                "Pantalla de proveedores - Usuario: " + usuario.getNombreUsuario(),
                SwingConstants.CENTER
        );
        add(lbl, BorderLayout.CENTER);

        setVisible(true);
    }
}
