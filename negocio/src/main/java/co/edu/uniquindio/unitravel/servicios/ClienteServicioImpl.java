package co.edu.uniquindio.unitravel.servicios;


import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.repositorios.*;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.ToDoubleBiFunction;

@Service
public class ClienteServicioImpl implements ClienteServicio{

    private ClienteRepo usuarioRepo;
    private ComentarioRepo comentarioRepo;
    private ReservaRepo reservaRepo;
    private HotelRepo hotelRepo;
    private EmailServicio emailServicio;
    private CiudadRepo ciudadRepo;

    public ClienteServicioImpl (ClienteRepo usuarioRepo, ComentarioRepo comentarioRepo,
                                ReservaRepo reservaRepo, HotelRepo hotelRepo, EmailServicio emailServicio,
                                CiudadRepo ciudadRepo){
        this.usuarioRepo=usuarioRepo;
        this.comentarioRepo=comentarioRepo;
        this.reservaRepo=reservaRepo;
        this.hotelRepo=hotelRepo;
        this.emailServicio= emailServicio;
        this.ciudadRepo= ciudadRepo;
    }


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
        List<ReservaHabitacion> habitacions= reserva.getHabitaciones();
        for(ReservaHabitacion rh: habitacions){
            if(rh.getReserva().getFechaInicio().compareTo(reserva.getFechaInicio())>=0
            && rh.getReserva().getFechaFin().compareTo(reserva.getFechaInicio())<=0
            || rh.getReserva().getFechaInicio().compareTo(reserva.getFechaFin())>=0
            && rh.getReserva().getFechaFin().compareTo(reserva.getFechaFin())<=0){
                throw new Exception("La habitación no está disponible para esa fecha");
            }
        }


        //Validar vuelos dispoibles
        //Validar silla random
        return reservaRepo.save(reserva);
    }

    @Override
    public List<Reserva> listarReserva(Cliente cliente) {

        return reservaRepo.reservasByCliente(cliente.getEmail());
    }

    @Override
    public Comentario comentarHotel(Comentario comentario) throws Exception {
        //Validar hotel y usuario
        Cliente buscarCliente= comentario.getCliente();
        if(buscarCliente==null){
            throw new Exception("No existe el cliente");

        }
        Hotel hotelBuscar= comentario.getHotel();
        if(hotelBuscar==null){
            throw new Exception("No existe el hotel");
        }
        return comentarioRepo.save(comentario);
    }

    @Override
    public List<Comentario> listarComentarios() {
        return comentarioRepo.findAll();
    }



    @Override
    public List<Cliente> listarClientesReserva() {
        return usuarioRepo.clientesReservas();
    }

    @Override
    public List<Reserva> listarReservasByCliente(String correo) throws Exception {
        return reservaRepo.reservasByCliente(correo);
    }


    @Override
    public Reserva modificarReserva(Integer codigo) throws Exception {
        Reserva buscarRes= buscarReserva(codigo);
        if(buscarRes==null){
            throw new Exception("La reserva no existe");
        }
        return reservaRepo.save(buscarRes);
    }

    @Override
    public void  eliminarReserva(Integer codigo) throws Exception {
        Optional<Reserva> reserva1= reservaRepo.findById(codigo);
        if(reserva1==null){
            throw new Exception("La reserva no existe");
        }
        reservaRepo.delete(reserva1.get());

    }

    @Override
    public Cliente login(String email, String password) throws Exception {
        Optional<Cliente> cliente= usuarioRepo.findByEmailAndPassword(email, password);
        if (cliente.isEmpty()){
            throw new Exception("Los datos de autenticación son incorrectos");
        }
        return cliente.get();
    }

    @Override
    public List<Hotel> buscarHotelesByCiudad(Ciudad ciudad) {
        return hotelRepo.obtenerHotelByCodigoCiudad(ciudad.getCodigo());

    }

    @Override
    public List<Ciudad> listarCiudades() {
        return ciudadRepo.findAll();
    }

    @Override
    public Ciudad buscarCiudad(Integer codigo) throws Exception {
        return ciudadRepo.findById(codigo).orElse(null);
    }

    @Override
    public Hotel buscarHotelPorCodigo(Integer codigo) throws Exception{

        Optional<Hotel> hotel= hotelRepo.findById(codigo);
        if(hotel.isEmpty()){
            throw new Exception("El hotel no existe");
        }
        return hotelRepo.getById(codigo);
    }


    @Override
    public List<Hotel> buscarHotelesPorNombre(String nombre) throws Exception {
        return hotelRepo.obtenerHotelesByNombre("%"+nombre+"%");
    }

    @Override
    public String recuperarContrasena(String correo) throws Exception {
        Cliente buscarCliente= buscarClienteEmail(correo);
        if(buscarCliente==null){
            throw new Exception("El usuario no existe");
        }
        enviarCorreo(buscarCliente);
         return "Correo enviado";
    }

    @Override
    public Cliente buscarCliente(String cedula) throws Exception {
        return usuarioRepo.findById(cedula).orElse(null);
    }

    @Override
    public void enviarCorreo(Cliente c) throws Exception {
        try {
            emailServicio.enviarEmail("Recuperacion de contraseña",
                    "Su contraseña es: "+c.getPassword(),c.getEmail() );

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }
}
