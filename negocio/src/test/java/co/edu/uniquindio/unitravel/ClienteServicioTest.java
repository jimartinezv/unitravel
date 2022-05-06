package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.servicios.AdministradorServicio;
import co.edu.uniquindio.unitravel.servicios.CiudadServicio;
import co.edu.uniquindio.unitravel.servicios.ClienteServicio;
import co.edu.uniquindio.unitravel.servicios.EmailServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    /**
    private void crearReserva(){
        Cliente clienteReserva= crearCliente();
        registrarClienteTest();
        Reserva reserv= new Reserva();
        reserv.setCliente(clienteReserva);
        reserv.setCantidadPersonas((byte)2);
        reserv.setFechaReserva(LocalDateTime.now());
        LocalDate fechaInicio = LocalDate.parse("2022-10-10");
        LocalDate fechaFin = LocalDate.parse("2022-10-17");
        reserv.setFechaInicio(fechaInicio);
        reserv.setFechaFin(fechaFin);
        reserv.setEstado("ACTIVA");
        reserv.setPrecioTotal(1950000);
        reserv.setHabitaciones(crearReservaHabitaciones());
        //return reserv;
    }
    **/
    public Vuelo crearVuelo(){
        return null;
    }
    public Silla crearSillas(){
        return null;
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
    public Habitacion crearHabitacion(){
        Habitacion hab= new Habitacion();
        hab.setHotel(crearHotel());
        hab.setCapacidad((byte)2);
        hab.setCodigo("R1");
        hab.setPrecio(600000);

        return hab;
    }

    public List<ReservaHabitacion> crearReservaHabitaciones(){
        ReservaHabitacion reservaHabitacion= new ReservaHabitacion();
        reservaHabitacion.setHabitacion(crearHabitacion());
        reservaHabitacion.setPrecio(600000.0);
        List<ReservaHabitacion> reservaHabitacions= new ArrayList<>();
        reservaHabitacions.add(reservaHabitacion);
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
    @Sql("classpath:dataset.sql")
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
            Cliente login=clienteServicio.login(c.getEmail(),c.getPassword());
            Assertions.assertEquals(login.getNombre(),"Jorge Iván");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarClientesReservaTest(){

        clienteServicio.listarClientesReserva().forEach(System.out::println);
    }

    @Test
    //@Sql("classpath:dataset.sql")
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
            Ciudad c= clienteServicio.buscarCiudad(66);
            List<Hotel> hotels= clienteServicio.buscarHotelesByCiudad(c);
            Assertions.assertEquals(hotels.size(), 4);
        }catch (Exception e){
            System.out.println(e.getMessage()+"error");
        }


    }

    @Test
    //@Sql("classpath:dataset.sql")
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


}
