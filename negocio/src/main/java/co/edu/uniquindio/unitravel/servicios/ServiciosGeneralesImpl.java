package co.edu.uniquindio.unitravel.servicios;


import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiciosGeneralesImpl implements ServiciosGenerales{

    @Autowired
    private CamaRepo camaRepo;

    private CiudadRepo ciudadRepo;
    private CaracteristicaRepo caracteristicaRepo;

    private ClienteRepo clienteRepo;
    private AdministradorHotelRepo administradorHotelRepo;
    private AdministradorRepo administradorRepo;

    public ServiciosGeneralesImpl(CiudadRepo ciudadRepo, CaracteristicaRepo caracteristicaRepo,  ClienteRepo clienteRepo,
                                  AdministradorHotelRepo administradorHotelRepo, AdministradorRepo administradorRepo){
        this.ciudadRepo=ciudadRepo;
        this.caracteristicaRepo=caracteristicaRepo;

        this.clienteRepo=clienteRepo;
        this.administradorHotelRepo=administradorHotelRepo;
        this.administradorRepo=administradorRepo;

    }
    @Override
    public List<Ciudad> listarCiudades() {
        return ciudadRepo.findAll();
    }

    @Override
    public Persona login(String email, String password) throws Exception {
        Persona persona= clienteRepo.findByEmailAndPassword(email, password).orElse(null);
        if(persona==null){
            persona=administradorRepo.findByEmailAndPassword(email, password).orElse(null);
        }
        if(persona==null){
            persona=administradorHotelRepo.findByEmailAndPassword(email, password).orElse(null);
        }
        if (persona==null){
            throw new Exception("Los datos de autenticación son incorrectos");
        }
        return persona;
    }

    @Override
    public Caracteristica buscarCaracteristica(Integer codigo) throws Exception {
        return caracteristicaRepo.findById(codigo).orElseThrow(()-> new Exception("El codigo de la caracteristica no existe"));
    }

    @Override
    public List<Caracteristica> listarCaracteristicasHoteles() {
        return caracteristicaRepo.findAllByTipo("HOTEL");
    }

    @Override
    public List<Caracteristica> listarCaracteristicasHabitacion() {
        return caracteristicaRepo.findAllByTipo("HABITACION");
    }

    @Override
    public List<Cama> listarCamas() {
        return camaRepo.findAll();
    }


    @Override
    public Ciudad buscarCiudad(Integer codigo) throws Exception {
        return ciudadRepo.findById(codigo).orElse(null);
    }
}
