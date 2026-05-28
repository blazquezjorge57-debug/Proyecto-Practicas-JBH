package com.levelup.gui;

import javax.swing.*;
import java.awt.*;
import com.levelup.model.Usuario;

public class IAFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    public IAFrame(Usuario usuario) {
        setTitle("Funciones de IA");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lbl = new JLabel(
                "Pantalla de IA - Usuario: " + usuario.getNombreUsuario(),
                SwingConstants.CENTER
        );
        add(lbl, BorderLayout.CENTER);

        setVisible(true);
    }
}
