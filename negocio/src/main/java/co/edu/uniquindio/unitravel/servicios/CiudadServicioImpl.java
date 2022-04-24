package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.Ciudad;
import co.edu.uniquindio.unitravel.entidades.Departamento;
import co.edu.uniquindio.unitravel.repositorios.CiudadRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiudadServicioImpl implements CiudadServicio{

    private CiudadRepo ciudadRepo;


    public CiudadServicioImpl(CiudadRepo ciudadRepo){
        this.ciudadRepo=ciudadRepo;

    }

    @Override
    public Ciudad registrarCiudad(Ciudad c) throws Exception {
        return null;
    }

    @Override
    public Ciudad obtenerCiudad(Integer codigo) {
        Ciudad buscada= ciudadRepo.getById(codigo);
        return buscada;
    }

    @Override
    public Ciudad actualizarCiudad(Integer codigo) {
        return null;
    }

    @Override
    public void eliminarCiudad(Integer codigo) {

    }

    @Override
    public List<Ciudad> listaCiudades() {
        return null;
    }

    @Override
    public Departamento registrarDepartamento(Departamento d) throws Exception {
        return null;
    }

    @Override
    public Departamento obtenerDepartamento(Integer codigoDepto) {
        return null;
    }

    @Override
    public Departamento actualizarDepto(Integer codigoDepto) {
        return null;
    }

    @Override
    public void eliminarDepto(Integer codigoDepto) {

    }

    @Override
    public List<Departamento> listarDepartamentos() {
        return null;
    }
}
