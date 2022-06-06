package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.entidades.Habitacion;
import co.edu.uniquindio.unitravel.entidades.ReservaHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaHabitacionRepo extends JpaRepository<ReservaHabitacion, Integer> {

    @Query("select h from Habitacion h join h.reservaHabitaciones rs join rs.reserva r where h.codigo=:codigo and ((r.fechaInicio <=:fecha) and (r.fechaFin>=:fecha2))  ")
    Habitacion habitaciones(Integer codigo,LocalDate fecha, LocalDate fecha2);

    @Query("select rh from ReservaHabitacion rh join rh.habitacion h where h.codigo=:codigo")
    List<ReservaHabitacion> reservasByHabitacion(Integer codigo);

    //List<ReservaHabitacion> findByCodigoAndAndPrecio();

    //@Query("select h.codigo from Reserva r inner join r.reservaHabitacions rs inner join rs.habitacion h where r.fechaInicio <= :fechaI and r.fechaFin>=:fechaF")
    //String buscarReservaPorFecha(Integer codigoHabitacion, LocalDate fechaI, LocalDate fechaF);
}
