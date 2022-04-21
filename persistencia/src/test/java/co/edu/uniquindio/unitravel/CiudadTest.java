package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.repositorio.CiudadRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CiudadTest {

    @Autowired
    CiudadRepo ciudadRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCIudades(){
        System.out.println(ciudadRepo.findAll());
    }
}
