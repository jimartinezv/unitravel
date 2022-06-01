package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.*;

import java.time.LocalDate;
import java.util.List;

public interface ClienteServicio {



    /**
     * Metodo para recuperar contraseña utilizando el correo electronico del usuario
     * @param correo
     * @return
     * @throws Exception
     */
    String recuperarContrasena(String correo) throws Exception;

    void enviarCorreoRecovery(Cliente email) throws Exception;

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

    Habitacion buscarHabitacion(Integer codigo) throws Exception;

    /**
     * Elimina usuario por su cedula
     *
     * @param cedula
     * @throws Exception
     */
    void eliminarUsuario(String cedula) throws Exception;

    /**
     * Muestra un usuario por su cedula
     *
     * @param cedula
     * @return
     */
    Cliente obtenerUsuario(String cedula) throws Exception;





    /**
     * El cliente podrá buscar hoteles y vuelos por determinado destino
     *
     * @param ciudad
     * @return
     */
    List<Hotel> buscarHotelesByCiudad(Ciudad ciudad);

    List<Hotel> listarHoteles();

    /**
     * existen hoteles que tienen el mismo nombre usualmente estan en diferentes ciudades
     * este devuelve una lista de todos los hoteles que contengan ese nombre
     * @param nombre
     * @return
     * @throws Exception
     */
    List<Hotel> buscarHotelesPorNombre(String nombre);

    /**
     * Encuentra el hotel por su codigo
     * @param codigo
     * @return
     * @throws Exception
     */
    Hotel buscarHotelPorCodigo(Integer codigo) throws Exception;

    /**
     * Busca la reserva de habitacion
     * @param codigo
     * @return
     * @throws Exception
     */
    ReservaHabitacion buscarReservaHabitacion(Integer codigo) throws Exception;


    /**
     * Calcula el costo de las habitaciones
     * @param reserva
     * @return
     * @throws Exception
     */
    double calcularCostoReservaHabitacion(Reserva reserva)throws Exception;

    /**
     * Se crea las reservas de habitacion
     * @param rh
     * @return
     */
    ReservaHabitacion crearReservaHabitacion(ReservaHabitacion rh);

    /**
     * Para la busqueda de vuelos el cliente la deberá hacer con base a la ciudad
     * y la fecha de la busqieda
     * @param ciudad
     * @param localDate
     * @return
     */
    Vuelo buscarVuelos(Ciudad ciudad, LocalDate localDate);

    /**
     * Busca vuelo por su codigo
     * @param codigo
     * @return
     */
    Vuelo buscarVueloByCodigo(String codigo);

    /**
     * Discrimina si el vuelo existe o no
     * @param v
     * @return
     * @throws Exception
     */
    Boolean vuelosDisponibles(Vuelo v) throws Exception;

    /**
     * Metodo para asignar sillas
     * @param silla
     * @return
     */
    List<ReservaSilla> asignarSillas(List<Silla> silla, Reserva reserva) throws Exception;

    List<Silla> buscarSillasByVuelo(String codigo) throws Exception;

    /**
     * Crea la reserva de la silla del avion
     * @param silla
     * @param reserva
     * @return
     * @throws Exception
     */
    ReservaSilla crearReservaSilla(Silla silla, Reserva reserva) throws Exception;

    /**
     *
     * @param reserva
     * @return
     * @throws Exception
     */
    ReservaSilla actualizarReservaSilla(ReservaSilla rs,Reserva reserva) throws Exception;

    /**
     * calcula el costo de las reservas de los vuelos
     * @param reserva
     * @return
     * @throws Exception
     */
    double calcularCostoReservaSilla(List<ReservaSilla> reserva) throws Exception;

    /**
     * Cliente crea una reserva (seleccionando el destino, las habitaciones, vuelo)
     *
     * @param reserva
     * @return
     */
    Reserva crearReserva(Reserva reserva) throws Exception;

    boolean habitacionDisponible(Habitacion h, Reserva r)throws Exception;

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
     * Lista las reservas de un cliente por su correo
     * @param correo
     * @return
     * @throws Exception
     */
    List<Reserva> listarReservasByCliente(String correo) throws Exception;

    /**
     * Busca una reserva por su codigo
     * @param codigo
     * @return
     * @throws Exception
     */
    Reserva buscarReserva (Integer codigo) throws Exception;


    /**
     * Metodo donde se le aplica el descuento total a la reserva dependiendo el codigo de decuento
     * @param codigoDescuento
     * @return
     * @throws Exception
     */
    double aplicarCodigoDescuento(CodigoDescuento codigoDescuento) throws Exception;

    /**
     * Envia correo con detalles de reserva
     * @param reserva
     * @throws Exception
     */
    Reserva enviarCorreoReserva(Reserva reserva)throws Exception;


    /**
     * Lista todos los clientes
     *
     * @return
     */
    List<Cliente> listarUsuarios();




    /**
     * El cliente podrá comentar y calificar hotel
     *
     * @param comentario
     * @return
     */
    Comentario comentarHotel(Comentario comentario) throws Exception;

    /**
     * el cliente puede ver todos los comentarios realizados a un hotel
     * @return
     */
    List<Comentario> listarComentarios();



}
