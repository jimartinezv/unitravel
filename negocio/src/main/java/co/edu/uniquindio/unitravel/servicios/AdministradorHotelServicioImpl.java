package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.AdministradorHotel;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.repositorios.AdministradorHotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdministradorHotelServicioImpl implements AdministradorHotelServicio{

    @Autowired
    AdministradorHotelRepo administradorHotelRepo;

    @Override
    public AdministradorHotel loginAdminHotel(String correo, String password) throws Exception {
        Optional<AdministradorHotel> admin= administradorHotelRepo.findByEmailAndPassword(correo, password);
        if(admin.isEmpty()){
            throw new Exception("Los datos de autenticación son incorrectos");
        }
        return admin.get();
    }

    @Override
    public Hotel gestionarHotel(Hotel hotel) {
        return null;
    }
}
