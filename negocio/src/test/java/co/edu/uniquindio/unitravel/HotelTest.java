package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.repositorios.HotelRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HotelTest {
    @Autowired
    HotelRepo hotelRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarHotel(){
        Hotel m= hotelRepo.findById(1).orElse(null);
        List<Hotel> h= hotelRepo.hotelPorEstrellas(5);
        h.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void ciudadHotel(){
        String ciudad = hotelRepo.obtenerNombreCiudadHotel(3);
        Assertions.assertEquals("Armenia", ciudad);
        System.out.println(ciudad);

    }
}
