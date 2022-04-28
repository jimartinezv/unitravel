package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.*;

import java.util.List;

public interface AdministradorServicio {

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
     * Los administradores gestionan los destinos ya que ciertas regiones o ciudades
     * pueden estar invocadas en un solo destino ejemplo:
     * puedo tomar un avion a pereira y hospedarme en un hotel de armenia
     * @param destino
     * @return
     */
    Destino gestionarDestino(Destino destino) throws Exception;

    /**
     * El administrador podrá crear los vuelos
     * @param vuelo
     * @return
     * @throws Exception
     */
    Vuelo crearVuelo(Vuelo vuelo) throws Exception;

    /**
     * El administrador puede hacer modificaciones de vuelo como fecha y hora de salida
     * @param vuelo
     * @return
     * @throws Exception
     */
    Vuelo gestionarVuelo(Vuelo vuelo) throws Exception;

    /**
     * Cuando se crea un hotel el administrador puede crear uno o varios administradores para
     * ese hotel
     * @param administradorHotel
     * @return
     */
    AdministradorHotel gestionarAdminHotel(AdministradorHotel administradorHotel) throws Exception;

    /**
     * EL administrador creará los hoteles y definirá su administrador
     * @param hotel
     * @return
     */
    Hotel registrarHotel(Hotel hotel) throws Exception;

    /**
     * El administrador podrá crear toures
     * @param tour
     * @return
     * @throws Exception
     */
    Tour crearTour(Tour tour) throws Exception;

    /**
     * El administrador podrá crear codigos de descuento los cuales se aplicarán al valor total de la reserva
     * @param codigoDescuento
     * @return
     */
    CodigoDescuento crearCodigoDescuento(CodigoDescuento codigoDescuento) throws Exception;
}
