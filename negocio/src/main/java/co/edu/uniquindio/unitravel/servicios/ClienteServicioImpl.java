package co.edu.uniquindio.unitravel.servicios;


import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.repositorios.ClienteRepo;
import co.edu.uniquindio.unitravel.repositorios.ComentarioRepo;
import co.edu.uniquindio.unitravel.repositorios.HotelRepo;
import co.edu.uniquindio.unitravel.repositorios.ReservaRepo;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.ToDoubleBiFunction;

@Service
public class ClienteServicioImpl implements ClienteServicio{

    private ClienteRepo usuarioRepo;

    public ClienteServicioImpl (ClienteRepo usuarioRepo){
        this.usuarioRepo=usuarioRepo;
    }

    @Autowired
    private ComentarioRepo comentarioRepo;

    @Autowired
    private ReservaRepo reservaRepo;

    @Autowired
    private HotelRepo hotelRepo;

    @Override
    public Cliente registrarUsuario(Cliente u) throws Exception {
        Cliente buscado= obtenerUsuario(u.getCedula());
        if (buscado!=null){
            throw new Exception("EL codigo del usuario ya está registrado");
        }
        Cliente buscadoEmail= buscarClienteEmail(u.getEmail());
        if(buscadoEmail!=null){
            throw new Exception("El correo del usuario ya está registrado");
        }

        return usuarioRepo.save(u);

    }

    public Cliente buscarClienteEmail(String email){
        return usuarioRepo.findByEmail(email).orElse(null);

    }

    @Override
    public Cliente actualizarUsuario(Cliente u) throws Exception {
        Cliente buscado= obtenerUsuario(u.getCedula());
        if(buscado==null){
            throw new Exception("El usuario no existe en la base de datos");
        }
        return usuarioRepo.save(u);
    }

    @Override
    public Cliente obtenerUsuario(String cedula) {
        return usuarioRepo.findById(cedula).orElse(null);
    }

    @Override
    public void eliminarUsuario(String cedula) throws Exception {
        Cliente cliente = obtenerUsuario(cedula);
        if(cliente==null){
            throw new Exception("El usuario no existe");
        }
        System.out.println("Usuario eliminado");
        usuarioRepo.delete(cliente);
    }

    @Override
    public List<Cliente> listarUsuarios() {
        return usuarioRepo.findAll();
    }

    /**
     * Busca determinada reserva
     * @param codigo
     * @return
     */
    public Reserva buscarReserva(Integer codigo){
        return reservaRepo.getById(codigo);
    }
    @Override
    public Reserva crearReserva(Reserva reserva) throws Exception {
        return reservaRepo.save(reserva);
    }

    @Override
    public List<Reserva> listarReserva() {
        return null;
    }

    @Override
    public Comentario comentarHotel(Comentario comentario) throws Exception {
        return comentarioRepo.save(comentario);
    }

    @Override
    public List<Comentario> listarComentarios() {
        return comentarioRepo.findAll();
    }

    @Override
    public void recuperarPassword(String id) throws Exception {

    }

    @Override
    public List<Cliente> listarClientesReserva() {
        return usuarioRepo.clientesReservas();
    }

    @Override
    public Reserva gestionarReserva(Reserva reserva) throws Exception {
        return null;
    }

    @Override
    public Cliente validarLogin(String email, String password) throws Exception {
        Optional<Cliente> cliente= usuarioRepo.findByEmailAndPassword(email, password);
        if (cliente.isEmpty()){
            throw new Exception("Los datos de autenticación son incorrectos");
        }
        return cliente.get();
    }

    @Override
    public List<Hotel> buscarHoteles(Destino destino) {
        return null;
    }

    @Override
    public Hotel buscarHotelPorCodigo(Integer codigo) throws Exception{

        Optional<Hotel> hotel= hotelRepo.findById(codigo);
        if(hotel.isEmpty()){
            throw new Exception("El hotel no existe");
        }
        return hotelRepo.getById(codigo);
    }
}