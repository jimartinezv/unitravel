package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.servicios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;

@SpringBootTest
@Transactional //Garantiza que los datos no sean persistentes en la base de datos
public class ClienteServicioTest {


    @Autowired
    private ClienteServicio clienteServicio;


    @Autowired
    private CiudadServicio ciudadServicio;

    @Autowired
    private EmailServicio emailServicio;

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private ServiciosGenerales serviciosGenerales;

    public Cliente crearCliente(){
        Cliente u = new Cliente("1094900","Jorge Iván", "Vargas", "password", "port@gmail.com",null,null, null);
        Map<String, String> telefono= new HashMap<>();
        telefono.put("3215566772","Trabajo");
        telefono.put("6067374637","Casa");
        telefono.put("3219900211","Personal");
        u.setTelefono(telefono);
        Ciudad c= ciudadServicio.obtenerCiudad(66);
        u.setCiudad(c);

        u.setGenero(Genero.MASCULINO);
        return u;
    }

    public Comentario crearComentario(){
        Cliente clienteComentario= crearCliente();
        registrarClienteTest();
        Comentario c= new Comentario();
        c.setComentario("El hotel es excelente");
        c.setCalificacion(5);
        LocalDateTime localDateTime= LocalDateTime.now();
        c.setFecha(localDateTime);
        c.setCliente(clienteComentario);

    return  c;
    }

    public Habitacion crearHabitacion(){
        Habitacion h= new Habitacion();
        h.setCapacidad((byte) 2);
        h.setPrecio(20000);

        h.setNombre("H1");
        try{
            h.setHotel(clienteServicio.buscarHotelPorCodigo(1));
            //administradorServicio.crearHabitacion(h);
            //administradorServicio.buscarHabitacion(h.getCodigo());

        }catch (Exception e){
            System.out.println(e.getMessage()+" error");

        }
        return h;
    }
    public ReservaHabitacion crearReservaHabitacion(){
        ReservaHabitacion rh= new ReservaHabitacion();
        try {
            rh.setHabitacion(clienteServicio.buscarHabitacion(1));
        }catch (Exception e){

        }

        //rh.setHabitacion(crearHabitacion());

        rh.setPrecio(rh.getHabitacion().getPrecio());

        //clienteServicio.habitacionDisponible(rh.getHabitacion(),r)
        clienteServicio.crearReservaHabitacion(rh);
        return rh;
    }

    public ReservaSilla reservarSilla(List<Silla> sillas){
        List<ReservaSilla> reservaSillas=new ArrayList<>();

        for (Silla s: sillas) {

        }
        return null;
    }


    public Reserva crearReserva(){
        Reserva nr= new Reserva();
        nr.setCantidadPersonas((byte)(2));

        nr.setFechaInicio(LocalDate.parse("2022-10-09"));
        nr.setFechaFin(LocalDate.parse("2022-10-13"));
        nr.setFechaReserva(LocalDateTime.now());




        try {
            nr.setCliente(clienteServicio.obtenerUsuario("1"));
            nr.setEstado("FINALIZADO");

            nr.setReservaHabitacions(crearReservaHabitaciones());
            //Vuelo v=crearVuelo("A4738");
            Vuelo v= clienteServicio.buscarVueloByCodigo("A4738");
            //administradorServicio.crearVuelo(v);
            List<ReservaSilla> rs= new ArrayList<>();
            nr.setReservaSillas(rs);

            clienteServicio.asignarSillas(clienteServicio.buscarSillasByVuelo(v.getCodigo()),nr);



            //v.setSilla(crearSillasVuelo(v));
            //administradorServicio.actualizarVuelo(v);
            //nr.setVueloIda(v);
            //Vuelo v2=crearVuelo2("A4536");
            Vuelo v2= clienteServicio.buscarVueloByCodigo("A4536");
            //administradorServicio.crearVuelo(v2);

            //nr.getReservaSillas().addAll(clienteServicio.asignarSillas(v2.getSilla(),nr));
            //v2.setSilla(crearSillasVuelo(v2));
            //administradorServicio.actualizarVuelo(v2);
            clienteServicio.asignarSillas(clienteServicio.buscarSillasByVuelo(v2.getCodigo()), nr);
            //nr.setVueloRegreso(v2);
        }catch (Exception e){
            System.out.println(e.getMessage()+ " error");
        }

        return nr;

    }

    public Vuelo crearVuelo(String codigo){
        Vuelo v= new Vuelo();
        v.setAerolinea("AVIANCA");
        v.setCodigo(codigo);
        v.setSilla(crearSillasVuelo(v));
        try {
            v.setCiudadOrigen(serviciosGenerales.buscarCiudad(50));
            v.setCiudadDestino(serviciosGenerales.buscarCiudad(66));
        }catch (Exception e){
        }
        return v;
    }
    public Vuelo crearVuelo2(String codigo){
        Vuelo v= new Vuelo();
        v.setAerolinea("AVIANCA");
        v.setCodigo(codigo);
        v.setSilla(crearSillasVuelo2(v));
        try {
            v.setCiudadOrigen(serviciosGenerales.buscarCiudad(66));
            v.setCiudadDestino(serviciosGenerales.buscarCiudad(50));
        }catch (Exception e){
        }
        return v;
    }

    public List<Silla> crearSillasVuelo2(Vuelo v){
        List<Silla> sillas=new ArrayList<>();
        Silla a= new Silla();

        a.setPosicion("A1");

        a.setPrecio(20000);
        a.setVuelo(v);
        administradorServicio.crearSilla(a);

        Silla b= new Silla();

        b.setPosicion("B1");

        b.setPrecio(20000);
        b.setVuelo(v);
        administradorServicio.crearSilla(b);

        sillas.add(a);
        sillas.add(b);
        return sillas;
    }


    public List<Silla> crearSillasVuelo(Vuelo v){
        List<Silla> sillas=new ArrayList<>();
        Silla a= new Silla();

        a.setPosicion("A1");

        a.setPrecio(20000);
        a.setVuelo(v);
        administradorServicio.crearSilla(a);

        Silla b= new Silla();

        b.setPosicion("B1");

        b.setPrecio(20000);
        b.setVuelo(v);
        administradorServicio.crearSilla(b);

        sillas.add(a);
        sillas.add(b);
        return sillas;
    }

    public Hotel crearHotel(){
        Hotel hotel= new Hotel();
        hotel.setNombre("Hotel el paraiso");
        hotel.setNumEstrellas(5);
        hotel.setDireccion(crearDireccion());
        return hotel;
    }

    public Direccion crearDireccion(){
        Direccion dir= new Direccion();
        dir.setDireccion("Calle del mar 356");
        dir.setCiudad(BuscarCiudad());

        return dir;
    }
    public Ciudad BuscarCiudad(){
        return ciudadServicio.obtenerCiudad(66);
    }


    public List<ReservaHabitacion> crearReservaHabitaciones(){

        List<ReservaHabitacion> reservaHabitacions= new ArrayList<>();
        reservaHabitacions.add(crearReservaHabitacion());


        return reservaHabitacions;
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void registrarClienteTest(){

        try {
            Cliente guardado= clienteServicio.registrarUsuario(crearCliente());
            Assertions.assertNotNull(guardado);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarClienteTest(){

        try {
            Cliente guardado= clienteServicio.registrarUsuario(crearCliente());
            Cliente buscado=clienteServicio.obtenerUsuario(guardado.getCedula());
            Assertions.assertNotNull(buscado);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarClienteTest(){
        Cliente c= crearCliente();

        try {
            clienteServicio.registrarUsuario(c);
            clienteServicio.eliminarUsuario(c.getCedula());
            Cliente e= clienteServicio.obtenerUsuario(c.getCedula());
            Assertions.assertNull(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarClienteTet(){
        Cliente c= crearCliente();

        try {
            clienteServicio.registrarUsuario(c);
            c.setGenero(Genero.FEMENINO);
            c.setEmail("0039@gmail.com");
            clienteServicio.actualizarUsuario(c);
            clienteServicio.listarUsuarios().forEach(System.out::println);
            Assertions.assertEquals("0039@gmail.com", c.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    //@Sql("classpath:dataset.sql")
    public void recuperarPasswordTest(){
        try {
            Cliente c= clienteServicio.obtenerUsuario("1");
            System.out.println(c);
            emailServicio.enviarEmail("correo de recuperacion para: "+c.getNombre(),"Su contraseña es: "+ c.getPassword(), c.getEmail());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarClientesTest(){
        Cliente c= crearCliente();

        try {
            clienteServicio.registrarUsuario(c);
            List<Cliente> cp=clienteServicio.listarUsuarios();
            cp.forEach(System.out::println);
            Assertions.assertEquals(5,clienteServicio.listarUsuarios().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void loginClientesTest(){
        Cliente c= crearCliente();

        try {
            clienteServicio.registrarUsuario(c);
            Cliente login=(Cliente) serviciosGenerales.login(c.getEmail(),c.getPassword());
            Assertions.assertEquals(login.getNombre(),"Jorge Iván");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    @Sql("classpath:dataset.sql")
    public void listarReservaByEmailTest(){

        try {
            List<Reserva> rc=clienteServicio.listarReservasByCliente("guti@gmail.com");
            rc.forEach(System.out::println);

            Assertions.assertEquals(rc.size(),2);
        }catch (Exception e){

        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarHotelesByCiudadTest(){
        try {
            Ciudad c= serviciosGenerales.buscarCiudad(66);
            List<Hotel> hotels= clienteServicio.buscarHotelesByCiudad(c.getCodigo());
            Assertions.assertEquals(hotels.size(), 4);
        }catch (Exception e){
            System.out.println(e.getMessage()+"error");
        }


    }

    @Test
    @Sql("classpath:dataset.sql")
    public void crearComentarioTest(){

        Comentario c= crearComentario();
        try {


            c.setHotel(clienteServicio.buscarHotelPorCodigo(1));
            clienteServicio.comentarHotel(c);
            Assertions.assertEquals(6,clienteServicio.listarComentarios().size());

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        clienteServicio.listarComentarios().forEach(System.out::println);

    }

    @Test
    //@Sql("classpath:dataset.sql")
    public void crearReservaTest(){

        Reserva r= crearReserva();

        try {



            clienteServicio.crearReserva(r);

            System.out.println(r+" reserva creada");
        }catch (Exception e){
            System.out.println(e.getMessage()+ "error creando reserva");
        }

    }



}
