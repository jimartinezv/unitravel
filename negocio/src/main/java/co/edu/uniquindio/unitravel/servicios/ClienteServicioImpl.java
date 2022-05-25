package co.edu.uniquindio.unitravel.servicios;


import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.repositorios.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


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
    private SillaRepo sillaRepo;

    @Autowired
    private VueloRepo vueloRepo;

    @Autowired
    private  CodigoDescuentoRepo codigoDescuentoRepo;

    @Autowired
    private HabitacionRepo habitacionRepo;

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
    public List<ReservaSilla> asignarSillas(List<Silla> silla, Reserva reserva) throws Exception {

        int disponibles=0;
        for (Silla s:silla) {
            //if(s.getDisponible())
                disponibles++;
        }
        if(disponibles<reserva.getCantidadPersonas()){
            throw new Exception("La cantidad de personas excede sillas disponibles");
        }
        for(int i=0;i<reserva.getCantidadPersonas();i++){}
            /**if(silla.get(i).getDisponible()) {
                silla.get(i).setDisponible(false);
                ReservaSilla s=crearReservaSilla(silla.get(i),reserva);

                //reserva.getReservaSillas().add(crearReservaSilla(silla.get(i),reserva));


            }*/

        return reserva.getReservaSillas();
    }

    @Override
    public List<Silla> buscarSillasByVuelo(String codigo) throws Exception {

        return reservaSillaRepo.sillasByVuelo(codigo);
    }

    @Override
    public ReservaSilla crearReservaSilla(Silla silla, Reserva reserva) throws Exception {
        ReservaSilla rs= new ReservaSilla();
        rs.setReserva(reserva);
        rs.setSilla(silla);
        rs.setPrecio(silla.getPrecio());


        return reservaSillaRepo.save(rs);
    }

    @Override
    public ReservaSilla actualizarReservaSilla(ReservaSilla rs,Reserva reserva) throws Exception {
        rs.setReserva(reserva);
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
    public Habitacion buscarHabitacion(Integer codigo) throws Exception {
        return habitacionRepo.findById(codigo).orElse(null);
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

        for (ReservaHabitacion rh:reserva.getReservaHabitacions()) {
            habitacionDisponible(rh.getHabitacion(),reserva);
        }
        //vuelosDisponibles(reserva.getVueloIda());
        //vuelosDisponibles(reserva.getVueloRegreso());

        reserva.setPrecioTotal((calcularCostoReservaHabitacion(reserva)+ calcularCostoReservaSilla(reserva.getReservaSillas())));
        if(reserva.getCodigoDescuento()!=null){
            reserva.setPrecioTotal(reserva.getPrecioTotal()*aplicarCodigoDescuento(reserva.getCodigoDescuento()));
        }
        //Validar vuelos dispoibles
        //Validar silla random
        //return reservaRepo.save(reserva);
        //return enviarCorreoReserva(reserva);
        return reservaRepo.save(reserva);
    }

    @Override
    public boolean habitacionDisponible(Habitacion h, Reserva r) throws Exception {
        if(reservaHabitacionRepo.habitaciones(h.getCodigo(),r.getFechaInicio(),r.getFechaFin())!=null){
            throw new Exception("La habitación no está disponible para la fecha seleccionada");
        };
        return true;
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
            /**emailServicio.enviarEmail("Reserva realizada",
                    "Felicidades por su reserva #: "+reserva.getCodigo()+ " por el valor de" +
                            ": "+reserva.getPrecioTotal()+ " con vuelos: "+ reserva.getVueloIda().getCodigo()+
                    " y "+ reserva.getVueloRegreso().getCodigo(), reserva.getCliente().getEmail());*/

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return reservaRepo.save(reserva);
    }

    @Override
    public double calcularCostoReservaSilla(List<ReservaSilla> reserva) throws Exception {
        List<ReservaSilla> reservaSillas= reserva;
        double costo=0;
        for (ReservaSilla rs: reservaSillas  ) {
            costo+= rs.getPrecio();
        }
        return costo;
    }

    @Override
    public double calcularCostoReservaHabitacion(Reserva reserva) throws Exception {
        List<ReservaHabitacion> reservaHabitacions= reserva.getReservaHabitacions();
        double costo=0;
        for (ReservaHabitacion rh: reservaHabitacions ) {
           // if(reservaHabitacionRepo.buscarReservaPorFecha(rh.getCodigo(),reserva.getFechaInicio(),reserva.getFechaFin())!=null){
           //     throw new Exception("La habitación no está disponible para la fecha seleccionada");
           // }
            costo+=rh.getPrecio();
        }
        return costo;
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
    public List<Hotel> listarHoteles() {
        return hotelRepo.findAll();
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
    public Vuelo buscarVueloByCodigo(String codigo) {
        return vueloRepo.findById(codigo).orElse(null);
    }


    @Override
    public List<Hotel> buscarHotelesPorNombre(String nombre) {
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
