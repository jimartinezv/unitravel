package co.edu.uniquindio.unitravel.repositorio;
import co.edu.uniquindio.unitravel.entidades.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface DepartamentoRepo extends JpaRepository<Departamento, Integer> {

}
