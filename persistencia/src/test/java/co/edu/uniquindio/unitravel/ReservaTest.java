package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.repositorio.ReservaRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReservaTest {

    @Autowired
    ReservaRepo reservaRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void listarReserva(){
        System.out.println(reservaRepo.findAll());
    }
}
