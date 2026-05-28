package com.levelup.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import com.levelup.dao.ClienteDaoImpl;
import com.levelup.model.Cliente;
import com.levelup.model.Usuario;

public class ClientesFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private Usuario usuario;
    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;

    private JButton btnNuevo;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnActualizar;

    public ClientesFrame(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Gestión de Clientes");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Gestión de Clientes", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblTitulo, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Email");
        modeloTabla.addColumn("Teléfono");

        tablaClientes = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaClientes);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());

        btnNuevo = new JButton("Nuevo");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnActualizar = new JButton("Actualizar");

        panelBotones.add(btnNuevo);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnActualizar);

        add(panelBotones, BorderLayout.SOUTH);

        aplicarPermisos();
        eventos();
        cargarClientes();

        setVisible(true);
    }

    private void aplicarPermisos() {
        if (usuario.getRol().equalsIgnoreCase("empleado")) {
            btnNuevo.setEnabled(false);
            btnEditar.setEnabled(false);
            btnEliminar.setEnabled(false);
        }
    }

    private void eventos() {
        btnNuevo.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Aquí irá el formulario para añadir cliente.")
        );

        btnEditar.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Aquí irá el formulario para editar cliente.")
        );

        btnEliminar.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Aquí irá la eliminación del cliente seleccionado.")
        );

        btnActualizar.addActionListener(e -> cargarClientes());
    }

    private void cargarClientes() {
        modeloTabla.setRowCount(0);

        try {
            ClienteDaoImpl clienteDao = new ClienteDaoImpl();
            List<Cliente> listaClientes = clienteDao.listarTodos();

            for (Cliente cliente : listaClientes) {
                modeloTabla.addRow(new Object[]{
                    cliente.getIdCliente(),
                    cliente.getNombre(),
                    cliente.getEmail(),
                    cliente.getTelefono()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "Error al cargar clientes: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    private int obtenerIdClienteSeleccionado() {
        int filaSeleccionada = tablaClientes.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(
                this,
                "Debes seleccionar un cliente de la tabla.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE
            );
            return -1;
        }

        return (int) modeloTabla.getValueAt(filaSeleccionada, 0);
    }
    
}