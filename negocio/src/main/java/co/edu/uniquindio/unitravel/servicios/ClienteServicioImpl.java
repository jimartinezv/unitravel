package co.edu.uniquindio.unitravel.servicios;


import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.repositorios.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
    public List<Vuelo> listarVuelos() {
        return vueloRepo.findAll();
    }

    @Override
    public List<Vuelo> vueloByCiudadandFecha(Integer ciudadO, Integer ciudadD, LocalDate fecha) throws Exception{

        List<Vuelo> v= vueloRepo.vueloByCiudadAndDate(ciudadO, ciudadD,fecha);
        if(v.isEmpty()){
            throw new Exception("No se encontraron vuelos");
        }
        return v;
        //return vueloRepo.vueloByCiudadAndDate(ciudadO, ciudadD,fecha);
    }

    @Override
    public Vuelo buscarVuelo(Integer codigo) throws Exception {
        return vueloRepo.findById(codigo).orElse(null);
    }

    @Override
    public ReservaHabitacion crearReservaHabitacion(ReservaHabitacion rh) {
        return reservaHabitacionRepo.save(rh);
    }



    @Override
    public List<ReservaSilla> asignarSillas(List<Silla> silla, Reserva reserva) throws Exception {

        List<ReservaSilla> reservaSillaList;
        if(reserva.getReservaSillas()!=null){
            reservaSillaList=reserva.getReservaSillas();
        }else {
            reservaSillaList=new ArrayList<>();
        }



        int disponibles=0;
        System.out.println("cantidad de sillas del avion: " + silla.size());
        for (Silla s:silla) {
            System.out.println("Recorriendo silas");
            if(s.isDisponible()) {
                System.out.println("Aqui una disponible");
                disponibles++;
            }
        }
        System.out.println(disponibles+" disponibles total");
        if(disponibles<reserva.getCantidadPersonas()){
            throw new Exception("La cantidad de personas excede sillas disponibles");
        }
        for(int i=0;i<reserva.getCantidadPersonas();i++){
            if(silla.get(i).isDisponible()) {
                silla.get(i).setDisponible(false);
                sillaRepo.save(silla.get(i));
                ReservaSilla s=crearReservaSilla(silla.get(i),reserva);
                reservaSillaList.add(s);
                //reserva.getReservaSillas().add(s);


            }}

        reserva.setReservaSillas(reservaSillaList);
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
        System.out.println("codigo descuento "+codigoDescuento.getCodigo());
        CodigoDescuento cd= codigoDescuentoRepo.findById(codigoDescuento.getId()).orElse(null);
        if(cd==null){
            throw new Exception("No existe el codigo de descuento");
        }
        if(codigoDescuento.getFechaVencimiento()!=null && codigoDescuento.getFechaVencimiento().isBefore(LocalDateTime.now())){
            throw new Exception("Codigo de descuento caducado");
        }
        return codigoDescuento.getDescuento()/100-1;
    }

    @Override
    public CodigoDescuento findCodigo(String codigo) throws Exception {
        return codigoDescuentoRepo.getByCodigo(codigo);
    }

    @Override
    public ReservaHabitacion buscarReservaHabitacion(Integer codigo) throws Exception {

        return reservaHabitacionRepo.findById(codigo).orElse(null);
    }

    @Override
    public List<ReservaHabitacion> reservasByHabitacion(Habitacion habitacion) {
        return reservaHabitacionRepo.reservasByHabitacion(habitacion.getCodigo());
    }

    @Override
    public Habitacion buscarHabitacion(Integer codigo) throws Exception {

        return habitacionRepo.findById(codigo).orElse(null);
    }

    @Override
    public Hotel buscarHotelByHabitacion(Integer codigo) throws Exception {
        return habitacionRepo.buscarHotelHabitacion(codigo);
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
        System.out.println("Aquí entró");

        for (ReservaHabitacion rh:reserva.getReservaHabitacions()) {
            System.out.println("Aquí entró tambien");
            habitacionDisponible(rh.getHabitacion(),reserva.getFechaInicio(),reserva.getFechaFin());
        }
        //vuelosDisponibles(reserva.getVueloIda());
        //vuelosDisponibles(reserva.getVueloRegreso());
        System.out.println("Se esta colocando el precio");
        reserva.setPrecioTotal((calcularCostoReservaHabitacion(reserva)+ calcularCostoReservaSilla(reserva.getReservaSillas())));
        System.out.println("se colocó precio");
        if(reserva.getCodigoDescuento()!=null){
            reserva.setPrecioTotal(reserva.getPrecioTotal()*-(aplicarCodigoDescuento(reserva.getCodigoDescuento())));
        }
        //Validar vuelos dispoibles
        //Validar silla random
        //return reservaRepo.save(reserva);
        //return enviarCorreoReserva(reserva);
        return reservaRepo.save(reserva);
    }

    @Override
    public Reserva guardarReserva(Reserva reserva) throws Exception {
        if(reserva.getEstado().equals("FINALIZADO")){
            enviarCorreoReserva(reserva);
        }
        return reservaRepo.save(reserva);
    }

    @Override
    public boolean habitacionDisponible(Habitacion h, LocalDate checkin, LocalDate checkout) throws Exception {

        if(reservaHabitacionRepo.habitaciones(h.getCodigo(),checkin,checkout)!=null){
            throw new Exception("La habitación no está disponible para la fecha seleccionada");
        };
        return true;
    }

    @Override
    public Boolean vuelosDisponibles(Vuelo v) throws Exception{
        Vuelo buscar= vueloRepo.findById(v.getId()).orElse(null);
        if(buscar==null)
            throw new Exception("El vuelo no existe");
        return true;
    }

    @Override
    public Reserva enviarCorreoReserva(Reserva reserva) throws Exception {
        System.out.println("se enviará correo a: "+ reserva.getCliente().getEmail());
        try {
            emailServicio.enviarEmail("Reserva de Unitravel",
                    "Felicidades por su reserva #: "+reserva.getCodigo()+ " por el valor de" +
                            ": "+reserva.getPrecioTotal()+ " con vuelos: de ida: "+reserva.getReservaSillas().get(0).getSilla().getVuelo().getCodigo()+
                    " con vuelo de regreso: "+reserva.getReservaSillas().get(reserva.getReservaSillas().size()-1).getSilla().getVuelo().getCodigo(), reserva.getCliente().getEmail());

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return reservaRepo.save(reserva);
    }

    @Override
    public void enviarCorreo(String email) throws Exception {
        System.out.println("se enviará correo a: " + email);
        try {
            emailServicio.enviarEmail("Reserva realizada",
                    "Felicidades por su reserva #:  por el valor de" +
                            ":  con vuelos: y ", email);

        }catch (MailException e){
            System.out.println(e.getMessage()+" este fue el error");
            throw new Exception(e.getMessage());
        }


    }


    @Override
    public double calcularCostoReservaSilla(List<ReservaSilla> reserva) throws Exception {
        List<ReservaSilla> reservaSillas= reserva;
        System.out.println("se estan calculando los costos de las reservas sillas sillasize "+reserva.size() );
        double costo=0;
        for (ReservaSilla rs: reservaSillas  ) {
            costo+= rs.getPrecio();
        }
        return costo;
    }

    @Override
    public double calcularCostoReservaHabitacion(Reserva reserva) throws Exception {

        double costo=0;
        //for (ReservaHabitacion rh: reservaHabitacions ) {
           // if(reservaHabitacionRepo.buscarReservaPorFecha(rh.getCodigo(),reserva.getFechaInicio(),reserva.getFechaFin())!=null){
           //     throw new Exception("La habitación no está disponible para la fecha seleccionada");
           // }
            //costo+=rh.getPrecio();
        //}
        System.out.println("si va a hacer consulta de costos");
        costo= reservaHabitacionRepo.calcularCostoReserva(reserva.getCodigo());
        System.out.println("si hizo consulta de costos");
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
        comentario.setFecha(LocalDateTime.now());
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
    public List<Hotel> buscarHotelesByCiudad(Integer ciudad) {
        return hotelRepo.obtenerHotelByCodigoCiudad(ciudad);

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
    public Vuelo buscarVueloByCodigo(Integer codigo) {
        return vueloRepo.findById(codigo).orElse(null);
    }



    @Override
    public List<Hotel> buscarHotelesPorNombre(String nombre) {
       return hotelRepo.obtenerHotelesByNombre("%"+nombre+"%");

    }

    @Override
    public List<Hotel> buscarHotelesByCaracteristicas(Integer cat) {

        return hotelRepo.obtenerHotelesByCaracteristicas(cat);
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
