package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.repositorios.VueloRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VueloTest {
    @Autowired
    VueloRepo vueloRepo;
    @Test
    @Sql("classpath:dataset.sql")
    public void listarVuelos(){
        System.out.println(vueloRepo.findAll());
    }
}
