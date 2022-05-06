package co.edu.uniquindio.unitravel.repositorios;


import co.edu.uniquindio.unitravel.entidades.Ciudad;
import co.edu.uniquindio.unitravel.entidades.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VueloRepo extends JpaRepository<Vuelo, String> {

    List<Vuelo> findByCiudadOrigen(Ciudad ciudad);

}
