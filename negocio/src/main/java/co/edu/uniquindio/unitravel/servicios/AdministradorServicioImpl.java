package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorServicioImpl implements AdministradorServicio{


    private AdministradorRepo administradorRepo;


    private VueloRepo vueloRepo;


    private AdministradorHotelRepo administradorHotelRepo;


    private HotelRepo hotelRepo;


    private CodigoDescuentoRepo codigoDescuentoRepo;

    private CiudadRepo ciudadRepo;

    @Autowired
    private DepartamentoRepo departamentoRepo;

    @Autowired
    private SillaRepo sillaRepo;

    @Autowired HabitacionRepo habitacionRepo;
    public AdministradorServicioImpl(AdministradorRepo administradorRepo, VueloRepo vueloRepo, AdministradorHotelRepo administradorHotelRepo,
                                HotelRepo hotelRepo,CodigoDescuentoRepo codigoDescuentoRepo,
                                     CiudadRepo ciudadRepo){
        this.administradorRepo=administradorRepo;
        this.vueloRepo=vueloRepo;
        this.administradorHotelRepo=administradorHotelRepo;
        this.hotelRepo=hotelRepo;
        this.codigoDescuentoRepo=codigoDescuentoRepo;
        this.ciudadRepo= ciudadRepo;

    }
    /**
     * Busca la existencia del administrador
     * @param id
     * @return
     */
    public Administrador buscarAdmin(String id){

        return administradorRepo.getById(id);
    }

    public Administrador buscarAdminByEmail(String correo){
        return administradorRepo.findByEmail(correo);
    }

    /**
     * Determina la existencia de un deteminado vuelo
     * @param codigo
     * @return
     */
    public Vuelo buscarVuelo(String codigo){
        return vueloRepo.findById(codigo).orElse(null);
    }

    /**
     * Determina la existencia del administrador de un hotel
     * @param cedula
     * @return
     */
    public AdministradorHotel buscarAdminHotel(String cedula){
        return administradorHotelRepo.findById(cedula).orElse(null);
    }

    @Override
    public AdministradorHotel buscarAdminHotelByEmail(String email) {
        return administradorHotelRepo.findByEmail(email).orElse(null);
    }

    @Override
    public void eliminarAdminHotel(String cedula) throws Exception {

        AdministradorHotel adminHotel= buscarAdminHotel(cedula);
        if(adminHotel==null){
            throw new Exception("El administrador no existe o no se puede eliminar");
        }
        administradorHotelRepo.delete(adminHotel);
    }

    @Override
    public Departamento crearDepartamento(Departamento d) throws Exception{

        return departamentoRepo.save(d);
    }

    @Override
    public Departamento consultarDepartamento(Integer codigo) throws Exception{
        Departamento buscado=departamentoRepo.findById(codigo).orElse(null);
        if(buscado==null){
            throw new Exception("Departamento no existe");
        }
        return buscado;
    }

    /**
     * Determina la existencia de un hotel
     * @param codigo
     * @return
     */
    public Hotel buscarHotel(Integer codigo){
        return hotelRepo.getById(codigo);
    }

    /**
     * Determina si existe un codigo de descuento
     * @param codigo
     * @return
     */
    public CodigoDescuento buscarCodigoDescuento(String codigo){
        return codigoDescuentoRepo.getByCodigo(codigo);
    }

    /**
     * Un administrador puede crear otros administradores
     * @param administrador
     * @return
     * @throws Exception
     */
    @Override
    public Administrador registrarAdmin(Administrador administrador) throws Exception {

        Administrador adminBuscado= buscarAdmin(administrador.getCedula());
        if(adminBuscado!=null){
            throw new Exception("El administrador ya se encuentra registrado");
        }
        Administrador adminEmail= buscarAdminByEmail(administrador.getEmail());
        if(adminEmail!=null){
            throw new Exception("El correo ya esta registrado");
        }
        return administradorRepo.save(administrador);
    }

    @Override
    public Administrador actualizarAdmin(Administrador administrador) throws Exception {
        Administrador admin= buscarAdmin(administrador.getCedula());
        if(admin==null){
            throw new Exception("El administrador no existe");
        }
        return administradorRepo.save(administrador);
    }

    @Override
    public void eliminarAdministraor(String id) throws Exception {
        Administrador admin= buscarAdmin(id);
        if(admin==null){
            throw new Exception("El administrador no existe");
        }
        administradorRepo.delete(admin);
    }

    @Override
    public Habitacion crearHabitacion(Habitacion h) {
        return habitacionRepo.save(h);
    }

    @Override
    public Habitacion buscarHabitacion(String codigo) {
        return habitacionRepo.findById(codigo).orElse(null);
    }

    @Override
    public Habitacion actualizarHabitacion(Habitacion h) throws Exception {
        Habitacion hb= buscarHabitacion(h.getCodigo());
        if(hb==null){
            throw new Exception("La habitación no existe");
        }
        return h;
    }

    @Override
    public List<Habitacion> habitacionByHotel(Hotel hotel) {
        return habitacionRepo.findByHotel(hotel);
    }

    @Override
    public void eliminarHabitacion(String codigo) throws Exception{
        Habitacion h= buscarHabitacion(codigo);
        if(h==null){
            throw new Exception("No se puede eliminar la habitacion");
        }
        habitacionRepo.delete(h);
    }

    @Override
    public List<Administrador> listarAdministradores() {
        return administradorRepo.findAll();
    }

    @Override
    public Administrador loginAdmin(String correo, String password) throws Exception {
        Administrador loginAdmin= administradorRepo.findByEmailAndPassword(correo,password);
        if(loginAdmin==null){
            throw new Exception("Datos incorrectos");
        }
        return loginAdmin;
    }



    @Override
    public Ciudad consultarCiudad(Integer codigo){
        System.out.println("buscando ciudad");
        Ciudad buscada= ciudadRepo.findById(codigo).orElse(null);

        return buscada;
    }

    @Override
    public Ciudad crearCiudad(Ciudad ciudad) throws Exception {

        System.out.println("ya se va"+ciudad.getNombre()+"nombre");
        return ciudadRepo.save(ciudad);
    }

    @Override
    public Ciudad modificarCiudad(Ciudad ciudad) throws Exception {
        Ciudad buscar= consultarCiudad(ciudad.getCodigo());
        if(buscar==null){
            throw new Exception("La ciudad no existe");
        }
        return ciudadRepo.save(ciudad);
    }

    @Override
    public void eliminarCiudad(Integer codigo) throws Exception {

        Ciudad buscar= consultarCiudad(codigo);

        if(buscar==null){
            throw new Exception("La ciudad no existe");
        }
        System.out.println("eliminando ciudad"+ buscar.getNombre());
        ciudadRepo.delete(buscar);
    }

    @Override
    public List<Ciudad> listarCiudades() {
        return ciudadRepo.findAll();
    }

    @Override
    public List<Ciudad> listarCiudadByDepartamento(Departamento dep) {
        return ciudadRepo.listarCiudadByDepartamento(dep.getNombre());
    }

    @Override
    public Vuelo crearVuelo(Vuelo vuelo) throws Exception {
        Vuelo vueloBuscado = buscarVuelo(vuelo.getCodigo());
        if(vueloBuscado!=null){
            throw new Exception("El vuelo ya existe");
        }
        return vueloRepo.save(vuelo);
    }

    @Override
    public Vuelo actualizarVuelo(Vuelo vuelo) throws Exception {
        Vuelo vueloBuscado= buscarVuelo(vuelo.getCodigo());
        if(vueloBuscado==null){
            throw new Exception("El vuelo no existe");
        }
        return vueloRepo.save(vuelo);
    }

    @Override
    public void eliminarVuelo(String codigo) throws Exception {
        Vuelo buscarVuelo= buscarVuelo(codigo);
        if(buscarVuelo== null){
            throw new Exception("El vuelo no existe");
        }
        vueloRepo.delete(buscarVuelo);

    }

    @Override
    public List<Vuelo> listarVuelo() {
        return vueloRepo.findAll();
    }

    @Override
    public List<Vuelo> listarVueloByCiudad(Ciudad ciudad) {
        return vueloRepo.findByCiudadOrigen(ciudad);
    }



    @Override
    public AdministradorHotel crearAdministradorHotel(AdministradorHotel administradorHotel) throws Exception{
        AdministradorHotel nuevoAdmin= buscarAdminHotel(administradorHotel.getCedula());
        if(nuevoAdmin!=null){
            throw new Exception("Ya existe el administrador");
        }
        nuevoAdmin=buscarAdminHotelByEmail(administradorHotel.getEmail());

        if(nuevoAdmin!=null){
            throw new Exception("El correo del usuario ya está registrado");
        }
        System.out.println("el admin hotel se guarda");
        return administradorHotelRepo.save(administradorHotel);



    }

    @Override
    public AdministradorHotel modificarAdminHotel(AdministradorHotel administradorHotel) throws Exception {
        AdministradorHotel buscarAdmin=buscarAdminHotel(administradorHotel.getCedula());
        if(buscarAdmin==null){
            throw new Exception("No existe el administrador de hotel");
        }
        System.out.println("admin actualizado");
        return administradorHotelRepo.save(administradorHotel);


    }

    @Override
    public Silla crearSilla(Silla s) {
        return sillaRepo.save(s);
    }

    @Override
    public Tour crearTour(Tour tour) throws Exception {
        return null;
    }

    @Override
    public CodigoDescuento crearCodigoDescuento(CodigoDescuento codigoDescuento) throws Exception{
        CodigoDescuento buscarCodigo= buscarCodigoDescuento(codigoDescuento.getCodigo());
        if(buscarCodigo!=null){
            throw new Exception("Codigo de descuento ya existe");
        }

        return codigoDescuento;
    }
}
