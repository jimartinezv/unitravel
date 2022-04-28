package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.AdministradorHotel;
import co.edu.uniquindio.unitravel.entidades.Hotel;

public interface AdministradorHotelServicio {

    //Loguearse
    //Gestionar Hoteles

    /**
     * Login del administrador del hotel
     * @param correo
     * @param password
     * @return
     */
    AdministradorHotel loginAdminHotel(String correo, String password);

    /**
     * Aqui se modifica o gestiona un hotel
     * @return
     */
    Hotel gestionarHotel(Hotel hotel);
}
