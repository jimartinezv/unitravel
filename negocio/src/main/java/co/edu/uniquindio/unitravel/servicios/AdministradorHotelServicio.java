package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.AdministradorHotel;
import co.edu.uniquindio.unitravel.entidades.Caracteristica;
import co.edu.uniquindio.unitravel.entidades.Habitacion;
import co.edu.uniquindio.unitravel.entidades.Hotel;

import java.util.List;

public interface AdministradorHotelServicio {

    //Loguearse
    //Gestionar Hoteles

    /**
     * Login del administrador del hotel
     * @param correo
     * @param password
     * @return
     */
    AdministradorHotel loginAdminHotel(String correo, String password) throws Exception;

    /**
     * AdministradorHotel tiene tarea de crear hotel y administrarlo
     * @param hotel
     * @return
     */
    Hotel crearHotel(Hotel hotel) throws Exception;
    /**
     * Metodo para eliminar hotel
     * @return
     */
    void eliminarHotel(Integer codigo) throws Exception;

    /**
     * Metodo para modificar un hotel
     * @param hotel
     * @return
     * @throws Exception
     */
    Hotel modificarHotel(Hotel hotel) throws Exception;
    /**
     * Lista todas las caracteristicas que tiene una habitación
     * @param habitacion
     * @return
     */
    List<Caracteristica> listarCaracteristicasByHabitacion(Habitacion habitacion);

    /**
     * Crea caracteristicas
     * @param caracteristica
     * @return
     */
    Caracteristica crearCaracteristica(Caracteristica caracteristica);

    /**
     * Metodo para crear habitaciones
     * @param habitacion
     * @return
     * @throws Exception
     */
    Habitacion crearHabitacion(Habitacion habitacion) throws Exception;

    /**
     * Muestra las habitaciones de determinado hotel
     * @param hotel
     * @return
     */
    List<Habitacion> listarHabitacionesByHotel(Hotel hotel);

    /**
     * Metodo para listar todas las habitaciones de todoslos hoteles
     * @return
     */
    List<Habitacion> listarHabitaciones();


    Hotel buscarHotel(Integer codigo) throws Exception;

    List<Caracteristica> listarCaracteristicas();


}
