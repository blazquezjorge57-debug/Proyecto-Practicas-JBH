package com.levelup.dao;

import java.util.List;
import com.levelup.model.Cliente;

public interface ClienteDAO {

    boolean insertar(Cliente Cliente);

    List<Cliente> listarTodos();

    Cliente buscarPorId(int idCliente);

    boolean actualizar(Cliente cliente);

    boolean eliminar(int idCliente);
}