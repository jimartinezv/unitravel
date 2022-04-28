package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.Cliente;

import java.util.List;

public interface ClienteServicio {

    Cliente registrarUsuario(Cliente u) throws Exception;

    Cliente actualizarUsuario(Cliente u) throws Exception;

    Cliente obtenerUsuario(String cedula);

    void eliminarUsuario(String cedula) throws Exception;

    List<Cliente> listarUsuarios();

    List<Cliente> listarClientesReserva();

    Cliente validarLogin(String email, String password) throws Exception;
}
