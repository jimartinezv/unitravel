package co.edu.uniquindio.unitravel.servicios;


import co.edu.uniquindio.unitravel.entidades.Caracteristica;
import co.edu.uniquindio.unitravel.entidades.Ciudad;

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


}
