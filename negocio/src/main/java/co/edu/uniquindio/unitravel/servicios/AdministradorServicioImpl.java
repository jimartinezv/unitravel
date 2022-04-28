package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorServicioImpl implements AdministradorServicio{
    @Override
    public Administrador registrarAdmin(Administrador administrador) throws Exception {
        return null;
    }

    @Override
    public Administrador actualizarAdmin(Administrador administrador) throws Exception {
        return null;
    }

    @Override
    public void eliminarAdministraor(String id) throws Exception {

    }

    @Override
    public List<Administrador> listarAdministradores() {
        return null;
    }

    @Override
    public void loginAdmin(String correo, String password) throws Exception {

    }

    @Override
    public Destino gestionarDestino(Destino destino) throws Exception {
        return null;
    }

    @Override
    public Vuelo crearVuelo(Vuelo vuelo) throws Exception {
        return null;
    }

    @Override
    public Vuelo gestionarVuelo(Vuelo vuelo) throws Exception {
        return null;
    }

    @Override
    public AdministradorHotel gestionarAdminHotel(AdministradorHotel administradorHotel) {
        return null;
    }

    @Override
    public Hotel registrarHotel(Hotel hotel) {
        return null;
    }

    @Override
    public Tour crearTour(Tour tour) throws Exception {
        return null;
    }

    @Override
    public CodigoDescuento crearCodigoDescuento(CodigoDescuento codigoDescuento) {
        return null;
    }
}
