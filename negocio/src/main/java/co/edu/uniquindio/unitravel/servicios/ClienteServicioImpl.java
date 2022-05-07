package co.edu.uniquindio.unitravel.servicios;


import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.repositorios.*;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Autowired
    private ReservaHabitacionRepo reservaHabitacionRepo;

    @Autowired
    private ReservaSillaRepo reservaSillaRepo;

    @Autowired
    private VueloRepo vueloRepo;

    @Autowired
    private  CodigoDescuentoRepo codigoDescuentoRepo;

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
    public ReservaHabitacion crearReservaHabitacion(ReservaHabitacion rh) {
        return reservaHabitacionRepo.save(rh);
    }

    @Override
    public ReservaSilla asignarSillas(Silla silla) {
        ReservaSilla rs= new ReservaSilla();
        rs.setSilla(silla);
        rs.setPrecio(silla.getPrecio());
        return rs;
    }

    @Override
    public ReservaSilla crearReservaSilla(Silla silla, Reserva reserva) throws Exception {
        ReservaSilla rs= new ReservaSilla();
        if(silla.getDisponible()){
            rs.setSilla(silla);
            rs.setPrecio(silla.getPrecio());
            rs.setReserva(reserva);
            silla.setDisponible(false);
        }else{
            throw new Exception("La silla no está disponible");
        }

        return reservaSillaRepo.save(rs);
    }



    @Override
    public double aplicarCodigoDescuento(CodigoDescuento codigoDescuento) throws Exception {
        CodigoDescuento cd= codigoDescuentoRepo.findById(codigoDescuento.getCodigo()).orElse(null);
        if(cd==null){
            throw new Exception("No existe el codigo de descuento");
        }
        if(codigoDescuento.getFechaVencimiento().isBefore(LocalDateTime.now())){
            throw new Exception("Codigo de descuento caducado");
        }
        return codigoDescuento.getDescuento();
    }

    @Override
    public ReservaHabitacion buscarReservaHabitacion(Integer codigo) throws Exception {

        return reservaHabitacionRepo.findById(codigo).orElse(null);
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
        //@TODO
        // para realizar la validacion de las fechas de las habitaciones
        // hacemos una consulta donde se traiga las reservas que contiene la habitacion
        // y que la fechas de reserva sean mayores o igual que las de a reserva actual

        List<ReservaHabitacion> habitacions= reserva.getHabitaciones();
        /**for(ReservaHabitacion rh: habitacions){
            if(rh.getReserva().getFechaInicio().compareTo(reserva.getFechaInicio())>=0
            && rh.getReserva().getFechaFin().compareTo(reserva.getFechaInicio())<=0
            || rh.getReserva().getFechaInicio().compareTo(reserva.getFechaFin())>=0
            && rh.getReserva().getFechaFin().compareTo(reserva.getFechaFin())<=0){
                //throw new Exception("La habitación no está disponible para esa fecha");
            }
        }**/

        vuelosDisponibles(reserva.getVueloIda());
        vuelosDisponibles(reserva.getVueloRegreso());

        reserva.setPrecioTotal((calcularCostoReservaHabitacion(reserva)+ calcularCostoReservaSilla(reserva)));
        if(reserva.getCodigoDescuento()!=null){
            reserva.setPrecioTotal(reserva.getPrecioTotal()*aplicarCodigoDescuento(reserva.getCodigoDescuento()));
        }
        //Validar vuelos dispoibles
        //Validar silla random
        //return reservaRepo.save(reserva);
        return enviarCorreoReserva(reserva);
    }

    @Override
    public Boolean vuelosDisponibles(Vuelo v) throws Exception{
        Vuelo buscar= vueloRepo.findById(v.getCodigo()).orElse(null);
        if(buscar==null)
            throw new Exception("El vuelo no existe");
        return true;
    }

    @Override
    public Reserva enviarCorreoReserva(Reserva reserva) throws Exception {
        System.out.println("se enviará correo a: "+ reserva.getCliente().getEmail());
        try {
            emailServicio.enviarEmail("Reserva realizada",
                    "Felicidades por su reserva #: "+reserva.getCodigo()+ " por el valor de" +
                            ": "+reserva.getPrecioTotal()+ " con vuelos: "+ reserva.getVueloIda().getCodigo()+
                    " y "+ reserva.getVueloRegreso().getCodigo(), reserva.getCliente().getEmail());

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return reservaRepo.save(reserva);
    }

    @Override
    public double calcularCostoReservaSilla(Reserva reserva) throws Exception {
        List<ReservaSilla> reservaSillas= reserva.getReservaSillas();
        double costo=0;
        for (ReservaSilla rs: reservaSillas  ) {
            costo+= rs.getPrecio();
        }
        return costo;
    }

    @Override
    public double calcularCostoReservaHabitacion(Reserva reserva) throws Exception {
        List<ReservaHabitacion> reservaHabitacions= reserva.getHabitaciones();
        double costo=0;
        for (ReservaHabitacion rh: reservaHabitacions ) {
            costo+=rh.getPrecio();
        }
        return costo;
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
    public Vuelo buscarVuelos(Ciudad ciudad, LocalDate localDate) {
        return null;
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
        enviarCorreoRecovery(buscarCliente);
         return "Correo enviado";
    }


    @Override
    public void enviarCorreoRecovery(Cliente c) throws Exception {
        try {
            emailServicio.enviarEmail("Recuperacion de contraseña",
                    "Su contraseña es: "+c.getPassword(),c.getEmail() );

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }
}
