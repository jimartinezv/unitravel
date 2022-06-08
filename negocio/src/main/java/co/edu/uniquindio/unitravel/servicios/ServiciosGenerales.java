package co.edu.uniquindio.unitravel.servicios;


import co.edu.uniquindio.unitravel.entidades.*;

import java.util.List;

public interface ServiciosGenerales {

    /**
     * Busca ciudad por codigo
     * @param codigo
     * @return
     * @throws Exception
     */
    Ciudad buscarCiudad(Integer codigo) throws Exception;

    /**
     * Lista las ciudades
     * @return
     */
    List<Ciudad> listarCiudades();

    /**
     * Login del cliente
     * @param email
     * @param password
     * @return
     * @throws Exception
     */
    Persona login(String email, String password) throws Exception;

    /**
     * Busca caracteristica por codigo
     * @param codigo
     * @return
     * @throws Exception
     */
    Caracteristica buscarCaracteristica(Integer codigo) throws Exception;
    /**
     * Lista las caracteristicas del hotel
     * @return
     */
    List<Caracteristica> listarCaracteristicasHoteles();

    List<Caracteristica> listarCaracteristicasHabitacion();

    List<Cama> listarCamas();


}
