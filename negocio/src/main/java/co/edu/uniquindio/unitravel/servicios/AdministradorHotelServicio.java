package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.repositorios.AdministradorHotelRepo;

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

    void eliminarHabitacion(Integer codigo) throws Exception;

    /**
     * Metodo para modificar el administrador de hotel segun su codigo
     * @param adminHotel
     * @return
     * @throws Exception
     */
    AdministradorHotel modificarAdminHotel(AdministradorHotel adminHotel) throws Exception;
    /**
     * Obtiene el administrador del hotel por su codigo
     * @param codigo
     * @return
     */
    AdministradorHotel obtenerAdminHotel(String codigo);

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
     * COnsult
     * @param email
     * @return
     */
    AdministradorHotel buscarAdminHotelByEmail(String email);

    Habitacion buscarHabitacion(Integer codigo);

    /**
     * Actualiza habitaciones
     * @param h
     * @return
     * @throws Exception
     */
    Habitacion actualizarHabitacion(Habitacion h) throws Exception;

    /**
     * Muestra las habitaciones de un hotel
     * @param hotel
     * @return
     */
    List<Habitacion> habitacionByHotel(Hotel hotel);





    List<AdministradorHotel> listarAdminHoteles();
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


    /**
     * Busca hotel por su codigo
     * @param codigo
     * @return
     * @throws Exception
     */
    Hotel buscarHotel(Integer codigo) throws Exception;

    /**
     * Lista las carecteristicas del hotel y sus habitaciones
     * @return
     */
    List<Caracteristica> listarCaracteristicas();



    /**
     * Crea direccion
     * @param d
     * @return
     * @throws Exception
     */
    Direccion crearDireccion(Direccion d) throws Exception;

    /**
     * Actualiza direccion
     * @param d
     * @return
     * @throws Exception
     */
    Direccion actualizarDireccion(Direccion d)throws Exception;

    /**
     * Elimina direccion
     */
    void eliminarDireccion(Integer codigo) throws Exception;


}
