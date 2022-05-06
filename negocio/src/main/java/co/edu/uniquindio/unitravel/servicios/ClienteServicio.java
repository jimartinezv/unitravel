package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.*;

import java.util.List;

public interface ClienteServicio {

    /**
     * Metodo para registrar un nuevo cliente
     *
     * @param u
     * @return
     * @throws Exception
     */
    Cliente registrarUsuario(Cliente u) throws Exception;

    /**
     * El cliente podrá actualizar todos sus datos excepto su id
     *
     * @param u
     * @return
     * @throws Exception
     */
    Cliente actualizarUsuario(Cliente u) throws Exception;

    /**
     * Muestra un usuario por su cedula
     *
     * @param cedula
     * @return
     */
    Cliente obtenerUsuario(String cedula) throws Exception;

    /**
     * Elimina usuario por su cedula
     *
     * @param cedula
     * @throws Exception
     */
    void eliminarUsuario(String cedula) throws Exception;

    /**
     * Lista todos los clientes
     *
     * @return
     */
    List<Cliente> listarUsuarios();

    Reserva buscarReserva (Integer codigo) throws Exception;
    /**
     * Cliente crea una reserva (seleccionando el destino, las habitaciones, vuelo)
     *
     * @param reserva
     * @return
     */
    Reserva crearReserva(Reserva reserva) throws Exception;

    /**
     * Se modifica reserva por su codigo
     * @param reserva
     * @return
     * @throws Exception
     */
    Reserva modificarReserva(Integer reserva) throws Exception;

    /**
     * Se elimina reserva por su codigo
     * @param codigo
     * @return
     * @throws Exception
     */
    void eliminarReserva(Integer codigo) throws Exception;

    /**
     * Aquí el cliente podra listar sus reservas
     *
     * @param
     * @return
     */
    List<Reserva> listarReserva(Cliente cliente);


    /**
     * El cliente podrá comentar y calificar hotel
     *
     * @param comentario
     * @return
     */
    Comentario comentarHotel(Comentario comentario) throws Exception;

    List<Comentario> listarComentarios();



    List<Cliente> listarClientesReserva();

    List<Reserva> listarReservasByCliente(String correo) throws Exception;



    /**
     * Login del cliente
     * @param email
     * @param password
     * @return
     * @throws Exception
     */
    Cliente login(String email, String password) throws Exception;

    /**
     * El cliente podrá buscar hoteles y vuelos por determinado destino
     *
     * @param ciudad
     * @return
     */
    List<Hotel> buscarHotelesByCiudad(Ciudad ciudad);

    /**
     * Lista todos las ciudades
     * @return
     */
    List<Ciudad> listarCiudades();

    /**
     * Busca la ciudad por codigo
     * @param codigo
     * @return
     * @throws Exception
     */
    Ciudad buscarCiudad(Integer codigo) throws Exception;

    Hotel buscarHotelPorCodigo(Integer codigo) throws Exception;

    /**
     * existen hoteles que tienen el mismo nombre usualmente estan en diferentes ciudades
     * este devuelve una lista de todos los hoteles que contengan ese nombre
     * @param nombre
     * @return
     * @throws Exception
     */
    List<Hotel> buscarHotelesPorNombre(String nombre) throws Exception;

    /**
     * Metodo para recuperar contraseña utilizando el correo electronico del usuario
     * @param correo
     * @return
     * @throws Exception
     */
    String recuperarContrasena(String correo) throws Exception;

    Cliente buscarCliente(String cedula) throws Exception;

    void enviarCorreo(Cliente email) throws Exception;
    //Registrarse y loguearse.
    //Buscar destinos y/o hoteles.
    //Crear una reserva (seleccionando el destino, las habitaciones, vuelo).
    //Gestionar sus propias reservas.
    //Listar todos los hoteles por cada destino.
    //Comentar y calificar Hoteles.
    //Recuperar contraseñas usando correo electrónico.
    //Listar sus propias reservas.

}
