package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorHotelServicioImpl implements AdministradorHotelServicio{


    private AdministradorHotelRepo administradorHotelRepo;


    private HotelRepo hotelRepo;


    private  HabitacionRepo habitacionRepo;


    private  CaracteristicaRepo caracteristicaRepo;

    private CiudadRepo ciudadRepo;

    private DireccionRepo direccionRepo;

    public AdministradorHotelServicioImpl(AdministradorHotelRepo administradorHotelRepo, HotelRepo hotelRepo,
                                          HabitacionRepo habitacionRepo, CaracteristicaRepo caracteristicaRepo,
                                          CiudadRepo ciudadRepo, DireccionRepo direccionRepo  ){
        this.administradorHotelRepo= administradorHotelRepo;
        this.hotelRepo= hotelRepo;
        this.habitacionRepo=habitacionRepo;
        this.caracteristicaRepo=caracteristicaRepo;
        this.ciudadRepo=ciudadRepo;
        this.direccionRepo= direccionRepo;
    }


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

        return hotelRepo.save(hotel);
    }

    @Override
    public void eliminarHotel(Integer codigo) throws Exception {
        Hotel buscarHoltel= buscarHotel(codigo);
        if(buscarHoltel==null){
            throw new Exception("El hotel no existe");
        }
        System.out.println("Se eliminó hotel");
        hotelRepo.delete(buscarHoltel);
    }

    @Override
    public Hotel modificarHotel(Hotel hotel) throws Exception {
        Hotel buscarHoltel= buscarHotel(hotel.getCodigo());
        if(buscarHoltel==null){
            throw new Exception("El hotel no existe");
        }
        return hotelRepo.save(buscarHoltel);
    }

    @Override
    public AdministradorHotel obtenerAdminHotel(String codigo) {
        return administradorHotelRepo.findById(codigo).orElse(null);

    }

    @Override
    public AdministradorHotel buscarAdminHotelByEmail(String email) {
        return administradorHotelRepo.findByEmail(email).orElse(null);

    }


    @Override
    public AdministradorHotel modificarAdminHotel(AdministradorHotel adminHotel) throws Exception {
        AdministradorHotel buscado= obtenerAdminHotel(adminHotel.getCedula());

        if(buscado==null){
            throw new Exception("El usuario no existe en la base de datos");
        }

        return administradorHotelRepo.save(adminHotel);
    }


    @Override
    public List<AdministradorHotel> listarAdminHoteles() {
        return administradorHotelRepo.findAll();
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

        return hotelRepo.findById(codigo).orElse(null);
    }

    @Override
    public List<Caracteristica> listarCaracteristicas() {
        return caracteristicaRepo.findAll();
    }

    @Override
    public Ciudad buscarCiudad(Integer codigo)  {
        return ciudadRepo.findById(codigo).orElse(null);
    }

    @Override
    public List<Ciudad> listarCiudades() {
        return ciudadRepo.findAll();
    }

    @Override
    public Direccion crearDireccion(Direccion d) throws Exception {

        return direccionRepo.save(d);
    }

    @Override
    public Direccion actualizarDireccion(Direccion d) throws Exception {
        return direccionRepo.save(d);
    }

    @Override
    public void eliminarDireccion(Integer codigo) throws Exception{
        Direccion d= direccionRepo.findById(codigo).orElse(null);
        if(d==null){
            throw new Exception("La direccion no existe");
        }
        direccionRepo.delete(d);
    }


}
