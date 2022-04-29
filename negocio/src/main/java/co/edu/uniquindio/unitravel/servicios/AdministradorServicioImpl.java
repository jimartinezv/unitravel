package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorServicioImpl implements AdministradorServicio{

    @Autowired
    private AdministradorRepo administradorRepo;

    @Autowired
    private VueloRepo vueloRepo;

    @Autowired
    private AdministradorHotelRepo administradorHotelRepo;

    @Autowired
    private HotelRepo hotelRepo;

    @Autowired
    private CodigoDescuentoRepo codigoDescuentoRepo;

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
    public Vuelo buscarBuelo(String codigo){
        return vueloRepo.getById(codigo);
    }

    /**
     * Determina la existencia del administrador de un hotel
     * @param cedula
     * @return
     */
    public AdministradorHotel buscarAdminHotel(String cedula){
        return administradorHotelRepo.getById(cedula);
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
    public Destino gestionarDestino(Destino destino) throws Exception {
        return null;
    }

    @Override
    public Vuelo crearVuelo(Vuelo vuelo) throws Exception {
        Vuelo vueloBuscado = buscarBuelo(vuelo.getCodigo());
        if(vueloBuscado!=null){
            throw new Exception("El vuelo ya existe");
        }
        return vueloRepo.save(vuelo);
    }

    @Override
    public Vuelo gestionarVuelo(Vuelo vuelo) throws Exception {
        Vuelo vueloBuscado= buscarBuelo(vuelo.getCodigo());
        if(vueloBuscado==null){
            throw new Exception("El vuelo no existe");
        }
        return vueloRepo.save(vuelo);
    }

    @Override
    public AdministradorHotel gestionarAdminHotel(AdministradorHotel administradorHotel) throws Exception {
        AdministradorHotel buscarAdminHotel= buscarAdminHotel(administradorHotel.getCedula());
        if(buscarAdminHotel==null){
            throw new Exception("El administrador de hotel no existe");
        }
        return administradorHotelRepo.save(administradorHotel);
    }

    @Override
    public Hotel registrarHotel(Hotel hotel) throws Exception{
        Hotel buscarHotel= buscarHotel(hotel.getCodigo());
        if(buscarHotel!=null){
            throw new Exception("El hotel ya existe");
        }
        return hotelRepo.save(hotel);
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
