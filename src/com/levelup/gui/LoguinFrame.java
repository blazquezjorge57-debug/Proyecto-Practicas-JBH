package com.levelup.gui;

import javax.swing.*;
import java.awt.*;

import com.levelup.dao.UsuarioDaoImpl;
import com.levelup.model.Usuario;

public class LoguinFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoguinFrame() {
        setTitle("LevelUp Arcade - Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Iniciar sesión", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new GridLayout(2, 2, 10, 10));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelCentro.add(new JLabel("Usuario:"));
        txtUsuario = new JTextField();
        panelCentro.add(txtUsuario);

        panelCentro.add(new JLabel("Contraseña:"));
        txtPassword = new JPasswordField();
        panelCentro.add(txtPassword);

        add(panelCentro, BorderLayout.CENTER);

        btnLogin = new JButton("Entrar");
        add(btnLogin, BorderLayout.SOUTH);

        btnLogin.addActionListener(e -> validarLogin());

        setVisible(true);
    }

    private void validarLogin() {
        String nombreUsuario = txtUsuario.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (nombreUsuario.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Debes rellenar usuario y contraseña.",
                "Campos vacíos",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();
        Usuario usuario = usuarioDao.login(nombreUsuario, password);

        if (usuario != null) {
            JOptionPane.showMessageDialog(
                this,
                "Bienvenido " + usuario.getNombreUsuario()
            );

            new MainFrame(usuario);
            dispose();
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Usuario o contraseña incorrectos.",
                "Error de login",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
