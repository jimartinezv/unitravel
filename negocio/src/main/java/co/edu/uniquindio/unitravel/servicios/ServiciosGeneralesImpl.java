package co.edu.uniquindio.unitravel.servicios;


import co.edu.uniquindio.unitravel.entidades.Caracteristica;
import co.edu.uniquindio.unitravel.entidades.Ciudad;
import co.edu.uniquindio.unitravel.repositorios.CaracteristicaRepo;
import co.edu.uniquindio.unitravel.repositorios.CiudadRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiciosGeneralesImpl implements ServiciosGenerales{
    private CiudadRepo ciudadRepo;
    private CaracteristicaRepo caracteristicaRepo;

    public ServiciosGeneralesImpl(CiudadRepo ciudadRepo, CaracteristicaRepo caracteristicaRepo){
        this.ciudadRepo=ciudadRepo;
        this.caracteristicaRepo=caracteristicaRepo;

    }
    @Override
    public List<Ciudad> listarCiudades() {
        return ciudadRepo.findAll();
    }

    @Override
    public Caracteristica buscarCaracteristica(Integer codigo) throws Exception {
        return caracteristicaRepo.findById(codigo).orElseThrow(()-> new Exception("El codigo de la caracteristica no existe"));
    }

    @Override
    public List<Caracteristica> listarCaracteristicasHoteles() {
        return caracteristicaRepo.findAll();
    }


    @Override
    public Ciudad buscarCiudad(Integer codigo) throws Exception {
        return ciudadRepo.findById(codigo).orElse(null);
    }
}
