package com.levelup.view;
//*
import java.util.List;
import java.util.Scanner;
import com.levelup.model.Cliente;

public class InterfazConsola {

    private Scanner sc = new Scanner(System.in);

    public int mostrarMenuClientes() {
        while (true) {
            System.out.println("\n--- GESTIÓN DE CLIENTES ---");
            System.out.println("1. Insertar cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Buscar cliente por ID");
            System.out.println("4. Actualizar cliente");
            System.out.println("5. Eliminar cliente");
            System.out.println("6. Generar descripción de producto con IA");
            System.out.println("7. Sugerir categoría de producto con IA");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            String entrada = sc.nextLine();

            if (entrada == null || entrada.trim().isEmpty()) {
                System.out.println("No has escrito ninguna opción.");
                continue;
            }

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Debes introducir un número válido.");
            }
        }
    }

    public Cliente pedirDatosCliente() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellidos: ");
        String apellidos = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();

        System.out.print("Dirección: ");
        String direccion = sc.nextLine();

        return new Cliente(nombre, apellidos, email, telefono, direccion);
    }

    public int pedirIdCliente() {
        System.out.print("Introduce el ID del cliente: ");
        return Integer.parseInt(sc.nextLine());
    }

    public Cliente pedirDatosClienteActualizado() {
        System.out.print("ID del cliente: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Nuevos apellidos: ");
        String apellidos = sc.nextLine();

        System.out.print("Nuevo email: ");
        String email = sc.nextLine();

        System.out.print("Nuevo teléfono: ");
        String telefono = sc.nextLine();

        System.out.print("Nueva dirección: ");
        String direccion = sc.nextLine();

        Cliente cliente = new Cliente(nombre, apellidos, email, telefono, direccion);
        cliente.setIdCliente(id);
        return cliente;
    }

    public String pedirNombreProductoIA() {
        System.out.print("Introduce el nombre del producto: ");
        return sc.nextLine();
    }

    public void mostrarCliente(Cliente cliente) {
        if (cliente != null) {
            System.out.println(cliente);
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    public void mostrarListaClientes(List<Cliente> clientes) {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    public String pedirNombreUsuario() {
        System.out.print("Nombre de usuario: ");
        return sc.nextLine();
    }

    public String pedirPassword() {
        System.out.print("Contraseña: ");
        return sc.nextLine();
    }
    public int mostrarMenuEmpleado() {
        while (true) {
            System.out.println("\n--- MENÚ EMPLEADO ---");
            System.out.println("1. Listar clientes");
            System.out.println("2. Buscar cliente por ID");
            System.out.println("3. Generar descripción de producto con IA");
            System.out.println("4. Sugerir categoría de producto con IA");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            String entrada = sc.nextLine();

            if (entrada == null || entrada.trim().isEmpty()) {
                System.out.println("No has escrito ninguna opción.");
                continue;
            }

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Debes introducir un número válido.");
            }
        }
    }
}