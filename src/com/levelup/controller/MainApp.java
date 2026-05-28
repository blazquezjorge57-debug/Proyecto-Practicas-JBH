package com.levelup.controller;

import java.util.List;

import com.levelup.dao.ClienteDAO;
import com.levelup.dao.ClienteDaoImpl;
import com.levelup.dao.UsuarioDaoImpl;
import com.levelup.model.Cliente;
import com.levelup.model.Usuario;
import com.levelup.services.LlmService;
import com.levelup.view.InterfazConsola;

public class MainApp {

    public static void main(String[] args) {
        ClienteDAO clienteDAO = new ClienteDaoImpl();
        UsuarioDaoImpl usuarioDAO = new UsuarioDaoImpl();
        InterfazConsola vista = new InterfazConsola();
        LlmService llmService = new LlmService();

        System.out.println("--- LOGIN LEVELUP ARCADE ---");

        String nombreUsuario = vista.pedirNombreUsuario();
        String password = vista.pedirPassword();

        Usuario usuarioLogueado = usuarioDAO.login(nombreUsuario, password);

        if (usuarioLogueado == null) {
            vista.mostrarMensaje("Usuario o contraseña incorrectos.");
            return;
        }

        String rol = usuarioLogueado.getRol();

        if (rol != null) {
            rol = rol.trim();
        }

        vista.mostrarMensaje("Bienvenido, " + usuarioLogueado.getNombreUsuario() +
                             " (" + usuarioLogueado.getRol() + ")");

        if ("administrador".equalsIgnoreCase(rol)) {
            ejecutarMenuAdmin(vista, clienteDAO, llmService);
        } else if ("empleado".equalsIgnoreCase(rol)) {
            ejecutarMenuEmpleado(vista, clienteDAO, llmService);
        } else {
            vista.mostrarMensaje("Rol no reconocido: " + rol);
        }
    }

    private static void ejecutarMenuAdmin(InterfazConsola vista, ClienteDAO clienteDAO, LlmService llmService) {
        int opcion;

        do {
            opcion = vista.mostrarMenuClientes();

            switch (opcion) {
                case 1:
                    Cliente nuevo = vista.pedirDatosCliente();
                    if (clienteDAO.insertar(nuevo)) {
                        vista.mostrarMensaje("Cliente insertado correctamente.");
                    } else {
                        vista.mostrarMensaje("No se pudo insertar el cliente.");
                    }
                    break;

                case 2:
                    List<Cliente> clientes = clienteDAO.listarTodos();
                    vista.mostrarListaClientes(clientes);
                    break;

                case 3:
                    int idBuscar = vista.pedirIdCliente();
                    Cliente encontrado = clienteDAO.buscarPorId(idBuscar);
                    vista.mostrarCliente(encontrado);
                    break;

                case 4:
                    Cliente actualizado = vista.pedirDatosClienteActualizado();
                    if (clienteDAO.actualizar(actualizado)) {
                        vista.mostrarMensaje("Cliente actualizado correctamente.");
                    } else {
                        vista.mostrarMensaje("No se pudo actualizar el cliente.");
                    }
                    break;

                case 5:
                    int idEliminar = vista.pedirIdCliente();
                    if (clienteDAO.eliminar(idEliminar)) {
                        vista.mostrarMensaje("Cliente eliminado correctamente.");
                    } else {
                        vista.mostrarMensaje("No se pudo eliminar el cliente.");
                    }
                    break;

                case 6:
                    String nombreProductoDescripcion = vista.pedirNombreProductoIA();
                    String descripcionGenerada = llmService.generarDescripcionProducto(nombreProductoDescripcion);
                    vista.mostrarMensaje("Descripción generada por IA:");
                    vista.mostrarMensaje(descripcionGenerada);
                    break;

                case 7:
                    String nombreProductoCategoria = vista.pedirNombreProductoIA();
                    String categoriaSugerida = llmService.sugerirCategoriaProducto(nombreProductoCategoria);
                    vista.mostrarMensaje("Categoría sugerida por IA:");
                    vista.mostrarMensaje(categoriaSugerida);
                    break;

                case 0:
                    vista.mostrarMensaje("Saliendo del programa...");
                    break;

                default:
                    vista.mostrarMensaje("Opción no válida.");
            }

        } while (opcion != 0);
    }

    private static void ejecutarMenuEmpleado(InterfazConsola vista, ClienteDAO clienteDAO, LlmService llmService) {
        int opcion;

        do {
            opcion = vista.mostrarMenuEmpleado();

            switch (opcion) {
                case 1:
                    List<Cliente> clientes = clienteDAO.listarTodos();
                    vista.mostrarListaClientes(clientes);
                    break;

                case 2:
                    int idBuscar = vista.pedirIdCliente();
                    Cliente encontrado = clienteDAO.buscarPorId(idBuscar);
                    vista.mostrarCliente(encontrado);
                    break;

                case 3:
                    String nombreProductoDescripcion = vista.pedirNombreProductoIA();
                    String descripcionGenerada = llmService.generarDescripcionProducto(nombreProductoDescripcion);
                    vista.mostrarMensaje("Descripción generada por IA:");
                    vista.mostrarMensaje(descripcionGenerada);
                    break;

                case 4:
                    String nombreProductoCategoria = vista.pedirNombreProductoIA();
                    String categoriaSugerida = llmService.sugerirCategoriaProducto(nombreProductoCategoria);
                    vista.mostrarMensaje("Categoría sugerida por IA:");
                    vista.mostrarMensaje(categoriaSugerida);
                    break;

                case 0:
                    vista.mostrarMensaje("Saliendo del programa...");
                    break;

                default:
                    vista.mostrarMensaje("Opción no valida.");
            }

        } while (opcion != 0);
    }
}