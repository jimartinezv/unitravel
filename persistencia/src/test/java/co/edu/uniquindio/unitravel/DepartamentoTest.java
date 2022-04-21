package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.Departamento;
import co.edu.uniquindio.unitravel.repositorio.DepartamentoRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DepartamentoTest {
    @Autowired
    DepartamentoRepo departamentoRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void listarDepartamentos(){
        List<Departamento> departamentos=departamentoRepo.findAll();
        //departamentoRepo.save(dep);
        //List<Departamento> departamentos= departamentoRepo.findAll();
        System.out.println(departamentos);
    }
}
