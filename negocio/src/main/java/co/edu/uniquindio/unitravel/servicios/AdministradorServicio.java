package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.*;

import java.util.List;

public interface AdministradorServicio {

    /**
     * Crea caracteristicas
     * @param caracteristica
     * @return
     */
    Caracteristica crearCaracteristica(Caracteristica caracteristica);



    /**
     * Un administrador puede registrar otros administradores
     * @param administrador
     * @return
     */
    Administrador registrarAdmin(Administrador administrador) throws Exception;

    /**
     * El administrador actualiza datos como correo, telefono y contraseña
     * No podrá actualizar su id
     * @param administrador
     * @return
     */
    Administrador actualizarAdmin(Administrador administrador) throws Exception;

    /**
     * En caso de que ya no se necesiten los servicios del administrador este saldrá del sistema
     * y ya no podrá volver a hacer tareas de administrador
     * @param id
     */
    void eliminarAdministraor(String id) throws Exception;


    /**
     * Se podrán ver todos los administradores
     * @return
     */
    List<Administrador> listarAdministradores();

    /**
     * Para logearse necesita diligenciar correctamente formulario de login
     * @param correo
     * @param password
     * @throws Exception
     */
    Administrador loginAdmin(String correo, String password) throws Exception;



    /**
     * COnsulta la ciudad por su codigo
     * @param codigo
     * @return
     */
    Ciudad consultarCiudad(Integer codigo);

    /**
     * Crear ciudad
     * @param ciudad
     * @return
     * @throws Exception
     */
    Ciudad crearCiudad(Ciudad ciudad) throws Exception;

    /**
     * Modifica las ciudades
     * @param ciudad
     * @return
     * @throws Exception
     */
    Ciudad modificarCiudad(Ciudad ciudad) throws Exception;

    /**
     * Elimina la ciudad por codigo
     * @param codigo
     * @return
     * @throws Exception
     */
    void eliminarCiudad(Integer codigo) throws Exception;

    /**
     * Lista las ciudades
     * @return
     */
    List<Ciudad> listarCiudades();


    /**
     * El administrador podrá crear los vuelos
     * @param vuelo
     * @return
     * @throws Exception
     */
    Vuelo crearVuelo(Vuelo vuelo) throws Exception;



    /**
     * Busca la ciudad por codigo
     * @param codigo
     * @return
     * @throws Exception
     */
    Ciudad buscarCiudad(Integer codigo) throws Exception;


    /**
     * Metodo para modificar vuelos
     * @param vuelo
     * @return
     * @throws Exception
     */
    Vuelo actualizarVuelo(Vuelo vuelo) throws Exception;

    /**
     * Metodo para eliminar vuelo por su codigo
     * @param codigo
     * @throws Exception
     */
    void eliminarVuelo(String codigo) throws Exception;

    Vuelo buscarVuelo(String codigo);

    /**
     * Lista todos los vuelos
     * @return
     */
    List<Vuelo> listarVuelo();

    /**
     * Lista vuelos por ciudad
     * @param ciudad
     * @return
     */
    List<Vuelo> listarVueloByCiudad(Ciudad ciudad);

    /**
     * Creacion de administrador de hotel
     * @param administradorHotel
     * @return
     */
    AdministradorHotel crearAdministradorHotel(AdministradorHotel administradorHotel) throws Exception;

    /**
     * Metodo para actualizar administrador de hotel
     * no se puede modificar su codigo
     * @param administradorHotel
     * @return
     */
    AdministradorHotel modificarAdminHotel(AdministradorHotel administradorHotel) throws Exception;

    Silla crearSilla(Silla s);
    /**
     * Metodo para buscar el administrador del hotel por su cedula
     * @param cedula
     * @return
     */
    AdministradorHotel buscarAdminHotel(String cedula);

    /**
     * Se busca el administrador de hotel por su email que es unico
     * @param email
     * @return
     */
    AdministradorHotel buscarAdminHotelByEmail(String email);

    /**
     * Metodo para eliminar administrador de hotel por su cedula
     * @param cedula
     */
    void eliminarAdminHotel(String cedula) throws Exception;


    /**
     * El administrador podrá crear codigos de descuento los cuales se aplicarán al valor total de la reserva
     * @param codigoDescuento
     * @return
     */
    CodigoDescuento crearCodigoDescuento(CodigoDescuento codigoDescuento) throws Exception;
}
