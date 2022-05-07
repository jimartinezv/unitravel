package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.entidades.ReservaHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaHabitacionRepo extends JpaRepository<ReservaHabitacion, Integer> {

    @Query("select rh from ReservaHabitacion rh join rh.reserva r where r.codigo=:codigoReserva ")
    List<ReservaHabitacion> reservaHabitacionByReserva(Integer codigoReserva);
}
