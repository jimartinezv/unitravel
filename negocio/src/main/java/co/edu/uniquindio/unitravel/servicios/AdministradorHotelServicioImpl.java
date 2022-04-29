package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.AdministradorHotel;
import co.edu.uniquindio.unitravel.entidades.Caracteristica;
import co.edu.uniquindio.unitravel.entidades.Habitacion;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.repositorios.AdministradorHotelRepo;
import co.edu.uniquindio.unitravel.repositorios.CaracteristicaRepo;
import co.edu.uniquindio.unitravel.repositorios.HabitacionRepo;
import co.edu.uniquindio.unitravel.repositorios.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorHotelServicioImpl implements AdministradorHotelServicio{

    @Autowired
    AdministradorHotelRepo administradorHotelRepo;

    @Autowired
    HotelRepo hotelRepo;

    @Autowired
    HabitacionRepo habitacionRepo;

    @Autowired
    CaracteristicaRepo caracteristicaRepo;



    @Override
    public AdministradorHotel loginAdminHotel(String correo, String password) throws Exception {
        Optional<AdministradorHotel> admin= administradorHotelRepo.findByEmailAndPassword(correo, password);
        if(admin.isEmpty()){
            throw new Exception("Los datos de autenticación son incorrectos");
        }
        return admin.get();
    }


    @Override
    public Hotel crearHotel(Hotel hotel) throws Exception {
        Hotel nuevoHotel= buscarHotel(hotel.getCodigo());
        if(nuevoHotel!=null){
            throw new Exception("El hotel ya existe");
        }
        return hotelRepo.save(hotel);
    }

    @Override
    public Hotel gestionarHotel(Hotel hotel) {
        return null;
    }

    @Override
    public List<Caracteristica> listarCaracteristicasByHabitacion(Habitacion habitacion) {
        return caracteristicaRepo.findAll();
    }

    @Override
    public Caracteristica crearCaracteristica(Caracteristica caracteristica) {
        return caracteristicaRepo.save(caracteristica);
    }

    @Override
    public Habitacion crearHabitacion(Habitacion habitacion) throws Exception {

        return habitacionRepo.save(habitacion);
    }

    @Override
    public List<Habitacion> listarHabitacionesByHotel(Hotel hotel) {
        return habitacionRepo.findByHotel(hotel);
    }

    @Override
    public List<Habitacion> listarHabitaciones() {
        return habitacionRepo.findAll();
    }

    @Override
    public Hotel buscarHotel(Integer codigo) throws Exception {
        Hotel buscarHotel= hotelRepo.getById(codigo);
        if(buscarHotel==null){
            throw new Exception("EL hotel no existe");
        }
        return buscarHotel;
    }

    @Override
    public List<Caracteristica> listarCaracteristicas() {
        return caracteristicaRepo.findAll();
    }


}
