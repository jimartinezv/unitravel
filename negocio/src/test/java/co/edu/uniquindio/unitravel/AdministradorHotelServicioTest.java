package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.servicios.AdministradorHotelServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class AdministradorHotelServicioTest {

    @Autowired
    private AdministradorHotelServicio adminHotelSer;

    public Direccion crearDireccion(){
        Direccion d= new Direccion();
        d.setDireccion("Calle las palmitas #10-20");

        d.setCiudad(adminHotelSer.buscarCiudad(66));
        try {
            adminHotelSer.crearDireccion(d);
        }catch (Exception e){

        }

        return d;
    }



    public Hotel crearHotel(){
        Hotel h= new Hotel();
        h.setNombre("Hotel el paraiso");
        h.setNumEstrellas(4);
        Direccion d= crearDireccion();
        h.setDireccion(d);


        return h;
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void loginAdminHotelTest(){
        try{
            AdministradorHotel ah=adminHotelSer.loginAdminHotel("jimv9200@gmail.com", "019992");
            Assertions.assertEquals(ah.getNombre(),"Jorge Iván");
        }catch (Exception e){

        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void crearHotelTest(){
        Hotel h= crearHotel();

        try {
            adminHotelSer.crearHotel(h);

            Assertions.assertEquals(adminHotelSer.buscarHotel(h.getCodigo()).getNombre(), "Hotel el paraiso");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarHotelTest(){
        Hotel h= crearHotel();

        try {
            adminHotelSer.crearHotel(h);
            h.setNombre("hotel el malo");
            adminHotelSer.modificarHotel(h);
            Assertions.assertEquals(adminHotelSer.buscarHotel(h.getCodigo()).getNombre(), "Hotel el malo");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarHotelTest(){
        Hotel h= crearHotel();

        try {
            adminHotelSer.crearHotel(h);
            adminHotelSer.buscarHotel(h.getCodigo());
            Assertions.assertEquals(adminHotelSer.buscarHotel(h.getCodigo()).getNombre(), "Hotel el paraiso");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarHotelTest(){
        Hotel h= crearHotel();
        try {
            adminHotelSer.crearHotel(h);

            adminHotelSer.eliminarHotel(h.getCodigo());
            Assertions.assertNull(adminHotelSer.buscarHotel(h.getCodigo()));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void crearHabitacionTest(){
        Habitacion h = new Habitacion();
        h.setPrecio(250000);
        h.setCapacidad((byte)3);
        try {
            h.setHotel(adminHotelSer.buscarHotel(1));
            adminHotelSer.crearHabitacion(h);
            Assertions.assertEquals(5,adminHotelSer.listarHabitaciones().size());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        adminHotelSer.listarHabitaciones().forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void crearCaracteristica(){
        Caracteristica c= new Caracteristica();
        c.setDescripcion("Hotel muy bonito");
        adminHotelSer.crearCaracteristica(c);
        adminHotelSer.listarCaracteristicas().forEach(System.out::println);
        Assertions.assertEquals(6, adminHotelSer.listarCaracteristicas().size());
    }
}
