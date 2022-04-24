package co.edu.uniquindio.unitravel.servicios;


import co.edu.uniquindio.unitravel.entidades.Ciudad;
import co.edu.uniquindio.unitravel.entidades.Departamento;

import java.util.List;

public interface CiudadServicio {

    Ciudad registrarCiudad(Ciudad c) throws Exception;

    Ciudad obtenerCiudad(Integer codigo);

    Ciudad actualizarCiudad(Integer codigo);

    void eliminarCiudad(Integer codigo);

    List<Ciudad> listaCiudades();

    Departamento registrarDepartamento(Departamento d) throws Exception;

    Departamento obtenerDepartamento(Integer codigoDepto);

    Departamento actualizarDepto(Integer codigoDepto);

    void eliminarDepto(Integer codigoDepto);

    List<Departamento> listarDepartamentos();
}
