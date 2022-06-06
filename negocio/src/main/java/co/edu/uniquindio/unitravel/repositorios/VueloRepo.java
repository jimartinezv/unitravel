package co.edu.uniquindio.unitravel.repositorios;


import co.edu.uniquindio.unitravel.entidades.Ciudad;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.entidades.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VueloRepo extends JpaRepository<Vuelo, String> {

    List<Vuelo> findByCiudadOrigen(Ciudad ciudad);


    @Query("select h from Vuelo h inner join h.ciudadOrigen co inner join h.ciudadDestino cd where h.fecha=:fecha and co.codigo=:ciudadO and cd.codigo=:ciudadD ")
    List<Vuelo> vueloByCiudadAndDate(Integer ciudadO, Integer ciudadD, LocalDate fecha);


    //@Query("select v from Vuelo v join v.ciudadOrigen co join v.ciudadDestino cd where v.fecha=:fecha")
    //List<Vuelo> vueloByCiudadAndDate(LocalDate fecha);
    //List<Vuelo> vueloByCiudadAndDate(Integer ciudadOrigen, Integer ciudadDestino, LocalDate fecha);
}
