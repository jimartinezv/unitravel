package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.Caracteristica;
import co.edu.uniquindio.unitravel.entidades.Habitacion;
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
    AdministradorHotelServicio adminHotelSer;

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
