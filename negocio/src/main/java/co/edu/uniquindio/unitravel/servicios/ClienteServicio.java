package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.*;

import java.util.List;

public interface ClienteServicio {

    /**
     * Metodo para registrar un nuevo cliente
     * @param u
     * @return
     * @throws Exception
     */
    Cliente registrarUsuario(Cliente u) throws Exception;

    /**
     * El cliente podrá actualizar todos sus datos excepto su id
     * @param u
     * @return
     * @throws Exception
     */
    Cliente actualizarUsuario(Cliente u) throws Exception;

    /**
     * Muestra un usuario por su cedula
     * @param cedula
     * @return
     */
    Cliente obtenerUsuario(String cedula) throws Exception;

    /**
     * Elimina usuario por su cedula
     * @param cedula
     * @throws Exception
     */
    void eliminarUsuario(String cedula) throws Exception;

    /**
     * Lista todos los clientes
     * @return
     */
    List<Cliente> listarUsuarios();

    /**
     * Cliente crea una reserva (seleccionando el destino, las habitaciones, vuelo)
     * @param reserva
     * @return
     */
    Reserva crearReserva(Reserva reserva) throws Exception;

    /**
     * Aquí el cliente podra listar sus reservas
     * @param
     * @return
     */
    List<Reserva> listarReserva();

    /**
     * El cliente podrá comentar y calificar hotel
     * @param comentario
     * @return
     */
    Comentario comentarHotel(Comentario comentario) throws Exception;

    List<Comentario> listarComentarios();

    /**
     * El usuario podrá recuperar su contraseña utilizando su correo electronico
     * @param id
     */
    void recuperarPassword(String id) throws Exception;

    List<Cliente> listarClientesReserva();

    /**
     * El cliente podrá gestionar sus propias reservas
     * @param reserva
     * @return
     */
    Reserva gestionarReserva(Reserva reserva) throws Exception;

    Cliente validarLogin(String email, String password) throws Exception;

    /**
     * El cliente podrá buscar hoteles y vuelos por determinado destino
     * @param destino
     * @return
     */
    List<Hotel> buscarHoteles(Destino destino);

    Hotel buscarHotelPorCodigo(Integer codigo) throws Exception;
    //Registrarse y loguearse.
    //Buscar destinos y/o hoteles.
    //Crear una reserva (seleccionando el destino, las habitaciones, vuelo).
    //Gestionar sus propias reservas.
    //Listar todos los hoteles por cada destino.
    //Comentar y calificar Hoteles.
    //Recuperar contraseñas usando correo electrónico.
    //Listar sus propias reservas.

}
