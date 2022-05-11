package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.entidades.ReservaSilla;
import co.edu.uniquindio.unitravel.entidades.Silla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaSillaRepo extends JpaRepository<ReservaSilla, Integer> {

    @Query("select s from Silla s join s.vuelo v where v.codigo=:codigo")
    List<Silla> sillasByVuelo(String codigo);
}
