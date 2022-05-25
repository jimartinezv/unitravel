package co.edu.uniquindio.unitravel.servicios;


import co.edu.uniquindio.unitravel.entidades.Ciudad;

import java.util.List;

public interface CiudadServicio {

    Ciudad registrarCiudad(Ciudad c) throws Exception;

    Ciudad obtenerCiudad(Integer codigo);

    Ciudad actualizarCiudad(Integer codigo);

    void eliminarCiudad(Integer codigo);

    List<Ciudad> listaCiudades();

    void eliminarDepto(Integer codigoDepto);


}
