package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.entidades.Caracteristica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaracteristicaRepo extends JpaRepository<Caracteristica, Integer> {

    List<Caracteristica> findAllByTipo(String tipo);
}
